package jp.co.world.storedevelopment.batch.actionstatus;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.MProjectActionStatus;
import jp.co.world.storedevelopment.model.ProjectSectionProgress;
import jp.co.world.storedevelopment.model.mapper.repository.MProjectActionStatusRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectSectionProgressRepository;

public class ProjectActionStatusUpdateBatch {
	public static void main(String[] args) {
		System.out.println("処理を開始します");
		
		new ProjectRepository().findAllNotDeleted().stream().forEach(p -> {
			if (p.getProjectCategoryId() == null || p.getiSalesChannelId() == null) {
				return;
			}
			
			List<MProjectActionStatus> list = new MProjectActionStatusRepository().findSortDescBy(p.getProjectCategoryId(), p.getiSalesChannelId());
			
			if (list.size() == 0) {
				return;
			}

			System.out.println(String.format("処理開始ID:%s", p.getId()));
			
			String old = "未設定";
			
			Optional<MProjectActionStatus> status = p.actionStatus();
			if (status.isPresent()) {
				old = status.get().getName();
			}
			
			p.registerStartDate(p.getStartDate());
			p.registerArticleReviewResult();
			p.registerManagementResult();
			p.registerInvestmentResult();
			
			Optional<ProjectSectionProgress> negotiationSectionOption = new ProjectSectionProgressRepository()
					.getNegotiationSectionProgressByProjectId(p.getId());
			negotiationSectionOption.ifPresent(negotiationSection -> {
				p.checkOpeningDate(negotiationSection);
				p.registerActionStatus(p.getiSalesChannelId(), negotiationSection);
			});

			
			if (p.hasNegotiation()) {
				p.registerProjectNegotiation();
			}
			
			p.registerActionStatus(p.getiSalesChannelId());
			p.update();
			
			MProjectActionStatus newStatus = p.actionStatus().orElseGet(() -> {
				throw new IllegalStateException(String.format("行動ステータスが設定できません ID:%s", p.getId()));
			});

			
			System.out.println(String.format("ID:%s 名前:%s\n    %s -> %s", p.getId(), p.getTitle(), old, newStatus.getName()));
		});
		
		System.out.println("処理が終了しました");
	}
}
