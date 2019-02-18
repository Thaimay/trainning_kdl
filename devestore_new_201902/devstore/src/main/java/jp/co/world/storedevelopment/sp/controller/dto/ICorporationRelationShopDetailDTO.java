package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.ICorporation;

public class ICorporationRelationShopDetailDTO implements DTO<ICorporation> {

	private Long id;
	private String corporationName;

	public ICorporationRelationShopDetailDTO() {

	}

	public ICorporationRelationShopDetailDTO(ICorporation iCorporation) {
		this.copyProperties(this, iCorporation);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCorporationName() {
		return corporationName;
	}

	public void setCorporationName(String corporationName) {
		this.corporationName = corporationName;
	}

	@Override
	public ICorporation createModel() {
		return new ICorporation();
	}

}
