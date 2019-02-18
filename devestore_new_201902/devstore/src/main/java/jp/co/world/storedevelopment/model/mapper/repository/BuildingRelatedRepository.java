package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;

import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.mapper.BuildingRelatedRepositoryMapper;

public abstract class BuildingRelatedRepository<T, M extends BuildingRelatedRepositoryMapper<T>>
		extends Repository<T, M> {

	public List<T> findByOriginBuildingId(Long originBuildingId) {
		return execute(mapper -> {
			return mapper.findByOriginBuildingId(tableName(), originBuildingId);
		});
	}

	public List<T> findByBuildingId(Long buildingId) {
		return execute(mapper -> {
			return mapper.findByBuildingId(tableName(), buildingId);
		});
	}

	public List<T> findByBuildingCd(String buildingCd) {
		return execute(mapper -> {
			return mapper.findByBuildingCd(tableName(), buildingCd);
		});
	}

	public int deleteByBuildingId(Long buildingId) {
		return execute(mapper -> {
			return mapper.deleteByBuildingId(tableName(), buildingId);
		});
	}

	public int deleteByBuildingCd(String buildingCd) {
		return execute(mapper -> {
			return mapper.deleteByBuildingCd(tableName(), buildingCd);
		});
	}

	public List<T> findHistoryByBuildingHistoryId(Long buildingId) {
		return execute(mapper -> {
			return mapper.findHistoryByBuildingHistoryId(tableName(), buildingId);
		});
	}

	public List<T> findHistoryByBuildingHistoryCd(String buildingCd) {
		return execute(mapper -> {
			return mapper.findHistoryByBuildingHistoryCd(tableName(), buildingCd);
		});
	}
}
