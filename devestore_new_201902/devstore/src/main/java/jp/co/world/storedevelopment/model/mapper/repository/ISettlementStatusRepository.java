package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.ISettlementStatus;
import jp.co.world.storedevelopment.model.mapper.ISettlementStatusRepositoryMapper;

public class ISettlementStatusRepository extends Repository<ISettlementStatus, ISettlementStatusRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ISettlementStatus.class);
	}

	@Override
	protected ISettlementStatusRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ISettlementStatusRepositoryMapper.class);
	}

	public Optional<ISettlementStatus> findByImportCode(ISettlementStatus model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getSettlementStatusId().toString()));
		});
	}
}
