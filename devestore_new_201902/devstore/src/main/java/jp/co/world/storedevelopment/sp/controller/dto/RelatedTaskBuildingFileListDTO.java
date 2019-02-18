package jp.co.world.storedevelopment.sp.controller.dto;

import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.RelatedTask;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;

public class RelatedTaskBuildingFileListDTO {

	private Long id;
	private String content;
	private Long accountId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime updateDatetime;

	public RelatedTaskBuildingFileListDTO(RelatedTask rt) {
		setId(rt.getId());
		setContent(rt.getContent());
		setUpdateDatetime(rt.getUpdateDatetime());
		setAccountId(rt.getAccountId());
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
}
