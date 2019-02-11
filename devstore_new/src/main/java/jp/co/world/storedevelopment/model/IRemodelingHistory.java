package jp.co.world.storedevelopment.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.IRemodelingHistoryModelMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class IRemodelingHistory extends IActiveModel<IRemodelingHistory> {

	private BigDecimal remodelingLogId;
	private BigDecimal shopId;
	private String leaveStartDate;
	private String remodelingDate;
	private String endDate;
	private String floor;
	private BigDecimal contractTsubo;
	private BigDecimal effectiveTsubo;
	private BigDecimal backYardTsubo;
	private BigDecimal contractArea;
	private BigDecimal effectiveArea;
	private BigDecimal backYardArea;
	private BigDecimal remodelingConvenienceId;
	private String remodelingReason;
	private String remodelingTarget;
	private BigDecimal remodelingDivisionId;
	private BigDecimal relocationDivisionId;
	private BigDecimal increaseAndDecreaseFloorDivisionId;
	private String economyConditionChangeExistence;
	private BigDecimal lossOnDisposalOfPropertyBalanceTop;
	private BigDecimal lossOnDisposalOfPropertyAccountingTop;
	private BigDecimal cancellationMoney;
	private BigDecimal upholsteryExpenses;
	private BigDecimal buildingEquipmentExpenses;
	private BigDecimal burdenMoney;
	private BigDecimal managementManagementExpenses;
	private BigDecimal vmdCostEtc;
	private BigDecimal otherExpenses;
	private BigDecimal upholsteryCostCompanyBurden;
	private BigDecimal constructionEquipmentCostCompanyBurden;
	private BigDecimal burdenMoneyCompanyBurden;
	private BigDecimal managementManagementCostCompanyBurden;
	private BigDecimal vmdCostEtcCompanyBurden;
	private BigDecimal otherExpensesCompanyBurden;
	private BigDecimal costTotalCompanyBurden;
	private BigDecimal costSum;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime coordinationCreatedDatetime;
	private String coordinationCreatedAccountCode;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime coordinationUpdateDatetime;
	private String coordinationUpdateAccountCode;
	private String action;

	public IRemodelingHistory() {

	}

	public BigDecimal getRemodelingLogId() {
		return remodelingLogId;
	}

	public void setRemodelingLogId(BigDecimal remodelingLogId) {
		this.remodelingLogId = remodelingLogId;
	}

	public BigDecimal getShopId() {
		return shopId;
	}

	public void setShopId(BigDecimal shopId) {
		this.shopId = shopId;
	}

	public String getLeaveStartDate() {
		return leaveStartDate;
	}

	public void setLeaveStartDate(String leaveStartDate) {
		this.leaveStartDate = leaveStartDate;
	}

	public String getRemodelingDate() {
		return remodelingDate;
	}

	public void setRemodelingDate(String remodelingDate) {
		this.remodelingDate = remodelingDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public BigDecimal getContractTsubo() {
		return contractTsubo;
	}

	public void setContractTsubo(BigDecimal contractTsubo) {
		this.contractTsubo = contractTsubo;
	}

	public BigDecimal getEffectiveTsubo() {
		return effectiveTsubo;
	}

	public void setEffectiveTsubo(BigDecimal effectiveTsubo) {
		this.effectiveTsubo = effectiveTsubo;
	}

	public BigDecimal getBackYardTsubo() {
		return backYardTsubo;
	}

	public void setBackYardTsubo(BigDecimal backYardTsubo) {
		this.backYardTsubo = backYardTsubo;
	}

	public BigDecimal getContractArea() {
		return contractArea;
	}

	public void setContractArea(BigDecimal contractArea) {
		this.contractArea = contractArea;
	}

	public BigDecimal getEffectiveArea() {
		return effectiveArea;
	}

	public void setEffectiveArea(BigDecimal effectiveArea) {
		this.effectiveArea = effectiveArea;
	}

	public BigDecimal getBackYardArea() {
		return backYardArea;
	}

	public void setBackYardArea(BigDecimal backYardArea) {
		this.backYardArea = backYardArea;
	}

	public BigDecimal getRemodelingConvenienceId() {
		return remodelingConvenienceId;
	}

	public void setRemodelingConvenienceId(BigDecimal remodelingConvenienceId) {
		this.remodelingConvenienceId = remodelingConvenienceId;
	}

	public String getRemodelingReason() {
		return remodelingReason;
	}

	public void setRemodelingReason(String remodelingReason) {
		this.remodelingReason = remodelingReason;
	}

	public String getRemodelingTarget() {
		return remodelingTarget;
	}

	public void setRemodelingTarget(String remodelingTarget) {
		this.remodelingTarget = remodelingTarget;
	}

	public BigDecimal getRemodelingDivisionId() {
		return remodelingDivisionId;
	}

	public void setRemodelingDivisionId(BigDecimal remodelingDivisionId) {
		this.remodelingDivisionId = remodelingDivisionId;
	}

	public BigDecimal getRelocationDivisionId() {
		return relocationDivisionId;
	}

	public void setRelocationDivisionId(BigDecimal relocationDivisionId) {
		this.relocationDivisionId = relocationDivisionId;
	}

	public BigDecimal getIncreaseAndDecreaseFloorDivisionId() {
		return increaseAndDecreaseFloorDivisionId;
	}

	public void setIncreaseAndDecreaseFloorDivisionId(BigDecimal increaseAndDecreaseFloorDivisionId) {
		this.increaseAndDecreaseFloorDivisionId = increaseAndDecreaseFloorDivisionId;
	}

	public String getEconomyConditionChangeExistence() {
		return economyConditionChangeExistence;
	}

	public void setEconomyConditionChangeExistence(String economyConditionChangeExistence) {
		this.economyConditionChangeExistence = economyConditionChangeExistence;
	}

	public BigDecimal getLossOnDisposalOfPropertyBalanceTop() {
		return lossOnDisposalOfPropertyBalanceTop;
	}

	public void setLossOnDisposalOfPropertyBalanceTop(BigDecimal lossOnDisposalOfPropertyBalanceTop) {
		this.lossOnDisposalOfPropertyBalanceTop = lossOnDisposalOfPropertyBalanceTop;
	}

	public BigDecimal getLossOnDisposalOfPropertyAccountingTop() {
		return lossOnDisposalOfPropertyAccountingTop;
	}

	public void setLossOnDisposalOfPropertyAccountingTop(BigDecimal lossOnDisposalOfPropertyAccountingTop) {
		this.lossOnDisposalOfPropertyAccountingTop = lossOnDisposalOfPropertyAccountingTop;
	}

	public BigDecimal getCancellationMoney() {
		return cancellationMoney;
	}

	public void setCancellationMoney(BigDecimal cancellationMoney) {
		this.cancellationMoney = cancellationMoney;
	}

	public BigDecimal getUpholsteryExpenses() {
		return upholsteryExpenses;
	}

	public void setUpholsteryExpenses(BigDecimal upholsteryExpenses) {
		this.upholsteryExpenses = upholsteryExpenses;
	}

	public BigDecimal getBuildingEquipmentExpenses() {
		return buildingEquipmentExpenses;
	}

	public void setBuildingEquipmentExpenses(BigDecimal buildingEquipmentExpenses) {
		this.buildingEquipmentExpenses = buildingEquipmentExpenses;
	}

	public BigDecimal getBurdenMoney() {
		return burdenMoney;
	}

	public void setBurdenMoney(BigDecimal burdenMoney) {
		this.burdenMoney = burdenMoney;
	}

	public BigDecimal getManagementManagementExpenses() {
		return managementManagementExpenses;
	}

	public void setManagementManagementExpenses(BigDecimal managementManagementExpenses) {
		this.managementManagementExpenses = managementManagementExpenses;
	}

	public BigDecimal getVmdCostEtc() {
		return vmdCostEtc;
	}

	public void setVmdCostEtc(BigDecimal vmdCostEtc) {
		this.vmdCostEtc = vmdCostEtc;
	}

	public BigDecimal getOtherExpenses() {
		return otherExpenses;
	}

	public void setOtherExpenses(BigDecimal otherExpenses) {
		this.otherExpenses = otherExpenses;
	}

	public BigDecimal getUpholsteryCostCompanyBurden() {
		return upholsteryCostCompanyBurden;
	}

	public void setUpholsteryCostCompanyBurden(BigDecimal upholsteryCostCompanyBurden) {
		this.upholsteryCostCompanyBurden = upholsteryCostCompanyBurden;
	}

	public BigDecimal getConstructionEquipmentCostCompanyBurden() {
		return constructionEquipmentCostCompanyBurden;
	}

	public void setConstructionEquipmentCostCompanyBurden(BigDecimal constructionEquipmentCostCompanyBurden) {
		this.constructionEquipmentCostCompanyBurden = constructionEquipmentCostCompanyBurden;
	}

	public BigDecimal getBurdenMoneyCompanyBurden() {
		return burdenMoneyCompanyBurden;
	}

	public void setBurdenMoneyCompanyBurden(BigDecimal burdenMoneyCompanyBurden) {
		this.burdenMoneyCompanyBurden = burdenMoneyCompanyBurden;
	}

	public BigDecimal getManagementManagementCostCompanyBurden() {
		return managementManagementCostCompanyBurden;
	}

	public void setManagementManagementCostCompanyBurden(BigDecimal managementManagementCostCompanyBurden) {
		this.managementManagementCostCompanyBurden = managementManagementCostCompanyBurden;
	}

	public BigDecimal getVmdCostEtcCompanyBurden() {
		return vmdCostEtcCompanyBurden;
	}

	public void setVmdCostEtcCompanyBurden(BigDecimal vmdCostEtcCompanyBurden) {
		this.vmdCostEtcCompanyBurden = vmdCostEtcCompanyBurden;
	}

	public BigDecimal getOtherExpensesCompanyBurden() {
		return otherExpensesCompanyBurden;
	}

	public void setOtherExpensesCompanyBurden(BigDecimal otherExpensesCompanyBurden) {
		this.otherExpensesCompanyBurden = otherExpensesCompanyBurden;
	}

	public BigDecimal getCostTotalCompanyBurden() {
		return costTotalCompanyBurden;
	}

	public void setCostTotalCompanyBurden(BigDecimal costTotalCompanyBurden) {
		this.costTotalCompanyBurden = costTotalCompanyBurden;
	}

	public BigDecimal getCostSum() {
		return costSum;
	}

	public void setCostSum(BigDecimal costSum) {
		this.costSum = costSum;
	}

	public LocalDateTime getCoordinationCreatedDatetime() {
		return coordinationCreatedDatetime;
	}

	public void setCoordinationCreatedDatetime(LocalDateTime coordinationCreatedDatetime) {
		this.coordinationCreatedDatetime = coordinationCreatedDatetime;
	}

	public String getCoordinationCreatedAccountCode() {
		return coordinationCreatedAccountCode;
	}

	public void setCoordinationCreatedAccountCode(String coordinationCreatedAccountCode) {
		this.coordinationCreatedAccountCode = coordinationCreatedAccountCode;
	}

	public LocalDateTime getCoordinationUpdateDatetime() {
		return coordinationUpdateDatetime;
	}

	public void setCoordinationUpdateDatetime(LocalDateTime coordinationUpdateDatetime) {
		this.coordinationUpdateDatetime = coordinationUpdateDatetime;
	}

	public String getCoordinationUpdateAccountCode() {
		return coordinationUpdateAccountCode;
	}

	public void setCoordinationUpdateAccountCode(String coordinationUpdateAccountCode) {
		this.coordinationUpdateAccountCode = coordinationUpdateAccountCode;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	protected ModelMapper<IRemodelingHistory> modelMapper(SqlSession session) {
		return session.getMapper(IRemodelingHistoryModelMapper.class);
	}

}
