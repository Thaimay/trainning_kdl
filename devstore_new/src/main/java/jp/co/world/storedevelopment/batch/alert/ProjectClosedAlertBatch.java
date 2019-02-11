package jp.co.world.storedevelopment.batch.alert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.MTodoBatchSetting;
import jp.co.world.storedevelopment.model.Mail;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.SendReserve;
import jp.co.world.storedevelopment.model.Todo;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectRepository;
import jp.co.world.storedevelopment.model.mapper.repository.TodoRepository;

public class ProjectClosedAlertBatch extends BaseAlertBatch<Project> {
	public static void main(String[] args) {
		new ProjectClosedAlertBatch().write();
	}
	
	public ProjectClosedAlertBatch() {
	}
	
	public void execute() {
		new TodoRepository().deleteByDetailsDivisionId(21);
		
		Optional<MTodoBatchSetting> config = MTodoBatchSetting.preventRetreat();
	
		config.ifPresent(c -> {
			execute(c);
		});
	}

	public void execute(MTodoBatchSetting config) {
		Optional<MTodoBatchSetting> target = MTodoBatchSetting.closeCategoryId();

		target.ifPresent(t -> {
			List<Project> list = new ProjectRepository().findStartDateAlert(t.getValue());

			List<Project> filter = list.stream().filter(p -> {
				return p.correntCloses(Integer.parseInt(config.getValue()));
			}).collect(Collectors.toList());

			todo(filter);
		});
	}

	@Override
	public List<Account> targetAccount(Project model) {
		return model.storeSalesAccounts();
	}

	@Override
	public void createTodo(Project t, Account a) {
		Todo model = new Todo();
		model.setProjectId(t.getId());
		model.setAccountId(a.getId());
		model.setDetailsDivisionId(21);
		model.setContent(t.closedMessage());
		model.create();		
	}

	@Override
	public void reserve(List<Project> list) {
		allAccount(list).forEach(v -> {
			List<Project> targets = targetProject(list, v);
			
			Project task = targets.get(0);
			String message = task.closedMessage();
			SendReserve reserve = new SendReserve();

			if (targets.size() > 1) {
				message += String.format("\n他 %s件", targets.size());
			}

			reserve.setEmployeeCdList(v.getEmployeCode());
			reserve.setMessage(message);
			reserve.create();
		});
	}
	
	@Override
	public void mail(List<Project> list) {
		if (canCreate(list)) {
			Project task = list.get(0);
			Mail reserve = new Mail();
			reserve.setMailAddress(accountEmployeeCodes(list));
			reserve.setBody(String.format("%s %s件あります", task.closedMessage(), list.size()));
			reserve.create();
		}
	}

}
