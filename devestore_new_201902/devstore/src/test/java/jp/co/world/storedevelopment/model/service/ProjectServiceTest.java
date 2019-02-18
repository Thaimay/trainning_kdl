package jp.co.world.storedevelopment.model.service;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.OtherProjectAccount;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.ProjectFile;
import jp.co.world.storedevelopment.model.ProjectImage;
import jp.co.world.storedevelopment.model.ProjectSchedule;
import jp.co.world.storedevelopment.model.ProjectTask;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IAreaRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBlockRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBusinessCardRepository;
import jp.co.world.storedevelopment.model.mapper.repository.OtherProjectAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectFileRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectImageRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectScheduleRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectTaskRepository;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectCreateDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectScheduleDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectUpdateDTO;

public class ProjectServiceTest {

	private Account account;

	private Project project;

	private ProjectCreateDTO dto;

	private ProjectUpdateDTO update;

	public ProjectServiceTest() {
		new ProjectScheduleRepository().deleteAllWithResetSerial();
		try {
			account = new AccountRepository().getHead().get();
			dto = getProjectDTO();

			project = new ProjectService().createAll(dto, account);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private ProjectCreateDTO getProjectDTO() {

		Long iAreaId = new IAreaRepository().getHead().get().getId();

		Long iBlockId = new IBlockRepository().getHead().get().getId();

		Long iBusinessId = new IBusinessCardRepository().getHead().get().getId();

		Long mActionStatusId = (long) 1;

		// List<OtherProjectAccount> otherProjectAccounts = new
		// OtherProjectAccountRepository().findAll().subList(0, 8);

		List<ProjectTask> projectTasks = new ProjectTaskRepository().findAll().subList(0, 1);

		List<ProjectImage> projectImageList = getNewImage();

		List<ProjectFile> projectFiles = getNewFile();

		List<ProjectScheduleDTO> projectScheduleDto = getNewProjectSchedule();

		ProjectCreateDTO dto = new ProjectCreateDTO();
		dto.setTitle("Project Name test");
		dto.setDivision("出店");
		dto.setProjectCategoryId(1L);
		dto.setMProjectActionStatusId(1L);
		// dto.setOpenInformation("案件公開情報_test");
		// dto.setDescription("案件概要_test");
		// dto.setStartDatetime("案件開始日_test");
		// dto.setiAreaId(iAreaId);
		// dto.setiBlockId(iBlockId);
		// dto.setiBusinessCardId(iBusinessId);
		// dto.setInHouseProgress("社内進捗_test");
		// dto.setNegotiationProgress("交渉進捗_test");
		// dto.setContractProgress("契約進捗_test");
		// dto.setExternalRelease(LocalDateTime.now());
		// dto.setExternalReleaseConfirm("外部公開可能日_test");
		// dto.setExternalReleaseStartDate("外部公開可能日確定_test");
		// dto.setActionStatus("行動ステータス_test");
		// dto.setActionSchedule("行動スケジュール_test");
		// dto.setArticleReviewResult("物件検討会・結果_test");
		// dto.setArticleReviewDatetime(LocalDateTime.now());
		// dto.setManagementDatetime(LocalDateTime.now());
		// dto.setManagementResult("経営会議・結果_test");
		// dto.setInvestmentProcessDatetime(LocalDateTime.now());
		// dto.setInvestmentProcessResult("投資上程・結果_test");
		// dto.setInvestmentProcessResultComment("投資上程・結果コメント_test");
		// dto.setContractUpdate("契約更新_test");
		// dto.setEconomyCondition("経済条件_test");
		// dto.setTenancy("定借化_test");
		// dto.setAgainContractStartDate(LocalDateTime.now());
		// dto.setAgainContractEndDate(LocalDateTime.now());
		// dto.setTransferDivision("移転・区分_test");
		// dto.setTransferImplementationDatetime(LocalDateTime.now());
		// dto.setTransferPeriod("移転・実施期_test");
		// dto.setTransferRequest("移転・要望_test");
		// dto.setTransferTenancy("移転・定借満了_test");
		// dto.setOpeningStoreForm("出店・形態 _test");
		// dto.setOpeningStoreAdoptDifficulty("出店・区画_test");
		// dto.setOpeningStoreCorporationName("出店・販売区分_test");
		// dto.setOpeningStoreSalesDivision("出店・採用難易度_test");
		// dto.setOpeningStoreSection("出店・法人名_test");
		// dto.setRedecorationExpectFloor("改装・館期待月坪_test");
		// dto.setRedecorationFrontage("改装・間口_test");
		// dto.setRedecorationGroundFloor("改装・坪数_test");
		// dto.setRedecorationLastSalesDatetime(LocalDateTime.now());
		// dto.setLandPossibility("着地可能性_test");
		// dto.setImportantContact("重要連絡事項_test");
		// dto.setBuOpinion("BU見解_test");
		// dto.setBusinessHours("営業時間 _test");
		// dto.setBusinessHoursChangeStartDate(LocalDateTime.now());
		// dto.setBranchStoreOpinion("支店見解_test");
		// dto.setManpowerSchema("人員計画_test");
		// dto.setNeedfullyManpowerDatetime(LocalDateTime.now());
		// dto.setNeedfullyManpowerNumber(60);
		// dto.setScheduleManpower("スケジュール_test");

		dto.setProjectImageDto(projectImageList);
		dto.setProjectFileDto(projectFiles);
		dto.setProjectTaskDto(projectTasks);
		dto.setProjectScheduleDto(projectScheduleDto);
		// dto.setProjectPersonalDevelopDto(projectPersonalDevelops);

		return dto;
	}

	private ProjectUpdateDTO updateProjectFormDTO() {

		Long iAreaId = new IAreaRepository().findAll().get(1).getId();

		Long iBlockId = new IBlockRepository().findAll().get(1).getId();

		Long iBusinessId = new IBusinessCardRepository().findAll().get(1).getId();

		Long mActionStatusId = (long) 1;

		List<OtherProjectAccount> otherProjectAccounts = updateOtherProjectAccount();

		List<ProjectTask> projectTasks = updateProjectTask();

		// List<ProjectImage> projectImageList = updateImage();

		// List<ProjectFile> projectFiles = updateFile();

		List<ProjectScheduleDTO> projectScheduleDto = updateProjectSchedule();

		Project getProjectFromDB = new ProjectRepository().findById(project.getId()).get();

		ProjectUpdateDTO updateDto = new ProjectUpdateDTO(getProjectFromDB);

		// updateDto.setName("Project Name update");
		// updateDto.setDivision("出店");
		// updateDto.setOpenInformation("案件公開情報_update");
		// updateDto.setDescription("案件概要_update");
		// updateDto.setStartDatetime("案件開始日_update");
		// updateDto.setiAreaId(iAreaId);
		// updateDto.setiBlockId(iBlockId);
		// updateDto.setiBusinessCardId(iBusinessId);
		// updateDto.setInHouseProgress("社内進捗_update");
		// updateDto.setNegotiationProgress("交渉進捗_update");
		// updateDto.setContractProgress("契約進捗_update");
		// updateDto.setExternalRelease(LocalDateTime.now());
		// updateDto.setExternalReleaseConfirm("外部公開可能日_update");
		// updateDto.setExternalReleaseStartDate("外部公開可能日確定_update");
		// updateDto.setActionStatus("行動ステータス_update");
		// updateDto.setActionSchedule("行動スケジュール_update");
		// updateDto.setArticleReviewResult("物件検討会・結果_update");
		// updateDto.setArticleReviewDatetime(LocalDateTime.now());
		// updateDto.setManagementDatetime(LocalDateTime.now());
		// updateDto.setManagementResult("経営会議・結果_update");
		// updateDto.setInvestmentProcessDatetime(LocalDateTime.now());
		// updateDto.setInvestmentProcessResult("投資上程・結果_update");
		// updateDto.setInvestmentProcessResultComment("投資上程・結果コメント_update");
		// updateDto.setContractUpdate("契約更新_update");
		// updateDto.setEconomyCondition("経済条件_update");
		// updateDto.setTenancy("定借化_update");
		// updateDto.setAgainContractStartDate(LocalDateTime.now());
		// updateDto.setAgainContractEndDate(LocalDateTime.now());
		// updateDto.setTransferDivision("移転・区分_update");
		// updateDto.setTransferImplementationDatetime(LocalDateTime.now());
		// updateDto.setTransferPeriod("移転・実施期_update");
		// updateDto.setTransferRequest("移転・要望_update");
		// updateDto.setTransferTenancy("移転・定借満了_update");
		// updateDto.setOpeningStoreForm("出店・形態 _update");
		// updateDto.setOpeningStoreAdoptDifficulty("出店・区画_update");
		// updateDto.setOpeningStoreCorporationName("出店・販売区分_update");
		// updateDto.setOpeningStoreSalesDivision("出店・採用難易度_update");
		// updateDto.setOpeningStoreSection("出店・法人名_update");
		// updateDto.setRedecorationExpectFloor("改装・館期待月坪_update");
		// updateDto.setRedecorationFrontage("改装・間口_update");
		// updateDto.setRedecorationGroundFloor("改装・坪数_update");
		// updateDto.setRedecorationLastSalesDatetime(LocalDateTime.now());
		// updateDto.setLandPossibility("着地可能性_update");
		// updateDto.setImportantContact("重要連絡事項_update");
		// updateDto.setBuOpinion("BU見解_update");
		// updateDto.setBusinessHours("営業時間 _update");
		// updateDto.setBusinessHoursChangeStartDate(LocalDateTime.now());
		// updateDto.setBranchStoreOpinion("支店見解_update");
		// updateDto.setManpowerSchema("人員計画_update");
		// updateDto.setNeedfullyManpowerDatetime(LocalDateTime.now());
		// updateDto.setNeedfullyManpowerNumber(60);
		// updateDto.setScheduleManpower("スケジュール_update");
		//
		// updateDto.setProjectImageDto(projectImageList);
		// updateDto.setProjectFileDto(projectFiles);
		// updateDto.setProjectTaskDto(projectTasks);
		// updateDto.setOtherProjectAccountDto(otherProjectAccounts);
		updateDto.setProjectScheduleDto(projectScheduleDto);

		return updateDto;
	}

	private List<ProjectTask> updateProjectTask() {
		List<ProjectTask> list = new ProjectTaskRepository().findByProjectId(project.getId());
		List<Long> listAccountId = new AccountRepository().findAll().stream().map(b -> b.getId())
				.collect(Collectors.toList());

		Random rand = new Random();
		for (ProjectTask pt : list) {
			// pt.setAccountId(listAccountId.get(rand.nextInt(listAccountId.size())));
			pt.setPeriod(LocalDate.now());
			//pt.setPeriodTime(LocalTime.now());
			pt.setComment("コメント_update");
		}

		ProjectTask addProjectTask = new ProjectTask();
		addProjectTask.setId(Long.valueOf(0));
		// addProjectTask.setAccountId(listAccountId.get(rand.nextInt(listAccountId.size())));
		addProjectTask.setProjectId(project.getId());
		addProjectTask.setPeriod(LocalDate.now());
		//addProjectTask.setPeriodTime(LocalTime.now());
		addProjectTask.setComment("コメント_update");
		list.add(addProjectTask);

		return list;
	}

	private List<OtherProjectAccount> updateOtherProjectAccount() {
		List<OtherProjectAccount> list = new OtherProjectAccountRepository().findByProjectId(project.getId());
		List<Long> listAccountId = new AccountRepository().findAll().stream().map(b -> b.getId())
				.collect(Collectors.toList());
		String[] category = { "branchsSales", "storeDeveloper", "businessFC", "humanResourceLeader" };
		List<String> listCategory = Arrays.asList(category);

		Random rand = new Random();
		for (OtherProjectAccount opa : list) {
			opa.setiAccountId(listAccountId.get(rand.nextInt(listAccountId.size())));
			opa.setCategory(listCategory.get(rand.nextInt(4)));
		}

		OtherProjectAccount addOtherProjectAccount = new OtherProjectAccount();
		addOtherProjectAccount.setiAccountId(listAccountId.get(rand.nextInt(listAccountId.size())));
		addOtherProjectAccount.setProjectId(project.getId());
		addOtherProjectAccount.setCategory(listCategory.get(rand.nextInt(4)));
		list.add(addOtherProjectAccount);

		return list;
	}

	private List<ProjectImage> getNewImage() {
		List<ProjectImage> list = new ArrayList<ProjectImage>();
		ProjectImage projectImage = new ProjectImage();
		projectImage.setComment("メモ_test");
		projectImage.setDivision("PROJECT");
		projectImage.setType("JPG");
		projectImage.setId(Long.valueOf(0));
		projectImage.setIsDefaultImage(false);
		projectImage.setDisplayName("apita.jpg");
		Path path = Paths.get("./_test", "apita.jpg");
		if (Files.exists(path)) {
			try {
				InputStream is = Files.newInputStream(path);
				MultipartFile file = new MockMultipartFile("apita.jpg", "apita.jpg", null, is);
				projectImage.setFile(file);
			} catch (IOException e) {
				e.printStackTrace();
				fail();
			}
		}

		list.add(projectImage);
		return list;
	}

	private List<ProjectImage> updateImage() {
		List<ProjectImage> list = new ProjectImageRepository().findByProjectId(project.getId());
		list.get(0).setComment("メモ_update");
		list.get(0).setDivision("PROJECT");
		list.get(0).setType("JPG");
		list.get(0).setIsDefaultImage(false);
		list.get(0).setDisplayName("apita-nagatuda.jpg");
		Path path = Paths.get("./_test", "apita-nagatuda.jpg");
		if (Files.exists(path)) {
			try {
				InputStream is = Files.newInputStream(path);
				MultipartFile file = new MockMultipartFile("apita-nagatuda.jpg", "apita-nagatuda.jpg", null, is);
				list.get(0).setFile(file);
			} catch (IOException e) {
				e.printStackTrace();
				fail();
			}
		}

		return list;
	}

	private List<ProjectFile> getNewFile() {
		List<ProjectFile> list = new ArrayList<ProjectFile>();
		ProjectFile projectFile = new ProjectFile();
		projectFile.setComment("メモ_test");
		projectFile.setDivision("PROJECT");
		projectFile.setType("PDF");
		projectFile.setId(Long.valueOf(0));
		projectFile.setDisplayName("sample.pdf");
		;
		Path path = Paths.get("./_test", "sample.pdf");
		if (Files.exists(path)) {
			try {
				InputStream is = Files.newInputStream(path);
				MultipartFile file = new MockMultipartFile("sample.pdf", "sample.pdf", null, is);
				projectFile.setFile(file);
			} catch (IOException e) {
				e.printStackTrace();
				fail();
			}
		}

		list.add(projectFile);
		return list;
	}

	private List<ProjectFile> updateFile() {
		List<ProjectFile> list = new ProjectFileRepository().findByProjectId(project.getId());
		list.get(0).setComment("メモ_update");
		list.get(0).setDivision("BUILDING");
		list.get(0).setDisplayName("sample-update.pdf");
		Path path = Paths.get("./_test", "sample-update.pdf");
		if (Files.exists(path)) {
			try {
				InputStream is = Files.newInputStream(path);
				MultipartFile file = new MockMultipartFile("sample-update.pdf", "sample-update.pdf", null, is);
				list.get(0).setFile(file);
			} catch (IOException e) {
				e.printStackTrace();
				fail();
			}
		}

		return list;
	}

	private List<ProjectScheduleDTO> getNewProjectSchedule() {
		List<ProjectScheduleDTO> list = new ArrayList<ProjectScheduleDTO>();
		ProjectScheduleDTO projectScheduleDTO = new ProjectScheduleDTO();
		LocalDate now = LocalDate.now();
		projectScheduleDTO.setScheduleDate(now);
		list.add(projectScheduleDTO);
		return list;
	}

	private List<ProjectScheduleDTO> updateProjectSchedule() {
		List<ProjectScheduleDTO> list = new ArrayList<ProjectScheduleDTO>();
		ProjectSchedule projectSchedule = new ProjectScheduleRepository().getHead().get();
		ProjectScheduleDTO projectScheduleDto = new ProjectScheduleDTO(projectSchedule);
		LocalDate updateDate = LocalDate.of(2019, 9, 1);
		projectScheduleDto.setScheduleDate(updateDate);
		list.add(projectScheduleDto);
		return list;
	}

	public void testCreateProjectSchedule() {
		ProjectCreateDTO dto = getProjectDTO();
		Project project = new ProjectService().createAll(dto, account);
	}

	public void testCreateProjectForm() {
		try {

			assertNotEquals(Long.valueOf(0), project.getId());
			Optional<Project> projectOption = new ProjectRepository().findById(project.getId());

			Project created = projectOption.get();

			assertTrue(created.getId() > 0);
			// assertEquals(created.getName(), project.getName());
			//
			// assertEquals(created.getiAreaId(), project.getiAreaId());
			// assertEquals(created.getiBlockId(), project.getiBlockId());
			// assertEquals(created.getiBusinessCardId(), project.getiBusinessCardId());

		} catch (Exception e) {
			fail();
			System.out.println(e.getMessage());
		}
	}

	public void testCreateProjectImage() {
		ProjectImage imageTest = dto.getProjectImageDto().get(0);

		List<ProjectImage> listImageCreated = new ProjectImageRepository().findByProjectId(project.getId());

		ProjectImage imageCreated = listImageCreated.get(0);

		assertEquals(listImageCreated.size(), dto.getProjectImageDto().size());
		assertEquals(imageCreated.getDivision(), imageTest.getDivision());
		assertEquals(imageCreated.getDisplayName(), imageTest.getDisplayName());
		assertEquals(imageCreated.getComment(), imageTest.getComment());
		assertEquals(imageCreated.getType(), imageTest.getType());
	}

	public void testCreateProjectFile() {
		ProjectFile fileTest = dto.getProjectFileDto().get(0);

		List<ProjectFile> listFileCreated = new ProjectFileRepository().findByProjectId(project.getId());
		ProjectFile fileCreated = listFileCreated.get(0);

		assertEquals(listFileCreated.size(), dto.getProjectFileDto().size());
		assertEquals(fileCreated.getDivision(), fileTest.getDivision());
		assertEquals(fileCreated.getDisplayName(), fileTest.getDisplayName());
		assertEquals(fileCreated.getComment(), fileTest.getComment());
		assertEquals(fileCreated.getType(), fileTest.getType());
	}

	public void testCreateProjectPersonalDevelop() {
		// List<Long> listAccountIdTest =
		// dto.getProjectPersonalDevelopDto().stream().map(lb -> lb.getAccountId())
		// .collect(Collectors.toList());
		// List<String> listCategoryTest =
		// dto.getProjectPersonalDevelopDto().stream().map(lb -> lb.getCategory())
		// .collect(Collectors.toList());
		//
		// List<ProjectPersonalDevelop> listProjectPersonalDevelopCreated = new
		// ProjectPersonalDevelopRepository()
		// .findByProjectId(project.getId());
		// List<Long> listId = listProjectPersonalDevelopCreated.stream().map(lb ->
		// lb.getAccountId())
		// .collect(Collectors.toList());
		// List<String> listCategoryId =
		// listProjectPersonalDevelopCreated.stream().map(lb -> lb.getCategory())
		// .collect(Collectors.toList());
		//
		// assertEquals(listProjectPersonalDevelopCreated.size(),
		// dto.getProjectPersonalDevelopDto().size());
		// assertArrayEquals(listAccountIdTest.toArray(), listId.toArray());
		// assertArrayEquals(listCategoryTest.toArray(), listCategoryId.toArray());
	}

	public void testCreateProjectTask() {
		// List<Long> listAccountIdTest = dto.getProjectTaskDto().stream().map(lb ->
		// lb.getAccountId()).collect(Collectors.toList());
		// List<String> listCommentTest = dto.getProjectTaskDto().stream().map(lb ->
		// lb.getComment()).collect(Collectors.toList());
		//
		// List<ProjectTask> listProjectTaskCreated = new
		// ProjectTaskRepository().findByProjectId(project.getId());
		// List<Long> listId = listProjectTaskCreated.stream().map(lb ->
		// lb.getAccountId()).collect(Collectors.toList());
		// List<String> listComment = listProjectTaskCreated.stream().map(lb ->
		// lb.getComment()).collect(Collectors.toList());
		//
		// assertEquals(listProjectTaskCreated.size(), dto.getProjectTaskDto().size());
		// assertArrayEquals(listAccountIdTest.toArray(), listId.toArray());
		// assertArrayEquals(listCommentTest.toArray(), listComment.toArray());
	}

	@Test
	public void testUpdateProject() {
		update = updateProjectFormDTO();

		new ProjectService().updateAll(update, account);

		project = new ProjectRepository().findById(project.getId()).get();

		testUpdateProjectForm();
		testUpdateProjectTask();
		testUpdateProjectImage();
		testUpdateProjectFile();
		testUpdateOtherProjectAccount();
		testUpdateProjectSchedule(project);
	}

	private void testUpdateProjectSchedule(Project project) {
		List<ProjectSchedule> list = new ProjectScheduleRepository().findByProjectId(project.getId());
		list.forEach(dto -> {
			LocalDate date = LocalDate.of(2019, 9, 1);
			assertEquals(date, dto.getScheduleDate());
		});
	}

	private void testUpdateProjectForm() {
		try {

			assertNotEquals(Long.valueOf(0), project.getId());
			Optional<Project> projectOption = new ProjectRepository().findById(project.getId());

			Project Updated = projectOption.get();

			assertTrue(Updated.getId() > 0);
			// assertEquals(Updated.getName(), project.getName());
			//
			// assertEquals(Updated.getiAreaId(), project.getiAreaId());
			// assertEquals(Updated.getiBlockId(), project.getiBlockId());
			// assertEquals(Updated.getiBusinessCardId(), project.getiBusinessCardId());

		} catch (Exception e) {
			fail();
			System.out.println(e.getMessage());
		}
	}

	private void testUpdateProjectImage() {
		// List<ProjectImage> listImageTest = update.getProjectImageDto();
		//
		// List<ProjectImage> listImageUpdated = new
		// ProjectImageRepository().findByProjectId(project.getId());
		// int index = 0;
		// assertEquals(listImageUpdated.size(), listImageTest.size());
		// for (ProjectImage pi : listImageTest) {
		// assertEquals(listImageUpdated.get(index).getDivision(), pi.getDivision());
		// assertEquals(listImageUpdated.get(index).getDisplayName(),
		// pi.getDisplayName());
		// assertEquals(listImageUpdated.get(index).getComment(), pi.getComment());
		// assertEquals(listImageUpdated.get(index).getType(), pi.getType());
		// index++;
		// }

	}

	private void testUpdateProjectFile() {
		// List<ProjectFile> listFileTest = update.getProjectFileDto();
		//
		// List<ProjectFile> listFileUpdated = new
		// ProjectFileRepository().findByProjectId(project.getId());
		// int index = 0;
		//
		// assertEquals(listFileUpdated.size(), listFileTest.size());
		// for (ProjectFile pf : listFileTest) {
		// assertEquals(listFileTest.get(index).getDivision(), pf.getDivision());
		// assertEquals(listFileTest.get(index).getDisplayName(), pf.getDisplayName());
		// assertEquals(listFileTest.get(index).getComment(), pf.getComment());
		// assertEquals(listFileTest.get(index).getType(), pf.getType());
		// index++;
		// }

	}

	private void testUpdateOtherProjectAccount() {
		// List<Long> listAccountIdTest =
		// update.getOtherProjectAccountDto().stream().map(lb -> lb.getiAccountId())
		// .collect(Collectors.toList());
		// List<String> listCategoryTest =
		// update.getOtherProjectAccountDto().stream().map(lb -> lb.getCategory())
		// .collect(Collectors.toList());
		//
		// List<OtherProjectAccount> listOtherProjectAccountUpdated = new
		// OtherProjectAccountRepository()
		// .findByProjectId(project.getId());
		// List<Long> listId = listOtherProjectAccountUpdated.stream().map(lb ->
		// lb.getiAccountId())
		// .collect(Collectors.toList());
		// List<String> listCategoryId = listOtherProjectAccountUpdated.stream().map(lb
		// -> lb.getCategory())
		// .collect(Collectors.toList());
		//
		// assertEquals(listOtherProjectAccountUpdated.size(),
		// update.getOtherProjectAccountDto().size());
		// assertArrayEquals(listAccountIdTest.toArray(), listId.toArray());
		// assertArrayEquals(listCategoryTest.toArray(), listCategoryId.toArray());
	}

	private void testUpdateProjectTask() {
		// List<Long> listAccountIdTest = update.getProjectTaskDto().stream().map(lb ->
		// lb.getAccountId()).collect(Collectors.toList());
		// List<String> listCommentTest = update.getProjectTaskDto().stream().map(lb ->
		// lb.getComment()).collect(Collectors.toList());
		//
		// List<ProjectTask> listProjectTaskUpdated = new
		// ProjectTaskRepository().findByProjectId(project.getId());
		// List<Long> listId = listProjectTaskUpdated.stream().map(lb ->
		// lb.getAccountId()).collect(Collectors.toList());
		// List<String> listComment = listProjectTaskUpdated.stream().map(lb ->
		// lb.getComment()).collect(Collectors.toList());
		//
		// assertEquals(listProjectTaskUpdated.size(),
		// update.getProjectTaskDto().size());
		// assertArrayEquals(listAccountIdTest.toArray(), listId.toArray());
		// assertArrayEquals(listCommentTest.toArray(), listComment.toArray());
	}

}
