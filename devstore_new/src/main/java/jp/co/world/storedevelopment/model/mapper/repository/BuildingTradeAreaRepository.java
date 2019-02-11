package jp.co.world.storedevelopment.model.mapper.repository;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.BuildingTradeArea;
import jp.co.world.storedevelopment.model.mapper.BuildingTradeAreaRepositoryMapper;

public class BuildingTradeAreaRepository extends Repository<BuildingTradeArea, BuildingTradeAreaRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(BuildingTradeArea.class);
	}

	@Override
	protected BuildingTradeAreaRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(BuildingTradeAreaRepositoryMapper.class);
	}

	public BuildingTradeArea getBuildingTradeAreaByBuildingCd(String buildingCd) {
		return execute(mapper -> {
			return mapper.getBuildingTradeAreaByBuildingCd(buildingCd);
		});
	}

}
