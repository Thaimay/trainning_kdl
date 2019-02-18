package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.Optional;

import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.IArea;
import jp.co.world.storedevelopment.model.mapper.repository.IAreaRepository;

public class BuildingRelationShopListDTO implements DTO<Building> {

	private Long id;
	private String buildingCd;
	private String name;
	private Long iAreaId;

	public BuildingRelationShopListDTO() {

	}

	public BuildingRelationShopListDTO(Building building) {
		copyProperties(this, building);
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

	public IAreaRelationShopDetailDTO getiArea() {
		if (getiAreaId() == null) {
			return null;
		}
		
		Optional<IArea> iArea = new IAreaRepository().findById(getiAreaId());
		return iArea.isPresent() ? new IAreaRelationShopDetailDTO(iArea.get()) : null;
	}

	@Override
	public Building createModel() {
		return new Building();
	}

}
