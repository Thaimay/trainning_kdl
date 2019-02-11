package jp.co.world.storedevelopment.batch.alert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.MTodoBatchSetting;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.SendReserve;
import jp.co.world.storedevelopment.model.Todo;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectRepository;
import jp.co.world.storedevelopment.model.mapper.repository.TodoRepository;

public class ProjectExternalAlertBatch extends BaseAlertBatch<Project> {
	public static void main(String[] args) {
		new ProjectExternalAlertBatch().write();
	}
	
	public ProjectExternalAlertBatch() {
	}
	
	@Override
	public void execute() {
		new TodoRepository().deleteByDetailsDivisionId(17);
		new TodoRepository().deleteByDetailsDivisionId(18);
		new TodoRepository().deleteByDetailsDivisionId(19);

		Optional<MTodoBatchSetting> config = MTodoBatchSetting.externalReleaseInAdvance();

		config.ifPresent(c -> {
			execute(c);
		});
	}
	
	public void execute(MTodoBatchSetting config) {
		List<Project> list = new ProjectRepository().findAlert();
		
		List<Project> prevList = list.stream().filter(t -> {
			return t.correntPrev(Integer.parseInt(config.getValue()));
		}).collect(Collectors.toList());

		List<Project> theDayList = list.stream().filter(t -> {
			return t.correntTheDay();
		}).collect(Collectors.toList());

		List<Project> expiredList = list.stream().filter(t -> {
			return t.correntExpired();
		}).collect(Collectors.toList());

		todo(prevList);
		todo(theDayList);
		todo(expiredList);
	}

	@Override
	public List<Account> targetAccount(Project model) {
		return model.accounts();
	}

	@Override
	public void createTodo(Project t, Account a) {
		Todo model = new Todo();
		model.setProjectId(t.getId());
		model.setAccountId(a.getId());
		settingTodo(t, model);
		model.create();		
	}

	private void settingTodo(Project t, Todo todo) {
		MTodoBatchSetting config = MTodoBatchSetting.taskTermInAdvance().orElseGet(() -> {
			throw new IllegalStateException();
		});

		if (t.correntPrev(config.valueInteger())) {
			todo.setDetailsDivisionId(17);
			todo.setContent(t.prevMessage());			
		} else if (t.correntTheDay()) {
			todo.setDetailsDivisionId(18);
			todo.setContent(t.theDayMessage());			
		} else {
			todo.setDetailsDivisionId(19);
			todo.setContent(t.expiredMessage());			
		}
	}

	@Override
	public void reserve(List<Project> list) {
		allAccount(list).forEach(v -> {
			List<Project> targets = targetProject(list, v);
			
			Project task = targets.get(0);
			SendReserve reserve = new SendReserve();

			reserve.setEmployeeCdList(v.getEmployeCode());
			settingReserveMessage(task, reserve, targets.size());
			reserve.create();
		});
	}
	
	public void settingReserveMessage(Project t, SendReserve r, Integer size) {
		MTodoBatchSetting config = MTodoBatchSetting.taskTermInAdvance().orElseGet(() -> {
			throw new IllegalStateException();
		});
		
		String message = "";

		if (t.correntPrev(config.valueInteger())) {
			message += t.prevMessage();
		} else if (t.correntTheDay()) {
			message += t.theDayMessage();
		} else {
			message += t.expiredMessage();
		}
		
		if (size > 1) {
			message += String.format("\n他 %s件", size);
		}
		
		r.setMessage(message);
	}

}
