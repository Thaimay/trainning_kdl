package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.ISalesAgencyTarget;

public class ISalesAgencyTargetListDTO implements DTO<ISalesAgencyTarget> {

	private Long id;
	private String salesAgencyTargetName;

	@Override
	public ISalesAgencyTarget createModel() {
		return new ISalesAgencyTarget();
	}

	public ISalesAgencyTargetListDTO() {
		//
	}

	public ISalesAgencyTargetListDTO(ISalesAgencyTarget sa) {
		this.copyProperties(this, sa);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSalesAgencyTargetName() {
		return salesAgencyTargetName;
	}

	public void setSalesAgencyTargetName(String salesAgencyTargetName) {
		this.salesAgencyTargetName = salesAgencyTargetName;
	}

	public String getName() {
		return this.getSalesAgencyTargetName();
	}

}
