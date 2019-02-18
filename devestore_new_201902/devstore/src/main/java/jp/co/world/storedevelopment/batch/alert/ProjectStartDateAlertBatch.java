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

public class ProjectStartDateAlertBatch extends BaseAlertBatch<Project> {
	public static void main(String[] args) {
		new ProjectStartDateAlertBatch().write();
	}
	
	public ProjectStartDateAlertBatch() {
	}
	
	@Override
	public void execute() {
		new TodoRepository().deleteByDetailsDivisionId(22);
		
		Optional<MTodoBatchSetting> config = MTodoBatchSetting.startDatetimeExpired();

		config.ifPresent(c -> {
			execute(c);
		});		
	}
	
	public void execute(MTodoBatchSetting config) {
		Optional<MTodoBatchSetting> target = MTodoBatchSetting.closeCategoryId();

		target.ifPresent(t -> {
			List<Project> list = new ProjectRepository().findStartDateAlert(t.getValue());

			List<Project> filter = list.stream().filter(p -> {
				return p.correntStartDateAlert(Integer.parseInt(config.getValue()));
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
		model.setDetailsDivisionId(22);
		model.setContent(t.startDateMessage());
		model.create();		
	}

	@Override
	public void reserve(List<Project> list) {
		allAccount(list).forEach(v -> {
			List<Project> targets = targetProject(list, v);
			
			Project task = targets.get(0);
			String message = task.startDateMessage();
			SendReserve reserve = new SendReserve();

			if (targets.size() > 1) {
				message += String.format("\n他 %s件", targets.size());
			}

			reserve.setEmployeeCdList(v.getEmployeCode());
			reserve.setMessage(message);
			reserve.create();
		});
	}

}
