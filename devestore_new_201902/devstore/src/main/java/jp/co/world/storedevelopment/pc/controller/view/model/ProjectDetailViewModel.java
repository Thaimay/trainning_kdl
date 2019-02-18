package jp.co.world.storedevelopment.pc.controller.view.model;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.ProjectSwitingItemControl;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectReadLaterAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectScheduleRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectSwitingItemControlRepository;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectDetailDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ScheduleDateDTO;

public class ProjectDetailViewModel extends ProjectDetailDTO {

	private static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	private static DateTimeFormatter DATE_FORMAT_YYYYMM = DateTimeFormatter.ofPattern("yyyy/MM");
	private static DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

	private List<NegotiationViewModel> relatedNegotiationViewModelList = new ArrayList<NegotiationViewModel>();
	private List<ProjectViewModel> relatedProjectViewModelList = new ArrayList<ProjectViewModel>();
	private List<ScheduleDateDTO> scheduleList = new ArrayList<>();
	private ProjectSwitingItemControl itemControl = new ProjectSwitingItemControl();

	public ProjectDetailViewModel(Project p, Account a) {
		super(p, a);

		List<Negotiation> listNegotiation = new ArrayList<>();
		getProjectNegotiations().forEach(x -> {
			Optional<Negotiation> n = new NegotiationRepository().findById(x.getNegotiationId());
			if (n.isPresent()) {
				listNegotiation.add(n.get());
			}
		});
		List<NegotiationViewModel> list = NegotiationViewModel.toList(listNegotiation, a);
		setRelatedNegotiationViewModelList(list);

		List<ProjectViewModel> projectList = new ArrayList<>();
		if (getBuildingId() != null) {
			List<Project> listProject = new ProjectRepository().getProjectListByBuildingId(getBuildingId());
			listProject = listProject.stream().filter(x -> (x.getId() != p.getId())).collect(Collectors.toList());
			projectList = ProjectViewModel.toList(listProject, a);
		}
		setRelatedProjectViewModelList(projectList);
		setIsLater(new ProjectReadLaterAccountRepository().isReadLater(p, a));
		List<ScheduleDateDTO> scheduleList = new ProjectScheduleRepository().findByProjectId(p.getId()).stream().map(s -> {
			return new ScheduleDateDTO(s, getMProjectActionStatus());
		}).collect(Collectors.toList());
		setScheduleList(scheduleList);

		getScheduleList().forEach(s -> {
			Long statusId = s.getProjectStatusId();

			if (statusId.equals(p.getMProjectActionStatusId())) {
				setCurrentScheduleId(statusId);
			}

			if (s.getIsPastDay()) {
				setGoalScheduleId(statusId);
			}
		});

		Optional<ProjectSwitingItemControl> projectSwitingItemControl = new ProjectSwitingItemControlRepository()
				.findByProjectCategoryId(p.getProjectCategoryId());
		setItemControl(projectSwitingItemControl.orElse(new ProjectSwitingItemControl()));

		setAccount(a);
	}

	public ProjectSwitingItemControl getItemControl() {
		return itemControl;
	}

	public void setItemControl(ProjectSwitingItemControl itemControl) {
		this.itemControl = itemControl;
	}

	public List<NegotiationViewModel> getRelatedNegotiationViewModelList() {
		return relatedNegotiationViewModelList;
	}

	public void setRelatedNegotiationViewModelList(List<NegotiationViewModel> relatedNegotiationViewModelList) {
		this.relatedNegotiationViewModelList = relatedNegotiationViewModelList;
	}

	public List<ProjectViewModel> getRelatedProjectViewModelList() {
		return relatedProjectViewModelList;
	}

	public void setRelatedProjectViewModelList(List<ProjectViewModel> relatedProjectViewModelList) {
		this.relatedProjectViewModelList = relatedProjectViewModelList;
	}

	public String getCreatedDatetimeString() {
		if (getCreatedDatetime() != null) {
			return getCreatedDatetime().format(DATE_TIME_FORMAT);
		} else {
			return StringUtils.EMPTY;
		}
	}

	public String getUpdateDatetimeString() {
		if (getUpdateDatetime() != null) {
			return getUpdateDatetime().format(DATE_TIME_FORMAT);
		} else {
			return StringUtils.EMPTY;
		}
	}

	private Optional<Account> getCreatedAccount() {
		return new AccountRepository().findByCode(getCreatedAccountCode());
	}

	public String getCreatedAccountName() {
		Optional<Account> createdAccount = getCreatedAccount();
		if (createdAccount.isPresent()) {
			return createdAccount.get().getFullName();
		} else {
			return StringUtils.EMPTY;
		}
	}

	private Optional<Account> getUpdateAccount() {
		return new AccountRepository().findByCode(getUpdateAccountCode());
	}

	public String getUpdateAccountName() {
		Optional<Account> updateAccount = getUpdateAccount();
		if (updateAccount.isPresent()) {
			return updateAccount.get().getFullName();
		} else {
			return StringUtils.EMPTY;
		}
	}

	public List<ScheduleDateDTO> getScheduleList() {
		return scheduleList;
	}

	public void setScheduleList(List<ScheduleDateDTO> scheduleList) {
		this.scheduleList = scheduleList;
	}
}
