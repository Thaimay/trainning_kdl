package jp.co.world.storedevelopment.sp.controller.dto;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Activation;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.BuildingFile;
import jp.co.world.storedevelopment.model.BuildingImage;
import jp.co.world.storedevelopment.model.BuildingMeetingHistory;
import jp.co.world.storedevelopment.model.BuildingPersonalDevelop;
import jp.co.world.storedevelopment.model.BuildingSales;
import jp.co.world.storedevelopment.model.PmCorporation;
import jp.co.world.storedevelopment.model.mapper.repository.ActivationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingFileRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingImageRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingKeymanRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingMeetingHistoryRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingPersonalDevelopRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingSalesRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingTenantRepository;
import jp.co.world.storedevelopment.model.mapper.repository.PmCorporationRepository;

public class BuildingUpdateDTO implements DTO<Building> {
	private static final ObjectMapper MAPPER = new ObjectMapper();

	private Long id;
	private String name;
	private String buildingCd;
	private Long iCorporationId;
	private String buildingGroupId;
	private String owner;
	private Integer buildingType;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate openDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate closeDate;
	private String address;
	private Long iPrefecturesId;
	private String homepageUrl;
	private String jiscd;
	private String groundFloor;
	private String structure;
	private String coreTenant;
	private String subCoreTenant;

	private Integer tenantNumber;
	private String attractingContent;
	private String parkingNumber;
	private String businessHours;
	private String nearestStation;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private Long iSalesChannelId;
	private Long salesChannelCd2;
	private Long iBlockId;
	private Long iAreaId;
	private String locationFirst;
	private String locationSecond;
	private String smallMarketArea;
	private String adoptDifficulty;
	private String note;

	private Optional<Account> account;
	private List<PmCorporation> pmCorporationDto;
	private List<BuildingTenantRelationBuildingUpdateDTO> buildingTenantDto;
	private List<BuildingPersonalDevelop> buildingPersonalDevelopDto;

	private List<BuildingKeymanRelationBuildingUpdateDTO> buildingKeymanDto;
	private List<Activation> activationDto;
	private List<BuildingFile> buildingFileDto;
	private List<BuildingImage> buildingImageDto;
	private List<BuildingMeetingHistory> buildingMeetingDto;
	private List<BuildingSales> buildingSalesDto;

	public BuildingUpdateDTO() {

	}

	public BuildingUpdateDTO(Building building) {
		this.copyProperties(this, building);
		this.setPmCorporationDto(new PmCorporationRepository().findByBuildingId(this.getId()));
		this.setBuildingTenantDto(new BuildingTenantRepository().findByBuildingId(this.getId()).stream()
				.map(x -> new BuildingTenantRelationBuildingUpdateDTO(x)).collect(Collectors.toList()));
		this.setBuildingPersonalDevelopDto(new BuildingPersonalDevelopRepository().findByBuildingId(this.getId()));
		this.setBuildingKeymanDto(new BuildingKeymanRepository().findByBuildingId(this.getId()).stream()
				.map(x -> new BuildingKeymanRelationBuildingUpdateDTO(x)).collect(Collectors.toList()));
		this.setActivationDto(new ActivationRepository().findByBuildingId(this.getId()));
		this.setBuildingFileDto(new BuildingFileRepository().findByBuildingId(this.getId()));
		this.setBuildingImageDto(new BuildingImageRepository().findByBuildingId(this.getId()));
		this.setBuildingMeetingDto(new BuildingMeetingHistoryRepository().findByBuildingId(this.getId()));
		this.setBuildingSalesDto(new BuildingSalesRepository().findByBuildingId(this.getId()));
	}

	@Override
	public Building createModel() {
		return new Building();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBuildingCd() {
		return buildingCd;
	}

	public void setBuildingCd(String buildingCd) {
		this.buildingCd = buildingCd;
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

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Integer getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(Integer buildingType) {
		this.buildingType = buildingType;
	}

	public LocalDate getOpenDate() {
		return openDate;
	}

	public void setOpenDate(LocalDate openDate) {
		this.openDate = openDate;
	}

	public LocalDate getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(LocalDate closeDate) {
		this.closeDate = closeDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getiPrefecturesId() {
		return iPrefecturesId;
	}

	public void setiPrefecturesId(Long iPrefecturesId) {
		this.iPrefecturesId = iPrefecturesId;
	}

	public String getHomepageUrl() {
		return homepageUrl;
	}

	public void setHomepageUrl(String homepageUrl) {
		this.homepageUrl = homepageUrl;
	}

	public String getJiscd() {
		return jiscd;
	}

	public void setJiscd(String jiscd) {
		this.jiscd = jiscd;
	}

	public String getGroundFloor() {
		return groundFloor;
	}

	public void setGroundFloor(String groundFloor) {
		this.groundFloor = groundFloor;
	}

	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public String getCoreTenant() {
		return coreTenant;
	}

	public void setCoreTenant(String coreTenant) {
		this.coreTenant = coreTenant;
	}

	public String getSubCoreTenant() {
		return subCoreTenant;
	}

	public void setSubCoreTenant(String subCoreTenant) {
		this.subCoreTenant = subCoreTenant;
	}

	public Integer getTenantNumber() {
		return tenantNumber;
	}

	public void setTenantNumber(Integer tenantNumber) {
		this.tenantNumber = tenantNumber;
	}

	public String getAttractingContent() {
		return attractingContent;
	}

	public void setAttractingContent(String attractingContent) {
		this.attractingContent = attractingContent;
	}

	public String getParkingNumber() {
		return parkingNumber;
	}

	public void setParkingNumber(String parkingNumber) {
		this.parkingNumber = parkingNumber;
	}

	public String getBusinessHours() {
		return businessHours;
	}

	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}

	public String getNearestStation() {
		return nearestStation;
	}

	public void setNearestStation(String nearestStation) {
		this.nearestStation = nearestStation;
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

	public Long getiSalesChannelId() {
		return iSalesChannelId;
	}

	public void setiSalesChannelId(Long iSalesChannelId) {
		this.iSalesChannelId = iSalesChannelId;
	}

	public Long getSalesChannelCd2() {
		return salesChannelCd2;
	}

	public void setSalesChannelCd2(Long salesChannelCd2) {
		this.salesChannelCd2 = salesChannelCd2;
	}

	public Long getiBlockId() {
		return iBlockId;
	}

	public void setiBlockId(Long iBlockId) {
		this.iBlockId = iBlockId;
	}

	public Long getiAreaId() {
		return iAreaId;
	}

	public void setiAreaId(Long iAreaId) {
		this.iAreaId = iAreaId;
	}

	public String getLocationFirst() {
		return locationFirst;
	}

	public void setLocationFirst(String locationFirst) {
		this.locationFirst = locationFirst;
	}

	public String getLocationSecond() {
		return locationSecond;
	}

	public void setLocationSecond(String locationSecond) {
		this.locationSecond = locationSecond;
	}

	public String getSmallMarketArea() {
		return smallMarketArea;
	}

	public void setSmallMarketArea(String smallMarketArea) {
		this.smallMarketArea = smallMarketArea;
	}

	public String getAdoptDifficulty() {
		return adoptDifficulty;
	}

	public void setAdoptDifficulty(String adoptDifficulty) {
		this.adoptDifficulty = adoptDifficulty;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Optional<Account> getAccount() {
		return account;
	}

	public void setAccount(Optional<Account> account) {
		this.account = account;
	}

	public List<PmCorporation> getPmCorporationDto() {
		return pmCorporationDto;
	}

	public void setPmCorporationDto(List<PmCorporation> pmCorporationDto) {
		this.pmCorporationDto = pmCorporationDto;
	}

	public List<BuildingTenantRelationBuildingUpdateDTO> getBuildingTenantDto() {
		return buildingTenantDto;
	}

	public void setBuildingTenantDto(List<BuildingTenantRelationBuildingUpdateDTO> buildingTenantDto) {
		this.buildingTenantDto = buildingTenantDto;
	}

	public List<BuildingPersonalDevelop> getBuildingPersonalDevelopDto() {
		return buildingPersonalDevelopDto;
	}

	public void setBuildingPersonalDevelopDto(List<BuildingPersonalDevelop> buildingPersonalDevelopDto) {
		this.buildingPersonalDevelopDto = buildingPersonalDevelopDto;
	}

	public List<Activation> getActivationDto() {
		return activationDto;
	}

	public void setActivationDto(List<Activation> activationDto) {
		this.activationDto = activationDto;
	}

	public List<BuildingFile> getBuildingFileDto() {
		return buildingFileDto;
	}

	public void setBuildingFileDto(List<BuildingFile> buildingFileDto) {
		this.buildingFileDto = buildingFileDto;
	}

	public List<BuildingImage> getBuildingImageDto() {
		return buildingImageDto;
	}

	public void setBuildingImageDto(List<BuildingImage> buildingImageDto) {
		this.buildingImageDto = buildingImageDto;
	}

	public List<BuildingMeetingHistory> getBuildingMeetingDto() {
		return buildingMeetingDto;
	}

	public void setBuildingMeetingDto(List<BuildingMeetingHistory> buildingMeetingDto) {
		this.buildingMeetingDto = buildingMeetingDto;
	}

	public List<BuildingKeymanRelationBuildingUpdateDTO> getBuildingKeymanDto() {
		return buildingKeymanDto;
	}

	public void setBuildingKeymanDto(List<BuildingKeymanRelationBuildingUpdateDTO> buildingKeymanDto) {
		this.buildingKeymanDto = buildingKeymanDto;
	}

	public List<BuildingSales> getBuildingSalesDto() {
		return buildingSalesDto;
	}

	public void setBuildingSalesDto(List<BuildingSales> buildingSalesDto) {
		this.buildingSalesDto = buildingSalesDto;
	}

	public static BuildingUpdateDTO toDTO(String json) {
		try {
			MAPPER.registerModule(new JavaTimeModule());
			return MAPPER.readValue(json, BuildingUpdateDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getMessage());
		}
	}

}
