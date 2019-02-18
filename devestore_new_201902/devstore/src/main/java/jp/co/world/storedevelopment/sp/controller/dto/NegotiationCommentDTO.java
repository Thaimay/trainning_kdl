package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.NegotiationComment;

public class NegotiationCommentDTO {
	private Long id;

	private String content;

	private String accountName;

	private String createdDatetime;

	private Boolean isOwn = false;

	public static List<NegotiationCommentDTO> toList(List<NegotiationComment> list, Account account) {
		return list.stream().map(c -> {
			return new NegotiationCommentDTO(c, account);
		}).collect(Collectors.toList());
	}

	public NegotiationCommentDTO(NegotiationComment model, Account account) {
		setId(model.getId());
		setContent(model.getContent());
		setAccountName(model.createAccountName());
		setIsOwn(account.getEmployeeCd().equals(model.getAccountCode()));
		setCreatedDatetime(model.getCreateDate());
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

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Boolean getIsOwn() {
		return isOwn;
	}

	public void setIsOwn(Boolean isOwn) {
		this.isOwn = isOwn;
	}

	public String getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(String createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

}
