package jp.co.world.storedevelopment.sp.controller.dto;

import com.world.storedevelopment.utils.ZenkakuUtils;

import jp.co.world.storedevelopment.model.Building;

public class BuildingListParentDTO implements DTO<Building> {

	private String buildingCd;
	private String name;
	private Boolean isBuildingGroup;

	@Override
	public Building createModel() {
		return new Building();
	}

	public BuildingListParentDTO() {
		//
	}

	public BuildingListParentDTO(Building building) {
		this.copyProperties(this, building);
	}

	public Long getId() {
		return Long.valueOf(this.getBuildingCd());
	}

	public String getBuildingCd() {
		return buildingCd;
	}

	public void setBuildingCd(String buildingCd) {
		this.buildingCd = buildingCd;
	}

	public String getName() {
		return ZenkakuUtils.toZenkaku(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsBuildingGroup() {
		return isBuildingGroup;
	}

	public void setIsBuildingGroup(Boolean isBuildingGroup) {
		this.isBuildingGroup = isBuildingGroup;
	}

}
