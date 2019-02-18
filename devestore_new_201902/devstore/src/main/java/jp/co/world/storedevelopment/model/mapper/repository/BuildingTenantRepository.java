package jp.co.world.storedevelopment.model.mapper.repository;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.BuildingTenant;
import jp.co.world.storedevelopment.model.mapper.BuildingTenantRepositoryMapper;

public class BuildingTenantRepository
		extends BuildingRelatedRepository<BuildingTenant, BuildingTenantRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(BuildingTenant.class);
	}

	@Override
	protected BuildingTenantRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(BuildingTenantRepositoryMapper.class);
	}

}
