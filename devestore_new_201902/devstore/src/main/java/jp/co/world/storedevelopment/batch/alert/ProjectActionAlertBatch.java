package jp.co.world.storedevelopment.batch.alert;

import java.util.List;
import java.util.stream.Collectors;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.SendReserve;
import jp.co.world.storedevelopment.model.Todo;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectOpinionRepository;
import jp.co.world.storedevelopment.model.mapper.repository.TodoRepository;

public class ProjectActionAlertBatch extends BaseAlertBatch<Project> {
	public static void main(String[] args) {		
		new ProjectActionAlertBatch().write();
	}
	
	public ProjectActionAlertBatch() {
	}
	
	public void execute() {
		new TodoRepository().deleteByDetailsDivisionId(16);

		List<Project> list = new ProjectOpinionRepository().findConsenseAlert();
		
		List<Project> filter = list.stream().filter(p -> {
			return p.correntConsensus();
		}).collect(Collectors.toList());
		
		todo(filter);
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
		model.setDetailsDivisionId(16);
		model.setContent(t.consenseMessage());
		model.create();		
	}

	@Override
	public void reserve(List<Project> list) {
			allAccount(list).forEach(v -> {
				List<Project> targets = targetProject(list, v);
				
				Project task = targets.get(0);
				String message = task.consenseMessage();
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
