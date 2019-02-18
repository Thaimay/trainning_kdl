package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.ProjectOpinion;
import jp.co.world.storedevelopment.model.mapper.ProjectOpinionRepositoryMapper;

public class ProjectOpinionRepository extends ProjectRelatedRepository<ProjectOpinion, ProjectOpinionRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ProjectOpinion.class);
	}

	@Override
	protected ProjectOpinionRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ProjectOpinionRepositoryMapper.class);
	}
	
	public List<Project> findAlert(String target) {
		return execute((mapper) -> {
			return mapper.findAlert(target);
		});
	}
	
	public List<Project> findConsenseAlert() {
		return execute((mapper) -> {
			return mapper.findConsenseAlert();
		});
	}

	public Optional<ProjectOpinion> getBranchOpinion(Long id) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.getProjectOpinion(id, "BRANCH"));
		});
	}

	public Optional<ProjectOpinion> getViewOpinion(Long id) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.getProjectOpinion(id, "VIEW"));
		});
	}

	public Optional<ProjectOpinion> getBuOpinion(Long id) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.getProjectOpinion(id, "BU"));
		});
	}

	public Optional<ProjectOpinion> getHistoryBranchOpinion(Long id) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.getHistoryProjectOpinion(id, "BRANCH"));
		});
	}

	public Optional<ProjectOpinion> getHistoryViewOpinion(Long id) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.getHistoryProjectOpinion(id, "VIEW"));
		});
	}

	public Optional<ProjectOpinion> getHistoryBuOpinion(Long id) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.getHistoryProjectOpinion(id, "BU"));
		});
	}
}
