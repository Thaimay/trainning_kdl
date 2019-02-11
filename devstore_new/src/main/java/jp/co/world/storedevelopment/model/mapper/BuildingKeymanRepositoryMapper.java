package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.BuildingKeyman;
import jp.co.world.storedevelopment.model.Keyman;

public interface BuildingKeymanRepositoryMapper extends BuildingRelatedRepositoryMapper<BuildingKeyman> {
	@Select("select * from BUILDING_KEYMAN where building_cd = ${buildingCode} AND business_card_id = ${businessCardId}")
	public BuildingKeyman existsBy(@Param("buildingCode") String buildingCode,
			@Param("businessCardId") Long businessCardId);

	@Select("select * from BUILDING_KEYMAN where building_cd = '${building.buildingCd}' AND keyman_id = ${keyman.id}")
	public BuildingKeyman existsByKeyman(@Param("building") Building building, @Param("keyman") Keyman keyman);
}
