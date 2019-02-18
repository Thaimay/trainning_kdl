package jp.co.world.storedevelopment.dev;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

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
import jp.co.world.storedevelopment.model.Competition;
import jp.co.world.storedevelopment.model.Division;
import jp.co.world.storedevelopment.model.IArea;
import jp.co.world.storedevelopment.model.IBlock;
import jp.co.world.storedevelopment.model.IBusinessCard;
import jp.co.world.storedevelopment.model.ICompetitionSales;
import jp.co.world.storedevelopment.model.IContractType;
import jp.co.world.storedevelopment.model.ICorporation;
import jp.co.world.storedevelopment.model.ICorporationGroup;
import jp.co.world.storedevelopment.model.IIncomeUnit;
import jp.co.world.storedevelopment.model.IPlace;
import jp.co.world.storedevelopment.model.IPrefectures;
import jp.co.world.storedevelopment.model.IRentContract;
import jp.co.world.storedevelopment.model.ISalesAgencyTarget;
import jp.co.world.storedevelopment.model.ISalesChannel;
import jp.co.world.storedevelopment.model.IShop;
import jp.co.world.storedevelopment.model.IShopCompany;
import jp.co.world.storedevelopment.model.IShopSalesLatestOneYear;
import jp.co.world.storedevelopment.model.ImportantInformation;
import jp.co.world.storedevelopment.model.ImportantInformationBuilding;
import jp.co.world.storedevelopment.model.Keyman;
import jp.co.world.storedevelopment.model.MCreditRank;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.ParticipatingStoreCorporation;
import jp.co.world.storedevelopment.model.PmCorporation;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.SalesTrend;
import jp.co.world.storedevelopment.model.Shop;
import jp.co.world.storedevelopment.model.ShopImage;
import jp.co.world.storedevelopment.model.Tenant;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IAreaRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBlockRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBusinessCardRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IIncomeUnitRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IPlaceRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IPrefecturesRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesAgencyTargetRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesChannelRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IShopCompanyRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.KeymanRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MCreditRankRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ParticipatingStoreCorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.TenantRepository;

public class InitBuildingData extends InitTestDataSupport {
	public ICorporation iCorporation;
	public ISalesChannel iSalesChannel;
	public IBlock iBlock;
	public IArea iArea;
	public IPrefectures iPrefectures;
	public ISalesAgencyTarget iSalesAgencyTarget;
	public ParticipatingStoreCorporation participatingStoreCorporation;
	public Tenant tenant;
	public Competition competition;
	public MCreditRank mCreditRank;
	public IIncomeUnit iIncomeUnit;
	public Keyman keyman;
	public IPlace iPlace;
	public ImportantInformation importantInformation;
	public IBusinessCard iBusinessCard;
	public IShopCompany iShopCompany;

	@Override
	public void init(InitTestData main) {
		MAIN_ACCOUNT = main.MAIN_ACCOUNT;
		initMainData(main);
		IntStream.range(1, GlobalVariable.buildingSize).forEach(i -> mockUpBuildingData(i));
	}

	public Account getAccount() {
		return MAIN_ACCOUNT;
	}

	public void initMainData(InitTestData main) {
		iCorporation = new ICorporationRepository().getHead().get();
		iSalesChannel = new ISalesChannelRepository().getHead().get();
		iBlock = new IBlockRepository().getHead().get();
		iArea = new IAreaRepository().getHead().get();
		iPrefectures = new IPrefecturesRepository().getHead().get();
		tenant = new TenantRepository().getHead().get();
		competition = createCompetition(0);
		mCreditRank = new MCreditRankRepository().getHead().get();
		iSalesAgencyTarget = new ISalesAgencyTargetRepository().getHead().get();
		participatingStoreCorporation = new ParticipatingStoreCorporationRepository().getHead().get();
		iIncomeUnit = new IIncomeUnitRepository().getHead().get();
		keyman = new KeymanRepository().getHead().get();
		iPlace = new IPlaceRepository().getHead().get();
		importantInformation = new ImportantInformationRepository().getHead().get();
		iBusinessCard = new IBusinessCardRepository().getHead().get();
		iShopCompany = new IShopCompanyRepository().getHead().get();
	}

	public IBusinessCard createIBusinessCard(int i) {
		IBusinessCard businessCard = new IBusinessCard("大市" + i);
		businessCard.create();
		return businessCard;
	}

	public Building initBuilding(int partOfName) {

		Building building = new Building();

		// properties
		building.setBuildingCd(String.valueOf(6000 + partOfName));

		building.setOriginBuildingId(Long.valueOf(partOfName));
		String[] buildingGroupId = new String[] { "6002", "6004", "6006" };
		if (building.getBuildingCd().equals("6001")) {
			building.setName("館名_Not_Parent" + partOfName);
			building.setBuildingGroupId("");
			building.setIsBuildingGroup(false);
		} else if (building.getBuildingCd().equals("6002")) {
			building.setName("館名_Parent" + partOfName);
			building.setBuildingGroupId("");
			building.setIsBuildingGroup(true);
		} else {
			building.setIsBuildingGroup(partOfName % 2 == 0);
			if (building.getIsBuildingGroup()) {
				building.setName("館名_Parent" + partOfName);
				building.setBuildingGroupId("");
			} else {
				building.setName("館名_Not_Parent" + partOfName);
				building.setBuildingGroupId(buildingGroupId[partOfName % 3]);
			}
		}

		building.setBuildingType((partOfName % 2) + 1);
		building.setOpenDate(GlobalVariable.localDate);
		building.setCloseDate(GlobalVariable.localDate);
		building.setOwner("館長" + partOfName);
		building.setLocationFirst("立地①" + partOfName);
		building.setLocationSecond("立地②" + partOfName);
		building.setSmallMarketArea("小商圏" + partOfName);
		building.setAdoptDifficulty("採用難易度" + partOfName);
		building.setAddress("住所" + partOfName);
		building.setHomepageUrl("https://www.google.com.vn/search?q=" + partOfName);
		building.setJiscd("JISCD" + partOfName);
		building.setGroundFloor("グランドフロア" + partOfName);
		building.setAttractingContent("集客コンテンツ" + partOfName);
		building.setCoreTenant("核テナント" + partOfName);
		building.setSubCoreTenant("サブ核テナント" + partOfName);
		building.setStructure("建物構造" + partOfName);
		building.setParkingNumber("駐車場台数" + partOfName);
		building.setBusinessHours("営業時間" + partOfName);
		building.setNearestStation("最寄駅" + partOfName);
		building.setLatitude(new BigDecimal(0.0435998 + RandomTestData.getInteger(-84, 84)));
		building.setLongitude(new BigDecimal(0.7920068 + RandomTestData.getInteger(-179, 179)));
		building.setTenantNumber(partOfName);
		building.setNote("メモ" + partOfName);
		building.setImagePath("img/apita.jpg?id=" + partOfName);

		return building;
	}

	public BuildingTradeArea createBuildingTradeArea(int partOfName, Building building) {
		BuildingTradeArea buildingTradeArea = new BuildingTradeArea();
		buildingTradeArea.setBuildingCd(building.getBuildingCd());
		buildingTradeArea.setTradeAreaPopulation1Km(partOfName);
		buildingTradeArea.setMale1Km(partOfName);
		buildingTradeArea.setFemale1Km(partOfName);
		buildingTradeArea.setDaytimePopulation1Km(partOfName);
		buildingTradeArea.setNighttimePopulation1Km(partOfName);
		buildingTradeArea.setRetailSales1Km(partOfName);
		buildingTradeArea.setSalesFloorArea1Km(Long.valueOf(partOfName));
		buildingTradeArea.setDepartmentOccupancy1Km(Long.valueOf(partOfName));
		buildingTradeArea.setPopulationPower1Km(Long.valueOf(partOfName));
		buildingTradeArea.setTradeAreaPower1Km(partOfName);
		buildingTradeArea.setTradeAreaPopulation3Km(partOfName);
		buildingTradeArea.setMale3Km(partOfName * 3);
		buildingTradeArea.setFemale3Km(partOfName * 3);
		buildingTradeArea.setSituatedInRetailSales3Km(partOfName * 3);
		buildingTradeArea.setWithinSalesFloorArea3Km(Long.valueOf(partOfName * 3));
		buildingTradeArea.setWithinSalesFloorAreaAddingUp3Km(Long.valueOf(partOfName * 3));
		buildingTradeArea.setDepartmentOccupancy3Km(Long.valueOf(partOfName * 3));
		buildingTradeArea.setPopulationPower3Km(Long.valueOf(partOfName * 3));
		buildingTradeArea.setTradeAreaPower3Km(partOfName * 3);
		buildingTradeArea.setTradeAreaPopulation5Km(partOfName * 5);
		buildingTradeArea.setMale5Km(partOfName * 5);
		buildingTradeArea.setFemale5Km(partOfName * 5);
		buildingTradeArea.setSituatedInRetailSales5Km(partOfName * 5);
		buildingTradeArea.setWithinSalesFloorArea5Km(Long.valueOf(partOfName * 5));
		buildingTradeArea.setWithinSalesFloorAreaAddingUp5Km(Long.valueOf(partOfName * 5));
		buildingTradeArea.setDepartmentOccupancy5Km(Long.valueOf(partOfName * 5));
		buildingTradeArea.setPopulationPower5Km(Long.valueOf(partOfName * 5));
		buildingTradeArea.setTradeAreaPower5Km(partOfName * 5);
		buildingTradeArea.setTradeAreaPopulation10Km(partOfName * 10);
		buildingTradeArea.setMale10Km(partOfName * 10);
		buildingTradeArea.setFemale10Km(partOfName * 10);
		buildingTradeArea.setSituatedInRetailSales10Km(partOfName * 10);
		buildingTradeArea.setWithinSalesFloorArea10Km(Long.valueOf(partOfName * 10));
		buildingTradeArea.setWithinSalesFloorAreaAddingUp10Km(Long.valueOf(partOfName * 10));
		buildingTradeArea.setDepartmentOccupancy10Km(Long.valueOf(partOfName * 10));
		buildingTradeArea.setPopulationPower10Km(Long.valueOf(partOfName * 10));
		buildingTradeArea.setTradeAreaPower10Km(partOfName * 10);
		buildingTradeArea.setDrivePopulationWithin5Minutes(partOfName * 5);
		buildingTradeArea.setDrivePopulationWithin15Minutes(partOfName * 15);
		buildingTradeArea.setDrivePopulationWithin20Minutes(partOfName * 20);
		buildingTradeArea.setDrivePopulationWithin30Minutes(partOfName * 30);
		buildingTradeArea.setExpensesPerPeople(partOfName);
		buildingTradeArea.setIncomePerPeople(partOfName);
		buildingTradeArea.setTradeAreaAnalysisGisPath("商圏分析GIS" + partOfName);
		buildingTradeArea.setCreatedAccountCode(new AccountRepository().getHead().get().getId().toString());
		buildingTradeArea.setCreatedDatetime(LocalDateTime.now());
		buildingTradeArea.create();
		return buildingTradeArea;
	}

	public Building createBuilding(int partOfName) {
		Building building = initBuilding(partOfName);

		// relation
		building.setCreatedAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		building.setiSalesChannelId(iSalesChannel.getId());
		building.setSalesChannelCd2(iSalesChannel.getId());
		building.setiBlockId(iBlock.getId());
		building.setiAreaId(iArea.getId());
		building.setiPrefecturesId(iPrefectures.getId());
		building.setiCorporationId(iCorporation.getId());
		building.setCreatedAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		building.setUpdateAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		building.create();
		return building;
	}

	public Project createProject(int partOfName) {
		Project project = new Project();
		project.setTitle("案件進捗" + partOfName);
		project.create();
		return project;
	}

	public BuildingPersonalDevelop createBuildingPersonalDevelop(int partOfName, Building building, Account acc) {
		BuildingPersonalDevelop buildingPersonalDevelop = new BuildingPersonalDevelop();
		String[] category = new String[] { "storeDeveloper", "branchsSales", "humanResourceLeader" };
		buildingPersonalDevelop.setBuildingCd(building.getBuildingCd());
		buildingPersonalDevelop.setCategory(category[partOfName % 3]);
		buildingPersonalDevelop.setAccountId(acc.getId());
		buildingPersonalDevelop.create();
		return buildingPersonalDevelop;
	}

	public Tenant createTenant(int partOfName) {
		Tenant tenant = new Tenant();
		tenant.setName("テナント名" + partOfName);
		tenant.create();
		return tenant;
	}

	public BuildingSales createBuildingSales(int partOfName, Building building) {
		BuildingSales buildingSales = new BuildingSales();
		buildingSales.setBuildingCd(building.getBuildingCd());
		buildingSales.setDivision(Long.valueOf(RandomTestData.getInteger(1, 3)));
		buildingSales.setSales(BigDecimal.valueOf(partOfName * 1000000));
		buildingSales.setArea(BigDecimal.valueOf(partOfName * 10));
		buildingSales.setMonthBasis(BigDecimal.valueOf(partOfName * 1000));
		buildingSales.setFinancialMonth(GlobalVariable.localDate);
		buildingSales.setType(Long.valueOf(RandomTestData.getInteger(1, 3)));
		buildingSales.create();
		return buildingSales;
	}

	public PmCorporation createPmCorporation(int partOfName, Building building, ICorporation iCorporation) {
		PmCorporation pmCorporation = new PmCorporation();
		pmCorporation.setBuildingCd(building.getBuildingCd());
		pmCorporation.setCorporationId(Long.valueOf(partOfName % 2 + 1));
		pmCorporation.setCorporationName(iCorporation.getCorporationName());
		pmCorporation.create();
		return pmCorporation;
	}

	public Activation createActivation(int partOfName, Building building) {
		Activation activation = new Activation();
		activation.setBuildingCd(building.getBuildingCd());
		activation.setContent("内容" + partOfName);
		activation.setExpectedDay(GlobalVariable.localDate);
		activation.setFloor("フロア" + partOfName);
		activation.create();
		return activation;
	}

	public ImportantInformationBuilding createImportantInformationBuilding(int partOfName, Building building,
			ImportantInformation importantInformation) {
		ImportantInformationBuilding importantInformationBuilding = new ImportantInformationBuilding();
		importantInformationBuilding.setBuildingId(building.getId());
		importantInformationBuilding.setImportantInformationId(importantInformation.getId());
		importantInformationBuilding.setCorporationGroup("001");
		importantInformationBuilding.create();
		return importantInformationBuilding;
	}

	public Negotiation createNegotiation(int partOfName) {
		Negotiation negotiation = new Negotiation();
		negotiation.setTitle("実施商談-" + partOfName);
		negotiation.setDivision("区分" + partOfName);
		negotiation.setScheduleStartDatetime(GlobalVariable.localDateTime);
		negotiation.setScheduleEndDatetime(GlobalVariable.localDateTime);
		negotiation.setImplementationStartDatetime(null);
		negotiation.setImplementationEndDatetime(null);
		negotiation.setPlace("場所" + partOfName);
		negotiation.setTitle("件名" + partOfName);
		negotiation.setContent("内容" + partOfName);
		negotiation.setPriority(partOfName);
		negotiation.setRelease(true);
		negotiation.setNextActionContent("ネクストアクション" + partOfName);
		negotiation.create();
		return negotiation;
	}

	public Competition createCompetition(int partOfName) {
		Competition competition = new Competition();
		competition.setName("コンペチ" + partOfName);
		competition.create();
		return competition;
	}

	public BuildingFile createBuildingFile(int partOfName, Building building, Account acc) throws IOException {
		BuildingFile buildingFile = new BuildingFile(createFile("sample.pdf"), building, acc);
		buildingFile.setDisplayName("ファイル名" + partOfName);
		buildingFile.setComment("メモ" + partOfName);
		buildingFile.setDivision("BUILDING");
		buildingFile.setOutputNumber(partOfName);
		buildingFile.create();
		return buildingFile;
	}

	public BuildingImage createBuildingImage(int partOfName, Building building, Account acc) throws IOException {
		String[] outputFlag = new String[] { "ON", "OFF" };
		BuildingImage buildingImage = new BuildingImage(createFile("apita.jpg"), building, acc);
		buildingImage.setDisplayName("ファイル" + partOfName);
		buildingImage.setComment("メモ" + partOfName);
		buildingImage.setDivision("BUILDING");
		buildingImage.setOutputFlag(String.valueOf(partOfName));
		buildingImage.setOutputNumber(partOfName);
		buildingImage.create();
		return buildingImage;
	}

	public BuildingMeetingHistory createBuildingMeetingHistory(int partOfName, Building building, Account acc) {
		BuildingMeetingHistory buildingMeetingHistory = new BuildingMeetingHistory();
		buildingMeetingHistory.setBuildingCd(building.getBuildingCd());
		buildingMeetingHistory.setOverview("総会概要" + partOfName);
		buildingMeetingHistory.setName("ファイル名" + partOfName);
		buildingMeetingHistory.setDate(GlobalVariable.localDateTime);
		buildingMeetingHistory.setAttendee("出席者" + partOfName);
		buildingMeetingHistory.setCreatedAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		buildingMeetingHistory.create();
		return buildingMeetingHistory;
	}

	public IShopSalesLatestOneYear createIShopSalesLatestOneYear(int partOfName, Building building, IShop iShop,
			IBlock iblock) {
		IShopSalesLatestOneYear iShopSalesLatestOneYear = new IShopSalesLatestOneYear();
		iShopSalesLatestOneYear.setBlockCd(iblock.getBlockCd());
		iShopSalesLatestOneYear.setBlockName(iblock.getBlockName());
		iShopSalesLatestOneYear.setBuildingCd(building.getBuildingCd());
		iShopSalesLatestOneYear.setBuildingName(building.getName());
		iShopSalesLatestOneYear.setShopCd(iShop.getShopCd());
		iShopSalesLatestOneYear.setShopName(iShop.getShopNameZenkaku());
		iShopSalesLatestOneYear.setOpenDate(GlobalVariable.localDate);
		iShopSalesLatestOneYear.setCloseDate(GlobalVariable.localDate);
		iShopSalesLatestOneYear.setRemodelingDate(GlobalVariable.localDate);
		iShopSalesLatestOneYear.setTsuboNum(new BigDecimal(partOfName));
		iShopSalesLatestOneYear.setFloor("フロア" + partOfName);
		iShopSalesLatestOneYear.setSalesAchievement(new BigDecimal(partOfName));
		iShopSalesLatestOneYear.setSalesAchievementComposition(new BigDecimal(partOfName));
		iShopSalesLatestOneYear.setSalesAchievementLastYearDifferenceComposition(new BigDecimal(partOfName));
		iShopSalesLatestOneYear.setSalesAchievementYearToYear(new BigDecimal(partOfName));
		iShopSalesLatestOneYear.setSalesAchievementLastYearDifference(new BigDecimal(partOfName));
		iShopSalesLatestOneYear.setOperatingProfitAchievement(new BigDecimal(partOfName));
		iShopSalesLatestOneYear.setOperatingProfitAchievementComposition(new BigDecimal(partOfName));
		iShopSalesLatestOneYear.setOperatingProfitLastYearDifferenceComposition(new BigDecimal(partOfName));
		iShopSalesLatestOneYear.setOperatingProfitYearToYear(new BigDecimal(partOfName));
		iShopSalesLatestOneYear.setOperatingProfitLastYearDifference(new BigDecimal(partOfName));
		iShopSalesLatestOneYear.setMonthAreaSalesAchievement(new BigDecimal(partOfName));
		iShopSalesLatestOneYear.setMonthAreaSalesYearToYear(new BigDecimal(partOfName));
		iShopSalesLatestOneYear.setMonthAreaSalesLastYearDifference(new BigDecimal(partOfName));
		iShopSalesLatestOneYear.setDispOrder(Long.valueOf(partOfName));
		iShopSalesLatestOneYear.create();
		return iShopSalesLatestOneYear;
	}

	public ICompetitionSales createICompetitionSales(int partOfName, Building building, Competition competition) {
		ICompetitionSales iCompetitionSales = new ICompetitionSales();
		iCompetitionSales.setBuildingCd(building.getBuildingCd());
		iCompetitionSales.setBuildingName(building.getName());
		iCompetitionSales.setCompetitionShopId(competition.getId());
		iCompetitionSales.setCompetitionShopName(competition.getName());
		iCompetitionSales.setTsuboNum(new BigDecimal(1));
		BigDecimal[] yearMonth = new BigDecimal[] { new BigDecimal(201802), new BigDecimal(201801),
				new BigDecimal(201712), new BigDecimal(201711), new BigDecimal(201710), new BigDecimal(201709) };
		iCompetitionSales.setYearMonth(yearMonth[partOfName]);
		iCompetitionSales.setSalesRatio(new BigDecimal(RandomTestData.getInteger(2)));
		iCompetitionSales.setInputActiveDays(partOfName);
		iCompetitionSales.setDispOrder(partOfName);
		iCompetitionSales.create();
		return iCompetitionSales;
	}

	public BuildingKeyman createBuildingKeyman(int partOfName, Building building, Keyman keyman) {
		BuildingKeyman buildingKeyman = new BuildingKeyman();
		String[] category = new String[] { "HQ leasing", "Building leasing", "Inside control" };
		String[] role = new String[] { "HQ Leasing", "Building Leasing", "Building Promotion", "Interior Management" };
		String[] star = new String[] { "0", "1", "2", "3" };
		buildingKeyman.setBuildingCd(building.getBuildingCd());
		buildingKeyman.setKeymanId(keyman.getId());
		buildingKeyman.setCategory(category[partOfName % 3]);
		buildingKeyman.setPriority(star[partOfName % 4]);
		buildingKeyman.setRole(role[partOfName % 4]);
		buildingKeyman.setNote("メモ" + partOfName);
		buildingKeyman.create();
		return buildingKeyman;
	}

	public Keyman createKeyman(int partOfName) {
		Keyman keyman = new Keyman();
		keyman.setBusinessCardId(iBusinessCard.getId());
		keyman.setCorporationId(iCorporation.getId());
		keyman.create();
		return keyman;
	}

	public BuildingTenant createBuildingTenant(int partOfName, Building building, Tenant tenant) {
		BuildingTenant buildingTenant = new BuildingTenant();
		buildingTenant.setBuildingCd(building.getBuildingCd());
		buildingTenant.setTenantId(tenant.getId());
		buildingTenant.create();
		return buildingTenant;
	}

	public SalesTrend createSalesTrend(int partOfName, Building building) {
		SalesTrend salesTrend = new SalesTrend();
		salesTrend.setBuildingCd(building.getBuildingCd());
		salesTrend.setPeriod("期" + partOfName);
		salesTrend.setCategory("カテゴリ" + partOfName);
		salesTrend.create();
		return salesTrend;
	}

	public IPlace createIPlace(int partOfName) {
		IPlace iPlace = new IPlace();
		iPlace.setPlaceId(Long.valueOf(1L));
		iPlace.setPlaceName("場所名" + partOfName);
		iPlace.create();
		return iPlace;
	}

	public Shop createShop(int partOfName, IShop iShop) {
		Shop shop = new Shop();
		shop.setiShopId(iShop.getId());
		shop.setSection("区画" + partOfName);
		shop.setFrontage("間口" + partOfName);
		shop.setiSalesAgencyTargetId(iSalesAgencyTarget.getId());
		shop.setParticipatingStoreCorporationId(participatingStoreCorporation.getId());
		shop.setBuildingExpectedValue(partOfName);
		shop.setRemarks("メモ" + partOfName);
		shop.setImagePath("img/apita.jpg?id=" + partOfName);
		shop.create();

		return shop;
	}

	public IShop createIShop(int partOfName) {
		IShop iShop = new IShop();
		iShop.setShopId(Long.valueOf(RandomTestData.getInteger(4)));
		iShop.setShopCd("店舗CD" + RandomTestData.getString(4, true, true));
		iShop.setShopNameZenkaku("店舗名（全角）" + (partOfName + RandomTestData.getInteger(3)));
		iShop.setShopNameZenkakuHankaku("店舗名（全角半角）" + partOfName);
		iShop.setShopNameKana("店舗名（ｶﾅ）" + partOfName);
		iShop.setShopAbbreviationName("店舗略名" + partOfName);
		iShop.setShopAbbreviationNameAccounting("会計用店舗略名" + partOfName);
		iShop.setPostCd(String.valueOf(partOfName));
		iShop.setPrefecturesName("都道府県名" + partOfName);
		iShop.setCityName("市区町村名" + partOfName);
		iShop.setTownNameAddress("町名・番地" + partOfName);
		iShop.setBuildingName("ビル名" + partOfName);
		iShop.setFloorNum("フロア番号" + partOfName);
		iShop.setShopAddress("店舗住所" + partOfName);
		iShop.setShopAddressOld("移行用店舗住所" + partOfName);
		iShop.setShopAddressKana1("店舗住所（ｶﾅ）1" + partOfName);
		iShop.setShopAddressKana2("店舗住所（ｶﾅ）2" + partOfName);
		iShop.setPhoneNumber("電話番号" + partOfName);
		iShop.setFaxNumber("FAX番号" + partOfName);
		iShop.setiShopCompanyId(iShopCompany.getId());
		iShop.setJisPrefecturesCd(String.valueOf(partOfName));
		iShop.setJisDistrictCd(String.valueOf(partOfName));
		iShop.setMarketId(Long.valueOf(partOfName));
		iShop.setTransactionGp(String.valueOf(partOfName));
		iShop.setShopTownParentId("商店街親ID" + partOfName);
		iShop.setConsolidationId("集約ID" + partOfName);
		iShop.setCompoundShopDivisionId(Long.valueOf(partOfName));
		iShop.setiIncomeUnitId(Long.valueOf(1));
		iShop.setContractTsubo(new BigDecimal(partOfName));
		iShop.setEffectiveTsubo(new BigDecimal(partOfName));
		iShop.setBackyardTsubo(new BigDecimal(partOfName));
		iShop.setContractArea(new BigDecimal(partOfName));
		iShop.setEffectiveArea(new BigDecimal(partOfName));
		iShop.setBackyardArea(new BigDecimal(partOfName));
		iShop.setStatusId(Long.valueOf(partOfName));
		iShop.setOpenDate(GlobalVariable.localDate);
		iShop.setPreOpenDate(GlobalVariable.localDate);
		iShop.setCloseDate(GlobalVariable.localDate);
		iShop.setSalesAgencyId(Long.valueOf(partOfName));
		iShop.setSalesChannelId(Long.valueOf(partOfName));
		iShop.setIsEnhancedShop(String.valueOf("E"));
		iShop.setSegmentDivisionId(Long.valueOf(partOfName));
		iShop.setAccountingStartDate(GlobalVariable.localDate);
		iShop.setAccountingEndDate(GlobalVariable.localDate);
		iShop.setDepartmentStoreUnificationCd(String.valueOf(partOfName));
		iShop.setPlaceId(Long.valueOf(partOfName));
		iShop.setWholesaleAreaId(iArea.getId());
		iShop.setHtposDivisionId(Long.valueOf(partOfName));
		iShop.setStepsRateCalculationDivisionId(Long.valueOf(partOfName));
		iShop.setDepreciationOfFixedAssetsDivisionId(Long.valueOf(partOfName));
		iShop.setRelationUpdateDatetime(GlobalVariable.localDateTime);
		iShop.setCoordinationCreatedDatetime(LocalDateTime.now());
		iShop.setCoordinationCreatedAccountCode("連携元登録者CD" + partOfName);
		iShop.setCoordinationUpdateDatetime(LocalDateTime.now());
		iShop.setCoordinationUpdateAccountCode("連携元登録者CD" + partOfName);
		iShop.setAction("0");
		iShop.setIsWsdCoordinated(partOfName % 2 == 0);
		iShop.create();

		return iShop;
	}

	public Shop createShopIndex(int partOfName, IShop iShop, int i) {
		Shop shop = new Shop();
		shop.setiShopId(iShop.getId());
		shop.setSection("区画" + i);
		shop.setFrontage("間口" + i);
		shop.setiSalesAgencyTargetId(iSalesAgencyTarget.getId());
		shop.setParticipatingStoreCorporationId(participatingStoreCorporation.getId());
		shop.setBuildingExpectedValue(partOfName);
		shop.setRemarks("メモ" + i);
		shop.setImagePath("img/apita.jpg?id=" + partOfName);
		shop.create();

		return shop;
	}

	public ShopImage createShopImage(int partOfName, Shop shop) throws IOException {
		Account acc = new AccountRepository().getHead().get();
		ShopImage shopImage = new ShopImage(createFile("apita.jpg"), shop, acc);
		shopImage.setComment("メモ" + partOfName);
		shopImage.setOutputFlag(String.valueOf(partOfName));
		shopImage.setOutputNumber(partOfName);
		shopImage.create();
		return shopImage;
	}

	public Account createAccount(int partOfName) {
		Account acc = new Account();
		acc.setFullName("Account" + partOfName);
		acc.setEmployeeCd("Emp" + partOfName);
		// acc.setPlaceOfLoanEmployeCode("EP" + partOfName);
		acc.setMailAddress("Email" + partOfName);
		// acc.setPassword("Pass" + partOfName);
		acc.create();
		return acc;
	}

	public Division createDivision(int partOfName) {
		Division division = new Division();
		division.setName("名前" + partOfName);
		division.create();
		return division;
	}

	public ISalesChannel createISalesChannel(int partOfName) {
		ISalesChannel iSalesChannel = new ISalesChannel();
		iSalesChannel.setSalesChannelId(new BigDecimal(partOfName));
		iSalesChannel.setSalesChannelCd(String.valueOf(partOfName));
		switch (partOfName % 4) {
		case 1:
			iSalesChannel.setSalesChannelName("SC");
			break;
		case 2:
			iSalesChannel.setSalesChannelName("NSC");
			break;
		case 3:
			iSalesChannel.setSalesChannelName("駅FB");
			break;
		default:
			iSalesChannel.setSalesChannelName("百貨店");
			break;
		}
		iSalesChannel.setVerificationSalesChannelCd(String.valueOf(partOfName));
		iSalesChannel.setVerificationSalesChannelName("検証販売チャネル名" + partOfName);
		iSalesChannel.setCoordinationCreatedDatetime(LocalDateTime.now());
		iSalesChannel.setCoordinationCreatedAccountCode("連携元登録者CD" + partOfName);
		iSalesChannel.setCoordinationUpdateDatetime(LocalDateTime.now());
		iSalesChannel.setCoordinationUpdateAccountCode("連携元登録者CD" + partOfName);
		iSalesChannel.setAction("0");
		iSalesChannel.setCreatedDatetime(LocalDateTime.now());
		iSalesChannel.setCreatedAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		iSalesChannel.setUpdateAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		iSalesChannel.create();
		return iSalesChannel;
	}

	public IBlock createIBlock(int partOfName) {
		IBlock iBlock = new IBlock();
		iBlock.setBlockId(new BigDecimal(partOfName));
		iBlock.setBlockCd(String.valueOf(partOfName));
		iBlock.setBlockName("ブロック名" + partOfName);
		iBlock.setCoordinationCreatedDatetime(LocalDateTime.now());
		iBlock.setCoordinationCreatedAccountCode("連携元登録者CD" + partOfName);
		iBlock.setCoordinationUpdateDatetime(LocalDateTime.now());
		iBlock.setCoordinationUpdateAccountCode("連携元登録者CD" + partOfName);
		iBlock.setAction("0");
		iBlock.setCreatedDatetime(LocalDateTime.now());
		iBlock.setCreatedAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		iBlock.setUpdateAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		iBlock.create();
		return iBlock;
	}

	public IArea createIArea(int partOfName) {
		IArea iArea = new IArea();
		iArea.setAreaId(new BigDecimal(partOfName));
		iArea.setAreaCd(String.valueOf(partOfName));
		iArea.setAreaName("エリア名" + partOfName);
		iArea.setWholesaleYn("0");
		iArea.setCoordinationCreatedDatetime(LocalDateTime.now());
		iArea.setCoordinationCreatedAccountCode("連携元登録者CD" + partOfName);
		iArea.setCoordinationUpdateDatetime(LocalDateTime.now());
		iArea.setCoordinationUpdateAccountCode("連携元登録者CD" + partOfName);
		iArea.setAction("0");
		iArea.setCreatedDatetime(LocalDateTime.now());
		iArea.setCreatedAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		iArea.setUpdateAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		iArea.create();
		return iArea;
	}

	public MCreditRank createMCreditRank(int partOfName) {
		MCreditRank mCreditRank = new MCreditRank();
		mCreditRank.setCorporationCd(String.valueOf(partOfName));
		mCreditRank.setCreditRank("与信ランク" + partOfName);
		mCreditRank.setCreatedDatetime(LocalDateTime.now());
		mCreditRank.setCreatedAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		mCreditRank.setUpdateAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		mCreditRank.create();
		return mCreditRank;
	}

	public ISalesAgencyTarget createISalesAgencyTarget(int partOfName) {
		ISalesAgencyTarget iSalesAgencyTarget = new ISalesAgencyTarget();
		iSalesAgencyTarget.setSalesAgencyTargetId(new BigDecimal(partOfName));
		iSalesAgencyTarget.setSalesAgencyTargetName("販売代行先名" + partOfName);
		iSalesAgencyTarget.setCoordinationCreatedDatetime(LocalDateTime.now());
		iSalesAgencyTarget.setCoordinationCreatedAccountCode("連携元登録者CD" + partOfName);
		iSalesAgencyTarget.setCoordinationUpdateDatetime(LocalDateTime.now());
		iSalesAgencyTarget.setCoordinationUpdateAccountCode("連携元登録者CD" + partOfName);
		iSalesAgencyTarget.setAction("0");
		iSalesAgencyTarget.setCreatedDatetime(LocalDateTime.now());
		iSalesAgencyTarget.setCreatedAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		iSalesAgencyTarget.setUpdateAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		iSalesAgencyTarget.create();
		return iSalesAgencyTarget;
	}

	public ParticipatingStoreCorporation createParticipatingStoreCorporation(int partOfName) {
		ParticipatingStoreCorporation participatingStoreCorporation = new ParticipatingStoreCorporation();
		participatingStoreCorporation.setCorporationDivision("法人区分" + partOfName);
		participatingStoreCorporation.setCorporationCd(String.valueOf(partOfName));
		participatingStoreCorporation.setCorporationName("法人名" + partOfName);
		participatingStoreCorporation.setCorporationNameKana("法人名カナ" + partOfName);
		participatingStoreCorporation.setPostalCode("郵便番号" + partOfName);
		participatingStoreCorporation.setAddress("住所" + partOfName);
		participatingStoreCorporation.setRepresentativePhoneNumber("代表電話番号" + partOfName);
		participatingStoreCorporation.setRepresentativeFaxNumber("代表FAX" + partOfName);
		participatingStoreCorporation.setCorporationUrl("企業URL" + partOfName);
		participatingStoreCorporation.setCreatedDatetime(LocalDateTime.now());
		participatingStoreCorporation.setCreatedAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		participatingStoreCorporation.setUpdateAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		participatingStoreCorporation.create();
		return participatingStoreCorporation;
	}

	public ICorporationGroup createICorporationGroup(int partOfName) {
		ICorporationGroup iCorporationGroup = new ICorporationGroup();
		iCorporationGroup.setCorporationGpId(new BigDecimal(partOfName));
		iCorporationGroup.setCorporationGpCd(String.valueOf(partOfName));
		iCorporationGroup.setCorporationGpName("企業GP名カナ" + partOfName);
		iCorporationGroup.setCorporationGpNameKana("企業GP名カナ" + partOfName);
		iCorporationGroup.setCreatedDatetime(LocalDateTime.now());
		iCorporationGroup.setCreatedAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		iCorporationGroup.setUpdateAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		iCorporationGroup.create();
		return iCorporationGroup;
	}

	public ICorporation createICorporation(int partOfName) {
		ICorporation iCorporation = new ICorporation("法人名" + partOfName).create();
		return iCorporation;
	}

	public IIncomeUnit createIIncomeUnit(int partOfName) {
		IIncomeUnit iIncomeUnit = new IIncomeUnit();
		iIncomeUnit.setIncomeUnitId(new BigDecimal(partOfName));
		iIncomeUnit.setIncomeUnitCd(String.valueOf(partOfName));
		iIncomeUnit.setIncomeUnitName("利益単位名" + partOfName);
		iIncomeUnit.setCompanyId(new BigDecimal(partOfName));
		// iIncomeUnit.setCoordinationCreationTime(LocalDateTime.now());
		// iIncomeUnit.setCoordinationCreatedAccountCode("連携元登録者CD" + partOfName);
		// iIncomeUnit.setCoordinationUpdateDatetime(LocalDateTime.now());
		// iIncomeUnit.setCoordinationUpdateAccountCode("連携元登録者CD" + partOfName);
		// iIncomeUnit.setAction("0");
		iIncomeUnit.setCreatedDatetime(LocalDateTime.now());
		iIncomeUnit.setCreatedAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		iIncomeUnit.setUpdateAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		iIncomeUnit.create();
		return iIncomeUnit;
	}

	public IPrefectures createIPrefectures(int partOfName) {
		IPrefectures iPrefectures = new IPrefectures();
		iPrefectures.setJisPrefecturesId(String.valueOf(partOfName));
		iPrefectures.setPrefecturesName(String.valueOf(partOfName));
		iPrefectures.setPrefecturesNameEllipsisName(String.valueOf(partOfName));
		iPrefectures.setCoordinationCreatedDatetime(LocalDateTime.now());
		iPrefectures.setCoordinationCreatedAccountCode("連携元登録者CD" + partOfName);
		iPrefectures.setCoordinationUpdateDatetime(LocalDateTime.now());
		iPrefectures.setCoordinationUpdateAccountCode("連携元登録者CD" + partOfName);
		iPrefectures.setAction("0");
		iPrefectures.setCreatedDatetime(LocalDateTime.now());
		iPrefectures.setCreatedAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		iPrefectures.setUpdateAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		iPrefectures.create();
		return iPrefectures;
	}

	public IRentContract createIRentContract(int partOfName, IShop iShop, IContractType iContractType) {
		IRentContract iRentContract = new IRentContract();
		iRentContract.setRentContractId(Long.valueOf(partOfName));
		iRentContract.setShopId(iShop.getShopId());
		iRentContract.setContractKindId(Long.valueOf(partOfName));
		iRentContract.setContractTypeId(iContractType.getContractTypeId());
		iRentContract.setPostCd(String.valueOf(partOfName));
		iRentContract.setPrefecturesName("0");
		iRentContract.setCityName("市区町村名" + partOfName);
		iRentContract.setTownNameAddress("町名・番地名" + partOfName);
		iRentContract.setBuildingName("ビル名" + partOfName);
		iRentContract.setFloorNum("フロア番号" + partOfName);
		iRentContract.setAddress1("住所1" + partOfName);
		iRentContract.setAddress2("住所2" + partOfName);
		iRentContract.setAddress("住所" + partOfName);
		iRentContract.setAddressOld("移行住所格納用" + partOfName);
		iRentContract.setStartDate(String.valueOf("2018010" + partOfName));
		iRentContract.setEndDate(String.valueOf("2018030" + partOfName));
		iRentContract.setCancelDate(String.valueOf("2018040" + partOfName));
		iRentContract.setHolderCompanyId(Long.valueOf(partOfName));
		iRentContract.setContractTargetName("契約先名" + partOfName);
		iRentContract.setPaymentMethodId(Long.valueOf(partOfName));
		iRentContract.setAutoUpdatingFlag("0");
		iRentContract.setNotificationDeadline(partOfName);
		iRentContract.setCancelPropriety("0");
		iRentContract.setCancelOfferDeadline(partOfName);
		iRentContract.setCancelWritingFlag("0");
		iRentContract.setCancelPenaltyFlag("0");
		iRentContract.setCancelCondition("中途解約違約金金額・条件など" + partOfName);
		iRentContract.setClosingDate1("01");
		iRentContract.setClosingDate2("02");
		iRentContract.setClosingDate3("03");
		iRentContract.setClosingDate4("04");
		iRentContract.setClosingDate5("05");
		iRentContract.setClosingDate6("06");
		iRentContract.setCollectionMonth1("01");
		iRentContract.setCollectionMonth2("02");
		iRentContract.setCollectionMonth3("03");
		iRentContract.setCollectionMonth4("04");
		iRentContract.setCollectionMonth5("05");
		iRentContract.setCollectionMonth6("06");
		iRentContract.setCollectionDay1("01");
		iRentContract.setCollectionDay2("02");
		iRentContract.setCollectionDay3("03");
		iRentContract.setCollectionDay4("04");
		iRentContract.setCollectionDay5("05");
		iRentContract.setCollectionDay6("06");
		iRentContract.setContractPeriod(partOfName);
		iRentContract.setCoordinationCreatedDatetime(LocalDateTime.now());
		iRentContract.setCoordinationCreatedAccountCode("連携元登録者CD" + partOfName);
		iRentContract.setCoordinationUpdateDatetime(LocalDateTime.now());
		iRentContract.setCoordinationUpdateAccountCode("連携元登録者CD" + partOfName);
		iRentContract.setAction("0");
		iRentContract.setCreatedDatetime(LocalDateTime.now());
		iRentContract.setCreatedAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		iRentContract.setUpdateAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		iRentContract.create();
		return iRentContract;
	}

	public IContractType createIContractType(int partOfName) {
		IContractType iContractType = new IContractType();
		iContractType.setContractTypeId(Long.valueOf(RandomTestData.getInteger(4)));
		iContractType.setContractTypeCd("00");
		iContractType.setContractTypeName("契約形態名" + partOfName);
		iContractType.setCoordinationCreatedDatetime(LocalDateTime.now());
		iContractType.setCoordinationCreatedAccountCode("連携元登録者CD" + partOfName);
		iContractType.setCoordinationUpdateDatetime(LocalDateTime.now());
		iContractType.setCoordinationUpdateAccountCode("連携元登録者CD" + partOfName);
		iContractType.setAction("0");
		iContractType.setCreatedDatetime(LocalDateTime.now());
		iContractType.setCreatedAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		iContractType.setUpdateAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		iContractType.create();
		return iContractType;
	}

	public Building mockUpBuildingData(int i) {
		Building building = createBuilding(i);

		createBuildingTradeArea(i, building);

		mockUpBuildingSalesData(GlobalVariable.buildingSalesDetailSize, building);

		mockUpBuildingKeymanData(GlobalVariable.buildingBuildingKeymanDetailSize, building);

		mockUpTenantData(GlobalVariable.buildingTenantDetailSize, building);

		mockUpBuildingPersonalDevelopData(GlobalVariable.buildingPersonalDevelopDetailSize, building);

		mockUpPmCorporationData(GlobalVariable.buildingPmCorporationSize, building);

		mockUpActivationData(GlobalVariable.buildingActivationDetailSize, building);

		mockUpImportantInformationBuildingData(GlobalVariable.buildingImportantInformationDetailSize, building);

		mockUpCompetitionData(GlobalVariable.buildingCompetitionDetailSize, building);

		mockUpBuildingFileData(GlobalVariable.buildingFileDetailSize, building);

		mockUpBuildingImageData(GlobalVariable.buildingImageDetailSize, building);

		mockUpBuildingMeetingHistoryData(GlobalVariable.buildingMeetingHistoryDetailSize, building);

		mockUpShopData(GlobalVariable.shopSize, building);

		return building;
	}

	public void mockUpBuildingSalesData(int size, Building building) {
		IntStream.range(0, size).forEach(index -> {
			createBuildingSales(index, building);
		});
	}

	public void mockUpBuildingKeymanData(int size, Building building) {
		IntStream.range(0, size).forEach(index -> {
			createBuildingKeyman(index, building, keyman);
		});
	}

	public void mockUpTenantData(int size, Building building) {
		IntStream.range(0, size).forEach(index -> {
			createBuildingTenant(index, building, tenant);
		});
	}

	public void mockUpBuildingPersonalDevelopData(int size, Building building) {
		IntStream.range(0, size).forEach(index -> {
			createBuildingPersonalDevelop(index, building, MAIN_ACCOUNT);
		});
	}

	public void mockUpPmCorporationData(int size, Building building) {
		IntStream.range(0, size).forEach(index -> {
			createPmCorporation(index, building, iCorporation);
		});
	}

	public void mockUpActivationData(int size, Building building) {
		IntStream.range(0, size).forEach(index -> {
			createActivation(index, building);
		});
	}

	public void mockUpImportantInformationBuildingData(int size, Building building) {
		IntStream.range(0, size).forEach(index -> {
			createImportantInformationBuilding(index, building, importantInformation);
		});
	}

	public void mockUpCompetitionData(int size, Building building) {
		IntStream.range(0, size).forEach(index -> {
			mockUpCompetitionSalesData(GlobalVariable.competitionSalesDetailSize, building, createCompetition(index));
		});
	}

	public void mockUpCompetitionSalesData(int size, Building building, Competition competition) {
		IntStream.range(0, size).forEach(index -> {
			createICompetitionSales(index, building, competition);
		});
	}

	public void mockUpBuildingFileData(int size, Building building) {
		IntStream.range(0, size).forEach(index -> {
			try {
				createBuildingFile(index, building, MAIN_ACCOUNT);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public void mockUpBuildingImageData(int size, Building building) {
		IntStream.range(0, size).forEach(index -> {
			try {
				createBuildingImage(index, building, MAIN_ACCOUNT);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	public void mockUpBuildingMeetingHistoryData(int size, Building building) {
		IntStream.range(0, size).forEach(index -> {
			createBuildingMeetingHistory(index, building, MAIN_ACCOUNT);
		});
	}

	public void mockUpShopData(int size, Building building) {
		IntStream.rangeClosed(1, size).forEach(index -> {
			IShop iShop = createIShop(index + (size*(building.getId().intValue()-1)));
			Shop shop = createShop(index, iShop);

			IContractType iContractType = createIContractType(index);
			createIRentContract(index, iShop, iContractType);
			createIShopSalesLatestOneYear(index, building, iShop, iBlock);

			mockUpShopImageData(GlobalVariable.shopImageDetailSize, shop);
		});
	}

	public void mockUpShopImageData(int size, Shop shop) {
		IntStream.range(0, size).forEach(index -> {
			try {
				createShopImage(index, shop);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	public MultipartFile createFile(String fileName) throws IOException {
		Path path = Paths.get("./_test", fileName);
		InputStream is = Files.newInputStream(path);
		return new MockMultipartFile(fileName, fileName, null, is);
	}
}
