package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.IArea;

public class IAreaRelationBuildingDTO implements DTO<IArea> {

	private Long id;
	private String areaName;

	public IAreaRelationBuildingDTO() {

	}

	public IAreaRelationBuildingDTO(IArea iArea) {
		this.copyProperties(this, iArea);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Override
	public IArea createModel() {
		return new IArea();
	}

}
