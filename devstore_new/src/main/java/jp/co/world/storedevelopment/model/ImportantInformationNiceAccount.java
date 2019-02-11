package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ImportantInformationNiceAccountModelMapper;

public class ImportantInformationNiceAccount extends ActiveModel<ImportantInformationNiceAccount> {
	private Long importantInformationId;
	private Long accountId;

	public ImportantInformationNiceAccount() {
	}

	public ImportantInformationNiceAccount(Long importantInformationId, Long accountId) {
		setImportantInformationId(importantInformationId);
		setAccountId(accountId);
	}

	public Long getImportantInformationId() {
		return importantInformationId;
	}

	public void setImportantInformationId(Long importantInformationId) {
		this.importantInformationId = importantInformationId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	@Override
	protected ModelMapper<ImportantInformationNiceAccount> modelMapper(SqlSession session) {
		return session.getMapper(ImportantInformationNiceAccountModelMapper.class);
	}

}
