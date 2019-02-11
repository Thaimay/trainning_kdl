package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.ProjectContractProgress;
import jp.co.world.storedevelopment.model.mapper.ProjectContractProgressRepositoryMapper;

public class ProjectContractProgressRepository
		extends ProjectRelatedRepository<ProjectContractProgress, ProjectContractProgressRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ProjectContractProgress.class);
	}

	@Override
	protected ProjectContractProgressRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ProjectContractProgressRepositoryMapper.class);
	}

	public Optional<ProjectContractProgress> getCurrentContractByProjectId(Long id) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.getCurrentContractByProjectId(id));
		});
	}

	public Optional<ProjectContractProgress> getProgressContractByProjectId(Long id) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.getProgressContractByProjectId(id));
		});
	}

	public Optional<ProjectContractProgress> getHistoryCurrentContractByProjectId(Long id) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.getHistoryCurrentContractByProjectId(id));
		});
	}

	public Optional<ProjectContractProgress> getHistoryProgressContractByProjectId(Long id) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.getHistoryProgressContractByProjectId(id));
		});
	}
}
