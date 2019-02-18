package jp.co.world.storedevelopment.sp.controller.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.ImportantInformation;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;

public class ImportantInformationDetailDTO implements DTO<ImportantInformation> {
	private String content;
	private String division;
	private CreateAccountDTO account = new CreateAccountDTO();
	private String updateAccountCode;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime updateDatetime;

	public ImportantInformationDetailDTO() {
	}

	public ImportantInformationDetailDTO(ImportantInformation i) {
		this.copyProperties(this, i);
	}

	public ImportantInformationDetailDTO(ImportantInformation i, Account user) {
		Account account = new AccountRepository().findByCode(i.getUpdateAccountCode())
				.orElseThrow(() -> new IllegalStateException("存在しないアカウントコードです:"));
		setAccount(new CreateAccountDTO(account, i));

		setContent(i.getContent());
		setDivision(i.getDivision());
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public CreateAccountDTO getAccount() {
		return account;
	}

	public void setAccount(CreateAccountDTO account) {
		this.account = account;
	}

	public String getUpdateAccountCode() {
		return updateAccountCode;
	}

	public void setUpdateAccountCode(String updateAccountCode) {
		this.updateAccountCode = updateAccountCode;
	}

	public LocalDateTime getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(LocalDateTime updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	@Override
	public ImportantInformation createModel() {
		return new ImportantInformation();
	}

}
