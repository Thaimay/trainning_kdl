package jp.co.world.storedevelopment.sp.controller.dto;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.IShop;
import jp.co.world.storedevelopment.model.IShopSalesLatestOneYear;
import jp.co.world.storedevelopment.model.mapper.repository.IShopRepository;

public class IShopSalesLatestOneYearRelationBuildingDetailDTO implements DTO<IShopSalesLatestOneYear> {

	private Long id;
	private String buildingCd;
	private String shopCd;
	private String shopName;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate openDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate closeDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate remodelingDate;
	private BigDecimal tsuboNum;
	private String floor;
	private BigDecimal salesAchievement;
	private BigDecimal salesAchievementComposition;
	private BigDecimal salesAchievementLastYearDifferenceComposition;
	private BigDecimal salesAchievementYearToYear;
	private BigDecimal salesAchievementLastYearDifference;
	private BigDecimal operatingProfitAchievement;
	private BigDecimal operatingProfitAchievementComposition;
	private BigDecimal operatingProfitLastYearDifferenceComposition;
	private BigDecimal operatingProfitYearToYear;
	private BigDecimal operatingProfitLastYearDifference;
	private BigDecimal monthAreaSalesAchievement;
	private BigDecimal monthAreaSalesYearToYear;
	private BigDecimal monthAreaSalesLastYearDifference;
	private Long dispOrder;

	public IShopSalesLatestOneYearRelationBuildingDetailDTO() {

	}

	public IShopSalesLatestOneYearRelationBuildingDetailDTO(IShopSalesLatestOneYear iBuildingShopSalesLatestOneYear) {
		copyProperties(this, iBuildingShopSalesLatestOneYear);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBuildingCd() {
		return buildingCd;
	}

	public void setBuildingCd(String buildingCd) {
		this.buildingCd = buildingCd;
	}

	public String getShopCd() {
		return shopCd;
	}

	public void setShopCd(String shopCd) {
		this.shopCd = shopCd;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public LocalDate getOpenDate() {
		return openDate;
	}

	public void setOpenDate(LocalDate openDate) {
		this.openDate = openDate;
	}

	public LocalDate getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(LocalDate closeDate) {
		this.closeDate = closeDate;
	}

	public LocalDate getRemodelingDate() {
		return remodelingDate;
	}

	public void setRemodelingDate(LocalDate remodelingDate) {
		this.remodelingDate = remodelingDate;
	}

	public BigDecimal getTsuboNum() {
		return tsuboNum;
	}

	public void setTsuboNum(BigDecimal tsuboNum) {
		this.tsuboNum = tsuboNum;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public BigDecimal getSalesAchievement() {
		return salesAchievement;
	}

	public void setSalesAchievement(BigDecimal salesAchievement) {
		this.salesAchievement = salesAchievement;
	}

	public BigDecimal getSalesAchievementComposition() {
		return salesAchievementComposition;
	}

	public void setSalesAchievementComposition(BigDecimal salesAchievementComposition) {
		this.salesAchievementComposition = salesAchievementComposition;
	}

	public BigDecimal getSalesAchievementLastYearDifferenceComposition() {
		return salesAchievementLastYearDifferenceComposition;
	}

	public void setSalesAchievementLastYearDifferenceComposition(
			BigDecimal salesAchievementLastYearDifferenceComposition) {
		this.salesAchievementLastYearDifferenceComposition = salesAchievementLastYearDifferenceComposition;
	}

	public BigDecimal getSalesAchievementYearToYear() {
		return salesAchievementYearToYear;
	}

	public void setSalesAchievementYearToYear(BigDecimal salesAchievementYearToYear) {
		this.salesAchievementYearToYear = salesAchievementYearToYear;
	}

	public BigDecimal getSalesAchievementLastYearDifference() {
		return salesAchievementLastYearDifference;
	}

	public void setSalesAchievementLastYearDifference(BigDecimal salesAchievementLastYearDifference) {
		this.salesAchievementLastYearDifference = salesAchievementLastYearDifference;
	}

	public BigDecimal getOperatingProfitAchievement() {
		return operatingProfitAchievement;
	}

	public void setOperatingProfitAchievement(BigDecimal operatingProfitAchievement) {
		this.operatingProfitAchievement = operatingProfitAchievement;
	}

	public BigDecimal getOperatingProfitAchievementComposition() {
		return operatingProfitAchievementComposition;
	}

	public void setOperatingProfitAchievementComposition(BigDecimal operatingProfitAchievementComposition) {
		this.operatingProfitAchievementComposition = operatingProfitAchievementComposition;
	}

	public BigDecimal getOperatingProfitLastYearDifferenceComposition() {
		return operatingProfitLastYearDifferenceComposition;
	}

	public void setOperatingProfitLastYearDifferenceComposition(
			BigDecimal operatingProfitLastYearDifferenceComposition) {
		this.operatingProfitLastYearDifferenceComposition = operatingProfitLastYearDifferenceComposition;
	}

	public BigDecimal getOperatingProfitYearToYear() {
		return operatingProfitYearToYear;
	}

	public void setOperatingProfitYearToYear(BigDecimal operatingProfitYearToYear) {
		this.operatingProfitYearToYear = operatingProfitYearToYear;
	}

	public BigDecimal getOperatingProfitLastYearDifference() {
		return operatingProfitLastYearDifference;
	}

	public void setOperatingProfitLastYearDifference(BigDecimal operatingProfitLastYearDifference) {
		this.operatingProfitLastYearDifference = operatingProfitLastYearDifference;
	}

	public BigDecimal getMonthAreaSalesAchievement() {
		return monthAreaSalesAchievement;
	}

	public void setMonthAreaSalesAchievement(BigDecimal monthAreaSalesAchievement) {
		this.monthAreaSalesAchievement = monthAreaSalesAchievement;
	}

	public BigDecimal getMonthAreaSalesYearToYear() {
		return monthAreaSalesYearToYear;
	}

	public void setMonthAreaSalesYearToYear(BigDecimal monthAreaSalesYearToYear) {
		this.monthAreaSalesYearToYear = monthAreaSalesYearToYear;
	}

	public BigDecimal getMonthAreaSalesLastYearDifference() {
		return monthAreaSalesLastYearDifference;
	}

	public void setMonthAreaSalesLastYearDifference(BigDecimal monthAreaSalesLastYearDifference) {
		this.monthAreaSalesLastYearDifference = monthAreaSalesLastYearDifference;
	}

	public Long getDispOrder() {
		return dispOrder;
	}

	public void setDispOrder(Long dispOrder) {
		this.dispOrder = dispOrder;
	}

	public IShopRelationShopDetailDTO getiShop() {
		if (getShopCd() == null) {
			return null;
		}

		List<IShop> iShops = new IShopRepository().findByShopCd(getShopCd());
		return iShops != null && iShops.size() > 0 ? new IShopRelationShopDetailDTO(iShops.get(0)) : null;
	}

	@Override
	public IShopSalesLatestOneYear createModel() {
		return new IShopSalesLatestOneYear();
	}

	public String getAchievementRate() {
		String rate = "－";
		if (getiShop() != null && getiShop().getShop() != null
				&& getiShop().getShop().getBuildingExpectedValue() != null
				&& getiShop().getShop().getBuildingExpectedValue() != 0) {

			rate = new DecimalFormat("###,##0.00").format(getMonthAreaSalesAchievement().doubleValue()
					* 1000 * 100 / getiShop().getShop().getBuildingExpectedValue()) + "%";
		}
		return rate;
	}
}
