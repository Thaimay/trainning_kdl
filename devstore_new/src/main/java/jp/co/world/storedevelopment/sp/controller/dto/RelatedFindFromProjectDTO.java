package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.List;

import com.world.storedevelopment.utils.HankakuUtils;

import jp.co.world.storedevelopment.model.Account;

public class RelatedFindFromProjectDTO extends FindFormDTO{

	private String text = "";
	private List<Long> buildingIds;
	private List<Long> corporationIds;
	private List<Long> brandIds;
	private Account account;
	public RelatedFindFromProjectDTO() {
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<Long> getBuildingIds() {
		return buildingIds;
	}
	public void setBuildingIds(List<Long> buildingIds) {
		this.buildingIds = buildingIds;
	}
	public List<Long> getCorporationIds() {
		return corporationIds;
	}
	public void setCorporationIds(List<Long> corporationIds) {
		this.corporationIds = corporationIds;
	}
	public List<Long> getBrandIds() {
		return brandIds;
	}
	public void setBrandIds(List<Long> brandIds) {
		this.brandIds = brandIds;
	}
	public String getTextHankaku() {
		return HankakuUtils.toHankaku(text);
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}

}
