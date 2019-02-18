package jp.co.world.storedevelopment.sp.controller.dto;

import com.world.storedevelopment.utils.HankakuUtils;
import com.world.storedevelopment.utils.ZenkakuUtils;

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

	public String getTextZenkaku() {
		return ZenkakuUtils.toZenkaku(text);
	}
	
	public String getTextHankaku() {
		return HankakuUtils.toHankaku(text);
	}
	
	public String getTextZenkakuEisu() {
		return ZenkakuUtils.toZenkakuEisu(text);
	}
}
