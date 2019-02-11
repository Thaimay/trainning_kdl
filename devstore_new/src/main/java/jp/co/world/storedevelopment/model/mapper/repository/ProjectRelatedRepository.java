package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;

import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.mapper.ProjectRelatedRepositoryMapper;

public abstract class ProjectRelatedRepository<T, M extends ProjectRelatedRepositoryMapper<T>>
		extends Repository<T, M> {

	public List<T> findByProjectId(Long projectId) {
		return execute(mapper -> {
			return mapper.findByProjectId(tableName(), projectId);
		});
	}

	public int deleteByProjectId(Long projectId) {
		return execute(mapper -> {
			return mapper.deleteByProjectId(tableName(), projectId);
		});
	}

	public List<T> findHistoryByProjectId(Long projectId) {
		return execute(mapper -> {
			return mapper.findHistoryByProjectId(tableName(), projectId);
		});
	}
}
