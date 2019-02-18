package jp.co.world.storedevelopment.pc.controller.view.model;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

import com.world.storedevelopment.utils.ZenkakuUtils;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.IArea;
import jp.co.world.storedevelopment.model.IBlock;
import jp.co.world.storedevelopment.model.IBrandIncomeUnit;
import jp.co.world.storedevelopment.model.ICorporation;
import jp.co.world.storedevelopment.model.ISalesAgencyTarget;
import jp.co.world.storedevelopment.model.ISalesChannel;
import jp.co.world.storedevelopment.model.IShop;
import jp.co.world.storedevelopment.model.MPeriod;
import jp.co.world.storedevelopment.model.MProjectProgressStatus;
import jp.co.world.storedevelopment.model.MShopCompanyAbbreviation;
import jp.co.world.storedevelopment.model.MStoreDevelopTeam;
import jp.co.world.storedevelopment.model.OtherProjectTeam;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.ProjectCategory;
import jp.co.world.storedevelopment.model.ProjectClassification;
import jp.co.world.storedevelopment.model.ProjectContractProgress;
import jp.co.world.storedevelopment.model.ProjectPlan;
import jp.co.world.storedevelopment.model.ProjectSectionProgress;
import jp.co.world.storedevelopment.model.Shop;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IAreaRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBlockRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBrandIncomeUnitRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesAgencyTargetRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesChannelRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IShopRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MPeriodRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MProjectActionStatusRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MProjectProgressStatusRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MShopCompanyAbbreviationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MStoreDevelopTeamRepository;
import jp.co.world.storedevelopment.model.mapper.repository.OtherProjectAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.OtherProjectTeamRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectCategoryRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectClassificationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectContractProgressRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectPlanRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectReadLaterAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectSectionProgressRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ShopRepository;
import jp.co.world.storedevelopment.sp.controller.dto.IBrandIncomeUnitRelationProjectDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectContractProgressRelationProjectListDTO;

public class ProjectViewModel extends Project {

	private String stopReasonValue;
	private String personInCharge;
	private Boolean hasReadLater;

	public String getStopReasonValue() {
		return stopReasonValue;
	}

	public void setStopReasonValue(String stopReasonValue) {
		this.stopReasonValue = stopReasonValue;
	}

	public static List<ProjectViewModel> toList(List<Project> list, Account account) {
		return list.stream().map(p -> {
			ProjectViewModel model = new ProjectViewModel();
			copyProperties(model, p);
			model.setStopReasonValue(p.stopReason());
			model.setHasReadLater(new ProjectReadLaterAccountRepository().isReadLater(p, account));
			model.setPersonInCharge(p);
			return model;
		}).collect(Collectors.toList());
	}

	private static ProjectViewModel copyProperties(ProjectViewModel model, Project project) {
		try {
			PropertyUtils.copyProperties(model, project);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalStateException("オブジェクトのコピーに失敗しました");
		}
		return model;
	}

	public void setPersonInCharge(Project project) {
		String names = project.storeAccounts().stream().map(a -> {
			return a.getFullName();
		}).collect(Collectors.joining(" "));
		setPersonInCharge(names);
	}

	public String getDisplayExternalReleaseConfirm() {
		if (getExternalReleaseConfirm() != null && getExternalReleaseConfirm() == true) {
			return "確定";
		} else if (getExternalReleaseStartDate() != null) {
			return getExternalReleaseStartDate() + "頃";
		} else {
			return "";
		}
	}

	public IArea getIArea() {
		if (getiAreaId() == null) {
			return null;
		}

		Optional<IArea> iarea = new IAreaRepository().findById(getiAreaId());
		return iarea.isPresent() ? iarea.get() : null;
	}

	public IBlock getIBlock() {
		if (getiBlockId() == null) {
			return null;
		}

		Optional<IBlock> iblock = new IBlockRepository().findById(getiBlockId());
		return iblock.isPresent() ? iblock.get() : null;
	}

	public ICorporation getICorporation() {
		if (getCorporationId() == null) {
			return null;
		}

		Optional<ICorporation> iCorporation = new ICorporationRepository().findById(getCorporationId());
		return iCorporation.isPresent() ? iCorporation.get() : null;
	}

	public Building getBuilding() {
		if (getBuildingId() == null) {
			return null;
		}

		Optional<Building> building = new BuildingRepository().findById(getBuildingId());
		return building.isPresent() ? building.get() : null;
	}

	public IShop getIShop() {
		if (getiShopId() == null) {
			return null;
		}

		Optional<IShop> iShop = new IShopRepository().findById(getiShopId());
		return iShop.isPresent() ? iShop.get() : null;
	}

	public ProjectCategory getProjectCategory() {
		if (getProjectCategoryId() == null) {
			return null;
		}

		Optional<ProjectCategory> projectCategory = new ProjectCategoryRepository().findById(getProjectCategoryId());
		return projectCategory.isPresent() ? projectCategory.get() : null;
	}

	public ProjectClassification getProjectLandingCategory() {
		if (getLandingProjectCategoryId() == null) {
			return null;
		}

		Optional<ProjectClassification> projectClassification = new ProjectClassificationRepository()
				.findById(getLandingProjectCategoryId());
		return projectClassification.isPresent() ? projectClassification.get() : null;
	}

	public ProjectClassification getProjectLandingPercent() {
		if (getConclusionPossibilityPercentId() == null) {
			return null;
		}

		Optional<ProjectClassification> projectClassification = new ProjectClassificationRepository()
				.findById(getConclusionPossibilityPercentId());
		return projectClassification.isPresent() ? projectClassification.get() : null;
	}

	public ProjectClassification getStopReason() {
		if (getStopReasonId() == null) {
			return null;
		}

		Optional<ProjectClassification> projectClassification = new ProjectClassificationRepository()
				.findById(getStopReasonId());
		return projectClassification.isPresent() ? projectClassification.get() : null;
	}

	public IBrandIncomeUnitRelationProjectDTO getIBrand() {
		if (getBrandId() != null) {
			Optional<IBrandIncomeUnit> iBrand = new IBrandIncomeUnitRepository().findById(getBrandId());
			if (iBrand.isPresent()) {
				return new IBrandIncomeUnitRelationProjectDTO(iBrand.get());
			}
		}
		return null;
	}

	public ISalesChannel getISalesChannel() {
		if (getiSalesChannelId() == null) {
			return null;
		}

		Optional<ISalesChannel> iSalesChannel = new ISalesChannelRepository().findById(getiSalesChannelId());
		return iSalesChannel.isPresent() ? iSalesChannel.get() : null;
	}

	public ProjectContractProgressRelationProjectListDTO getCurrentProjectContractProgress() {
		Optional<ProjectContractProgress> projectContractProgress = new ProjectContractProgressRepository()
				.getCurrentContractByProjectId(getId());
		return projectContractProgress.isPresent()
				? new ProjectContractProgressRelationProjectListDTO(projectContractProgress.get())
				: null;
	}

	public ProjectContractProgressRelationProjectListDTO getProgressProjectContractProgress() {
		Optional<ProjectContractProgress> projectContractProgress = new ProjectContractProgressRepository()
				.getProgressContractByProjectId(getId());
		return projectContractProgress.isPresent()
				? new ProjectContractProgressRelationProjectListDTO(projectContractProgress.get())
				: null;
	}

	public ProjectSectionProgress getCurrentProjectSectionProgress() {
		Optional<ProjectSectionProgress> projectSectionProgress = new ProjectSectionProgressRepository()
				.getCurrentSectionProgressByProjectId(getId());
		return projectSectionProgress.isPresent() ? projectSectionProgress.get() : null;
	}

	public ProjectSectionProgress getNegotiationProjectSectionProgress() {
		Optional<ProjectSectionProgress> projectSectionProgress = new ProjectSectionProgressRepository()
				.getNegotiationSectionProgressByProjectId(getId());
		return projectSectionProgress.isPresent() ? projectSectionProgress.get() : null;
	}

	public Boolean getHasReadLater() {
		return hasReadLater;
	}

	public void setHasReadLater(Boolean hasReadLater) {
		this.hasReadLater = hasReadLater;
	}

	public String getIAccount() {
		if (getId() != null) {
			List<Account> accounts = new OtherProjectAccountRepository().findStoreByProjectId(getId());
			if (accounts.size() > 0) {
				return String.join(" ", accounts.stream().map(x -> x.getFullName()).collect(Collectors.toList()));
			}
		}
		return StringUtils.EMPTY;
	}

	public String getDisplayTenancy() {
		if (getTenancy() != null && getTenancy() == true) {
			return "なる";
		} else {
			return "ならない";
		}
	}

	public List<ProjectPlan> getListProjectPlan() {
		List<ProjectPlan> dtos = new ArrayList<>();
		if (id != null) {
			dtos = new ProjectPlanRepository().findByProjectId(getId());
		}
		return dtos;
	}

	public MProjectProgressStatus getOfficeProjectProgress() {
		if (getOfficeProjectProgressId() == null) {
			return null;
		}
		Optional<MProjectProgressStatus> projectProgress = new MProjectProgressStatusRepository()
				.findById(getOfficeProjectProgressId());
		return projectProgress.isPresent() ? projectProgress.get() : null;
	}

	public MProjectProgressStatus getNegotiationProjectProgress() {
		if (getNegotiationProjectProgressId() == null) {
			return null;
		}
		Optional<MProjectProgressStatus> projectProgress = new MProjectProgressStatusRepository()
				.findById(getNegotiationProjectProgressId());
		return projectProgress.isPresent() ? projectProgress.get() : null;
	}

	public String getStopDivision() {
		if (getStopReasonId() != null) {
			ProjectClassification pc = new ProjectClassificationRepository().findById(getStopReasonId()).get();
			if (pc != null) {
				return pc.getName();
			}
		}
		return StringUtils.EMPTY;
	}

	public MPeriod getMPeriod() {
		if (getImplementationDatetime() == null) {
			return null;
		}
		Optional<MPeriod> projectProgress = new MPeriodRepository()
				.getPeriod(getImplementationDatetime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		return projectProgress.isPresent() ? projectProgress.get() : null;
	}

	public String getProjectActionStatus() {
		if (getMProjectActionStatusId() == null) {
			return "";
		} else {
			return new MProjectActionStatusRepository().findById(getMProjectActionStatusId()).orElseGet(() -> {
				throw new IllegalStateException("存在しない行動ステータス");
			}).getName();
		}
	}

	public String getConclusionPossibilityPercent() {
		if (getConclusionPossibilityPercentId() != null) {
			Optional<ProjectClassification> classification = new ProjectClassificationRepository()
					.findById(getConclusionPossibilityPercentId());
			if (classification.isPresent()) {
				return classification.get().getName();
			}
		}
		return StringUtils.EMPTY;
	}

	public String getImplementationPeriod() {
		if (getImplementationDatetime() != null) {
			Optional<MPeriod> opt = new MPeriodRepository()
					.getPeriod(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(getImplementationDatetime()));
			if (opt.isPresent()) {
				MPeriod period = opt.get();
				if (getImplementationDatetime().isAfter(period.getFirstHalfEndDate())) {
					return opt.get().getPeriod() + " 下期";
				} else {
					return opt.get().getPeriod() + " 上期";
				}
			}
		} else if (getImplementationDatetime() == null && getImplementationScheduleDatetime() != null) {
			Optional<MPeriod> opt = new MPeriodRepository()
					.getPeriod(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(getImplementationScheduleDatetime()));
			if (opt.isPresent()) {
				MPeriod period = opt.get();
				if (getImplementationScheduleDatetime().isAfter(period.getFirstHalfEndDate())) {
					return opt.get().getPeriod() + " 下期";
				} else {
					return opt.get().getPeriod() + " 上期";
				}
			}
		}

		return StringUtils.EMPTY;
	}

	public String getPlanStatus() {
		if (getPlanStatusId() != null) {
			Optional<ProjectClassification> classification = new ProjectClassificationRepository()
					.findById(getPlanStatusId());
			if (classification.isPresent()) {
				return classification.get().getName();
			}
		}
		return StringUtils.EMPTY;
	}

	public ISalesAgencyTarget getCurrentSalesAgencyTarget() {
		if (getCurrentISalesAgencyTargetId() != null) {
			Optional<ISalesAgencyTarget> salesAgencyTarget = new ISalesAgencyTargetRepository()
					.findBySalesAgencyTargetId(getCurrentISalesAgencyTargetId());
			if (salesAgencyTarget.isPresent()) {
				return salesAgencyTarget.get();
			}
		}
		return null;
	}

	public ISalesAgencyTarget getProgressSalesAgencyTarget() {
		if (getProgressISalesAgencyTargetId() != null) {
			Optional<ISalesAgencyTarget> salesAgencyTarget = new ISalesAgencyTargetRepository()
					.findBySalesAgencyTargetId(getProgressISalesAgencyTargetId());
			if (salesAgencyTarget.isPresent()) {
				return salesAgencyTarget.get();
			}
		}
		return null;
	}

	public ProjectPlan getSalesAgencyCurrent() {
		ProjectPlan plan = null;
		if (getListProjectPlan().size() > 0) {
			for (ProjectPlan pl : getListProjectPlan()) {
				if (pl.getCategory().equals("CURRENT")) {
					plan = pl;
					if (plan.getNumberOfYear() != null) {
						Float result = new BigDecimal(plan.getNumberOfYear()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
						plan.setNumberOfYear(result);
					}
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
					if (plan.getNumberOfYear() != null) {
						Float result = new BigDecimal(plan.getNumberOfYear()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
						plan.setNumberOfYear(result);
					}
				}
			}
			return plan;
		}
		return null;
	}

	public String getNumberOfYearSalesAgencyCurrent() {
		ProjectPlan plan = getSalesAgencyCurrent();
		if (plan != null && plan.getNumberOfYear() != null) {
			return String.valueOf(Math.round(plan.getNumberOfYear() * 100.0) / 100.0);
		}
		return null;
	}

	public String getNumberOfYearSalesAgencyProgress() {
		ProjectPlan plan = getSalesAgencyProgress();
		if (plan != null && plan.getNumberOfYear() != null) {
			return String.valueOf(Math.round(plan.getNumberOfYear() * 100.0) / 100.0);
		}
		return null;
	}

	public String getContractTsuboSectionChange() {
		ProjectSectionProgress current = getCurrentProjectSectionProgress();
		ProjectSectionProgress negotiation = getNegotiationProjectSectionProgress();
		if (current != null && current.getContractTsubo() != null && negotiation != null
				&& negotiation.getContractTsubo() != null && !getCategoryName().equals(ProjectCategory.storeOpenings)) {
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

	public String getCategoryName() {
		if (getProjectCategory() != null && getProjectCategory().getCategoryName() != null) {
			return getProjectCategory().getCategoryName();
		}
		return StringUtils.EMPTY;
	}

	public String getIBussinessName() {
		if (getBrandId() != null) {
			Optional<IBrandIncomeUnit> ibrand = new IBrandIncomeUnitRepository().findById(getBrandId());
			if (ibrand.isPresent()) {
				Optional<MShopCompanyAbbreviation> optShopCompany = new MShopCompanyAbbreviationRepository()
						.findByName(ibrand.get().getBrandIncomeUnitName());
				if (optShopCompany.isPresent()) {
					return ZenkakuUtils.toZenkaku(optShopCompany.get().getAbbreviatedCompanyName());
				}
			}
		}

		return StringUtils.EMPTY;
	}

	public Integer getCurrentBuildingExpectedValue() {
		if (getIShop() != null) {
			Optional<Shop> shop = new ShopRepository().findById(getIShop().getShopId());
			if (shop.isPresent()) {
				return shop.get().getBuildingExpectedValue();
			}
		}
		return null;
	}

	public String getDeptName() {
		if (getId() != null) {
			List<OtherProjectTeam> projectTeam = new OtherProjectTeamRepository().findByProjectId(getId());
			if (projectTeam.size() > 0) {
				String deptName = "";
				for (OtherProjectTeam opt : projectTeam) {
					if (opt != null && opt.getDeptCd() != null && opt.getDeptCd() != "") {
						Optional<MStoreDevelopTeam> mstore = new MStoreDevelopTeamRepository()
								.findByDeptCd(opt.getDeptCd());
						if (mstore.isPresent()) {
							deptName += mstore.get().getDeptName() + "/";
						}
					}
				}
				if (!deptName.equals("")) {
					return deptName.substring(0, deptName.length() - 1);
				}
			}
		}
		return StringUtils.EMPTY;
	}

	public String getPersonInCharge() {
		return personInCharge;
	}

	public void setPersonInCharge(String personInCharge) {
		this.personInCharge = personInCharge;
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

}
