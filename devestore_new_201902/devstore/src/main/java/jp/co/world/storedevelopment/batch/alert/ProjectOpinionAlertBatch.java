package jp.co.world.storedevelopment.batch.alert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.MTodoBatchSetting;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.Todo;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectOpinionRepository;
import jp.co.world.storedevelopment.model.mapper.repository.TodoRepository;

public class ProjectOpinionAlertBatch extends BaseAlertBatch<Project> {
	
	public static void main(String[] args) {
		new ProjectOpinionAlertBatch().write();
	}
	
	public ProjectOpinionAlertBatch() {
	}
	
	@Override
	public void execute() {
		new TodoRepository().deleteByDetailsDivisionId(20);
		
		Optional<MTodoBatchSetting> config = MTodoBatchSetting.opinionRecordInAdvance();

		config.ifPresent(c -> {
			execute(c);
		});		
	}
	
	public void execute(MTodoBatchSetting config) {
		Optional<MTodoBatchSetting> target = MTodoBatchSetting.opinionTargetProjectCategoryId();

		target.ifPresent(t -> {
			List<Project> list = new ProjectOpinionRepository().findAlert(t.getValue());

			List<Project> filter = list.stream().filter(p -> {
				return p.correntArticleReviewScheduleAlert(Integer.parseInt(config.getValue()));
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
		model.setDetailsDivisionId(20);
		model.setContent(t.articleReviewMessage());
		model.create();		
	}

}
