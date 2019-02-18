package jp.co.world.storedevelopment.model;

import java.math.BigDecimal;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.IIncomeUnitLayerModelMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class IIncomeUnitLayer extends IActiveModel<IIncomeUnitLayer> {

	private String companyId;
	private String incomeUnitId;
	private String levelLevel1;
	private String levelLevel2;
	private String levelLevel3;
	private String levelLevel4;
	private String levelLevel5;
	private String levelLevel6;
	private String levelLevelSeven;
	private String levelLevelEight;
	private String levelLevelNine;
	private String levelLevelTen;
	private String startDate;
	private String endDate;
	private BigDecimal reportDisplayControlRank;
	private String webMenuDivision;
	private String backupCd1;
	private String backupCd2;
	private String backupCd3;
	private String backupCd4;
	private String backupCd5;
	private String coordinationCreator;
	private String coordinationCreationTime;
	private String coordinationDeletingFlag;
	private String coordinationApplyingDate;
	private String coordinationUpdater;
	private String coordinationUpdateTime;

	public IIncomeUnitLayer() {

	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getIncomeUnitId() {
		return incomeUnitId;
	}

	public void setIncomeUnitId(String incomeUnitId) {
		this.incomeUnitId = incomeUnitId;
	}

	public String getLevelLevel1() {
		return levelLevel1;
	}

	public void setLevelLevel1(String levelLevel1) {
		this.levelLevel1 = levelLevel1;
	}

	public String getLevelLevel2() {
		return levelLevel2;
	}

	public void setLevelLevel2(String levelLevel2) {
		this.levelLevel2 = levelLevel2;
	}

	public String getLevelLevel3() {
		return levelLevel3;
	}

	public void setLevelLevel3(String levelLevel3) {
		this.levelLevel3 = levelLevel3;
	}

	public String getLevelLevel4() {
		return levelLevel4;
	}

	public void setLevelLevel4(String levelLevel4) {
		this.levelLevel4 = levelLevel4;
	}

	public String getLevelLevel5() {
		return levelLevel5;
	}

	public void setLevelLevel5(String levelLevel5) {
		this.levelLevel5 = levelLevel5;
	}

	public String getLevelLevel6() {
		return levelLevel6;
	}

	public void setLevelLevel6(String levelLevel6) {
		this.levelLevel6 = levelLevel6;
	}

	public String getLevelLevelSeven() {
		return levelLevelSeven;
	}

	public void setLevelLevelSeven(String levelLevelSeven) {
		this.levelLevelSeven = levelLevelSeven;
	}

	public String getLevelLevelEight() {
		return levelLevelEight;
	}

	public void setLevelLevelEight(String levelLevelEight) {
		this.levelLevelEight = levelLevelEight;
	}

	public String getLevelLevelNine() {
		return levelLevelNine;
	}

	public void setLevelLevelNine(String levelLevelNine) {
		this.levelLevelNine = levelLevelNine;
	}

	public String getLevelLevelTen() {
		return levelLevelTen;
	}

	public void setLevelLevelTen(String levelLevelTen) {
		this.levelLevelTen = levelLevelTen;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getReportDisplayControlRank() {
		return reportDisplayControlRank;
	}

	public void setReportDisplayControlRank(BigDecimal reportDisplayControlRank) {
		this.reportDisplayControlRank = reportDisplayControlRank;
	}

	public String getWebMenuDivision() {
		return webMenuDivision;
	}

	public void setWebMenuDivision(String webMenuDivision) {
		this.webMenuDivision = webMenuDivision;
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

	public String getCoordinationCreator() {
		return coordinationCreator;
	}

	public void setCoordinationCreator(String coordinationCreator) {
		this.coordinationCreator = coordinationCreator;
	}

	public String getCoordinationCreationTime() {
		return coordinationCreationTime;
	}

	public void setCoordinationCreationTime(String coordinationCreationTime) {
		this.coordinationCreationTime = coordinationCreationTime;
	}

	public String getCoordinationDeletingFlag() {
		return coordinationDeletingFlag;
	}

	public void setCoordinationDeletingFlag(String coordinationDeletingFlag) {
		this.coordinationDeletingFlag = coordinationDeletingFlag;
	}

	public String getCoordinationApplyingDate() {
		return coordinationApplyingDate;
	}

	public void setCoordinationApplyingDate(String coordinationApplyingDate) {
		this.coordinationApplyingDate = coordinationApplyingDate;
	}

	public String getCoordinationUpdater() {
		return coordinationUpdater;
	}

	public void setCoordinationUpdater(String coordinationUpdater) {
		this.coordinationUpdater = coordinationUpdater;
	}

	public String getCoordinationUpdateTime() {
		return coordinationUpdateTime;
	}

	public void setCoordinationUpdateTime(String coordinationUpdateTime) {
		this.coordinationUpdateTime = coordinationUpdateTime;
	}

	@Override
	protected ModelMapper<IIncomeUnitLayer> modelMapper(SqlSession session) {
		return session.getMapper(IIncomeUnitLayerModelMapper.class);
	}

}
