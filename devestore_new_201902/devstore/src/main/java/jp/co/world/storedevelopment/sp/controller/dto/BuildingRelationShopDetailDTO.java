package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.IArea;
import jp.co.world.storedevelopment.model.mapper.repository.IAreaRepository;

public class BuildingRelationShopDetailDTO implements DTO<Building> {

	private Long id;
	private String buildingCd;
	private String name;
	private Long iAreaId;
	private String businessHours;

	public BuildingRelationShopDetailDTO() {

	}

	public BuildingRelationShopDetailDTO(Building building) {
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

	public String getBusinessHours() {
		return businessHours;
	}

	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}

	public IAreaRelationShopDetailDTO getiArea() {
		IArea iArea = new IAreaRepository().findById(this.getiAreaId())
				.orElseThrow(() -> new IllegalStateException("存在しないエリアIDです:" + this.getiAreaId()));
		return new IAreaRelationShopDetailDTO(iArea);
	}

	@Override
	public Building createModel() {
		return new Building();
	}

}
