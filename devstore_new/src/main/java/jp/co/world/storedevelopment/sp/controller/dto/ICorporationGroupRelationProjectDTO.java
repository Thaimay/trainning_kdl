package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.ICorporationGroup;

public class ICorporationGroupRelationProjectDTO implements DTO<ICorporationGroup> {

	private Long id;
	private String corporationGpName;

	public ICorporationGroupRelationProjectDTO() {

	}

	public ICorporationGroupRelationProjectDTO(ICorporationGroup iCorporationGroup) {
		this.copyProperties(this, iCorporationGroup);
	}
	
	@Override
	public ICorporationGroup createModel() {
		return new ICorporationGroup();
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
		return corporationGpName;
	}
}
