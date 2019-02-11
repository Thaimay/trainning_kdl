package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.BuildingSales;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingSalesRepository;

public class BuildingChildrenRelationBuildingDetailDTO implements DTO<Building> {

	private String buildingCd;
	private String name;

	public BuildingChildrenRelationBuildingDetailDTO() {

	}

	public BuildingChildrenRelationBuildingDetailDTO(Building building) {
		copyProperties(this, building);
	}

	public String getId() {
		return getBuildingCd();
	}

	public String getBuildingCd() {
		return buildingCd;
	}

	public void setBuildingCd(String buildingCd) {
		this.buildingCd = buildingCd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BuildingSalesRelationBuildingDetailDTO> getBuildingSaless() {
		List<BuildingSalesRelationBuildingDetailDTO> dtos = new ArrayList<>();
		
		if (getBuildingCd() == null || getBuildingCd().isEmpty()) {
			return dtos;
		}

		List<BuildingSales> buildingSaless = new BuildingSalesRepository().findByBuildingCd(getBuildingCd());
		return buildingSaless != null && buildingSaless.size() > 0 ? buildingSaless.stream()
				.map(x -> new BuildingSalesRelationBuildingDetailDTO(x)).collect(Collectors.toList()) : dtos;
	}

	@Override
	public Building createModel() {
		return new Building();
	}

}
