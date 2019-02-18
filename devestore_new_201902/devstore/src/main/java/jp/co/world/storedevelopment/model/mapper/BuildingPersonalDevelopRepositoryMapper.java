package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import jp.co.world.storedevelopment.model.BuildingPersonalDevelop;

public interface BuildingPersonalDevelopRepositoryMapper
		extends BuildingRelatedRepositoryMapper<BuildingPersonalDevelop> {

	List<Long> getListStoreDevAccountId(@Param("buildingCd") String buildingCd);
}
