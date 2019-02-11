
package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.List;

import com.world.storedevelopment.utils.HankakuUtils;

/**
 * @author thaipd
 *
 */
public class BuildingFindFormDTO extends FindFormDTO {

	public static final BuildingFindFormDTO EMPTY = new BuildingFindFormDTO();

	private String buildingName = "";
	private List<Long> iCorporationIds;
	private Integer buildingType;
	private List<Long> iSalesChannelIds;
	private List<Long> salesChannelCd2s;
	private List<Long> iBlockIds;
	private List<Long> iAreaIds;

	public BuildingFindFormDTO() {
		//
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

	public String getBuildingNameHankaku() {
		return HankakuUtils.toHankaku(buildingName);
	}
}
