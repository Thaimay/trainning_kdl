package jp.co.world.storedevelopment.sp.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.IArea;
import jp.co.world.storedevelopment.model.IBlock;
import jp.co.world.storedevelopment.model.IBrandIncomeUnit;
import jp.co.world.storedevelopment.model.ICorporation;
import jp.co.world.storedevelopment.model.ICorporationGroup;
import jp.co.world.storedevelopment.model.IPrefectures;
import jp.co.world.storedevelopment.model.ISalesAgencyTarget;
import jp.co.world.storedevelopment.model.ISalesChannel;
import jp.co.world.storedevelopment.model.IShop;
import jp.co.world.storedevelopment.model.MPeriod;
import jp.co.world.storedevelopment.model.MProjectActionStatus;
import jp.co.world.storedevelopment.model.MProjectMeetingResult;
import jp.co.world.storedevelopment.model.MProjectProgressStatus;
import jp.co.world.storedevelopment.model.MStoreDevelopTeam;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.OtherProjectAccount;
import jp.co.world.storedevelopment.model.OtherProjectTeam;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.ProjectCategory;
import jp.co.world.storedevelopment.model.ProjectClassification;
import jp.co.world.storedevelopment.model.ProjectContractProgress;
import jp.co.world.storedevelopment.model.ProjectFile;
import jp.co.world.storedevelopment.model.ProjectImage;
import jp.co.world.storedevelopment.model.ProjectNegotiation;
import jp.co.world.storedevelopment.model.ProjectOpinion;
import jp.co.world.storedevelopment.model.ProjectPastConference;
import jp.co.world.storedevelopment.model.ProjectPlan;
import jp.co.world.storedevelopment.model.ProjectSectionProgress;
import jp.co.world.storedevelopment.model.ProjectSwitingItemControl;
import jp.co.world.storedevelopment.model.ProjectTask;
import jp.co.world.storedevelopment.model.ProjectVideo;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IAreaRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBlockRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBrandIncomeUnitRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationGroupRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IPrefecturesRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesAgencyTargetRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesChannelRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IShopRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MPeriodRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MProjectActionStatusRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MProjectMeetingResultRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MProjectProgressStatusRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MStoreDevelopTeamRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.OtherProjectAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.OtherProjectTeamRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectCategoryRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectClassificationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectContractProgressRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectFileRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectImageRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectNegotiationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectOpinionRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectPastConferenceRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectPlanRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectReadLaterAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectScheduleRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectSectionProgressRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectSwitingItemControlRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectTaskRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectVideoRepository;

public class ProjectDetailDTO implements DTO<Project> {

	private Long id;
	private String title;
	private Long projectCategoryId;
	private String division;
	private Boolean open;
	private Long officeProjectProgressId;
	private Long negotiationProjectProgressId;
	private String description;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	private Long buildingId;
	private Long iShopId;
	private String shopCd;
	private String shopName;
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
	private Long salesPrediction;
	private Boolean investmentDiscussionTarget;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate externalReleaseStartDate;
	private Boolean externalReleaseConfirm;
	private Boolean stop;
	private Long stopReasonId;
	private Long landingProjectCategoryId;
	private Long conclusionPossibilityPercentId;
	private String landingMemo;
	private Long planStatusId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate implementationDatetime;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate planDate;
	private Integer planPeriod;
	private String planPeriodHalf;
	private String operationDivision;
	private Long iSalesChannelId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate salesEndDate;
	private String rentIncreaseDecrease;
	private Boolean tenancy;
	private BigDecimal profitRate;
	private String tenancyPeriod;
	private String otherRequest;
	private String ourRequest;
	private String requestorName;
	private String requestorStatus;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate articleReviewDate;
	private Long articleReviewResultId;
	private String articleReviewResultComment;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate managementDate;
	private Long managementResultId;
	private String managementResultComment;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate investmentProcessDate;
	private Long investmentProcessResultId;
	private String investmentProcessResultComment;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime createdDatetime;
	private String createdAccountCode;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime updateDatetime;
	private String updateAccountCode;
	private Long mProjectActionStatusId;
	private String adoptDifficulty;
	private Float differenceWeeks;
	private Long currentScheduleId;
	private Long goalScheduleId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate implementationScheduleDatetime;
	private String shopNameTemporary;
	private Float profitExpectation;
	private String implementationDateValue;
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
	private Boolean isChangedImplementationScheduleDatetime;

	private Boolean isLater = false;
	private ProjectSwitingItemControl projectSwitingItemControl = new ProjectSwitingItemControl();
	private List<NegotiationListDTO> relatedNegotiationList = new ArrayList<NegotiationListDTO>();
	private List<ProjectListDTO> relatedProjectList = new ArrayList<ProjectListDTO>();

	private List<ScheduleDateDTO> scheduleList = new ArrayList<>();
	public Account account;

	public ProjectDetailDTO(Project project) {
		copyProperties(this, project);
		Optional<ProjectSwitingItemControl> projectSwitingItemControlOption = new ProjectSwitingItemControlRepository()
				.findByProjectCategoryId(project.getProjectCategoryId());
		projectSwitingItemControlOption.ifPresent(projectSwitingItemControl -> {
			setProjectSwitingItemControl(projectSwitingItemControl);
		});
		setmProjectActionStatusId(project.getMProjectActionStatusId());
		if (project.getSalesPrediction() != null) {
			setSalesPrediction(project.getSalesPrediction() / 1000);
		}
		if (project.getProfitExpectation() != null) {
			setProfitExpectation(project.getProfitExpectation() / 1000);
		}
		setDifferenceWeeks(project.scheduleDelay());
		setImplementationDateValue(project.getImplementationDateValue());
	}

	public ProjectDetailDTO(Project project, Account a) {
		this(project);

		List<Negotiation> listNegotiation = new ArrayList<>();
		List<ProjectNegotiationRelationProjectDTO> projectNegotiations = getProjectNegotiations();
		projectNegotiations.forEach(x -> {
			Optional<Negotiation> n = new NegotiationRepository().findById(x.getNegotiationId());
			if (n.isPresent()) {
				listNegotiation.add(n.get());
			}
		});
		List<NegotiationListDTO> dto = NegotiationListDTO.toList(listNegotiation, a);
		setRelatedNegotiationList(dto);

		List<ProjectListDTO> projectList = new ArrayList<>();
		if (buildingId != null) {
			List<Project> list = new ProjectRepository().getProjectListByBuildingId(buildingId);
			list.forEach(x -> {
				if (x.getId() != id) {
					projectList.add(new ProjectListDTO(x, a));
				}
			});
		}
		setRelatedProjectList(projectList);

		setIsLater(new ProjectReadLaterAccountRepository().isReadLater(project, a));

		List<ScheduleDateDTO> list = new ProjectScheduleRepository().findByProjectId(project.getId()).stream()
				.map(s -> {
					return new ScheduleDateDTO(s, getMProjectActionStatus());
				}).collect(Collectors.toList());
		setScheduleList(list);

		getScheduleList().forEach(s -> {
			Long statusId = s.getProjectStatusId();

			if (statusId.equals(project.getMProjectActionStatusId())) {
				setCurrentScheduleId(statusId);
			}

			if (s.getIsPastDay()) {
				setGoalScheduleId(statusId);
			}
		});

		setImplementationDateValue(project.getImplementationDateValue());
		setAccount(a);
	}

	public Long getmProjectActionStatusId() {
		return mProjectActionStatusId;
	}

	public void setmProjectActionStatusId(Long mProjectActionStatusId) {
		this.mProjectActionStatusId = mProjectActionStatusId;
	}

	public String getAdoptDifficulty() {
		return adoptDifficulty;
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

	public void setAdoptDifficulty(String adoptDifficulty) {
		this.adoptDifficulty = adoptDifficulty;
	}

	public List<ScheduleDateDTO> getScheduleList() {
		return scheduleList;
	}

	public void setScheduleList(List<ScheduleDateDTO> scheduleList) {
		this.scheduleList = scheduleList;
	}

	public ProjectDetailDTO() {

	}

	@Override
	public Project createModel() {
		return new Project();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getOperationDivision() {
		return operationDivision;
	}

	public void setOperationDivision(String operationDivision) {
		this.operationDivision = operationDivision;
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

	public MProjectMeetingResult getArticleReviewResult() {
		return new MProjectMeetingResultRepository().findById(articleReviewResultId).orElseGet(() -> {
			return null;
		});
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

	public MProjectMeetingResult getManagementResult() {
		return new MProjectMeetingResultRepository().findById(managementResultId).orElseGet(() -> {
			return null;
		});
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

	public MProjectMeetingResult getInvestmentProcessResult() {
		return new MProjectMeetingResultRepository().findById(investmentProcessResultId).orElseGet(() -> {
			return null;
		});
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

	public LocalDateTime getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(LocalDateTime createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public String getCreatedAccountCode() {
		return createdAccountCode;
	}

	public void setCreatedAccountCode(String createdAccountCode) {
		this.createdAccountCode = createdAccountCode;
	}

	public LocalDateTime getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(LocalDateTime updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public String getUpdateAccountCode() {
		return updateAccountCode;
	}

	public void setUpdateAccountCode(String updateAccountCode) {
		this.updateAccountCode = updateAccountCode;
	}

	public List<NegotiationListDTO> getRelatedNegotiationList() {
		return relatedNegotiationList;
	}

	public void setRelatedNegotiationList(List<NegotiationListDTO> relatedNegotiationList) {
		this.relatedNegotiationList = relatedNegotiationList;
	}

	public List<ProjectListDTO> getRelatedProjectList() {
		return relatedProjectList;
	}

	public void setRelatedProjectList(List<ProjectListDTO> relatedProjectList) {
		this.relatedProjectList = relatedProjectList;
	}

	public ProjectSwitingItemControl getProjectSwitingItemControl() {
		return projectSwitingItemControl;
	}

	public void setProjectSwitingItemControl(ProjectSwitingItemControl projectSwitingItemControl) {
		this.projectSwitingItemControl = projectSwitingItemControl;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<ProjectNegotiationRelationProjectDTO> getProjectNegotiations() {
		List<ProjectNegotiationRelationProjectDTO> projectNegotiations = new ArrayList<>();
		if (id != null) {
			List<ProjectNegotiation> list = new ProjectNegotiationRepository().findByProjectId(id);
			list.forEach(x -> projectNegotiations.add(new ProjectNegotiationRelationProjectDTO(x)));
		}
		return projectNegotiations;
	}

	public BuildingRelationProjectDTO getBuilding() {
		BuildingRelationProjectDTO dto = null;
		if (buildingId != null) {
			Optional<Building> building = new BuildingRepository().findById(buildingId);
			if (building.isPresent()) {
				return new BuildingRelationProjectDTO(building.get());
			}
		}
		return dto;
	}

	public IShopRelationProjectDTO getShop() {
		if (iShopId != null && account != null) {
			Optional<IShop> shop = new IShopRepository().findById(iShopId);
			if (shop.isPresent()) {
				return new IShopRelationProjectDTO(shop.get(), account);
			}
		}
		return null;
	}

	public ISalesChannelRelationProjectDTO getSalesChannel() {
		if (iSalesChannelId != null) {
			Optional<ISalesChannel> salesChannel = new ISalesChannelRepository().findById(iSalesChannelId);
			if (salesChannel.isPresent()) {
				return new ISalesChannelRelationProjectDTO(salesChannel.get());
			}
		}
		return null;
	}

	public ICorporationRelationProjectDTO getCorporation() {
		if (corporationId != null) {
			Optional<ICorporation> corporation = new ICorporationRepository().findById(corporationId);
			if (corporation.isPresent()) {
				return new ICorporationRelationProjectDTO(corporation.get());
			}
		}
		return null;
	}

	public ICorporationGroupRelationProjectDTO getCorporationGroup() {
		if (corporationGpId != null) {
			Optional<ICorporationGroup> corporationGp = new ICorporationGroupRepository().findById(corporationGpId);
			if (corporationGp.isPresent()) {
				return new ICorporationGroupRelationProjectDTO(corporationGp.get());
			}
		}
		return null;
	}

	public IAreaRelationProjectDTO getArea() {
		if (iAreaId != null) {
			Optional<IArea> area = new IAreaRepository().findById(iAreaId);
			if (area.isPresent()) {
				return new IAreaRelationProjectDTO(area.get());
			}
		}
		return null;
	}

	public IBlockRelationProjectDTO getBlock() {
		if (iBlockId != null) {
			Optional<IBlock> block = new IBlockRepository().findById(iBlockId);
			if (block.isPresent()) {
				return new IBlockRelationProjectDTO(block.get());
			}
		}
		return null;
	}

	public IPrefecturesRelationProjectDTO getPrefectures() {
		if (iPrefecturesId != null) {
			Optional<IPrefectures> prefectures = new IPrefecturesRepository().findById(iPrefecturesId);
			if (prefectures.isPresent()) {
				return new IPrefecturesRelationProjectDTO(prefectures.get());
			}
		}
		return null;
	}

	public IBrandIncomeUnitRelationProjectDTO getBrand() {
		if (brandId != null) {
			Optional<IBrandIncomeUnit> iBrand = new IBrandIncomeUnitRepository().findById(brandId);
			if (iBrand.isPresent()) {
				return new IBrandIncomeUnitRelationProjectDTO(iBrand.get());
			}
		}
		return null;
	}

	public MProjectProgressStatus getOfficeProjectProgress() {
		if (officeProjectProgressId != null) {
			Optional<MProjectProgressStatus> projectProgress = new MProjectProgressStatusRepository()
					.findById(officeProjectProgressId);
			if (projectProgress.isPresent()) {
				return projectProgress.get();
			}
		}
		return null;
	}

	public MProjectProgressStatus getNegotiationProjectProgress() {
		if (negotiationProjectProgressId != null) {
			Optional<MProjectProgressStatus> projectProgress = new MProjectProgressStatusRepository()
					.findById(negotiationProjectProgressId);
			if (projectProgress.isPresent()) {
				return projectProgress.get();
			}
		}
		return null;
	}

	private ProjectCategory getProjectCategory() {
		if (projectCategoryId != null) {
			Optional<ProjectCategory> projectCategory = new ProjectCategoryRepository().findById(projectCategoryId);
			if (projectCategory.isPresent()) {
				return projectCategory.get();
			}
		}
		return null;
	}

	public String getCategory() {
		if (getProjectCategory() != null) {
			return getProjectCategory().getName();
		}
		return StringUtils.EMPTY;
	}

	public String getCategoryName() {
		if (getProjectCategory() != null && getProjectCategory().getCategoryName() != null) {
			return getProjectCategory().getCategoryName();
		}
		return StringUtils.EMPTY;
	}

	public MProjectActionStatus getMProjectActionStatus() {
		if (mProjectActionStatusId != null) {
			Optional<MProjectActionStatus> opt = new MProjectActionStatusRepository().findById(mProjectActionStatusId);
			if (opt.isPresent()) {
				return opt.get();
			}
		}
		return null;
	}

	public String getProjectActionStatus() {
		if (mProjectActionStatusId == null || getMProjectActionStatus() == null) {
			return "";
		} else {
			return getMProjectActionStatus().getName();
		}
	}

	public String getLandingProjectCategory() {
		if (landingProjectCategoryId != null) {
			Optional<ProjectClassification> classification = new ProjectClassificationRepository()
					.findById(landingProjectCategoryId);
			if (classification.isPresent()) {
				return classification.get().getName();
			}
		}
		return StringUtils.EMPTY;
	}

	public ProjectSectionProgress getCurrentSectionProgress() {
		if (id != null) {
			Optional<ProjectSectionProgress> currentSectionProgressOption = new ProjectSectionProgressRepository()
					.getCurrentSectionProgressByProjectId(id);
			if (currentSectionProgressOption.isPresent()) {
				return currentSectionProgressOption.get();
			}
		}
		return null;
	}

	public ProjectSectionProgress getNegotiationSectionProgress() {
		if (id != null) {
			Optional<ProjectSectionProgress> negotiationSectionProgressOption = new ProjectSectionProgressRepository()
					.getNegotiationSectionProgressByProjectId(id);
			if (negotiationSectionProgressOption.isPresent()) {
				return negotiationSectionProgressOption.get();
			}
		}
		return null;
	}

	public String getContractTsuboSectionChange() {
		ProjectSectionProgress current = getCurrentSectionProgress();
		ProjectSectionProgress negotiation = getNegotiationSectionProgress();
		if (current != null && current.getContractTsubo() != null && negotiation != null
				&& negotiation.getContractTsubo() != null
				&& !getCategoryName().equals(ProjectCategory.storeOpenings)) {
			float changeValue = negotiation.getContractTsubo().floatValue() - current.getContractTsubo().floatValue();
			if (changeValue > 0) {
				// return "↑" + Math.abs(changeValue) + "増坪";
				return "増";
			} else if (changeValue < 0) {
				// return "↓" + Math.abs(changeValue) + "減坪";
				return "減";
			} else {
				return "同坪";
			}
		}
		return StringUtils.EMPTY;
	}

	public ProjectContractProgressRelationProjectDTO getCurrentContract() {
		if (id != null) {
			Optional<ProjectContractProgress> currentContract = new ProjectContractProgressRepository()
					.getCurrentContractByProjectId(id);
			if (currentContract.isPresent()) {
				return new ProjectContractProgressRelationProjectDTO(currentContract.get());
			}
		}
		return null;
	}

	public ProjectContractProgressRelationProjectDTO getProgressContract() {
		if (id != null) {
			Optional<ProjectContractProgress> progressContract = new ProjectContractProgressRepository()
					.getProgressContractByProjectId(id);
			if (progressContract.isPresent()) {
				return new ProjectContractProgressRelationProjectDTO(progressContract.get());
			}
		}
		return null;
	}

	public List<ProjectTaskRelationProjectDTO> getProjectTasks() {
		List<ProjectTaskRelationProjectDTO> dtos = new ArrayList<>();
		if (id != null) {
			List<ProjectTask> list = new ProjectTaskRepository().findByProjectId(id);
			list.forEach(x -> dtos.add(new ProjectTaskRelationProjectDTO(x)));
		}
		return dtos;
	}

	public List<ProjectPastConference> getProjectPastConferences() {
		if (id != null) {
			return new ProjectPastConferenceRepository().findByProjectId(id);
		}
		return new ArrayList<>();
	}

	public List<RelatedAccountDTO> getStoreAccounts() {
		List<RelatedAccountDTO> dtos = new ArrayList<>();
		if (id != null) {
			List<OtherProjectAccount> list = new OtherProjectAccountRepository().findByProjectId(id);
			list.forEach(x -> {
				if (x.getCategory().equals("STORE")) {
					Optional<Account> account = new AccountRepository().findById(x.getiAccountId());
					if (account.isPresent()) {
						dtos.add(new RelatedAccountDTO(account.get()));
					}
				}
			});
		}
		return dtos;
	}

	public List<RelatedAccountDTO> getSalesAccounts() {
		List<RelatedAccountDTO> dtos = new ArrayList<>();
		if (id != null) {
			List<OtherProjectAccount> list = new OtherProjectAccountRepository().findByProjectId(id);
			list.forEach(x -> {
				if (x.getCategory().equals("SALES")) {
					Optional<Account> account = new AccountRepository().findById(x.getiAccountId());
					if (account.isPresent()) {
						dtos.add(new RelatedAccountDTO(account.get()));
					}
				}
			});
		}
		return dtos;
	}

	public List<RelatedAccountDTO> getSectionAccounts() {
		List<RelatedAccountDTO> dtos = new ArrayList<>();
		if (id != null) {
			List<OtherProjectAccount> list = new OtherProjectAccountRepository().findByProjectId(id);
			list.forEach(x -> {
				if (x.getCategory().equals("SECTION")) {
					Optional<Account> account = new AccountRepository().findById(x.getiAccountId());
					if (account.isPresent()) {
						dtos.add(new RelatedAccountDTO(account.get()));
					}
				}
			});
		}
		return dtos;
	}

	public List<RelatedAccountDTO> getFcAccounts() {
		List<RelatedAccountDTO> dtos = new ArrayList<>();
		if (id != null) {
			List<OtherProjectAccount> list = new OtherProjectAccountRepository().findByProjectId(id);
			list.forEach(x -> {
				if (x.getCategory().equals("FC")) {
					Optional<Account> account = new AccountRepository().findById(x.getiAccountId());
					if (account.isPresent()) {
						dtos.add(new RelatedAccountDTO(account.get()));
					}
				}
			});
		}
		return dtos;
	}

	public List<RelatedAccountDTO> getOtherAccounts() {
		List<RelatedAccountDTO> dtos = new ArrayList<>();
		if (id != null) {
			List<OtherProjectAccount> list = new OtherProjectAccountRepository().findByProjectId(id);
			list.forEach(x -> {
				if (x.getCategory().equals("OTHER")) {
					Optional<Account> account = new AccountRepository().findById(x.getiAccountId());
					if (account.isPresent()) {
						dtos.add(new RelatedAccountDTO(account.get()));
					}
				}
			});
		}
		return dtos;
	}

	public List<RelatedAccountDTO> getLeaderAccounts() {
		List<RelatedAccountDTO> dtos = new ArrayList<>();
		if (id != null) {
			List<OtherProjectAccount> list = new OtherProjectAccountRepository().findByProjectId(id);
			list.forEach(x -> {
				if (x.getCategory().equals("humanResourceLeader")) {
					Optional<Account> account = new AccountRepository().findById(x.getiAccountId());
					if (account.isPresent()) {
						dtos.add(new RelatedAccountDTO(account.get()));
					}
				}
			});
		}
		return dtos;
	}

	public List<ProjectFileUploadDTO> getFiles() {
		List<ProjectFileUploadDTO> list = new ArrayList<>();
		if (id != null) {
			List<Long> listOpinionFile = new ArrayList<>();
			List<ProjectOpinion> projectOpinions = new ProjectOpinionRepository().findByProjectId(id);
			if (projectOpinions != null) {
				projectOpinions.forEach(x -> {
					if (x.getFileId() != null) {
						listOpinionFile.add(x.getFileId());
					}
				});
			}

			List<ProjectFile> files = new ProjectFileRepository().findByProjectId(id);
			if (files != null) {
				files.forEach(x -> {
					if (!listOpinionFile.contains(x.getId())) {
						list.add(new ProjectFileUploadDTO(x));
					}
				});
			}

			List<ProjectImage> images = new ProjectImageRepository().findByProjectId(id);
			if (images != null) {
				images.forEach(x -> {
					if (!listOpinionFile.contains(x.getId())) {
						list.add(new ProjectFileUploadDTO(x));
					}
				});
			}

			List<ProjectVideo> videos = new ProjectVideoRepository().findByProjectId(id);
			if (videos != null) {
				videos.forEach(x -> {
					if (!listOpinionFile.contains(x.getId())) {
						list.add(new ProjectFileUploadDTO(x));
					}
				});
			}
		}
		return list;
	}

	public ISalesAgencyTarget getCurrentSalesAgencyTarget() {
		if (currentISalesAgencyTargetId != null) {
			Optional<ISalesAgencyTarget> salesAgencyTarget = new ISalesAgencyTargetRepository()
					.findBySalesAgencyTargetId(currentISalesAgencyTargetId);
			if (salesAgencyTarget.isPresent()) {
				return salesAgencyTarget.get();
			}
		}
		return null;
	}

	public ISalesAgencyTarget getProgressSalesAgencyTarget() {
		if (progressISalesAgencyTargetId != null) {
			Optional<ISalesAgencyTarget> salesAgencyTarget = new ISalesAgencyTargetRepository()
					.findBySalesAgencyTargetId(progressISalesAgencyTargetId);
			if (salesAgencyTarget.isPresent()) {
				return salesAgencyTarget.get();
			}
		}
		return null;
	}

	public String getUpdateAccountName() {
		Optional<Account> updateAccount = new AccountRepository().findByCode(updateAccountCode);
		if (updateAccount.isPresent()) {
			return updateAccount.get().getFullName();
		} else {
			return StringUtils.EMPTY;
		}
	}

	public ProjectOpinionRelationProjectDTO getBranchOpinion() {
		if (id != null) {
			Optional<ProjectOpinion> opinion = new ProjectOpinionRepository().getBranchOpinion(id);
			if (opinion.isPresent()) {
				return new ProjectOpinionRelationProjectDTO(opinion.get());
			}
		}
		return null;
	}

	public ProjectOpinionRelationProjectDTO getViewOpinion() {
		if (id != null) {
			Optional<ProjectOpinion> opinion = new ProjectOpinionRepository().getViewOpinion(id);
			if (opinion.isPresent()) {
				return new ProjectOpinionRelationProjectDTO(opinion.get());
			}
		}
		return null;
	}

	public ProjectOpinionRelationProjectDTO getBuOpinion() {
		if (id != null) {
			Optional<ProjectOpinion> opinion = new ProjectOpinionRepository().getBuOpinion(id);
			if (opinion.isPresent()) {
				return new ProjectOpinionRelationProjectDTO(opinion.get());
			}
		}
		return null;
	}

	public Boolean getIsLater() {
		return isLater;
	}

	public void setIsLater(Boolean isLater) {
		this.isLater = isLater;
	}

	public String getStopReason() {
		if (stopReasonId != null) {
			Optional<ProjectClassification> classification = new ProjectClassificationRepository()
					.findById(stopReasonId);
			if (classification.isPresent()) {
				return classification.get().getName();
			}
		}
		return StringUtils.EMPTY;
	}

	public String getConclusionPossibilityPercent() {
		if (conclusionPossibilityPercentId != null) {
			Optional<ProjectClassification> classification = new ProjectClassificationRepository()
					.findById(conclusionPossibilityPercentId);
			if (classification.isPresent()) {
				return classification.get().getName();
			}
		}
		return StringUtils.EMPTY;
	}

	public List<ProjectPlan> getListProjectPlan() {
		List<ProjectPlan> dtos = new ArrayList<>();
		if (id != null) {
			dtos = new ProjectPlanRepository().findByProjectId(id);
		}
		return dtos;
	}

	public ProjectPlan getSalesAgencyCurrent() {
		ProjectPlan plan = null;
		if (getListProjectPlan().size() > 0) {
			for (ProjectPlan pl : getListProjectPlan()) {
				if (pl.getCategory().equals("CURRENT")) {
					plan = pl;
				}
			}
			return plan;
		}
		return null;
	}

	public ProjectPlan getSalesAgencyProgress() {
		ProjectPlan plan = null;
		if (getListProjectPlan().size() > 0) {
			for (ProjectPlan pl : getListProjectPlan()) {
				if (pl.getCategory().equals("PROGRESS")) {
					plan = pl;
				}
			}
			return plan;
		}
		return null;
	}

	public String getNumberOfYearSalesAgencyCurrent() {
		ProjectPlan plan = getSalesAgencyCurrent();
		if (plan != null) {
			if (plan.getEndDate() != null && plan.getStartDate() != null) {
				Long diff = java.time.temporal.ChronoUnit.DAYS.between(plan.getStartDate(), plan.getEndDate());
				Double result = Double.parseDouble(diff.toString())/Double.parseDouble("365");
				return String.valueOf(Math.round(result * 100.0) / 100.0);
			}
		}
		return null;
	}

	public String getStopDivision() {
		if (this.stopReasonId != null) {
			ProjectClassification pc = new ProjectClassificationRepository().findById(this.stopReasonId).get();
			if (pc != null) {
				return pc.getName();
			}
		}
		return "";
	}

	public String getDisplayTenancy() {
		if (tenancy != null && tenancy == true) {
			return "なる";
		} else {
			return "ならない";
		}
	}

	public List<MStoreDevelopTeamListDTO> getOtherProjectTeams() {
		List<MStoreDevelopTeamListDTO> listDTO = new ArrayList<>();
		if (id != null) {
			List<OtherProjectTeam> listTeam = new OtherProjectTeamRepository().findByProjectId(id);
			listTeam.forEach(x -> {
				String deptCd = x.getDeptCd();
				Optional<MStoreDevelopTeam> opt = new MStoreDevelopTeamRepository().findByDeptCd(deptCd);
				if (opt.isPresent()) {
					listDTO.add(new MStoreDevelopTeamListDTO(opt.get()));
				}
			});
		}
		return listDTO;
	}

	public String getImplementationPeriod() {
		if (implementationDatetime != null) {
			Optional<MPeriod> opt = new MPeriodRepository()
					.getPeriod(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(implementationDatetime));
			if (opt.isPresent()) {
				MPeriod period = opt.get();
				if(implementationDatetime.isAfter(period.getFirstHalfEndDate())) {
					return opt.get().getPeriod() + "期下期";
				} else {
					return opt.get().getPeriod() + "期上期";
				}
			}
		} else if (implementationDatetime == null && implementationScheduleDatetime != null) {
			Optional<MPeriod> opt = new MPeriodRepository()
					.getPeriod(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(implementationScheduleDatetime));
			if (opt.isPresent()) {
				MPeriod period = opt.get();
				if(implementationScheduleDatetime.isAfter(period.getFirstHalfEndDate())) {
					return opt.get().getPeriod() + "期下期";
				} else {
					return opt.get().getPeriod() + "期上期";
				}
			}
		}

		return StringUtils.EMPTY;
	}

	public String getPlanStatus() {
		if (planStatusId != null) {
			Optional<ProjectClassification> classification = new ProjectClassificationRepository()
					.findById(planStatusId);
			if (classification.isPresent()) {
				return classification.get().getName();
			}
		}
		return StringUtils.EMPTY;
	}

	public Float getDifferenceWeeks() {
		return differenceWeeks;
	}

	public void setDifferenceWeeks(Float differenceWeeks) {
		this.differenceWeeks = differenceWeeks;
	}

	public Long getCurrentScheduleId() {
		return currentScheduleId;
	}

	public void setCurrentScheduleId(Long currentScheduleId) {
		this.currentScheduleId = currentScheduleId;
	}

	public Long getGoalScheduleId() {
		return goalScheduleId;
	}

	public void setGoalScheduleId(Long goalScheduleId) {
		this.goalScheduleId = goalScheduleId;
	}

	public Float getProfitExpectation() {
		return profitExpectation;
	}

	public void setProfitExpectation(Float profitExpectation) {
		this.profitExpectation = profitExpectation;
	}

	public String getImplementationDateValue() {
		return implementationDateValue;
	}

	public void setImplementationDateValue(String implementationDateValue) {
		this.implementationDateValue = implementationDateValue;
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

	public String getShopCd() {
		return shopCd;
	}

	public void setShopCd(String shopCd) {
		this.shopCd = shopCd;
	}

	public Boolean getIsChangedImplementationScheduleDatetime() {
		return isChangedImplementationScheduleDatetime;
	}

	public void setIsChangedImplementationScheduleDatetime(Boolean isChangedImplementationScheduleDatetime) {
		this.isChangedImplementationScheduleDatetime = isChangedImplementationScheduleDatetime;
	}
	
	
}
