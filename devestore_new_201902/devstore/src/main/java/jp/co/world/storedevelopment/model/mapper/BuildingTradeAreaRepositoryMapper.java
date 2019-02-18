package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.BuildingTradeArea;

public interface BuildingTradeAreaRepositoryMapper extends RepositoryMapper<BuildingTradeArea> {

	@Select("select * from building_trade_area where is_deleted is false and building_cd = trim('${buildingCd}') LIMIT 1")
	BuildingTradeArea getBuildingTradeAreaByBuildingCd(@Param("buildingCd") String buildingCd);
}
