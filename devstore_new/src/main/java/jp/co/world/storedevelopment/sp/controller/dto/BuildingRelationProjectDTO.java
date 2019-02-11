package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.Building;

public class BuildingRelationProjectDTO implements DTO<Building> {

	private Long id;
	private Long originBuildingId;
	private String buildingCd;
	private String name;
	private String adoptDifficulty;

	public BuildingRelationProjectDTO() {

	}

	public BuildingRelationProjectDTO(Building building) {
		copyProperties(this, building);
	}

	@Override
	public Building createModel() {
		return new Building();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOriginBuildingId() {
		return originBuildingId;
	}

	public void setOriginBuildingId(Long originBuildingId) {
		this.originBuildingId = originBuildingId;
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

	public String getAdoptDifficulty() {
		return adoptDifficulty;
	}

	public void setAdoptDifficulty(String adoptDifficulty) {
		this.adoptDifficulty = adoptDifficulty;
	}
}
