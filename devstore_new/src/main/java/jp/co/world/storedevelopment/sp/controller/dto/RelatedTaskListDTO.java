package jp.co.world.storedevelopment.sp.controller.dto;

import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.NegotiationComment;
import jp.co.world.storedevelopment.model.RelatedTask;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationCommentRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationRepository;

public class RelatedTaskListDTO {

	private Long id;
	private String division;
	private String content;
	private Long accountId;
	private Long negotiationId;
	private Long negotiationCommentId;
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm")
	private LocalDateTime updateDatetime;

	public RelatedTaskListDTO(RelatedTask rt) {
		setId(rt.getId());
		setDivision(rt.getDivision());
		setContent(rt.getContent());
		setAccountId(rt.getAccountId());
		setNegotiationId(rt.getNegotiationId());
		setNegotiationCommentId(rt.getNegotiationCommentId());
		setUpdateDatetime(rt.getUpdateDatetime());
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

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public Long getNegotiationId() {
		return negotiationId;
	}

	public void setNegotiationId(Long negotiationId) {
		this.negotiationId = negotiationId;
	}

	public Long getNegotiationCommentId() {
		return negotiationCommentId;
	}

	public void setNegotiationCommentId(Long negotiationCommentId) {
		this.negotiationCommentId = negotiationCommentId;
	}

	public UpdateInformationListDTO getUpdateInformation() {
		if (getNegotiationId() == null) {
			return null;
		}

		Optional<Negotiation> negotiation = new NegotiationRepository().findById(getNegotiationId());
		return negotiation.get() != null ? new UpdateInformationListDTO(negotiation.get()) : null;
	}

	public UpdateInformationCommentListDTO getUpdateInformationComment() {
		if (getNegotiationCommentId() == null) {
			return null;
		}

		Optional<NegotiationComment> negotiationComment = new NegotiationCommentRepository()
				.findById(getNegotiationCommentId());
		return negotiationComment.get() != null ? new UpdateInformationCommentListDTO(negotiationComment.get()) : null;
	}
}
