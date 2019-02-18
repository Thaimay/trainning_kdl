package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.IRentCondition;
import jp.co.world.storedevelopment.model.mapper.IRentConditionRepositoryMapper;

public class IRentConditionRepository extends Repository<IRentCondition, IRentConditionRepositoryMapper> {

	@Override
	protected IRentConditionRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IRentConditionRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IRentCondition.class);
	}

	public Optional<IRentCondition> findByImportCode(IRentCondition model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getRentConditionId().toString()));
		});
	}
}
