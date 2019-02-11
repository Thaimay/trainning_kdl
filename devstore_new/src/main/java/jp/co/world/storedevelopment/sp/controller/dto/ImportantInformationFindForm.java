package jp.co.world.storedevelopment.sp.controller.dto;

import com.world.storedevelopment.utils.HankakuUtils;

import jp.co.world.storedevelopment.model.Account;

public class ImportantInformationFindForm extends FindFormDTO {
	private Long id;
	private String text;
	private Account account;

	public ImportantInformationFindForm() {
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHankakuText() {
		return getText() != null ? HankakuUtils.toHankaku(getText()) : "";
	}

}
