package jp.co.world.storedevelopment.sp.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.MPeriod;
import jp.co.world.storedevelopment.model.MProjectActionStatus;
import jp.co.world.storedevelopment.model.MProjectProgressStatus;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.ProjectCategory;
import jp.co.world.storedevelopment.model.ProjectClassification;
import jp.co.world.storedevelopment.model.ProjectContractProgress;
import jp.co.world.storedevelopment.model.mapper.repository.MPeriodRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MProjectProgressStatusRepository;
import jp.co.world.storedevelopment.model.mapper.repository.OtherProjectAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectCategoryRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectClassificationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectReadLaterAccountRepository;

public class ProjectListDTO implements DTO<Project> {
	private static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");

	private Long id;
	private String title;
	private Long projectCategoryId;
	private Long officeProjectProgressId;
	private Long negotiationProjectProgressId;
	private String inHouseProgressStatus;
	private String negotiationProgressStatus;
	private Long mActionStatusId;
	private Boolean externalReleaseConfirm;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate externalReleaseStartDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate implementationDatetime;
	private Long conclusionPossibilityPercentId;
	private Long landingProjectCategoryId;
	private BigDecimal profitRate;
	private Boolean isReadLater;
	private String actionStatus;
	private String contractCurrentEndDate;
	private String contractCurrentPeriod = "";
	private Boolean contractCurrentAutoUpdate;
	private String contractProgressEndDate;
	private String contractProgressPeriod = "";
	private Integer planPeriod;
	private String planPeriodHalf;
	private Boolean open;
	private String operationDivision;
	private String actionStatusProgress;
	private Boolean isChangedOperationDivision;
	private Boolean isChangedOfficeProjectProgressId;
	private Boolean isChangedNegotiationProjectProgressId;
	private Boolean isChangedExternalReleaseStartDate;
	private Boolean isChangedExternalReleaseConfirm;
	private Boolean isChangedLandingProjectCategoryId;
	private Boolean isChangedConclusionPossibilityPercentId;
	private Boolean isChangedImplementationDatetime;
	private Boolean isChangedNegotiationContractEndDate;
	private Boolean printLanding;
	private String implementationDateValue;
	private Boolean isChangedImplementationScheduleDatetime;

	@Override
	public Project createModel() {
		return new Project();
	}

	public ProjectListDTO() {
		//
	}

	public ProjectListDTO(Project project) {
		copyProperties(this, project);
	}

	public ProjectListDTO(Project project, Account account) {
		setIsReadLater(new ProjectReadLaterAccountRepository().isReadLater(project, account));
		copyProperties(this, project);

		Optional<MProjectActionStatus> model = project.actionStatus();
		Optional<MProjectProgressStatus> inHouse = project.inHouseProgressStatus();
		Optional<MProjectProgressStatus> negotiation = project.negotiationStatus();
		Optional<ProjectContractProgress> contractCurrent = project.currentContractProgress();
		Optional<ProjectContractProgress> contractProgress = project.negotiationContractProgress();
		model.ifPresent(m -> {
			setActionStatus(m.getName());
		});

		inHouse.ifPresent(m -> {
			setInHouseProgressStatus(m.getName());
		});

		negotiation.ifPresent(m -> {
			setNegotiationProgressStatus(m.getName());
			setActionStatusProgress(m.getCode());
		});

		contractCurrent.ifPresent(contract -> {
			if (contract.getContractEndDate() != null) {
				setContractCurrentEndDate(DATE_FORMAT.format(contract.getContractEndDate()));

				Optional<MPeriod> opt = new MPeriodRepository()
						.getPeriod(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(contract.getContractEndDate()));
				if (opt.isPresent()) {
					if (contract.getContractEndDate().isBefore(opt.get().getSecondHalfStartDate())) {
						setContractCurrentPeriod(opt.get().getPeriod() + "期 上期");
					} else {
						setContractCurrentPeriod(opt.get().getPeriod() + "期 下期");
					}
				}
			} else {
				setContractCurrentEndDate("");
			}
			setContractCurrentAutoUpdate(contract.getAutoUpdate());
		});

		contractProgress.ifPresent(contract -> {
			if (contract.getContractEndDate() != null) {
				setContractProgressEndDate(DATE_FORMAT.format(contract.getContractEndDate()));

				Optional<MPeriod> opt = new MPeriodRepository()
						.getPeriod(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(contract.getContractEndDate()));
				if (opt.isPresent()) {
					if (contract.getContractEndDate().isBefore(opt.get().getSecondHalfStartDate())) {
						setContractProgressPeriod(opt.get().getPeriod() + "期 上期");
					} else {
						setContractProgressPeriod(opt.get().getPeriod() + "期 下期");
					}
				}
			} else {
				setContractProgressEndDate("");
			}

		});

		if (project.getPlanPeriod() != null) {
			setPlanPeriod(project.getPlanPeriod());
		}
		if (project.getPlanPeriodHalf() != null) {
			setPlanPeriodHalf(project.getPlanPeriodHalf());
		}
		if (project.getOpen() != null) {
			setOpen(project.getOpen());
		}
		if (project.getOperationDivision() != null) {
			setOperationDivision(project.getOperationDivision());
		}

		setImplementationDateValue(project.implemenrationDateValue());
		setPrintLanding(project.category().printLanding());
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

	public Long getMActionStatusId() {
		return mActionStatusId;
	}

	public void setMActionStatusId(Long mActionStatusId) {
		this.mActionStatusId = mActionStatusId;
	}

	public String getDisplayExternalReleaseConfirm() {
		if (this.externalReleaseConfirm != null && this.externalReleaseConfirm == true) {
			return "確定";
		} else if (getExternalReleaseStartDate() != null) {
			return getExternalReleaseStartDate() + "頃";
		} else {
			return "";
		}
	}

	public Boolean getExternalReleaseConfirm() {
		return externalReleaseConfirm;
	};

	public void setExternalReleaseConfirm(Boolean externalReleaseConfirm) {
		this.externalReleaseConfirm = externalReleaseConfirm;
	}

	public LocalDate getExternalReleaseStartDate() {
		return externalReleaseStartDate;
	}

	public void setExternalReleaseStartDate(LocalDate externalReleaseStartDate) {
		this.externalReleaseStartDate = externalReleaseStartDate;
	}

	public LocalDate getImplementationDatetime() {
		return implementationDatetime;
	}

	public void setImplementationDatetime(LocalDate implementationDatetime) {
		this.implementationDatetime = implementationDatetime;
	}

	public Long getConclusionPossibilityPercentId() {
		return conclusionPossibilityPercentId;
	}

	public void setConclusionPossibilityPercentId(Long conclusionPossibilityPercentId) {
		this.conclusionPossibilityPercentId = conclusionPossibilityPercentId;
	}

	public Long getLandingProjectCategoryId() {
		return landingProjectCategoryId;
	}

	public void setLandingProjectCategoryId(Long landingProjectCategoryId) {
		this.landingProjectCategoryId = landingProjectCategoryId;
	}

	public BigDecimal getProfitRate() {
		return profitRate;
	}

	public void setProfitRate(BigDecimal profitRate) {
		this.profitRate = profitRate;
	}

	public Boolean getIsReadLater() {
		return isReadLater;
	}

	public void setIsReadLater(Boolean isReadLater) {
		this.isReadLater = isReadLater;
	}

	public ProjectCategory getProjectCategory() {
		if (getProjectCategoryId() == null) {
			return null;
		}

		Optional<ProjectCategory> projectCategory = new ProjectCategoryRepository().findById(getProjectCategoryId());
		return projectCategory.isPresent() ? projectCategory.get() : null;
	}

	public String getProjectLandingCategory() {
		if (getLandingProjectCategoryId() == null) {
			return null;
		}

		Optional<ProjectClassification> classification = new ProjectClassificationRepository()
				.findById(getLandingProjectCategoryId());
		return classification.isPresent() ? classification.get().getName() : null;
	}

	public String getProjectClassificationChild() {
		if (getLandingProjectCategoryId() == null) {
			return null;
		}

		ProjectClassification classification = new ProjectClassificationRepository()
				.getLandingClassificationChild(getLandingProjectCategoryId());
		if (classification != null) {
			return classification.getName();
		}
		return null;
	}

	public String getProjectClassificationCategoryShortName() {
		if (getProjectCategoryId() == null) {
			return null;
		}

		List<ProjectClassification> classification = new ProjectClassificationRepository()
				.getClassificationCategoryShortName(getProjectCategoryId());
		if (classification.size() > 0) {
			return classification.get(0).getName();
		}
		return null;
	}

	public String getProjectProgressOffice() {
		if (getOfficeProjectProgressId() == null) {
			return null;
		}

		Optional<MProjectProgressStatus> projectProgress = new MProjectProgressStatusRepository()
				.findById(getOfficeProjectProgressId());
		return projectProgress.isPresent() ? projectProgress.get().getName() : null;
	}

	public String getProjectProgressNegotiation() {
		if (getNegotiationProjectProgressId() == null) {
			return null;
		}

		Optional<MProjectProgressStatus> projectProgress = new MProjectProgressStatusRepository()
				.findById(getNegotiationProjectProgressId());
		return projectProgress.isPresent() ? projectProgress.get().getName() : null;
	}

	public String getInHouseProgressStatus() {
		if (inHouseProgressStatus != null && inHouseProgressStatus.isEmpty() == false) {
			return inHouseProgressStatus;
		} else {
			return "-";
		}
	}

	public void setInHouseProgressStatus(String inHouseProgressStatus) {
		this.inHouseProgressStatus = inHouseProgressStatus;
	}

	public String getNegotiationProgressStatus() {
		if (negotiationProgressStatus != null && negotiationProgressStatus.isEmpty() == false) {
			return negotiationProgressStatus;
		} else {
			return "-";
		}
	}

	public void setNegotiationProgressStatus(String negotiationProgressStatus) {
		this.negotiationProgressStatus = negotiationProgressStatus;
	}

	public Long getmActionStatusId() {
		return mActionStatusId;
	}

	public void setmActionStatusId(Long mActionStatusId) {
		this.mActionStatusId = mActionStatusId;
	}

	public String getConclusionPossibilityPercent() {
		if (conclusionPossibilityPercentId != null) {
			Optional<ProjectClassification> classification = new ProjectClassificationRepository()
					.findById(conclusionPossibilityPercentId);
			if (classification.isPresent()) {
				return classification.get().getName();
			}
		}
		return null;
	}

	public String getActionStatus() {
		if (actionStatus != null && actionStatus.isEmpty() == false) {
			return actionStatus;
		} else {
			return null;
		}
	}

	public void setActionStatus(String actionStatus) {
		this.actionStatus = actionStatus;
	}

	public String getContractCurrentEndDate() {
		return contractCurrentEndDate;
	}

	public void setContractCurrentEndDate(String contractCurrentEndDate) {
		if (contractCurrentEndDate == "") {
			this.contractCurrentEndDate = null;
		} else {
			this.contractCurrentEndDate = contractCurrentEndDate;
		}
	}

	public String getContractCurrentPeriod() {
		return contractCurrentPeriod;
	}

	public void setContractCurrentPeriod(String contractCurrentPeriod) {
		this.contractCurrentPeriod = contractCurrentPeriod;
	}

	public Boolean getContractCurrentAutoUpdate() {
		return contractCurrentAutoUpdate;
	}

	public void setContractCurrentAutoUpdate(Boolean contractCurrentAutoUpdate) {
		this.contractCurrentAutoUpdate = contractCurrentAutoUpdate;
	}

	public String getContractProgressEndDate() {
		return contractProgressEndDate;
	}

	public void setContractProgressEndDate(String contractProgressEndDate) {
		if (contractProgressEndDate == "") {
			this.contractProgressEndDate = null;
		} else {
			this.contractProgressEndDate = contractProgressEndDate;
		}
	}

	public String getContractProgressPeriod() {
		return contractProgressPeriod;
	}

	public void setContractProgressPeriod(String contractProgressPeriod) {
		this.contractProgressPeriod = contractProgressPeriod;
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

	public String getAccount() {
		List<Account> account = new OtherProjectAccountRepository().findStoreByProjectId(getId());
		if (account.size() > 0) {
			String accountName = "";
			for (Account acc : account) {
				if (acc.getFullName() != "" && acc.getFullName() != null) {
					accountName += acc.getFullName() + "/";
				}
			}
			accountName = accountName.substring(0, accountName.length() - 1);
			if (accountName != null && accountName != "") {
				return accountName;
			}
		}
		return null;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public String getOperationDivision() {
		return operationDivision;
	}

	public void setOperationDivision(String operationDivision) {
		this.operationDivision = operationDivision;
	}

	public String getActionStatusProgress() {
		return actionStatusProgress;
	}

	public void setActionStatusProgress(String actionStatusProgress) {
		this.actionStatusProgress = actionStatusProgress;
	}

	public String getProjectClassificationColor() {
		if (this.projectCategoryId != null) {
			ProjectClassification pc = new ProjectClassificationRepository()
					.getClassificationColor(this.projectCategoryId);
			if (pc != null) {
				return pc.getName();
			}
		}
		return null;
	}

	public Boolean getIsChangedOperationDivision() {
		return isChangedOperationDivision;
	}

	public void setIsChangedOperationDivision(Boolean isChangedOperationDivision) {
		this.isChangedOperationDivision = isChangedOperationDivision;
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

	public Boolean getIsChangedLandingProjectCategoryId() {
		return isChangedLandingProjectCategoryId;
	}

	public void setIsChangedLandingProjectCategoryId(Boolean isChangedLandingProjectCategoryId) {
		this.isChangedLandingProjectCategoryId = isChangedLandingProjectCategoryId;
	}

	public Boolean getIsChangedImplementationDatetime() {
		return isChangedImplementationDatetime;
	}

	public void setIsChangedImplementationDatetime(Boolean isChangedImplementationDatetime) {
		this.isChangedImplementationDatetime = isChangedImplementationDatetime;
	}

	public Boolean getIsChangedNegotiationContractEndDate() {
		return isChangedNegotiationContractEndDate;
	}

	public void setIsChangedNegotiationContractEndDate(Boolean isChangedNegotiationContractEndDate) {
		this.isChangedNegotiationContractEndDate = isChangedNegotiationContractEndDate;
	}

	public Boolean getPrintLanding() {
		return printLanding;
	}

	public void setPrintLanding(Boolean printLanding) {
		this.printLanding = printLanding;
	}

	public String getImplementationDateValue() {
		return implementationDateValue;
	}

	public void setImplementationDateValue(String implementationDateValue) {
		this.implementationDateValue = implementationDateValue;
	}

	public Boolean getIsChangedConclusionPossibilityPercentId() {
		return isChangedConclusionPossibilityPercentId;
	}

	public void setIsChangedConclusionPossibilityPercentId(Boolean isChangedConclusionPossibilityPercentId) {
		this.isChangedConclusionPossibilityPercentId = isChangedConclusionPossibilityPercentId;
	}

	public Boolean getIsChangedImplementationScheduleDatetime() {
		return isChangedImplementationScheduleDatetime;
	}

	public void setIsChangedImplementationScheduleDatetime(Boolean isChangedImplementationScheduleDatetime) {
		this.isChangedImplementationScheduleDatetime = isChangedImplementationScheduleDatetime;
	}

}
