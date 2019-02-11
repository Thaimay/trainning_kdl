package jp.co.world.storedevelopment.dev;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import jp.co.world.storedevelopment.CRUDTest;
import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Brand;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.IBusinessCard;
import jp.co.world.storedevelopment.model.ICorporation;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.NegotiationComment;
import jp.co.world.storedevelopment.model.NegotiationImage;
import jp.co.world.storedevelopment.model.mapper.repository.BrandRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBusinessCardRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;
import jp.co.world.storedevelopment.model.service.NegotiationService;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationCreateFormDTO;

public class InitStagingData extends CRUDTest {
	public Account MAIN_ACCOUNT;
	public Account LAST_ACCOUNT;

	public static List<String> accountNames = Arrays.asList("山大 営業太郎", "山田 営業次郎", "鈴木 営業次郎", "田中 営業三郎");

	@Override
	@BeforeAll
	public void beforeAll() {
		super.beforeAll();
	}

	public void initForTest() {
		beforeInit();
		init();
	}

	@Before
	public void beforeInit() {
		super.before();

	}

	@Test
	public void init() {
		// InitTestData init = new InitTestData();
		// Arrays.asList("イオンリーテル(株)", "株式会社ウエノ", "三菱商事UBSリアルティ（株）").forEach(s ->
		// createCorporation(s));
		// Arrays.asList("タケオキクチ", "THE SHOP TK").forEach(s -> createBrand(s));
		// Arrays.asList("山田 太郎", "鈴木 太郎", "山本 次郎", "山田 次郎", "松本 裕次").forEach(s ->
		// createBusinessCard(s));
		//
		// init.initMainData();
		// MAIN_ACCOUNT = init.MAIN_ACCOUNT;
		//
		// accountNames.forEach(n -> {
		// Account a = new Account(n);
		// a.setEmployeCode(n.toString());
		// a.create();
		// });
		//
		// List<Account> list = new AccountRepository().findAll();
		// LAST_ACCOUNT = list.get(list.size() - 1);
		//
		//
		// Building b1 = init.mockUpBuildingData(1);
		// b1.setName("仙台パークタピオ");
		// b1.update();
		//
		// Building b2 = init.mockUpBuildingData(1);
		// b2.setName("和田山イオンエスタ交渉");
		// b2.update();
		//
		// Building b3 = init.mockUpBuildingData(1);
		// b3.setName("恵比寿アトレ");
		// b3.update();
		//
		// Arrays.asList("仙台パークタピオ交渉", "恵比寿アトレ交渉", "株式会社ウエノ交渉").forEach(s -> {
		// try {
		// createNegotiationSchedule(s, b1);
		// } catch (IOException e1) {
		// e1.printStackTrace();
		// }
		// });
		// Arrays.asList("コルテラル後交渉", "和田山イオンエスタ交渉").forEach(s -> {
		// try {
		// createNegotiationImplemenation(s, b2);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// });
	}

	public void createNegotiationSchedule(String name, Building building) throws IOException {
		ICorporation c = new ICorporationRepository().getHead().get();
		IBusinessCard bc = new IBusinessCardRepository().getHead().get();

		Negotiation n = new Negotiation(name);
		n.setScheduleStartDatetime(LocalDateTime.now());
		n.setScheduleEndDatetime(LocalDateTime.now());
		n.setImplementationStartDatetime(null);
		n.setImplementationEndDatetime(null);
		n.setCreatedAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		n.setUpdateAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		n.setContent("【予定商談】両店舗ともに高収入店舗であり、60期案\n" + "新潟西店で減坪の以降に伝えられています。\n" + "具体的には48.2坪を減坪の上、48.2坪で\n"
				+ "グリーンパークスを考えているとのことです。\n" + "こちらは温度感としては確率は高いです。\n" + "また長津田店でも同じく館活性化を検討しており、\n"
				+ "賃料効率改善のためシューラルーが減坪の可能性を\n" + "孕んでいるとのことです。\n" + "長津田店はまだ活性化の案も固まっていないため、\n" + "現場所での再契約と\n"
				+ "当初計画通り 食品側の壁面を\n" + "撤去する同場所改装にて進めるつもりです");
		n.setPlace("株式会社ユニー様 4F 事務所");
		n.setDivision("NEGOTIATION");
		n.setPriority(3);

		NegotiationCreateFormDTO dto = new NegotiationCreateFormDTO();
		List<Long> accountIds = new ArrayList<>();
		accountIds.add(MAIN_ACCOUNT.getId());
		dto.setAccountIds(accountIds);
		dto.setNoticeIds(accountIds);

		List<Long> brandsIds = new ArrayList<>();
		Brand b = new BrandRepository().getHead().get();
		brandsIds.add(b.getId());
		dto.setBrandIds(accountIds);
		dto.setNoticeIds(accountIds);

		new NegotiationService().createAll(n, dto, MAIN_ACCOUNT);
		n.addInterviewBuilding(building);
		n.addInterviewCorporation(c);
		n.addInterviewBusinessCard(bc);

		List<String> comments = Arrays.asList("各事業部長と話してください。\nよろしくお願いします。", "山口さんに連絡お願いします。\n (^^)/",
				"メールの転送お願いします。\n部長への報告もお願いします。");
		comments.forEach(com -> new NegotiationComment(n, MAIN_ACCOUNT, com).create());

		Path path = Paths.get("./_test", "apita.jpg");
		InputStream is = Files.newInputStream(path);
		MultipartFile multiPartFile = new MockMultipartFile("apita.jpg", "apita.jpg", null, is);

		NegotiationImage file = new NegotiationImage(multiPartFile, n, MAIN_ACCOUNT);

		file.create();
	}

	public void createNegotiationImplemenation(String name, Building building) throws IOException {
		List<IBusinessCard> list = new IBusinessCardRepository().findAll();
		IBusinessCard bc = list.get(list.size() - 1);

		ICorporation c = new ICorporationRepository().getHead().get();
		Negotiation n = new Negotiation(name);
		n.setScheduleStartDatetime(LocalDateTime.now());
		n.setScheduleEndDatetime(LocalDateTime.now());
		n.setImplementationStartDatetime(LocalDateTime.now());
		n.setImplementationEndDatetime(LocalDateTime.now());
		n.setCreatedAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		n.setUpdateAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		n.setContent("【実施商談】両店舗ともに高収入店舗であり、60期案\n" + "新潟西店で減坪の以降に伝えられています。\n" + "具体的には48.2坪を減坪の上、48.2坪で\n"
				+ "グリーンパークスを考えているとのことです。\n" + "こちらは温度感としては確率は高いです。\n" + "また長津田店でも同じく館活性化を検討しており、\n"
				+ "賃料効率改善のためシューラルーが減坪の可能性を\n" + "孕んでいるとのことです。\n" + "長津田店はまだ活性化の案も固まっていないため、\n" + "現場所での再契約と\n"
				+ "当初計画通り 食品側の壁面を\n" + "撤去する同場所改装にて進めるつもりです");
		n.setPlace("株式会社ユニー様 4F 事務所");
		n.setDivision("OTHER");
		n.setPriority(4);

		NegotiationCreateFormDTO dto = new NegotiationCreateFormDTO();
		List<Long> accountIds = new ArrayList<>();
		accountIds.add(MAIN_ACCOUNT.getId());
		dto.setAccountIds(accountIds);
		dto.setNoticeIds(accountIds);

		List<Long> brandsIds = new ArrayList<>();
		Brand b = new BrandRepository().getHead().get();
		brandsIds.add(b.getId());
		dto.setBrandIds(accountIds);
		dto.setNoticeIds(accountIds);

		new NegotiationService().createAll(n, dto, MAIN_ACCOUNT);
		n.addInterviewBuilding(building);
		n.addInterviewCorporation(c);
		n.addInterviewBusinessCard(bc);

		List<String> comments = Arrays.asList("各事業部長と話してください。\nよろしくお願いします。", "山口さんに連絡お願いします。\n (^^)/",
				"メールの転送お願いします。\n部長への報告もお願いします。");
		comments.forEach(com -> new NegotiationComment(n, LAST_ACCOUNT, com).create());

		Path path = Paths.get("./_test", "apita.jpg");
		InputStream is = Files.newInputStream(path);
		MultipartFile multiPartFile = new MockMultipartFile("apita.jpg", "apita.jpg", null, is);

		NegotiationImage file = new NegotiationImage(multiPartFile, n, MAIN_ACCOUNT);

		file.create();
	}

	public ICorporation createCorporation(String name) {
		ICorporation corporation = new ICorporation(name);
		return corporation.create();
	}

	public Brand createBrand(String name) {
		Brand b = new Brand();
		b.setName(name);
		b.setBu("****");
		b.setGroupBu("グループ");
		b.setBlmg("blmg");
		b.setIntegrationBu("integrationBu");
		b.setProfitUnit("*ユニット*");
		b.setSsv("*SSV*");
		return b.create();
	}

	public void createBusinessCard(String name) {
		IBusinessCard businessCard = new IBusinessCard(name);
		businessCard.create();
	}

}
