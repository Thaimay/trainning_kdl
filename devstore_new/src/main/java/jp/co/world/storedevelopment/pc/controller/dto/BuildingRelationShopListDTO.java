package jp.co.world.storedevelopment.pc.controller.dto;

import jp.co.world.storedevelopment.model.Building;

public class BuildingRelationShopListDTO implements DTO<Building> {

	private Long id;
	private String buildingCd;
	private String name;
	private Long iAreaId;

	public BuildingRelationShopListDTO() {

	}

	public BuildingRelationShopListDTO(Building building) {
		this.copyProperties(this, building);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBuildingCd() {
		return buildingCd;
	}

	public void setBuildingCd(String buildingCd) {
		this.buildingCd = buildingCd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getiAreaId() {
		return iAreaId;
	}

	public void setiAreaId(Long iAreaId) {
		this.iAreaId = iAreaId;
	}

	@Override
	public Building createModel() {
		return new Building();
	}

}
