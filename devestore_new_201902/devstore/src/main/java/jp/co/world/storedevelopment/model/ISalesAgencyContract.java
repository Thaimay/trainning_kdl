package jp.co.world.storedevelopment.model;

import java.math.BigDecimal;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ISalesAgencyContractModelMapper;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class ISalesAgencyContract extends IActiveModel<ISalesAgencyContract> {

	private Long salesAgencyContractId;
	private Long shopId;
	private String settlementNo;
	private String startDate;
	private String endDate;
	private Long salesAgencyTargetId;
	private Long feeTypeDivisionId;
	private String remarks;
	private String companySales;
	private BigDecimal companySalesConditions;
	private BigDecimal depletionPRate;
	private BigDecimal depletionBRate;
	private String offSet;
	private String advancesExpenses;
	private String coordinationCreatedDatetime;
	private String coordinationCreatedAccountCode;
	private String coordinationUpdateDatetime;
	private String coordinationUpdateAccountCode;
	private String action;

	public Long getSalesAgencyContractId() {
		return salesAgencyContractId;
	}

	public void setSalesAgencyContractId(Long salesAgencyContractId) {
		this.salesAgencyContractId = salesAgencyContractId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getSettlementNo() {
		return settlementNo;
	}

	public void setSettlementNo(String settlementNo) {
		this.settlementNo = settlementNo;
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

	public Long getSalesAgencyTargetId() {
		return salesAgencyTargetId;
	}

	public void setSalesAgencyTargetId(Long salesAgencyTargetId) {
		this.salesAgencyTargetId = salesAgencyTargetId;
	}

	public Long getFeeTypeDivisionId() {
		return feeTypeDivisionId;
	}

	public void setFeeTypeDivisionId(Long feeTypeDivisionId) {
		this.feeTypeDivisionId = feeTypeDivisionId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCompanySales() {
		return companySales;
	}

	public void setCompanySales(String companySales) {
		this.companySales = companySales;
	}

	public BigDecimal getCompanySalesConditions() {
		return companySalesConditions;
	}

	public void setCompanySalesConditions(BigDecimal companySalesConditions) {
		this.companySalesConditions = companySalesConditions;
	}

	public BigDecimal getDepletionPRate() {
		return depletionPRate;
	}

	public void setDepletionPRate(BigDecimal depletionPRate) {
		this.depletionPRate = depletionPRate;
	}

	public BigDecimal getDepletionBRate() {
		return depletionBRate;
	}

	public void setDepletionBRate(BigDecimal depletionBRate) {
		this.depletionBRate = depletionBRate;
	}

	public String getOffSet() {
		return offSet;
	}

	public void setOffSet(String offSet) {
		this.offSet = offSet;
	}

	public String getAdvancesExpenses() {
		return advancesExpenses;
	}

	public void setAdvancesExpenses(String advancesExpenses) {
		this.advancesExpenses = advancesExpenses;
	}

	public String getCoordinationCreatedDatetime() {
		return coordinationCreatedDatetime;
	}

	public void setCoordinationCreatedDatetime(String coordinationCreatedDatetime) {
		this.coordinationCreatedDatetime = coordinationCreatedDatetime;
	}

	public String getCoordinationCreatedAccountCode() {
		return coordinationCreatedAccountCode;
	}

	public void setCoordinationCreatedAccountCode(String coordinationCreatedAccountCode) {
		this.coordinationCreatedAccountCode = coordinationCreatedAccountCode;
	}

	public String getCoordinationUpdateDatetime() {
		return coordinationUpdateDatetime;
	}

	public void setCoordinationUpdateDatetime(String coordinationUpdateDatetime) {
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
	protected ModelMapper<ISalesAgencyContract> modelMapper(SqlSession session) {
		return session.getMapper(ISalesAgencyContractModelMapper.class);
	}

}
