package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.NegotiationComment;

public class NegotiationCommentCreateDTO implements DTO<NegotiationComment> {

	private Long id;

	private String content;

	@Override
	public NegotiationComment createModel() {
		return new NegotiationComment();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
