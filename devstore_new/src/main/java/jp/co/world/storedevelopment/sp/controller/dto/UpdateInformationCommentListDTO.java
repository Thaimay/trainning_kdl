package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.NegotiationComment;

public class UpdateInformationCommentListDTO {
	private Long id;

	private String comment;

	private String createAccountName;

	private String createDateTime;

	private String title = "";

	public UpdateInformationCommentListDTO(NegotiationComment c) {
		setId(c.getNegotiationId());
		setComment(c.getContent());
		setCreateAccountName(c.createAccountName());
		setCreateDateTime(c.getCreateDate());

		if (c.getNegotiation() != null) {
			setTitle(c.getNegotiation().getTitle());
		}
	}

	public String getCreateAccountName() {
		return createAccountName;
	}

	public void setCreateAccountName(String createAccountName) {
		this.createAccountName = createAccountName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
