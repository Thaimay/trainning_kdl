package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.ParticipatingStoreCorporation;

public class ParticipatingStoreCorporationRelationShopDetailDTO implements DTO<ParticipatingStoreCorporation> {

	private Long id;
	private String corporationName;

	public ParticipatingStoreCorporationRelationShopDetailDTO() {

	}

	public ParticipatingStoreCorporationRelationShopDetailDTO(
			ParticipatingStoreCorporation participatingStoreCorporation) {
		this.copyProperties(this, participatingStoreCorporation);
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
	public ParticipatingStoreCorporation createModel() {
		return new ParticipatingStoreCorporation();
	}
	
	public String getName() {
		return this.getCorporationName();
	}

}
