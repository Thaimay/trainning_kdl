package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.ICorporationGroup;

public class ICorporationGroupRelationBuildingDetailDTO implements DTO<ICorporationGroup> {

	private Long id;
	private String corporationGpName;

	public ICorporationGroupRelationBuildingDetailDTO() {

	}

	public ICorporationGroupRelationBuildingDetailDTO(ICorporationGroup iCorporationGroup) {
		this.copyProperties(this, iCorporationGroup);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCorporationGpName() {
		return corporationGpName;
	}

	public void setCorporationGpName(String corporationGpName) {
		this.corporationGpName = corporationGpName;
	}

	public String getName() {
		return getCorporationGpName();
	}

	@Override
	public ICorporationGroup createModel() {
		return new ICorporationGroup();
	}

}
