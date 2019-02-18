package jp.co.world.storedevelopment.model;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.dev.RandomTestData;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.FileDivisionRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IAreaRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBlockRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBusinessCardRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.OtherProjectAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectCategoryRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectContractProgressRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectFileRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectImageRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectNegotiationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectOpinionRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectPastConferenceRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectProgressRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectSectionProgressRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectTaskAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectTaskRepository;

public class ProjectTest extends ModelTest {
	Account account;
	IBlock block;
	IArea area;
	IBusinessCard businessCard;
	Negotiation negotiation;
	MultipartFile mockFile;
	MultipartFile mockImage;
	FileDivision fileDivision;

	@Override
	@Before
	public void before() {
		// super.before();
		account = new AccountRepository().getHead().get();
		block = new IBlockRepository().getHead().get();
		area = new IAreaRepository().getHead().get();
		businessCard = new IBusinessCardRepository().getHead().get();
		negotiation = new NegotiationRepository().getHead().get();
		fileDivision = new FileDivisionRepository().getHead().get();

		try {
			mockFile = createFile("sample.pdf");
			mockImage = createFile("apita.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private MultipartFile createFile(String fileName) throws IOException {
		Path path = Paths.get("./_test", fileName);
		InputStream is = Files.newInputStream(path);
		return new MockMultipartFile(fileName, fileName, null, is);
	}

	public Project createProject() {
		Project p = new Project();
		// p.setName("案件名_test");
		// p.setDivision("出店");
		// p.setiBlockId(block.getId());
		// p.setiAreaId(area.getId());
		// p.setiBusinessCardId(businessCard.getId());
		p.setCreateAccount(account);
		return p.create();
	}

	@Test
	public void createData() {
		new ProjectProgressRepository().deleteAllWithResetSerial();
		new ProjectOpinionRepository().deleteAllWithResetSerial();
		new ProjectContractProgressRepository().deleteAllWithResetSerial();
		new ProjectSectionProgressRepository().deleteAllWithResetSerial();
		new ProjectImageRepository().deleteAllWithResetSerial();
		new ProjectFileRepository().deleteAllWithResetSerial();
		new OtherProjectAccountRepository().deleteAllWithResetSerial();
		new ProjectPastConferenceRepository().deleteAllWithResetSerial();
		new ProjectNegotiationRepository().deleteAllWithResetSerial();
		new ProjectTaskAccountRepository().deleteAllWithResetSerial();
		new ProjectTaskRepository().deleteAllWithResetSerial();
		new ProjectCategoryRepository().deleteAllWithResetSerial();
		new ProjectRepository().deleteAllWithResetSerial();

		IntStream.rangeClosed(1, 6).forEach(i -> {
			createProjectCategory(i);
		});

		IntStream.rangeClosed(1, 8).forEach(i -> {
			createProjectProgress(i);
		});

		IntStream.rangeClosed(1, 45).forEach(x -> {
			Project p = createProject(x);

			IntStream.rangeClosed(1, 3).forEach(i -> {
				ProjectTask pt = createProjectTask(p, i);
				createProjectTaskAccount(pt, i);
			});

			createProjectNegotiation(p, 1);

			IntStream.rangeClosed(1, 3).forEach(i -> {
				createProjectPastConference(p, i);
			});

			IntStream.rangeClosed(1, 5).forEach(i -> {
				createOtherProjectAccount(p, i);
			});

			try {
				ProjectFile file = createProjectFile(x, p);
				IntStream.rangeClosed(1, 3).forEach(i -> {
					createProjectOpinion(p, file.getId(), i);
				});
				createProjectImage(x, p);
			} catch (IOException e) {
				e.printStackTrace();
			}

			IntStream.rangeClosed(1, 2).forEach(i -> {
				createProjectSectionProgress(p, i);
			});

			IntStream.rangeClosed(1, 2).forEach(i -> {
				createProjectContractProgress(p, i);
			});

		});
	}

	public ProjectProgress createProjectProgress(int partOfName) {
		String[] category = new String[] { "COMPANY", "COMPANY", "COMPANY", "COMPANY", "NEGOTIATION", "NEGOTIATION",
				"NEGOTIATION", "NEGOTIATION" };
		String[] name = new String[] { "部内検討前 部内検討中", "物件検討会承認", "経営会議・投資委員会承認", "本決裁済み", "交渉前", "交渉中", "合意済み（詳細交渉中）",
				"実施日確定" };

		ProjectProgress model = new ProjectProgress();
		model.setCategory(category[(partOfName - 1) % category.length]);
		model.setName(name[(partOfName - 1) % name.length]);
		model.setCode("PROGRESS" + partOfName);
		model.setPriority(RandomTestData.getInteger(0, 5));
		model.setCreateAccount(account);
		return model.create();
	}

	public ProjectOpinion createProjectOpinion(Project p, Long fileId, int partOfName) {
		String[] category = new String[] { "BRANCH", "VIEW", "BU" };
		ProjectOpinion model = new ProjectOpinion();
		model.setProjectId(p.getId());
		model.setCategory(category[partOfName % category.length]);
		model.setFileId(fileId);
		model.setComment("コメント" + partOfName);
		model.setCreateAccount(account);
		return model.create();
	}

	public ProjectContractProgress createProjectContractProgress(Project p, int partOfName) {
		String[] category = new String[] { "CURRENT", "PROGRESS" };
		String[] form = new String[] { "賃借（通常）", "賃借（定借）", "転借（通常）", "売仕", "その他" };

		ProjectContractProgress model = new ProjectContractProgress();
		model.setProjectId(p.getId());
		model.setCategory(category[partOfName % category.length]);
		model.setForm(form[RandomTestData.getInteger(0, form.length - 1)]);
		model.setContractStartDate(LocalDate.now());
		model.setContractEndDate(LocalDate.now());
		model.setContractNumberOfYear(RandomTestData.getFloat());
		model.setAutoUpdate(partOfName % 2 == 0);
		model.setMemo("メモ" + partOfName);
		model.setCreateAccount(account);
		return model.create();
	}

	public ProjectSectionProgress createProjectSectionProgress(Project p, int partOfName) {
		String[] category = new String[] { "CURRENT", "NEGOTIATION" };
		ProjectSectionProgress model = new ProjectSectionProgress();
		model.setProjectId(p.getId());
		model.setCategory(category[partOfName % category.length]);
		model.setSection("区画" + partOfName);
		model.setFrontage("間口" + partOfName);
		model.setFloor("フロア" + partOfName);
		model.setContractTsubo(new BigDecimal(RandomTestData.getInteger(3)));
		model.setBusinessHours("営業時間" + partOfName);
		model.setMemo("メモ" + partOfName);
		model.setCreateAccount(account);
		return model.create();
	}

	public ProjectFile createProjectFile(int partOfName, Project p) throws IOException {
		ProjectFile pf = new ProjectFile(mockFile, p, account);
		pf.setDisplayName("ファイル名" + partOfName);
		pf.setComment("メモ" + partOfName);
		pf.setDivision(fileDivision.getFileDivisionCode());
		pf.setOutputFlag("T");
		pf.setOutputNumber(1);
		pf.setCreateAccount(account);
		pf.create();
		return pf;
	}

	public ProjectImage createProjectImage(int partOfName, Project p) throws IOException {
		ProjectImage pi = new ProjectImage(mockImage, p, account);
		pi.setDisplayName("ファイル" + partOfName);
		pi.setComment("メモ" + partOfName);
		pi.setDivision(fileDivision.getFileDivisionCode());
		pi.setOutputFlag("F");
		pi.setOutputNumber(2);
		pi.setCreateAccount(account);
		pi.create();
		return pi;
	}

	public OtherProjectAccount createOtherProjectAccount(Project p, int partOfName) {
		String[] category = new String[] { "STORE", "SALES", "SECTION", "FC", "OTHER" };
		OtherProjectAccount model = new OtherProjectAccount();
		model.setProjectId(p.getId());
		model.setCategory(category[partOfName % category.length]);
		model.setiAccountId(account.getId());
		model.setCreateAccount(account);
		return model.create();
	}

	public ProjectPastConference createProjectPastConference(Project p, int partOfName) {
		ProjectPastConference model = new ProjectPastConference();
		model.setProjectId(p.getId());
		model.setName("会議名" + partOfName);
		model.setScheduleDate(LocalDate.now());
		model.setResult("結果" + partOfName);
		model.setAttendees("出席者" + partOfName);
		model.setComment("コメント" + partOfName);
		model.setCreateAccount(account);
		return model.create();
	}

	public ProjectNegotiation createProjectNegotiation(Project p, int partOfName) {
		ProjectNegotiation model = new ProjectNegotiation();
		model.setProjectId(p.getId());
		model.setNegotiationId(negotiation.getId());
		model.setCreateAccount(account);
		return model.create();
	}

	public ProjectTask createProjectTask(Project p, int partOfName) {
		ProjectTask model = new ProjectTask();
		model.setProjectId(p.getId());
		model.setPeriod(LocalDate.now());
		// model.setPeriodTime(LocalTime.now());
		// model.setImportant(partOfName % 2 == 0);
		model.setComplete(partOfName % 2 == 0);
		model.setComment("コメント" + partOfName);
		model.setCreateAccount(account);
		return model.create();
	}

	public ProjectTaskAccount createProjectTaskAccount(ProjectTask p, int partOfName) {
		ProjectTaskAccount model = new ProjectTaskAccount();
		model.setProjectTaskId(p.getId());
		model.setAccountId(account.getId());
		model.setCreateAccount(account);
		return model.create();
	}

	public ProjectCategory createProjectCategory(int partOfName) {
		String[] category = new String[] { "LANDING", "LANDING", "LANDING", "PROJECT", "PROJECT", "PROJECT" };
		String[] name = new String[] { "出店", "退店", "改装(移)", "改装(同)", "継続", "賃料低減(期中)" };
		ProjectCategory model = new ProjectCategory();
		model.setName(name[(partOfName - 1) % name.length]);
		model.setCreateAccount(account);
		return model.create();
	}

	public Project createProject(int partOfName) {
		String[] division = new String[] { "出店", "未読" };
		String[] conclusionPossibilityPercent = new String[] { "30％以下", "30％～70％", "70％以上", "100％" };
		String[] operationForm = new String[] { "FC", "VSPA", "直営" };
		String[] rentIncreaseDecrease = new String[] { "同じ", "増", "減" };
		String[] tenancyPeriod = new String[] { "伴う", "伴わない" };
		String[] requestorName = new String[] { "先方", "自社" };
		String[] stopReason = new String[] { "Reason 1", "Reason 2" };
		Project p = new Project();
		p.setTitle("案件名" + partOfName);
		p.setProjectCategoryId(Long.valueOf(RandomTestData.getInteger(4, 6)));
		p.setDivision(division[RandomTestData.getInteger(0, division.length - 1)]);
		p.setOpen(partOfName % 2 == 0 ? true : false);
		p.setOfficeProjectProgressId(Long.valueOf(RandomTestData.getInteger(1, 4)));
		p.setNegotiationProjectProgressId(Long.valueOf(RandomTestData.getInteger(5, 8)));
		p.setDescription("案件概要" + partOfName);
		p.setStartDate(LocalDate.now());
		p.setBuildingId(1L);
		p.setiShopId(1L);
		p.setShopName("店舗名" + partOfName);
		p.setCorporationId(1L);
		p.setCorporationGpId(1L);
		p.setBrandId(1L);
		p.setiAreaId(1L);
		p.setiBlockId(1L);
		p.setiPrefecturesId(1L);
		p.setActionStatus("行動ステータス" + partOfName);
		p.setInHouseProgress("社内進捗" + partOfName);
		p.setNegotiationProgress("交渉進捗" + partOfName);
		p.setContractProgress("契約進捗" + partOfName);
		p.setSalesPrediction(Long.valueOf(partOfName));
		p.setInvestmentDiscussionTarget(partOfName % 2 == 0 ? true : false);
		p.setExternalReleaseStartDate(LocalDate.now());
		p.setExternalReleaseConfirm(partOfName % 2 == 0 ? true : false);
		p.setStop(partOfName % 2 == 0 ? true : false);
		p.setLandingProjectCategoryId(Long.valueOf(RandomTestData.getInteger(1, 3)));
		p.setLandingMemo("着地補足事項" + partOfName);
		p.setPlanStatusId(1L);
		p.setImplementationDatetime(LocalDate.now());
		p.setiSalesChannelId(1L);
		p.setSalesEndDate(LocalDate.now());
		p.setRentIncreaseDecrease(rentIncreaseDecrease[RandomTestData.getInteger(0, rentIncreaseDecrease.length - 1)]);
		p.setTenancy(partOfName % 2 == 0 ? true : false);
		p.setProfitRate(new BigDecimal(RandomTestData.getInteger(0, 100)));
		p.setTenancyPeriod(tenancyPeriod[RandomTestData.getInteger(0, tenancyPeriod.length - 1)]);
		p.setOtherRequest("先方要望" + partOfName);
		p.setOurRequest("当社要望" + partOfName);
		p.setRequestorName(requestorName[RandomTestData.getInteger(0, requestorName.length - 1)]);
		p.setArticleReviewDate(LocalDate.now());
		p.setArticleReviewResultId(1L);
		p.setArticleReviewResultComment("物件検討会・結果コメント" + partOfName);
		p.setManagementDate(LocalDate.now());
		p.setManagementResultId(1L);
		p.setManagementResultComment("経営会議・結果コメント" + partOfName);
		p.setInvestmentProcessDate(LocalDate.now());
		p.setInvestmentProcessResultId(1L);
		p.setInvestmentProcessResultComment("投資上程・結果コメント" + partOfName);
		p.setCurrentISalesAgencyTargetId(1L);
		p.setProgressISalesAgencyTargetId(2L);
		p.setCreateAccount(account);
		return p.create();
	}

	// @Test
	public void create() {
		Project project = createProject();

		assertNotEquals(Long.valueOf(0), project.getId());

		Optional<Project> projectOption = new ProjectRepository().findById(project.getId());

		if (projectOption.isPresent()) {
			assertNotEquals(Long.valueOf(0), projectOption.get().getId());
		} else {
			fail();
		}
	}
}
