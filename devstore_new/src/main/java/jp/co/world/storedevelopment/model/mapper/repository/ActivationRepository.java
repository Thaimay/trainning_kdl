package jp.co.world.storedevelopment.model.mapper.repository;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.Activation;
import jp.co.world.storedevelopment.model.mapper.ActivationRepositoryMapper;

public class ActivationRepository extends BuildingRelatedRepository<Activation, ActivationRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(Activation.class);
	}

	@Override
	protected ActivationRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ActivationRepositoryMapper.class);
	}

}
