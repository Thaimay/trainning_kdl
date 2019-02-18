package jp.co.world.storedevelopment.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.BuildingModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingKeymanRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.KeymanRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectRepository;

/**
 * @author hungdh
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class Building extends ActiveModel<Building> {

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

	private String[] ignoreFields = new String[] {};

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	public void setIgnoreFields(String[] ignoreFields) {
		this.ignoreFields = ignoreFields;
	}

	public Building(String name) {
		this.name = name;
	}

	public Building() {

	}
	
	public List<Project> relatedProjects() {
		return new ProjectRepository().findByBuilding(this);
	}
	
	public Long corporationGroupId() {
		Optional<ICorporation> model = new ICorporationRepository().findById(getiCorporationId());
		
		if (model.isPresent()) {
			return model.get().getCorporationGpId();
		} else {
			return null;
		}
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

	@Override
	protected ModelMapper<Building> modelMapper(SqlSession session) {
		return session.getMapper(BuildingModelMapper.class);
	}

	public List<Negotiation> findRelatedNegotiations() {
		return new NegotiationRepository().findByBuilding(getId());
	}

	public void addKeyman(IBusinessCard card) {
		Keyman keyman = new KeymanRepository().findByBusinessCardId(card.getId()).orElseGet(() -> {
			return new Keyman(card).create();
		});

		if (new BuildingKeymanRepository().existsByKeyman(this, keyman) == false) {
			new BuildingKeyman(this, keyman).create();
		}
	}

}
