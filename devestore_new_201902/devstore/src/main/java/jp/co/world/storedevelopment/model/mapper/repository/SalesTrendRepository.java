package jp.co.world.storedevelopment.model.mapper.repository;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.SalesTrend;
import jp.co.world.storedevelopment.model.mapper.SalesTrendRepositoryMapper;

public class SalesTrendRepository extends BuildingRelatedRepository<SalesTrend, SalesTrendRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(SalesTrend.class);
	}

	@Override
	protected SalesTrendRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(SalesTrendRepositoryMapper.class);
	}

}
