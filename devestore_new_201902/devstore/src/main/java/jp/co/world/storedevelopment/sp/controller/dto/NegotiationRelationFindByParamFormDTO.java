package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.List;

import com.world.storedevelopment.utils.HankakuUtils;

import jp.co.world.storedevelopment.Application;

public class NegotiationRelationFindByParamFormDTO {

	private String text = "";
	private List<Long> params;

	public NegotiationRelationFindByParamFormDTO() {
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

	public List<Long> getParams() {
		return params;
	}

	public void setParams(List<Long> params) {
		this.params = params;
	}

	public int getLimit() {
		return Application.SUGGEST_LIMIT_SIZE;
	}
}
