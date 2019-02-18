
package jp.co.world.storedevelopment.pc.controller.form;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.world.storedevelopment.utils.HankakuUtils;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.pc.controller.dto.FindFormDTO;

/**
 * @author thaipd
 *
 */
public class BuildingFindForm extends FindFormDTO {

	public static final BuildingFindForm EMPTY = new BuildingFindForm();

	private String buildingName = "";
	private List<Long> iCorporationIds;
	private Integer buildingType;
	private List<Long> iSalesChannelIds;
	private List<Long> salesChannelCd2s;
	private List<Long> iBlockIds;
	private List<Long> iAreaIds;
	private String jsonCurrentCorporation = "[]";
	private String jsonCurrentSalesChannel = "[]";
	private String jsonCurrentSalesChannel2 = "[]";
	private String jsonCurrentBlock = "[]";
	private String jsonCurrentArea = "[]";

	public BuildingFindForm() {
		//
	}

	@JsonIgnore
	public Boolean getIsNotLocal() {
		return !Application.isLocal();
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public List<Long> getiCorporationIds() {
		return iCorporationIds;
	}

	public void setiCorporationIds(List<Long> iCorporationIds) {
		this.iCorporationIds = iCorporationIds;
	}

	public Integer getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(Integer buildingType) {
		this.buildingType = buildingType;
	}

	public List<Long> getiSalesChannelIds() {
		return iSalesChannelIds;
	}

	public void setiSalesChannelIds(List<Long> iSalesChannelIds) {
		this.iSalesChannelIds = iSalesChannelIds;
	}

	public List<Long> getSalesChannelCd2s() {
		return salesChannelCd2s;
	}

	public void setSalesChannelCd2s(List<Long> salesChannelCd2s) {
		this.salesChannelCd2s = salesChannelCd2s;
	}

	public List<Long> getiBlockIds() {
		return iBlockIds;
	}

	public void setiBlockIds(List<Long> iBlockIds) {
		this.iBlockIds = iBlockIds;
	}

	public List<Long> getiAreaIds() {
		return iAreaIds;
	}

	public void setiAreaIds(List<Long> iAreaIds) {
		this.iAreaIds = iAreaIds;
	}

	public String getJsonCurrentCorporation() {
		return jsonCurrentCorporation;
	}

	public void setJsonCurrentCorporation(String jsonCurrentCorporation) {
		this.jsonCurrentCorporation = jsonCurrentCorporation;
	}

	public String getJsonCurrentSalesChannel() {
		return jsonCurrentSalesChannel;
	}

	public void setJsonCurrentSalesChannel(String jsonCurrentSalesChannel) {
		this.jsonCurrentSalesChannel = jsonCurrentSalesChannel;
	}

	public String getJsonCurrentSalesChannel2() {
		return jsonCurrentSalesChannel2;
	}

	public void setJsonCurrentSalesChannel2(String jsonCurrentSalesChannel2) {
		this.jsonCurrentSalesChannel2 = jsonCurrentSalesChannel2;
	}

	public String getJsonCurrentBlock() {
		return jsonCurrentBlock;
	}

	public void setJsonCurrentBlock(String jsonCurrentBlock) {
		this.jsonCurrentBlock = jsonCurrentBlock;
	}

	public String getJsonCurrentArea() {
		return jsonCurrentArea;
	}

	public void setJsonCurrentArea(String jsonCurrentArea) {
		this.jsonCurrentArea = jsonCurrentArea;
	}

	public String getBuildingNameHankaku() {
		return HankakuUtils.toHankaku(buildingName);
	}

}
