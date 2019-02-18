package jp.co.world.storedevelopment.sp.controller.dto;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jp.co.world.storedevelopment.model.OtherProjectAccount;
import jp.co.world.storedevelopment.model.OtherProjectTeam;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.ProjectContractProgress;
import jp.co.world.storedevelopment.model.ProjectOpinion;
import jp.co.world.storedevelopment.model.ProjectPlan;
import jp.co.world.storedevelopment.model.ProjectSectionProgress;
import jp.co.world.storedevelopment.model.ProjectTask;

public class ProjectUpdateDTO implements DTO<Project> {
	private static final ObjectMapper MAPPER = new ObjectMapper();

	private Long id;
	private String title;
	private Long projectCategoryId;
	private String division;
	private Boolean open = false;
	private Long officeProjectProgressId;
	private Long negotiationProjectProgressId;
	private Boolean investmentDiscussionTarget = false;
	private String description;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
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
	private Long mProjectActionStatusId;
	private Long progressISalesAgencyTargetId;
	private Long salesPrediction;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate externalReleaseStartDate;
	private Boolean externalReleaseConfirm = false;
	private Long stopReasonId;
	private Long landingProjectCategoryId;
	private Long conclusionPossibilityPercentId;
	private String landingMemo;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate implementationDatetime;
	private Long iSalesChannelId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate salesEndDate;
	private String rentIncreaseDecrease;
	private Boolean tenancy = false;
	private BigDecimal profitRate;
	private String tenancyPeriod;
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
	private String operationDivision;
	private String adoptDifficulty;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate implementationScheduleDatetime;
	private String shopNameTemporary;
	private Float profitExpectation;

	private List<ProjectSectionProgress> projectSectionProgressDto = new ArrayList<>();
	private List<ProjectContractProgress> projectContractProgressDto = new ArrayList<>();
	private List<ProjectOpinion> projectOpinionDto = new ArrayList<>();
	private List<OtherProjectTeam> otherProjectTeamDto = new ArrayList<>();
	private List<OtherProjectAccount> otherProjectAccountDtos = new ArrayList<>();
	private List<ProjectTask> projectTaskDtos = new ArrayList<>();

	private List<MultipartFile> multipartFiles = new ArrayList<>();
	private List<ProjectMediaDocumentDTO> projectMediaDocumentDto = new ArrayList<>();
	private List<ProjectScheduleDTO> projectScheduleDto = new ArrayList<>();
	private List<ProjectPlan> projectPlanDto;

	public ProjectUpdateDTO() {

	}

	public ProjectUpdateDTO(Project project) {
		this.copyProperties(this, project);
	}

	public static ProjectUpdateDTO toDTO(String json) {
		try {
			MAPPER.registerModule(new JavaTimeModule());
			return MAPPER.readValue(json, ProjectUpdateDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getMessage());
		}
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

	public Boolean getInvestmentDiscussionTarget() {
		return investmentDiscussionTarget;
	}

	public void setInvestmentDiscussionTarget(Boolean investmentDiscussionTarget) {
		this.investmentDiscussionTarget = investmentDiscussionTarget;
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

	public Long getSalesPrediction() {
		return salesPrediction;
	}

	public void setSalesPrediction(Long salesPrediction) {
		this.salesPrediction = salesPrediction;
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

	public LocalDate getImplementationDatetime() {
		return implementationDatetime;
	}

	public void setImplementationDatetime(LocalDate implementationDatetime) {
		this.implementationDatetime = implementationDatetime;
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
		if(tenancyPeriod != null && tenancyPeriod.trim().length() != 0) {
			this.tenancyPeriod = tenancyPeriod;
		}else {
			this.tenancyPeriod = null;
		}
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

	public String getOperationDivision() {
		return operationDivision;
	}

	public void setOperationDivision(String operationDivision) {
		this.operationDivision = operationDivision;
	}

	public String getAdoptDifficulty() {
		return adoptDifficulty;
	}

	public void setAdoptDifficulty(String adoptDifficulty) {
		this.adoptDifficulty = adoptDifficulty;
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

	public List<ProjectSectionProgress> getProjectSectionProgressDto() {
		return projectSectionProgressDto;
	}

	public void setProjectSectionProgressDto(List<ProjectSectionProgress> projectSectionProgressDto) {
		this.projectSectionProgressDto = projectSectionProgressDto;
	}

	public List<ProjectContractProgress> getProjectContractProgressDto() {
		return projectContractProgressDto;
	}

	public void setProjectContractProgressDto(List<ProjectContractProgress> projectContractProgressDto) {
		this.projectContractProgressDto = projectContractProgressDto;
	}

	public List<ProjectOpinion> getProjectOpinionDto() {
		return projectOpinionDto;
	}

	public void setProjectOpinionDto(List<ProjectOpinion> projectOpinionDto) {
		this.projectOpinionDto = projectOpinionDto;
	}

	public List<OtherProjectTeam> getOtherProjectTeamDto() {
		return otherProjectTeamDto;
	}

	public void setOtherProjectTeamDto(List<OtherProjectTeam> otherProjectTeamDto) {
		this.otherProjectTeamDto = otherProjectTeamDto;
	}

	public List<OtherProjectAccount> getOtherProjectAccountDtos() {
		return otherProjectAccountDtos;
	}

	public void setOtherProjectAccountDtos(List<OtherProjectAccount> otherProjectAccountDtos) {
		this.otherProjectAccountDtos = otherProjectAccountDtos;
	}

	public List<ProjectTask> getProjectTaskDtos() {
		return projectTaskDtos;
	}

	public void setProjectTaskDtos(List<ProjectTask> projectTaskDtos) {
		this.projectTaskDtos = projectTaskDtos;
	}

	public List<MultipartFile> getMultipartFiles() {
		return multipartFiles;
	}

	public void setMultipartFiles(List<MultipartFile> multipartFiles) {
		this.multipartFiles = multipartFiles;
	}

	public List<ProjectMediaDocumentDTO> getProjectMediaDocumentDto() {
		return projectMediaDocumentDto;
	}

	public void setProjectMediaDocumentDto(List<ProjectMediaDocumentDTO> projectMediaDocumentDto) {
		this.projectMediaDocumentDto = projectMediaDocumentDto;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public List<ProjectScheduleDTO> getProjectScheduleDto() {
		return projectScheduleDto;
	}

	public void setProjectScheduleDto(List<ProjectScheduleDTO> projectScheduleDto) {
		this.projectScheduleDto = projectScheduleDto;
	}

	public void setProjectActionStatusId(Long mProjectActionStatusId) {
		this.mProjectActionStatusId = mProjectActionStatusId;
	}

	public Long getMProjectActionStatusId() {
		return mProjectActionStatusId;
	}

	public void setMProjectActionStatusId(Long mProjectActionStatusId) {
		this.mProjectActionStatusId = mProjectActionStatusId;
	}

	public List<ProjectPlan> getProjectPlanDto() {
		return projectPlanDto;
	}

	public void setProjectPlanDto(List<ProjectPlan> projectPlanDto) {
		this.projectPlanDto = projectPlanDto;
	}

}