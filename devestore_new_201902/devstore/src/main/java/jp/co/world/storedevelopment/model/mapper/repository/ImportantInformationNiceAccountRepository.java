package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.ImportantInformationNiceAccount;
import jp.co.world.storedevelopment.model.mapper.ImportantInformationNiceAccountRepositoryMapper;

public class ImportantInformationNiceAccountRepository
		extends Repository<ImportantInformationNiceAccount, ImportantInformationNiceAccountRepositoryMapper> {

	@Override
	protected ImportantInformationNiceAccountRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ImportantInformationNiceAccountRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ImportantInformationNiceAccount.class);
	}

	public Optional<ImportantInformationNiceAccount> findBy(Long importantInformationid, Long accountId) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findBy(importantInformationid, accountId));
		});
	}

	public int countBy(Long importantInformationId) {
		return execute((mapper) -> {
			return mapper.countBy(importantInformationId);
		});
	}
}
