package jp.co.world.storedevelopment.pc.controller.dto;

import jp.co.world.storedevelopment.model.IIncomeUnit;

public class IIncomeUnitRelationShopListDTO implements DTO<IIncomeUnit> {

	private Long id;
	private String incomeUnitCd;
	private String incomeUnitName;

	public IIncomeUnitRelationShopListDTO() {

	}

	public IIncomeUnitRelationShopListDTO(IIncomeUnit iIncomeUnit) {
		this.copyProperties(this, iIncomeUnit);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIncomeUnitCd() {
		return incomeUnitCd;
	}

	public void setIncomeUnitCd(String incomeUnitCd) {
		this.incomeUnitCd = incomeUnitCd;
	}

	public String getIncomeUnitName() {
		return incomeUnitName;
	}

	public void setIncomeUnitName(String incomUnitName) {
		this.incomeUnitName = incomUnitName;
	}

	@Override
	public IIncomeUnit createModel() {
		return new IIncomeUnit();
	}

}
