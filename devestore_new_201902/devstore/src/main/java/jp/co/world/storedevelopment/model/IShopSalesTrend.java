package jp.co.world.storedevelopment.model;

import java.math.BigDecimal;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.IShopSalesTrendModelMapper;

/**
 * @author tainm
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class IShopSalesTrend extends IActiveModel<IShopSalesTrend> {

	private String buildingCd;
	private String buildingName;
	private Long shopId;
	private String shopName;
	private BigDecimal tsuboNum;
	private BigDecimal sales;
	private BigDecimal salesPlanningRatio;
	private BigDecimal salesYearToYear;
	private BigDecimal operatingIncome;
	private BigDecimal operatingProfitPlanningRatio;
	private BigDecimal operatingProfitYearToYear;
	private BigDecimal monthTsuboSales;
	private BigDecimal monthTsuboSalesPlanningRatio;
	private BigDecimal monthTsuboSalesYearToYear;
	private String appropriatingYearMonth;
	private Integer dispOrder;

	public String getBuildingCd() {
		return buildingCd;
	}

	public void setBuildingCd(String buildingCd) {
		this.buildingCd = buildingCd;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public BigDecimal getTsuboNum() {
		return tsuboNum;
	}

	public void setTsuboNum(BigDecimal tsuboNum) {
		this.tsuboNum = tsuboNum;
	}

	public BigDecimal getSales() {
		return sales;
	}

	public void setSales(BigDecimal sales) {
		this.sales = sales;
	}

	public BigDecimal getSalesPlanningRatio() {
		return salesPlanningRatio;
	}

	public void setSalesPlanningRatio(BigDecimal salesPlanningRatio) {
		this.salesPlanningRatio = salesPlanningRatio;
	}

	public BigDecimal getSalesYearToYear() {
		return salesYearToYear;
	}

	public void setSalesYearToYear(BigDecimal salesYearToYear) {
		this.salesYearToYear = salesYearToYear;
	}

	public BigDecimal getOperatingIncome() {
		return operatingIncome;
	}

	public void setOperatingIncome(BigDecimal operatingIncome) {
		this.operatingIncome = operatingIncome;
	}

	public BigDecimal getOperatingProfitPlanningRatio() {
		return operatingProfitPlanningRatio;
	}

	public void setOperatingProfitPlanningRatio(BigDecimal operatingProfitPlanningRatio) {
		this.operatingProfitPlanningRatio = operatingProfitPlanningRatio;
	}

	public BigDecimal getOperatingProfitYearToYear() {
		return operatingProfitYearToYear;
	}

	public void setOperatingProfitYearToYear(BigDecimal operatingProfitYearToYear) {
		this.operatingProfitYearToYear = operatingProfitYearToYear;
	}

	public BigDecimal getMonthTsuboSales() {
		return monthTsuboSales;
	}

	public void setMonthTsuboSales(BigDecimal monthTsuboSales) {
		this.monthTsuboSales = monthTsuboSales;
	}

	public BigDecimal getMonthTsuboSalesPlanningRatio() {
		return monthTsuboSalesPlanningRatio;
	}

	public void setMonthTsuboSalesPlanningRatio(BigDecimal monthTsuboSalesPlanningRatio) {
		this.monthTsuboSalesPlanningRatio = monthTsuboSalesPlanningRatio;
	}

	public BigDecimal getMonthTsuboSalesYearToYear() {
		return monthTsuboSalesYearToYear;
	}

	public void setMonthTsuboSalesYearToYear(BigDecimal monthTsuboSalesYearToYear) {
		this.monthTsuboSalesYearToYear = monthTsuboSalesYearToYear;
	}

	public String getAppropriatingYearMonth() {
		return appropriatingYearMonth;
	}

	public void setAppropriatingYearMonth(String appropriatingYearMonth) {
		this.appropriatingYearMonth = appropriatingYearMonth;
	}

	public Integer getDispOrder() {
		return dispOrder;
	}

	public void setDispOrder(Integer dispOrder) {
		this.dispOrder = dispOrder;
	}

	@Override
	protected ModelMapper<IShopSalesTrend> modelMapper(SqlSession session) {
		return session.getMapper(IShopSalesTrendModelMapper.class);
	}
}