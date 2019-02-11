package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ProjectSwitingItemControlModelMapper;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName", "id", "corporationGroup", "createdAccountCode", "updateAccountCode",
		"createdDatetime", "updateDatetime", "isDeleted", "projectCategoryId" })
public class ProjectSwitingItemControl extends ActiveModel<ProjectSwitingItemControl> {

	private Long projectCategoryId;
	private Boolean basicBuilding = false;
	private Boolean basicChannel = false;
	private Boolean basicCorporationGroup = false;
	private Boolean basicCorporation = false;
	private Boolean basicShopName = false;
	private Boolean basicShop = false;
	private Boolean basicShopCd = false;
	private Boolean basicTsubo = false;
	private Boolean basicManagementForm = false;
	private Boolean basicBrand = false;
	private Boolean basicArea = false;
	private Boolean basicAdoptDifficulty = false;
	private Boolean basicBlock = false;
	private Boolean basicPrefectures = false;
	private Boolean basicInvestmentDiscussion = false;
	private Boolean basicWorkDivision = false;
	private Boolean basicSalesAgency = false;
	private Boolean basicAffiliateShopCorporation = false;
	private Boolean basicInHouseProgress = false;
	private Boolean basicNegotiationProgress = false;
	private Boolean basicExternalReleaseStartDate = false;
	private Boolean basicExternalReleaseConfirm = false;
	private Boolean basicActionStatus = false;
	private Boolean basicProjectStartDate = false;
	private Boolean basicStop = false;
	private Boolean basicStopReason = false;
	private Boolean basicDescription = false;
	private Boolean basicPlan = false;
	private Boolean basicPlanImplementationDate = false;
	private Boolean basicPlanPeriod = false;
	private Boolean basicOtherRequest = false;
	private Boolean basicOurRequest = false;
	private Boolean basicProfitExpectation = false;
	private Boolean basicDirectionalityGroup = false;
	private Boolean basicDirectionalityLandingDirectionalityName = false;
	private Boolean basicDirectionalityLandingDirectionalityPercent = false;
	private Boolean basicDirectionalityRequestorName = false;
	private Boolean basicDirectionalityRequestorStatus = false;
	private Boolean basicDirectionalityLandingDirectionalityMemo = false;
	private Boolean basicDirectionalityRentIncreaseDecrease = false;
	private Boolean basicDirectionalityTenancy = false;
	private Boolean basicDirectionalitySalesPrediction = false;
	private Boolean basicDirectionalityTenancyPeriod = false;
	private Boolean basicDirectionalityProfitRate = false;
	private Boolean basicDirectionalityImplementationDatetime = false;
	private Boolean basicDirectionalityRedecorationLastSalesDate = false;
	private Boolean basicDirectionalityImplementationPeriod = false;
	private Boolean basicDirectionalityManagementForm = false;
	private Boolean basicCurrentStoreGroup = false;
	private Boolean basicCurrentStoreSection = false;
	private Boolean basicCurrentStoreFrontage = false;
	private Boolean basicCurrentStoreFloor = false;
	private Boolean basicCurrentStoreContractTsubo = false;
	private Boolean basicCurrentStoreBusinessHours = false;
	private Boolean basicCurrentStoreMemo = false;
	private Boolean basicCurrentStoreBuildingExpectedValue = false;
	private Boolean basicNegotiationStoreGroup = false;
	private Boolean basicNegotiationStoreSection = false;
	private Boolean basicNegotiationStoreFloor = false;
	private Boolean basicNegotiationStoreFrontage = false;
	private Boolean basicNegotiationStoreContractTsubo = false;
	private Boolean basicNegotiationStoreContractTsuboIncreaseDecrease = false;
	private Boolean basicNegotiationStoreBusinessHours = false;
	private Boolean basicNegotiationStoreMemo = false;
	private Boolean basicNegotiationStoreExpectedValue = false;
	private Boolean basicCurrentContractGroup = false;
	private Boolean basicCurrentContractForm = false;
	private Boolean basicCurrentContractName = false;
	private Boolean basicCurrentContractStartDate = false;
	private Boolean basicCurrentContractEndDate = false;
	private Boolean basicCurrentContractYear = false;
	private Boolean basicCurrentContractTenancyExpirationPeriod = false;
	private Boolean basicCurrentContractAutoUpdate = false;
	private Boolean basicCurrentContractEconomicCondition = false;
	private Boolean basicCurrentContractRentReduceStartDate = false;
	private Boolean basicCurrentContractRentReduceEndDate = false;
	private Boolean basicCurrentContractRentReduceYear = false;
	private Boolean basicCurrentContractMemo = false;
	private Boolean basicNegotiationContractGroup = false;
	private Boolean basicNegotiationContractForm = false;
	private Boolean basicNegotiationContractName = false;
	private Boolean basicNegotiationContractStartDate = false;
	private Boolean basicNegotiationContractEndDate = false;
	private Boolean basicNegotiationContractYear = false;
	private Boolean basicNegotiationContractTenancyExpirationPeriod = false;
	private Boolean basicNegotiationContractAutoUpdate = false;
	private Boolean basicNegotiationContractRentReduceStartDate = false;
	private Boolean basicNegotiationContractRentReduceEndDate = false;
	private Boolean basicNegotiationContractRentReduceYear = false;
	private Boolean basicNegotiationContractMemo = false;
	private Boolean basicCurrentRelatedCorporationGroup = false;
	private Boolean basicCurrentRelatedCorporationSalesAgency = false;
	private Boolean basicCurrentRelatedCorporationSalesAgencyStartDate = false;
	private Boolean basicCurrentRelatedCorporationSalesAgencyEndDate = false;
	private Boolean basicCurrentRelatedCorporationSalesAgencyYear = false;
	private Boolean basicCurrentRelatedCorporationAffiliateShop = false;
	private Boolean basicCurrentRelatedCorporationPeriod = false;
	private Boolean basicNegotiationRelatedCorporationGroup = false;
	private Boolean basicNegotiationRelatedCorporationSalesAgency = false;
	private Boolean basicNegotiationRelatedCorporationSalesAgencyStartDate = false;
	private Boolean basicNegotiationRelatedCorporationSalesAgencyEndDate = false;
	private Boolean basicNegotiationRelatedCorporationSalesAgencyYear = false;
	private Boolean basicNegotiationRelatedCorporationAffiliateShop = false;
	private Boolean basicNegotiationRelatedCorporationPeriod = false;
	private Boolean basicBranchStoreStaffOpinion = false;
	private Boolean basicBranchStoreOpinion = false;
	private Boolean basicBuOpinion = false;
	private Boolean scheduleActionScheduleGroup = false;
	private Boolean scheduleArticleReviewDatetime = false;
	private Boolean scheduleArticleReviewResult = false;
	private Boolean scheduleArticleReviewResultComment = false;
	private Boolean scheduleManagementDatetime = false;
	private Boolean scheduleManagementResult = false;
	private Boolean scheduleManagementResultComment = false;
	private Boolean scheduleInvestmentProcessDatetime = false;
	private Boolean scheduleInvestmentProcessResult = false;
	private Boolean scheduleInvestmentProcessResultComment = false;
	private Boolean schedulePastMeetingGroup = false;
	private Boolean schedulePersonStoreDevelopmentTeam = false;
	private Boolean schedulePersonStoreDevelopment = false;
	private Boolean schedulePersonBranchStoreSales = false;
	private Boolean schedulePersonBusiness = false;
	private Boolean schedulePersonFranchise = false;
	private Boolean schedulePersonLeader = false;
	private Boolean schedulePersonOther = false;
	private Boolean negotiationTab = false;
	private Boolean taskTab = false;
	private Boolean buildingAndStoreTab = false;
	private Boolean imageAndFileTab = false;

	private String[] ignoreFields = new String[] {};

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	@Override
	protected ModelMapper<ProjectSwitingItemControl> modelMapper(SqlSession session) {
		return session.getMapper(ProjectSwitingItemControlModelMapper.class);
	}

	public Long getProjectCategoryId() {
		return projectCategoryId;
	}

	public void setProjectCategoryId(Long projectCategoryId) {
		this.projectCategoryId = projectCategoryId;
	}

	public Boolean getBasicBuilding() {
		return basicBuilding;
	}

	public void setBasicBuilding(Boolean basicBuilding) {
		this.basicBuilding = basicBuilding;
	}

	public Boolean getBasicChannel() {
		return basicChannel;
	}

	public void setBasicChannel(Boolean basicChannel) {
		this.basicChannel = basicChannel;
	}

	public Boolean getBasicCorporationGroup() {
		return basicCorporationGroup;
	}

	public void setBasicCorporationGroup(Boolean basicCorporationGroup) {
		this.basicCorporationGroup = basicCorporationGroup;
	}

	public Boolean getBasicCorporation() {
		return basicCorporation;
	}

	public void setBasicCorporation(Boolean basicCorporation) {
		this.basicCorporation = basicCorporation;
	}

	public Boolean getBasicShopName() {
		return basicShopName;
	}

	public void setBasicShopName(Boolean basicShopName) {
		this.basicShopName = basicShopName;
	}

	public Boolean getBasicShop() {
		return basicShop;
	}

	public void setBasicShop(Boolean basicShop) {
		this.basicShop = basicShop;
	}

	public Boolean getBasicShopCd() {
		return basicShopCd;
	}

	public void setBasicShopCd(Boolean basicShopCd) {
		this.basicShopCd = basicShopCd;
	}

	public Boolean getBasicTsubo() {
		return basicTsubo;
	}

	public void setBasicTsubo(Boolean basicTsubo) {
		this.basicTsubo = basicTsubo;
	}

	public Boolean getBasicManagementForm() {
		return basicManagementForm;
	}

	public void setBasicManagementForm(Boolean basicManagementForm) {
		this.basicManagementForm = basicManagementForm;
	}

	public Boolean getBasicBrand() {
		return basicBrand;
	}

	public void setBasicBrand(Boolean basicBrand) {
		this.basicBrand = basicBrand;
	}

	public Boolean getBasicArea() {
		return basicArea;
	}

	public void setBasicArea(Boolean basicArea) {
		this.basicArea = basicArea;
	}

	public Boolean getBasicAdoptDifficulty() {
		return basicAdoptDifficulty;
	}

	public void setBasicAdoptDifficulty(Boolean basicAdoptDifficulty) {
		this.basicAdoptDifficulty = basicAdoptDifficulty;
	}

	public Boolean getBasicBlock() {
		return basicBlock;
	}

	public void setBasicBlock(Boolean basicBlock) {
		this.basicBlock = basicBlock;
	}

	public Boolean getBasicPrefectures() {
		return basicPrefectures;
	}

	public void setBasicPrefectures(Boolean basicPrefectures) {
		this.basicPrefectures = basicPrefectures;
	}

	public Boolean getBasicInvestmentDiscussion() {
		return basicInvestmentDiscussion;
	}

	public void setBasicInvestmentDiscussion(Boolean basicInvestmentDiscussion) {
		this.basicInvestmentDiscussion = basicInvestmentDiscussion;
	}

	public Boolean getBasicWorkDivision() {
		return basicWorkDivision;
	}

	public void setBasicWorkDivision(Boolean basicWorkDivision) {
		this.basicWorkDivision = basicWorkDivision;
	}

	public Boolean getBasicSalesAgency() {
		return basicSalesAgency;
	}

	public void setBasicSalesAgency(Boolean basicSalesAgency) {
		this.basicSalesAgency = basicSalesAgency;
	}

	public Boolean getBasicAffiliateShopCorporation() {
		return basicAffiliateShopCorporation;
	}

	public void setBasicAffiliateShopCorporation(Boolean basicAffiliateShopCorporation) {
		this.basicAffiliateShopCorporation = basicAffiliateShopCorporation;
	}

	public Boolean getBasicInHouseProgress() {
		return basicInHouseProgress;
	}

	public void setBasicInHouseProgress(Boolean basicInHouseProgress) {
		this.basicInHouseProgress = basicInHouseProgress;
	}

	public Boolean getBasicNegotiationProgress() {
		return basicNegotiationProgress;
	}

	public void setBasicNegotiationProgress(Boolean basicNegotiationProgress) {
		this.basicNegotiationProgress = basicNegotiationProgress;
	}

	public Boolean getBasicExternalReleaseStartDate() {
		return basicExternalReleaseStartDate;
	}

	public void setBasicExternalReleaseStartDate(Boolean basicExternalReleaseStartDate) {
		this.basicExternalReleaseStartDate = basicExternalReleaseStartDate;
	}

	public Boolean getBasicExternalReleaseConfirm() {
		return basicExternalReleaseConfirm;
	}

	public void setBasicExternalReleaseConfirm(Boolean basicExternalReleaseConfirm) {
		this.basicExternalReleaseConfirm = basicExternalReleaseConfirm;
	}

	public Boolean getBasicActionStatus() {
		return basicActionStatus;
	}

	public void setBasicActionStatus(Boolean basicActionStatus) {
		this.basicActionStatus = basicActionStatus;
	}

	public Boolean getBasicProjectStartDate() {
		return basicProjectStartDate;
	}

	public void setBasicProjectStartDate(Boolean basicProjectStartDate) {
		this.basicProjectStartDate = basicProjectStartDate;
	}

	public Boolean getBasicStop() {
		return basicStop;
	}

	public void setBasicStop(Boolean basicStop) {
		this.basicStop = basicStop;
	}

	public Boolean getBasicStopReason() {
		return basicStopReason;
	}

	public void setBasicStopReason(Boolean basicStopReason) {
		this.basicStopReason = basicStopReason;
	}

	public Boolean getBasicDescription() {
		return basicDescription;
	}

	public void setBasicDescription(Boolean basicDescription) {
		this.basicDescription = basicDescription;
	}

	public Boolean getBasicPlan() {
		return basicPlan;
	}

	public void setBasicPlan(Boolean basicPlan) {
		this.basicPlan = basicPlan;
	}

	public Boolean getBasicPlanImplementationDate() {
		return basicPlanImplementationDate;
	}

	public void setBasicPlanImplementationDate(Boolean basicPlanImplementationDate) {
		this.basicPlanImplementationDate = basicPlanImplementationDate;
	}

	public Boolean getBasicPlanPeriod() {
		return basicPlanPeriod;
	}

	public void setBasicPlanPeriod(Boolean basicPlanPeriod) {
		this.basicPlanPeriod = basicPlanPeriod;
	}

	public Boolean getBasicOtherRequest() {
		return basicOtherRequest;
	}

	public void setBasicOtherRequest(Boolean basicOtherRequest) {
		this.basicOtherRequest = basicOtherRequest;
	}

	public Boolean getBasicOurRequest() {
		return basicOurRequest;
	}

	public void setBasicOurRequest(Boolean basicOurRequest) {
		this.basicOurRequest = basicOurRequest;
	}

	public Boolean getBasicProfitExpectation() {
		return basicProfitExpectation;
	}

	public void setBasicProfitExpectation(Boolean basicProfitExpectation) {
		this.basicProfitExpectation = basicProfitExpectation;
	}

	public Boolean getBasicDirectionalityGroup() {
		return basicDirectionalityGroup;
	}

	public void setBasicDirectionalityGroup(Boolean basicDirectionalityGroup) {
		this.basicDirectionalityGroup = basicDirectionalityGroup;
	}

	public Boolean getBasicDirectionalityLandingDirectionalityName() {
		return basicDirectionalityLandingDirectionalityName;
	}

	public void setBasicDirectionalityLandingDirectionalityName(Boolean basicDirectionalityLandingDirectionalityName) {
		this.basicDirectionalityLandingDirectionalityName = basicDirectionalityLandingDirectionalityName;
	}

	public Boolean getBasicDirectionalityLandingDirectionalityPercent() {
		return basicDirectionalityLandingDirectionalityPercent;
	}

	public void setBasicDirectionalityLandingDirectionalityPercent(
			Boolean basicDirectionalityLandingDirectionalityPercent) {
		this.basicDirectionalityLandingDirectionalityPercent = basicDirectionalityLandingDirectionalityPercent;
	}

	public Boolean getBasicDirectionalityRequestorName() {
		return basicDirectionalityRequestorName;
	}

	public void setBasicDirectionalityRequestorName(Boolean basicDirectionalityRequestorName) {
		this.basicDirectionalityRequestorName = basicDirectionalityRequestorName;
	}

	public Boolean getBasicDirectionalityRequestorStatus() {
		return basicDirectionalityRequestorStatus;
	}

	public void setBasicDirectionalityRequestorStatus(Boolean basicDirectionalityRequestorStatus) {
		this.basicDirectionalityRequestorStatus = basicDirectionalityRequestorStatus;
	}

	public Boolean getBasicDirectionalityLandingDirectionalityMemo() {
		return basicDirectionalityLandingDirectionalityMemo;
	}

	public void setBasicDirectionalityLandingDirectionalityMemo(Boolean basicDirectionalityLandingDirectionalityMemo) {
		this.basicDirectionalityLandingDirectionalityMemo = basicDirectionalityLandingDirectionalityMemo;
	}

	public Boolean getBasicDirectionalityRentIncreaseDecrease() {
		return basicDirectionalityRentIncreaseDecrease;
	}

	public void setBasicDirectionalityRentIncreaseDecrease(Boolean basicDirectionalityRentIncreaseDecrease) {
		this.basicDirectionalityRentIncreaseDecrease = basicDirectionalityRentIncreaseDecrease;
	}

	public Boolean getBasicDirectionalityTenancy() {
		return basicDirectionalityTenancy;
	}

	public void setBasicDirectionalityTenancy(Boolean basicDirectionalityTenancy) {
		this.basicDirectionalityTenancy = basicDirectionalityTenancy;
	}

	public Boolean getBasicDirectionalitySalesPrediction() {
		return basicDirectionalitySalesPrediction;
	}

	public void setBasicDirectionalitySalesPrediction(Boolean basicDirectionalitySalesPrediction) {
		this.basicDirectionalitySalesPrediction = basicDirectionalitySalesPrediction;
	}

	public Boolean getBasicDirectionalityTenancyPeriod() {
		return basicDirectionalityTenancyPeriod;
	}

	public void setBasicDirectionalityTenancyPeriod(Boolean basicDirectionalityTenancyPeriod) {
		this.basicDirectionalityTenancyPeriod = basicDirectionalityTenancyPeriod;
	}

	public Boolean getBasicDirectionalityProfitRate() {
		return basicDirectionalityProfitRate;
	}

	public void setBasicDirectionalityProfitRate(Boolean basicDirectionalityProfitRate) {
		this.basicDirectionalityProfitRate = basicDirectionalityProfitRate;
	}

	public Boolean getBasicDirectionalityImplementationDatetime() {
		return basicDirectionalityImplementationDatetime;
	}

	public void setBasicDirectionalityImplementationDatetime(Boolean basicDirectionalityImplementationDatetime) {
		this.basicDirectionalityImplementationDatetime = basicDirectionalityImplementationDatetime;
	}

	public Boolean getBasicDirectionalityRedecorationLastSalesDate() {
		return basicDirectionalityRedecorationLastSalesDate;
	}

	public void setBasicDirectionalityRedecorationLastSalesDate(Boolean basicDirectionalityRedecorationLastSalesDate) {
		this.basicDirectionalityRedecorationLastSalesDate = basicDirectionalityRedecorationLastSalesDate;
	}

	public Boolean getBasicDirectionalityImplementationPeriod() {
		return basicDirectionalityImplementationPeriod;
	}

	public void setBasicDirectionalityImplementationPeriod(Boolean basicDirectionalityImplementationPeriod) {
		this.basicDirectionalityImplementationPeriod = basicDirectionalityImplementationPeriod;
	}

	public Boolean getBasicDirectionalityManagementForm() {
		return basicDirectionalityManagementForm;
	}

	public void setBasicDirectionalityManagementForm(Boolean basicDirectionalityManagementForm) {
		this.basicDirectionalityManagementForm = basicDirectionalityManagementForm;
	}

	public Boolean getBasicCurrentStoreGroup() {
		return basicCurrentStoreGroup;
	}

	public void setBasicCurrentStoreGroup(Boolean basicCurrentStoreGroup) {
		this.basicCurrentStoreGroup = basicCurrentStoreGroup;
	}

	public Boolean getBasicCurrentStoreSection() {
		return basicCurrentStoreSection;
	}

	public void setBasicCurrentStoreSection(Boolean basicCurrentStoreSection) {
		this.basicCurrentStoreSection = basicCurrentStoreSection;
	}

	public Boolean getBasicCurrentStoreFrontage() {
		return basicCurrentStoreFrontage;
	}

	public void setBasicCurrentStoreFrontage(Boolean basicCurrentStoreFrontage) {
		this.basicCurrentStoreFrontage = basicCurrentStoreFrontage;
	}

	public Boolean getBasicCurrentStoreFloor() {
		return basicCurrentStoreFloor;
	}

	public void setBasicCurrentStoreFloor(Boolean basicCurrentStoreFloor) {
		this.basicCurrentStoreFloor = basicCurrentStoreFloor;
	}

	public Boolean getBasicCurrentStoreContractTsubo() {
		return basicCurrentStoreContractTsubo;
	}

	public void setBasicCurrentStoreContractTsubo(Boolean basicCurrentStoreContractTsubo) {
		this.basicCurrentStoreContractTsubo = basicCurrentStoreContractTsubo;
	}

	public Boolean getBasicCurrentStoreBusinessHours() {
		return basicCurrentStoreBusinessHours;
	}

	public void setBasicCurrentStoreBusinessHours(Boolean basicCurrentStoreBusinessHours) {
		this.basicCurrentStoreBusinessHours = basicCurrentStoreBusinessHours;
	}

	public Boolean getBasicCurrentStoreMemo() {
		return basicCurrentStoreMemo;
	}

	public void setBasicCurrentStoreMemo(Boolean basicCurrentStoreMemo) {
		this.basicCurrentStoreMemo = basicCurrentStoreMemo;
	}

	public Boolean getBasicCurrentStoreBuildingExpectedValue() {
		return basicCurrentStoreBuildingExpectedValue;
	}

	public void setBasicCurrentStoreBuildingExpectedValue(Boolean basicCurrentStoreBuildingExpectedValue) {
		this.basicCurrentStoreBuildingExpectedValue = basicCurrentStoreBuildingExpectedValue;
	}

	public Boolean getBasicNegotiationStoreGroup() {
		return basicNegotiationStoreGroup;
	}

	public void setBasicNegotiationStoreGroup(Boolean basicNegotiationStoreGroup) {
		this.basicNegotiationStoreGroup = basicNegotiationStoreGroup;
	}

	public Boolean getBasicNegotiationStoreSection() {
		return basicNegotiationStoreSection;
	}

	public void setBasicNegotiationStoreSection(Boolean basicNegotiationStoreSection) {
		this.basicNegotiationStoreSection = basicNegotiationStoreSection;
	}

	public Boolean getBasicNegotiationStoreFloor() {
		return basicNegotiationStoreFloor;
	}

	public void setBasicNegotiationStoreFloor(Boolean basicNegotiationStoreFloor) {
		this.basicNegotiationStoreFloor = basicNegotiationStoreFloor;
	}

	public Boolean getBasicNegotiationStoreContractTsubo() {
		return basicNegotiationStoreContractTsubo;
	}

	public void setBasicNegotiationStoreContractTsubo(Boolean basicNegotiationStoreContractTsubo) {
		this.basicNegotiationStoreContractTsubo = basicNegotiationStoreContractTsubo;
	}

	public Boolean getBasicNegotiationStoreContractTsuboIncreaseDecrease() {
		return basicNegotiationStoreContractTsuboIncreaseDecrease;
	}

	public void setBasicNegotiationStoreContractTsuboIncreaseDecrease(
			Boolean basicNegotiationStoreContractTsuboIncreaseDecrease) {
		this.basicNegotiationStoreContractTsuboIncreaseDecrease = basicNegotiationStoreContractTsuboIncreaseDecrease;
	}

	public Boolean getBasicNegotiationStoreBusinessHours() {
		return basicNegotiationStoreBusinessHours;
	}

	public void setBasicNegotiationStoreBusinessHours(Boolean basicNegotiationStoreBusinessHours) {
		this.basicNegotiationStoreBusinessHours = basicNegotiationStoreBusinessHours;
	}

	public Boolean getBasicNegotiationStoreMemo() {
		return basicNegotiationStoreMemo;
	}

	public void setBasicNegotiationStoreMemo(Boolean basicNegotiationStoreMemo) {
		this.basicNegotiationStoreMemo = basicNegotiationStoreMemo;
	}

	public Boolean getBasicNegotiationStoreExpectedValue() {
		return basicNegotiationStoreExpectedValue;
	}

	public void setBasicNegotiationStoreExpectedValue(Boolean basicNegotiationStoreExpectedValue) {
		this.basicNegotiationStoreExpectedValue = basicNegotiationStoreExpectedValue;
	}

	public Boolean getBasicCurrentContractGroup() {
		return basicCurrentContractGroup;
	}

	public void setBasicCurrentContractGroup(Boolean basicCurrentContractGroup) {
		this.basicCurrentContractGroup = basicCurrentContractGroup;
	}

	public Boolean getBasicCurrentContractForm() {
		return basicCurrentContractForm;
	}

	public void setBasicCurrentContractForm(Boolean basicCurrentContractForm) {
		this.basicCurrentContractForm = basicCurrentContractForm;
	}

	public Boolean getBasicCurrentContractName() {
		return basicCurrentContractName;
	}

	public void setBasicCurrentContractName(Boolean basicCurrentContractName) {
		this.basicCurrentContractName = basicCurrentContractName;
	}

	public Boolean getBasicCurrentContractStartDate() {
		return basicCurrentContractStartDate;
	}

	public void setBasicCurrentContractStartDate(Boolean basicCurrentContractStartDate) {
		this.basicCurrentContractStartDate = basicCurrentContractStartDate;
	}

	public Boolean getBasicCurrentContractEndDate() {
		return basicCurrentContractEndDate;
	}

	public void setBasicCurrentContractEndDate(Boolean basicCurrentContractEndDate) {
		this.basicCurrentContractEndDate = basicCurrentContractEndDate;
	}

	public Boolean getBasicCurrentContractYear() {
		return basicCurrentContractYear;
	}

	public void setBasicCurrentContractYear(Boolean basicCurrentContractYear) {
		this.basicCurrentContractYear = basicCurrentContractYear;
	}

	public Boolean getBasicCurrentContractTenancyExpirationPeriod() {
		return basicCurrentContractTenancyExpirationPeriod;
	}

	public void setBasicCurrentContractTenancyExpirationPeriod(Boolean basicCurrentContractTenancyExpirationPeriod) {
		this.basicCurrentContractTenancyExpirationPeriod = basicCurrentContractTenancyExpirationPeriod;
	}

	public Boolean getBasicCurrentContractAutoUpdate() {
		return basicCurrentContractAutoUpdate;
	}

	public void setBasicCurrentContractAutoUpdate(Boolean basicCurrentContractAutoUpdate) {
		this.basicCurrentContractAutoUpdate = basicCurrentContractAutoUpdate;
	}

	public Boolean getBasicCurrentContractEconomicCondition() {
		return basicCurrentContractEconomicCondition;
	}

	public void setBasicCurrentContractEconomicCondition(Boolean basicCurrentContractEconomicCondition) {
		this.basicCurrentContractEconomicCondition = basicCurrentContractEconomicCondition;
	}

	public Boolean getBasicCurrentContractRentReduceStartDate() {
		return basicCurrentContractRentReduceStartDate;
	}

	public void setBasicCurrentContractRentReduceStartDate(Boolean basicCurrentContractRentReduceStartDate) {
		this.basicCurrentContractRentReduceStartDate = basicCurrentContractRentReduceStartDate;
	}

	public Boolean getBasicCurrentContractRentReduceEndDate() {
		return basicCurrentContractRentReduceEndDate;
	}

	public void setBasicCurrentContractRentReduceEndDate(Boolean basicCurrentContractRentReduceEndDate) {
		this.basicCurrentContractRentReduceEndDate = basicCurrentContractRentReduceEndDate;
	}

	public Boolean getBasicCurrentContractRentReduceYear() {
		return basicCurrentContractRentReduceYear;
	}

	public void setBasicCurrentContractRentReduceYear(Boolean basicCurrentContractRentReduceYear) {
		this.basicCurrentContractRentReduceYear = basicCurrentContractRentReduceYear;
	}

	public Boolean getBasicCurrentContractMemo() {
		return basicCurrentContractMemo;
	}

	public void setBasicCurrentContractMemo(Boolean basicCurrentContractMemo) {
		this.basicCurrentContractMemo = basicCurrentContractMemo;
	}

	public Boolean getBasicNegotiationContractGroup() {
		return basicNegotiationContractGroup;
	}

	public void setBasicNegotiationContractGroup(Boolean basicNegotiationContractGroup) {
		this.basicNegotiationContractGroup = basicNegotiationContractGroup;
	}

	public Boolean getBasicNegotiationContractForm() {
		return basicNegotiationContractForm;
	}

	public void setBasicNegotiationContractForm(Boolean basicNegotiationContractForm) {
		this.basicNegotiationContractForm = basicNegotiationContractForm;
	}

	public Boolean getBasicNegotiationContractName() {
		return basicNegotiationContractName;
	}

	public void setBasicNegotiationContractName(Boolean basicNegotiationContractName) {
		this.basicNegotiationContractName = basicNegotiationContractName;
	}

	public Boolean getBasicNegotiationContractStartDate() {
		return basicNegotiationContractStartDate;
	}

	public void setBasicNegotiationContractStartDate(Boolean basicNegotiationContractStartDate) {
		this.basicNegotiationContractStartDate = basicNegotiationContractStartDate;
	}

	public Boolean getBasicNegotiationContractEndDate() {
		return basicNegotiationContractEndDate;
	}

	public void setBasicNegotiationContractEndDate(Boolean basicNegotiationContractEndDate) {
		this.basicNegotiationContractEndDate = basicNegotiationContractEndDate;
	}

	public Boolean getBasicNegotiationContractYear() {
		return basicNegotiationContractYear;
	}

	public void setBasicNegotiationContractYear(Boolean basicNegotiationContractYear) {
		this.basicNegotiationContractYear = basicNegotiationContractYear;
	}

	public Boolean getBasicNegotiationContractTenancyExpirationPeriod() {
		return basicNegotiationContractTenancyExpirationPeriod;
	}

	public void setBasicNegotiationContractTenancyExpirationPeriod(
			Boolean basicNegotiationContractTenancyExpirationPeriod) {
		this.basicNegotiationContractTenancyExpirationPeriod = basicNegotiationContractTenancyExpirationPeriod;
	}

	public Boolean getBasicNegotiationContractAutoUpdate() {
		return basicNegotiationContractAutoUpdate;
	}

	public void setBasicNegotiationContractAutoUpdate(Boolean basicNegotiationContractAutoUpdate) {
		this.basicNegotiationContractAutoUpdate = basicNegotiationContractAutoUpdate;
	}

	public Boolean getBasicNegotiationContractRentReduceStartDate() {
		return basicNegotiationContractRentReduceStartDate;
	}

	public void setBasicNegotiationContractRentReduceStartDate(Boolean basicNegotiationContractRentReduceStartDate) {
		this.basicNegotiationContractRentReduceStartDate = basicNegotiationContractRentReduceStartDate;
	}

	public Boolean getBasicNegotiationContractRentReduceEndDate() {
		return basicNegotiationContractRentReduceEndDate;
	}

	public void setBasicNegotiationContractRentReduceEndDate(Boolean basicNegotiationContractRentReduceEndDate) {
		this.basicNegotiationContractRentReduceEndDate = basicNegotiationContractRentReduceEndDate;
	}

	public Boolean getBasicNegotiationContractRentReduceYear() {
		return basicNegotiationContractRentReduceYear;
	}

	public void setBasicNegotiationContractRentReduceYear(Boolean basicNegotiationContractRentReduceYear) {
		this.basicNegotiationContractRentReduceYear = basicNegotiationContractRentReduceYear;
	}

	public Boolean getBasicNegotiationContractMemo() {
		return basicNegotiationContractMemo;
	}

	public void setBasicNegotiationContractMemo(Boolean basicNegotiationContractMemo) {
		this.basicNegotiationContractMemo = basicNegotiationContractMemo;
	}

	public Boolean getBasicCurrentRelatedCorporationGroup() {
		return basicCurrentRelatedCorporationGroup;
	}

	public void setBasicCurrentRelatedCorporationGroup(Boolean basicCurrentRelatedCorporationGroup) {
		this.basicCurrentRelatedCorporationGroup = basicCurrentRelatedCorporationGroup;
	}

	public Boolean getBasicCurrentRelatedCorporationSalesAgency() {
		return basicCurrentRelatedCorporationSalesAgency;
	}

	public void setBasicCurrentRelatedCorporationSalesAgency(Boolean basicCurrentRelatedCorporationSalesAgency) {
		this.basicCurrentRelatedCorporationSalesAgency = basicCurrentRelatedCorporationSalesAgency;
	}

	public Boolean getBasicCurrentRelatedCorporationSalesAgencyStartDate() {
		return basicCurrentRelatedCorporationSalesAgencyStartDate;
	}

	public void setBasicCurrentRelatedCorporationSalesAgencyStartDate(
			Boolean basicCurrentRelatedCorporationSalesAgencyStartDate) {
		this.basicCurrentRelatedCorporationSalesAgencyStartDate = basicCurrentRelatedCorporationSalesAgencyStartDate;
	}

	public Boolean getBasicCurrentRelatedCorporationSalesAgencyEndDate() {
		return basicCurrentRelatedCorporationSalesAgencyEndDate;
	}

	public void setBasicCurrentRelatedCorporationSalesAgencyEndDate(
			Boolean basicCurrentRelatedCorporationSalesAgencyEndDate) {
		this.basicCurrentRelatedCorporationSalesAgencyEndDate = basicCurrentRelatedCorporationSalesAgencyEndDate;
	}

	public Boolean getBasicCurrentRelatedCorporationSalesAgencyYear() {
		return basicCurrentRelatedCorporationSalesAgencyYear;
	}

	public void setBasicCurrentRelatedCorporationSalesAgencyYear(
			Boolean basicCurrentRelatedCorporationSalesAgencyYear) {
		this.basicCurrentRelatedCorporationSalesAgencyYear = basicCurrentRelatedCorporationSalesAgencyYear;
	}

	public Boolean getBasicCurrentRelatedCorporationAffiliateShop() {
		return basicCurrentRelatedCorporationAffiliateShop;
	}

	public void setBasicCurrentRelatedCorporationAffiliateShop(Boolean basicCurrentRelatedCorporationAffiliateShop) {
		this.basicCurrentRelatedCorporationAffiliateShop = basicCurrentRelatedCorporationAffiliateShop;
	}

	public Boolean getBasicCurrentRelatedCorporationPeriod() {
		return basicCurrentRelatedCorporationPeriod;
	}

	public void setBasicCurrentRelatedCorporationPeriod(Boolean basicCurrentRelatedCorporationPeriod) {
		this.basicCurrentRelatedCorporationPeriod = basicCurrentRelatedCorporationPeriod;
	}

	public Boolean getBasicNegotiationRelatedCorporationGroup() {
		return basicNegotiationRelatedCorporationGroup;
	}

	public void setBasicNegotiationRelatedCorporationGroup(Boolean basicNegotiationRelatedCorporationGroup) {
		this.basicNegotiationRelatedCorporationGroup = basicNegotiationRelatedCorporationGroup;
	}

	public Boolean getBasicNegotiationRelatedCorporationSalesAgency() {
		return basicNegotiationRelatedCorporationSalesAgency;
	}

	public void setBasicNegotiationRelatedCorporationSalesAgency(
			Boolean basicNegotiationRelatedCorporationSalesAgency) {
		this.basicNegotiationRelatedCorporationSalesAgency = basicNegotiationRelatedCorporationSalesAgency;
	}

	public Boolean getBasicNegotiationRelatedCorporationSalesAgencyStartDate() {
		return basicNegotiationRelatedCorporationSalesAgencyStartDate;
	}

	public void setBasicNegotiationRelatedCorporationSalesAgencyStartDate(
			Boolean basicNegotiationRelatedCorporationSalesAgencyStartDate) {
		this.basicNegotiationRelatedCorporationSalesAgencyStartDate = basicNegotiationRelatedCorporationSalesAgencyStartDate;
	}

	public Boolean getBasicNegotiationRelatedCorporationSalesAgencyEndDate() {
		return basicNegotiationRelatedCorporationSalesAgencyEndDate;
	}

	public void setBasicNegotiationRelatedCorporationSalesAgencyEndDate(
			Boolean basicNegotiationRelatedCorporationSalesAgencyEndDate) {
		this.basicNegotiationRelatedCorporationSalesAgencyEndDate = basicNegotiationRelatedCorporationSalesAgencyEndDate;
	}

	public Boolean getBasicNegotiationRelatedCorporationSalesAgencyYear() {
		return basicNegotiationRelatedCorporationSalesAgencyYear;
	}

	public void setBasicNegotiationRelatedCorporationSalesAgencyYear(
			Boolean basicNegotiationRelatedCorporationSalesAgencyYear) {
		this.basicNegotiationRelatedCorporationSalesAgencyYear = basicNegotiationRelatedCorporationSalesAgencyYear;
	}

	public Boolean getBasicNegotiationRelatedCorporationAffiliateShop() {
		return basicNegotiationRelatedCorporationAffiliateShop;
	}

	public void setBasicNegotiationRelatedCorporationAffiliateShop(
			Boolean basicNegotiationRelatedCorporationAffiliateShop) {
		this.basicNegotiationRelatedCorporationAffiliateShop = basicNegotiationRelatedCorporationAffiliateShop;
	}

	public Boolean getBasicNegotiationRelatedCorporationPeriod() {
		return basicNegotiationRelatedCorporationPeriod;
	}

	public void setBasicNegotiationRelatedCorporationPeriod(Boolean basicNegotiationRelatedCorporationPeriod) {
		this.basicNegotiationRelatedCorporationPeriod = basicNegotiationRelatedCorporationPeriod;
	}

	public Boolean getBasicBranchStoreStaffOpinion() {
		return basicBranchStoreStaffOpinion;
	}

	public void setBasicBranchStoreStaffOpinion(Boolean basicBranchStoreStaffOpinion) {
		this.basicBranchStoreStaffOpinion = basicBranchStoreStaffOpinion;
	}

	public Boolean getBasicBranchStoreOpinion() {
		return basicBranchStoreOpinion;
	}

	public void setBasicBranchStoreOpinion(Boolean basicBranchStoreOpinion) {
		this.basicBranchStoreOpinion = basicBranchStoreOpinion;
	}

	public Boolean getBasicBuOpinion() {
		return basicBuOpinion;
	}

	public void setBasicBuOpinion(Boolean basicBuOpinion) {
		this.basicBuOpinion = basicBuOpinion;
	}

	public Boolean getScheduleActionScheduleGroup() {
		return scheduleActionScheduleGroup;
	}

	public void setScheduleActionScheduleGroup(Boolean scheduleActionScheduleGroup) {
		this.scheduleActionScheduleGroup = scheduleActionScheduleGroup;
	}

	public Boolean getScheduleArticleReviewDatetime() {
		return scheduleArticleReviewDatetime;
	}

	public void setScheduleArticleReviewDatetime(Boolean scheduleArticleReviewDatetime) {
		this.scheduleArticleReviewDatetime = scheduleArticleReviewDatetime;
	}

	public Boolean getScheduleArticleReviewResult() {
		return scheduleArticleReviewResult;
	}

	public void setScheduleArticleReviewResult(Boolean scheduleArticleReviewResult) {
		this.scheduleArticleReviewResult = scheduleArticleReviewResult;
	}

	public Boolean getScheduleArticleReviewResultComment() {
		return scheduleArticleReviewResultComment;
	}

	public void setScheduleArticleReviewResultComment(Boolean scheduleArticleReviewResultComment) {
		this.scheduleArticleReviewResultComment = scheduleArticleReviewResultComment;
	}

	public Boolean getScheduleManagementDatetime() {
		return scheduleManagementDatetime;
	}

	public void setScheduleManagementDatetime(Boolean scheduleManagementDatetime) {
		this.scheduleManagementDatetime = scheduleManagementDatetime;
	}

	public Boolean getScheduleManagementResult() {
		return scheduleManagementResult;
	}

	public void setScheduleManagementResult(Boolean scheduleManagementResult) {
		this.scheduleManagementResult = scheduleManagementResult;
	}

	public Boolean getScheduleManagementResultComment() {
		return scheduleManagementResultComment;
	}

	public void setScheduleManagementResultComment(Boolean scheduleManagementResultComment) {
		this.scheduleManagementResultComment = scheduleManagementResultComment;
	}

	public Boolean getScheduleInvestmentProcessDatetime() {
		return scheduleInvestmentProcessDatetime;
	}

	public void setScheduleInvestmentProcessDatetime(Boolean scheduleInvestmentProcessDatetime) {
		this.scheduleInvestmentProcessDatetime = scheduleInvestmentProcessDatetime;
	}

	public Boolean getScheduleInvestmentProcessResult() {
		return scheduleInvestmentProcessResult;
	}

	public void setScheduleInvestmentProcessResult(Boolean scheduleInvestmentProcessResult) {
		this.scheduleInvestmentProcessResult = scheduleInvestmentProcessResult;
	}

	public Boolean getScheduleInvestmentProcessResultComment() {
		return scheduleInvestmentProcessResultComment;
	}

	public void setScheduleInvestmentProcessResultComment(Boolean scheduleInvestmentProcessResultComment) {
		this.scheduleInvestmentProcessResultComment = scheduleInvestmentProcessResultComment;
	}

	public Boolean getSchedulePastMeetingGroup() {
		return schedulePastMeetingGroup;
	}

	public void setSchedulePastMeetingGroup(Boolean schedulePastMeetingGroup) {
		this.schedulePastMeetingGroup = schedulePastMeetingGroup;
	}

	public Boolean getSchedulePersonStoreDevelopmentTeam() {
		return schedulePersonStoreDevelopmentTeam;
	}

	public void setSchedulePersonStoreDevelopmentTeam(Boolean schedulePersonStoreDevelopmentTeam) {
		this.schedulePersonStoreDevelopmentTeam = schedulePersonStoreDevelopmentTeam;
	}

	public Boolean getSchedulePersonStoreDevelopment() {
		return schedulePersonStoreDevelopment;
	}

	public void setSchedulePersonStoreDevelopment(Boolean schedulePersonStoreDevelopment) {
		this.schedulePersonStoreDevelopment = schedulePersonStoreDevelopment;
	}

	public Boolean getSchedulePersonBranchStoreSales() {
		return schedulePersonBranchStoreSales;
	}

	public void setSchedulePersonBranchStoreSales(Boolean schedulePersonBranchStoreSales) {
		this.schedulePersonBranchStoreSales = schedulePersonBranchStoreSales;
	}

	public Boolean getSchedulePersonBusiness() {
		return schedulePersonBusiness;
	}

	public void setSchedulePersonBusiness(Boolean schedulePersonBusiness) {
		this.schedulePersonBusiness = schedulePersonBusiness;
	}

	public Boolean getSchedulePersonFranchise() {
		return schedulePersonFranchise;
	}

	public void setSchedulePersonFranchise(Boolean schedulePersonFranchise) {
		this.schedulePersonFranchise = schedulePersonFranchise;
	}

	public Boolean getSchedulePersonOther() {
		return schedulePersonOther;
	}

	public void setSchedulePersonOther(Boolean schedulePersonOther) {
		this.schedulePersonOther = schedulePersonOther;
	}

	public Boolean getNegotiationTab() {
		return negotiationTab;
	}

	public void setNegotiationTab(Boolean negotiationTab) {
		this.negotiationTab = negotiationTab;
	}

	public Boolean getTaskTab() {
		return taskTab;
	}

	public void setTaskTab(Boolean taskTab) {
		this.taskTab = taskTab;
	}

	public Boolean getBuildingAndStoreTab() {
		return buildingAndStoreTab;
	}

	public void setBuildingAndStoreTab(Boolean buildingAndStoreTab) {
		this.buildingAndStoreTab = buildingAndStoreTab;
	}

	public Boolean getImageAndFileTab() {
		return imageAndFileTab;
	}

	public void setImageAndFileTab(Boolean imageAndFileTab) {
		this.imageAndFileTab = imageAndFileTab;
	}

	public Boolean getBasicNegotiationStoreFrontage() {
		return basicNegotiationStoreFrontage;
	}

	public void setBasicNegotiationStoreFrontage(Boolean basicNegotiationStoreFrontage) {
		this.basicNegotiationStoreFrontage = basicNegotiationStoreFrontage;
	}

	public Boolean getSchedulePersonLeader() {
		return schedulePersonLeader;
	}

	public void setSchedulePersonLeader(Boolean schedulePersonLeader) {
		this.schedulePersonLeader = schedulePersonLeader;
	}

}
