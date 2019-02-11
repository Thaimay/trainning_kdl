package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.ProjectSectionProgress;
import jp.co.world.storedevelopment.model.mapper.ProjectSectionProgressRepositoryMapper;

public class ProjectSectionProgressRepository
		extends ProjectRelatedRepository<ProjectSectionProgress, ProjectSectionProgressRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ProjectSectionProgress.class);
	}

	@Override
	protected ProjectSectionProgressRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ProjectSectionProgressRepositoryMapper.class);
	}

	public Optional<ProjectSectionProgress> getCurrentSectionProgressByProjectId(Long id) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.getCurrentSectionProgressByProjectId(id));
		});
	}

	public Optional<ProjectSectionProgress> getNegotiationSectionProgressByProjectId(Long id) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.getNegotiationSectionProgressByProjectId(id));
		});
	}

	public Optional<ProjectSectionProgress> getHistoryCurrentSectionProgressByProjectId(Long id) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.getHistoryCurrentSectionProgressByProjectId(id));
		});
	}

	public Optional<ProjectSectionProgress> getHistoryNegotiationSectionProgressByProjectId(Long id) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.getHistoryNegotiationSectionProgressByProjectId(id));
		});
	}
}
