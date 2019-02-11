package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.IContractKind;
import jp.co.world.storedevelopment.model.mapper.IContractKindRepositoryMapper;

public class IContractKindRepository extends Repository<IContractKind, IContractKindRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IContractKind.class);
	}

	@Override
	protected IContractKindRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IContractKindRepositoryMapper.class);
	}

	public Optional<IContractKind> findByImportCode(IContractKind model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getContractKindCd()));
		});
	}
}
