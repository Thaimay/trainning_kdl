package jp.co.world.storedevelopment.sp.controller.dto;

import com.world.storedevelopment.utils.HankakuUtils;

public class NegotiationRelationFindByTextFormDTO {

	private String text = "";

	public NegotiationRelationFindByTextFormDTO() {
		//
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTextHankaku() {
		return HankakuUtils.toHankaku(text);
	}
}
