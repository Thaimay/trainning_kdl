package jp.co.world.storedevelopment.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ProjectModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesAgencyTargetRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IShopRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MPeriodRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MProjectActionStatusRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MProjectMeetingResultRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MProjectProgressStatusRepository;
import jp.co.world.storedevelopment.model.mapper.repository.OtherProjectAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectCategoryRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectClassificationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectContractProgressRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectFileRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectNegotiationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectOpinionRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectPlanRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectScheduleRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectSectionProgressRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectTaskRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ShopRepository;
import jp.co.world.storedevelopment.model.mapper.repository.TodoRepository;
import jp.co.world.storedevelopment.model.service.ActionStatusCalculationService;
import jp.co.world.storedevelopment.model.service.ProjectService;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectUpdateDTO;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class Project extends ActiveModel<Project> implements ProjectTargetAlert {

	MProjectProgressStatusRepository mProjectProgressStatusRepository = new MProjectProgressStatusRepository();
	MProjectActionStatusRepository mProjectActionStatusRepository = new MProjectActionStatusRepository();
	MProjectMeetingResultRepository mProjectMeetingResultRespository = new MProjectMeetingResultRepository();

	private String title;
	private Long projectCategoryId;
	private String division;
	private Boolean open;
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
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate planDate;
	private Integer planPeriod;
	private String planPeriodHalf;
	private String operationDivision;
	private Long iSalesChannelId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
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
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate implementationScheduleDatetime;
	private String shopNameTemporary;
	private Float profitExpectation;
	private String[] ignoreFields = new String[] { "implementationDateValue", "currentISalesAgencyTargetId", "progressISalesAgencyTargetId" };

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
	
	public Boolean getIsChangedImplementationScheduleDatetime() {
		return isChangedImplementationScheduleDatetime;
	}

	public void setIsChangedImplementationScheduleDatetime(Boolean isChangedImplementationScheduleDatetime) {
		this.isChangedImplementationScheduleDatetime = isChangedImplementationScheduleDatetime;
	}

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
	private static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	public void setIgnoreFields(String[] ignoreFields) {
		this.ignoreFields = ignoreFields;
	}

	public Boolean isWorkEnd() {
		return "終了".equals(getOperationDivision());
	}

	public String implemenrationDateValue() {
		DateTimeFormatter implemenratoinFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		DateTimeFormatter scheduleFormat = DateTimeFormatter.ofPattern("yyyy/MM");

		if (getImplementationDatetime() == null && getImplementationScheduleDatetime() == null) {
			return "";
		} else if (getImplementationDatetime() != null) {
			return implemenratoinFormat.format(getImplementationDatetime());
		} else {
			return String.format("%s%s", scheduleFormat.format(getImplementationScheduleDatetime()),
					implemenrationSeason());    
		}
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

	public void createArticleReviewPastConference() {
		if (getArticleReviewResultId() != null) {
			MProjectMeetingResult result = new MProjectMeetingResultRepository().findById(getArticleReviewResultId())
					.orElseGet(() -> {
						throw new IllegalStateException();
					});
			ProjectPastConference model = new ProjectPastConference();
			model.setProjectId(getId());
			model.setName("物件検討会");
			model.setResult(result.getName());
			model.setScheduleDate(getArticleReviewDate());
			model.setComment(getArticleReviewResultComment());
			model.create();

			setArticleReviewResultId(null);
			setArticleReviewDate(null);
			setArticleReviewResultComment(null);
			update();
		}
	}

	public String stopReason() {
		if (getStopReasonId() == null) {
			return "";
		} else {
			ProjectClassification model = new ProjectClassificationRepository().findById(getStopReasonId())
					.orElseGet(() -> {
						throw new IllegalStateException(String.format("中止理由のマスタが存在しません ID:%s", getStopReasonId()));
					});
			return model.getName();
		}
	}

	public void createManagementPastConference() {
		if (getManagementResultId() != null) {
			MProjectMeetingResult result = new MProjectMeetingResultRepository().findById(getManagementResultId())
					.orElseGet(() -> {
						throw new IllegalStateException();
					});
			ProjectPastConference model = new ProjectPastConference();
			model.setProjectId(getId());
			model.setName("経営会議");
			model.setResult(result.getName());
			model.setScheduleDate(getManagementDate());
			model.setComment(getManagementResultComment());
			model.create();

			setManagementResultId(null);
			setManagementDate(null);
			setManagementResultComment(null);
			update();
		}
	}

	public void createInvestmentPastConference() {
		if (getInvestmentProcessResultId() != null) {
			MProjectMeetingResult result = new MProjectMeetingResultRepository()
					.findById(getInvestmentProcessResultId()).orElseGet(() -> {
						throw new IllegalStateException();
					});
			ProjectPastConference model = new ProjectPastConference();
			model.setProjectId(getId());
			model.setName("投資上程");
			model.setResult(result.getName());
			model.setScheduleDate(getInvestmentProcessDate());
			model.setComment(getInvestmentProcessResultComment());
			model.create();

			setInvestmentProcessResultId(null);
			setInvestmentProcessDate(null);
			setInvestmentProcessResultComment(null);
			update();
		}
	}

	public ProjectCategory category() {
		return new ProjectCategoryRepository().findById(getProjectCategoryId()).orElseGet(() -> {
			throw new IllegalStateException("存在しないカテゴリです");
		});
	}

	public Boolean hasNegotiation() {
		return new ProjectNegotiationRepository().findByProjectId(getId()).size() > 0;
	}

	public Boolean isCategoryComplete() {
		ProjectCategory category = category();

		return category.requireStoreCopy();
	}

	public Optional<MProjectActionStatus> actionStatus() {
		return new MProjectActionStatusRepository().findById(getMProjectActionStatusId());
	}

	public Optional<MProjectProgressStatus> inHouseProgressStatus() {
		return new MProjectProgressStatusRepository().findById(getOfficeProjectProgressId());
	}

	public Optional<MProjectProgressStatus> negotiationStatus() {
		return new MProjectProgressStatusRepository().findById(getNegotiationProjectProgressId());
	}

	public Optional<IShop> iShop() {
		if (getiShopId() != null) {
			return new IShopRepository().findById(getiShopId());
		} else {
			return Optional.empty();
		}
	}

	public Optional<Shop> shop() {
		if (getiShopId() != null) {
			return Optional.ofNullable(new ShopRepository().findByIShopId(getiShopId()));
		} else {
			return Optional.empty();
		}
	}

	public Optional<ProjectSchedule> articleReviewSchedule() {
		return new ProjectScheduleRepository().findReviewSchedule(getId());
	}

	public Optional<ProjectSchedule> consensusSchedule() {
		return new ProjectScheduleRepository().findConsensusSchedule(getId());
	}

	public Optional<ProjectSchedule> currentSchedule() {
		return new ProjectScheduleRepository().getProjectSchedule(getId(), getMProjectActionStatusId());
	}
	
	public Optional<ProjectSchedule> nextSchedule() {
		Long nextId = getMProjectActionStatusId();
		if (nextId != null) {
			nextId = nextId + 1L;
		}
		
		return new ProjectScheduleRepository().getProjectSchedule(getId(), nextId);
	}

	public Float scheduleDelay() {
		Optional<ProjectSchedule> model = nextSchedule();

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

	public Boolean correntConsensus() {
		if (consensusSchedule().isPresent()) {
			Optional<LocalDate> date = consensusSchedule().get().scheduleDateOptional();

			if (date.isPresent()) {
				Integer days = betweenPeriod(date.get());
				return days < 0;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public List<ProjectTask> projectTasks() {
		return new ProjectTaskRepository().findByProjectId(getId());
	}

	public void refreshTodo() {
		projectTasks().forEach(t -> {
			refreshTaskPrev(t);
			refreshTheDay(t);
			refreshExpired(t);
		});
		refreshConsense();
		refreshExternalPrev();
		refreshExternalTheDay();
		refreshExternalExpired();
		refreshOpinion();
		refreshClosed();
		refreshStartDate();
	}

	public void refreshTaskPrev(ProjectTask task) {
		Optional<MTodoBatchSetting> config = MTodoBatchSetting.taskTermInAdvance();

		config.ifPresent(c -> {
			if (task.correctPrev(c.valueInteger()) == false) {
				new TodoRepository().deleteByProject(getId(), 12);
			}
		});
	}

	public void refreshTheDay(ProjectTask task) {
		Optional<MTodoBatchSetting> config = MTodoBatchSetting.taskTermInAdvance();

		config.ifPresent(c -> {
			if (task.correntTheDay() == false) {
				new TodoRepository().deleteByProject(getId(), 13);
			}
		});
	}

	public void refreshExpired(ProjectTask task) {
		Optional<MTodoBatchSetting> config = MTodoBatchSetting.taskTermInAdvance();

		config.ifPresent(c -> {
			if (task.correntExpired() == false) {
				new TodoRepository().deleteByProject(getId(), 14);
			}
		});
	}

	public void refreshConsense() {
		if (correntConsensus() == false) {
			new TodoRepository().deleteByProject(getId(), 16);
		}
	}

	public void refreshExternalPrev() {
		Optional<MTodoBatchSetting> config = MTodoBatchSetting.externalReleaseInAdvance();

		config.ifPresent(c -> {
			if (correntPrev(c.valueInteger()) == false) {
				new TodoRepository().deleteByProject(getId(), 17);
			}
		});
	}

	public void refreshExternalTheDay() {
		Optional<MTodoBatchSetting> config = MTodoBatchSetting.externalReleaseInAdvance();

		config.ifPresent(c -> {
			if (correntTheDay() == false) {
				new TodoRepository().deleteByProject(getId(), 18);
			}
		});
	}

	public void refreshExternalExpired() {
		Optional<MTodoBatchSetting> config = MTodoBatchSetting.externalReleaseInAdvance();

		config.ifPresent(c -> {
			if (correntExpired() == false) {
				new TodoRepository().deleteByProject(getId(), 19);
			}
		});
	}

	public void refreshOpinion() {
		Optional<MTodoBatchSetting> config = MTodoBatchSetting.opinionRecordInAdvance();

		config.ifPresent(c -> {
			if (correntArticleReviewScheduleAlert(c.valueInteger()) == false) {
				new TodoRepository().deleteByProject(getId(), 20);
			}
		});
	}

	public void refreshClosed() {
		Optional<MTodoBatchSetting> config = MTodoBatchSetting.preventRetreat();

		config.ifPresent(c -> {
			if (correntCloses(c.valueInteger()) == false) {
				new TodoRepository().deleteByProject(getId(), 21);
			}
		});
	}

	public void refreshStartDate() {
		Optional<MTodoBatchSetting> config = MTodoBatchSetting.startDatetimeExpired();

		config.ifPresent(c -> {
			if (correntStartDateAlert(c.valueInteger())) {
				new TodoRepository().deleteByProject(getId(), 22);
			}
		});
	}

	public Boolean correntStartDateAlert(Integer value) {
		LocalDate date = LocalDate.of(getCreatedDatetime().getYear(), getCreatedDatetime().getMonthValue(),
				getCreatedDatetime().getDayOfMonth());
		Integer days = betweenPeriod(date);

		return days <= value && getStartDate() == null;
	}

	public Boolean correntCloses(Integer value) {
		LocalDate date = LocalDate.of(getCreatedDatetime().getYear(), getCreatedDatetime().getMonthValue(),
				getCreatedDatetime().getDayOfMonth());
		Integer days = betweenPeriod(date);

		return days > value;
	}

	public Optional<ProjectOpinion> branchOpinion() {
		return new ProjectOpinionRepository().getBranchOpinion(getId());
	}

	public Optional<ProjectOpinion> viewOpinion() {
		return new ProjectOpinionRepository().getViewOpinion(getId());
	}

	private Boolean correctInputBranchOpinion() {
		Optional<ProjectOpinion> model = branchOpinion();

		if (model.isPresent()) {
			return model.get().notEmptyComment();
		} else {
			return false;
		}
	}

	private Boolean correctInputViewOpinion() {
		Optional<ProjectOpinion> model = viewOpinion();

		if (model.isPresent()) {
			return model.get().notEmptyComment();
		} else {
			return false;
		}
	}

	public Boolean correntArticleReviewScheduleAlert(Integer value) {
		if (articleReviewSchedule().isPresent()) {
			Optional<LocalDate> date = articleReviewSchedule().get().scheduleDateOptional();

			if (date.isPresent()) {
				Integer days = betweenPeriod(date.get());
				return (days == 0 || days <= value)
						&& (correctInputBranchOpinion() == false || correctInputViewOpinion() == false);
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public Boolean correntPrev(Integer prev) {
		Optional<LocalDate> date = Optional.ofNullable(externalReleaseStartDate);

		if (date.isPresent()) {
			Integer day = betweenPeriod(date.get());
			return day <= prev && day >= 1 && getExternalReleaseConfirm() == false;
		} else {
			return false;
		}
	}

	public Boolean correntTheDay() {
		Optional<LocalDate> date = Optional.ofNullable(externalReleaseStartDate);

		if (date.isPresent()) {
			Integer day = betweenPeriod(date.get());
			return day == 0 && getExternalReleaseConfirm() == false;
		} else {
			return false;
		}
	}

	public Boolean correntExpired() {
		Optional<LocalDate> date = Optional.ofNullable(externalReleaseStartDate);

		if (date.isPresent()) {
			Integer day = betweenPeriod(date.get());
			return day < 0 && getExternalReleaseConfirm() == false;
		} else {
			return false;
		}
	}

	public String closedMessage() {
		return String.format("退店阻止案件が作成されました。状況を確認のうえ、案件を開始してください %s %s", mailTitle(), currentContractDateValue());
	}

	public String startDateMessage() {
		return String.format("リマインド：退店阻止案件が作成されました。状況を確認のうえ、案件を開始してください %s %s", mailTitle(), currentContractDateValue());
	}

	private String currentContractDateValue() {
		Optional<ProjectContractProgress> progress = currentContractProgress();
		if (progress.isPresent()) {
			ProjectContractProgress model = progress.get();
			if (model.getContractEndDate() != null) {
				return DATE_FORMAT.format(model.getContractEndDate());
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	public String consenseMessage() {
		return String.format("合意予定の日付を過ぎています。案件の状況を確認してください。\n%s", mailTitle());
	}

	public String articleReviewMessage() {
		String date = DATE_FORMAT.format(articleReviewSchedule().get().getScheduleDate());
		return String.format("%s 物件検討会ですが、支店見解、事業部見解が未入力です。\n%s", date, mailTitle());
	}

	public String prevMessage() {
		String date = DATE_FORMAT.format(externalReleaseStartDate);
		return String.format("外部公開予定日の%s日前ですが、日付が確定されていません %s 外部公開予定日：%s", betweenPeriod(externalReleaseStartDate), mailTitle(),
				date);
	}

	public String theDayMessage() {
		String date = DATE_FORMAT.format(LocalDate.now());
		return String.format("外部公開予定日当日ですが、日付が確定されていません %s 外部公開予定日：%s\n実施日：%s", mailTitle(), date, implemenrationDateValue());
	}

	public String expiredMessage() {
		String date = DATE_FORMAT.format(externalReleaseStartDate);
		return String.format("外部公開予定日を過ぎています。日付が確定されていません %s 外部公開予定日：%s\n実施日：%s", mailTitle(), date, implemenrationDateValue());
	}

	public List<Account> accounts() {
		return new OtherProjectAccountRepository().findAccountByProjectId(getId());
	}

	public List<Account> storeAccounts() {
		return new OtherProjectAccountRepository().findStoreByProjectId(getId());
	}

	public List<Account> storeSalesAccounts() {
		return new OtherProjectAccountRepository().findStoreSalesByProjectId(getId());
	}

	public Integer betweenPeriod(LocalDate date) {
		Temporal now = LocalDate.now().atTime(0, 0);
		Temporal target = LocalDate.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth()).atTime(0, 0);
		return (int) Duration.between(now, target).toDays();
	}

	public void copyShop(IShop model) {
		new ProjectService().updateFromShop(this, model);
	}

	public void copyBuilding(Building model) {
		new ProjectService().updateFromBuilding(this, model);
	}

	public Optional<ProjectSectionProgress> currentSectionProgress() {
		return new ProjectSectionProgressRepository().getCurrentSectionProgressByProjectId(getId());
	}

	public Optional<ProjectSectionProgress> negotiationSectionProgress() {
		return new ProjectSectionProgressRepository().getNegotiationSectionProgressByProjectId(getId());
	}

	public Optional<ProjectContractProgress> currentContractProgress() {
		return new ProjectContractProgressRepository().getCurrentContractByProjectId(getId());
	}

	public Optional<ProjectContractProgress> negotiationContractProgress() {
		return new ProjectContractProgressRepository().getProgressContractByProjectId(getId());
	}

	public Optional<ProjectPlan> planCurrent() {
		return new ProjectPlanRepository().findCurrentByProjectId(getId());
	}

	public Optional<ProjectPlan> planProgress() {
		return new ProjectPlanRepository().findProgressByProjectId(getId());
	}

	public Optional<ISalesAgencyTarget> currentCorporation() {
		return new ISalesAgencyTargetRepository().findById(getProgressISalesAgencyTargetId());
	}

	@Override
	protected ModelMapper<Project> modelMapper(SqlSession session) {
		return session.getMapper(ProjectModelMapper.class);
	}

	public String mailTitle() {
		String title = getTitle();

		if (title != null && title.isEmpty() == false) {
			return title;
		} else {
			return "案件名なし";
		}
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

	public Long getMProjectActionStatusId() {
		return mProjectActionStatusId;
	}

	public void setMProjectActionStatusId(Long mActionStatusId) {
		this.mProjectActionStatusId = mActionStatusId;
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

	public void changeStatusCodeIgnoreOther(MProjectProgressStatus mProjectProgressStatus) {
		// 手動入力による更新は「社内進捗」「交渉進捗」のみのため、契約進捗は外部入力のセット項目に含めない
		switch (mProjectProgressStatus.getCategory()) {
		case MProjectProgressStatus.COMPANY:
		case MProjectProgressStatus.NEGOTIATION:
			setStatusCode(mProjectProgressStatus);
			break;
		}
	}

	public void initStatusCode(Long projectCategoryId) {
		List<MProjectProgressStatus> mProjectProgressStatusList = mProjectProgressStatusRepository
				.getInitMProjectProgressStatus(projectCategoryId);
		mProjectProgressStatusList.forEach(mProjectProgressStatus -> {
			setStatusCode(mProjectProgressStatus);
		});
	}

	private void setStatusCode(MProjectProgressStatus mProjectProgressStatus) {
		switch (mProjectProgressStatus.getCategory()) {
		case MProjectProgressStatus.COMPANY:
			setOfficeProjectProgressId(mProjectProgressStatus.getId());
			setInHouseProgress(mProjectProgressStatus.getCode());
			break;
		case MProjectProgressStatus.NEGOTIATION:
			setNegotiationProjectProgressId(mProjectProgressStatus.getId());
			setNegotiationProgress(mProjectProgressStatus.getCode());
			break;
		case MProjectProgressStatus.OTHER:
			setContractProgress(mProjectProgressStatus.getCode());
			break;
		}
	}

	private MProjectProgressStatus toNextProgress(MProjectProgressStatus currentProgress) {
		String category = currentProgress.getCategory();
		Long projectCategoryId = currentProgress.getProjectCategoryId();
		Integer priority = currentProgress.getPriority();
		MProjectProgressStatus nextProgress = mProjectProgressStatusRepository
				.getNextProgress(category, projectCategoryId, priority).orElse(currentProgress);
		return nextProgress;
	}

	private void toNextInHouseProgress(MProjectProgressStatus currentProgress) {
		MProjectProgressStatus nextProgress = toNextProgress(currentProgress);

		if (currentProgress.getPriority() < nextProgress.getPriority()) {
			setInHouseProgress(nextProgress.getCode());
			setOfficeProjectProgressId(nextProgress.getId());
		}
	}

	private void toNextNegotiationProgress(MProjectProgressStatus currentProgress) {
		MProjectProgressStatus nextProgress = toNextProgress(currentProgress);

		if (currentProgress.getPriority() < nextProgress.getPriority()) {
			setNegotiationProgress(nextProgress.getCode());
			setNegotiationProjectProgressId(nextProgress.getId());
		}
	}

	private void toNextOtherProgress(MProjectProgressStatus currentProgress) {
		MProjectProgressStatus nextProgress = toNextProgress(currentProgress);

		if (currentProgress.getPriority() < nextProgress.getPriority()) {
			setContractProgress(nextProgress.getCode());
		}
	}

	private void upgradeStepByResult(Optional<MProjectProgressStatus> currentProgressOption, Long resultId) {
		currentProgressOption.ifPresent(currentProgress -> {
			Optional<MProjectMeetingResult> meetingResultOption = mProjectMeetingResultRespository.findById(resultId);
			meetingResultOption.ifPresent(meetingResult -> {
				String code = currentProgress.getCode();
				switch (code) {
				case MProjectProgressStatus.UNDER_CONSIDERATION:
				case MProjectProgressStatus.PROPERTY_CONSIDERATION_APPROVAL:
					Integer priority = meetingResult.getPriority();
					if (0 < priority && priority < 3) {
						toNextInHouseProgress(currentProgress);
					}
					break;
				default:
					toNextInHouseProgress(currentProgress);
					break;
				}
			});
		});
	}

	public void registerStartDate(LocalDate startDate) {
		if (startDate != null) {
			Optional<MProjectProgressStatus> currentProgressOption = mProjectProgressStatusRepository
					.getCurrentProgress(MProjectProgressStatus.COMPANY, getProjectCategoryId(),
							MProjectProgressStatus.BEFORE_CONSIDERATION);
			Optional<MProjectProgressStatus> current = mProjectProgressStatusRepository
					.findById(getOfficeProjectProgressId());

			currentProgressOption.ifPresent(currentProgress -> {
				current.ifPresent(c -> {
					if (currentProgress.getPriority() == c.getPriority()) {
						toNextInHouseProgress(currentProgress);
					}
				});
			});
			setStartDate(startDate);
		}
	}

	public void registerArticleReviewResult() {
		Optional<Long> resultOption = Optional.ofNullable(getArticleReviewResultId());
		resultOption.ifPresent(result -> {
			Optional<MProjectProgressStatus> currentProgressOption = mProjectProgressStatusRepository
					.getCurrentProgress(MProjectProgressStatus.COMPANY, getProjectCategoryId(),
							MProjectProgressStatus.UNDER_CONSIDERATION);
			Optional<MProjectProgressStatus> current = mProjectProgressStatusRepository
					.findById(getOfficeProjectProgressId());

			currentProgressOption.ifPresent(currentProgress -> {
				current.ifPresent(c -> {
					if (currentProgress.getPriority() == c.getPriority()) {
						upgradeStepByResult(currentProgressOption, result);
					}
				});
			});
		});
	}

	public void registerManagementResult() {
		Optional<Long> resultOption = Optional.ofNullable(getManagementResultId());
		resultOption.ifPresent(resultId -> {
			Optional<MProjectProgressStatus> currentProgressOption = mProjectProgressStatusRepository
					.getCurrentProgress(MProjectProgressStatus.COMPANY, getProjectCategoryId(),
							MProjectProgressStatus.PROPERTY_CONSIDERATION_APPROVAL);
			Optional<MProjectProgressStatus> current = mProjectProgressStatusRepository
					.findById(getOfficeProjectProgressId());

			currentProgressOption.ifPresent(currentProgress -> {
				current.ifPresent(c -> {
					if (currentProgress.getPriority() == c.getPriority() && getInvestmentDiscussionTarget() == false) {
						upgradeStepByResult(currentProgressOption, resultId);
					}
				});
			});
		});
	}

	private Boolean isAgreementResult(Integer priority) {
		return 0 < priority && priority < 3;
	}

	public void registerInvestmentResult() {
		Long managementResultId = Optional.ofNullable(getManagementResultId()).orElse(0L);
		Optional<MProjectMeetingResult> managementResultOption = mProjectMeetingResultRespository
				.findById(managementResultId);
		managementResultOption.ifPresent(managementResult -> {

			Long investmentResultId = Optional.ofNullable(getInvestmentProcessResultId()).orElse(0L);
			Optional<MProjectMeetingResult> investmentResultOption = mProjectMeetingResultRespository
					.findById(investmentResultId);
			investmentResultOption.ifPresent(investmentResult -> {
				if (isAgreementResult(managementResult.getPriority()) && getInvestmentDiscussionTarget()) {
					Optional<MProjectProgressStatus> currentProgressOption = mProjectProgressStatusRepository
							.getCurrentProgress(MProjectProgressStatus.COMPANY, getProjectCategoryId(),
									MProjectProgressStatus.PROPERTY_CONSIDERATION_APPROVAL);
					Optional<MProjectProgressStatus> current = mProjectProgressStatusRepository
							.findById(getOfficeProjectProgressId());

					currentProgressOption.ifPresent(currentProgress -> {
						current.ifPresent(c -> {
							if (currentProgress.getPriority() == c.getPriority()) {
								upgradeStepByResult(currentProgressOption, investmentResultId);
							}
						});
					});
				}
			});

		});
	}

	public void changeInvestmentDiscussionTargetFlg(Boolean flg) {
		if (MProjectProgressStatus.MANAGEMENT_MEETING_APPROVAL.equals(getInHouseProgress())) {
			if (getInvestmentDiscussionTarget() == false && getInvestmentDiscussionTarget() != flg) {
				backProgressPropertyConsiderationApproval();
			}
		}
		setInvestmentDiscussionTarget(flg);
	}

	private void backProgressPropertyConsiderationApproval() {
		setInHouseProgress(MProjectProgressStatus.PROPERTY_CONSIDERATION_APPROVAL);
	}

	public void registerProjectNegotiation() {
		Optional<MProjectProgressStatus> currentProgressOption = mProjectProgressStatusRepository.getCurrentProgress(
				MProjectProgressStatus.NEGOTIATION, getProjectCategoryId(), MProjectProgressStatus.BEFORE_NEGOTIATION);
		Optional<MProjectProgressStatus> current = mProjectProgressStatusRepository
				.findById(getNegotiationProjectProgressId());

		currentProgressOption.ifPresent(currentProgress -> {
			current.ifPresent(c -> {
				if (currentProgress.getPriority() == c.getPriority()) {
					toNextNegotiationProgress(currentProgress);
				}
			});
		});
	}

	public void registerTsubo(ProjectSectionProgress projectSectionProgress) {
		if (projectSectionProgress.getCategory() != null && projectSectionProgress.getCategory().equals("NEGOTIATION")
				&& projectSectionProgress.getContractTsubo() != null) {
			Optional<MProjectProgressStatus> currentProgressOption = mProjectProgressStatusRepository
					.getCurrentProgress(MProjectProgressStatus.OTHER, getProjectCategoryId(),
							MProjectProgressStatus.NOT_INPUT);
			currentProgressOption.ifPresent(currentProgress -> {
				toNextOtherProgress(currentProgress);
			});
		}
	}

	public void registerSection(ProjectSectionProgress projectSectionProgress) {
		if (projectSectionProgress.getCategory() != null && projectSectionProgress.getCategory().equals("NEGOTIATION")
				&& isEmpty(projectSectionProgress.getSection()) == false) {
			Optional<MProjectProgressStatus> currentProgressOption = mProjectProgressStatusRepository
					.getCurrentProgress(MProjectProgressStatus.OTHER, getProjectCategoryId(),
							MProjectProgressStatus.AREA_ENTERED);
			currentProgressOption.ifPresent(currentProgress -> {
				toNextOtherProgress(currentProgress);
			});
		}
	}

	public void checkOpeningDate(ProjectSectionProgress negotiationProgress) {
		String otherProgress = contractProgressCalculation(negotiationProgress);
		setContractProgress(otherProgress);
	}

	public void initProjectActionStatus(Long projectCategoryId, Long salesChannelId) {
		Optional<MProjectActionStatus> mProjectActionStatusOption = mProjectActionStatusRepository
				.getInitStatus(projectCategoryId, salesChannelId);
		mProjectActionStatusOption.ifPresent(mProjectActionStatus -> {
			setMProjectActionStatusId(mProjectActionStatus.getId());
			setActionStatus(mProjectActionStatus.getName());
		});
	}

	private Boolean isEmpty(String str) {
		if (str == null || str.isEmpty()) {
			return true;
		}
		return false;
	}

	private Integer matchCount(String status, String progress, Integer count) {
		if (isEmpty(status)) {
			count++;
		} else if (status.equals(progress)) {
			count++;
		}
		return count;
	}

	private Optional<MProjectActionStatus> statusMatching(MProjectActionStatus actionStatus, String inHouse,
			String negotiation, String other) {
		final Integer MAX_MATCH = 3;

		Integer count = 0;
		count = matchCount(actionStatus.getCompanyStatusCode(), inHouse, count);
		count = matchCount(actionStatus.getNegotiationStatusCode(), negotiation, count);
		count = matchCount(actionStatus.getOtherStatusCode(), other, count);

		if (count == MAX_MATCH) {
			return Optional.ofNullable(actionStatus);
		}
		return Optional.ofNullable(null);
	}

	public String inHouseProgress() {
		if (getOfficeProjectProgressId() != null) {
			Optional<MProjectProgressStatus> opt = new MProjectProgressStatusRepository()
					.findById(getOfficeProjectProgressId());
			if (opt.isPresent()) {
				return opt.get().getCode();
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	public String negotiationProgress() {
		if (getNegotiationProjectProgressId() != null) {
			Optional<MProjectProgressStatus> opt = new MProjectProgressStatusRepository()
					.findById(getNegotiationProjectProgressId());
			if (opt.isPresent()) {
				return opt.get().getCode();
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	private String contractProgress() {
		return getContractProgress();
	}

	private String contractProgress(ProjectSectionProgress model) {
		return contractProgressCalculation(model);
	}

	private String contractProgressCalculation(ProjectSectionProgress model) {
		BigDecimal tsubo = model.getContractTsubo();
		LocalDate date = getImplementationDatetime();
		Boolean isOpeningStore = false;
		ZoneId zoneId = ZoneId.systemDefault();
		LocalDate now = LocalDate.now();

		if (date != null) {
			isOpeningStore = date.atStartOfDay(zoneId).toEpochSecond() <= now.atStartOfDay(zoneId).toEpochSecond();
		}

		if (isOpeningStore && tsubo != null) {
			return "OPENING_STORE";
		} else if (tsubo != null) {
			return "AREA_ENTERED";
		} else {
			return "NOT_INPUT";
		}
	}

	private MProjectActionStatus calculateActionStatus(Long salesChannelId, ProjectSectionProgress model) {
		String inHouseProgress = inHouseProgress();
		String negotiationProgress = negotiationProgress();
		String otherProgress = "";
		if (model == null) {
			otherProgress = contractProgress();
		} else {
			otherProgress = contractProgress(model);
		}

		List<MProjectActionStatus> list = new MProjectActionStatusRepository().findSortDescBy(getProjectCategoryId(),
				getiSalesChannelId());

		if (list.size() == 0) {
			return null;
		}

		return new ActionStatusCalculationService().calculation(list, inHouseProgress, negotiationProgress,
				otherProgress);
	}

	public void registerActionStatus(Long salesChannelId) {
		MProjectActionStatus mProjectActionStatus = calculateActionStatus(salesChannelId, null);
		if (mProjectActionStatus != null) {
			setMProjectActionStatusId(mProjectActionStatus.getId());
			setActionStatus(mProjectActionStatus.getName());
		}
	}

	public void registerActionStatus(Long salesChannelId, ProjectSectionProgress model) {
		MProjectActionStatus mProjectActionStatus = calculateActionStatus(salesChannelId, model);
		if (mProjectActionStatus != null) {
			setMProjectActionStatusId(mProjectActionStatus.getId());
			setActionStatus(mProjectActionStatus.getName());
		}
	}

	public String getShopCd() {
		return shopCd;
	}

	public void setShopCd(String shopCd) {
		this.shopCd = shopCd;
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

	public void checkDifferenceColumns(ProjectUpdateDTO dto) {
		ProjectChangeConfirm projectChangeConfirm = new ProjectChangeConfirm(this, dto);
		projectChangeConfirm.check();
	}
}

class ProjectChangeConfirm {
	private Project newProject;
	private Project oldProject;
	private ProjectUpdateDTO updateDto;

	public ProjectChangeConfirm(Project newProject, ProjectUpdateDTO dto) {
		this.newProject = newProject;
		this.updateDto = dto;
		Optional<Project> oldProjectOption = new ProjectRepository().findById(newProject.getId());
		oldProjectOption.ifPresent(oldProject -> {
			this.oldProject = oldProject;
		});
	}

	private Boolean stringCompare(String val1, String val2) {
		Boolean result = false;
		if (!StringUtils.isEmpty(val1) || !StringUtils.isEmpty(val2)) {
			result = !StringUtils.equals(val1, val2);
		}
		return result;
	}

	private Boolean booleanCompare(Boolean newFlg, Boolean oldFlg) {
		Boolean newOptionalFlag = Optional.ofNullable(newFlg).orElseGet(() -> {
			return false;
		});
		Boolean oldOptionalFlag = Optional.ofNullable(oldFlg).orElseGet(() -> {
			return false;
		});
		return newOptionalFlag != oldOptionalFlag;
	}

	public Project check() {
		checkChangeOperationDivision();
		checkChangeMProjectActionStatusId();
		checkChangeOperationDivision();
		checkChangeOfficeProjectProgressId();
		checkChangeNegotiationProjectProgressId();
		checkChangeLandingProjectCategoryId();
		checkChangeConclusionPossibilityPercentId();
		checkChangeLandingMemo();
		checkChangeRentIncreaseDecrease();
		checkChangeExternalReleaseStartDate();
		checkChangeExternalReleaseConfirm();
		checkChangeImplementationDatetime();
		checkChangeSalesEndDate();
		checkChangeImplementationPeriod();
		checkChangeStopReasonId();
		checkChangeNegotiationSection();
		checkChangeNegotiationContract();
		checkChangeNegotiationRelated();
		checkChangeOpinion();
		checkChangeImplementationScheduleDatetime();
		return newProject;
	}

	private void checkChangeOperationDivision() {
		Boolean result = stringCompare(newProject.getOperationDivision(), oldProject.getOperationDivision());
		newProject.setIsChangedOperationDivision(result);
	}

	private void checkChangeLandingMemo() {
		Boolean result = stringCompare(newProject.getLandingMemo(), oldProject.getLandingMemo());
		newProject.setIsChangedLandingMemo(result);
	}

	private void checkChangeMProjectActionStatusId() {
		if (newProject.getMProjectActionStatusId() != null && oldProject.getMProjectActionStatusId() != null) {
			Boolean result = !newProject.getMProjectActionStatusId().equals(oldProject.getMProjectActionStatusId());
			newProject.setIsChangedMProjectActionStatusId(result);
		} else {
			Boolean result = newProject.getMProjectActionStatusId() != oldProject.getMProjectActionStatusId();
			newProject.setIsChangedMProjectActionStatusId(result);
		}
	}

	private void checkChangeOfficeProjectProgressId() {
		Boolean result = newProject.getOfficeProjectProgressId() != oldProject.getOfficeProjectProgressId();
		newProject.setIsChangedOfficeProjectProgressId(result);
	}

	private void checkChangeNegotiationProjectProgressId() {
		Boolean result = newProject.getNegotiationProjectProgressId() != oldProject.getNegotiationProjectProgressId();
		newProject.setIsChangedNegotiationProjectProgressId(result);
	}

	private void checkChangeLandingProjectCategoryId() {
		Boolean result = newProject.getLandingProjectCategoryId() != oldProject.getLandingProjectCategoryId();
		newProject.setIsChangedLandingProjectCategoryId(result);
	}

	private void checkChangeConclusionPossibilityPercentId() {
		Boolean result = newProject.getConclusionPossibilityPercentId() != oldProject
				.getConclusionPossibilityPercentId();
		newProject.setIsChangedConclusionPossibilityPercentId(result);
	}

	private void checkChangeRentIncreaseDecrease() {
		Boolean result = !StringUtils.equals(newProject.getRentIncreaseDecrease(),
				oldProject.getRentIncreaseDecrease());
		newProject.setIsChangedRentIncreaseDecrease(result);
	}

	private void checkChangeExternalReleaseStartDate() {
		if (newProject.getExternalReleaseStartDate() != null) {
			Boolean result = !newProject.getExternalReleaseStartDate().equals(oldProject.getExternalReleaseStartDate());
			newProject.setIsChangedExternalReleaseStartDate(result);
		} else if (oldProject.getExternalReleaseStartDate() != null) {
			Boolean result = !oldProject.getExternalReleaseStartDate().equals(newProject.getExternalReleaseStartDate());
			newProject.setIsChangedExternalReleaseStartDate(result);
		} else {
			newProject.setIsChangedExternalReleaseStartDate(false);
		}
	}

	private void checkChangeExternalReleaseConfirm() {
		Boolean result = booleanCompare(newProject.getExternalReleaseConfirm(), oldProject.getExternalReleaseConfirm());
		newProject.setIsChangedExternalReleaseConfirm(result);
	}

	private void checkChangeImplementationScheduleDatetime() {
		if (newProject.getImplementationScheduleDatetime() != null) {
			Boolean result = !newProject.getImplementationScheduleDatetime().equals(oldProject.getImplementationScheduleDatetime());
			newProject.setIsChangedImplementationScheduleDatetime(result);
		} else if (oldProject.getImplementationScheduleDatetime() != null) {
			Boolean result = !oldProject.getImplementationScheduleDatetime().equals(newProject.getImplementationScheduleDatetime());
			newProject.setIsChangedImplementationScheduleDatetime(result);
		} else {
			newProject.setIsChangedImplementationScheduleDatetime(false);
		}
	}
	
	private void checkChangeImplementationDatetime() {
		if (newProject.getImplementationDatetime() != null) {
			Boolean result = !newProject.getImplementationDatetime().equals(oldProject.getImplementationDatetime());
			newProject.setIsChangedImplementationDatetime(result);
		} else if (oldProject.getImplementationDatetime() != null) {
			Boolean result = !oldProject.getImplementationDatetime().equals(newProject.getImplementationDatetime());
			newProject.setIsChangedImplementationDatetime(result);
		} else {
			newProject.setIsChangedImplementationDatetime(false);
		}
	}
	
	private void checkChangeSalesEndDate() {
		if (newProject.getSalesEndDate() != null) {
			Boolean result = !newProject.getSalesEndDate().equals(oldProject.getSalesEndDate());
			newProject.setIsChangedSalesEndDate(result);
		} else if (oldProject.getSalesEndDate() != null) {
			Boolean result = !oldProject.getSalesEndDate().equals(newProject.getSalesEndDate());
			newProject.setIsChangedSalesEndDate(result);
		} else {
			newProject.setIsChangedSalesEndDate(false);
		}
	}

	private void checkChangeImplementationPeriod() {
		LocalDate newImplementationDate = updateDto.getImplementationDatetime();
		LocalDate oldImplementationDate = oldProject.getImplementationDatetime();
		Optional<MPeriod> newMPeriodOption = null;
		Optional<MPeriod> oldMPeriodOption = null;
		MPeriod newMPeriod = null;
		MPeriod oldMPeriod = null;
		if (newImplementationDate != null) {
			newMPeriodOption = new MPeriodRepository().getPeriod(newImplementationDate.toString());
			newMPeriod = newMPeriodOption.get();
		}
		if (oldImplementationDate != null) {
			oldMPeriodOption = new MPeriodRepository().getPeriod(oldImplementationDate.toString());
			oldMPeriod = oldMPeriodOption.get();
		}
		if (newMPeriod != null && oldMPeriod != null) {
			Boolean result = newMPeriod.getId() != oldMPeriod.getId();
			newProject.setIsChangedImplementationPeriod(result);
		} else if (newMPeriod != null) {
			Boolean result = newMPeriod.getId() != null;
			newProject.setIsChangedImplementationPeriod(result);
		} else if (oldMPeriod != null) {
			Boolean result = oldMPeriod.getId() != null;
			newProject.setIsChangedImplementationPeriod(result);
		} else {
			newProject.setIsChangedImplementationPeriod(false);
		}
	}

	private void checkChangeStopReasonId() {
		Boolean result = newProject.getStopReasonId() != oldProject.getStopReasonId();
		newProject.setIsChangedStopReasonId(result);
	}

	private void checkChangeNegotiationSection() {
		Optional<ProjectSectionProgress> oldNegotiationSectionOption = new ProjectSectionProgressRepository()
				.getNegotiationSectionProgressByProjectId(oldProject.getId());
		ProjectSectionProgress oldNegotiationSection = oldNegotiationSectionOption.orElse(new ProjectSectionProgress());
		ProjectSectionProgress newCurrentSection = null;
		ProjectSectionProgress newNegotiationSection = null;
		for (Iterator<ProjectSectionProgress> itr = updateDto.getProjectSectionProgressDto().iterator(); itr
				.hasNext();) {
			ProjectSectionProgress dto = itr.next();
			if (dto.getCategory().equals("NEGOTIATION")) {
				checkChangeNegotiationSectionFloor(dto, oldNegotiationSection);
				checkChangeNegotiationSectionContractTsubo(dto, oldNegotiationSection);
				checkChangeNegotiationSectionBusinessHours(dto, oldNegotiationSection);
				checkChangeNegotiationSectionMemo(dto, oldNegotiationSection);
				newNegotiationSection = dto;
			} else {
				newCurrentSection = dto;
			}
		}
		checkChangeNegotiationSectionTsuboIncreaseDecrease(newNegotiationSection, newCurrentSection,
				oldNegotiationSection);
	}

	private void checkChangeNegotiationSectionFloor(ProjectSectionProgress updateDto,
		ProjectSectionProgress oldNegotiationSection) {
		Boolean result = stringCompare(updateDto.getFloor(), oldNegotiationSection.getFloor());
		newProject.setIsChangedNegotiationSectionFloor(result);
	}

	private void checkChangeNegotiationSectionContractTsubo(ProjectSectionProgress updateDto,
			ProjectSectionProgress oldNegotiationSection) {
		if (updateDto.getContractTsubo() != null && oldNegotiationSection.getContractTsubo() != null) {
			Boolean result = updateDto.getContractTsubo().compareTo(oldNegotiationSection.getContractTsubo()) != 0;
			newProject.setIsChangedNegotiationSectionContractTsubo(result);
		} else {
			Boolean result = updateDto.getContractTsubo() != oldNegotiationSection.getContractTsubo();
			newProject.setIsChangedNegotiationSectionContractTsubo(result);
		}
	}

	private void checkChangeNegotiationSectionTsuboIncreaseDecrease(ProjectSectionProgress newNegotiationSection,
		ProjectSectionProgress newCurrentSection, ProjectSectionProgress oldNegotiationSection) {
		Optional<ProjectSectionProgress> currentSectionOption = new ProjectSectionProgressRepository()
				.getCurrentSectionProgressByProjectId(oldProject.getId());
		ProjectSectionProgress currentSection = currentSectionOption.orElse(new ProjectSectionProgress());
		Boolean result = false;
		// 減 -1, 同 0, 増 1
		Integer oldIncreaseDecrease = null;
		Integer newIncreaseDecrease = null;
		if (currentSection != null && currentSection.getContractTsubo() != null && oldNegotiationSection != null
				&& oldNegotiationSection.getContractTsubo() != null) {
			float changeValue = oldNegotiationSection.getContractTsubo().floatValue()
					- currentSection.getContractTsubo().floatValue();
			if (changeValue > 0) {
				oldIncreaseDecrease = 1;
			} else if (changeValue < 0) {
				oldIncreaseDecrease = -1;
			} else {
				oldIncreaseDecrease = 0;
			}
		}
		if (currentSection != null && currentSection.getContractTsubo() != null && newNegotiationSection != null
				&& newNegotiationSection.getContractTsubo() != null) {
			float changeValue = newNegotiationSection.getContractTsubo().floatValue()
					- currentSection.getContractTsubo().floatValue();
			if (changeValue > 0) {
				newIncreaseDecrease = 1;
			} else if (changeValue < 0) {
				newIncreaseDecrease = -1;
			} else {
				newIncreaseDecrease = 0;
			}
		}
		result = oldIncreaseDecrease != newIncreaseDecrease;
		newProject.setIsChangedNegotiationSectionTsuboIncreaseDecrease(result);
	}

	private void checkChangeNegotiationSectionBusinessHours(ProjectSectionProgress updateDto,
		ProjectSectionProgress oldNegotiationSection) {
		Boolean result = stringCompare(updateDto.getBusinessHours(), oldNegotiationSection.getBusinessHours());
		newProject.setIsChangedNegotiationSectionBusinessHours(result);
	}

	private void checkChangeNegotiationSectionMemo(ProjectSectionProgress updateDto,
		ProjectSectionProgress oldNegotiationSection) {
		Boolean result = stringCompare(updateDto.getMemo(), oldNegotiationSection.getMemo());
		newProject.setIsChangedNegotiationSectionMemo(result);
	}

	private void checkChangeNegotiationContract() {
		Optional<ProjectContractProgress> negotiationContractOption = new ProjectContractProgressRepository()
				.getProgressContractByProjectId(oldProject.getId());
		ProjectContractProgress negotiationContract = negotiationContractOption.orElse(new ProjectContractProgress());
		updateDto.getProjectContractProgressDto().forEach(dto -> {
			if (dto.getCategory().equals("PROGRESS")) {
				checkChangeNegotiationContractForm(dto, negotiationContract);
				checkChangeNegotiationContractName(dto, negotiationContract);
				checkChangeNegotiationContractStartDate(dto, negotiationContract);
				checkChangeNegotiationContractEndDate(dto, negotiationContract);
				checkChangeNegotiationContractYear(dto, negotiationContract);
				checkChangeNegotiationContractTenancyPeriod(dto, negotiationContract);
				checkChangeNegotiationContractAutoUpdate(dto, negotiationContract);
				checkChangeNegotiationContractMemo(dto, negotiationContract);
			}
		});
	}

	private void checkChangeNegotiationContractForm(ProjectContractProgress updateDto,
		ProjectContractProgress negotiationContract) {
		Boolean result = stringCompare(updateDto.getForm(), negotiationContract.getForm());
		newProject.setIsChangedNegotiationContractForm(result);
	}

	private void checkChangeNegotiationContractName(ProjectContractProgress updateDto,
		ProjectContractProgress negotiationContract) {
		Boolean result = stringCompare(updateDto.getContractTargetName(), negotiationContract.getContractTargetName());
		newProject.setIsChangedNegotiationContractName(result);
	}

	private void checkChangeNegotiationContractStartDate(ProjectContractProgress updateDto,
		ProjectContractProgress negotiationContract) {
		if (updateDto.getContractStartDate() != null) {
			Boolean result = !updateDto.getContractStartDate().equals(negotiationContract.getContractStartDate());
			newProject.setIsChangedNegotiationContractStartDate(result);
		} else if (negotiationContract.getContractStartDate() != null) {
			Boolean result = !negotiationContract.getContractStartDate().equals(updateDto.getContractStartDate());
			newProject.setIsChangedNegotiationContractStartDate(result);
		} else {
			newProject.setIsChangedNegotiationContractStartDate(false);
		}
	}

	private void checkChangeNegotiationContractEndDate(ProjectContractProgress updateDto,
			ProjectContractProgress negotiationContract) {
		if (updateDto.getContractEndDate() != null) {
			Boolean result = !updateDto.getContractEndDate().equals(negotiationContract.getContractEndDate());
			newProject.setIsChangedNegotiationContractEndDate(result);
		} else if (negotiationContract.getContractEndDate() != null) {
			Boolean result = !negotiationContract.getContractEndDate().equals(updateDto.getContractEndDate());
			newProject.setIsChangedNegotiationContractEndDate(result);
		} else {
			newProject.setIsChangedNegotiationContractEndDate(false);
		}
	}

	private void checkChangeNegotiationContractYear(ProjectContractProgress updateDto, ProjectContractProgress negotiationContract) {
		if (updateDto.getContractNumberOfYear() != null && negotiationContract.getContractNumberOfYear() != null) {
			BigDecimal val1 = new BigDecimal(updateDto.getContractNumberOfYear()).setScale(2, BigDecimal.ROUND_DOWN);
			BigDecimal val2 = new BigDecimal(negotiationContract.getContractNumberOfYear()).setScale(2, BigDecimal.ROUND_DOWN);
			Boolean result = !val1.equals(val2);
			newProject.setIsChangedNegotiationContractYear(result);
		} else {
			Boolean result = updateDto.getContractNumberOfYear() != negotiationContract.getContractNumberOfYear();
			newProject.setIsChangedNegotiationContractYear(result);
		}
	}

	private void checkChangeNegotiationContractTenancyPeriod(ProjectContractProgress updateDto,
			ProjectContractProgress negotiationContract) {
		LocalDate newContractEndDate = updateDto.getContractEndDate();
		LocalDate oldContractEndDate = negotiationContract.getContractEndDate();
		Optional<MPeriod> newMPeriodOption = null;
		Optional<MPeriod> oldMPeriodOption = null;
		MPeriod newMPeriod = null;
		MPeriod oldMPeriod = null;
		if (newContractEndDate != null) {
			newMPeriodOption = new MPeriodRepository().getPeriod(newContractEndDate.toString());
			newMPeriod = newMPeriodOption.orElse(new MPeriod());
		}
		if (oldContractEndDate != null) {
			oldMPeriodOption = new MPeriodRepository().getPeriod(oldContractEndDate.toString());
			oldMPeriod = oldMPeriodOption.orElse(new MPeriod());
		}
		if (newMPeriod != null && oldMPeriod != null) {
			Boolean result = oldMPeriod.getId() != newMPeriod.getId();
			newProject.setIsChangedNegotiationContractTenancyPeriod(result);
		} else if (newMPeriod != null) {
			Boolean result = newMPeriod.getId() != null;
			newProject.setIsChangedNegotiationContractTenancyPeriod(result);
		} else if (oldMPeriod != null) {
			Boolean result = oldMPeriod.getId() != null;
			newProject.setIsChangedNegotiationContractTenancyPeriod(result);
		} else {
			newProject.setIsChangedNegotiationContractTenancyPeriod(false);
		}
	}

	private void checkChangeNegotiationContractAutoUpdate(ProjectContractProgress updateDto,
			ProjectContractProgress negotiationContract) {
		if (updateDto.getAutoUpdate() != null) {
			Boolean result = !updateDto.getAutoUpdate().equals(negotiationContract.getAutoUpdate());
			newProject.setIsChangedNegotiationContractAutoUpdate(result);
		} else if (negotiationContract.getAutoUpdate() != null) {
			Boolean result = !negotiationContract.getAutoUpdate().equals(updateDto.getAutoUpdate());
			newProject.setIsChangedNegotiationContractAutoUpdate(result);
		} else {
			newProject.setIsChangedNegotiationContractAutoUpdate(false);
		}
	}

	private void checkChangeNegotiationContractMemo(ProjectContractProgress updateDto,
		ProjectContractProgress negotiationContract) {
		Boolean result = stringCompare(updateDto.getMemo(), negotiationContract.getMemo());
		newProject.setIsChangedNegotiationContractMemo(result);
	}

	private void checkChangeNegotiationRelated() {
		if (updateDto.getProjectPlanDto() != null) {
			Optional<ProjectPlan> negotiationPlanOption = new ProjectPlanRepository()
					.findProgressByProjectId(oldProject.getId());
			ProjectPlan negotiationPlan = negotiationPlanOption.orElse(new ProjectPlan());
			updateDto.getProjectPlanDto().forEach(dto -> {
				if (dto.getCategory().equals("PROGRESS")) {
					checkChangeNegotiationRelatedAgency(dto, negotiationPlan);
					checkChangeNegotiationRelatedAgencyStartDate(dto, negotiationPlan);
					checkChangeNegotiationRelatedAgencyEndDate(dto, negotiationPlan);
					checkChangeNegotiationRelatedAgencyYear(dto, negotiationPlan);
					checkChangeNegotiationRelatedAgencyAffiliateShop(dto, negotiationPlan);
				}
			});
		}
	}

	private void checkChangeNegotiationRelatedAgency(ProjectPlan updateDto, ProjectPlan negotiationPlan) {
		Boolean result = updateDto.getSalesAgencyTargetId() != negotiationPlan.getSalesAgencyTargetId();
		newProject.setIsChangedNegotiationRelatedAgency(result);
	}

	private void checkChangeNegotiationRelatedAgencyStartDate(ProjectPlan updateDto, ProjectPlan negotiationPlan) {
		if (updateDto.getStartDate() != null) {
			Boolean result = !updateDto.getStartDate().equals(negotiationPlan.getStartDate());
			newProject.setIsChangedNegotiationRelatedAgencyStartDate(result);
		} else if (negotiationPlan.getStartDate() != null) {
			Boolean result = !negotiationPlan.getStartDate().equals(updateDto.getStartDate());
			newProject.setIsChangedNegotiationRelatedAgencyStartDate(result);
		} else {
			newProject.setIsChangedNegotiationRelatedAgencyStartDate(false);
		}
	}

	private void checkChangeNegotiationRelatedAgencyEndDate(ProjectPlan updateDto, ProjectPlan negotiationPlan) {
		if (updateDto.getEndDate() != null) {
			Boolean result = !updateDto.getEndDate().equals(negotiationPlan.getEndDate());
			newProject.setIsChangedNegotiationRelatedAgencyEndDate(result);
		} else if (negotiationPlan.getEndDate() != null) {
			Boolean result = !negotiationPlan.getEndDate().equals(updateDto.getEndDate());
			newProject.setIsChangedNegotiationRelatedAgencyEndDate(result);
		} else {
			newProject.setIsChangedNegotiationRelatedAgencyEndDate(false);
		}
	}

	private void checkChangeNegotiationRelatedAgencyYear(ProjectPlan updateDto, ProjectPlan negotiationPlan) {
		if (updateDto.getNumberOfYear() != null && negotiationPlan.getNumberOfYear() != null) {
			BigDecimal val1 = new BigDecimal(updateDto.getNumberOfYear()).setScale(2, BigDecimal.ROUND_DOWN);
			BigDecimal val2 = new BigDecimal(negotiationPlan.getNumberOfYear()).setScale(2, BigDecimal.ROUND_DOWN);
			Boolean result = !val1.equals(val2);
			newProject.setIsChangedNegotiationRelatedAgencyYear(result);
		} else {
			Boolean result = updateDto.getNumberOfYear() != negotiationPlan.getNumberOfYear();
			newProject.setIsChangedNegotiationRelatedAgencyYear(result);
		}
	}

	private void checkChangeNegotiationRelatedAgencyAffiliateShop(ProjectPlan updateDto, ProjectPlan negotiationPlan) {
		Boolean result = updateDto.getParticipatingStoreCorporationId() != negotiationPlan
				.getParticipatingStoreCorporationId();
		newProject.setIsChangedNegotiationRelatedAffiliateShop(result);
	}

	private void checkChangeOpinion() {
		updateDto.getProjectOpinionDto().forEach(opinion -> {
			if (opinion.getCategory().equals("BRANCH")) {
				checkChangeBranchStoreStaffOpinion(opinion);
			} else if (opinion.getCategory().equals("VIEW")) {
				checkChangeBranchStoreOpinion(opinion);
			} else if (opinion.getCategory().equals("BU")) {
				checkChangeBuOpinion(opinion);
			}
		});
	}

	private Boolean isChangeOpinionUploadFile(ProjectOpinion newOpinion, ProjectOpinion oldOpinion) {
		Boolean result = false;
		Optional<ProjectFile> oldFileOption = new ProjectFileRepository().findById(oldOpinion.getFileId());
		if (oldFileOption.isPresent()) {
			ProjectFile oldFile = oldFileOption.get();
			result = newOpinion.getFormFile().getSize() != oldFile.getSize();
		} else {
			result = true;
		}
		return result;
	}

	private Boolean isChangeOpinionComment(ProjectOpinion newOpinion, ProjectOpinion oldOpinion) {
		return stringCompare(newOpinion.getComment(), oldOpinion.getComment());
	}

	private void checkChangeBranchStoreStaffOpinion(ProjectOpinion newOpinion) {
		Boolean result = false;
		Optional<ProjectOpinion> oldOpinionOption = new ProjectOpinionRepository().getBranchOpinion(oldProject.getId());
		ProjectOpinion oldOpinion = oldOpinionOption.orElse(new ProjectOpinion());
		if (newOpinion.getFormFile() != null) {
			Boolean isChangeFile = isChangeOpinionUploadFile(newOpinion, oldOpinion);
			Boolean isChangeComment = isChangeOpinionComment(newOpinion, oldOpinion);
			result = isChangeFile || isChangeComment;
			newProject.setIsChangedBranchStoreStaffOpinion(result);
		} else {
			result = isChangeOpinionComment(newOpinion, oldOpinion);
			newProject.setIsChangedBranchStoreStaffOpinion(result);
		}
	}

	private void checkChangeBranchStoreOpinion(ProjectOpinion newOpinion) {
		Boolean result = false;
		Optional<ProjectOpinion> oldOpinionOption = new ProjectOpinionRepository().getViewOpinion(oldProject.getId());
		ProjectOpinion oldOpinion = oldOpinionOption.orElse(new ProjectOpinion());
		if (newOpinion.getFormFile() != null) {
			Boolean isChangeFile = isChangeOpinionUploadFile(newOpinion, oldOpinion);
			Boolean isChangeComment = isChangeOpinionComment(newOpinion, oldOpinion);
			result = isChangeFile || isChangeComment;
			newProject.setIsChangedBranchStoreOpinion(result);
		} else {
			result = isChangeOpinionComment(newOpinion, oldOpinion);
			newProject.setIsChangedBranchStoreOpinion(result);
		}
	}

	private void checkChangeBuOpinion(ProjectOpinion newOpinion) {
		Boolean result = false;
		Optional<ProjectOpinion> oldOpinionOption = new ProjectOpinionRepository().getBuOpinion(oldProject.getId());
		ProjectOpinion oldOpinion = oldOpinionOption.orElse(new ProjectOpinion());
		if (newOpinion.getFormFile() != null) {
			Boolean isChangeFile = isChangeOpinionUploadFile(newOpinion, oldOpinion);
			Boolean isChangeComment = isChangeOpinionComment(newOpinion, oldOpinion);
			result = isChangeFile || isChangeComment;
			newProject.setIsChangedBuOpinion(result);
		} else {
			result = isChangeOpinionComment(newOpinion, oldOpinion);
			newProject.setIsChangedBuOpinion(result);
		}
	}
}
