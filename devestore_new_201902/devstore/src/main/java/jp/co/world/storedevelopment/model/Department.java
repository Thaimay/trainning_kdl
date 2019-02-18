package jp.co.world.storedevelopment.model;

import java.time.LocalDate;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.DepartmentModelMapper;

public class Department extends IActiveModel<Department> {
	private String appropriatingYearMonth = "";
	private String deptCd = "";
	private String deptName = "";
	private String deptKanaName = "";
	private String companyCd = "";
	private String incomeUnitCd1 = "";
	private String incomeUnitCd2 = "";
	private String incomeUnitCd3 = "";
	private String businessPlatformCd = "";
	private String depreciationDivision = "";
	private String financialIfDivision = "";
	private String inputModel = "";
	private String missionCriticalIfDivision = "";
	private String backupCd1 = "";
	private String backupCd2 = "";
	private String backupCd3 = "";
	private String backupCd4 = "";
	private String backupCd5 = "";
	private String startDateForFinancialAccounting = "";
	private String endDaysForFinancialAccounting = "";
	private String startDateForManagementAccounting = "";
	private String endDaysForManagementAccounting = "";
	private String stepsRateBackBr = "";
	private String storeStepsRateCostDivision = "";
	private String marketDivision = "";
	private String temporaryCdDivision = "";
	private LocalDate registrationDate = LocalDate.now();
	private String registrant = "";
	private String registrationProcessingName = "";
	private LocalDate changeDate = LocalDate.now();
	private String changePerson = "";
	private String changeProcessingName = "";

	public Department() {
		//
	}

	public Department(String code, String companyCode, String incomeUnitCode1) {
		deptCd = code;
		companyCd = companyCode;
		incomeUnitCd1 = incomeUnitCode1;
	}

	@Override
	protected ModelMapper<Department> modelMapper(SqlSession session) {
		return session.getMapper(DepartmentModelMapper.class);
	}

	@Override
	public String getTableName() {
		return "I_DEPT";
	}

	public String getAppropriatingYearMonth() {
		return appropriatingYearMonth;
	}

	public void setAppropriatingYearMonth(String appropriatingYearMonth) {
		this.appropriatingYearMonth = appropriatingYearMonth;
	}

	public String getDeptCd() {
		return deptCd;
	}

	public void setDeptCd(String deptId) {
		deptCd = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptKanaName() {
		return deptKanaName;
	}

	public void setDeptKanaName(String deptKanaName) {
		this.deptKanaName = deptKanaName;
	}

	public String getCompanyCd() {
		return companyCd;
	}

	public void setCompanyCd(String companyId) {
		companyCd = companyId;
	}

	public String getIncomeUnitCd1() {
		return incomeUnitCd1;
	}

	public void setIncomeUnitCd1(String incomeUnitCd1) {
		this.incomeUnitCd1 = incomeUnitCd1;
	}

	public String getIncomeUnitCd2() {
		return incomeUnitCd2;
	}

	public void setIncomeUnitCd2(String incomeUnitCd2) {
		this.incomeUnitCd2 = incomeUnitCd2;
	}

	public String getIncomeUnitCd3() {
		return incomeUnitCd3;
	}

	public void setIncomeUnitCd3(String incomeUnitCd3) {
		this.incomeUnitCd3 = incomeUnitCd3;
	}

	public String getBusinessPlatformCd() {
		return businessPlatformCd;
	}

	public void setBusinessPlatformCd(String businessPlatformCd) {
		this.businessPlatformCd = businessPlatformCd;
	}

	public String getDepreciationDivision() {
		return depreciationDivision;
	}

	public void setDepreciationDivision(String depreciationDivision) {
		this.depreciationDivision = depreciationDivision;
	}

	public String getFinancialIfDivision() {
		return financialIfDivision;
	}

	public void setFinancialIfDivision(String financialIfDivision) {
		this.financialIfDivision = financialIfDivision;
	}

	public String getInputModel() {
		return inputModel;
	}

	public void setInputModel(String inputModel) {
		this.inputModel = inputModel;
	}

	public String getMissionCriticalIfDivision() {
		return missionCriticalIfDivision;
	}

	public void setMissionCriticalIfDivision(String missionCriticalIfDivision) {
		this.missionCriticalIfDivision = missionCriticalIfDivision;
	}

	public String getBackupCd1() {
		return backupCd1;
	}

	public void setBackupCd1(String backupCd1) {
		this.backupCd1 = backupCd1;
	}

	public String getBackupCd2() {
		return backupCd2;
	}

	public void setBackupCd2(String backupCd2) {
		this.backupCd2 = backupCd2;
	}

	public String getBackupCd3() {
		return backupCd3;
	}

	public void setBackupCd3(String backupCd3) {
		this.backupCd3 = backupCd3;
	}

	public String getBackupCd4() {
		return backupCd4;
	}

	public void setBackupCd4(String backupCd4) {
		this.backupCd4 = backupCd4;
	}

	public String getBackupCd5() {
		return backupCd5;
	}

	public void setBackupCd5(String backupCd5) {
		this.backupCd5 = backupCd5;
	}

	public String getStartDateForFinancialAccounting() {
		return startDateForFinancialAccounting;
	}

	public void setStartDateForFinancialAccounting(String startDateForFinancialAccounting) {
		this.startDateForFinancialAccounting = startDateForFinancialAccounting;
	}

	public String getEndDaysForFinancialAccounting() {
		return endDaysForFinancialAccounting;
	}

	public void setEndDaysForFinancialAccounting(String endDaysForFinancialAccounting) {
		this.endDaysForFinancialAccounting = endDaysForFinancialAccounting;
	}

	public String getStartDateForManagementAccounting() {
		return startDateForManagementAccounting;
	}

	public void setStartDateForManagementAccounting(String startDateForManagementAccounting) {
		this.startDateForManagementAccounting = startDateForManagementAccounting;
	}

	public String getEndDaysForManagementAccounting() {
		return endDaysForManagementAccounting;
	}

	public void setEndDaysForManagementAccounting(String endDaysForManagementAccounting) {
		this.endDaysForManagementAccounting = endDaysForManagementAccounting;
	}

	public String getStepsRateBackBr() {
		return stepsRateBackBr;
	}

	public void setStepsRateBackBr(String stepsRateBack_Br) {
		stepsRateBackBr = stepsRateBack_Br;
	}

	public String getStoreStepsRateCostDivision() {
		return storeStepsRateCostDivision;
	}

	public void setStoreStepsRateCostDivision(String storeStepsRateCostDivision) {
		this.storeStepsRateCostDivision = storeStepsRateCostDivision;
	}

	public String getMarketDivision() {
		return marketDivision;
	}

	public void setMarketDivision(String marketDivision) {
		this.marketDivision = marketDivision;
	}

	public String getTemporaryCdDivision() {
		return temporaryCdDivision;
	}

	public void setTemporaryCdDivision(String temporaryCdDivision) {
		this.temporaryCdDivision = temporaryCdDivision;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getRegistrant() {
		return registrant;
	}

	public void setRegistrant(String registrant) {
		this.registrant = registrant;
	}

	public String getRegistrationProcessingName() {
		return registrationProcessingName;
	}

	public void setRegistrationProcessingName(String registrationProcessingName) {
		this.registrationProcessingName = registrationProcessingName;
	}

	public LocalDate getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(LocalDate changeDate) {
		this.changeDate = changeDate;
	}

	public String getChangePerson() {
		return changePerson;
	}

	public void setChangePerson(String changePerson) {
		this.changePerson = changePerson;
	}

	public String getChangeProcessingName() {
		return changeProcessingName;
	}

	public void setChangeProcessingName(String changeProcessingName) {
		this.changeProcessingName = changeProcessingName;
	}

}
