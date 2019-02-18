package jp.co.world.storedevelopment.dev;

import static jp.co.world.storedevelopment.dev.GlobalVariable.iBrandIncomeUnitSize;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Brand;
import jp.co.world.storedevelopment.model.Competition;
import jp.co.world.storedevelopment.model.Department;
import jp.co.world.storedevelopment.model.Division;
import jp.co.world.storedevelopment.model.IArea;
import jp.co.world.storedevelopment.model.IBlock;
import jp.co.world.storedevelopment.model.IBrandIncomeUnit;
import jp.co.world.storedevelopment.model.IBusinessCard;
import jp.co.world.storedevelopment.model.ICorporation;
import jp.co.world.storedevelopment.model.ICorporationGroup;
import jp.co.world.storedevelopment.model.IIncomeUnit;
import jp.co.world.storedevelopment.model.IPlace;
import jp.co.world.storedevelopment.model.IPrefectures;
import jp.co.world.storedevelopment.model.ISalesAgencyTarget;
import jp.co.world.storedevelopment.model.ISalesChannel;
import jp.co.world.storedevelopment.model.IShopCompany;
import jp.co.world.storedevelopment.model.ImportantInformation;
import jp.co.world.storedevelopment.model.Keyman;
import jp.co.world.storedevelopment.model.MBuildingSalesClassifications;
import jp.co.world.storedevelopment.model.MBuildingSalesTypes;
import jp.co.world.storedevelopment.model.MCreditRank;
import jp.co.world.storedevelopment.model.ParticipatingStoreCorporation;
import jp.co.world.storedevelopment.model.Role;
import jp.co.world.storedevelopment.model.SubAccount;
import jp.co.world.storedevelopment.model.Tenant;
import jp.co.world.storedevelopment.model.WorldAuthAccount;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBusinessCardRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;

public class InitCommonData extends InitTestDataSupport {

	//@formatter:off
		public List<Role> roles = Arrays.asList(
				new Role("経営層・店舗開発管理職", "全グループ会社案件が参照可能",
						"","","000000,000001", false, false, false,
						"NEGOTIATION_LV1,ECONOMIC_REQUIREMENT,IMPORTANT_INFORMATION, ROLE, SUB_ACCOUNT"),
				new Role("店舗開発担当",           "全グループ会社案件が参照可能",
						"","821090,821110","000001",  false, false, false,
						"NEGOTIATION_LV1,ECONOMIC_REQUIREMENT,IMPORTANT_INFORMATION, ROLE, SUB_ACCOUNT"),
				new Role("支店営業",               "全グループ会社案件のうち対象エリアの案件が参照可能",
						"000002","821010","000002",         true, false, false,
						"NEGOTIATION_LV1,ECONOMIC_REQUIREMENT"),
				new Role("事業管理者",             "全自社内案件が参照可能",
						"","","H",              false, true, false,
						"NEGOTIATION_LV1,ECONOMIC_REQUIREMENT,SUB_ACCOUNT"),
				new Role("BUMG・店舗MD",           "自利益単位部門のみ参照可能",
						"","1005,3305,3030","000003,000004,000005,000006,00003", false, false, true,
						"NEGOTIATION_LV1"),
				new Role("システム管理者",         "情報管理者",
						"","","J",              false, false, false,
						"NEGOTIATION_LV1,ECONOMIC_REQUIREMENT,IMPORTANT_INFORMATION, ROLE, SUB_ACCOUNT"),
				new Role("テスト", "テスト", "", "", "100000", false, false, false,
						"NEGOTIATION_LV1,NEGOTIATION_LV2_OPEN,ECONOMIC_REQUIREMENT,IMPORTANT_INFORMATION, ROLE, SUB_ACCOUNT").create(),
				new Role("テスト", "テスト", "", "", "200000", false, false, false,
						"NEGOTIATION_LV1,NEGOTIATION_LV2_CLOSE,ECONOMIC_REQUIREMENT,IMPORTANT_INFORMATION, ROLE, SUB_ACCOUNT").create());

	public List<Department> departments = Arrays.asList(
			new Department("000001", "", ""),
			new Department("000002", "", "821090"),
			new Department("000003", "", "821010"),
			new Department("000004", "", ""),
			new Department("000005", "", "1005"),
			new Department("000006", "", ""));

	public List<Account> accounts = Arrays.asList(
			new Account("000000", "経営者", "a0000@test.com", departments.get(0).getDeptCd()),
			new Account("000001", "店舗開発担当", "000001@test.com", departments.get(1).getDeptCd()),
			new Account("000002", "支店営業", "000002@test.com", departments.get(2).getDeptCd()),
			new Account("000003", "事業管理者", "h000003@test.com", departments.get(3).getDeptCd()),
			new Account("000004", "BUMG・店舗MD", "000004@test.com", departments.get(4).getDeptCd()),
			new Account("000005", "システム管理者", "j000005@test.com", departments.get(5).getDeptCd()),
			new Account("000006", "スタッフ", "j000006@test.com", departments.get(5).getDeptCd())
			);
	//@formatter:on

	public List<String> accountNames = Arrays.asList("テスト 太郎", "John Spike", "山田 太郎", "山田 次郎", "山田 三郎", "山田 四郎", "山田 五郎", "山田 六郎");
	public List<String> corporationGroupNames = Arrays.asList("イオングループ", "イオングループ(株)");
	public List<String> corporationNames = Arrays.asList("イオンリーテル(株)", "株式会社ウエノ", "三菱商事UBSリアルティ（株）");
	public List<String> brandNames = Arrays.asList("タケオキクチ", "THE　SHOP TK");
	public List<String> businessCardNames = Arrays.asList("山田 太郎", "鈴木 太郎", "山本 次郎", "山田 次郎", "松本 裕次");
	public List<String> divisionNames = Arrays.asList("館区分");
	public List<String> tenantNames = Arrays.asList("ケンタッキー", "Tscollection", "ノジマ");
	public List<String> competitionNames = Arrays.asList("セリア長津田店");
	public List<String> prefectureNames = Arrays.asList("東京", "大阪");
	public List<String> participatingStoreNames = Arrays.asList("花屋", "定食屋");
	public List<String> iSalesChannelNames = Arrays.asList("SC", "NSC", "駅FB", "百貨店");
	public List<String> iBlockNames = Arrays.asList("ブロック１", "ブロック２");
	public List<String> iAreaNames = Arrays.asList("エリア１", "エリア２");
	public List<String> iSalesAgencyTargetNames = Arrays.asList("販売代理店１", "販売代理店２");
	public List<String> mCreditRanktNames = Arrays.asList("信用ランク１", "信用ランク２");
	public List<String> iPlaceNames = Arrays.asList("場所１", "場所２");
	public List<String> iIncomeUnitNames = Arrays.asList("屋号１", "屋号２");
	public List<String> iBussinessCards = Arrays.asList("名前１", "名前２");
	public List<String> mBuildingSalesClassifications = Arrays.asList("ALL", "直営", "食品(SM)", "専門店", "フロア(B3)", "フロア(10)");
	public List<String> mBuildingSalesTypes = Arrays.asList("実績（A）", "計画（P）", "推定（E）");
	public List<String> iShopCompanys = Arrays.asList("会社名１", "会社名２");

	@Override
	public void init(InitTestData main) {
		initRoles();
		initDepartment();
		initAccount();
		initMainData(main);
	}

	private void initDepartment() {
		departments.forEach(d -> {
			d.create();
		});
	}

	private void initRoles() {
		roles.forEach(r -> {
			r.create();
		});
	}

	private void initAccount() {
		accounts.forEach(a -> createAccount(a));
	}

	private void createAccount(Account a) {
		a.create();
		WorldAuthAccount account = new WorldAuthAccount(a.getMailAddress(), "test1234", a.getFullName());
		account.setApprovalFlg(1);
		account.setSyaincd(a.getEmployeeCd());
		account.create();

		IArea area = new IArea();
		area.setAreaId(new BigDecimal(1));
		area.setAreaCd("search");
		area.setAreaName("検索用");
		area.create();

		SubAccount subAccount = new SubAccount();
		subAccount.setAreaCode(area.getAreaCd());
		subAccount.setEmployeeCode(a.getEmployeeCd());
		subAccount.create();
	}

	private void initMainData(InitTestData main) {
		main.MAIN_ACCOUNT = accounts.get(1);

		IntStream.range(0, iBrandIncomeUnitSize).forEach(i -> createIBrandIncomeUnit());
		corporationGroupNames.stream().forEach(s -> createCorporationGroup(s));
		corporationNames.stream().forEach(s -> createCorporation(s, corporationNames.indexOf(s) + 1));
		brandNames.stream().forEach(s -> createBrand(s));
		businessCardNames.stream().forEach(s -> createIBusinessCard(s));
		divisionNames.stream().forEach(s -> createDivision(s));
		tenantNames.stream().forEach(s -> createTenant(s));
		competitionNames.stream().forEach(s -> createCompetition(s));
		prefectureNames.stream().forEach(s -> createIPrefectures(s));
		participatingStoreNames.stream().forEach(s -> createParticipatingStoreCorporation(s));
		iSalesChannelNames.stream().forEach(s -> createISalesChannel(s));
		iBlockNames.stream().forEach(s -> createIBlock(s));
		iAreaNames.stream().forEach(s -> createIArea(s));
		iSalesAgencyTargetNames.stream().forEach(s -> createISalesAgencyTarget(s));
		mCreditRanktNames.stream().forEach(s -> createMCreditRank(s));
		iPlaceNames.stream().forEach(s -> createIPlace(s));
		iIncomeUnitNames.stream().forEach(s -> createIIncomeUnit(s));
		createImportantInformation();
		createKeyman();
		iBussinessCards.stream().forEach(s -> createIBusinessCard(s));
		mBuildingSalesClassifications.stream().forEach(s -> createMBuildingSalesClassifications(s));
		mBuildingSalesTypes.stream().forEach(s -> createMBuildingSalesTypes(s));
		iShopCompanys.forEach(s -> createIShopCompany(s));
	}

	private void createIBrandIncomeUnit() {
		new IBrandIncomeUnit().create();
	}

	private void createBrand() {
		new Brand().create();
	}

	private void createCorporationGroup(String name) {
		new ICorporationGroup(name).create();
	}

	private void createDivision(String name) {
		new Division(name).create();
	}

	private void createTenant(String name) {
		new Tenant(name).create();
	}

	private void createCompetition(String name) {
		new Competition(name).create();
	}

	private void createIPrefectures(String name) {
		new IPrefectures(name).create();
	}

	private void createParticipatingStoreCorporation(String name) {
		new ParticipatingStoreCorporation(name).create();
	}

	private void createCorporation(String name, Integer index) {
		new ICorporation(name).create();
	}

	private void createBrand(String name) {
		new Brand(name).create();
	}

	private void createIBusinessCard(String name) {
		new IBusinessCard(name).create();
	}

	private void createISalesChannel(String name) {
		new ISalesChannel(name).create();
	}

	private void createIBlock(String name) {
		new IBlock(name).create();
	}

	private void createIArea(String name) {
		new IArea(name).create();
	}

	private void createISalesAgencyTarget(String name) {
		new ISalesAgencyTarget(name).create();
	}

	private void createMCreditRank(String name) {
		new MCreditRank(name).create();
	}

	private void createIPlace(String name) {
		new IPlace(name).create();
	}

	private void createIIncomeUnit(String name) {
		new IIncomeUnit(name).create();
	}

	private void createKeyman() {
		Keyman keyman = new Keyman();
		keyman.setBusinessCardId(new IBusinessCardRepository().getHead().get().getId());
		keyman.setCorporationId(new ICorporationRepository().getHead().get().getId());
		keyman.setCreatedAccountCode(new AccountRepository().getHead().get().getEmployeeCd());
		keyman.setUpdateAccountCode(new AccountRepository().getHead().get().getEmployeeCd());
		keyman.create();
	}

	private void createImportantInformation() {
		ImportantInformation importantInformation = new ImportantInformation();
		LocalDateTime start = LocalDateTime.now();
		LocalDateTime end = LocalDateTime.now();

		start.minus(Period.ofDays(3));
		end.plus(Period.ofDays(3));
		importantInformation.setCreateAccount(accounts.get(1));
		importantInformation.setShowStartDatetime(start);
		importantInformation.setShowEndDatetime(end);
		importantInformation.setContent("内容です");
		importantInformation.setDivision("BUILDING");
		importantInformation.create();

		List<Account> accounts = new AccountRepository().findAll();
		accounts.stream().forEach(a -> {
			importantInformation.addAccessRecord(a);
		});
	}

	private void createMBuildingSalesClassifications(String name) {
		new MBuildingSalesClassifications(name).create();
	}

	private void createMBuildingSalesTypes(String name) {
		new MBuildingSalesTypes(name).create();
	}

	private void createIShopCompany(String name) {
		new IShopCompany(name).create();
	}
}
