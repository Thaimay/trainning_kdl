package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.AccountData;
import jp.co.world.storedevelopment.model.mapper.AccountDataRepositoryMapper;

public class AccountDataRepository extends Repository<AccountData, AccountDataRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(AccountData.class);
	}

	@Override
	protected AccountDataRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(AccountDataRepositoryMapper.class);
	}

	public Optional<AccountData> findByAccountId(Long id) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByAccountId(id));
		});
	}
}
