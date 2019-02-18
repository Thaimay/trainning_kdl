package jp.co.world.storedevelopment.model;

import java.math.BigDecimal;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ISalesAgencyConditionModelMapper;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class ISalesAgencyCondition extends IActiveModel<ISalesAgencyCondition> {

	private Long salesAgencyConditionId;
	private Long shopId;
	private String startDate;
	private String endDate;
	private BigDecimal monthlyFixedFeeYenMonth;
	private BigDecimal monthlyFixedFeeYenTsubo;
	private BigDecimal monthlyAssuranceSalesYenMonth;
	private BigDecimal monthlyAssuranceSalesYenTsubo;
	private BigDecimal monthlyAssuranceFeeYenMonth;
	private BigDecimal monthlyAssuranceFeeYenTsubo;
	private BigDecimal monthlyPFeePercent;
	private BigDecimal monthlyBFeePercent;
	private BigDecimal monthlyPRate1;
	private BigDecimal monthlyPRate2;
	private BigDecimal monthlyPRate3;
	private BigDecimal monthlyPRate4;
	private BigDecimal monthlyPRate5;
	private BigDecimal monthlyBRate1;
	private BigDecimal monthlyBRate2;
	private BigDecimal monthlyBRate3;
	private BigDecimal monthlyBRate4;
	private BigDecimal monthlyBRate5;
	private BigDecimal monthlySalesMin1;
	private BigDecimal monthlySalesMin2;
	private BigDecimal monthlySalesMin3;
	private BigDecimal monthlySalesMin4;
	private BigDecimal monthlySalesMin5;
	private BigDecimal monthlySalesMax1;
	private BigDecimal monthlySalesMax2;
	private BigDecimal monthlySalesMax3;
	private BigDecimal monthlySalesMax4;
	private BigDecimal monthlySalesMax5;
	private BigDecimal annualFixedFeeYenYears;
	private BigDecimal annualFixedFeeYenTsubo;
	private BigDecimal annualAssuranceSalesYenYears;
	private BigDecimal annualAssuranceSalesYenTsubo;
	private BigDecimal annualAssuranceFeeYenYears;
	private BigDecimal annualAssuranceFeeYenTsubo;
	private BigDecimal annualPFeePercent;
	private BigDecimal annualBFeePercent;
	private BigDecimal annualPRate1;
	private BigDecimal annualPRate2;
	private BigDecimal annualPRate3;
	private BigDecimal annualPRate4;
	private BigDecimal annualPRate5;
	private BigDecimal annualBRate1;
	private BigDecimal annualBRate2;
	private BigDecimal annualBRate3;
	private BigDecimal annualBRate4;
	private BigDecimal annualBRate5;
	private BigDecimal annualSalesMin1;
	private BigDecimal annualSalesMin2;
	private BigDecimal annualSalesMin3;
	private BigDecimal annualSalesMin4;
	private BigDecimal annualSalesMin5;
	private BigDecimal annualSalesMax1;
	private BigDecimal annualSalesMax2;
	private BigDecimal annualSalesMax3;
	private BigDecimal annualSalesMax4;
	private BigDecimal annualSalesMax5;
	private String coordinationCreatedDatetime;
	private String coordinationCreatedAccountCode;
	private String coordinationUpdateDatetime;
	private String coordinationUpdateAccountCode;
	private String action;

	public Long getSalesAgencyConditionId() {
		return salesAgencyConditionId;
	}

	public void setSalesAgencyConditionId(Long salesAgencyConditionId) {
		this.salesAgencyConditionId = salesAgencyConditionId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
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

	public BigDecimal getMonthlyFixedFeeYenMonth() {
		return monthlyFixedFeeYenMonth;
	}

	public void setMonthlyFixedFeeYenMonth(BigDecimal monthlyFixedFeeYenMonth) {
		this.monthlyFixedFeeYenMonth = monthlyFixedFeeYenMonth;
	}

	public BigDecimal getMonthlyFixedFeeYenTsubo() {
		return monthlyFixedFeeYenTsubo;
	}

	public void setMonthlyFixedFeeYenTsubo(BigDecimal monthlyFixedFeeYenTsubo) {
		this.monthlyFixedFeeYenTsubo = monthlyFixedFeeYenTsubo;
	}

	public BigDecimal getMonthlyAssuranceSalesYenMonth() {
		return monthlyAssuranceSalesYenMonth;
	}

	public void setMonthlyAssuranceSalesYenMonth(BigDecimal monthlyAssuranceSalesYenMonth) {
		this.monthlyAssuranceSalesYenMonth = monthlyAssuranceSalesYenMonth;
	}

	public BigDecimal getMonthlyAssuranceSalesYenTsubo() {
		return monthlyAssuranceSalesYenTsubo;
	}

	public void setMonthlyAssuranceSalesYenTsubo(BigDecimal monthlyAssuranceSalesYenTsubo) {
		this.monthlyAssuranceSalesYenTsubo = monthlyAssuranceSalesYenTsubo;
	}

	public BigDecimal getMonthlyAssuranceFeeYenMonth() {
		return monthlyAssuranceFeeYenMonth;
	}

	public void setMonthlyAssuranceFeeYenMonth(BigDecimal monthlyAssuranceFeeYenMonth) {
		this.monthlyAssuranceFeeYenMonth = monthlyAssuranceFeeYenMonth;
	}

	public BigDecimal getMonthlyAssuranceFeeYenTsubo() {
		return monthlyAssuranceFeeYenTsubo;
	}

	public void setMonthlyAssuranceFeeYenTsubo(BigDecimal monthlyAssuranceFeeYenTsubo) {
		this.monthlyAssuranceFeeYenTsubo = monthlyAssuranceFeeYenTsubo;
	}

	public BigDecimal getMonthlyPFeePercent() {
		return monthlyPFeePercent;
	}

	public void setMonthlyPFeePercent(BigDecimal monthlyPFeePercent) {
		this.monthlyPFeePercent = monthlyPFeePercent;
	}

	public BigDecimal getMonthlyBFeePercent() {
		return monthlyBFeePercent;
	}

	public void setMonthlyBFeePercent(BigDecimal monthlyBFeePercent) {
		this.monthlyBFeePercent = monthlyBFeePercent;
	}

	public BigDecimal getMonthlyPRate1() {
		return monthlyPRate1;
	}

	public void setMonthlyPRate1(BigDecimal monthlyPRate1) {
		this.monthlyPRate1 = monthlyPRate1;
	}

	public BigDecimal getMonthlyPRate2() {
		return monthlyPRate2;
	}

	public void setMonthlyPRate2(BigDecimal monthlyPRate2) {
		this.monthlyPRate2 = monthlyPRate2;
	}

	public BigDecimal getMonthlyPRate3() {
		return monthlyPRate3;
	}

	public void setMonthlyPRate3(BigDecimal monthlyPRate3) {
		this.monthlyPRate3 = monthlyPRate3;
	}

	public BigDecimal getMonthlyPRate4() {
		return monthlyPRate4;
	}

	public void setMonthlyPRate4(BigDecimal monthlyPRate4) {
		this.monthlyPRate4 = monthlyPRate4;
	}

	public BigDecimal getMonthlyPRate5() {
		return monthlyPRate5;
	}

	public void setMonthlyPRate5(BigDecimal monthlyPRate5) {
		this.monthlyPRate5 = monthlyPRate5;
	}

	public BigDecimal getMonthlyBRate1() {
		return monthlyBRate1;
	}

	public void setMonthlyBRate1(BigDecimal monthlyBRate1) {
		this.monthlyBRate1 = monthlyBRate1;
	}

	public BigDecimal getMonthlyBRate2() {
		return monthlyBRate2;
	}

	public void setMonthlyBRate2(BigDecimal monthlyBRate2) {
		this.monthlyBRate2 = monthlyBRate2;
	}

	public BigDecimal getMonthlyBRate3() {
		return monthlyBRate3;
	}

	public void setMonthlyBRate3(BigDecimal monthlyBRate3) {
		this.monthlyBRate3 = monthlyBRate3;
	}

	public BigDecimal getMonthlyBRate4() {
		return monthlyBRate4;
	}

	public void setMonthlyBRate4(BigDecimal monthlyBRate4) {
		this.monthlyBRate4 = monthlyBRate4;
	}

	public BigDecimal getMonthlyBRate5() {
		return monthlyBRate5;
	}

	public void setMonthlyBRate5(BigDecimal monthlyBRate5) {
		this.monthlyBRate5 = monthlyBRate5;
	}

	public BigDecimal getMonthlySalesMin1() {
		return monthlySalesMin1;
	}

	public void setMonthlySalesMin1(BigDecimal monthlySalesMin1) {
		this.monthlySalesMin1 = monthlySalesMin1;
	}

	public BigDecimal getMonthlySalesMin2() {
		return monthlySalesMin2;
	}

	public void setMonthlySalesMin2(BigDecimal monthlySalesMin2) {
		this.monthlySalesMin2 = monthlySalesMin2;
	}

	public BigDecimal getMonthlySalesMin3() {
		return monthlySalesMin3;
	}

	public void setMonthlySalesMin3(BigDecimal monthlySalesMin3) {
		this.monthlySalesMin3 = monthlySalesMin3;
	}

	public BigDecimal getMonthlySalesMin4() {
		return monthlySalesMin4;
	}

	public void setMonthlySalesMin4(BigDecimal monthlySalesMin4) {
		this.monthlySalesMin4 = monthlySalesMin4;
	}

	public BigDecimal getMonthlySalesMin5() {
		return monthlySalesMin5;
	}

	public void setMonthlySalesMin5(BigDecimal monthlySalesMin5) {
		this.monthlySalesMin5 = monthlySalesMin5;
	}

	public BigDecimal getMonthlySalesMax1() {
		return monthlySalesMax1;
	}

	public void setMonthlySalesMax1(BigDecimal monthlySalesMax1) {
		this.monthlySalesMax1 = monthlySalesMax1;
	}

	public BigDecimal getMonthlySalesMax2() {
		return monthlySalesMax2;
	}

	public void setMonthlySalesMax2(BigDecimal monthlySalesMax2) {
		this.monthlySalesMax2 = monthlySalesMax2;
	}

	public BigDecimal getMonthlySalesMax3() {
		return monthlySalesMax3;
	}

	public void setMonthlySalesMax3(BigDecimal monthlySalesMax3) {
		this.monthlySalesMax3 = monthlySalesMax3;
	}

	public BigDecimal getMonthlySalesMax4() {
		return monthlySalesMax4;
	}

	public void setMonthlySalesMax4(BigDecimal monthlySalesMax4) {
		this.monthlySalesMax4 = monthlySalesMax4;
	}

	public BigDecimal getMonthlySalesMax5() {
		return monthlySalesMax5;
	}

	public void setMonthlySalesMax5(BigDecimal monthlySalesMax5) {
		this.monthlySalesMax5 = monthlySalesMax5;
	}

	public BigDecimal getAnnualFixedFeeYenYears() {
		return annualFixedFeeYenYears;
	}

	public void setAnnualFixedFeeYenYears(BigDecimal annualFixedFeeYenYears) {
		this.annualFixedFeeYenYears = annualFixedFeeYenYears;
	}

	public BigDecimal getAnnualFixedFeeYenTsubo() {
		return annualFixedFeeYenTsubo;
	}

	public void setAnnualFixedFeeYenTsubo(BigDecimal annualFixedFeeYenTsubo) {
		this.annualFixedFeeYenTsubo = annualFixedFeeYenTsubo;
	}

	public BigDecimal getAnnualAssuranceSalesYenYears() {
		return annualAssuranceSalesYenYears;
	}

	public void setAnnualAssuranceSalesYenYears(BigDecimal annualAssuranceSalesYenYears) {
		this.annualAssuranceSalesYenYears = annualAssuranceSalesYenYears;
	}

	public BigDecimal getAnnualAssuranceSalesYenTsubo() {
		return annualAssuranceSalesYenTsubo;
	}

	public void setAnnualAssuranceSalesYenTsubo(BigDecimal annualAssuranceSalesYenTsubo) {
		this.annualAssuranceSalesYenTsubo = annualAssuranceSalesYenTsubo;
	}

	public BigDecimal getAnnualAssuranceFeeYenYears() {
		return annualAssuranceFeeYenYears;
	}

	public void setAnnualAssuranceFeeYenYears(BigDecimal annualAssuranceFeeYenYears) {
		this.annualAssuranceFeeYenYears = annualAssuranceFeeYenYears;
	}

	public BigDecimal getAnnualAssuranceFeeYenTsubo() {
		return annualAssuranceFeeYenTsubo;
	}

	public void setAnnualAssuranceFeeYenTsubo(BigDecimal annualAssuranceFeeYenTsubo) {
		this.annualAssuranceFeeYenTsubo = annualAssuranceFeeYenTsubo;
	}

	public BigDecimal getAnnualPFeePercent() {
		return annualPFeePercent;
	}

	public void setAnnualPFeePercent(BigDecimal annualPFeePercent) {
		this.annualPFeePercent = annualPFeePercent;
	}

	public BigDecimal getAnnualBFeePercent() {
		return annualBFeePercent;
	}

	public void setAnnualBFeePercent(BigDecimal annualBFeePercent) {
		this.annualBFeePercent = annualBFeePercent;
	}

	public BigDecimal getAnnualPRate1() {
		return annualPRate1;
	}

	public void setAnnualPRate1(BigDecimal annualPRate1) {
		this.annualPRate1 = annualPRate1;
	}

	public BigDecimal getAnnualPRate2() {
		return annualPRate2;
	}

	public void setAnnualPRate2(BigDecimal annualPRate2) {
		this.annualPRate2 = annualPRate2;
	}

	public BigDecimal getAnnualPRate3() {
		return annualPRate3;
	}

	public void setAnnualPRate3(BigDecimal annualPRate3) {
		this.annualPRate3 = annualPRate3;
	}

	public BigDecimal getAnnualPRate4() {
		return annualPRate4;
	}

	public void setAnnualPRate4(BigDecimal annualPRate4) {
		this.annualPRate4 = annualPRate4;
	}

	public BigDecimal getAnnualPRate5() {
		return annualPRate5;
	}

	public void setAnnualPRate5(BigDecimal annualPRate5) {
		this.annualPRate5 = annualPRate5;
	}

	public BigDecimal getAnnualBRate1() {
		return annualBRate1;
	}

	public void setAnnualBRate1(BigDecimal annualBRate1) {
		this.annualBRate1 = annualBRate1;
	}

	public BigDecimal getAnnualBRate2() {
		return annualBRate2;
	}

	public void setAnnualBRate2(BigDecimal annualBRate2) {
		this.annualBRate2 = annualBRate2;
	}

	public BigDecimal getAnnualBRate3() {
		return annualBRate3;
	}

	public void setAnnualBRate3(BigDecimal annualBRate3) {
		this.annualBRate3 = annualBRate3;
	}

	public BigDecimal getAnnualBRate4() {
		return annualBRate4;
	}

	public void setAnnualBRate4(BigDecimal annualBRate4) {
		this.annualBRate4 = annualBRate4;
	}

	public BigDecimal getAnnualBRate5() {
		return annualBRate5;
	}

	public void setAnnualBRate5(BigDecimal annualBRate5) {
		this.annualBRate5 = annualBRate5;
	}

	public BigDecimal getAnnualSalesMin1() {
		return annualSalesMin1;
	}

	public void setAnnualSalesMin1(BigDecimal annualSalesMin1) {
		this.annualSalesMin1 = annualSalesMin1;
	}

	public BigDecimal getAnnualSalesMin2() {
		return annualSalesMin2;
	}

	public void setAnnualSalesMin2(BigDecimal annualSalesMin2) {
		this.annualSalesMin2 = annualSalesMin2;
	}

	public BigDecimal getAnnualSalesMin3() {
		return annualSalesMin3;
	}

	public void setAnnualSalesMin3(BigDecimal annualSalesMin3) {
		this.annualSalesMin3 = annualSalesMin3;
	}

	public BigDecimal getAnnualSalesMin4() {
		return annualSalesMin4;
	}

	public void setAnnualSalesMin4(BigDecimal annualSalesMin4) {
		this.annualSalesMin4 = annualSalesMin4;
	}

	public BigDecimal getAnnualSalesMin5() {
		return annualSalesMin5;
	}

	public void setAnnualSalesMin5(BigDecimal annualSalesMin5) {
		this.annualSalesMin5 = annualSalesMin5;
	}

	public BigDecimal getAnnualSalesMax1() {
		return annualSalesMax1;
	}

	public void setAnnualSalesMax1(BigDecimal annualSalesMax1) {
		this.annualSalesMax1 = annualSalesMax1;
	}

	public BigDecimal getAnnualSalesMax2() {
		return annualSalesMax2;
	}

	public void setAnnualSalesMax2(BigDecimal annualSalesMax2) {
		this.annualSalesMax2 = annualSalesMax2;
	}

	public BigDecimal getAnnualSalesMax3() {
		return annualSalesMax3;
	}

	public void setAnnualSalesMax3(BigDecimal annualSalesMax3) {
		this.annualSalesMax3 = annualSalesMax3;
	}

	public BigDecimal getAnnualSalesMax4() {
		return annualSalesMax4;
	}

	public void setAnnualSalesMax4(BigDecimal annualSalesMax4) {
		this.annualSalesMax4 = annualSalesMax4;
	}

	public BigDecimal getAnnualSalesMax5() {
		return annualSalesMax5;
	}

	public void setAnnualSalesMax5(BigDecimal annualSalesMax5) {
		this.annualSalesMax5 = annualSalesMax5;
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
	protected ModelMapper<ISalesAgencyCondition> modelMapper(SqlSession session) {
		return session.getMapper(ISalesAgencyConditionModelMapper.class);
	}

}
