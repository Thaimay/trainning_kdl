package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.MStoreDevelopTeam;

public class MStoreDevelopTeamListDTO implements DTO<MStoreDevelopTeam> {

	private Long id;
	private String incomeUnitCd;
	private String incomeUnitName;
	private String deptCd;
	private String deptName;

	public MStoreDevelopTeamListDTO() {

	}

	public MStoreDevelopTeamListDTO(MStoreDevelopTeam model) {
		this.copyProperties(this, model);
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

	public void setIncomeUnitName(String incomeUnitName) {
		this.incomeUnitName = incomeUnitName;
	}

	public String getDeptCd() {
		return deptCd;
	}

	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
	public MStoreDevelopTeam createModel() {
		return new MStoreDevelopTeam();
	}

	public String getName() {
		return this.getDeptName();
	}

}
