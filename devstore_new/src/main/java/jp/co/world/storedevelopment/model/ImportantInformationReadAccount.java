package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ImportantInformationReadAccountModelMapper;

public class ImportantInformationReadAccount extends ActiveModel<ImportantInformationReadAccount> {
	private Long importantInformationId;
	private Long accountId;

	public ImportantInformationReadAccount() {

	}

	public ImportantInformationReadAccount(Long importantInformationId, Long accountId) {
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
	protected ModelMapper<ImportantInformationReadAccount> modelMapper(SqlSession session) {
		return session.getMapper(ImportantInformationReadAccountModelMapper.class);
	}

}
