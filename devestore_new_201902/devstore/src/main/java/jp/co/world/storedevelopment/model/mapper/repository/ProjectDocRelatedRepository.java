package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;

import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.mapper.ProjectDocRelatedRepositoryMapper;

public abstract class ProjectDocRelatedRepository<T, M extends ProjectDocRelatedRepositoryMapper<T>> 
extends Repository<T, M>{
//	public List<T> findByOriginBuildingId(Long originBuildingId) {
//		return execute(mapper -> {
//			return mapper.findByOriginBuildingId(tableName(), originBuildingId);
//		});
//	}

	public List<T> findByProjectId(Long projectId) {
		return execute(mapper -> {
			return mapper.findByProjectDocId(tableName(), projectId);
		});
	}


}
