package jp.co.world.storedevelopment.dev;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.IntStream;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.IArea;
import jp.co.world.storedevelopment.model.IBlock;
import jp.co.world.storedevelopment.model.IBusinessCard;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.ProjectFile;
import jp.co.world.storedevelopment.model.ProjectImage;
import jp.co.world.storedevelopment.model.ProjectNegotiation;
import jp.co.world.storedevelopment.model.ProjectPersonalDevelop;
import jp.co.world.storedevelopment.model.ProjectReadLaterAccount;
import jp.co.world.storedevelopment.model.ProjectTask;
import jp.co.world.storedevelopment.model.mapper.repository.IAreaRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBlockRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBusinessCardRepository;

public class InitProjectData extends InitTestDataSupport {
	public IBlock iBlock;
	public IArea iArea;
	public IBusinessCard iBusinessCard;
	MultipartFile mockFile;
	MultipartFile mockImage;

	@Override
	public void init(InitTestData main) {
		MAIN_ACCOUNT = main.MAIN_ACCOUNT;
		initMainData(main);
		IntStream.rangeClosed(1, 33).forEach(i -> mockUpProjectData(i));
	}

	public Account getAccount() {
		return MAIN_ACCOUNT;
	}

	public void initMainData(InitTestData main) {
		iBlock = new IBlockRepository().getHead().get();
		iArea = new IAreaRepository().getHead().get();
		iBusinessCard = new IBusinessCardRepository().getHead().get();

		try {
			mockFile = createFile("sample.pdf");
			mockImage = createFile("apita.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Project createProject(int partOfName) {
		String[] division = new String[] { "出店", "未読" };
		Project p = new Project();
		p.setTitle("案件名" + partOfName);
		p.setProjectCategoryId(1L);
		p.setDivision(division[partOfName % division.length]);
		p.setOpen(partOfName % 2 == 0 ? true : false);
		p.setOfficeProjectProgressId(1L);
		p.setNegotiationProjectProgressId(1L);
		p.setDescription("案件概要" + partOfName);
		p.setStartDate(LocalDate.now());
		p.setBuildingId(1L);
		p.setBrandId(1L);
		p.setiAreaId(1L);
		p.setiBlockId(1L);
		p.setiShopId(1L);
		p.initStatusCode(p.getProjectCategoryId());
		p.initProjectActionStatus(p.getProjectCategoryId(), 1L);
		p.setInvestmentDiscussionTarget(partOfName % 2 == 0 ? true : false);
		p.setExternalReleaseStartDate(LocalDate.now());
		p.setExternalReleaseConfirm(partOfName % 2 == 0 ? true : false);
		p.setStop(partOfName % 2 == 0 ? true : false);
		p.setLandingProjectCategoryId(1L);
		p.setLandingMemo("着地補足事項" + partOfName);
		p.setImplementationDatetime(LocalDate.now());
		p.setSalesEndDate(LocalDate.now());
		p.setRentIncreaseDecrease("賃料増減" + partOfName);
		p.setTenancy(partOfName % 2 == 0 ? true : false);
		p.setProfitRate(new BigDecimal(RandomTestData.getInteger(0, 100)));
		p.setTenancyPeriod("定借満了" + partOfName);
		p.setOtherRequest("先方要望" + partOfName);
		p.setOurRequest("当社要望" + partOfName);
		p.setRequestorName("要望先名" + partOfName);
		p.setArticleReviewDate(LocalDate.now());
		p.setArticleReviewResultId(1L);
		p.setArticleReviewResultComment("物件検討会・結果コメント" + partOfName);
		p.setManagementDate(LocalDate.now());
		p.setManagementResultId(1L);
		p.setManagementResultComment("経営会議・結果コメント" + partOfName);
		p.setInvestmentProcessDate(LocalDate.now());
		p.setInvestmentProcessResultId(1L);
		p.setInvestmentProcessResultComment("投資上程・結果コメント" + partOfName);
		p.setCreateAccount(MAIN_ACCOUNT);
		return p.create();
	}

	public ProjectPersonalDevelop createProjectPersonalDevelop(int partOfName, Project project) {
		ProjectPersonalDevelop projectPersonalDevelop = new ProjectPersonalDevelop();
		String[] category = new String[] { "storeDeveloper", "branchsSales", "humanResourceLeader", "businessFC" };
		projectPersonalDevelop.setProjectId(project.getId());
		projectPersonalDevelop.setCategory(category[partOfName % category.length]);
		projectPersonalDevelop.setAccountId(Long.valueOf(partOfName));
		projectPersonalDevelop.setCreateAccount(MAIN_ACCOUNT);
		return projectPersonalDevelop.create();
	}

	public ProjectTask createProjectTask(int partOfName, Project project) {
		ProjectTask projectTask = new ProjectTask();
		projectTask.setProjectId(project.getId());
		// projectTask.setImportant(true);
		projectTask.setPeriod(LocalDate.now());
		//projectTask.setPeriodTime(LocalTime.now());
		projectTask.setComment("コメント" + partOfName);
		projectTask.setCreateAccount(MAIN_ACCOUNT);
		return projectTask.create();
	}

	public ProjectNegotiation createProjectNegotiation(int partOfName, Project project) {
		ProjectNegotiation projectNegotiation = new ProjectNegotiation();
		projectNegotiation.setProjectId(project.getId());
		projectNegotiation.setNegotiationId(Long.valueOf(partOfName));
		projectNegotiation.setCreateAccount(MAIN_ACCOUNT);
		return projectNegotiation.create();
	}

	private MultipartFile createFile(String fileName) throws IOException {
		Path path = Paths.get("./_test", fileName);
		InputStream is = Files.newInputStream(path);
		return new MockMultipartFile(fileName, fileName, null, is);
	}

	public ProjectFile createProjectFile(int partOfName, Project p) throws IOException {
		ProjectFile pf = new ProjectFile(mockFile, p, MAIN_ACCOUNT);
		pf.setDisplayName("ファイル名" + partOfName);
		pf.setComment("メモ" + partOfName);
		pf.setDivision("PROJECT");
		pf.setOutputNumber(partOfName);
		pf.create();
		return pf;
	}

	public ProjectImage createProjectImage(int partOfName, Project p) throws IOException {
		ProjectImage pi = new ProjectImage(mockImage, p, MAIN_ACCOUNT);
		pi.setDisplayName("ファイル" + partOfName);
		pi.setComment("メモ" + partOfName);
		pi.setDivision("PROJECT");
		pi.setOutputNumber(partOfName);
		pi.create();
		return pi;
	}

	public ProjectReadLaterAccount createProjectReadLaterAccount(int partOfName, Project project) {
		ProjectReadLaterAccount projectReadLaterAccount = new ProjectReadLaterAccount(project, MAIN_ACCOUNT);
		projectReadLaterAccount.setProjectId(project.getId());
		projectReadLaterAccount.setCreateAccount(MAIN_ACCOUNT);
		return projectReadLaterAccount.create();
	}

	public Project mockUpProjectData(int i) {
		Project p = createProject(i);

		mockUpProjectPersonalData(7, p);

		mockUpProjectTaskData(5, p);

		mockUpProjectNegotiationData(4, p);

		mockUpProjectReadLaterAccountData(3, p);

		mockUpProjectFileData(4, p);

		mockUpProjectImageData(4, p);

		return p;
	}

	public void mockUpProjectPersonalData(int size, Project p) {
		IntStream.rangeClosed(1, size).forEach(index -> createProjectPersonalDevelop(index, p));
	}

	public void mockUpProjectTaskData(int size, Project p) {
		IntStream.rangeClosed(1, size).forEach(index -> createProjectTask(index, p));
	}

	public void mockUpProjectNegotiationData(int size, Project p) {
		IntStream.rangeClosed(1, size).forEach(index -> createProjectNegotiation(index, p));
	}

	public void mockUpProjectReadLaterAccountData(int size, Project p) {
		IntStream.rangeClosed(1, size).forEach(index -> createProjectReadLaterAccount(index, p));
	}

	public void mockUpProjectFileData(int size, Project p) {
		IntStream.rangeClosed(1, size).forEach(index -> {
			try {
				createProjectFile(index, p);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public void mockUpProjectImageData(int size, Project p) {
		IntStream.rangeClosed(1, size).forEach(index -> {
			try {
				createProjectImage(index, p);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
}
