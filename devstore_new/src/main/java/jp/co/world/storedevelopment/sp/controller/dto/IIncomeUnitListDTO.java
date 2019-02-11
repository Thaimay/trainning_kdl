package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.IIncomeUnit;

public class IIncomeUnitListDTO implements DTO<IIncomeUnit> {

	private Long id;
	private String incomeUnitName;

	@Override
	public IIncomeUnit createModel() {
		return new IIncomeUnit();
	}

	public IIncomeUnitListDTO() {
		//
	}

	public IIncomeUnitListDTO(IIncomeUnit ii) {
		this.copyProperties(this, ii);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIncomeUnitName() {
		return incomeUnitName;
	}

	public void setIncomeUnitName(String incomeUnitName) {
		this.incomeUnitName = incomeUnitName;
	}
	
	public String getName() {
		return this.getIncomeUnitName();
	}

}
