package jp.co.world.storedevelopment.batch.actionstatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.ProjectSectionProgress;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectHistoryRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectSectionProgressRepository;
import jp.co.world.storedevelopment.utils.JsonParseUtils;

public class ProjectActionStatusBatch {

	public static void main(String[] args) {
		try {

			List<Project> list = new ProjectRepository().findOpeningStoreProject();

			list.forEach(project -> {
				String jsonCurrent = JsonParseUtils.parse(project);
				Optional<Long> iSalesChannelIdOption = Optional.ofNullable(project.getiSalesChannelId());
				iSalesChannelIdOption.ifPresent(iSalesChannelId -> {
					Optional<ProjectSectionProgress> negotiationSectionOption = new ProjectSectionProgressRepository()
							.getNegotiationSectionProgressByProjectId(project.getId());
					negotiationSectionOption.ifPresent(negotiationSection -> {
						project.checkOpeningDate(negotiationSection);
						project.registerActionStatus(iSalesChannelId, negotiationSection);
					});
					
					String jsonChange = JsonParseUtils.parse(project);
					
					// update when data change
					if(!jsonCurrent.equals(jsonChange)) {
						// create project history
						new ProjectHistoryRepository().updateProjectHistory(project);
						
						// update project
						project.setUpdateDatetime(LocalDateTime.now());
						project.update();
					}
				});
			});
		} catch (Exception e) {
			e.printStackTrace();
            System.err.println(String.format("行動ステータスの更新に失敗しました"));
		}
	}
}