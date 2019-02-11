package jp.co.world.storedevelopment.sp.controller.dto;

import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.RecentChange;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;

public class RecentChangeNegotiationListDTO {
	private Long id;
	private String content;
	private Long accountId;
	private Long negotiationId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime updateDatetime;

	public RecentChangeNegotiationListDTO(RecentChange rc) {
		setId(rc.getId());
		setContent(rc.getContent());
		setUpdateDatetime(rc.getUpdateDatetime());
		setNegotiationId(rc.getNeogtationId());
		setAccountId(rc.getAccountId());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public LocalDateTime getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(LocalDateTime updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public String getUpdateAccountName() {
		if (getAccountId() == null || getAccountId() <= 0) {
			return "";
		}

		Optional<Account> updateAccount = new AccountRepository().findById(getAccountId());
		return updateAccount.isPresent() ? updateAccount.get().getFullName() : "";
	}

	public Long getNegotiationId() {
		return negotiationId;
	}

	public void setNegotiationId(Long negotiationId) {
		this.negotiationId = negotiationId;
	}

}
