package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.ICorporation;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IAreaRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBlockRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationGroupRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IPrefecturesRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesChannelRepository;

public class ProjectCreateFormDTO implements DTO<Project> {
	private Building building;
	private ICorporation corporation;
	public ProjectCreateFormDTO(Long buildingId) {
		this.building = new BuildingRepository().findById(buildingId).get();
		this.corporation = new ICorporationRepository().findById(this.building.getiCorporationId()).get();
	}
	public ICorporation getCorporation() {
		return this.corporation;
	}
	public String getCorporationGroupName(){
		return new ICorporationGroupRepository().findById(this.corporation.getCorporationGpId()).get().getCorporationGpName();
	};
	public String getCorporationName(){
		return this.corporation.getCorporationName();
	};
	public String getAreaName(){
		return new IAreaRepository().findById(this.building.getiAreaId()).get().getAreaName();
	};
	public String getBlockName(){
		return new IBlockRepository().findById(this.building.getiBlockId()).get().getBlockName();
	};
	public String getPrefecturesName(){
		return new IPrefecturesRepository().findById(this.building.getiPrefecturesId()).get().getPrefecturesName();
	};
	public String getSalesChannelName(){
		return new ISalesChannelRepository().findById(this.building.getiSalesChannelId()).get().getSalesChannelName();
	};
	public String getBuildingAdoptDifficulty() {
		return this.building.getAdoptDifficulty();
	}
	public String getBuildingName() {
		return this.building.getName();
	}
	
	public Building getBuilding() {
		return building;
	}
	public void setBuilding(Building building) {
		this.building = building;
	}
	
	public void setCorporation(ICorporation corporation) {
		this.corporation = corporation;
	}
	
	@Override
	public Project createModel() {
		return new Project();
	}

}
