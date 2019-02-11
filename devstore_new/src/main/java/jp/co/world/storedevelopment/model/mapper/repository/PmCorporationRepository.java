package jp.co.world.storedevelopment.model.mapper.repository;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.PmCorporation;
import jp.co.world.storedevelopment.model.mapper.PmCorporationRepositoryMapper;

public class PmCorporationRepository extends BuildingRelatedRepository<PmCorporation, PmCorporationRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(PmCorporation.class);
	}

	@Override
	protected PmCorporationRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(PmCorporationRepositoryMapper.class);
	}

}
