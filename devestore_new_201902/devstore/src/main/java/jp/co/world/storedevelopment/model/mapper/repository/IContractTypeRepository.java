package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.IContractType;
import jp.co.world.storedevelopment.model.mapper.IContractTypeRepositoryMapper;

public class IContractTypeRepository extends Repository<IContractType, IContractTypeRepositoryMapper> {

	@Override
	protected IContractTypeRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IContractTypeRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IContractType.class);
	}

	public IContractType getByContractTypeId(Long contractTypeId) {
		return execute((mapper) -> {
			return mapper.getByContractTypeId(contractTypeId);
		});
	}

	public Optional<IContractType> findByImportCode(IContractType model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getContractTypeCd()));
		});
	}
}
