package jp.co.world.storedevelopment.pc.controller.view.model;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.ICorporationGroup;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationGroupRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectRepository;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingDetailDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingPersonalDevelopRelationBuildingDetailDTO;

public class BuildingDetailViewModel extends BuildingDetailDTO {

	private static final String EMPTY = "";
	private static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	private static DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

	private List<NegotiationViewModel> relatedNegotiationViewModelList = new ArrayList<NegotiationViewModel>();
	private List<ProjectViewModel> relatedProjectViewModels = new ArrayList<>();

	public BuildingDetailViewModel(Building b) {
		super(b);
	}

	public BuildingDetailViewModel(Building b, Account a) {
		super(b, a);
		List<NegotiationViewModel> list = NegotiationViewModel.toList(b.findRelatedNegotiations(), a);
		setRelatedNegotiationViewModelList(list);
		setRelatedProjectViewModels(b, a);
	}

	public String getCreatedDatetimeString() {
		if (getCreatedDatetime() != null) {
			return getCreatedDatetime().format(DATE_TIME_FORMAT);
		} else {
			return EMPTY;
		}
	}

	public Optional<Account> getCreatedAccount() {
		return new AccountRepository().findByCode(getCreatedAccountCode());
	}

	public String getCreatedAccountName() {
		if (getCreatedAccount().isPresent()) {
			return getCreatedAccount().get().getFullName();
		} else {
			return EMPTY;
		}
	}

	public ICorporationGroup getiCorporationGroup() {
		if (getiCorporation() != null && getiCorporation().getCorporationGpId() != null) {
			Optional<ICorporationGroup> iCorporationGroup = new ICorporationGroupRepository()
					.findById(getiCorporation().getCorporationGpId());
			return iCorporationGroup.isPresent() ? iCorporationGroup.get() : null;
		}
		return null;
	}

	public String getOpenDateString() {
		if (getOpenDate() != null) {
			return getOpenDate().format(DATE_FORMAT);
		} else {
			return EMPTY;
		}
	}

	public String getCloseDateString() {
		if (getCloseDate() != null) {
			return getCloseDate().format(DATE_FORMAT);
		} else {
			return EMPTY;
		}
	}

	public List<String> getStoreDeveloperNames() {
		List<String> storeDeveloperNames = new ArrayList<String>();

		if (getBuildingPersonalDevelops() != null) {
			for (BuildingPersonalDevelopRelationBuildingDetailDTO bdp : getBuildingPersonalDevelops()) {
				if (bdp.getCategory().toLowerCase().equals("storedeveloper")) {
					storeDeveloperNames.add(bdp.getAccountName());
				}
			}
		}

		return storeDeveloperNames;
	}

	public List<String> getBranchsSalesNames() {
		List<String> branchsSalesNames = new ArrayList<String>();

		if (getBuildingPersonalDevelops() != null) {
			for (BuildingPersonalDevelopRelationBuildingDetailDTO bdp : getBuildingPersonalDevelops()) {
				if (bdp.getCategory().toLowerCase().equals("branchssales")) {
					branchsSalesNames.add(bdp.getAccountName());
				}
			}
		}

		return branchsSalesNames;
	}

	public List<String> getHumanResourceLeaderNames() {
		List<String> humanResourceLeaderNames = new ArrayList<String>();

		if (getBuildingPersonalDevelops() != null) {
			for (BuildingPersonalDevelopRelationBuildingDetailDTO bdp : getBuildingPersonalDevelops()) {
				if (bdp.getCategory().toLowerCase().equals("humanresourceleader")) {
					humanResourceLeaderNames.add(bdp.getAccountName());
				}
			}
		}

		return humanResourceLeaderNames;
	}

	public List<NegotiationViewModel> getRelatedNegotiationViewModelList() {
		return relatedNegotiationViewModelList;
	}

	public void setRelatedNegotiationViewModelList(List<NegotiationViewModel> relatedNegotiationViewModelList) {
		this.relatedNegotiationViewModelList = relatedNegotiationViewModelList;
	}

	public List<ProjectViewModel> getRelatedProjectViewModels() {
		return relatedProjectViewModels;
	}

	public void setRelatedProjectViewModels(Building b, Account a) {
		List<Project> list =  new ProjectRepository().getProjectListByBuildingId(b.getId());
		if(list.size() > 0 ) {
			relatedProjectViewModels = ProjectViewModel.toList(list, a);
		}
	}
}
