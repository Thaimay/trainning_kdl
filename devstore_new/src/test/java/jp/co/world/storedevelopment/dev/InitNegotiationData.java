package jp.co.world.storedevelopment.dev;

import static jp.co.world.storedevelopment.dev.GlobalVariable.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Brand;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.IBrandIncomeUnit;
import jp.co.world.storedevelopment.model.IBusinessCard;
import jp.co.world.storedevelopment.model.ICorporation;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.NegotiationComment;
import jp.co.world.storedevelopment.model.NegotiationImage;
import jp.co.world.storedevelopment.model.NegotiationInterviewAccount;
import jp.co.world.storedevelopment.model.NegotiationInterviewBrand;
import jp.co.world.storedevelopment.model.NegotiationInterviewBusinessCard;
import jp.co.world.storedevelopment.model.NegotiationNoticeAccount;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BrandRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBrandIncomeUnitRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBusinessCardRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationRepository;
import jp.co.world.storedevelopment.model.service.NegotiationService;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationCreateFormDTO;

public class InitNegotiationData extends InitTestDataSupport {

	private static LocalDateTime mockDateTime = LocalDateTime.of(2018, 03, 12, 11, 35, 00);

	@Override
	public void init(InitTestData main) {
		// InitBuildingData init = new InitBuildingData();
		// init.initMainData(main);
		// init.MAIN_ACCOUNT = main.MAIN_ACCOUNT;
		// mockUpBuildingData(0);

		MAIN_ACCOUNT = main.MAIN_ACCOUNT;
		IntStream.range(0, negotiationSckeduleSize).forEach(i -> createNegotiationSckedule(i));
		IntStream.range(0, negotiationImplementationSize).forEach(i -> createNegotiationImplementation(i));
		IntStream.range(0, negotiationCorporationOnlySize).forEach(i -> createNegotiationForCorporationOnly(i));
		IntStream.range(0, negotiationCorporationOnlySize).forEach(i -> createNegotiationForCorporationTwo(i));
		IntStream.range(0, negotiationOwnOnlySize).forEach(i -> createNegotiationForTitleOnly());
		IntStream.range(0, negotiationINterviewStaffSize).forEach(i -> createNegotiationForInterviewStaffA(i));
		IntStream.range(0, negotiationINterviewStaffSize).forEach(i -> createNegotiationForInterviewStaffB(i));
		IntStream.range(0, negotiationlementationDateSize).forEach(i -> createNegotiationForDatesStartOnly(i));
		IntStream.range(0, negotiationlementationDateSize).forEach(i -> createNegotiationForDatesEndOnly(i));
		IntStream.range(0, negotiationlementationDateSize).forEach(i -> createNegotiationForDatesBoth(i));
		IntStream.range(0, negotiationlementationDivisionSize).forEach(i -> createNegotiationForDivisionNegotiation(i));
		IntStream.range(0, negotiationlementationDivisionSize).forEach(i -> createNegotiationForDivisionINSPECTION(i));
		IntStream.range(0, negotiationlementationDivisionSize).forEach(i -> createNegotiationForDivisionOther(i));
		IntStream.range(0, negotiationlementationFullTextSize).forEach(i -> createNegotiationForFullText(i));
		IntStream.range(0, negotiationlementationFullTextSize).forEach(i -> createNegotiationAllDataByImplement(i));
		// IntStream.range(0, negotiationlementationFullTextSize).forEach(i ->
		// createNegotiationAllDataBySchedule(i));
		IntStream.range(0, fileSize).forEach(i -> {
			try {
				createFile(i);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	private void createNegotiationForCorporationTwo(int i) {
		Negotiation n = new Negotiation("法人２商談" + i);
		ICorporation c = new ICorporationRepository().getHead().get();
		ICorporation createCorporation = new ICorporation("ユニー");
		createCorporation.create();
		n.setDivision("NEGOTIATION");
		n.setContent("【法人2】両店舗ともに高収入店舗であり、60期案\n" + "新潟西店で減坪の以降に伝えられています。\n" + "具体的には48.2坪を減坪の上、48.2坪で\n"
				+ "グリーンパークスを考えているとのことです。\n" + "こちらは温度感としては確率は高いです。\n" + "また長津田店でも同じく館活性化を検討しており、\n"
				+ "賃料効率改善のためシューラルーが減坪の可能性を\n" + "孕んでいるとのことです。\n" + "長津田店はまだ活性化の案も固まっていないため、\n" + "現場所での再契約と\n"
				+ "当初計画通り 食品側の壁面を\n" + "撤去する同場所改装にて進めるつもりです");
		n.setReleaseLevel("NEGOTIATION_LV1");

		NegotiationCreateFormDTO dto = new NegotiationCreateFormDTO();
		new NegotiationService().createAll(n, dto, MAIN_ACCOUNT);

		n.addInterviewCorporation(c);
		n.addInterviewCorporation(createCorporation);
	}

	private void createNegotiationForInterviewStaffA(int i) {
		Negotiation n = new Negotiation("面談者A商談" + i);
		IBusinessCard bc = new IBusinessCardRepository().getHead().get();

		n.setContent("【面談者A】両店舗ともに高収入店舗であり、60期案\n" + "新潟西店で減坪の以降に伝えられています。\n" + "具体的には48.2坪を減坪の上、48.2坪で\n"
				+ "グリーンパークスを考えているとのことです。\n" + "こちらは温度感としては確率は高いです。\n" + "また長津田店でも同じく館活性化を検討しており、\n"
				+ "賃料効率改善のためシューラルーが減坪の可能性を\n" + "孕んでいるとのことです。\n" + "長津田店はまだ活性化の案も固まっていないため、\n" + "現場所での再契約と\n"
				+ "当初計画通り 食品側の壁面を\n" + "撤去する同場所改装にて進めるつもりです");
		n.setReleaseLevel("NEGOTIATION_LV1");

		NegotiationCreateFormDTO dto = new NegotiationCreateFormDTO();
		new NegotiationService().createAll(n, dto, MAIN_ACCOUNT);

		n.addInterviewBusinessCard(bc);
	}

	private void createNegotiationForInterviewStaffB(int i) {
		Negotiation n = new Negotiation("面談者B商談" + i);
		IBusinessCard bc = new IBusinessCardRepository().getHead().get();
		IBusinessCard createBusinessCard = new IBusinessCard();
		createBusinessCard.setCorporationGroup("001");
		createBusinessCard.setCardId(1L);
		createBusinessCard.setCompanyName("会社名");
		createBusinessCard.setDepartmentName("部署名");
		createBusinessCard.setPositionName("役職名");
		createBusinessCard.setName("名前");
		createBusinessCard.setEmail("メールアドレス");
		createBusinessCard.create();

		n.setContent("【面談者B】両店舗ともに高収入店舗であり、60期案\n" + "新潟西店で減坪の以降に伝えられています。\n" + "具体的には48.2坪を減坪の上、48.2坪で\n"
				+ "グリーンパークスを考えているとのことです。\n" + "こちらは温度感としては確率は高いです。\n" + "また長津田店でも同じく館活性化を検討しており、\n"
				+ "賃料効率改善のためシューラルーが減坪の可能性を\n" + "孕んでいるとのことです。\n" + "長津田店はまだ活性化の案も固まっていないため、\n" + "現場所での再契約と\n"
				+ "当初計画通り 食品側の壁面を\n" + "撤去する同場所改装にて進めるつもりです");
		n.setReleaseLevel("NEGOTIATION_LV1");

		NegotiationCreateFormDTO dto = new NegotiationCreateFormDTO();
		new NegotiationService().createAll(n, dto, MAIN_ACCOUNT);

		n.addInterviewBusinessCard(bc);
		n.addInterviewBusinessCard(createBusinessCard);
	}

	private void createNegotiationForDatesStartOnly(int i) {
		Negotiation n = new Negotiation("予定日・実施日startのみ商談" + i);
		NegotiationCreateFormDTO dto = new NegotiationCreateFormDTO();

		n.setScheduleStartDatetime(LocalDateTime.of(2015, 12, 15, 23, 30, 59));
		n.setImplementationStartDatetime(LocalDateTime.of(2015, 12, 15, 23, 30, 59));
		n.setContent("【予定日・実施日startのみ】両店舗ともに高収入店舗であり、60期案\n" + "新潟西店で減坪の以降に伝えられています。\n" + "具体的には48.2坪を減坪の上、48.2坪で\n"
				+ "グリーンパークスを考えているとのことです。\n" + "こちらは温度感としては確率は高いです。\n" + "また長津田店でも同じく館活性化を検討しており、\n"
				+ "賃料効率改善のためシューラルーが減坪の可能性を\n" + "孕んでいるとのことです。\n" + "長津田店はまだ活性化の案も固まっていないため、\n" + "現場所での再契約と\n"
				+ "当初計画通り 食品側の壁面を\n" + "撤去する同場所改装にて進めるつもりです");
		n.setReleaseLevel("NEGOTIATION_LV1");

		new NegotiationService().createAll(n, dto, MAIN_ACCOUNT);
	}

	private void createNegotiationForDatesEndOnly(int i) {
		Negotiation n = new Negotiation("予定日・実施日endのみ商談" + i);
		NegotiationCreateFormDTO dto = new NegotiationCreateFormDTO();

		n.setScheduleEndDatetime(LocalDateTime.of(2015, 12, 15, 23, 30, 59));
		n.setImplementationEndDatetime(LocalDateTime.of(2015, 12, 15, 23, 30, 59));
		n.setContent("【予定日・実施日endのみ】両店舗ともに高収入店舗であり、60期案\n" + "新潟西店で減坪の以降に伝えられています。\n" + "具体的には48.2坪を減坪の上、48.2坪で\n"
				+ "グリーンパークスを考えているとのことです。\n" + "こちらは温度感としては確率は高いです。\n" + "また長津田店でも同じく館活性化を検討しており、\n"
				+ "賃料効率改善のためシューラルーが減坪の可能性を\n" + "孕んでいるとのことです。\n" + "長津田店はまだ活性化の案も固まっていないため、\n" + "現場所での再契約と\n"
				+ "当初計画通り 食品側の壁面を\n" + "撤去する同場所改装にて進めるつもりです");
		n.setReleaseLevel("NEGOTIATION_LV1");

		new NegotiationService().createAll(n, dto, MAIN_ACCOUNT);
	}

	private void createNegotiationForDatesBoth(int i) {
		Negotiation n = new Negotiation("予定日・実施日start/end商談" + i);
		NegotiationCreateFormDTO dto = new NegotiationCreateFormDTO();

		n.setScheduleStartDatetime(LocalDateTime.of(2015, 12, 15, 23, 30, 59));
		n.setScheduleEndDatetime(LocalDateTime.of(2015, 12, 16, 23, 30, 59));
		n.setImplementationStartDatetime(LocalDateTime.of(2015, 12, 15, 23, 30, 59));
		n.setImplementationEndDatetime(LocalDateTime.of(2015, 12, 16, 23, 30, 59));
		n.setContent("【予定日・実施日start/end】両店舗ともに高収入店舗であり、60期案\n" + "新潟西店で減坪の以降に伝えられています。\n" + "具体的には48.2坪を減坪の上、48.2坪で\n"
				+ "グリーンパークスを考えているとのことです。\n" + "こちらは温度感としては確率は高いです。\n" + "また長津田店でも同じく館活性化を検討しており、\n"
				+ "賃料効率改善のためシューラルーが減坪の可能性を\n" + "孕んでいるとのことです。\n" + "長津田店はまだ活性化の案も固まっていないため、\n" + "現場所での再契約と\n"
				+ "当初計画通り 食品側の壁面を\n" + "撤去する同場所改装にて進めるつもりです");
		n.setReleaseLevel("NEGOTIATION_LV1");

		new NegotiationService().createAll(n, dto, MAIN_ACCOUNT);
	}

	private void createNegotiationForDivisionNegotiation(int i) {
		Negotiation n = new Negotiation("区分商談の商談");
		NegotiationCreateFormDTO dto = new NegotiationCreateFormDTO();
		n.setContent("【区分商談】両店舗ともに高収入店舗であり、60期案\n" + "新潟西店で減坪の以降に伝えられています。\n" + "具体的には48.2坪を減坪の上、48.2坪で\n"
				+ "グリーンパークスを考えているとのことです。\n" + "こちらは温度感としては確率は高いです。\n" + "また長津田店でも同じく館活性化を検討しており、\n"
				+ "賃料効率改善のためシューラルーが減坪の可能性を\n" + "孕んでいるとのことです。\n" + "長津田店はまだ活性化の案も固まっていないため、\n" + "現場所での再契約と\n"
				+ "当初計画通り 食品側の壁面を\n" + "撤去する同場所改装にて進めるつもりです");

		new NegotiationService().createAll(n, dto, MAIN_ACCOUNT);

		n.setDivision("NEGOTIATION");
		n.update();
	}

	private void createNegotiationForDivisionINSPECTION(int i) {
		Negotiation n = new Negotiation("区分現調の商談");
		NegotiationCreateFormDTO dto = new NegotiationCreateFormDTO();
		n.setContent("【区分訪問】両店舗ともに高収入店舗であり、60期案\n" + "新潟西店で減坪の以降に伝えられています。\n" + "具体的には48.2坪を減坪の上、48.2坪で\n"
				+ "グリーンパークスを考えているとのことです。\n" + "こちらは温度感としては確率は高いです。\n" + "また長津田店でも同じく館活性化を検討しており、\n"
				+ "賃料効率改善のためシューラルーが減坪の可能性を\n" + "孕んでいるとのことです。\n" + "長津田店はまだ活性化の案も固まっていないため、\n" + "現場所での再契約と\n"
				+ "当初計画通り 食品側の壁面を\n" + "撤去する同場所改装にて進めるつもりです");
		n.setReleaseLevel("NEGOTIATION_LV1");

		new NegotiationService().createAll(n, dto, MAIN_ACCOUNT);

		n.setDivision("INSPECTION");
		n.update();
	}

	private void createNegotiationForDivisionOther(int i) {
		Negotiation n = new Negotiation("区分その他の商談");
		NegotiationCreateFormDTO dto = new NegotiationCreateFormDTO();
		n.setContent("【区分その他】両店舗ともに高収入店舗であり、60期案\n" + "新潟西店で減坪の以降に伝えられています。\n" + "具体的には48.2坪を減坪の上、48.2坪で\n"
				+ "グリーンパークスを考えているとのことです。\n" + "こちらは温度感としては確率は高いです。\n" + "また長津田店でも同じく館活性化を検討しており、\n"
				+ "賃料効率改善のためシューラルーが減坪の可能性を\n" + "孕んでいるとのことです。\n" + "長津田店はまだ活性化の案も固まっていないため、\n" + "現場所での再契約と\n"
				+ "当初計画通り 食品側の壁面を\n" + "撤去する同場所改装にて進めるつもりです");
		n.setReleaseLevel("NEGOTIATION_LV1");

		new NegotiationService().createAll(n, dto, MAIN_ACCOUNT);

		n.setDivision("OTHER");
		n.update();
	}

	private void createNegotiationForFullText(int i) {
		Negotiation n = new Negotiation("全文検索用商談");
		NegotiationCreateFormDTO dto = new NegotiationCreateFormDTO();
		n.setContent("【全文検索用商談】両店舗ともに高収入店舗であり、60期案\n" + "新潟西店で減坪の以降に伝えられています。\n" + "具体的には48.2坪を減坪の上、48.2坪で\n"
				+ "グリーンパークスを考えているとのことです。\n" + "こちらは温度感としては確率は高いです。\n" + "また長津田店でも同じく館活性化を検討しており、\n"
				+ "賃料効率改善のためシューラルーが減坪の可能性を\n" + "孕んでいるとのことです。\n" + "長津田店はまだ活性化の案も固まっていないため、\n" + "現場所での再契約と\n"
				+ "当初計画通り 食品側の壁面を\n" + "撤去する同場所改装にて進めるつもりです");
		n.setReleaseLevel("NEGOTIATION_LV1");

		new NegotiationService().createAll(n, dto, MAIN_ACCOUNT);
		n.setContent("三菱他所リテールマネジメント（株）");
		n.update();
	}

	private void createNegotiationForCorporationOnly(int i) {
		Negotiation n = new Negotiation("法人１商談" + i);
		ICorporation c = new ICorporationRepository().getHead().get();

		NegotiationCreateFormDTO dto = new NegotiationCreateFormDTO();
		n.setContent("【法人１】両店舗ともに高収入店舗であり、60期案\n" + "新潟西店で減坪の以降に伝えられています。\n" + "具体的には48.2坪を減坪の上、48.2坪で\n"
				+ "グリーンパークスを考えているとのことです。\n" + "こちらは温度感としては確率は高いです。\n" + "また長津田店でも同じく館活性化を検討しており、\n"
				+ "賃料効率改善のためシューラルーが減坪の可能性を\n" + "孕んでいるとのことです。\n" + "長津田店はまだ活性化の案も固まっていないため、\n" + "現場所での再契約と\n"
				+ "当初計画通り 食品側の壁面を\n" + "撤去する同場所改装にて進めるつもりです");
		n.setReleaseLevel("NEGOTIATION_LV1");

		new NegotiationService().createAll(n, dto, MAIN_ACCOUNT);

		n.addInterviewCorporation(c);
	}

	private void createNegotiationSckedule(int i) {
		Negotiation n = new Negotiation("アピタ予定-" + i);
		n.setDivision("NEGOTIATION");
		n.setScheduleStartDatetime(mockDateTime);
		n.setScheduleEndDatetime(mockDateTime);
		n.setImplementationStartDatetime(null);
		n.setImplementationEndDatetime(null);
		n.setCreatedAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		n.setUpdateAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		n.setContent("両店舗ともに高収入店舗であり、60期案\n" + "新潟西店で減坪の以降に伝えられています。\n" + "具体的には48.2坪を減坪の上、48.2坪で\n"
				+ "グリーンパークスを考えているとのことです。\n" + "こちらは温度感としては確率は高いです。\n" + "また長津田店でも同じく館活性化を検討しており、\n"
				+ "賃料効率改善のためシューラルーが減坪の可能性を\n" + "孕んでいるとのことです。\n" + "長津田店はまだ活性化の案も固まっていないため、\n" + "現場所での再契約と\n"
				+ "当初計画通り 食品側の壁面を\n" + "撤去する同場所改装にて進めるつもりです");
		n.setNextActionContent("テストネクストアクション");
		n.setPlace("会場場所");
		n.setReleaseLevel("NEGOTIATION_LV1");

		NegotiationCreateFormDTO dto = new NegotiationCreateFormDTO();
		List<Long> accountIds = new ArrayList<>();
		accountIds.add(MAIN_ACCOUNT.getId());
		dto.setAccountIds(accountIds);
		dto.setNoticeIds(accountIds);
		dto.setNoticeSendTypes(Arrays.asList("TO"));

		List<Long> brandsIds = new ArrayList<>();
		Brand b = new BrandRepository().getHead().get();
		brandsIds.add(b.getId());
		dto.setBrandIds(accountIds);

		new NegotiationService().createAll(n, dto, MAIN_ACCOUNT);
	}

	private void createNegotiationImplementation(int i) {
		Negotiation n = new Negotiation("アピタ実施-" + i);

		List<Long> accountIds = new ArrayList<>();
		accountIds.add(MAIN_ACCOUNT.getId());

		n.setDivision("OTHER");
		n.setScheduleStartDatetime(mockDateTime);
		n.setScheduleEndDatetime(mockDateTime);
		n.setImplementationStartDatetime(mockDateTime);
		n.setImplementationEndDatetime(mockDateTime);
		n.setPlace("会場場所");
		n.setContent("【商談実績】両店舗ともに高収入店舗であり、60期案\n" + "新潟西店で減坪の以降に伝えられています。\n" + "具体的には48.2坪を減坪の上、48.2坪で\n"
				+ "グリーンパークスを考えているとのことです。\n" + "こちらは温度感としては確率は高いです。\n" + "また長津田店でも同じく館活性化を検討しており、\n"
				+ "賃料効率改善のためシューラルーが減坪の可能性を\n" + "孕んでいるとのことです。\n" + "長津田店はまだ活性化の案も固まっていないため、\n" + "現場所での再契約と\n"
				+ "当初計画通り 食品側の壁面を\n" + "撤去する同場所改装にて進めるつもりです");
		n.setReleaseLevel("NEGOTIATION_LV1");

		NegotiationCreateFormDTO dto = new NegotiationCreateFormDTO();
		dto.setNoticeIds(accountIds);
		dto.setNoticeSendTypes(Arrays.asList("TO"));

		List<Long> brandsIds = new ArrayList<>();
		Brand b = new BrandRepository().getHead().get();
		brandsIds.add(b.getId());
		dto.setBrandIds(accountIds);

		Negotiation newNegotiation = new NegotiationService().createAll(n, dto, MAIN_ACCOUNT);

		newNegotiation.switchReadLater(MAIN_ACCOUNT);
		newNegotiation.open(MAIN_ACCOUNT);

		List<String> comments = Arrays.asList("各事業部長と話してください。\nよろしくお願いします。", "山口さんに連絡お願いします。\n (^^)/",
				"メールの転送お願いします。\n部長への報告もお願いします。");
		comments.forEach(c -> new NegotiationComment(n, MAIN_ACCOUNT, c).create());
	}

	private void createNegotiationForTitleOnly() {
		Negotiation n = new Negotiation("アピタ長津田新潟西契約交渉");
		n.setContent("【タイトル】両店舗ともに高収入店舗であり、60期案\n" + "新潟西店で減坪の以降に伝えられています。\n" + "具体的には48.2坪を減坪の上、48.2坪で\n"
				+ "グリーンパークスを考えているとのことです。\n" + "こちらは温度感としては確率は高いです。\n" + "また長津田店でも同じく館活性化を検討しており、\n"
				+ "賃料効率改善のためシューラルーが減坪の可能性を\n" + "孕んでいるとのことです。\n" + "長津田店はまだ活性化の案も固まっていないため、\n" + "現場所での再契約と\n"
				+ "当初計画通り 食品側の壁面を\n" + "撤去する同場所改装にて進めるつもりです");
		n.setReleaseLevel("NEGOTIATION_LV1");

		NegotiationCreateFormDTO dto = new NegotiationCreateFormDTO();
		new NegotiationService().createAll(n, dto, MAIN_ACCOUNT);
	}

	private void createFile(int i) throws IOException {
		Account account = new AccountRepository().getHead().get();
		Negotiation n = new NegotiationRepository().getHead().get();

		Path path = Paths.get("./_test", "apita.jpg");
		InputStream is = Files.newInputStream(path);
		MultipartFile multiPartFile = new MockMultipartFile("apita.jpg", "apita.jpg", null, is);

		NegotiationImage file = new NegotiationImage(multiPartFile, n, account);
		file.setOutputNumber(i);
		file.create();
	}

	private void createNegotiationAllDataBySchedule(int i) {
		Negotiation n = new Negotiation();
		Building bu = new BuildingRepository().getHead().get();
		IBrandIncomeUnit br = new IBrandIncomeUnitRepository().getHead().get();
		ICorporation c = new ICorporationRepository().getHead().get();
		n.setDivision("NEGOTIATION");
		n.setScheduleStartDatetime(LocalDateTime.of(2018, 3, 18, 13, 30, 00));
		n.setScheduleEndDatetime(LocalDateTime.of(2018, 3, 18, 15, 30, 00));
		n.setPlace("商談会議場所");
		n.setTitle("全データ入り予定商談");
		n.setContent("商談名刺カードに名前のない人の内容");
		n.setPriority(5);
		n.setRelease(true);
		n.setNextActionContent("次の行動");
		n.setReleaseLevel("NEGOTIATION_LV1");
		NegotiationCreateFormDTO dto = new NegotiationCreateFormDTO();
		new NegotiationService().createAll(n, dto, MAIN_ACCOUNT);
		n.addInterviewCorporation(c);
		n.addInterviewBuilding(bu);
		new NegotiationInterviewBusinessCard(n, "名刺に名前のない人").create();
		new NegotiationInterviewAccount(n, "アカウントに名前のない人").create();
		new NegotiationInterviewBrand(n, br).create();
		new NegotiationNoticeAccount(n, MAIN_ACCOUNT).create();
		// new NegotiationInterviewBuilding(n, new
		// BuildingRepository().getHead().get());
	}

	private void createNegotiationAllDataByImplement(int i) {
		Negotiation n = new Negotiation();
		Account a = MAIN_ACCOUNT;
		// Building bu = new BuildingRepository().getHead().get();
		// Brand br = new BrandRepository().getHead().get();
		ICorporation c = new ICorporationRepository().getHead().get();
		n.setDivision("INSPECTION");
		n.setScheduleStartDatetime(LocalDateTime.of(2018, 2, 28, 12, 00, 00));
		n.setScheduleEndDatetime(LocalDateTime.of(2018, 2, 28, 14, 00, 00));
		n.setImplementationStartDatetime(LocalDateTime.of(2018, 3, 2, 18, 00, 00));
		n.setImplementationEndDatetime(LocalDateTime.of(2018, 3, 2, 19, 30, 00));
		n.setPlace("商談会議場所");
		n.setTitle("全データ入り実施商談");
		n.setContent("実施商談アカウントと名刺の登録＆未登録者表示");
		n.setPriority(5);
		n.setRelease(true);
		n.setNextActionContent("次の行動");
		n.setReleaseLevel("NEGOTIATION_LV1");

		NegotiationCreateFormDTO dto = new NegotiationCreateFormDTO();
		new NegotiationService().createAll(n, dto, MAIN_ACCOUNT);
		n.addInterviewCorporation(c);
		// n.addInterviewBuilding(bu);
		n.addInterviewAccount(MAIN_ACCOUNT);
		IBusinessCard bc = new IBusinessCardRepository().getHead().get();
		IBusinessCard createBusinessCard = new IBusinessCard();
		createBusinessCard.setCorporationGroup("001");
		createBusinessCard.setCardId(1L);
		createBusinessCard.setCompanyName("会社名");
		createBusinessCard.setDepartmentName("部署名");
		createBusinessCard.setPositionName("役職名");
		createBusinessCard.setName("名前");
		createBusinessCard.setEmail("メールアドレス");
		createBusinessCard.create();
		n.addInterviewBusinessCard(bc);
		n.addInterviewBusinessCard(createBusinessCard);
		NegotiationInterviewBusinessCard negotiationInterviewBusinessCard = new NegotiationInterviewBusinessCard(n, "名刺に名前のない人");
		negotiationInterviewBusinessCard.setBusinessCardId(bc.getCardId());
		negotiationInterviewBusinessCard.create();
		new NegotiationInterviewAccount(n, "アカウントに名前のない人").create();
		// new NegotiationInterviewBrand(n, br).create();
		new NegotiationNoticeAccount(n, MAIN_ACCOUNT).create();
		new NegotiationNoticeAccount(n, a).create();
	}
}
