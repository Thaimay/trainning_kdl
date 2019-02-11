package jp.co.world.storedevelopment.sp.controller.dto;

import com.world.storedevelopment.utils.HankakuUtils;

public class BuildingRelationFindByTextFormDTO {

	private String text = "";

	public BuildingRelationFindByTextFormDTO() {
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
