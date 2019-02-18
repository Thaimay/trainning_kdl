package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.IRentType;
import jp.co.world.storedevelopment.model.mapper.IRentTypeRepositoryMapper;

public class IRentTypeRepository extends Repository<IRentType, IRentTypeRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IRentType.class);
	}

	@Override
	protected IRentTypeRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IRentTypeRepositoryMapper.class);
	}

	public Optional<IRentType> findByImportCode(IRentType model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getRentTypeCd()));
		});
	}
}
