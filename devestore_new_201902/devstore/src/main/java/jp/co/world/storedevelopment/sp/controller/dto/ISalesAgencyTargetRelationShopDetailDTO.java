package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.ISalesAgencyTarget;

public class ISalesAgencyTargetRelationShopDetailDTO implements DTO<ISalesAgencyTarget> {

	private Long id;
	private String salesAgencyTargetName;

	public ISalesAgencyTargetRelationShopDetailDTO() {

	}

	public ISalesAgencyTargetRelationShopDetailDTO(ISalesAgencyTarget iSalesAgencyTarget) {
		this.copyProperties(this, iSalesAgencyTarget);
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

	@Override
	public ISalesAgencyTarget createModel() {
		return new ISalesAgencyTarget();
	}

}
