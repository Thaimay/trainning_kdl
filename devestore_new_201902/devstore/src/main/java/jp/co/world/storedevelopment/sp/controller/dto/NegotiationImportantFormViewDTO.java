package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jp.co.world.storedevelopment.model.ImportantInformation;
import jp.co.world.storedevelopment.model.ImportantInformationBuilding;
import jp.co.world.storedevelopment.model.ImportantInformationCorporation;

public class NegotiationImportantFormViewDTO {
	private List<ValueDTO> buildingValues = new ArrayList<ValueDTO>();
	private List<ValueDTO> corporationValues = new ArrayList<ValueDTO>();
	private List<ValueDTO> buildingUnmanagedValues = new ArrayList<ValueDTO>();
	private List<ValueDTO> corporationUnmanagedValues = new ArrayList<ValueDTO>();

	private String showStartDatetime;

	private String showEndDatetime;

	private String content = "";

	public NegotiationImportantFormViewDTO() {
	}

	public NegotiationImportantFormViewDTO(ImportantInformation in) {
		setShowStartDatetime(in.getShowStartDateValue());
		setShowEndDatetime(in.getShowEndDateValue());
		setContent(in.getContent());

		List<ImportantInformationBuilding> ibList = in.importantInformationBuildings();
		setBuildingValuesDTO(ibList);
		setBuildingUnmanagedValuesDTO(ibList);

		List<ImportantInformationCorporation> icList = in.importantInformationCorporation();
		setCorporationValuesDTO(icList);
		setCorporationUnmanagedValuesDTO(icList);
	}

	public void setBuildingValuesDTO(List<ImportantInformationBuilding> list) {
		List<ValueDTO> result = list.stream().filter(b -> b.isUnmanaged() == false).map(b -> {
			return new ValueDTO(b.getBuildingId(), b.getBuildingName());
		}).collect(Collectors.toList());
		setBuildingValues(result);
	}

	public void setBuildingUnmanagedValuesDTO(List<ImportantInformationBuilding> list) {
		List<ValueDTO> result = list.stream().filter(b -> b.isUnmanaged()).map(b -> {
			return new ValueDTO(b.getBuildingId(), b.getBuildingName());
		}).collect(Collectors.toList());
		setBuildingUnmanagedValues(result);
	}

	public void setCorporationValuesDTO(List<ImportantInformationCorporation> list) {
		List<ValueDTO> result = list.stream().filter(c -> c.isUnmanaged() == false).map(c -> {
			return new ValueDTO(c.getCorporationId(), c.corporationName());
		}).collect(Collectors.toList());
		setCorporationValues(result);
	}

	public void setCorporationUnmanagedValuesDTO(List<ImportantInformationCorporation> list) {
		List<ValueDTO> result = list.stream().filter(c -> c.isUnmanaged()).map(c -> {
			return new ValueDTO(c.getCorporationId(), c.corporationName());
		}).collect(Collectors.toList());
		setCorporationUnmanagedValues(result);
	}

	public List<ValueDTO> getBuildingValues() {
		return buildingValues;
	}

	public void setBuildingValues(List<ValueDTO> buildingValues) {
		this.buildingValues = buildingValues;
	}

	public List<ValueDTO> getCorporationValues() {
		return corporationValues;
	}

	public void setCorporationValues(List<ValueDTO> corporationValues) {
		this.corporationValues = corporationValues;
	}

	public List<ValueDTO> getBuildingUnmanagedValues() {
		return buildingUnmanagedValues;
	}

	public void setBuildingUnmanagedValues(List<ValueDTO> buildingUnmanagedValues) {
		this.buildingUnmanagedValues = buildingUnmanagedValues;
	}

	public List<ValueDTO> getCorporationUnmanagedValues() {
		return corporationUnmanagedValues;
	}

	public void setCorporationUnmanagedValues(List<ValueDTO> corporationUnmanagedValues) {
		this.corporationUnmanagedValues = corporationUnmanagedValues;
	}

	public String getShowStartDatetime() {
		return showStartDatetime;
	}

	public void setShowStartDatetime(String showStartDatetime) {
		this.showStartDatetime = showStartDatetime;
	}

	public String getShowEndDatetime() {
		return showEndDatetime;
	}

	public void setShowEndDatetime(String showEndDatetime) {
		this.showEndDatetime = showEndDatetime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
