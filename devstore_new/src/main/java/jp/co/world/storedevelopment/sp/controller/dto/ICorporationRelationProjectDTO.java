package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.ICorporation;

public class ICorporationRelationProjectDTO implements DTO<ICorporation> {

	private Long id;
	private String corporationCd;
	private String corporationName;
	private Long corporationGpId;

	public ICorporationRelationProjectDTO() {

	}

	public ICorporationRelationProjectDTO(ICorporation iCorporation) {
		this.copyProperties(this, iCorporation);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCorporationCd() {
		return corporationCd;
	}

	public void setCorporationCd(String corporationCd) {
		this.corporationCd = corporationCd;
	}

	public String getCorporationName() {
		return corporationName;
	}

	public void setCorporationName(String corporationName) {
		this.corporationName = corporationName;
	}

	public Long getCorporationGpId() {
		return corporationGpId;
	}

	public void setCorporationGpId(Long corporationGpId) {
		this.corporationGpId = corporationGpId;
	}

	public String getName() {
		return getCorporationName();
	}

	@Override
	public ICorporation createModel() {
		return new ICorporation();
	}

}
