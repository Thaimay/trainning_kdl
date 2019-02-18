package jp.co.world.storedevelopment.dev;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.ProjectCategory;
import jp.co.world.storedevelopment.model.ProjectSwitingItemControl;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectCategoryRepository;

public class InitProjectSwitingItemControlData extends InitTestDataSupport {

	@Override
	public void init(InitTestData main) {
		MAIN_ACCOUNT = main.MAIN_ACCOUNT;
		createProjectSwitingItemControl();
	}

	public Account getAccount() {
		return MAIN_ACCOUNT;
	}

	public ProjectSwitingItemControl createProjectSwitingItemControl() {
		ProjectCategory projectCategory = new ProjectCategoryRepository().getHead().get();
		ProjectSwitingItemControl projectSwitingItemControl = new ProjectSwitingItemControl();

		projectSwitingItemControl.setProjectCategoryId(projectCategory.getId());
		projectSwitingItemControl.setBasicBuilding(true);
		projectSwitingItemControl.setBasicChannel(true);
		projectSwitingItemControl.setBasicCorporationGroup(true);
		projectSwitingItemControl.setBasicCorporation(true);
		projectSwitingItemControl.setBasicShopName(true);
		projectSwitingItemControl.setBasicShop(true);
		projectSwitingItemControl.setBasicShopCd(true);
		projectSwitingItemControl.setBasicTsubo(true);
		projectSwitingItemControl.setBasicManagementForm(true);
		projectSwitingItemControl.setBasicBrand(true);
		projectSwitingItemControl.setBasicArea(true);
		projectSwitingItemControl.setBasicAdoptDifficulty(true);
		projectSwitingItemControl.setBasicBlock(true);
		projectSwitingItemControl.setBasicPrefectures(true);
		projectSwitingItemControl.setBasicInvestmentDiscussion(true);
		projectSwitingItemControl.setBasicWorkDivision(true);
		projectSwitingItemControl.setBasicSalesAgency(true);
		projectSwitingItemControl.setBasicAffiliateShopCorporation(true);
		projectSwitingItemControl.setBasicInHouseProgress(true);
		projectSwitingItemControl.setBasicNegotiationProgress(true);
		projectSwitingItemControl.setBasicExternalReleaseStartDate(true);
		projectSwitingItemControl.setBasicExternalReleaseConfirm(true);
		projectSwitingItemControl.setBasicActionStatus(true);
		projectSwitingItemControl.setBasicProjectStartDate(true);
		projectSwitingItemControl.setBasicStop(true);
		projectSwitingItemControl.setBasicStopReason(true);
		projectSwitingItemControl.setBasicDescription(true);
		projectSwitingItemControl.setBasicPlan(true);
		projectSwitingItemControl.setBasicPlanImplementationDate(true);
		projectSwitingItemControl.setBasicPlanPeriod(true);
		projectSwitingItemControl.setBasicOtherRequest(true);
		projectSwitingItemControl.setBasicOurRequest(true);
		projectSwitingItemControl.setBasicDirectionalityGroup(true);
		projectSwitingItemControl.setBasicDirectionalityLandingDirectionalityName(true);
		projectSwitingItemControl.setBasicDirectionalityLandingDirectionalityPercent(true);
		projectSwitingItemControl.setBasicDirectionalityRequestorName(true);
		projectSwitingItemControl.setBasicDirectionalityRequestorStatus(true);
		projectSwitingItemControl.setBasicDirectionalityLandingDirectionalityMemo(true);
		projectSwitingItemControl.setBasicDirectionalityRentIncreaseDecrease(true);
		projectSwitingItemControl.setBasicDirectionalityTenancy(true);
		projectSwitingItemControl.setBasicDirectionalitySalesPrediction(true);
		projectSwitingItemControl.setBasicDirectionalityTenancyPeriod(true);
		projectSwitingItemControl.setBasicDirectionalityProfitRate(true);
		projectSwitingItemControl.setBasicDirectionalityImplementationDatetime(true);
		projectSwitingItemControl.setBasicDirectionalityRedecorationLastSalesDate(true);
		projectSwitingItemControl.setBasicDirectionalityImplementationPeriod(true);
		projectSwitingItemControl.setBasicDirectionalityManagementForm(true);
		projectSwitingItemControl.setBasicCurrentStoreGroup(true);
		projectSwitingItemControl.setBasicCurrentStoreSection(true);
		projectSwitingItemControl.setBasicCurrentStoreFrontage(true);
		projectSwitingItemControl.setBasicCurrentStoreFloor(true);
		projectSwitingItemControl.setBasicCurrentStoreContractTsubo(true);
		projectSwitingItemControl.setBasicCurrentStoreBusinessHours(true);
		projectSwitingItemControl.setBasicCurrentStoreMemo(true);
		projectSwitingItemControl.setBasicCurrentStoreBuildingExpectedValue(true);
		projectSwitingItemControl.setBasicNegotiationStoreGroup(true);
		projectSwitingItemControl.setBasicNegotiationStoreSection(true);
		projectSwitingItemControl.setBasicNegotiationStoreFloor(true);
		projectSwitingItemControl.setBasicNegotiationStoreContractTsubo(true);
		projectSwitingItemControl.setBasicNegotiationStoreContractTsuboIncreaseDecrease(true);
		projectSwitingItemControl.setBasicNegotiationStoreBusinessHours(true);
		projectSwitingItemControl.setBasicNegotiationStoreMemo(true);
		projectSwitingItemControl.setBasicNegotiationStoreExpectedValue(true);
		projectSwitingItemControl.setBasicCurrentContractGroup(true);
		projectSwitingItemControl.setBasicCurrentContractForm(true);
		projectSwitingItemControl.setBasicCurrentContractName(true);
		projectSwitingItemControl.setBasicCurrentContractStartDate(true);
		projectSwitingItemControl.setBasicCurrentContractEndDate(true);
		projectSwitingItemControl.setBasicCurrentContractYear(true);
		projectSwitingItemControl.setBasicCurrentContractTenancyExpirationPeriod(true);
		projectSwitingItemControl.setBasicCurrentContractAutoUpdate(true);
		projectSwitingItemControl.setBasicCurrentContractEconomicCondition(true);
		projectSwitingItemControl.setBasicCurrentContractRentReduceStartDate(true);
		projectSwitingItemControl.setBasicCurrentContractRentReduceEndDate(true);
		projectSwitingItemControl.setBasicCurrentContractMemo(true);
		projectSwitingItemControl.setBasicNegotiationContractGroup(true);
		projectSwitingItemControl.setBasicNegotiationContractForm(true);
		projectSwitingItemControl.setBasicNegotiationContractName(true);
		projectSwitingItemControl.setBasicNegotiationContractStartDate(true);
		projectSwitingItemControl.setBasicNegotiationContractEndDate(true);
		projectSwitingItemControl.setBasicNegotiationContractYear(true);
		projectSwitingItemControl.setBasicNegotiationContractTenancyExpirationPeriod(true);
		projectSwitingItemControl.setBasicNegotiationContractAutoUpdate(true);
		projectSwitingItemControl.setBasicNegotiationContractMemo(true);
		projectSwitingItemControl.setBasicCurrentRelatedCorporationGroup(true);
		projectSwitingItemControl.setBasicCurrentRelatedCorporationSalesAgency(true);
		projectSwitingItemControl.setBasicCurrentRelatedCorporationSalesAgencyStartDate(true);
		projectSwitingItemControl.setBasicCurrentRelatedCorporationSalesAgencyEndDate(true);
		projectSwitingItemControl.setBasicCurrentRelatedCorporationSalesAgencyYear(true);
		projectSwitingItemControl.setBasicCurrentRelatedCorporationAffiliateShop(true);
		projectSwitingItemControl.setBasicNegotiationRelatedCorporationGroup(true);
		projectSwitingItemControl.setBasicNegotiationRelatedCorporationSalesAgency(true);
		projectSwitingItemControl.setBasicNegotiationRelatedCorporationSalesAgencyStartDate(true);
		projectSwitingItemControl.setBasicNegotiationRelatedCorporationSalesAgencyEndDate(true);
		projectSwitingItemControl.setBasicNegotiationRelatedCorporationSalesAgencyYear(true);
		projectSwitingItemControl.setBasicNegotiationRelatedCorporationAffiliateShop(true);
		projectSwitingItemControl.setBasicBranchStoreStaffOpinion(true);
		projectSwitingItemControl.setBasicBranchStoreOpinion(true);
		projectSwitingItemControl.setBasicBuOpinion(true);
		projectSwitingItemControl.setScheduleActionScheduleGroup(true);
		projectSwitingItemControl.setScheduleArticleReviewDatetime(true);
		projectSwitingItemControl.setScheduleArticleReviewResult(true);
		projectSwitingItemControl.setScheduleArticleReviewResultComment(true);
		projectSwitingItemControl.setScheduleManagementDatetime(true);
		projectSwitingItemControl.setScheduleManagementResult(true);
		projectSwitingItemControl.setScheduleManagementResultComment(true);
		projectSwitingItemControl.setScheduleInvestmentProcessDatetime(true);
		projectSwitingItemControl.setScheduleInvestmentProcessResult(true);
		projectSwitingItemControl.setScheduleInvestmentProcessResultComment(true);
		projectSwitingItemControl.setSchedulePastMeetingGroup(true);
		projectSwitingItemControl.setSchedulePersonStoreDevelopmentTeam(true);
		projectSwitingItemControl.setSchedulePersonStoreDevelopment(true);
		projectSwitingItemControl.setSchedulePersonBranchStoreSales(true);
		projectSwitingItemControl.setSchedulePersonBusiness(true);
		projectSwitingItemControl.setSchedulePersonFranchise(true);
		projectSwitingItemControl.setSchedulePersonOther(true);
		projectSwitingItemControl.setNegotiationTab(true);
		projectSwitingItemControl.setTaskTab(true);
		projectSwitingItemControl.setBuildingAndStoreTab(true);
		projectSwitingItemControl.setImageAndFileTab(true);
		projectSwitingItemControl.setCreateAccount(MAIN_ACCOUNT);
		return projectSwitingItemControl.create();
	}

}
