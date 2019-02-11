package jp.co.world.storedevelopment.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.dev.GlobalVariable;
import jp.co.world.storedevelopment.dev.RandomTestData;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IAreaRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBlockRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesChannelRepository;
import jp.co.world.storedevelopment.model.service.BuildingService;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingDetailDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingFindFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingKeymanRelationBuildingUpdateDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingTenantRelationBuildingUpdateDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingUpdateDTO;

public class BuildingTest extends ModelTest {

	Account account;
	ICorporation iCorporation;
	ISalesChannel iSalesChannel;
	IBlock iBlock;
	IArea iArea;

	@Override
	@Before
	public void before() {
		super.before();
		account = new AccountRepository().getHead().get();
		iCorporation = new ICorporationRepository().getHead().get();
		iSalesChannel = new ISalesChannelRepository().getHead().get();
		iBlock = new IBlockRepository().getHead().get();
		iArea = new IAreaRepository().getHead().get();
	}

	public Building createBuilding() {
		Building building = new Building();
		building.setName("館名test");
		building.setBuildingCd("館IDtest");
		building.setOpenDate(GlobalVariable.localDate);
		building.setCloseDate(GlobalVariable.localDate);
		building.setiCorporationId(iCorporation.getId());
		building.setOwner("館長test");
		building.setiSalesChannelId(iSalesChannel.getId());
		building.setLocationFirst("立地①");
		building.setLocationSecond("立地②");
		building.setiBlockId(iBlock.getId());
		building.setiAreaId(iArea.getId());
		building.setJiscd("JISCD");
		building.create();
		return building;
	}

	@Test
	public void create() {
		Building building = createBuilding();

		assertNotEquals(Long.valueOf(0), building.getId());

		Optional<Building> buildingOption = new BuildingRepository().findById(building.getId());

		if (buildingOption.isPresent()) {
			Building createdBuilding = buildingOption.get();
			assertNotEquals(Long.valueOf(0), building.getId());
			assertEquals(createdBuilding.getId(), building.getId());
			assertEquals(createdBuilding.getName(), building.getName());
			assertEquals(createdBuilding.getBuildingCd(), building.getBuildingCd());
			assertEquals(createdBuilding.getOpenDate(), building.getOpenDate());
			assertEquals(createdBuilding.getCloseDate(), building.getCloseDate());
			assertEquals(createdBuilding.getiCorporationId(), building.getiCorporationId());
			assertEquals(createdBuilding.getOwner(), building.getOwner());
			assertEquals(createdBuilding.getiSalesChannelId(), building.getiSalesChannelId());
			assertEquals(createdBuilding.getLocationFirst(), building.getLocationFirst());
			assertEquals(createdBuilding.getLocationSecond(), building.getLocationSecond());
			assertEquals(createdBuilding.getiBlockId(), building.getiBlockId());
			assertEquals(createdBuilding.getiAreaId(), building.getiAreaId());
			assertEquals(createdBuilding.getJiscd(), building.getJiscd());
		} else {
			fail();
		}
	}

	@Test
	public void findById() {
		Building createdBuilding = createBuilding();

		assertNotEquals(Long.valueOf(0), createdBuilding.getId());

		Building findBuilding = new BuildingRepository().findBuildingById(createdBuilding.getId()).toModel();
		assertEquals(findBuilding.getId(), createdBuilding.getId());
	}

	@Test
	public void findAll() {
		BuildingFindFormDTO dto = new BuildingFindFormDTO();
		dto.setPagingSize(0);
		List<Building> findAllBuilding = new BuildingRepository().find(dto).stream().map(x -> x.toModel())
				.collect(Collectors.toList());
		assertEquals(findAllBuilding.size(), 10);
	}

	@Test
	public void findAdvanced() {
		Building building = new BuildingRepository().getHead().get();
		BuildingDetailDTO buildingDTO = new BuildingDetailDTO(building);

		BuildingFindFormDTO dto = new BuildingFindFormDTO();
		dto.setPagingSize(0);
		dto.setAccountId(account.getId());
		dto.setBuildingName(buildingDTO.getName());
		dto.setBuildingType(buildingDTO.getBuildingType());
		dto.setiCorporationIds(Arrays.asList(buildingDTO.getiCorporationId()));
		dto.setiAreaIds(Arrays.asList(buildingDTO.getiAreaId()));
		dto.setiBlockIds(Arrays.asList(buildingDTO.getiBlockId()));
		dto.setiSalesChannelIds(Arrays.asList(buildingDTO.getiSalesChannelId()));
		dto.setSalesChannelCd2s(Arrays.asList(buildingDTO.getSalesChannelCd2()));

		List<BuildingListDTO> findAdvancedBuilding = new BuildingRepository().find(dto);
		assertEquals(findAdvancedBuilding.size(), 1);
	}

	@Test
	public void updateData() throws IOException {
		String updateString = "test update";
		Building buildingUpd = new BuildingRepository().getHead().get();
		BuildingDetailDTO detailDTO = new BuildingDetailDTO(buildingUpd);
		String buildingCd = buildingUpd.getBuildingCd();

		// Create ICorporation update
		ICorporation iCorporationUpd = new ICorporation("法人名");
		iCorporationUpd.create();

		// Create PmCorporation update
		List<PmCorporation> setListPmCorporationUpd = new ArrayList<PmCorporation>();
		ICorporation corp;
		PmCorporation pmCorp;

		for (int i = 0; i < GlobalVariable.buildingPmCorporationSize; i++) {
			corp = new ICorporation("法人名" + i);
			corp.create();
			pmCorp = new PmCorporation();
			pmCorp.setBuildingCd(buildingCd);
			pmCorp.setCorporationId(corp.getId());
			setListPmCorporationUpd.add(pmCorp);
		}

		// Create BuildingTenant update
		List<BuildingTenantRelationBuildingUpdateDTO> setListbuildingTenantUpd = new ArrayList<BuildingTenantRelationBuildingUpdateDTO>();
		Tenant tenant;
		BuildingTenantRelationBuildingUpdateDTO buildingTenant;

		for (int i = 0; i < detailDTO.getBuildingTenants().size(); i++) {
			if (i % 2 == 0) {
				tenant = new Tenant();
				tenant.setId(Long.valueOf(0));
				tenant.setName("テナント名" + i);
			} else {
				tenant = detailDTO.getBuildingTenants().get(i).getTenant();
			}
			buildingTenant = new BuildingTenantRelationBuildingUpdateDTO();
			buildingTenant.setBuildingCd(buildingCd);
			buildingTenant.setTenantId(tenant.getId());
			buildingTenant.setTenant(tenant);
			setListbuildingTenantUpd.add(buildingTenant);
		}

		// Create BuildingPersonalDevelop update
		String[] categoryBuildingPersonalDevelop = new String[] { "storeDeveloper", "branchsSales",
				"humanResourceLeader" };
		List<BuildingPersonalDevelop> setListBuildingPersonalDevelopUpd = new ArrayList<BuildingPersonalDevelop>();
		Account acc;
		BuildingPersonalDevelop buildingPersonalDevelop;

		for (int i = 0; i < detailDTO.getBuildingPersonalDevelops().size(); i++) {
			acc = new Account(0, "person" + i);
			acc.setMailAddress("Email" + i);
			acc.create();
			buildingPersonalDevelop = new BuildingPersonalDevelop();
			buildingPersonalDevelop.setAccountId(acc.getId());
			buildingPersonalDevelop.setCategory(categoryBuildingPersonalDevelop[i % 3]);
			setListBuildingPersonalDevelopUpd.add(buildingPersonalDevelop);
		}

		// Create BuildingKeyman update
		String[] buildingKeymanCategory = new String[] { "HQ leasing", "Building leasing", "Inside control" };
		String[] star = new String[] { "0", "1", "2", "3" };
		List<BuildingKeymanRelationBuildingUpdateDTO> setListBuildingKeymanUpd = new ArrayList<BuildingKeymanRelationBuildingUpdateDTO>();
		Keyman keyman;
		BuildingKeymanRelationBuildingUpdateDTO buildingKeyman;

		for (int i = 0; i < detailDTO.getBuildingKeymans().size(); i++) {
			keyman = detailDTO.getBuildingKeymans().get(i).getKeyman().toModel();
			corp = new ICorporation("法人名Keyman" + i);
			corp.create();
			keyman.setCorporationId(corp.getId());
			buildingKeyman = new BuildingKeymanRelationBuildingUpdateDTO();
			buildingKeyman.setCategory(buildingKeymanCategory[i % 3]);
			buildingKeyman.setPriority(star[i % 4]);
			buildingKeyman.setRole("役割" + i);
			buildingKeyman.setNote("メモ" + i);
			buildingKeyman.setKeyman(keyman);
			setListBuildingKeymanUpd.add(buildingKeyman);
		}

		// Create Activation update
		List<Activation> setListActivationUpd = new ArrayList<Activation>();
		Activation act;

		for (int i = 0; i < detailDTO.getActivations().size(); i++) {
			act = detailDTO.getActivations().get(i).toModel();
			act.setExpectedDay(GlobalVariable.localDate);
			act.setContent(act.getContent() + updateString);
			act.setFloor(act.getFloor() + updateString);
			setListActivationUpd.add(act);
		}

		// Create BuildingFile update
		List<BuildingFile> setListBuildingFile = detailDTO.getBuildingFiles().stream().map(f -> f.toModel())
				.collect(Collectors.toList());
		List<BuildingFile> setListBuildingFileUpd = new ArrayList<BuildingFile>();

		// update BuildingFile
		for (int i = 0; i < detailDTO.getBuildingFiles().size(); i++) {
			if (i % 2 == 0) {
				setListBuildingFile.get(i).setIsDeleted(true);
			}
			setListBuildingFileUpd.add(setListBuildingFile.get(i));
		}

		// insert new BuildingFile
		int buildingFileAddSize = 2;
		for (int i = 0; i < buildingFileAddSize; i++) {
			Path path = Paths.get("./_test", "sample.pdf");
			InputStream is = Files.newInputStream(path);
			MultipartFile multiPartFile = new MockMultipartFile("sample.pdf", "sample.pdf", null, is);
			BuildingFile ibf = new BuildingFile(multiPartFile, detailDTO.toModel(), account);
			ibf.setId(Long.valueOf(0));
			setListBuildingFileUpd.add(ibf);
		}

		// Create BuildingImage update
		List<BuildingImage> setListBuildingImage = detailDTO.getBuildingImages().stream().map(f -> f.toModel())
				.collect(Collectors.toList());
		List<BuildingImage> setListBuildingImageUpd = new ArrayList<BuildingImage>();

		// update BuildingFile
		for (int i = 0; i < detailDTO.getBuildingImages().size(); i++) {
			if (i % 2 == 0) {
				setListBuildingImage.get(i).setIsDeleted(true);
			}
			setListBuildingImageUpd.add(setListBuildingImage.get(i));
		}

		// insert new BuildingImage
		int buildingImageAddSize = 3;
		for (int i = 0; i < buildingImageAddSize; i++) {
			Path path = Paths.get("./_test", "apita.jpg");
			InputStream is = Files.newInputStream(path);
			MultipartFile multiPartFile = new MockMultipartFile("apita.jpg", "apita.jpg", null, is);
			BuildingImage ibm = new BuildingImage(multiPartFile, detailDTO.toModel(), account);
			ibm.setId(Long.valueOf(0));
			setListBuildingImageUpd.add(ibm);
		}

		// Create BuildingMeetingHistory update
		List<BuildingMeetingHistory> setListBuildingMeetingHistoryUpd = new ArrayList<BuildingMeetingHistory>();
		BuildingMeetingHistory buildingMeetingHistory;

		// update BuildingMeetingHistory
		for (int i = 0; i < detailDTO.getBuildingMeetingHistorys().size(); i++) {
			buildingMeetingHistory = detailDTO.getBuildingMeetingHistorys().get(i).toModel();
			if (i % 3 == 0) {
				buildingMeetingHistory.setIsDeleted(true);
			} else {
				buildingMeetingHistory.setName(buildingMeetingHistory.getName() + updateString);
				buildingMeetingHistory.setDate(GlobalVariable.localDateTime);
				buildingMeetingHistory.setAttendee(buildingMeetingHistory.getAttendee() + updateString);
			}
			setListBuildingMeetingHistoryUpd.add(buildingMeetingHistory);
		}

		// add BuildingMeetingHistory
		for (int i = 0; i < GlobalVariable.buildingMeetingHistoryDetailSize; i++) {
			buildingMeetingHistory = new BuildingMeetingHistory();
			buildingMeetingHistory.setId(Long.valueOf(0));
			buildingMeetingHistory.setBuildingCd(buildingCd);
			buildingMeetingHistory.setOverview("総会概要" + i);
			buildingMeetingHistory.setName("ファイル名" + i);
			buildingMeetingHistory.setDate(GlobalVariable.localDateTime);
			buildingMeetingHistory.setAttendee(account.getFullName());
			setListBuildingMeetingHistoryUpd.add(buildingMeetingHistory);
		}

		// Create BuildingSales update
		List<BuildingSales> lstBuildingSalesUpd = new ArrayList<BuildingSales>();
		BuildingSales buildingSales;

		// update BuildingMeetingHistory
		for (int i = 0; i < detailDTO.getBuildingSaless().size(); i++) {
			buildingSales = detailDTO.getBuildingSaless().get(i).toModel();
			if (i % 3 == 0) {
				buildingSales.setIsDeleted(true);
			} else {
				buildingSales.setDivision(buildingSales.getDivision() + 1);
				buildingSales.setSales(buildingSales.getSales().add(BigDecimal.valueOf(1000)));
				buildingSales.setArea(BigDecimal.valueOf(999));
				buildingSales.setMonthBasis(BigDecimal.valueOf(999999));
				buildingSales.setFinancialMonth(GlobalVariable.localDate);
				buildingSales.setType(buildingSales.getType() + 1);
			}
			lstBuildingSalesUpd.add(buildingSales);
		}

		// add BuildingMeetingHistory
		for (int i = 0; i < GlobalVariable.buildingSalesDetailSize; i++) {
			buildingSales = new BuildingSales();
			buildingSales.setId(Long.valueOf(0));
			buildingSales.setDivision(Long.valueOf(RandomTestData.getInteger(1, 3)));
			buildingSales.setSales(BigDecimal.valueOf(i * 10));
			buildingSales.setArea(BigDecimal.valueOf(i * 15));
			buildingSales.setMonthBasis(new BigDecimal(i));
			buildingSales.setFinancialMonth(GlobalVariable.localDate);
			buildingSales.setType(Long.valueOf(RandomTestData.getInteger(1, 3)));
			lstBuildingSalesUpd.add(buildingSales);
		}

		// Create MCreditRank update
		MCreditRank mCreditRankUpd = new MCreditRank();
		mCreditRankUpd.setCorporationCd("0");
		mCreditRankUpd.setCreditRank("与信ランク");
		mCreditRankUpd.create();

		// Create ISalesChannel update
		ISalesChannel iSalesChannelUpd = new ISalesChannel();
		iSalesChannelUpd.setSalesChannelId(new BigDecimal(1));
		iSalesChannelUpd.setSalesChannelCd("CD1");
		iSalesChannelUpd.create();

		// Create ISalesChannel 2 update
		ISalesChannel iSalesChannelUpd2 = new ISalesChannel();
		iSalesChannelUpd2.setSalesChannelId(new BigDecimal(2));
		iSalesChannelUpd2.setSalesChannelCd("CD2");
		iSalesChannelUpd2.create();

		// Create IBlock update
		IBlock iBlockUpd = new IBlock();
		iBlockUpd.setBlockId(new BigDecimal(1));
		iBlockUpd.setBlockCd("CD1");
		iBlockUpd.setBlockName("ブロック名");
		iBlockUpd.create();

		// Create IArea update
		IArea iAreaUpd = new IArea();
		iAreaUpd.setAreaId(new BigDecimal(1));
		iAreaUpd.setAreaCd("CD1");
		iAreaUpd.setAreaName("エリア名");
		iAreaUpd.create();

		// Create IArea update
		IPrefectures iPrefecturesUpd = new IPrefectures();
		iPrefecturesUpd.setJisPrefecturesId("CD");
		iPrefecturesUpd.setPrefecturesName("都道府県名");
		iPrefecturesUpd.create();

		// Update shop
		BuildingUpdateDTO updateDTO = new BuildingUpdateDTO();
		updateDTO.setId(buildingUpd.getId());
		updateDTO.setiCorporationId(iCorporationUpd.getId());
		updateDTO.setOwner(buildingUpd.getOwner() + updateString);
		updateDTO.setBuildingType(buildingUpd.getBuildingType() + 1);
		updateDTO.setOpenDate(GlobalVariable.localDate);
		updateDTO.setCloseDate(GlobalVariable.localDate);
		updateDTO.setAddress(buildingUpd.getAddress() + updateString);
		updateDTO.setiPrefecturesId(iPrefecturesUpd.getId());
		updateDTO.setHomepageUrl(buildingUpd.getHomepageUrl() + updateString);
		updateDTO.setJiscd(buildingUpd.getJiscd() + updateString);
		updateDTO.setGroundFloor(buildingUpd.getGroundFloor() + updateString);
		updateDTO.setStructure(buildingUpd.getStructure() + updateString);
		updateDTO.setCoreTenant(buildingUpd.getCoreTenant() + updateString);
		updateDTO.setSubCoreTenant(buildingUpd.getSubCoreTenant() + updateString);
		updateDTO.setTenantNumber(buildingUpd.getTenantNumber() + 1);
		updateDTO.setAttractingContent(buildingUpd.getAttractingContent() + updateString);
		updateDTO.setParkingNumber(buildingUpd.getParkingNumber() + updateString);
		updateDTO.setBusinessHours(buildingUpd.getBusinessHours() + updateString);
		updateDTO.setNearestStation(buildingUpd.getNearestStation() + updateString);
		updateDTO.setLatitude(new BigDecimal(1.000000));
		updateDTO.setLongitude(new BigDecimal(1.000000));
		updateDTO.setLocationFirst(buildingUpd.getLocationFirst() + updateString);
		updateDTO.setLocationSecond(buildingUpd.getLocationSecond() + updateString);
		updateDTO.setSmallMarketArea(buildingUpd.getSmallMarketArea() + updateString);
		updateDTO.setAdoptDifficulty(buildingUpd.getAdoptDifficulty() + updateString);
		updateDTO.setNote(buildingUpd.getNote() + updateString);
		updateDTO.setiSalesChannelId(iSalesChannelUpd.getId());
		updateDTO.setSalesChannelCd2(iSalesChannelUpd2.getId());
		updateDTO.setiBlockId(iBlockUpd.getId());
		updateDTO.setiAreaId(iAreaUpd.getId());
		updateDTO.setBuildingKeymanDto(setListBuildingKeymanUpd);
		updateDTO.setBuildingPersonalDevelopDto(setListBuildingPersonalDevelopUpd);
		updateDTO.setActivationDto(setListActivationUpd);
		updateDTO.setPmCorporationDto(setListPmCorporationUpd);
		updateDTO.setBuildingTenantDto(setListbuildingTenantUpd);
		updateDTO.setBuildingFileDto(setListBuildingFileUpd);
		updateDTO.setBuildingImageDto(setListBuildingImageUpd);
		updateDTO.setBuildingMeetingDto(setListBuildingMeetingHistoryUpd);
		updateDTO.setBuildingSalesDto(lstBuildingSalesUpd);

		new BuildingService().updateAll(updateDTO, account);

		// Test
		BuildingDetailDTO findDTO = new BuildingRepository().findBuildingById(buildingUpd.getId());

		assertEquals(updateDTO.getId(), findDTO.getId());
		assertEquals(updateDTO.getiCorporationId(), findDTO.getiCorporationId());
		assertEquals(updateDTO.getOwner(), findDTO.getOwner());
		assertEquals(updateDTO.getBuildingType(), findDTO.getBuildingType());
		assertEquals(updateDTO.getOpenDate(), findDTO.getOpenDate());
		assertEquals(updateDTO.getCloseDate(), findDTO.getCloseDate());
		assertEquals(updateDTO.getAddress(), findDTO.getAddress());
		assertEquals(updateDTO.getiPrefecturesId(), findDTO.getiPrefecturesId());
		assertEquals(updateDTO.getHomepageUrl(), findDTO.getHomepageUrl());
		assertEquals(updateDTO.getJiscd(), findDTO.getJiscd());
		assertEquals(updateDTO.getGroundFloor(), findDTO.getGroundFloor());
		assertEquals(updateDTO.getStructure(), findDTO.getStructure());
		assertEquals(updateDTO.getCoreTenant(), findDTO.getCoreTenant());
		assertEquals(updateDTO.getSubCoreTenant(), findDTO.getSubCoreTenant());
		assertEquals(updateDTO.getTenantNumber(), findDTO.getTenantNumber());
		assertEquals(updateDTO.getAttractingContent(), findDTO.getAttractingContent());
		assertEquals(updateDTO.getParkingNumber(), findDTO.getParkingNumber());
		assertEquals(updateDTO.getBusinessHours(), findDTO.getBusinessHours());
		assertEquals(updateDTO.getNearestStation(), findDTO.getNearestStation());
		assertEquals(updateDTO.getLatitude().setScale(6, BigDecimal.ROUND_HALF_UP),
				findDTO.getLatitude().setScale(6, BigDecimal.ROUND_HALF_UP));
		assertEquals(updateDTO.getLongitude().setScale(6, BigDecimal.ROUND_HALF_UP),
				findDTO.getLongitude().setScale(6, BigDecimal.ROUND_HALF_UP));
		assertEquals(updateDTO.getiSalesChannelId(), findDTO.getiSalesChannelId());
		assertEquals(updateDTO.getSalesChannelCd2(), findDTO.getSalesChannelCd2());
		assertEquals(updateDTO.getiBlockId(), findDTO.getiBlockId());
		assertEquals(updateDTO.getiAreaId(), findDTO.getiAreaId());
		assertEquals(updateDTO.getLocationFirst(), findDTO.getLocationFirst());
		assertEquals(updateDTO.getLocationSecond(), findDTO.getLocationSecond());
		assertEquals(updateDTO.getSmallMarketArea(), findDTO.getSmallMarketArea());
		assertEquals(updateDTO.getAdoptDifficulty(), findDTO.getAdoptDifficulty());
		assertEquals(updateDTO.getNote(), findDTO.getNote());
		assertEquals(updateDTO.getPmCorporationDto().stream().filter(x -> !x.getIsDeleted())
				.collect(Collectors.toList()).size(), findDTO.getPmCorporations().size());
		assertEquals(updateDTO.getBuildingTenantDto().stream().filter(x -> !x.getIsDeleted())
				.collect(Collectors.toList()).size(), findDTO.getBuildingTenants().size());
		assertEquals(updateDTO.getBuildingPersonalDevelopDto().stream().filter(x -> !x.getIsDeleted())
				.collect(Collectors.toList()).size(), findDTO.getBuildingPersonalDevelops().size());
		assertEquals(updateDTO.getBuildingKeymanDto().stream().filter(x -> !x.getIsDeleted())
				.collect(Collectors.toList()).size(), findDTO.getBuildingKeymans().size());
		assertEquals(updateDTO.getActivationDto().stream().filter(x -> !x.getIsDeleted()).collect(Collectors.toList())
				.size(), findDTO.getActivations().size());
		assertEquals(updateDTO.getBuildingFileDto().stream().filter(x -> !x.getIsDeleted()).collect(Collectors.toList())
				.size(), findDTO.getBuildingFiles().size());
		assertEquals(updateDTO.getBuildingImageDto().stream().filter(x -> !x.getIsDeleted())
				.collect(Collectors.toList()).size(), findDTO.getBuildingImages().size());
		assertEquals(updateDTO.getBuildingMeetingDto().stream().filter(x -> !x.getIsDeleted())
				.collect(Collectors.toList()).size(), findDTO.getBuildingMeetingHistorys().size());
		assertEquals(updateDTO.getBuildingSalesDto().stream().filter(x -> !x.getIsDeleted())
				.collect(Collectors.toList()).size(), findDTO.getBuildingSaless().size());
	}
}
