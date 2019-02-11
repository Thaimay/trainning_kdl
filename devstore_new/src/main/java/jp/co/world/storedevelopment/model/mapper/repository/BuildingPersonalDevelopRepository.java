package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.BuildingPersonalDevelop;
import jp.co.world.storedevelopment.model.mapper.BuildingPersonalDevelopRepositoryMapper;

public class BuildingPersonalDevelopRepository
		extends BuildingRelatedRepository<BuildingPersonalDevelop, BuildingPersonalDevelopRepositoryMapper> {

	@Override
	protected BuildingPersonalDevelopRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(BuildingPersonalDevelopRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(BuildingPersonalDevelop.class);
	}

	public List<Long> getListStoreDevAccountId(String buildingCd) {
		return execute((mapper) -> {
			return mapper.getListStoreDevAccountId(buildingCd);
		});
	}

}
