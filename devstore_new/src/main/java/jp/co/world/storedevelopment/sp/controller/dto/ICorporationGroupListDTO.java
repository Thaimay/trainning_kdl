package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.ICorporationGroup;

public class ICorporationGroupListDTO implements DTO<ICorporationGroup> {

	private Long id;
	private String corporationGpName;

	@Override
	public ICorporationGroup createModel() {
		return new ICorporationGroup();
	}

	public ICorporationGroupListDTO() {
		//
	}

	public ICorporationGroupListDTO(ICorporationGroup icg) {
		this.copyProperties(this, icg);
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
		return this.getCorporationGpName();
	}

}
