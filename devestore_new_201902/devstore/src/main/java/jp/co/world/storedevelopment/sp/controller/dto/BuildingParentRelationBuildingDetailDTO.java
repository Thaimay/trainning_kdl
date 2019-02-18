package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.Building;

public class BuildingParentRelationBuildingDetailDTO implements DTO<Building> {

	private String buildingCd;
	private String name;

	public BuildingParentRelationBuildingDetailDTO() {

	}

	public BuildingParentRelationBuildingDetailDTO(Building building) {
		this.copyProperties(this, building);
	}

	public String getBuildingCd() {
		return buildingCd;
	}

	public void setBuildingCd(String buildingCd) {
		this.buildingCd = buildingCd;
	}

	public String getId() {
		return this.getBuildingCd();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Building createModel() {
		return new Building();
	}

}
