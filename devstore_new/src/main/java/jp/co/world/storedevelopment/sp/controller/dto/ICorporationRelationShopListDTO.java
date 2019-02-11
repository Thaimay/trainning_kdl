package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.ICorporation;

public class ICorporationRelationShopListDTO implements DTO<ICorporation> {

	private Long id;
	private String corporationCd;
	private String corporationName;

	public ICorporationRelationShopListDTO() {

	}

	public ICorporationRelationShopListDTO(ICorporation iCorporation) {
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

	public String getCorporationCd() {
		return corporationCd;
	}

	public void setCorporationCd(String corporationCd) {
		this.corporationCd = corporationCd;
	}

	public void setCorporationName(String corporationName) {
		this.corporationName = corporationName;
	}

	@Override
	public ICorporation createModel() {
		return new ICorporation();
	}

}
