package jp.co.world.storedevelopment.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.IShopAdminModelMapper;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class IShopAdmin extends ActiveModel<IShopAdmin> {

	private Long shopAdminId;
	private Long shopId;
	private BigDecimal deposit;
	private String rentBrandCd;
	private String directlyOperatedHouseFlag;
	private String openingSettlementNo;
	private BigDecimal interiorCost;
	private BigDecimal equipmentExpenses;
	private BigDecimal burdenMoney;
	private BigDecimal managementExpenses;
	private BigDecimal vmdCost;
	private BigDecimal openingCostSum;
	private BigDecimal ownInteriorCost;
	private BigDecimal ownEquipmentExpenses;
	private BigDecimal ownBurdenMoney;
	private BigDecimal ownManagementExpenses;
	private BigDecimal ownVmdCost;
	private BigDecimal ownOpeningCostSum;
	private String closingSettlementNo;
	private String closingReason;
	private BigDecimal closingReason1;
	private BigDecimal closingReason2;
	private BigDecimal closingReason3;
	private BigDecimal currentSectionStatus;
	private BigDecimal alternativeOpeningDivisionId;
	private String realClosingFlag;
	private BigDecimal lossOnRetirementIncome;
	private BigDecimal lossOnRetirementAccounting;
	private BigDecimal cancellationMoney;
	private BigDecimal currentStatusRecoveryMoney;
	private BigDecimal penalty;
	private BigDecimal otherExpenses;
	private BigDecimal closingCostSum;
	private BigDecimal ownClosingCostSum;
	private String contentsReplacement;
	private String shopOtherFlag;
	private BigDecimal shopTypeId;
	private String shopCount;
	private BigDecimal brandIncomeUnitId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime coordinationCreatedDatetime;
	private String coordinationCreatedAccountCode;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime coordinationUpdateDatetime;
	private String coordinationUpdateAccountCode;
	private String action;

	private String[] ignoreFields = new String[] {};

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	@Override
	protected ModelMapper<IShopAdmin> modelMapper(SqlSession session) {
		return session.getMapper(IShopAdminModelMapper.class);
	}

	public Long getShopAdminId() {
		return shopAdminId;
	}

	public void setShopAdminId(Long shopAdminId) {
		this.shopAdminId = shopAdminId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public String getRentBrandCd() {
		return rentBrandCd;
	}

	public void setRentBrandCd(String rentBrandCd) {
		this.rentBrandCd = rentBrandCd;
	}

	public String getDirectlyOperatedHouseFlag() {
		return directlyOperatedHouseFlag;
	}

	public void setDirectlyOperatedHouseFlag(String directlyOperatedHouseFlag) {
		this.directlyOperatedHouseFlag = directlyOperatedHouseFlag;
	}

	public String getOpeningSettlementNo() {
		return openingSettlementNo;
	}

	public void setOpeningSettlementNo(String openingSettlementNo) {
		this.openingSettlementNo = openingSettlementNo;
	}

	public BigDecimal getInteriorCost() {
		return interiorCost;
	}

	public void setInteriorCost(BigDecimal interiorCost) {
		this.interiorCost = interiorCost;
	}

	public BigDecimal getEquipmentExpenses() {
		return equipmentExpenses;
	}

	public void setEquipmentExpenses(BigDecimal equipmentExpenses) {
		this.equipmentExpenses = equipmentExpenses;
	}

	public BigDecimal getBurdenMoney() {
		return burdenMoney;
	}

	public void setBurdenMoney(BigDecimal burdenMoney) {
		this.burdenMoney = burdenMoney;
	}

	public BigDecimal getManagementExpenses() {
		return managementExpenses;
	}

	public void setManagementExpenses(BigDecimal managementExpenses) {
		this.managementExpenses = managementExpenses;
	}

	public BigDecimal getVmdCost() {
		return vmdCost;
	}

	public void setVmdCost(BigDecimal vmdCost) {
		this.vmdCost = vmdCost;
	}

	public BigDecimal getOpeningCostSum() {
		return openingCostSum;
	}

	public void setOpeningCostSum(BigDecimal openingCostSum) {
		this.openingCostSum = openingCostSum;
	}

	public BigDecimal getOwnInteriorCost() {
		return ownInteriorCost;
	}

	public void setOwnInteriorCost(BigDecimal ownInteriorCost) {
		this.ownInteriorCost = ownInteriorCost;
	}

	public BigDecimal getOwnEquipmentExpenses() {
		return ownEquipmentExpenses;
	}

	public void setOwnEquipmentExpenses(BigDecimal ownEquipmentExpenses) {
		this.ownEquipmentExpenses = ownEquipmentExpenses;
	}

	public BigDecimal getOwnBurdenMoney() {
		return ownBurdenMoney;
	}

	public void setOwnBurdenMoney(BigDecimal ownBurdenMoney) {
		this.ownBurdenMoney = ownBurdenMoney;
	}

	public BigDecimal getOwnManagementExpenses() {
		return ownManagementExpenses;
	}

	public void setOwnManagementExpenses(BigDecimal ownManagementExpenses) {
		this.ownManagementExpenses = ownManagementExpenses;
	}

	public BigDecimal getOwnVmdCost() {
		return ownVmdCost;
	}

	public void setOwnVmdCost(BigDecimal ownVmdCost) {
		this.ownVmdCost = ownVmdCost;
	}

	public BigDecimal getOwnOpeningCostSum() {
		return ownOpeningCostSum;
	}

	public void setOwnOpeningCostSum(BigDecimal ownOpeningCostSum) {
		this.ownOpeningCostSum = ownOpeningCostSum;
	}

	public String getClosingSettlementNo() {
		return closingSettlementNo;
	}

	public void setClosingSettlementNo(String closingSettlementNo) {
		this.closingSettlementNo = closingSettlementNo;
	}

	public String getClosingReason() {
		return closingReason;
	}

	public void setClosingReason(String closingReason) {
		this.closingReason = closingReason;
	}

	public BigDecimal getClosingReason1() {
		return closingReason1;
	}

	public void setClosingReason1(BigDecimal closingReason1) {
		this.closingReason1 = closingReason1;
	}

	public BigDecimal getClosingReason2() {
		return closingReason2;
	}

	public void setClosingReason2(BigDecimal closingReason2) {
		this.closingReason2 = closingReason2;
	}

	public BigDecimal getClosingReason3() {
		return closingReason3;
	}

	public void setClosingReason3(BigDecimal closingReason3) {
		this.closingReason3 = closingReason3;
	}

	public BigDecimal getCurrentSectionStatus() {
		return currentSectionStatus;
	}

	public void setCurrentSectionStatus(BigDecimal currentSectionStatus) {
		this.currentSectionStatus = currentSectionStatus;
	}

	public BigDecimal getAlternativeOpeningDivisionId() {
		return alternativeOpeningDivisionId;
	}

	public void setAlternativeOpeningDivisionId(BigDecimal alternativeOpeningDivisionId) {
		this.alternativeOpeningDivisionId = alternativeOpeningDivisionId;
	}

	public String getRealClosingFlag() {
		return realClosingFlag;
	}

	public void setRealClosingFlag(String realClosingFlag) {
		this.realClosingFlag = realClosingFlag;
	}

	public BigDecimal getLossOnRetirementIncome() {
		return lossOnRetirementIncome;
	}

	public void setLossOnRetirementIncome(BigDecimal lossOnRetirementIncome) {
		this.lossOnRetirementIncome = lossOnRetirementIncome;
	}

	public BigDecimal getLossOnRetirementAccounting() {
		return lossOnRetirementAccounting;
	}

	public void setLossOnRetirementAccounting(BigDecimal lossOnRetirementAccounting) {
		this.lossOnRetirementAccounting = lossOnRetirementAccounting;
	}

	public BigDecimal getCancellationMoney() {
		return cancellationMoney;
	}

	public void setCancellationMoney(BigDecimal cancellationMoney) {
		this.cancellationMoney = cancellationMoney;
	}

	public BigDecimal getCurrentStatusRecoveryMoney() {
		return currentStatusRecoveryMoney;
	}

	public void setCurrentStatusRecoveryMoney(BigDecimal currentStatusRecoveryMoney) {
		this.currentStatusRecoveryMoney = currentStatusRecoveryMoney;
	}

	public BigDecimal getPenalty() {
		return penalty;
	}

	public void setPenalty(BigDecimal penalty) {
		this.penalty = penalty;
	}

	public BigDecimal getOtherExpenses() {
		return otherExpenses;
	}

	public void setOtherExpenses(BigDecimal otherExpenses) {
		this.otherExpenses = otherExpenses;
	}

	public BigDecimal getClosingCostSum() {
		return closingCostSum;
	}

	public void setClosingCostSum(BigDecimal closingCostSum) {
		this.closingCostSum = closingCostSum;
	}

	public BigDecimal getOwnClosingCostSum() {
		return ownClosingCostSum;
	}

	public void setOwnClosingCostSum(BigDecimal ownClosingCostSum) {
		this.ownClosingCostSum = ownClosingCostSum;
	}

	public String getContentsReplacement() {
		return contentsReplacement;
	}

	public void setContentsReplacement(String contentsReplacement) {
		this.contentsReplacement = contentsReplacement;
	}

	public String getShopOtherFlag() {
		return shopOtherFlag;
	}

	public void setShopOtherFlag(String shopOtherFlag) {
		this.shopOtherFlag = shopOtherFlag;
	}

	public BigDecimal getShopTypeId() {
		return shopTypeId;
	}

	public void setShopTypeId(BigDecimal shopTypeId) {
		this.shopTypeId = shopTypeId;
	}

	public String getShopCount() {
		return shopCount;
	}

	public void setShopCount(String shopCount) {
		this.shopCount = shopCount;
	}

	public BigDecimal getBrandIncomeUnitId() {
		return brandIncomeUnitId;
	}

	public void setBrandIncomeUnitId(BigDecimal brandIncomeUnitId) {
		this.brandIncomeUnitId = brandIncomeUnitId;
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

}
