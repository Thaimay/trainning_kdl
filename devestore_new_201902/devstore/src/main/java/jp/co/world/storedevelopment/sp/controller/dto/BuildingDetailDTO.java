package jp.co.world.storedevelopment.sp.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Activation;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.BuildingFile;
import jp.co.world.storedevelopment.model.BuildingImage;
import jp.co.world.storedevelopment.model.BuildingKeyman;
import jp.co.world.storedevelopment.model.BuildingMeetingHistory;
import jp.co.world.storedevelopment.model.BuildingPersonalDevelop;
import jp.co.world.storedevelopment.model.BuildingSales;
import jp.co.world.storedevelopment.model.BuildingTenant;
import jp.co.world.storedevelopment.model.BuildingTradeArea;
import jp.co.world.storedevelopment.model.IArea;
import jp.co.world.storedevelopment.model.IBlock;
import jp.co.world.storedevelopment.model.ICompetitionSales;
import jp.co.world.storedevelopment.model.ICorporation;
import jp.co.world.storedevelopment.model.IPrefectures;
import jp.co.world.storedevelopment.model.ISalesChannel;
import jp.co.world.storedevelopment.model.IShopSalesLatestOneYear;
import jp.co.world.storedevelopment.model.ImportantInformation;
import jp.co.world.storedevelopment.model.ImportantInformationBuilding;
import jp.co.world.storedevelopment.model.PmCorporation;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ActivationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingFileRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingImageRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingKeymanRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingMeetingHistoryRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingPersonalDevelopRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingSalesRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingTenantRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingTradeAreaRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IAreaRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBlockRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICompetitionSalesRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IPrefecturesRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesChannelRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IShopSalesLatestOneYearRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationBuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.PmCorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectRepository;

public class BuildingDetailDTO implements DTO<Building> {

	private Long id;
	private String name;
	private String buildingCd;
	private String buildingGroupId;
	private boolean isBuildingGroup;
	private Integer buildingType;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate openDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
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

	private List<NegotiationListDTO> relatedNegotiationList = new ArrayList<NegotiationListDTO>();
	private List<ProjectListDTO> relatedProjects = new ArrayList<ProjectListDTO>();

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime createdDatetime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime updateDatetime;
	private String createdAccountCode;
	private String updateAccountCode;

	public BuildingDetailDTO() {

	}

	public BuildingDetailDTO(Building building) {
		copyProperties(this, building);
	}

	public BuildingDetailDTO(Building building, Account account) {
		this(building);
		List<NegotiationListDTO> dto = NegotiationListDTO.toList(building.findRelatedNegotiations(), account);
		setRelatedNegotiationList(dto);
		setRelatedProjects(building, account);
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

	public List<BuildingPersonalDevelopRelationBuildingDetailDTO> getBuildingPersonalDevelops() {
		List<BuildingPersonalDevelopRelationBuildingDetailDTO> dtos = new ArrayList<>();

		if (getBuildingCd() == null || getBuildingCd().isEmpty()) {
			return dtos;
		}

		List<BuildingPersonalDevelop> buildingPersonalDevelops = new BuildingPersonalDevelopRepository()
				.findByBuildingCd(getBuildingCd());
		return buildingPersonalDevelops != null && buildingPersonalDevelops.size() > 0 ? buildingPersonalDevelops
				.stream().map(x -> new BuildingPersonalDevelopRelationBuildingDetailDTO(x)).collect(Collectors.toList())
				: dtos;
	}

	public List<BuildingTenantRelationBuildingDetailDTO> getBuildingTenants() {
		List<BuildingTenantRelationBuildingDetailDTO> dtos = new ArrayList<>();

		if (getBuildingCd() == null || getBuildingCd().isEmpty()) {
			return dtos;
		}

		List<BuildingTenant> buildingTenants = new BuildingTenantRepository().findByBuildingCd(getBuildingCd());
		return buildingTenants != null && buildingTenants.size() > 0 ? buildingTenants.stream()
				.map(x -> new BuildingTenantRelationBuildingDetailDTO(x)).collect(Collectors.toList()) : dtos;
	}

	public List<PmCorporationRelationBuildingDetailDTO> getPmCorporations() {
		List<PmCorporationRelationBuildingDetailDTO> dtos = new ArrayList<>();

		if (getBuildingCd() == null || getBuildingCd().isEmpty()) {
			return dtos;
		}

		List<PmCorporation> pmCorporations = new PmCorporationRepository().findByBuildingCd(getBuildingCd());
		return pmCorporations != null && pmCorporations.size() > 0 ? pmCorporations.stream()
				.map(x -> new PmCorporationRelationBuildingDetailDTO(x)).collect(Collectors.toList()) : dtos;
	}

	public List<BuildingKeymanRelationBuildingDetailDTO> getBuildingKeymans() {
		List<BuildingKeymanRelationBuildingDetailDTO> dtos = new ArrayList<>();

		if (getBuildingCd() == null || getBuildingCd().isEmpty()) {
			return dtos;
		}

		List<BuildingKeyman> buildingKeymans = new BuildingKeymanRepository().findByBuildingCd(getBuildingCd());
		return buildingKeymans != null && buildingKeymans.size() > 0 ? buildingKeymans.stream()
				.map(x -> new BuildingKeymanRelationBuildingDetailDTO(x)).collect(Collectors.toList()) : dtos;
	}

	public List<ActivationRelationBuildingDetailDTO> getActivations() {
		List<ActivationRelationBuildingDetailDTO> dtos = new ArrayList<>();

		if (getBuildingCd() == null || getBuildingCd().isEmpty()) {
			return dtos;
		}

		List<Activation> activations = new ActivationRepository().findByBuildingCd(getBuildingCd());
		return activations != null && activations.size() > 0
				? activations.stream().map(x -> new ActivationRelationBuildingDetailDTO(x)).collect(Collectors.toList())
				: dtos;
	}

	public List<IShopSalesLatestOneYearRelationBuildingDetailDTO> getiShopSalesLatestOneYears() {
		List<IShopSalesLatestOneYearRelationBuildingDetailDTO> dtos = new ArrayList<>();

		if (getBuildingCd() == null || getBuildingCd().isEmpty()) {
			return dtos;
		}

		List<IShopSalesLatestOneYear> iShopSalesLatestOneYears = new IShopSalesLatestOneYearRepository()
				.findByBuildingCd(getBuildingCd());
		return iShopSalesLatestOneYears != null && iShopSalesLatestOneYears.size() > 0 ? iShopSalesLatestOneYears
				.stream().map(x -> new IShopSalesLatestOneYearRelationBuildingDetailDTO(x)).collect(Collectors.toList())
				: dtos;
	}

	public List<ICompetitionSalesRelationBuildingDetailDTO> getiCompetitionSales() {
		List<ICompetitionSalesRelationBuildingDetailDTO> dtos = new ArrayList<>();

		if (getBuildingCd() == null || getBuildingCd().isEmpty()) {
			return dtos;
		}

		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");

		int n5Num = Integer.parseInt(now.plusMonths(-6).format(formatter));
		int n4Num = Integer.parseInt(now.plusMonths(-5).format(formatter));
		int n3Num = Integer.parseInt(now.plusMonths(-4).format(formatter));
		int n2Num = Integer.parseInt(now.plusMonths(-3).format(formatter));
		int n1Num = Integer.parseInt(now.plusMonths(-2).format(formatter));
		int nNum = Integer.parseInt(now.plusMonths(-1).format(formatter));

		List<ICompetitionSales> iCompetitionSaless = new ICompetitionSalesRepository()
				.findByBuildingCdFromTo(getBuildingCd(), n5Num, nNum);
		if (iCompetitionSaless != null) {

			List<Long> shopList = new ArrayList<Long>(new HashSet<Long>(
					iCompetitionSaless.stream().map(x -> x.getCompetitionShopId()).collect(Collectors.toList())));

			shopList.stream().forEach(x -> {
				ICompetitionSalesRelationBuildingDetailDTO dto = new ICompetitionSalesRelationBuildingDetailDTO();
				dto.setCompetitionShopId(x);

				Stream<ICompetitionSales> iCompetitionSalessFilter = iCompetitionSaless.stream()
						.filter(f -> f.getCompetitionShopId().equals(x));
				iCompetitionSalessFilter.forEach(y -> {
					dto.setCompetitionShopName(y.getCompetitionShopName());
					dto.setTsuboNum(y.getTsuboNum());
					if (y.getYearMonth().intValue() == n5Num) {
						dto.setN5Num(y.getSalesRatio());
					} else if (y.getYearMonth().intValue() == n4Num) {
						dto.setN4Num(y.getSalesRatio());
					} else if (y.getYearMonth().intValue() == n3Num) {
						dto.setN3Num(y.getSalesRatio());
					} else if (y.getYearMonth().intValue() == n2Num) {
						dto.setN2Num(y.getSalesRatio());
					} else if (y.getYearMonth().intValue() == n1Num) {
						dto.setN1Num(y.getSalesRatio());
					} else if (y.getYearMonth().intValue() == nNum) {
						dto.setnNum(y.getSalesRatio());
					}
				});
				dtos.add(dto);
			});
		}

		return dtos;
	}

	public List<BuildingMeetingHistoryRelationBuildingDetailDTO> getBuildingMeetingHistorys() {
		List<BuildingMeetingHistoryRelationBuildingDetailDTO> dtos = new ArrayList<>();

		if (getBuildingCd() == null || getBuildingCd().isEmpty()) {
			return dtos;
		}

		List<BuildingMeetingHistory> buildingMeetingHistorys = new BuildingMeetingHistoryRepository()
				.findByBuildingCd(getBuildingCd());
		return buildingMeetingHistorys != null && buildingMeetingHistorys.size() > 0 ? buildingMeetingHistorys.stream()
				.map(x -> new BuildingMeetingHistoryRelationBuildingDetailDTO(x)).collect(Collectors.toList()) : dtos;
	}

	public List<BuildingFileRelationBuildingDetailDTO> getBuildingFiles() {
		List<BuildingFileRelationBuildingDetailDTO> dtos = new ArrayList<>();

		if (getId() == null) {
			return dtos;
		}

		List<BuildingFile> buildingFiles = new BuildingFileRepository().findByBuildingId(getId());
		return buildingFiles != null && buildingFiles.size() > 0 ? buildingFiles.stream()
				.map(x -> new BuildingFileRelationBuildingDetailDTO(x)).collect(Collectors.toList()) : dtos;
	}

	public List<BuildingImageRelationBuildingDetailDTO> getBuildingImages() {
		List<BuildingImageRelationBuildingDetailDTO> dtos = new ArrayList<>();

		if (getId() == null) {
			return dtos;
		}

		List<BuildingImage> buildingImages = new BuildingImageRepository().findByBuildingId(getId());
		return buildingImages != null && buildingImages.size() > 0 ? buildingImages.stream()
				.map(x -> new BuildingImageRelationBuildingDetailDTO(x)).collect(Collectors.toList()) : dtos;
	}

	public BuildingTradeAreaRelationBuildingDetailDTO getBuildingTradeArea() {
		if (getBuildingCd() == null || getBuildingCd().isEmpty()) {
			return null;
		}

		BuildingTradeArea buildingTradeArea = new BuildingTradeAreaRepository()
				.getBuildingTradeAreaByBuildingCd(getBuildingCd());
		return buildingTradeArea != null ? new BuildingTradeAreaRelationBuildingDetailDTO(buildingTradeArea) : null;
	}

	public List<BuildingChildrenRelationBuildingDetailDTO> getBuildingChildrens() {
		List<BuildingChildrenRelationBuildingDetailDTO> dtos = new ArrayList<>();

		if (getBuildingCd() == null || getBuildingCd().isEmpty()) {
			return dtos;
		}

		if (getIsBuildingGroup()) {
			List<Building> buildingChildrens = new BuildingRepository().getListChildrenBuilding(getBuildingCd());
			return buildingChildrens != null && buildingChildrens.size() > 0 ? buildingChildrens.stream()
					.map(x -> new BuildingChildrenRelationBuildingDetailDTO(x)).collect(Collectors.toList()) : dtos;
		}
		return dtos;
	}

	public BuildingParentRelationBuildingDetailDTO getBuildingParent() {
		if (getBuildingGroupId() != null && !getBuildingGroupId().isEmpty()) {
			Building buildingParent = new BuildingRepository().getBuildingByBuildingCd(getBuildingGroupId());
			return buildingParent != null ? new BuildingParentRelationBuildingDetailDTO(buildingParent) : null;
		}
		return null;
	}

	public IAreaRelationBuildingDetailDTO getiArea() {
		if (getiAreaId() == null) {
			return null;
		}

		Optional<IArea> iArea = new IAreaRepository().findById(getiAreaId());
		return iArea.isPresent() ? new IAreaRelationBuildingDetailDTO(iArea.get()) : null;
	}

	public IBlockRelationBuildingDetailDTO getiBlock() {
		if (getiBlockId() == null) {
			return null;
		}

		Optional<IBlock> iBlock = new IBlockRepository().findById(getiBlockId());
		return iBlock.isPresent() ? new IBlockRelationBuildingDetailDTO(iBlock.get()) : null;
	}

	public ICorporationRelationBuildingDetailDTO getiCorporation() {
		if (getiCorporationId() == null) {
			return null;
		}

		Optional<ICorporation> iCorporation = new ICorporationRepository().findById(getiCorporationId());
		return iCorporation.isPresent() ? new ICorporationRelationBuildingDetailDTO(iCorporation.get()) : null;
	}

	public ISalesChannelRelationBuildingDetailDTO getiSalesChannel() {
		if (getiSalesChannelId() == null) {
			return null;
		}

		Optional<ISalesChannel> iSalesChannel = new ISalesChannelRepository().findById(getiSalesChannelId());
		return iSalesChannel.isPresent() ? new ISalesChannelRelationBuildingDetailDTO(iSalesChannel.get()) : null;
	}

	public ISalesChannelRelationBuildingDetailDTO getiSalesChannel2() {
		if (getSalesChannelCd2() == null) {
			return null;
		}

		Optional<ISalesChannel> iSalesChannel = new ISalesChannelRepository().findById(getSalesChannelCd2());
		return iSalesChannel.isPresent() ? new ISalesChannelRelationBuildingDetailDTO(iSalesChannel.get()) : null;
	}

	public IPrefecturesRelationBuildingDetailDTO getiPrefectures() {
		if (getiPrefecturesId() == null) {
			return null;
		}

		Optional<IPrefectures> iPrefectures = new IPrefecturesRepository().findById(getiPrefecturesId());
		return iPrefectures.isPresent() ? new IPrefecturesRelationBuildingDetailDTO(iPrefectures.get()) : null;
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

	public String getUpdateAccountName() {
		if (getUpdateAccountCode() == null || getUpdateAccountCode().isEmpty()) {
			return null;
		}

		Optional<Account> updateAccount = new AccountRepository().findByCode(getUpdateAccountCode());
		return updateAccount.isPresent() ? updateAccount.get().getFullName() : "";
	}

	public List<ImportantInformationDetailDTO> getImportantInformations() {
		List<ImportantInformationDetailDTO> dtos = new ArrayList<>();

		if (getId() == null) {
			return dtos;
		}

		List<ImportantInformation> lstImportantInformation = null;
		List<ImportantInformationBuilding> lstImportantInformationBuilding = new ImportantInformationBuildingRepository()
				.findByBuildingId(getId());

		if (lstImportantInformationBuilding != null && lstImportantInformationBuilding.size() > 0) {
			lstImportantInformation = lstImportantInformationBuilding.stream()
					.map(x -> new ImportantInformationRepository().findById(x.getImportantInformationId()).get())
					.collect(Collectors.toList());
		}
		return lstImportantInformation != null && lstImportantInformation.size() > 0 ? lstImportantInformation.stream()
				.map(x -> new ImportantInformationDetailDTO(x)).collect(Collectors.toList()) : dtos;
	}

	@Override
	public Building createModel() {
		return new Building();
	}

	public List<NegotiationListDTO> getRelatedNegotiationList() {
		return relatedNegotiationList;
	}

	public void setRelatedNegotiationList(List<NegotiationListDTO> list) {
		relatedNegotiationList = list;
	}

	public void setRelatedProjects(Building building, Account account) {
		List<ProjectListDTO> dtos = new ArrayList<>();

		List<Project> projects = new ProjectRepository().getProjectListByBuildingId(building.getId());
		this.relatedProjects = projects != null && projects.size() > 0
				? projects.stream().map(x -> new ProjectListDTO(x, account)).collect(Collectors.toList())
				: dtos;
	}

	public List<ProjectListDTO> getRelatedProjects() {
		return relatedProjects;
	}

}
