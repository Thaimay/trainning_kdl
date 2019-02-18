package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.BuildingPersonalDevelop;
import jp.co.world.storedevelopment.model.IArea;
import jp.co.world.storedevelopment.model.IBlock;
import jp.co.world.storedevelopment.model.ICorporation;
import jp.co.world.storedevelopment.model.ICorporationGroup;
import jp.co.world.storedevelopment.model.IPrefectures;
import jp.co.world.storedevelopment.model.ISalesChannel;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingPersonalDevelopRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IAreaRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBlockRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationGroupRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IPrefecturesRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesChannelRepository;

public class ProjectReferenceBuildingDTO implements DTO<Project> {
	private Building building;
	private ICorporation corporation;

	private List<RelatedAccountDTO> branchsSalesList = new ArrayList<>();
	private List<RelatedAccountDTO> storeDeveloperList = new ArrayList<>();
	private List<RelatedAccountDTO> humanResourceLeaderList = new ArrayList<>();

	public ProjectReferenceBuildingDTO(Long buildingId) {
		Optional<Building> optBuilding = new BuildingRepository().findById(buildingId);
		if (optBuilding.isPresent()) {
			this.building = optBuilding.get();

			// corporation
			Optional<ICorporation> optCorporation = new ICorporationRepository()
					.findById(this.building.getiCorporationId());
			if (optCorporation.isPresent()) {
				this.corporation = optCorporation.get();
			}

			// building personal develop
			List<BuildingPersonalDevelop> listPerson = new BuildingPersonalDevelopRepository()
					.findByBuildingCd(this.building.getBuildingCd());
			if (listPerson != null) {
				listPerson.forEach(x -> {
					Optional<Account> optAccount = new AccountRepository().findById(x.getAccountId());
					if (optAccount.isPresent()) {
						switch (x.getCategory()) {
						case "branchsSales":
							branchsSalesList.add(new RelatedAccountDTO(optAccount.get()));
							break;
						case "storeDeveloper":
							storeDeveloperList.add(new RelatedAccountDTO(optAccount.get()));
							break;
						case "humanResourceLeader":
							humanResourceLeaderList.add(new RelatedAccountDTO(optAccount.get()));
							break;
						}
					}
				});
			}
		}
	}

	public String getCorporationGroupName() {
		if (this.corporation != null && this.corporation.getCorporationGpId() != null) {
			Optional<ICorporationGroup> optCorporationGroup = new ICorporationGroupRepository()
					.findById(this.corporation.getCorporationGpId());
			if (optCorporationGroup.isPresent()) {
				return optCorporationGroup.get().getCorporationGpName();
			}
		}
		return StringUtils.EMPTY;
	};

	public String getCorporationName() {
		if (this.corporation != null) {
			return this.corporation.getCorporationName();
		}
		return StringUtils.EMPTY;
	};

	public String getAreaName() {
		if (this.building != null) {
			Optional<IArea> optArea = new IAreaRepository().findById(this.building.getiAreaId());
			if (optArea.isPresent()) {
				return optArea.get().getAreaName();
			}
		}
		return StringUtils.EMPTY;
	};

	public String getBlockName() {
		if (this.building != null) {
			Optional<IBlock> optBlock = new IBlockRepository().findById(this.building.getiBlockId());
			if (optBlock.isPresent()) {
				return optBlock.get().getBlockName();
			}
		}
		return StringUtils.EMPTY;
	};

	public String getPrefecturesName() {
		if (this.building != null) {
			Optional<IPrefectures> optPrefectures = new IPrefecturesRepository()
					.findById(this.building.getiPrefecturesId());
			if (optPrefectures.isPresent()) {
				return optPrefectures.get().getPrefecturesName();
			}
		}
		return StringUtils.EMPTY;
	};

	public String getSalesChannelName() {
		if (this.building != null) {
			Optional<ISalesChannel> optSalesChannel = new ISalesChannelRepository()
					.findById(this.building.getiSalesChannelId());
			if (optSalesChannel.isPresent()) {
				return optSalesChannel.get().getSalesChannelName();
			}
		}
		return StringUtils.EMPTY;
	};

	public String getBuildingAdoptDifficulty() {
		if (this.building != null) {
			return this.building.getAdoptDifficulty();
		}
		return StringUtils.EMPTY;
	}

	public String getBuildingName() {
		if (this.building != null) {
			return this.building.getName();
		}
		return StringUtils.EMPTY;

	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public ICorporation getCorporation() {
		return this.corporation;
	}

	public void setCorporation(ICorporation corporation) {
		this.corporation = corporation;
	}

	@Override
	public Project createModel() {
		return new Project();
	}

	public List<RelatedAccountDTO> getBranchsSalesList() {
		return branchsSalesList;
	}

	public void setBranchsSalesList(List<RelatedAccountDTO> branchsSalesList) {
		this.branchsSalesList = branchsSalesList;
	}

	public List<RelatedAccountDTO> getStoreDeveloperList() {
		return storeDeveloperList;
	}

	public void setStoreDeveloperList(List<RelatedAccountDTO> storeDeveloperList) {
		this.storeDeveloperList = storeDeveloperList;
	}

	public List<RelatedAccountDTO> getHumanResourceLeaderList() {
		return humanResourceLeaderList;
	}

	public void setHumanResourceLeaderList(List<RelatedAccountDTO> humanResourceLeaderList) {
		this.humanResourceLeaderList = humanResourceLeaderList;
	}
}
