package jp.co.world.storedevelopment.batch.alert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.MTodoBatchSetting;
import jp.co.world.storedevelopment.model.Mail;
import jp.co.world.storedevelopment.model.ProjectTask;
import jp.co.world.storedevelopment.model.SendReserve;
import jp.co.world.storedevelopment.model.Todo;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectTaskRepository;
import jp.co.world.storedevelopment.model.mapper.repository.TodoRepository;

public class ProjectTaskAlertBatch extends BaseAlertBatch<ProjectTask> {
	public static void main(String[] args) {
		new ProjectTaskAlertBatch().write();
	}
	
	public ProjectTaskAlertBatch() {
	}
	
	@Override
	public void execute() {
		new TodoRepository().deleteByDetailsDivisionId(12);
		new TodoRepository().deleteByDetailsDivisionId(13);
		new TodoRepository().deleteByDetailsDivisionId(14);
	
		Optional<MTodoBatchSetting> config = MTodoBatchSetting.taskTermInAdvance();
		
		config.ifPresent(c -> {
			execute(c);
		});		
	}
	
	public void execute(MTodoBatchSetting config) {
		List<ProjectTask> list = new ProjectTaskRepository().findAllTargetBatch();

		List<ProjectTask> prevList = list.stream().filter(t -> {
			return t.correctPrev(config.valueInteger());
		}).collect(Collectors.toList());

		List<ProjectTask> theDayList = list.stream().filter(t -> {
			return t.correntTheDay();
		}).collect(Collectors.toList());

		List<ProjectTask> expiredList = list.stream().filter(t -> {
			return t.correntExpired();
		}).collect(Collectors.toList());
		
		todo(prevList);
		todo(theDayList);
		todo(expiredList);
	}
	
	@Override
	public List<Account> targetAccount(ProjectTask model) {
		return model.accounts();
	}
	
	@Override
	public void createTodo(ProjectTask t, Account a) {
		Todo model = new Todo();
		model.setProjectId(t.getProjectId());
		model.setAccountId(a.getId());
		settingTodo(t, model);
		model.create();		
	}
	
	private void settingTodo(ProjectTask t, Todo todo) {
		MTodoBatchSetting config = MTodoBatchSetting.taskTermInAdvance().orElseGet(() -> {
			throw new IllegalStateException();
		});

		if (t.correctPrev(config.valueInteger())) {
			todo.setDetailsDivisionId(12);
			todo.setContent(t.prevMessage());			
		} else if (t.correntTheDay()) {
			todo.setDetailsDivisionId(13);
			todo.setContent(t.theDayMessage());			
		} else {
			todo.setDetailsDivisionId(14);
			todo.setContent(t.expiredMessage());			
		}
	}
		
	@Override
	public void reserve(List<ProjectTask> list) {
		allAccount(list).forEach(v -> {
			List<ProjectTask> targets = targetProject(list, v).stream().map(t -> {
				return t;
			}).distinct().collect(Collectors.toList());
			
			ProjectTask task = targets.get(0);
			SendReserve reserve = new SendReserve();
			reserve.setEmployeeCdList(v.getEmployeCode());
			settingReserveMessage(task, reserve, list.size(), targets.size());
			reserve.create();
		});
	}
	
	public void settingReserveMessage(ProjectTask t, SendReserve r, Integer size, Integer projectNumber) {
		MTodoBatchSetting config = MTodoBatchSetting.taskTermInAdvance().orElseGet(() -> {
			throw new IllegalStateException();
		});

		String message = "";

		if (t.correctPrev(config.valueInteger())) {
			message = t.prevMessage();
		} else if (t.correntTheDay()) {
			message = t.theDayMessage();
		} else {
			message = t.expiredMessage();
		}
		
		if (projectNumber > 1) {
			message += String.format("\n他 %s件", projectNumber);
		}
		
		r.setMessage(message);
	}

	@Override
	public void mail(List<ProjectTask> list) {
		if (canCreate(list)) {
			Mail mail = new Mail();
			mail.setMailAddress(accountEmployeeCodes(list));
			Boolean isCreate = settingMail(list, mail);
			
			if (isCreate) {
				mail.create();				
			}
		}
	}
	
	public Boolean settingMail(List<ProjectTask> list, Mail mail) {
		ProjectTask t = list.get(0);
		MTodoBatchSetting config = MTodoBatchSetting.taskTermInAdvance().orElseGet(() -> {
			throw new IllegalStateException();
		});

		if (t.correctPrev(config.valueInteger())) {
			return false;
		} else if (t.correntTheDay()) {
			mail.setProjectTaskTheDayTemplate(list);
		} else if (t.correntExpired()) {
			mail.setProjectTaskExpiredTemplate(list);
		}
		return true;
	}
}
