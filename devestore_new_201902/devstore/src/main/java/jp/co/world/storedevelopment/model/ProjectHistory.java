package jp.co.world.storedevelopment.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ProjectHistoryModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectScheduleRepository;

/**
 * @author hungdh
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class ProjectHistory extends ActiveModel<ProjectHistory> {

	private String title;
	private Long projectCategoryId;
	private String division;
	private Boolean open;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime openingDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate sectionSuggestDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate buildingMettingDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate consensusDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate contractDate;
	private Long officeProjectProgressId;
	private Long negotiationProjectProgressId;
	private String description;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	private Long buildingId;
	private Long iShopId;
	private String shopName;
	private String shopCd;
	private Long corporationId;
	private Long corporationGpId;
	private Long brandId;
	private Long iAreaId;
	private Long iBlockId;
	private Long iPrefecturesId;
	private Long currentISalesAgencyTargetId;
	private Long progressISalesAgencyTargetId;
	private String actionStatus;
	private String inHouseProgress;
	private String negotiationProgress;
	private String contractProgress;
	private Long mProjectActionStatusId;
	private Long salesPrediction;
	private Boolean investmentDiscussionTarget;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate externalReleaseStartDate;
	private Boolean externalReleaseConfirm;
	private Boolean stop;
	private Long stopReasonId;
	private Long landingProjectCategoryId;
	private Long conclusionPossibilityPercentId;
	private String landingMemo;
	private Long planStatusId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate implementationDatetime;
	private String operationDivision;
	private String implementationPeriod;
	private Long iSalesChannelId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate salesEndDate;
	private String rentIncreaseDecrease;
	private Boolean tenancy;
	private BigDecimal salesExpectation;
	private BigDecimal profitRate;
	private String tenancyPeriod;
	private String otherRequest;
	private String ourRequest;
	private String requestorName;
	private String requestorStatus;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate articleReviewDate;
	private Long articleReviewResultId;
	private String articleReviewResultComment;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate managementDate;
	private Long managementResultId;
	private String managementResultComment;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate investmentProcessDate;
	private Long investmentProcessResultId;
	private String investmentProcessResultComment;
	private String adoptDifficulty;
	private Long projectId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate implementationScheduleDatetime;
	private String shopNameTemporary;
	private Float profitExpectation;
	private String[] ignoreFields = new String[] {};

	private Boolean isChangedOperationDivision;
	private Boolean isChangedMProjectActionStatusId;
	private Boolean isChangedOfficeProjectProgressId;
	private Boolean isChangedNegotiationProjectProgressId;
	private Boolean isChangedLandingProjectCategoryId;
	private Boolean isChangedConclusionPossibilityPercentId;
	private Boolean isChangedLandingMemo;
	private Boolean isChangedRentIncreaseDecrease;
	private Boolean isChangedExternalReleaseStartDate;
	private Boolean isChangedExternalReleaseConfirm;
	private Boolean isChangedImplementationDatetime;
	private Boolean isChangedSalesEndDate;
	private Boolean isChangedImplementationPeriod;
	private Boolean isChangedStopReasonId;
	private Boolean isChangedNegotiationSectionFloor;
	private Boolean isChangedNegotiationSectionContractTsubo;
	private Boolean isChangedNegotiationSectionTsuboIncreaseDecrease;
	private Boolean isChangedNegotiationSectionBusinessHours;
	private Boolean isChangedNegotiationSectionMemo;
	private Boolean isChangedNegotiationContractForm;
	private Boolean isChangedNegotiationContractName;
	private Boolean isChangedNegotiationContractStartDate;
	private Boolean isChangedNegotiationContractEndDate;
	private Boolean isChangedNegotiationContractYear;
	private Boolean isChangedNegotiationContractTenancyPeriod;
	private Boolean isChangedNegotiationContractAutoUpdate;
	private Boolean isChangedNegotiationContractMemo;
	private Boolean isChangedNegotiationRelatedAgency;
	private Boolean isChangedNegotiationRelatedAgencyStartDate;
	private Boolean isChangedNegotiationRelatedAgencyEndDate;
	private Boolean isChangedNegotiationRelatedAgencyYear;
	private Boolean isChangedNegotiationRelatedAffiliateShop;
	private Boolean isChangedBranchStoreStaffOpinion;
	private Boolean isChangedBranchStoreOpinion;
	private Boolean isChangedBuOpinion;
	private LocalDate planDate;
	private Integer planPeriod;
	private String planPeriodHalf;
	private String implementationDateValue;
	private Boolean isChangedImplementationScheduleDatetime;
	
	private static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	public void setIgnoreFields(String[] ignoreFields) {
		this.ignoreFields = ignoreFields;
	}

	public ProjectHistory() {

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getProjectCategoryId() {
		return projectCategoryId;
	}

	public void setProjectCategoryId(Long projectCategoryId) {
		this.projectCategoryId = projectCategoryId;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public LocalDateTime getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(LocalDateTime openingDate) {
		this.openingDate = openingDate;
	}

	public LocalDate getSectionSuggestDate() {
		return sectionSuggestDate;
	}

	public void setSectionSuggestDate(LocalDate sectionSuggestDate) {
		this.sectionSuggestDate = sectionSuggestDate;
	}

	public LocalDate getBuildingMettingDate() {
		return buildingMettingDate;
	}

	public void setBuildingMettingDate(LocalDate buildingMettingDate) {
		this.buildingMettingDate = buildingMettingDate;
	}

	public LocalDate getConsensusDate() {
		return consensusDate;
	}

	public void setConsensusDate(LocalDate consensusDate) {
		this.consensusDate = consensusDate;
	}

	public LocalDate getContractDate() {
		return contractDate;
	}

	public void setContractDate(LocalDate contractDate) {
		this.contractDate = contractDate;
	}

	public Long getOfficeProjectProgressId() {
		return officeProjectProgressId;
	}

	public void setOfficeProjectProgressId(Long officeProjectProgressId) {
		this.officeProjectProgressId = officeProjectProgressId;
	}

	public Long getNegotiationProjectProgressId() {
		return negotiationProjectProgressId;
	}

	public void setNegotiationProjectProgressId(Long negotiationProjectProgressId) {
		this.negotiationProjectProgressId = negotiationProjectProgressId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	public Long getiShopId() {
		return iShopId;
	}

	public void setiShopId(Long iShopId) {
		this.iShopId = iShopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopCd() {
		return shopCd;
	}

	public void setShopCd(String shopCd) {
		this.shopCd = shopCd;
	}

	public Long getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(Long corporationId) {
		this.corporationId = corporationId;
	}

	public Long getCorporationGpId() {
		return corporationGpId;
	}

	public void setCorporationGpId(Long corporationGpId) {
		this.corporationGpId = corporationGpId;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public Long getiAreaId() {
		return iAreaId;
	}

	public void setiAreaId(Long iAreaId) {
		this.iAreaId = iAreaId;
	}

	public Long getiBlockId() {
		return iBlockId;
	}

	public void setiBlockId(Long iBlockId) {
		this.iBlockId = iBlockId;
	}

	public Long getiPrefecturesId() {
		return iPrefecturesId;
	}

	public void setiPrefecturesId(Long iPrefecturesId) {
		this.iPrefecturesId = iPrefecturesId;
	}

	public Long getCurrentISalesAgencyTargetId() {
		return currentISalesAgencyTargetId;
	}

	public void setCurrentISalesAgencyTargetId(Long currentISalesAgencyTargetId) {
		this.currentISalesAgencyTargetId = currentISalesAgencyTargetId;
	}

	public Long getProgressISalesAgencyTargetId() {
		return progressISalesAgencyTargetId;
	}

	public void setProgressISalesAgencyTargetId(Long progressISalesAgencyTargetId) {
		this.progressISalesAgencyTargetId = progressISalesAgencyTargetId;
	}

	public String getActionStatus() {
		return actionStatus;
	}

	public void setActionStatus(String actionStatus) {
		this.actionStatus = actionStatus;
	}

	public String getInHouseProgress() {
		return inHouseProgress;
	}

	public void setInHouseProgress(String inHouseProgress) {
		this.inHouseProgress = inHouseProgress;
	}

	public String getNegotiationProgress() {
		return negotiationProgress;
	}

	public void setNegotiationProgress(String negotiationProgress) {
		this.negotiationProgress = negotiationProgress;
	}

	public String getContractProgress() {
		return contractProgress;
	}

	public void setContractProgress(String contractProgress) {
		this.contractProgress = contractProgress;
	}

	public Long getmProjectActionStatusId() {
		return mProjectActionStatusId;
	}

	public void setmProjectActionStatusId(Long mProjectActionStatusId) {
		this.mProjectActionStatusId = mProjectActionStatusId;
	}

	public Long getSalesPrediction() {
		return salesPrediction;
	}

	public void setSalesPrediction(Long salesPrediction) {
		this.salesPrediction = salesPrediction;
	}

	public Boolean getInvestmentDiscussionTarget() {
		return investmentDiscussionTarget;
	}

	public void setInvestmentDiscussionTarget(Boolean investmentDiscussionTarget) {
		this.investmentDiscussionTarget = investmentDiscussionTarget;
	}

	public LocalDate getExternalReleaseStartDate() {
		return externalReleaseStartDate;
	}

	public void setExternalReleaseStartDate(LocalDate externalReleaseStartDate) {
		this.externalReleaseStartDate = externalReleaseStartDate;
	}

	public Boolean getExternalReleaseConfirm() {
		return externalReleaseConfirm;
	}

	public void setExternalReleaseConfirm(Boolean externalReleaseConfirm) {
		this.externalReleaseConfirm = externalReleaseConfirm;
	}

	public Boolean getStop() {
		return stop;
	}

	public void setStop(Boolean stop) {
		this.stop = stop;
	}

	public Long getStopReasonId() {
		return stopReasonId;
	}

	public void setStopReasonId(Long stopReasonId) {
		this.stopReasonId = stopReasonId;
	}

	public Long getLandingProjectCategoryId() {
		return landingProjectCategoryId;
	}

	public void setLandingProjectCategoryId(Long landingProjectCategoryId) {
		this.landingProjectCategoryId = landingProjectCategoryId;
	}

	public Long getConclusionPossibilityPercentId() {
		return conclusionPossibilityPercentId;
	}

	public void setConclusionPossibilityPercentId(Long conclusionPossibilityPercentId) {
		this.conclusionPossibilityPercentId = conclusionPossibilityPercentId;
	}

	public String getLandingMemo() {
		return landingMemo;
	}

	public void setLandingMemo(String landingMemo) {
		this.landingMemo = landingMemo;
	}

	public Long getPlanStatusId() {
		return planStatusId;
	}

	public void setPlanStatusId(Long planStatusId) {
		this.planStatusId = planStatusId;
	}

	public LocalDate getImplementationDatetime() {
		return implementationDatetime;
	}

	public void setImplementationDatetime(LocalDate implementationDatetime) {
		this.implementationDatetime = implementationDatetime;
	}

	public String getOperationDivision() {
		return operationDivision;
	}

	public void setOperationDivision(String operationDivision) {
		this.operationDivision = operationDivision;
	}

	public String getImplementationPeriod() {
		return implementationPeriod;
	}

	public void setImplementationPeriod(String implementationPeriod) {
		this.implementationPeriod = implementationPeriod;
	}

	public Long getiSalesChannelId() {
		return iSalesChannelId;
	}

	public void setiSalesChannelId(Long iSalesChannelId) {
		this.iSalesChannelId = iSalesChannelId;
	}

	public LocalDate getSalesEndDate() {
		return salesEndDate;
	}

	public void setSalesEndDate(LocalDate salesEndDate) {
		this.salesEndDate = salesEndDate;
	}

	public String getRentIncreaseDecrease() {
		return rentIncreaseDecrease;
	}

	public void setRentIncreaseDecrease(String rentIncreaseDecrease) {
		this.rentIncreaseDecrease = rentIncreaseDecrease;
	}

	public Boolean getTenancy() {
		return tenancy;
	}

	public void setTenancy(Boolean tenancy) {
		this.tenancy = tenancy;
	}

	public BigDecimal getSalesExpectation() {
		return salesExpectation;
	}

	public void setSalesExpectation(BigDecimal salesExpectation) {
		this.salesExpectation = salesExpectation;
	}

	public BigDecimal getProfitRate() {
		return profitRate;
	}

	public void setProfitRate(BigDecimal profitRate) {
		this.profitRate = profitRate;
	}

	public String getTenancyPeriod() {
		return tenancyPeriod;
	}

	public void setTenancyPeriod(String tenancyPeriod) {
		this.tenancyPeriod = tenancyPeriod;
	}

	public String getOtherRequest() {
		return otherRequest;
	}

	public void setOtherRequest(String otherRequest) {
		this.otherRequest = otherRequest;
	}

	public String getOurRequest() {
		return ourRequest;
	}

	public void setOurRequest(String ourRequest) {
		this.ourRequest = ourRequest;
	}

	public String getRequestorName() {
		return requestorName;
	}

	public void setRequestorName(String requestorName) {
		this.requestorName = requestorName;
	}

	public String getRequestorStatus() {
		return requestorStatus;
	}

	public void setRequestorStatus(String requestorStatus) {
		this.requestorStatus = requestorStatus;
	}

	public LocalDate getArticleReviewDate() {
		return articleReviewDate;
	}

	public void setArticleReviewDate(LocalDate articleReviewDate) {
		this.articleReviewDate = articleReviewDate;
	}

	public Long getArticleReviewResultId() {
		return articleReviewResultId;
	}

	public void setArticleReviewResultId(Long articleReviewResultId) {
		this.articleReviewResultId = articleReviewResultId;
	}

	public String getArticleReviewResultComment() {
		return articleReviewResultComment;
	}

	public void setArticleReviewResultComment(String articleReviewResultComment) {
		this.articleReviewResultComment = articleReviewResultComment;
	}

	public LocalDate getManagementDate() {
		return managementDate;
	}

	public void setManagementDate(LocalDate managementDate) {
		this.managementDate = managementDate;
	}

	public Long getManagementResultId() {
		return managementResultId;
	}

	public void setManagementResultId(Long managementResultId) {
		this.managementResultId = managementResultId;
	}

	public String getManagementResultComment() {
		return managementResultComment;
	}

	public void setManagementResultComment(String managementResultComment) {
		this.managementResultComment = managementResultComment;
	}

	public LocalDate getInvestmentProcessDate() {
		return investmentProcessDate;
	}

	public void setInvestmentProcessDate(LocalDate investmentProcessDate) {
		this.investmentProcessDate = investmentProcessDate;
	}

	public Long getInvestmentProcessResultId() {
		return investmentProcessResultId;
	}

	public void setInvestmentProcessResultId(Long investmentProcessResultId) {
		this.investmentProcessResultId = investmentProcessResultId;
	}

	public String getInvestmentProcessResultComment() {
		return investmentProcessResultComment;
	}

	public void setInvestmentProcessResultComment(String investmentProcessResultComment) {
		this.investmentProcessResultComment = investmentProcessResultComment;
	}

	public String getAdoptDifficulty() {
		return adoptDifficulty;
	}

	public void setAdoptDifficulty(String adoptDifficulty) {
		this.adoptDifficulty = adoptDifficulty;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public LocalDate getImplementationScheduleDatetime() {
		return implementationScheduleDatetime;
	}

	public void setImplementationScheduleDatetime(LocalDate implementationScheduleDatetime) {
		this.implementationScheduleDatetime = implementationScheduleDatetime;
	}

	public String getShopNameTemporary() {
		return shopNameTemporary;
	}

	public void setShopNameTemporary(String shopNameTemporary) {
		this.shopNameTemporary = shopNameTemporary;
	}

	public Float getProfitExpectation() {
		return profitExpectation;
	}

	public void setProfitExpectation(Float profitExpectation) {
		this.profitExpectation = profitExpectation;
	}

	public Boolean getIsChangedOperationDivision() {
		return isChangedOperationDivision;
	}

	public void setIsChangedOperationDivision(Boolean isChangedOperationDivision) {
		this.isChangedOperationDivision = isChangedOperationDivision;
	}

	public Boolean getIsChangedMProjectActionStatusId() {
		return isChangedMProjectActionStatusId;
	}

	public void setIsChangedMProjectActionStatusId(Boolean isChangedMProjectActionStatusId) {
		this.isChangedMProjectActionStatusId = isChangedMProjectActionStatusId;
	}

	public Boolean getIsChangedOfficeProjectProgressId() {
		return isChangedOfficeProjectProgressId;
	}

	public void setIsChangedOfficeProjectProgressId(Boolean isChangedOfficeProjectProgressId) {
		this.isChangedOfficeProjectProgressId = isChangedOfficeProjectProgressId;
	}

	public Boolean getIsChangedNegotiationProjectProgressId() {
		return isChangedNegotiationProjectProgressId;
	}

	public void setIsChangedNegotiationProjectProgressId(Boolean isChangedNegotiationProjectProgressId) {
		this.isChangedNegotiationProjectProgressId = isChangedNegotiationProjectProgressId;
	}

	public Boolean getIsChangedLandingProjectCategoryId() {
		return isChangedLandingProjectCategoryId;
	}

	public void setIsChangedLandingProjectCategoryId(Boolean isChangedLandingProjectCategoryId) {
		this.isChangedLandingProjectCategoryId = isChangedLandingProjectCategoryId;
	}

	public Boolean getIsChangedConclusionPossibilityPercentId() {
		return isChangedConclusionPossibilityPercentId;
	}

	public void setIsChangedConclusionPossibilityPercentId(Boolean isChangedConclusionPossibilityPercentId) {
		this.isChangedConclusionPossibilityPercentId = isChangedConclusionPossibilityPercentId;
	}

	public Boolean getIsChangedLandingMemo() {
		return isChangedLandingMemo;
	}

	public void setIsChangedLandingMemo(Boolean isChangedLandingMemo) {
		this.isChangedLandingMemo = isChangedLandingMemo;
	}

	public Boolean getIsChangedRentIncreaseDecrease() {
		return isChangedRentIncreaseDecrease;
	}

	public void setIsChangedRentIncreaseDecrease(Boolean isChangedRentIncreaseDecrease) {
		this.isChangedRentIncreaseDecrease = isChangedRentIncreaseDecrease;
	}

	public Boolean getIsChangedExternalReleaseStartDate() {
		return isChangedExternalReleaseStartDate;
	}

	public void setIsChangedExternalReleaseStartDate(Boolean isChangedExternalReleaseStartDate) {
		this.isChangedExternalReleaseStartDate = isChangedExternalReleaseStartDate;
	}

	public Boolean getIsChangedExternalReleaseConfirm() {
		return isChangedExternalReleaseConfirm;
	}

	public void setIsChangedExternalReleaseConfirm(Boolean isChangedExternalReleaseConfirm) {
		this.isChangedExternalReleaseConfirm = isChangedExternalReleaseConfirm;
	}

	public Boolean getIsChangedImplementationDatetime() {
		return isChangedImplementationDatetime;
	}

	public void setIsChangedImplementationDatetime(Boolean isChangedImplementationDatetime) {
		this.isChangedImplementationDatetime = isChangedImplementationDatetime;
	}

	public Boolean getIsChangedSalesEndDate() {
		return isChangedSalesEndDate;
	}

	public void setIsChangedSalesEndDate(Boolean isChangedSalesEndDate) {
		this.isChangedSalesEndDate = isChangedSalesEndDate;
	}

	public Boolean getIsChangedImplementationPeriod() {
		return isChangedImplementationPeriod;
	}

	public void setIsChangedImplementationPeriod(Boolean isChangedImplementationPeriod) {
		this.isChangedImplementationPeriod = isChangedImplementationPeriod;
	}

	public Boolean getIsChangedStopReasonId() {
		return isChangedStopReasonId;
	}

	public void setIsChangedStopReasonId(Boolean isChangedStopReasonId) {
		this.isChangedStopReasonId = isChangedStopReasonId;
	}

	public Boolean getIsChangedNegotiationSectionFloor() {
		return isChangedNegotiationSectionFloor;
	}

	public void setIsChangedNegotiationSectionFloor(Boolean isChangedNegotiationSectionFloor) {
		this.isChangedNegotiationSectionFloor = isChangedNegotiationSectionFloor;
	}

	public Boolean getIsChangedNegotiationSectionContractTsubo() {
		return isChangedNegotiationSectionContractTsubo;
	}

	public void setIsChangedNegotiationSectionContractTsubo(Boolean isChangedNegotiationSectionContractTsubo) {
		this.isChangedNegotiationSectionContractTsubo = isChangedNegotiationSectionContractTsubo;
	}

	public Boolean getIsChangedNegotiationSectionTsuboIncreaseDecrease() {
		return isChangedNegotiationSectionTsuboIncreaseDecrease;
	}

	public void setIsChangedNegotiationSectionTsuboIncreaseDecrease(
			Boolean isChangedNegotiationSectionTsuboIncreaseDecrease) {
		this.isChangedNegotiationSectionTsuboIncreaseDecrease = isChangedNegotiationSectionTsuboIncreaseDecrease;
	}

	public Boolean getIsChangedNegotiationSectionBusinessHours() {
		return isChangedNegotiationSectionBusinessHours;
	}

	public void setIsChangedNegotiationSectionBusinessHours(Boolean isChangedNegotiationSectionBusinessHours) {
		this.isChangedNegotiationSectionBusinessHours = isChangedNegotiationSectionBusinessHours;
	}

	public Boolean getIsChangedNegotiationSectionMemo() {
		return isChangedNegotiationSectionMemo;
	}

	public void setIsChangedNegotiationSectionMemo(Boolean isChangedNegotiationSectionMemo) {
		this.isChangedNegotiationSectionMemo = isChangedNegotiationSectionMemo;
	}

	public Boolean getIsChangedNegotiationContractForm() {
		return isChangedNegotiationContractForm;
	}

	public void setIsChangedNegotiationContractForm(Boolean isChangedNegotiationContractForm) {
		this.isChangedNegotiationContractForm = isChangedNegotiationContractForm;
	}

	public Boolean getIsChangedNegotiationContractName() {
		return isChangedNegotiationContractName;
	}

	public void setIsChangedNegotiationContractName(Boolean isChangedNegotiationContractName) {
		this.isChangedNegotiationContractName = isChangedNegotiationContractName;
	}

	public Boolean getIsChangedNegotiationContractStartDate() {
		return isChangedNegotiationContractStartDate;
	}

	public void setIsChangedNegotiationContractStartDate(Boolean isChangedNegotiationContractStartDate) {
		this.isChangedNegotiationContractStartDate = isChangedNegotiationContractStartDate;
	}

	public Boolean getIsChangedNegotiationContractEndDate() {
		return isChangedNegotiationContractEndDate;
	}

	public void setIsChangedNegotiationContractEndDate(Boolean isChangedNegotiationContractEndDate) {
		this.isChangedNegotiationContractEndDate = isChangedNegotiationContractEndDate;
	}

	public Boolean getIsChangedNegotiationContractYear() {
		return isChangedNegotiationContractYear;
	}

	public void setIsChangedNegotiationContractYear(Boolean isChangedNegotiationContractYear) {
		this.isChangedNegotiationContractYear = isChangedNegotiationContractYear;
	}

	public Boolean getIsChangedNegotiationContractTenancyPeriod() {
		return isChangedNegotiationContractTenancyPeriod;
	}

	public void setIsChangedNegotiationContractTenancyPeriod(Boolean isChangedNegotiationContractTenancyPeriod) {
		this.isChangedNegotiationContractTenancyPeriod = isChangedNegotiationContractTenancyPeriod;
	}

	public Boolean getIsChangedNegotiationContractAutoUpdate() {
		return isChangedNegotiationContractAutoUpdate;
	}

	public void setIsChangedNegotiationContractAutoUpdate(Boolean isChangedNegotiationContractAutoUpdate) {
		this.isChangedNegotiationContractAutoUpdate = isChangedNegotiationContractAutoUpdate;
	}

	public Boolean getIsChangedNegotiationContractMemo() {
		return isChangedNegotiationContractMemo;
	}

	public void setIsChangedNegotiationContractMemo(Boolean isChangedNegotiationContractMemo) {
		this.isChangedNegotiationContractMemo = isChangedNegotiationContractMemo;
	}

	public Boolean getIsChangedNegotiationRelatedAgency() {
		return isChangedNegotiationRelatedAgency;
	}

	public void setIsChangedNegotiationRelatedAgency(Boolean isChangedNegotiationRelatedAgency) {
		this.isChangedNegotiationRelatedAgency = isChangedNegotiationRelatedAgency;
	}

	public Boolean getIsChangedNegotiationRelatedAgencyStartDate() {
		return isChangedNegotiationRelatedAgencyStartDate;
	}

	public void setIsChangedNegotiationRelatedAgencyStartDate(Boolean isChangedNegotiationRelatedAgencyStartDate) {
		this.isChangedNegotiationRelatedAgencyStartDate = isChangedNegotiationRelatedAgencyStartDate;
	}

	public Boolean getIsChangedNegotiationRelatedAgencyEndDate() {
		return isChangedNegotiationRelatedAgencyEndDate;
	}

	public void setIsChangedNegotiationRelatedAgencyEndDate(Boolean isChangedNegotiationRelatedAgencyEndDate) {
		this.isChangedNegotiationRelatedAgencyEndDate = isChangedNegotiationRelatedAgencyEndDate;
	}

	public Boolean getIsChangedNegotiationRelatedAgencyYear() {
		return isChangedNegotiationRelatedAgencyYear;
	}

	public void setIsChangedNegotiationRelatedAgencyYear(Boolean isChangedNegotiationRelatedAgencyYear) {
		this.isChangedNegotiationRelatedAgencyYear = isChangedNegotiationRelatedAgencyYear;
	}

	public Boolean getIsChangedNegotiationRelatedAffiliateShop() {
		return isChangedNegotiationRelatedAffiliateShop;
	}

	public void setIsChangedNegotiationRelatedAffiliateShop(Boolean isChangedNegotiationRelatedAffiliateShop) {
		this.isChangedNegotiationRelatedAffiliateShop = isChangedNegotiationRelatedAffiliateShop;
	}

	public Boolean getIsChangedBranchStoreStaffOpinion() {
		return isChangedBranchStoreStaffOpinion;
	}

	public void setIsChangedBranchStoreStaffOpinion(Boolean isChangedBranchStoreStaffOpinion) {
		this.isChangedBranchStoreStaffOpinion = isChangedBranchStoreStaffOpinion;
	}

	public Boolean getIsChangedBranchStoreOpinion() {
		return isChangedBranchStoreOpinion;
	}

	public void setIsChangedBranchStoreOpinion(Boolean isChangedBranchStoreOpinion) {
		this.isChangedBranchStoreOpinion = isChangedBranchStoreOpinion;
	}

	public Boolean getIsChangedBuOpinion() {
		return isChangedBuOpinion;
	}

	public void setIsChangedBuOpinion(Boolean isChangedBuOpinion) {
		this.isChangedBuOpinion = isChangedBuOpinion;
	}

	@Override
	protected ModelMapper<ProjectHistory> modelMapper(SqlSession session) {
		return session.getMapper(ProjectHistoryModelMapper.class);
	}
	
	public Float scheduleDelay() {
		Optional<ProjectSchedule> model = currentSchedule();

		if (model.isPresent()) {
			LocalDate date = model.get().getScheduleDate();
			if (date == null) {
				return 0F;
			} else {
				return differenceWeekFromNow(date);
			}
		} else {
			return 0F;
		}
	}
	
	private Float differenceWeekFromNow(LocalDate date) {
		Float week = (float) ChronoUnit.WEEKS.between(date, LocalDate.now());

		if (week != 0F) {
			Float much = (week % 7) / 7F;
			week += new BigDecimal(much).setScale(1, BigDecimal.ROUND_DOWN).floatValue();
		}
		return week;
	}
	
	public Optional<ProjectSchedule> currentSchedule() {
		return new ProjectScheduleRepository().getProjectScheduleHistory(getId(), getMProjectActionStatusId());
	}
	
	public Long getMProjectActionStatusId() {
		return mProjectActionStatusId;
	}

	public void setMProjectActionStatusId(Long mActionStatusId) {
		this.mProjectActionStatusId = mActionStatusId;
	}
	
	public String getImplementationDateValue() {
		if (getImplementationDatetime() == null) {
			return implementationScheduleDateValue();
		} else {
			return DATE_FORMAT.format(getImplementationDatetime());
		}
	}
	
	public String implementationScheduleDateValue() {
		DateTimeFormatter scheduleFormat = DateTimeFormatter.ofPattern("yyyy/MM");

		if (getImplementationScheduleDatetime() == null) {
			return "";
		} else {
			return String.format("%s%s", scheduleFormat.format(getImplementationScheduleDatetime()),
					implemenrationSeason());
		}
	}
	
	private String implemenrationSeason() {
		LocalDate date = getImplementationScheduleDatetime();
		DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("d");

		if (date == null) {
			return "";
		} else {
			switch (dayFormat.format(date)) {
			case "5":
				return "上旬";
			case "15":
				return "中旬";
			case "25":
				return "下旬";
			default:
				return "";
			}
		}
	}

	public LocalDate getPlanDate() {
		return planDate;
	}

	public void setPlanDate(LocalDate planDate) {
		this.planDate = planDate;
	}

	public Integer getPlanPeriod() {
		return planPeriod;
	}

	public void setPlanPeriod(Integer planPeriod) {
		this.planPeriod = planPeriod;
	}

	public String getPlanPeriodHalf() {
		return planPeriodHalf;
	}

	public void setPlanPeriodHalf(String planPeriodHalf) {
		this.planPeriodHalf = planPeriodHalf;
	}

	public Boolean getIsChangedImplementationScheduleDatetime() {
		return isChangedImplementationScheduleDatetime;
	}

	public void setIsChangedImplementationScheduleDatetime(Boolean isChangedImplementationScheduleDatetime) {
		this.isChangedImplementationScheduleDatetime = isChangedImplementationScheduleDatetime;
	}
	
	
	

}
