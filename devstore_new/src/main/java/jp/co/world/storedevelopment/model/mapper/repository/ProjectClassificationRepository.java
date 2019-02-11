package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.ProjectClassification;
import jp.co.world.storedevelopment.model.mapper.ProjectClassificationRepositoryMapper;

public class ProjectClassificationRepository
		extends Repository<ProjectClassification, ProjectClassificationRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ProjectClassification.class);
	}

	@Override
	protected ProjectClassificationRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ProjectClassificationRepositoryMapper.class);
	}

	private List<ProjectClassification> getByClassification(String classification, Long categoryId) {
		return execute((mapper) -> {
			return mapper.getClassificationByCategory(classification, categoryId);
		});
	}

	private List<ProjectClassification> getByClassification(String classification) {
		return execute((mapper) -> {
			return mapper.getClassificationByClassification(classification);
		});
	}

	public List<ProjectClassification> getSuspensionClassification(Long categoryId) {
		return this.getByClassification(ProjectClassification.SUSPENSION, categoryId);
	}

	public List<ProjectClassification> getLandingClassification(Long categoryId) {
		return this.getByClassification(ProjectClassification.LANDING, categoryId);
	}

	public List<ProjectClassification> getLandingPossibilityClassification(Long categoryId) {
		return this.getByClassification(ProjectClassification.LANDING_POSSIBILITY, categoryId);
	}

	public List<ProjectClassification> getProjectPlanClassification(Long categoryId) {
		return this.getByClassification(ProjectClassification.PROJECT_PLAN, categoryId);
	}

	public List<ProjectClassification> getSuspensionClassification() {
		return this.getByClassification(ProjectClassification.SUSPENSION);
	}

	public List<ProjectClassification> getLandingClassification() {
		return this.getByClassification(ProjectClassification.LANDING);
	}

	public List<ProjectClassification> getLandingPossibilityClassification() {
		return this.getByClassification(ProjectClassification.LANDING_POSSIBILITY);
	}

	public List<ProjectClassification> getProjectPlanClassification() {
		return this.getByClassification(ProjectClassification.PROJECT_PLAN);
	}
	public ProjectClassification getLandingClassificationChild(Long categoryId) {
		return execute((mapper) -> {
			return mapper.getClassificationChild(categoryId);
		});
	}
	public List<ProjectClassification> getClassificationCategoryShortName(Long categoryId) {
		return this.getByClassification(ProjectClassification.PROJECT_CATEGORY_SHORT_NAME, categoryId);
	}
	public ProjectClassification getClassificationColor(Long categoryId) {
		return execute((mapper) -> {
			return mapper.findClassificationColor(categoryId);
		});
	}
	public List<ProjectClassification> getProgressCompanyClassification() {
		return this.getByClassification(ProjectClassification.PROJECT_PROGRESS_COMPANY_SEARCH_LABEL);
	}
	public List<ProjectClassification> getProgressNegotiationClassification() {
		return this.getByClassification(ProjectClassification.PROJECT_PROGRESS_NEGOTIATION_SEARCH_LABEL);
	}
	public List<ProjectClassification> getRequestorReasonClassification() {
		return this.getByClassification(ProjectClassification.PROJECT_REQUESTOR_REASON);
	}
}
