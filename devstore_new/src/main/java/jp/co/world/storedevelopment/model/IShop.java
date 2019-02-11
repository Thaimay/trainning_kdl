package jp.co.world.storedevelopment.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.IShopModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.IRentContractRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesAgencyTargetRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IShopSalesLatestOneYearRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ShopRepository;

/**
 * @author tainm
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class IShop extends IActiveModel<IShop> {

	private Long shopId;
	private String shopCd;
	private String shopNameZenkaku;
	private String shopNameZenkakuHankaku;
	private String shopNameKana;
	private String shopAbbreviationName;
	private String shopAbbreviationNameAccounting;
	private String postCd;
	private String prefecturesName;
	private String cityName;
	private String townNameAddress;
	private String buildingName;
	private String floorNum;
	private String shopAddress;
	private String shopAddressOld;
	private String shopAddressKana1;
	private String shopAddressKana2;
	private String phoneNumber;
	private String faxNumber;
	private Long iShopCompanyId;
	private String jisPrefecturesCd;
	private String jisDistrictCd;
	private Long marketId;
	private String transactionGp;
	private String shopTownParentId;
	private String consolidationId;
	private Long compoundShopDivisionId;
	private Long iIncomeUnitId;
	private BigDecimal contractTsubo;
	private BigDecimal effectiveTsubo;
	private BigDecimal backyardTsubo;
	private BigDecimal contractArea;
	private BigDecimal effectiveArea;
	private BigDecimal backyardArea;
	private Long statusId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate openDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate preOpenDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate closeDate;
	private Long salesAgencyId;
	private Long salesChannelId;
	private String isEnhancedShop;
	private Long segmentDivisionId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate accountingStartDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate accountingEndDate;
	private String departmentStoreUnificationCd;
	private Long placeId;
	private Long wholesaleAreaId;
	private Long htposDivisionId;
	private Long stepsRateCalculationDivisionId;
	private Long depreciationOfFixedAssetsDivisionId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime relationUpdateDatetime;
	private String action;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime coordinationCreatedDatetime;
	private String coordinationCreatedAccountCode;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime coordinationUpdateDatetime;
	private String coordinationUpdateAccountCode;
	private Boolean isWsdCoordinated;

	public Optional<Shop> shop() {
		Shop model = new ShopRepository().findByIShopId(getId());
		return Optional.ofNullable(model);
	}
	
	public Optional<Building> building() {
		return new IShopSalesLatestOneYearRepository().findBy(getShopCd());
	}
	
	public Optional<IRentContract> contract() {
		List<IRentContract> list = new IRentContractRepository().findByShopId(getId());
		
		if (list.size() > 0) {
			return Optional.ofNullable(list.get(list.size() - 1));
		} else {
			return Optional.ofNullable(null);
		}
	}
	
	public String tsuboValue() {
		if (getContractTsubo() == null) {
			return "";
		} else {
			return String.format("%.2f", getContractTsubo().floatValue());
		}
	}

	public Optional<ISalesAgencyTarget> progressSalesAgency() {
		return new ISalesAgencyTargetRepository()
				.findById(getSalesAgencyId());
	}
	
	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getShopCd() {
		return shopCd;
	}

	public void setShopCd(String shopCd) {
		this.shopCd = shopCd;
	}

	public String getShopNameZenkaku() {
		return shopNameZenkaku;
	}

	public void setShopNameZenkaku(String shopNameZenkaku) {
		this.shopNameZenkaku = shopNameZenkaku;
	}

	public String getShopNameZenkakuHankaku() {
		return shopNameZenkakuHankaku;
	}

	public void setShopNameZenkakuHankaku(String shopNameZenkakuHankaku) {
		this.shopNameZenkakuHankaku = shopNameZenkakuHankaku;
	}

	public String getShopNameKana() {
		return shopNameKana;
	}

	public void setShopNameKana(String shopNameKana) {
		this.shopNameKana = shopNameKana;
	}

	public String getShopAbbreviationName() {
		return shopAbbreviationName;
	}

	public void setShopAbbreviationName(String shopAbbreviationName) {
		this.shopAbbreviationName = shopAbbreviationName;
	}

	public String getShopAbbreviationNameAccounting() {
		return shopAbbreviationNameAccounting;
	}

	public void setShopAbbreviationNameAccounting(String shopAbbreviationNameAccounting) {
		this.shopAbbreviationNameAccounting = shopAbbreviationNameAccounting;
	}

	public String getPostCd() {
		return postCd;
	}

	public void setPostCd(String postCd) {
		this.postCd = postCd;
	}

	public String getPrefecturesName() {
		return prefecturesName;
	}

	public void setPrefecturesName(String prefecturesName) {
		this.prefecturesName = prefecturesName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getTownNameAddress() {
		return townNameAddress;
	}

	public void setTownNameAddress(String townNameAddress) {
		this.townNameAddress = townNameAddress;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getFloorNum() {
		return floorNum;
	}

	public void setFloorNum(String floorNum) {
		this.floorNum = floorNum;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}

	public String getShopAddressOld() {
		return shopAddressOld;
	}

	public void setShopAddressOld(String shopAddressOld) {
		this.shopAddressOld = shopAddressOld;
	}

	public String getShopAddressKana1() {
		return shopAddressKana1;
	}

	public void setShopAddressKana1(String shopAddressKana1) {
		this.shopAddressKana1 = shopAddressKana1;
	}

	public String getShopAddressKana2() {
		return shopAddressKana2;
	}

	public void setShopAddressKana2(String shopAddressKana2) {
		this.shopAddressKana2 = shopAddressKana2;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public Long getiShopCompanyId() {
		return iShopCompanyId;
	}

	public void setiShopCompanyId(Long iShopCompanyId) {
		this.iShopCompanyId = iShopCompanyId;
	}

	public String getJisPrefecturesCd() {
		return jisPrefecturesCd;
	}

	public void setJisPrefecturesCd(String jisPrefecturesCd) {
		this.jisPrefecturesCd = jisPrefecturesCd;
	}

	public String getJisDistrictCd() {
		return jisDistrictCd;
	}

	public void setJisDistrictCd(String jisDistrictCd) {
		this.jisDistrictCd = jisDistrictCd;
	}

	public Long getMarketId() {
		return marketId;
	}

	public void setMarketId(Long marketId) {
		this.marketId = marketId;
	}

	public String getTransactionGp() {
		return transactionGp;
	}

	public void setTransactionGp(String transactionGp) {
		this.transactionGp = transactionGp;
	}

	public String getShopTownParentId() {
		return shopTownParentId;
	}

	public void setShopTownParentId(String shopTownParentId) {
		this.shopTownParentId = shopTownParentId;
	}

	public String getConsolidationId() {
		return consolidationId;
	}

	public void setConsolidationId(String consolidationId) {
		this.consolidationId = consolidationId;
	}

	public Long getCompoundShopDivisionId() {
		return compoundShopDivisionId;
	}

	public void setCompoundShopDivisionId(Long compoundShopDivisionId) {
		this.compoundShopDivisionId = compoundShopDivisionId;
	}

	public Long getiIncomeUnitId() {
		return iIncomeUnitId;
	}

	public void setiIncomeUnitId(Long iIncomeUnitId) {
		this.iIncomeUnitId = iIncomeUnitId;
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

	public BigDecimal getBackyardTsubo() {
		return backyardTsubo;
	}

	public void setBackyardTsubo(BigDecimal backyardTsubo) {
		this.backyardTsubo = backyardTsubo;
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

	public BigDecimal getBackyardArea() {
		return backyardArea;
	}

	public void setBackyardArea(BigDecimal backyardArea) {
		this.backyardArea = backyardArea;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public LocalDate getOpenDate() {
		return openDate;
	}

	public void setOpenDate(LocalDate openDate) {
		this.openDate = openDate;
	}

	public LocalDate getPreOpenDate() {
		return preOpenDate;
	}

	public void setPreOpenDate(LocalDate preOpenDate) {
		this.preOpenDate = preOpenDate;
	}

	public LocalDate getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(LocalDate closeDate) {
		this.closeDate = closeDate;
	}

	public Long getSalesAgencyId() {
		return salesAgencyId;
	}

	public void setSalesAgencyId(Long salesAgencyId) {
		this.salesAgencyId = salesAgencyId;
	}

	public Long getSalesChannelId() {
		return salesChannelId;
	}

	public void setSalesChannelId(Long salesChannelId) {
		this.salesChannelId = salesChannelId;
	}

	public String getIsEnhancedShop() {
		return isEnhancedShop;
	}

	public void setIsEnhancedShop(String isEnhancedShop) {
		this.isEnhancedShop = isEnhancedShop;
	}

	public Long getSegmentDivisionId() {
		return segmentDivisionId;
	}

	public void setSegmentDivisionId(Long segmentDivisionId) {
		this.segmentDivisionId = segmentDivisionId;
	}

	public LocalDate getAccountingStartDate() {
		return accountingStartDate;
	}

	public void setAccountingStartDate(LocalDate accountingStartDate) {
		this.accountingStartDate = accountingStartDate;
	}

	public LocalDate getAccountingEndDate() {
		return accountingEndDate;
	}

	public void setAccountingEndDate(LocalDate accountingEndDate) {
		this.accountingEndDate = accountingEndDate;
	}

	public String getDepartmentStoreUnificationCd() {
		return departmentStoreUnificationCd;
	}

	public void setDepartmentStoreUnificationCd(String departmentStoreUnificationCd) {
		this.departmentStoreUnificationCd = departmentStoreUnificationCd;
	}

	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}

	public Long getWholesaleAreaId() {
		return wholesaleAreaId;
	}

	public void setWholesaleAreaId(Long wholesaleAreaId) {
		this.wholesaleAreaId = wholesaleAreaId;
	}

	public Long getHtposDivisionId() {
		return htposDivisionId;
	}

	public void setHtposDivisionId(Long htposDivisionId) {
		this.htposDivisionId = htposDivisionId;
	}

	public Long getStepsRateCalculationDivisionId() {
		return stepsRateCalculationDivisionId;
	}

	public void setStepsRateCalculationDivisionId(Long stepsRateCalculationDivisionId) {
		this.stepsRateCalculationDivisionId = stepsRateCalculationDivisionId;
	}

	public Long getDepreciationOfFixedAssetsDivisionId() {
		return depreciationOfFixedAssetsDivisionId;
	}

	public void setDepreciationOfFixedAssetsDivisionId(Long depreciationOfFixedAssetsDivisionId) {
		this.depreciationOfFixedAssetsDivisionId = depreciationOfFixedAssetsDivisionId;
	}

	public LocalDateTime getRelationUpdateDatetime() {
		return relationUpdateDatetime;
	}

	public void setRelationUpdateDatetime(LocalDateTime relationUpdateDatetime) {
		this.relationUpdateDatetime = relationUpdateDatetime;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
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

	public Boolean getIsWsdCoordinated() {
		return isWsdCoordinated;
	}

	public void setIsWsdCoordinated(Boolean isWsdCoordinated) {
		this.isWsdCoordinated = isWsdCoordinated;
	}

	@Override
	protected ModelMapper<IShop> modelMapper(SqlSession session) {
		return session.getMapper(IShopModelMapper.class);
	}
}