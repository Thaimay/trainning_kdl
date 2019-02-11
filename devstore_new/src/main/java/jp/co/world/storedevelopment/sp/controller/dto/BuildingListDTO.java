package jp.co.world.storedevelopment.sp.controller.dto;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.BuildingPersonalDevelop;
import jp.co.world.storedevelopment.model.BuildingSales;
import jp.co.world.storedevelopment.model.IArea;
import jp.co.world.storedevelopment.model.IBlock;
import jp.co.world.storedevelopment.model.ICorporation;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingPersonalDevelopRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingSalesRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IAreaRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBlockRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;

public class BuildingListDTO implements DTO<Building> {

	private Long id;
	private String buildingCd;
	private String name;
	private String buildingGroupId;
	private Long iCorporationId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate openDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime updateDatetime;
	private Long iAreaId;
	private Long iBlockId;
	private String address;
	private String homepageUrl;
	private String imagePath;
	private BigDecimal latitude;
	private BigDecimal longitude;

	@Override
	public Building createModel() {
		return new Building();
	}

	public BuildingListDTO() {
		//
	}

	public BuildingListDTO(Building building) {
		copyProperties(this, building);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getiCorporationId() {
		return iCorporationId;
	}

	public void setiCorporationId(Long iCorporationId) {
		this.iCorporationId = iCorporationId;
	}

	public String getBuildingGroupId() {
		return buildingGroupId;
	}

	public void setBuildingGroupId(String buildingGroupId) {
		this.buildingGroupId = buildingGroupId;
	}

	public LocalDate getOpenDate() {
		return openDate;
	}

	public void setOpenDate(LocalDate openDate) {
		this.openDate = openDate;
	}

	public LocalDateTime getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(LocalDateTime updateDatetime) {
		this.updateDatetime = updateDatetime;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHomepageUrl() {
		return homepageUrl;
	}

	public void setHomepageUrl(String homepageUrl) {
		this.homepageUrl = homepageUrl;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public ICorporationRelationBuildingListDTO getiCorporation() {
		if (getiCorporationId() == null) {
			return null;
		}

		Optional<ICorporation> iCorporation = new ICorporationRepository().findById(getiCorporationId());
		return iCorporation.isPresent() ? new ICorporationRelationBuildingListDTO(iCorporation.get()) : null;
	}

	public List<BuildingPersonalDevelopRelationBuildingListDTO> getBuildingPersonalDevelops() {
		List<BuildingPersonalDevelopRelationBuildingListDTO> dtos = new ArrayList<>();

		if (getBuildingCd() == null || getBuildingCd().isEmpty()) {
			return dtos;
		}

		List<BuildingPersonalDevelop> buildingPersonalDevelops = new BuildingPersonalDevelopRepository()
				.findByBuildingCd(getBuildingCd());
		return buildingPersonalDevelops != null && buildingPersonalDevelops.size() > 0 ? buildingPersonalDevelops
				.stream().map(x -> new BuildingPersonalDevelopRelationBuildingListDTO(x)).collect(Collectors.toList())
				: dtos;
	}

	public List<BuildingSalesRelationBuildingListDTO> getBuildingSaless() {
		List<BuildingSalesRelationBuildingListDTO> dtos = new ArrayList<>();

		if (getBuildingCd() == null || getBuildingCd().isEmpty()) {
			return dtos;
		}

		List<BuildingSales> buildingSaless = new BuildingSalesRepository().findByBuildingCd(getBuildingCd());
		return buildingSaless != null && buildingSaless.size() > 0 ? buildingSaless.stream()
				.map(x -> new BuildingSalesRelationBuildingListDTO(x)).collect(Collectors.toList()) : dtos;
	}

	public String getFormatBuildingSales() {
		String result = "";
		List<BuildingSalesRelationBuildingListDTO> buildingSales = getBuildingSaless().stream()
				.filter(x -> x.getSales().doubleValue() > 0 && x.getDivision() == 1).collect(Collectors.toList());

		if (buildingSales.size() > 0) {
			int maxYear = buildingSales.stream().map(x -> x.getFinancialMonth().getYear())
					.max(Comparator.comparing(Integer::valueOf)).get();

			double totalAmt = buildingSales.stream().filter(x -> x.getFinancialMonth().getYear() == maxYear)
					.mapToDouble(x -> x.getSales().doubleValue()).sum() / 100;

			result = String.format("%s年 %s億", Integer.toString(maxYear).substring(2),
					new DecimalFormat("###,##0.0").format(totalAmt));
		}

		return result;
	}

	public IAreaRelationBuildingListDTO getiArea() {
		if (getiAreaId() == null) {
			return null;
		}

		Optional<IArea> iArea = new IAreaRepository().findById(getiAreaId());
		return iArea.isPresent() ? new IAreaRelationBuildingListDTO(iArea.get()) : null;
	}

	public IBlockRelationBuildingListDTO getiBlock() {
		if (getiBlockId() == null) {
			return null;
		}

		Optional<IBlock> iBlock = new IBlockRepository().findById(getiBlockId());
		return iBlock.isPresent() ? new IBlockRelationBuildingListDTO(iBlock.get()) : null;
	}

}
