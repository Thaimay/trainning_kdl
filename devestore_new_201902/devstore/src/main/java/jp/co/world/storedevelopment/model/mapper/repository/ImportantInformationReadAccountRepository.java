package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.ImportantInformationReadAccount;
import jp.co.world.storedevelopment.model.mapper.ImportantInformationReadAccountRepositoryMapper;

public class ImportantInformationReadAccountRepository
		extends Repository<ImportantInformationReadAccount, ImportantInformationReadAccountRepositoryMapper> {

	@Override
	protected ImportantInformationReadAccountRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ImportantInformationReadAccountRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ImportantInformationReadAccount.class);
	}

	public Optional<ImportantInformationReadAccount> findBy(Long importantInformationId, Long accountId) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findBy(importantInformationId, accountId));
		});
	}
}
