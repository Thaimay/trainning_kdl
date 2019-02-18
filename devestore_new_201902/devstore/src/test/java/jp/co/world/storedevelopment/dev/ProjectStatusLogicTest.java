package jp.co.world.storedevelopment.dev;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.ISalesChannel;
import jp.co.world.storedevelopment.model.MProjectActionStatus;
import jp.co.world.storedevelopment.model.MProjectProgressStatus;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.ProjectCategory;
import jp.co.world.storedevelopment.model.ProjectSchedule;
import jp.co.world.storedevelopment.model.ProjectSectionProgress;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesChannelRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MProjectActionStatusRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MProjectProgressStatusRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectCategoryRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectScheduleRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectSectionProgressRepository;
import jp.co.world.storedevelopment.sp.controller.dto.MProjectActionStatusScheduleDTO;
import jp.co.world.storedevelopment.utils.JsonParseUtils;

public class ProjectStatusLogicTest extends ModelTest {
	Account account;

	Long projectCategoryId = 1L;
	Long salesChannelId = 1L;
	Long articleResultId = 1L;
	String articleResultComment = "物件検討会結果コメント";
	Long managementResultId = 1L;
	String managementResultComment = "経営会議コメント";
	Long investmentProcessResultId = 1L;
	String investmentProcessResultComment = "投資上程結果コメント";

	@Override
	@Before
	public void before() {
		super.before();
		account = new AccountRepository().getHead().get();
	}

	@Test
	public void getMProjectActionStatusScheduleDTOListTest() {
		Integer year = 2018;
		Integer month = 9;
		Integer day = 21;
		LocalDate targetDate = LocalDate.of(year, month, day);
		List<MProjectActionStatusScheduleDTO> listDto = new ArrayList<MProjectActionStatusScheduleDTO>();
		List<MProjectActionStatus> mProjectActionStatusList  = new MProjectActionStatusRepository().getScheduleUseList(projectCategoryId, salesChannelId);

		mProjectActionStatusList.forEach(m -> {
			listDto.add(new MProjectActionStatusScheduleDTO(m, targetDate));
		});
		System.out.println("MProjectActionStatusScheduleDTOList test: " + JsonParseUtils.parse(listDto));
	}

	@Test
	public void getMProjectActionStatusTest() {
		// 案件新規登録時は案件IDで取得出来ないため、種別はデフォルトで「出店」のものになる()
		ProjectCategory projectCategory = new ProjectCategoryRepository().getHead().get();

		// 館IDを案件登録画面から取得し、販売チャネルを決定する
		Building building = new BuildingRepository().getHead().get();
		ISalesChannel iSalesChannel = new ISalesChannelRepository().findById(building.getiSalesChannelId()).get();

		List<MProjectActionStatus> mProjectActionStatusList = new MProjectActionStatusRepository().getScheduleUseList(projectCategory.getId(), iSalesChannel.getId());
		mProjectActionStatusList.forEach(m -> {
			assertEquals(projectCategory.getId(), m.getProjectCategoryId());
			assertEquals(iSalesChannel.getId(), m.getSalesChannelId());
			assertTrue(m.getScheduleUse());
			assertFalse(m.getIsDeleted());
			System.out.println("puiblic void getMprojectActionStatusListTest: " + JsonParseUtils.parse(m));
		});
	}

	@Test
	public void scheduleDateToDateTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		LocalDate now = LocalDate.now();

		ProjectSchedule projectSchedule = new ProjectSchedule();
		Method method = ProjectSchedule.class.getDeclaredMethod("toLocalDate", String.class);
		method.setAccessible(true);

		// スケジュール記法単体記述 ([0-9]Y, [0-9]M, [0-9]D)
		String test1Str = "1Y";
		LocalDate test1Result = (LocalDate)method.invoke(projectSchedule, test1Str);
		assertEquals(now.plusYears(1).getYear(), test1Result.getYear());

		String test2Str = "2M";
		LocalDate test2Result = (LocalDate)method.invoke(projectSchedule, test2Str);
		assertEquals(now.plusMonths(2).getMonthValue(), test2Result.getMonthValue());

		String test3Str = "3D";
		LocalDate test3Result = (LocalDate)method.invoke(projectSchedule, test3Str);
		assertEquals(now.plusDays(3).getDayOfMonth(), test3Result.getDayOfMonth());

		// 複数のスケジュール記法組み合わせ (YMD の記述順番のみ許容する)
		String test4Str = "1Y2M3D";
		LocalDate test4Result = (LocalDate)method.invoke(projectSchedule, test4Str);
		assertEquals(now.plusYears(1).getYear(), test4Result.getYear());
		assertEquals(now.plusMonths(2).getMonthValue(), test4Result.getMonthValue());
		assertEquals(now.plusDays(3).getDayOfMonth(), test4Result.getDayOfMonth());

		// 入力にゴミが入っている場合、正確な記法の部分のみ反映
		String test5Str = "1Y1y2m4D";
		LocalDate test5Result = (LocalDate)method.invoke(projectSchedule, test5Str);
		assertEquals(now.plusYears(1).getYear(), test5Result.getYear());
		assertEquals(now.getMonthValue(), test5Result.getMonthValue());
		assertEquals(now.plusDays(4).getDayOfMonth(), test5Result.getDayOfMonth());

		System.out.println(test4Result);
	}

	@Test
	public void createProjectSchedule() {
		Project project = new ProjectRepository().getHead().get();
		Building building = new BuildingRepository().findById(project.getBuildingId()).get();
		ISalesChannel iSalesChannel = new ISalesChannelRepository().findById(building.getiSalesChannelId()).get();
		MProjectActionStatus mProjectActionStatus = new MProjectActionStatusRepository()
				.getScheduleUseList(project.getProjectCategoryId(), iSalesChannel.getId()).get(0);

		ProjectSchedule projectSchedule = new ProjectSchedule(project, mProjectActionStatus);
		projectSchedule.create();

		ProjectSchedule createRecord = new ProjectScheduleRepository().getProjectSchedule(project.getId(), mProjectActionStatus.getId()).get();
		System.out.println("public void createProjectSchedule: mProjectActionStatus.getId " + mProjectActionStatus.getId());
		System.out.println("public void createProjectSchedule: projectSchedule.getScheduleDate " + projectSchedule.getScheduleDate());
		System.out.println("public void createProjectSchedule: createRecord.getScheduleDate " + JsonParseUtils.parse(createRecord));
	}

	@Test
	public void setStatusCodeTest() {
		Project project = new Project();

		Long projectCategoryId = new ProjectCategoryRepository().getHead().get().getId();
		Integer priority = 1;

		String category = MProjectProgressStatus.COMPANY;
		MProjectProgressStatus projectProgress = new MProjectProgressStatusRepository().getMProjectProgressStatus(category, projectCategoryId, priority).get();
		project.changeStatusCodeIgnoreOther(projectProgress);
		assertEquals(projectProgress.getCode(), project.getInHouseProgress());

		category = MProjectProgressStatus.NEGOTIATION;
		projectProgress = new MProjectProgressStatusRepository().getMProjectProgressStatus(category, projectCategoryId, priority).get();
		project.changeStatusCodeIgnoreOther(projectProgress);
		assertEquals(projectProgress.getCode(), project.getNegotiationProgress());

		new ProjectRepository().getHead().get();
	}

	@Test
	public void getInitProjectProgress() {
		Project project = new Project();
		Long projectCategoryId = new ProjectCategoryRepository().getHead().get().getId();
		project.initStatusCode(projectCategoryId);

		List<MProjectProgressStatus> projectProgressList = new MProjectProgressStatusRepository().getInitMProjectProgressStatus(projectCategoryId);
		projectProgressList.forEach(projectProgress -> {
			switch (projectProgress.getCategory()) {
			case MProjectProgressStatus.COMPANY:
				assertEquals(projectProgress.getCode(), project.getInHouseProgress());
				break;
			case MProjectProgressStatus.NEGOTIATION:
				assertEquals(projectProgress.getCode(), project.getNegotiationProgress());
				break;
			case MProjectProgressStatus.OTHER:
				assertEquals(projectProgress.getCode(), project.getContractProgress());
				break;
			}
		});
	}

	public Project initProjectProgress() {
		Project project = new Project();
		Long buildingId = new BuildingRepository().getHead().get().getId();
		project.setBuildingId(buildingId);
		project.initStatusCode(buildingId);

		project.setProjectCategoryId(projectCategoryId);
		project.registerStartDate(LocalDate.now());
		return project;
	}

	public Project articleReviewResult() {
		Project project = initProjectProgress();
		project.setArticleReviewResultId(articleResultId);
		project.setArticleReviewResultComment(articleResultComment);
		project.registerArticleReviewResult();
		return project;
	}

	public Project managementResult() {
		Project project = articleReviewResult();
		project.setInvestmentDiscussionTarget(false);
		project.setManagementResultId(managementResultId);
		project.setManagementResultComment(managementResultComment);
		project.registerManagementResult();
		return project;
	}

	public Project investmentResult() {
		Project project = managementResult();
		project.setInvestmentDiscussionTarget(true);
		project.setInvestmentProcessResultId(investmentProcessResultId);
		project.setInvestmentProcessResultComment(investmentProcessResultComment);
		project.registerInvestmentResult();
		return project;
	}

	@Test
	public void registerStartDateTest() {
		// 部内検討中への昇格
		Project project = initProjectProgress();
		List<MProjectProgressStatus> projectProgressList = new MProjectProgressStatusRepository().getMProjectProgressStatusList(MProjectProgressStatus.COMPANY, projectCategoryId);

		assertEquals(projectProgressList.get(1).getCode(), project.getInHouseProgress());
	}

	@Test
	public void registerArticleReviewResult() {
		Long notUpgradeResultId = 3L;
		Project project = articleReviewResult();
		List<MProjectProgressStatus> projectProgressList = new MProjectProgressStatusRepository().getMProjectProgressStatusList(MProjectProgressStatus.COMPANY, projectCategoryId);

		assertEquals(articleResultId, project.getArticleReviewResultId());
		assertEquals(articleResultComment, project.getArticleReviewResultComment());
		assertEquals(projectProgressList.get(2).getCode(), project.getInHouseProgress());

		// 部内検討前 (null)
		project = initProjectProgress();
		project.setArticleReviewResultId(null);
		project.setInHouseProgress(MProjectProgressStatus.BEFORE_CONSIDERATION);
		project.registerArticleReviewResult();
		assertEquals(null, project.getArticleReviewResultId());
		assertEquals(null, project.getArticleReviewResultComment());
		assertEquals(MProjectProgressStatus.BEFORE_CONSIDERATION, project.getInHouseProgress());

		// 部内検討中->物件検討会承認の場合 (昇格有り、承認の場合)
		project = initProjectProgress();
		project.setArticleReviewResultId(articleResultId);
		project.registerArticleReviewResult();
		assertEquals(articleResultId, project.getArticleReviewResultId());
		assertEquals(null, project.getArticleReviewResultComment());
		assertEquals(projectProgressList.get(2).getCode(), project.getInHouseProgress());

		// 部内検討中->物件検討会承認の場合 (昇格有り、条件付承認の場合)
		Long conditionalApprovalId = 2L;
		project = initProjectProgress();
		project.setArticleReviewResultId(conditionalApprovalId);
		project.registerArticleReviewResult();
		assertEquals(conditionalApprovalId, project.getArticleReviewResultId());
		assertEquals(null, project.getArticleReviewResultComment());
		assertEquals(projectProgressList.get(2).getCode(), project.getInHouseProgress());


		// 物件検討中で止まる場合 (昇格無し)
		project = initProjectProgress();
		project.setArticleReviewResultId(notUpgradeResultId);
		project.registerArticleReviewResult();
		assertEquals(notUpgradeResultId, project.getArticleReviewResultId());
		assertEquals(null, project.getArticleReviewResultComment());
		assertEquals(projectProgressList.get(1).getCode(), project.getInHouseProgress());

	}

	@Test
	public void registerManagementResultTest() {
		Project project = managementResult();
		List<MProjectProgressStatus> projectProgressList = new MProjectProgressStatusRepository().getMProjectProgressStatusList(MProjectProgressStatus.COMPANY, projectCategoryId);

		// 物件検討会承認->経営会議、投資委員会承認 (昇格有り)
		assertEquals(managementResultId, project.getManagementResultId());
		assertEquals(managementResultComment, project.getManagementResultComment());
		assertEquals(projectProgressList.get(3).getCode(), project.getInHouseProgress());

		// 投資検討会 ON のため物件検討会承認で止まる場合 (昇格無し)
		project = articleReviewResult();
		project.setInvestmentDiscussionTarget(true);
		project.setManagementResultId(managementResultId);
		project.setManagementResultComment(managementResultComment);
		project.registerManagementResult();

		assertEquals(managementResultId, project.getManagementResultId());
		assertEquals(managementResultComment, project.getManagementResultComment());
		assertEquals(projectProgressList.get(2).getCode(), project.getInHouseProgress());

		// 結果が承認にならず、物件検討会承認で止まる場合 (昇格無し)
		Long notUpgradeResultId = 3L;
		project = articleReviewResult();
		project.setInvestmentDiscussionTarget(false);
		project.setManagementResultId(notUpgradeResultId);
		project.setManagementResultComment(managementResultComment);
		project.registerManagementResult();

		assertEquals(notUpgradeResultId, project.getManagementResultId());
		assertEquals(managementResultComment, project.getManagementResultComment());
		assertEquals(MProjectProgressStatus.PROPERTY_CONSIDERATION_APPROVAL, project.getInHouseProgress());
	}

	@Test
	public void registerInvestmentResultTest() {
		Project project = investmentResult();
		List<MProjectProgressStatus> projectProgressList = new MProjectProgressStatusRepository().getMProjectProgressStatusList(MProjectProgressStatus.COMPANY, projectCategoryId);

		// 物件検討会承認->経営会議、投資委員会承認 (昇格有り)
		assertEquals(investmentProcessResultId, project.getInvestmentProcessResultId());
		assertEquals(investmentProcessResultComment, project.getInvestmentProcessResultComment());
		assertEquals(projectProgressList.get(3).getCode(), project.getInHouseProgress());

		// 投資検討会 OFF のため物件検討会承認で止まる場合 (昇格無し)
		project = managementResult();
		project.setInvestmentDiscussionTarget(false);
		project.setInHouseProgress(MProjectProgressStatus.PROPERTY_CONSIDERATION_APPROVAL);
		project.registerInvestmentResult();

		assertEquals(null, project.getInvestmentProcessResultId());
		assertEquals(null, project.getInvestmentProcessResultComment());
		assertEquals(projectProgressList.get(2).getCode(), project.getInHouseProgress());

		// 結果が承認にならず、物件検討会承認で止まる場合 (昇格無し)
		Long notUpgradeResultId = 3L;
		project = managementResult();
		project.setInvestmentProcessResultId(notUpgradeResultId);
		project.setInvestmentDiscussionTarget(false);
		project.setInHouseProgress(MProjectProgressStatus.PROPERTY_CONSIDERATION_APPROVAL);
		project.registerInvestmentResult();

		assertEquals(notUpgradeResultId, project.getInvestmentProcessResultId());
		assertEquals(null, project.getInvestmentProcessResultComment());
		assertEquals(MProjectProgressStatus.PROPERTY_CONSIDERATION_APPROVAL, project.getInHouseProgress());
	}

	@Test
	public void changeInvestmentDiscussionTargetFlgTest() {
		Project project = managementResult();
		project.changeInvestmentDiscussionTargetFlg(true);

		assertEquals(MProjectProgressStatus.PROPERTY_CONSIDERATION_APPROVAL, project.getInHouseProgress());
		assertEquals(true, project.getInvestmentDiscussionTarget());
	}

	@Test
	public void createProjectNegotiationTest() {
		Project project = new Project();
		project.setProjectCategoryId(projectCategoryId);
		project.registerProjectNegotiation();

		assertEquals(MProjectProgressStatus.IN_NEGOTIATION, project.getNegotiationProgress());
	}

	@Test
	public void editProjectNegotiationTest() {
		Project project = new Project();
		Long projectCategoryId = new ProjectCategoryRepository().getHead().get().getId();
		project.setProjectCategoryId(projectCategoryId);
		project.registerProjectNegotiation();

		project.setNegotiationProgress(MProjectProgressStatus.AGREEMENT);
		assertEquals(MProjectProgressStatus.AGREEMENT, project.getNegotiationProgress());

		project.setNegotiationProgress(MProjectProgressStatus.UNSATISFIED);
		assertEquals(MProjectProgressStatus.UNSATISFIED, project.getNegotiationProgress());
	}

	@Test
	public void registerTsuboTest() {
		Project project = new Project();
		project.setProjectCategoryId(projectCategoryId);
		ProjectSectionProgress projectSectionProgress = new ProjectSectionProgress();
		projectSectionProgress.setCategory("NEGOTIATION");
		projectSectionProgress.setContractTsubo(new BigDecimal(10));
		project.registerTsubo(projectSectionProgress);

		assertEquals(MProjectProgressStatus.AREA_ENTERED, project.getContractProgress());
	}

	@Test
	public void registerSectionTest() {
		Project project = new Project();
		project.setProjectCategoryId(projectCategoryId);
		ProjectSectionProgress projectSectionProgress = new ProjectSectionProgress();
		projectSectionProgress.setCategory("NEGOTIATION");
		projectSectionProgress.setSection("区画");
		project.registerSection(projectSectionProgress);

		assertEquals(MProjectProgressStatus.SECTION_ENTERED, project.getContractProgress());
	}

	@Test
	public void checkOpeningDateTest() {
		Project project = new ProjectRepository().getHead().get();
		project.setProjectCategoryId(projectCategoryId);
		Optional<ProjectSectionProgress> negotiationSectionOption = new ProjectSectionProgressRepository()
				.getNegotiationSectionProgressByProjectId(project.getId());
		negotiationSectionOption.ifPresent(negotiationSection -> {
			project.checkOpeningDate(negotiationSection);
		});


		assertEquals(MProjectProgressStatus.OPENING_STORE, project.getContractProgress());
	}

	@Test
	public void initProjectActionStatusTest() {
		Project project = new Project();
		Long projectCategoryId = new ProjectCategoryRepository().getHead().get().getId();
		project.setProjectCategoryId(projectCategoryId);
		Building building = new BuildingRepository().getHead().get();
		ISalesChannel iSalesChannel = new ISalesChannelRepository().findById(building.getiSalesChannelId()).get();
		project.initProjectActionStatus(projectCategoryId, iSalesChannel.getId());

		MProjectActionStatus initMProjectActionStatus = new MProjectActionStatusRepository()
				.getInitStatus(projectCategoryId, iSalesChannel.getId()).get();

		assertEquals(initMProjectActionStatus.getId(), project.getMProjectActionStatusId());
		assertEquals(initMProjectActionStatus.getName(), project.getActionStatus());
	}

	@Test
	public void calculateActionStatusTest() {
		Project project = new Project();
		Long actionStatusId;
		Long projectCategoryId = new ProjectCategoryRepository().getHead().get().getId();
		project.setProjectCategoryId(projectCategoryId);
		Building building = new BuildingRepository().getHead().get();
		ISalesChannel iSalesChannel = new ISalesChannelRepository().findById(building.getiSalesChannelId()).get();

		// sort値が一番高い物をステータスに使う (COMPANYの値が一番高くなる場合)
		project.setInHouseProgress(MProjectProgressStatus.PROPERTY_CONSIDERATION_APPROVAL);
		project.setNegotiationProgress(MProjectProgressStatus.IN_NEGOTIATION);
		project.setContractProgress(MProjectProgressStatus.AREA_ENTERED);
		project.registerActionStatus(iSalesChannel.getId());
		// 物件上程
		actionStatusId = (long) 4;
		assertEquals(actionStatusId, project.getMProjectActionStatusId());
		assertEquals("物件上程", project.getActionStatus());

		// sort値が一番高い物をステータスに使う (NEGOTIATIONの値が一番高くなる場合)
		project.setInHouseProgress(MProjectProgressStatus.BEFORE_CONSIDERATION);
		project.setNegotiationProgress(MProjectProgressStatus.AGREEMENT);
		project.setContractProgress(MProjectProgressStatus.AREA_ENTERED);
		project.registerActionStatus(iSalesChannel.getId());
		// 基本合意
		actionStatusId = (long) 5;
		assertEquals(actionStatusId, project.getMProjectActionStatusId());
		assertEquals("基本合意", project.getActionStatus());

		// その他のみ進捗が進んでいて、他が追いついていない場合は COMPANY, NEGOTIATION の二つの中でsort値が一番高い物をステータスに使う
		project.initStatusCode(projectCategoryId);
		project.setInHouseProgress(MProjectProgressStatus.PROPERTY_CONSIDERATION_APPROVAL);
		project.setNegotiationProgress(MProjectProgressStatus.IN_NEGOTIATION);
		project.setContractProgress(MProjectProgressStatus.OPENING_STORE);
		project.registerActionStatus(iSalesChannel.getId());
		// 物件上程
		actionStatusId = (long) 4;
		assertEquals(actionStatusId, project.getMProjectActionStatusId());
		assertEquals("物件上程", project.getActionStatus());

		// 中止の場合 (COMPANY, NEGOTIATION, OTHER の全てが空欄の状態の場合)
		project.initStatusCode(projectCategoryId);
		project.setInHouseProgress("");
		project.setNegotiationProgress("");
		project.setContractProgress("");
		project.registerActionStatus(iSalesChannel.getId());
		// 中止
		actionStatusId = (long) 8;
		assertEquals(actionStatusId, project.getMProjectActionStatusId());
		assertEquals("中止", project.getActionStatus());

	}
}
