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

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.dev.GlobalVariable;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IAreaRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IIncomeUnitRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesAgencyTargetRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IShopCompanyRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IShopRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ParticipatingStoreCorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ShopRepository;
import jp.co.world.storedevelopment.model.service.ShopService;
import jp.co.world.storedevelopment.sp.controller.dto.ShopDetailDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ShopFindFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ShopUpdateDTO;

/**
 * @author TaiNM
 *
 */
public class ShopTest extends ModelTest {
	Account account;
	IShopCompany iShopCompany;
	Building building;
	IArea iArea;
	ISalesAgencyTarget iSalesAgencyTarget;
	ParticipatingStoreCorporation participatingStoreCorporation;
	IIncomeUnit iIncomeUnit;

	@Override
	@Before
	public void before() {
		super.before();
		account = new AccountRepository().getHead().get();
		iShopCompany = new IShopCompanyRepository().getHead().get();
		building = new BuildingRepository().getHead().get();
		iArea = new IAreaRepository().getHead().get();
		iSalesAgencyTarget = new ISalesAgencyTargetRepository().getHead().get();
		participatingStoreCorporation = new ParticipatingStoreCorporationRepository().getHead().get();
		iIncomeUnit = new IIncomeUnitRepository().getHead().get();
	}

	public Shop createShop(Building building) {
		Shop shop = new Shop();
		shop.setSection("区画Test");
		shop.setFrontage("間口Test");
		shop.setiSalesAgencyTargetId(iSalesAgencyTarget.getId());
		shop.setParticipatingStoreCorporationId(participatingStoreCorporation.getId());
		shop.setBuildingExpectedValue(999);
		shop.setRemarks("メモtest");
		shop.setImagePath("img/apita.jpg?id=999");
		shop.create();
		return shop;
	}

	public IShop createIShop(Shop shop) {
		IShop iShop = new IShop();

		iShop.setShopId(shop.getId());
		iShop.setShopCd("店舗CDTest");
		iShop.setShopNameZenkaku("店舗名（全角）Test");
		iShop.setiShopCompanyId(iShopCompany.getId());
		iShop.setWholesaleAreaId(iArea.getId());
		iShop.setiIncomeUnitId(Long.valueOf(1));
		iShop.setContractTsubo(BigDecimal.valueOf(999));
		iShop.setOpenDate(GlobalVariable.localDate);
		iShop.setIsWsdCoordinated(false);
		iShop.create();

		return iShop;
	}

	@Test
	public void create() {
		Shop shop = createShop(building);
		IShop iShop = createIShop(shop);

		assertNotEquals(Long.valueOf(0), shop.getId());
		assertNotEquals(Long.valueOf(0), iShop.getId());

		Optional<Shop> shopOption = new ShopRepository().findById(shop.getId());
		Optional<IShop> IShopOption = new IShopRepository().findById(iShop.getId());

		if (shopOption.isPresent()) {
			Shop createdShop = shopOption.get();
			assertNotEquals(Long.valueOf(0), shop.getId());
			assertEquals(createdShop.getId(), shop.getId());
			assertEquals(createdShop.getSection(), shop.getSection());
			assertEquals(createdShop.getFrontage(), shop.getFrontage());
			assertEquals(createdShop.getiSalesAgencyTargetId(), shop.getiSalesAgencyTargetId());
			assertEquals(createdShop.getParticipatingStoreCorporationId(), shop.getParticipatingStoreCorporationId());
			assertEquals(createdShop.getBuildingExpectedValue(), shop.getBuildingExpectedValue());
			assertEquals(createdShop.getRemarks(), shop.getRemarks());
			assertEquals(createdShop.getImagePath(), shop.getImagePath());
		} else {
			fail();
		}

		if (IShopOption.isPresent()) {
			IShop createdIShop = IShopOption.get();
			assertNotEquals(Long.valueOf(0), iShop.getId());
			assertEquals(createdIShop.getId(), iShop.getId());
			assertEquals(createdIShop.getShopId(), iShop.getShopId());
			assertEquals(createdIShop.getShopCd(), iShop.getShopCd());
			assertEquals(createdIShop.getShopNameZenkaku(), iShop.getShopNameZenkaku());
			assertEquals(createdIShop.getWholesaleAreaId(), iShop.getWholesaleAreaId());
			assertEquals(createdIShop.getiIncomeUnitId(), iShop.getiIncomeUnitId());
			assertEquals(createdIShop.getContractTsubo().setScale(8, BigDecimal.ROUND_HALF_UP),
					iShop.getContractTsubo().setScale(8, BigDecimal.ROUND_HALF_UP));
			assertEquals(createdIShop.getOpenDate(), iShop.getOpenDate());
		} else {
			fail();
		}
	}

	@Test
	public void findById() {
		Shop createdShop = createShop(building);

		assertNotEquals(Long.valueOf(0), createdShop.getId());

		// Shop findShop = new
		// ShopRepository().findShopById(createdShop.getId()).toModel();
		// assertEquals(findShop.getId(), createdShop.getId());
	}

	@Test
	public void findAll() {
		ShopFindFormDTO dto = new ShopFindFormDTO();
		dto.setPagingSize(0);
		// List<Shop> findAllShop = new ShopRepository().findShop(dto).stream().map(x ->
		// x.toModel())
		// .collect(Collectors.toList());
		// assertEquals(findAllShop.size(), 30);
	}

	@Test
	public void findAdvanced() {
		Shop shop = new ShopRepository().getHead().get();
		ShopDetailDTO shopDTO = new ShopDetailDTO(shop);

		ShopFindFormDTO dto = new ShopFindFormDTO();
		dto.setPagingSize(0);
		dto.setAccountId(account.getId());
		// dto.setiAreaIds(Arrays.asList(shopDTO.getiShop().getWholesaleAreaId()));
		// dto.setiIncomeUnitIds(Arrays.asList(shopDTO.getiShop().getiIncomeUnitId()));
		dto.setShopIds(Arrays.asList(shopDTO.getId()));
		// dto.setiCorporationIds(Arrays.asList(shopDTO.getiShop().getiCorporationId()));

		// List<ShopListDTO> findAdvancedShop = new ShopRepository().findShop(dto);
		// assertEquals(findAdvancedShop.size(), 1);
	}

	@Test
	public void updateData() throws IOException {
		String updateString = "test update";
		Shop shopUpd = new ShopRepository().getHead().get();

		// Create ISalesAgencyTarget update
		ISalesAgencyTarget iSalesAgencyTargetUpd = new ISalesAgencyTarget();
		iSalesAgencyTargetUpd.setSalesAgencyTargetId(new BigDecimal(1));
		iSalesAgencyTargetUpd.setAction("1");
		iSalesAgencyTargetUpd.create();

		// Create ParticipatingStoreCorporation update
		ParticipatingStoreCorporation participatingStoreCorporationUpd = new ParticipatingStoreCorporation();
		participatingStoreCorporationUpd.setCorporationDivision("法人区分");
		participatingStoreCorporationUpd.setCorporationCd("法人CD");
		participatingStoreCorporationUpd.setCorporationName("法人名");
		participatingStoreCorporationUpd.create();

		// Create ShopImage update
		List<ShopImage> shopImageUpds = new ArrayList<ShopImage>();
		// List<ShopImage> shopImagePresents = new
		// ShopDetailDTO(shopUpd).getShopImages().stream().map(x -> x.toModel())
		// .collect(Collectors.toList());
		ShopImage shopImage;
		// for (int i = 0; i < shopImagePresents.size(); i++) {
		// shopImage = shopImagePresents.get(i);
		// if (i % 2 == 0) {
		// shopImage.setIsDeleted(true);
		// }
		// shopImageUpds.add(shopImage);
		// }

		for (int i = 0; i < 2; i++) {
			// insert new
			Path path = Paths.get("./_test", "apita.jpg");
			InputStream is = Files.newInputStream(path);
			MultipartFile multiPartFile = new MockMultipartFile("apita.jpg", "apita.jpg", null, is);
			shopImage = new ShopImage(multiPartFile, shopUpd, account);
			shopImage.setId(Long.valueOf(0));
			shopImageUpds.add(shopImage);
		}

		// Update shop
		ShopUpdateDTO updateDTO = new ShopUpdateDTO();
		updateDTO.setId(shopUpd.getId());
		updateDTO.setSection(shopUpd.getSection() + updateString);
		updateDTO.setFrontage(shopUpd.getFrontage() + updateString);
		updateDTO.setBuildingExpectedValue(shopUpd.getBuildingExpectedValue() + 1);
		updateDTO.setRemarks(shopUpd.getRemarks() + updateString);
		updateDTO.setiSalesAgencyTargetId(iSalesAgencyTargetUpd.getId());
		updateDTO.setParticipatingStoreCorporationId(participatingStoreCorporationUpd.getId());
		updateDTO.setShopImageDto(shopImageUpds);
		new ShopService().updateAll(updateDTO, account);

		// Test
		// ShopDetailDTO findDTO = new ShopRepository().findShopById(shopUpd.getId());

		// assertEquals(updateDTO.getId(), findDTO.getId());
		// assertEquals(updateDTO.getSection(), findDTO.getSection());
		// assertEquals(updateDTO.getFrontage(), findDTO.getFrontage());
		// assertEquals(updateDTO.getBuildingExpectedValue(),
		// findDTO.getBuildingExpectedValue());
		// assertEquals(updateDTO.getRemarks(), findDTO.getRemarks());
		// assertEquals(
		// updateDTO.getShopImageDto().stream().filter(x ->
		// !x.getIsDeleted()).collect(Collectors.toList()).size(),
		// findDTO.getShopImages().size());
	}
}
