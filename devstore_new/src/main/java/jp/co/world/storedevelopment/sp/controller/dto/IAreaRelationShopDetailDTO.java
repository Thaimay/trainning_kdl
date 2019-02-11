package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.IArea;

public class IAreaRelationShopDetailDTO implements DTO<IArea> {

	private Long id;
	private String areaCd;
	private String areaName;

	public IAreaRelationShopDetailDTO() {

	}

	public IAreaRelationShopDetailDTO(IArea iArea) {
		this.copyProperties(this, iArea);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAreaCd() {
		return areaCd;
	}

	public void setAreaCd(String areaCd) {
		this.areaCd = areaCd;
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
