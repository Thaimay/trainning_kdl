package jp.co.world.storedevelopment.model;

public class RoleFinder {

	private String employeeCode = "";

	private String incomeUnitCode = "";

	private String companyCode = "";

	public RoleFinder(String employeeCode, String incomeUnitCode, String companyCode) {
		setEmployeeCode(employeeCode);
		setIncomeUnitCode(incomeUnitCode);
		setCompanyCode(companyCode);
	}

	public RoleFinder() {
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getIncomeUnitCode() {
		return incomeUnitCode;
	}

	public void setIncomeUnitCode(String incomeUnitCode) {
		this.incomeUnitCode = incomeUnitCode;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

}
