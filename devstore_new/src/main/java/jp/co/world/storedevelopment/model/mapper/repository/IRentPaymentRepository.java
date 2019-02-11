package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.IRentPayment;
import jp.co.world.storedevelopment.model.mapper.IRentPaymentRepositoryMapper;

public class IRentPaymentRepository extends Repository<IRentPayment, IRentPaymentRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IRentPayment.class);
	}

	@Override
	protected IRentPaymentRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IRentPaymentRepositoryMapper.class);
	}

	public Optional<IRentPayment> findByImportCode(IRentPayment model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getRentPaymentId().toString()));
		});
	}
}
