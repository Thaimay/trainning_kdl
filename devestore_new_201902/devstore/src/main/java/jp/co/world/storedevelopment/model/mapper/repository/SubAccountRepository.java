package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.SubAccount;
import jp.co.world.storedevelopment.model.mapper.SubAccountRepositoryMapper;

public class SubAccountRepository extends Repository<SubAccount, SubAccountRepositoryMapper> {

	@Override
	protected SubAccountRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(SubAccountRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(SubAccount.class);
	}

	public Optional<SubAccount> findByEmployeeCode(String employeeCd) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByEmployCode(employeeCd));
		});
	}

}
