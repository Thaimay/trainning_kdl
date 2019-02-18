package jp.co.world.storedevelopment.pc.controller.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.Activation;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.BuildingFile;
import jp.co.world.storedevelopment.model.BuildingImage;
import jp.co.world.storedevelopment.model.BuildingMeetingHistory;
import jp.co.world.storedevelopment.model.BuildingPersonalDevelop;
import jp.co.world.storedevelopment.model.BuildingSales;
import jp.co.world.storedevelopment.model.BuildingTradeArea;
import jp.co.world.storedevelopment.model.PmCorporation;
import jp.co.world.storedevelopment.pc.controller.dto.BuildingKeymanRelationBuildingCreateDTO;
import jp.co.world.storedevelopment.pc.controller.dto.BuildingTenantRelationBuildingCreateDTO;
import jp.co.world.storedevelopment.pc.controller.dto.DTO;

public class BuildingCreateForm implements DTO<Building> {

	protected Long id;
	private String corporationGroup = "001";
	private String name;
	private Long originBuildingId;
	private String buildingCd;
	private String buildingGroupId;
	private Boolean isBuildingGroup;
	private Integer buildingType;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate openDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate closeDate;
	private Long iCorporationId;
	private String owner;
	private Long iSalesChannelId;
	private String locationFirst;
	private String locationSecond;
	private Long iBlockId;
	private Long iAreaId;
	private String smallMarketArea;
	private Long salesChannelCd2;
	private String adoptDifficulty;
	private String address;
	private Long iPrefecturesId;
	private String homepageUrl;
	private String jiscd;
	private String groundFloor;
	private String attractingContent;
	private String coreTenant;
	private String subCoreTenant;
	private String structure;
	private String parkingNumber;
	private String businessHours;
	private String nearestStation;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private Integer tenantNumber;
	private String note;
	private String imagePath;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime createdDatetime = LocalDateTime.now();
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime updateDatetime = LocalDateTime.now();
	private String createdAccountCode = "";
	private String updateAccountCode = "";
	private Boolean isDeleted = false;

	private List<PmCorporation> pmCorporationDto;
	private List<BuildingTenantRelationBuildingCreateDTO> buildingTenantDto;
	private List<BuildingPersonalDevelop> buildingPersonalDevelopDto;
	private List<BuildingKeymanRelationBuildingCreateDTO> buildingKeymanDto;
	private List<Activation> activationDto;
	private List<BuildingFile> buildingFileDto;
	private List<BuildingImage> buildingImageDto;
	private List<BuildingMeetingHistory> buildingMeetingDto;
	private List<BuildingSales> buildingSalesDto;
	private BuildingTradeArea buildingTradeAreaDto;

	public BuildingCreateForm() {
		//
	}

	public BuildingCreateForm(Building building) {
		this.copyProperties(this, building);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCorporationGroup() {
		return corporationGroup;
	}

	public void setCorporationGroup(String corporationGroup) {
		this.corporationGroup = corporationGroup;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getOriginBuildingId() {
		return originBuildingId;
	}

	public void setOriginBuildingId(Long originBuildingId) {
		this.originBuildingId = originBuildingId;
	}

	public String getBuildingCd() {
		return buildingCd;
	}

	public void setBuildingCd(String buildingCd) {
		this.buildingCd = buildingCd;
	}

	public String getBuildingGroupId() {
		return buildingGroupId;
	}

	public void setBuildingGroupId(String buildingGroupId) {
		this.buildingGroupId = buildingGroupId;
	}

	public Boolean getIsBuildingGroup() {
		return isBuildingGroup;
	}

	public void setIsBuildingGroup(Boolean isBuildingGroup) {
		this.isBuildingGroup = isBuildingGroup;
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

	public Long getiCorporationId() {
		return iCorporationId;
	}

	public void setiCorporationId(Long iCorporationId) {
		this.iCorporationId = iCorporationId;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Long getiSalesChannelId() {
		return iSalesChannelId;
	}

	public void setiSalesChannelId(Long iSalesChannelId) {
		this.iSalesChannelId = iSalesChannelId;
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

	public String getSmallMarketArea() {
		return smallMarketArea;
	}

	public void setSmallMarketArea(String smallMarketArea) {
		this.smallMarketArea = smallMarketArea;
	}

	public Long getSalesChannelCd2() {
		return salesChannelCd2;
	}

	public void setSalesChannelCd2(Long salesChannelCd2) {
		this.salesChannelCd2 = salesChannelCd2;
	}

	public String getAdoptDifficulty() {
		return adoptDifficulty;
	}

	public void setAdoptDifficulty(String adoptDifficulty) {
		this.adoptDifficulty = adoptDifficulty;
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

	public String getAttractingContent() {
		return attractingContent;
	}

	public void setAttractingContent(String attractingContent) {
		this.attractingContent = attractingContent;
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

	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
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

	public Integer getTenantNumber() {
		return tenantNumber;
	}

	public void setTenantNumber(Integer tenantNumber) {
		this.tenantNumber = tenantNumber;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public LocalDateTime getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(LocalDateTime createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public LocalDateTime getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(LocalDateTime updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public String getCreatedAccountCode() {
		return createdAccountCode;
	}

	public void setCreatedAccountCode(String createdAccountCode) {
		this.createdAccountCode = createdAccountCode;
	}

	public String getUpdateAccountCode() {
		return updateAccountCode;
	}

	public void setUpdateAccountCode(String updateAccountCode) {
		this.updateAccountCode = updateAccountCode;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<PmCorporation> getPmCorporationDto() {
		return pmCorporationDto;
	}

	public void setPmCorporationDto(List<PmCorporation> pmCorporationDto) {
		this.pmCorporationDto = pmCorporationDto;
	}

	public List<BuildingTenantRelationBuildingCreateDTO> getBuildingTenantDto() {
		return buildingTenantDto;
	}

	public void setBuildingTenantDto(List<BuildingTenantRelationBuildingCreateDTO> buildingTenantDto) {
		this.buildingTenantDto = buildingTenantDto;
	}

	public List<BuildingPersonalDevelop> getBuildingPersonalDevelopDto() {
		return buildingPersonalDevelopDto;
	}

	public void setBuildingPersonalDevelopDto(List<BuildingPersonalDevelop> buildingPersonalDevelopDto) {
		this.buildingPersonalDevelopDto = buildingPersonalDevelopDto;
	}

	public List<BuildingKeymanRelationBuildingCreateDTO> getBuildingKeymanDto() {
		return buildingKeymanDto;
	}

	public void setBuildingKeymanDto(List<BuildingKeymanRelationBuildingCreateDTO> buildingKeymanDto) {
		this.buildingKeymanDto = buildingKeymanDto;
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

	public List<BuildingSales> getBuildingSalesDto() {
		return buildingSalesDto;
	}

	public void setBuildingSalesDto(List<BuildingSales> buildingSalesDto) {
		this.buildingSalesDto = buildingSalesDto;
	}

	public BuildingTradeArea getBuildingTradeAreaDto() {
		return buildingTradeAreaDto;
	}

	public void setBuildingTradeAreaDto(BuildingTradeArea buildingTradeAreaDto) {
		this.buildingTradeAreaDto = buildingTradeAreaDto;
	}

	@Override
	public Building createModel() {
		return new Building();
	}

}
