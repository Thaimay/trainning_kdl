package jp.co.world.storedevelopment.model.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.IRentContract;
import jp.co.world.storedevelopment.model.IShop;
import jp.co.world.storedevelopment.model.Mail;
import jp.co.world.storedevelopment.model.OtherProjectAccount;
import jp.co.world.storedevelopment.model.OtherProjectTeam;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.ProjectContractProgress;
import jp.co.world.storedevelopment.model.ProjectFile;
import jp.co.world.storedevelopment.model.ProjectImage;
import jp.co.world.storedevelopment.model.ProjectOpinion;
import jp.co.world.storedevelopment.model.ProjectPlan;
import jp.co.world.storedevelopment.model.ProjectSchedule;
import jp.co.world.storedevelopment.model.ProjectSectionProgress;
import jp.co.world.storedevelopment.model.ProjectTask;
import jp.co.world.storedevelopment.model.ProjectTaskAccount;
import jp.co.world.storedevelopment.model.ProjectVideo;
import jp.co.world.storedevelopment.model.Shop;
import jp.co.world.storedevelopment.model.mapper.repository.IRentContractRepository;
import jp.co.world.storedevelopment.model.mapper.repository.OtherProjectAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.OtherProjectTeamRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectContractProgressRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectFileRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectHistoryRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectImageRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectOpinionRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectPlanRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectScheduleRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectSectionProgressRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectTaskAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectTaskRepository;
import jp.co.world.storedevelopment.model.value.FileExtention;
import jp.co.world.storedevelopment.sp.controller.dto.ActionStatusFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectCreateDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectMediaDocumentDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectUpdateDTO;

public class ProjectService {
	private ProjectHistoryRepository projectHistoryRepository = new ProjectHistoryRepository();

	public Project createAll(ProjectCreateDTO dto, Account a) {
		Project p = createProject(dto, a);
		p.setIgnoreFields(new String[] { "implementationDateValue", "currentISalesAgencyTargetId", "progressISalesAgencyTargetId" });
		ProjectSectionProgress psp = new ProjectSectionProgress();
		if(dto.getProjectSectionProgressDto() != null) {
			Iterator<ProjectSectionProgress> iter = dto.getProjectSectionProgressDto().iterator();
			while (iter.hasNext()) {
				ProjectSectionProgress temp = iter.next();
				if (StringUtils.equals(temp.getCategory(), "NEGOTIATION")) {
					if (temp.getFloor() != null) {
						temp.setFloor(temp.getFloor() + "Ｆ");
					}
					psp = temp;
				}
			}
		}

		createRelatedOtherProjectTeams(p, dto.getOtherProjectTeamDto(), a);
		createRelatedOtherProjectAccounts(p, dto.getOtherProjectAccountDto(), a);
		createRelatedProjectTasks(p, dto.getProjectTaskDto(), a);
		createRelatedProjectSectionProgress(p, dto.getProjectSectionProgressDto(), a);
		createRelatedProjectContractProgress(p, dto.getProjectContractProgressDto(), a);
		createAllRelatedFile(p, dto.getMultipartFiles(), a, dto);
		createAllRelatedOpinion(p, dto.getProjectOpinionDto(), a);
		createAllRelatedProjectSchedule(p, dto, a);
		createRelatedProjectPlan(p, dto.getProjectPlanDto(), a);
		createCopyShop(p, psp);
		updateConference(p);
		return p;
	}

	private Project createProject(ProjectCreateDTO dto, Account a) {
		Project p = dto.toModel();

		if(p.getSalesPrediction() != null) {
			p.setSalesPrediction(p.getSalesPrediction() * 1000);
		}
		if(p.getProfitExpectation() != null) {
			p.setProfitExpectation(p.getProfitExpectation() * 1000);
		}
		p.setCreateAccount(a);
		if (dto.getProjectSectionProgressDto() != null) {
			dto.getProjectSectionProgressDto().forEach(projectSectionProgress -> {
				p.registerTsubo(projectSectionProgress);
				p.registerSection(projectSectionProgress);
			});
		}
		return p.create();
	}

	public void createCopyShop(Project model, ProjectSectionProgress dto) {
		if (model.getiShopId() != null) {
			Optional<IShop> shop = model.iShop();

			if (model.isWorkEnd() && shop.isPresent() && model.isCategoryComplete()) {
				updateShopFromProject(model, dto, shop.get());
			}
		}
	}

	public void updateCopyShop(Project project, ProjectSectionProgress dto) {
		Optional<IShop> shop = project.iShop();

		if (shop.isPresent() && project.isCategoryComplete() && project.isWorkEnd()) {
			updateShopFromProject(project, dto, shop.get());
		}
	}

	public void updateFromShop(Project model, IShop master) {
		model.setShopName(master.getShopNameZenkaku());
		model.setShopCd(master.getShopCd());

		updateSectionFromShop(model, master);
		// 交渉中契約情報はいらない？？
		// updateContractFromShop(model, master);

		model.update();
	}

	public void updateShopFromProject(Project model, ProjectSectionProgress sectionProgressDto, IShop iShop) {
		Optional<ProjectSectionProgress> progressOption = model.negotiationSectionProgress();
		Optional<ProjectContractProgress> contractOption = model.negotiationContractProgress();
		Optional<ProjectPlan> plan = model.planProgress();
		Shop shop = iShop.shop().orElseGet(() -> {
			return new Shop(iShop).create();
		});

		if (progressOption.isPresent()) {
			shop.setiSalesAgencyTargetId(model.getCurrentISalesAgencyTargetId());
		}

		contractOption.ifPresent(v -> {
			shop.setRentStartDate(v.getRentStartDate());
			shop.setRentEndDate(v.getRentEndDate());
			shop.setRentYear(v.getRentYear());
		});

		if (plan.isPresent()) {
			shop.setiSalesAgencyTargetId(plan.get().getSalesAgencyTargetId());
			shop.setParticipatingStoreCorporationId(plan.get().getParticipatingStoreCorporationId());
		}

		if (sectionProgressDto.getBuildingExpectedValue() != null) {
			shop.setBusinessHours(sectionProgressDto.getBusinessHours());
			shop.setBuildingExpectedValue(sectionProgressDto.getBuildingExpectedValue().intValue());
		}

		shop.update();
	}

	public void updateFromBuilding(Project model, Building building) {
		model.setCorporationId(building.getiCorporationId());
		model.setiAreaId(building.getiAreaId());
		model.setiBlockId(building.getiBlockId());
		model.setiSalesChannelId(building.getiSalesChannelId());
		model.setiPrefecturesId(building.getiPrefecturesId());
		model.setCorporationGpId(building.corporationGroupId());
		model.update();
	}

	/*
	private void updateContractFromShop(Project project, IShop master) {
		Optional<ProjectContractProgress> contract = project.negotiationContractProgress();
		Optional<IRentContract> rent = master.contract();

		if (contract.isPresent()) {
			ProjectContractProgress target = contract.get();

			if (rent.isPresent()) {
				IRentContract dest = rent.get();
				target.setContractTargetName(dest.getContractTargetName());
				target.setContractStartDate(dest.startDateValue());
				target.setContractEndDate(dest.endDateValue());
				target.setContractNumberOfYear(dest.getContractPeriod());
			}

			target.update();
		}
	}
	*/

	private void updateSectionFromShop(Project project, IShop master) {
		Optional<Shop> shopOption = master.shop();
		Optional<ProjectSectionProgress> sectionOption = project.currentSectionProgress();
		Optional<ProjectPlan> planOption = project.planCurrent();
		Optional<ProjectContractProgress> contract = project.currentContractProgress();

		contract.ifPresent(c -> {
			IRentContract rent = new IRentContractRepository().findRentContractRefShop(master.getShopId());
			if (rent != null) {
				c.setForm(rent.contractType());
				c.setContractTargetName(rent.getContractTargetName());
				c.setContractStartDate(rent.startDateValue());
				c.setContractEndDate(rent.endDateValue());
				
				Float year = new Float(Optional.ofNullable(rent.getContractPeriod()).orElseGet(() -> {
					return 0;
				}));
				c.setContractNumberOfYear(year);
				c.setAutoUpdate(rent.autoUpdateFlagBoolean());
			}

			shopOption.ifPresent(s -> {
				c.setRentStartDate(s.getRentStartDate());
				c.setRentEndDate(s.getRentEndDate());
				c.setRentYear(s.getRentYear());
			});
			c.update();
		});

		sectionOption.ifPresent(section -> {
			section.setFloor(master.getFloorNum());
			section.setContractTsubo(master.getContractTsubo());

			shopOption.ifPresent(shop -> {
				section.setSection(shop.getSection());
				section.setFrontage(shop.getFrontage());
				section.setBusinessHours(shop.getBusinessHours());
				if (shop.getBuildingExpectedValue() != null) {
					section.setBuildingExpectedValue(shop.getBuildingExpectedValue().longValue());
				}
			});
			section.update();
		});

		planOption.ifPresent(plan -> {
			shopOption.ifPresent(shop -> {
				plan.setSalesAgencyTargetId(shop.getiSalesAgencyTargetId());
				plan.setStartDate(shop.getRentStartDate());
				plan.setEndDate(shop.getRentEndDate());
				plan.setParticipatingStoreCorporationId(shop.getParticipatingStoreCorporationId());
			});
			plan.update();
		});
	}

	private void updateConference(Project p) {
		p.createArticleReviewPastConference();
		p.createManagementPastConference();
		p.createInvestmentPastConference();
		
		if (p.getiSalesChannelId() != null) {
			p.registerActionStatus(p.getiSalesChannelId());			
		}
	}

	public Long updateAll(ProjectUpdateDTO dto, Account a) {
		Project p = dto.toModel();
		ProjectSectionProgress psp = new ProjectSectionProgress();
		Iterator<ProjectSectionProgress> iter = dto.getProjectSectionProgressDto().iterator();
		while (iter.hasNext()) {
			ProjectSectionProgress temp = iter.next();
			if (StringUtils.equals(temp.getCategory(), "NEGOTIATION")) {
				if (temp.getFloor() != null) {
					temp.setFloor(temp.getFloor() + "Ｆ");
				}
				psp = temp;
			}
		}

		p.checkDifferenceColumns(dto);

		updateProjectHistory(p);

		updateProject(p, dto, a);

		updateRelatedModels(p, dto, a);

		updateCopyShop(p, psp);

		p.refreshTodo();

		updateConference(p);

		return p.getId();
	}

	public void updateRelatedModels(Project p, ProjectUpdateDTO dto, Account a) {
		// update project_section_progress
		updateRelatedSectionProgress(p, dto.getProjectSectionProgressDto(), a);

		// update project_contract_progress
		updateRelatedContractProgress(p, dto.getProjectContractProgressDto(), a);

		// update project_opinion
		updateRelatedProjectOpinion(p, dto.getProjectOpinionDto(), a);

		// update other_project_team
		updateRelatedOtherProjectTeams(p, dto.getOtherProjectTeamDto(), a);

		// update other_project_account
		updateRelatedOtherProjectAccounts(p, dto.getOtherProjectAccountDtos(), a);

		// update project task
		updateRelatedProjectTasks(p, dto.getProjectTaskDtos(), a);

		// update file
		updateRelatedFiles(p, dto.getMultipartFiles(), a, dto);

		updateRelatedProjectSchedules(p, dto, a);

		updateRelatedProjectPlan(p, dto.getProjectPlanDto(), a);
	}

	private void updateRelatedProjectSchedules(Project p, ProjectUpdateDTO dto, Account a) {
		Map<Long, LocalDate> actionStatusIdMap = new HashMap<Long, LocalDate>();
		List<ProjectSchedule> oldScheduleList = new ProjectScheduleRepository().findByProjectId(p.getId());
		oldScheduleList.forEach(schedule -> {
			Long id = schedule.getMProjectActionStatusId();
			LocalDate date = schedule.getScheduleDate();
			actionStatusIdMap.put(id, date);
		});

		new ProjectScheduleRepository().deleteByProjectId(p.getId());

		dto.getProjectScheduleDto().forEach(s -> {
			Boolean isChangedScheduleDate = false;
			LocalDate oldScheduleDate = actionStatusIdMap.get(s.getActionStatusId());
			if (oldScheduleDate != null) {
				isChangedScheduleDate = !oldScheduleDate.equals(s.getScheduleDate());
			} else {
				isChangedScheduleDate = oldScheduleDate != s.getScheduleDate();
			}
			createProjectSchedule(p, s.getScheduleDate(), s.getActionStatusId(), isChangedScheduleDate, a);
		});
	}

	public void updateRelatedProjectOpinion(Project p, List<ProjectOpinion> dto, Account a) {
		new ProjectOpinionRepository().deleteByProjectId(p.getId());

		dto.forEach(x -> {
			if (x.getFormFile() != null) {
				// delete old file
				if (x.getFileId() != null && x.getFileId() > 0) {
					Optional<ProjectFile> oldFile = new ProjectFileRepository().findById(x.getFileId());
					if (oldFile.isPresent()) {
						oldFile.get().delete();
					}
				}

				if (FileExtention.isImage(x.getFormFile().getOriginalFilename())) {
					ProjectImage pi = new ProjectImage(x.getFormFile(), p, a);
					pi.setDivision("PROJECT");
					pi.setComment(x.getComment());
					pi.setDisplayName(x.getFormFile().getOriginalFilename());

					x.setFileId(pi.create().getId());
				} else if (FileExtention.isDocument(x.getFormFile().getOriginalFilename())) {
					ProjectFile pf = new ProjectFile(x.getFormFile(), p, a);
					pf.setDivision("PROJECT");
					pf.setComment(x.getComment());
					pf.setDisplayName(x.getFormFile().getOriginalFilename());

					x.setFileId(pf.create().getId());
				} else if (FileExtention.isVideo(x.getFormFile().getOriginalFilename())) {
					ProjectVideo pv = new ProjectVideo(x.getFormFile(), p, a);
					pv.setDivision("PROJECT");
					pv.setComment(x.getComment());
					pv.setDisplayName(x.getFormFile().getOriginalFilename());

					x.setFileId(pv.create().getId());
				}
			}
			x.setProjectId(p.getId());
			x.create();
		});
	}

	public void updateProjectOpinion(Project p, ProjectOpinion dto, Account a) {
		if (dto.getFormFile() != null) {
			ProjectFile pf = new ProjectFile(dto.getFormFile(), p, a);
			pf.setDivision("PROJECT");
			pf.setComment(dto.getComment());
			pf.setDisplayName(dto.getFormFile().getOriginalFilename());
			dto.setFileId(pf.create().getId());
		}

		dto.setIgnoreFields(new String[] { "projectId", "category", "formFile", "hasFile", "createdDatetime",
				"createdAccountCode", "isDeleted" });
		dto.setUpdateAccount(a);
		dto.update();
	}

	public void updateRelatedContractProgress(Project p, List<ProjectContractProgress> dto, Account a) {
		new ProjectContractProgressRepository().deleteByProjectId(p.getId());

		createRelatedProjectContractProgress(p, dto, a);
	}

	public void updateRelatedSectionProgress(Project p, List<ProjectSectionProgress> dto, Account a) {
		new ProjectSectionProgressRepository().deleteByProjectId(p.getId());

		createRelatedProjectSectionProgress(p, dto, a);
	}

	public Project reflectActionStatus(Project p, ActionStatusFormDTO dto) {
		if (dto.getiSalesChannelId() != null) {
			p.initProjectActionStatus(p.getProjectCategoryId(), dto.getiSalesChannelId());
		}

		p.registerStartDate(p.getStartDate());
		p.registerArticleReviewResult();
		p.registerManagementResult();
		p.registerInvestmentResult();

		if (dto.getiSalesChannelId() != null) {
			p.registerActionStatus(dto.getiSalesChannelId(), dto.progress());
		}

		return p;
	}

	private void updateProject(Project p, ProjectUpdateDTO dto, Account a) {
		List<String> ignoreFields = new ArrayList<>();
		List<String> defaultIgnoreFields =  Arrays.asList( "division", "actionStatus", "inHouseProgress", "negotiationProgress",
				"contractProgress", "stop", "planStatusId", "planDate", "planPeriod", "planPeriodHalf", "salesExpectation",
				"otherRequest", "ourRequest", "createdDatetime", "createdAccountCode", "isDeleted", "implementationDateValue", "currentISalesAgencyTargetId", "progressISalesAgencyTargetId");
		List<String> strIgnoreField = Arrays.asList("corporationGpId", "corporationId", "iAreaId", "iBlockId", "iSalesChannelId", "iPrefecturesId");

		ignoreFields.addAll(defaultIgnoreFields);
		p.setUpdateAccount(a);
		p.setMProjectActionStatusId(dto.getMProjectActionStatusId());
		if(p.getSalesPrediction() != null) {
			p.setSalesPrediction(p.getSalesPrediction() * 1000);
		}
		if(p.getProfitExpectation() != null) {
			p.setProfitExpectation(p.getProfitExpectation() * 1000);
		}
		dto.getProjectSectionProgressDto().forEach(projectSectionProgress -> {
			p.registerTsubo(projectSectionProgress);
			p.registerSection(projectSectionProgress);
		});
		
		if (p.getBuildingId() == dto.getBuildingId()) {
			ignoreFields.addAll(strIgnoreField);
		}

		p.setIgnoreFields(ignoreFields.stream().toArray(String[]::new));
		p.update();
	}

	private void updateProjectHistory(Project p) {
		projectHistoryRepository.updateProjectHistory(p);
	}

	private void updateRelatedOtherProjectTeams(Project p, List<OtherProjectTeam> lstOtherProjectTeam, Account a) {
		new OtherProjectTeamRepository().deleteByProjectId(p.getId());

		createRelatedOtherProjectTeams(p, lstOtherProjectTeam, a);
	}

	private void updateRelatedOtherProjectAccounts(Project p, List<OtherProjectAccount> lstOtherProjectAccount,
			Account a) {
		new OtherProjectAccountRepository().deleteByProjectId(p.getId());

		createRelatedOtherProjectAccounts(p, lstOtherProjectAccount, a);
	}

	private void createRelatedOtherProjectTeams(Project p, List<OtherProjectTeam> lstOtherProjectTeam, Account a) {
		if (lstOtherProjectTeam != null) {
			lstOtherProjectTeam.forEach(opa -> createOtherProjectAccount(opa, p, a));
		}
	}

	private void createOtherProjectAccount(OtherProjectTeam model, Project p, Account a) {
		model.setProjectId(p.getId());
		model.setCreateAccount(a);
		model.create();
	}

	private void createRelatedOtherProjectAccounts(Project p, List<OtherProjectAccount> lstOtherProjectAccount,
			Account a) {
		if (lstOtherProjectAccount != null) {
			lstOtherProjectAccount.forEach(opa -> createOtherProjectAccount(opa, p, a));
		}
	}

	private void createOtherProjectAccount(OtherProjectAccount opa, Project p, Account a) {
		opa.setProjectId(p.getId());
		opa.setCreateAccount(a);
		opa.create();
	}

	private void createRelatedProjectTasks(Project p, List<ProjectTask> lstProjectTask, Account a) {
		if (lstProjectTask != null) {
			lstProjectTask.forEach(pt -> createProjectTask(p, pt, a));
		}
	}

	private void updateRelatedProjectTasks(Project p, List<ProjectTask> lstProjectTask, Account a) {
		if (lstProjectTask != null) {
			for (ProjectTask pt : lstProjectTask) {
				if (pt.getId() == 0) {
					createProjectTask(p, pt, a);
				} else if (pt.getIsDeleted() == true) {
					List<ProjectTaskAccount> lstPta = new ProjectTaskAccountRepository()
							.findByProjectTaskId(pt.getId());
					if (lstPta.size() > 0) {
						for (ProjectTaskAccount pta : lstPta) {
							pta.delete();
						}
					}
					pt.delete();
				} else {
					updateProjectTask(pt, a);
				}
			}
		}
	}

	private void createProjectTask(Project p, ProjectTask pt, Account a) {
		pt.setProjectId(p.getId());
		pt.setCreateAccount(a);
		ProjectTask pt_new = pt.create();
		if (pt.getAccount() != null && pt.getAccount().size() > 0) {
			pt.getAccount().forEach(acc -> createProjectTaskAccount(acc, pt_new, a));
			taskMailCreate(pt_new, a, pt.getAccount());
		}
	}

	private void taskMailCreate(ProjectTask model, Account account, List<ProjectTaskAccount> list) {
		Mail mail = new Mail();

		String mailAddressList = list.stream().map(a -> {
			return a.account().getMailAddress();
		}).collect(Collectors.joining(","));

		mail.setMailAddress(mailAddressList);
		mail.setProjectTaskCreate(model, account);
		mail.create();
	}

	private void taskMailUpdate(ProjectTask model, Account account, List<ProjectTaskAccount> list) {
		Mail mail = new Mail();

		String mailAddressList = list.stream().map(a -> {
			return a.account().getMailAddress();
		}).collect(Collectors.joining(","));

		mail.setMailAddress(mailAddressList);
		mail.setProjectTaskUpdate(model, account);
		mail.create();
	}

	private void createProjectTaskAccount(ProjectTaskAccount acc, ProjectTask pt_new, Account a) {
		acc.setProjectTaskId(pt_new.getId());
		acc.setAccountId(acc.getAccountId());
		acc.setCreateAccount(a);
		acc.create();
	}

	private void updateProjectTask(ProjectTask pt, Account a) {

		Boolean same = pt.sameOld();
		pt.setIgnoreFields(new String[] { "projectId", "complete", "createdDatetime", "createdAccountCode", "isDeleted",
				"account" });
		pt.setUpdateAccount(a);
		pt.update();
		ProjectTask afterUpdate = new ProjectTaskRepository().findById(pt.getId()).orElseGet(() -> {
			throw new IllegalStateException("更新失敗");
		});

		new ProjectTaskAccountRepository().deleteByProjectTaskId(pt.getId());

		if (pt.getAccount() != null && pt.getAccount().size() > 0) {
			pt.getAccount().forEach(acc -> createProjectTaskAccount(acc, afterUpdate, a));

			if (same == false) {
				taskMailUpdate(afterUpdate, a, pt.getAccount());
			}
		}
	}

	private void createRelatedProjectSectionProgress(Project p, List<ProjectSectionProgress> listProjectSection,
			Account a) {
		if (listProjectSection != null) {
			listProjectSection.forEach(psp -> createProjectSectionProgress(p, psp, a));
		}

	}

	private void createProjectSectionProgress(Project p, ProjectSectionProgress psp, Account a) {
		psp.setProjectId(p.getId());
		psp.setCreateAccount(a);
		psp.create();
	}

	private void createRelatedProjectContractProgress(Project p, List<ProjectContractProgress> listProjectContract,
			Account a) {
		if (listProjectContract != null) {
			listProjectContract.forEach(pcp -> createProjectContractProgress(p, pcp, a));
		}

	}

	private void createProjectContractProgress(Project p, ProjectContractProgress pcp, Account a) {
		pcp.setProjectId(p.getId());
		pcp.setCreateAccount(a);
		pcp.create();
	}

	private void createAllRelatedFile(Project p, List<MultipartFile> files, Account a, ProjectCreateDTO dto) {
		int index = 0;
		if (dto.getProjectMediaDocumentDto() != null) {
			for (ProjectMediaDocumentDTO pmd : dto.getProjectMediaDocumentDto()) {
				if (pmd.getId() > 0 && pmd.getAddFileReference()) {
					Path path = Paths.get(Application.resourcePath() + pmd.getPath());
					if(FileExtention.isDocument(pmd.getOriginFileName())){
						path = Paths.get(Application.resourcePath() + pmd.getUrlPath());
					}
					InputStream is;
					try {
						is = Files.newInputStream(path);
						MultipartFile file = new MockMultipartFile(pmd.getOriginFileName(), pmd.getOriginFileName(),
								null, is);
						pmd.setFile(file);
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
				if (pmd.getId() == 0) {
					pmd.setFile(files.get(index));
					index++;
				}
			}

			dto.getProjectMediaDocumentDto().forEach(pmd -> createProjectRelatedFile(p, pmd, a));
		}
	}

	private void createProjectRelatedFile(Project p, ProjectMediaDocumentDTO dto, Account a) {
		if (FileExtention.isImage(dto.getOriginFileName())) {
			createProjectImage(p, dto, a);
		} else if (FileExtention.isDocument(dto.getOriginFileName())) {
			createProjectFile(p, dto, a);
		} else if (FileExtention.isVideo(dto.getOriginFileName())) {
		}
	}

	private void updateRelatedFiles(Project p, List<MultipartFile> files, Account a, ProjectUpdateDTO dto) {
		int index = 0;
		for (ProjectMediaDocumentDTO pmd : dto.getProjectMediaDocumentDto()) {
			if (pmd.getId() > 0 && pmd.getAddFileReference()) {
				Path path = Paths.get(Application.resourcePath() + pmd.getPath());
				if(FileExtention.isDocument(pmd.getOriginFileName())){
					path = Paths.get(Application.resourcePath() + pmd.getUrlPath());
				}
				InputStream is;
				try {
					is = Files.newInputStream(path);
					MultipartFile file = new MockMultipartFile(pmd.getOriginFileName(), pmd.getOriginFileName(), null,
							is);
					pmd.setFile(file);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			if (pmd.getId() == 0) {
				pmd.setFile(files.get(index));
				index++;
			}
		}

		dto.getProjectMediaDocumentDto().forEach(pmd -> updateProjectRelatedFile(p, pmd, a));
	}

	private void updateProjectRelatedFile(Project p, ProjectMediaDocumentDTO dto, Account a) {
		if (FileExtention.isImage(dto.getOriginFileName())) {
			updateAllProjectImage(p, dto, a);
		} else if (FileExtention.isDocument(dto.getOriginFileName())) {
			updateAllProjectFile(p, dto, a);
		} else if (FileExtention.isVideo(dto.getOriginFileName())) {
		}
	}

	private void updateAllProjectFile(Project p, ProjectMediaDocumentDTO dto, Account a) {
		if (dto.getId().equals(Long.valueOf(0)) || dto.getAddFileReference()) {
			createProjectFile(p, dto, a);
		} else if (dto.getIsDeleted()) {
			ProjectFile pf = new ProjectFile();
			pf.setId(dto.getId());
			pf.delete();
		} else {
			updateProjectFile(p, dto, a);
		}
	}

	private void createProjectFile(Project p, ProjectMediaDocumentDTO dto, Account a) {
		ProjectFile pf = new ProjectFile(dto.getFile(), p, a);
		pf.setDivision(dto.getDivision());
		pf.setComment(dto.getComment());
		pf.setDisplayName(dto.getDisplayName());
		pf.setOutputFlag(dto.getOutputFlag());
		if (dto.getOutputFlag() != null && dto.getOutputFlag().equals("T")) {
			pf.setOutputNumber(dto.getOutputNumber());
		}

		pf.create();

	}

	private void updateProjectFile(Project p, ProjectMediaDocumentDTO dto, Account a) {
		// get file by id
		ProjectFile pfUpdate = new ProjectFileRepository().findById(dto.getId()).orElseGet(() -> {
			throw new IllegalArgumentException("存在しないファイルです");
		});
		pfUpdate.setUpdateAccount(a);
		pfUpdate.setComment(dto.getComment());
		pfUpdate.setDivision(dto.getDivision());
		pfUpdate.setDisplayName(dto.getDisplayName());
		pfUpdate.setOutputFlag(dto.getOutputFlag());
		if (dto.getOutputFlag() != null && dto.getOutputFlag().equals("T")) {
			pfUpdate.setOutputNumber(dto.getOutputNumber());
		}

		pfUpdate.update();
	}

	private void updateAllProjectImage(Project p, ProjectMediaDocumentDTO dto, Account a) {
		if (dto.getId().equals(Long.valueOf(0)) || dto.getAddFileReference()) {
			createProjectImage(p, dto, a);
		} else if (dto.getIsDeleted()) {
			ProjectImage img = new ProjectImage();
			img.setId(dto.getId());
			img.delete();
		} else {
			updateProjectImage(p, dto, a);
		}
	}

	private void createProjectImage(Project p, ProjectMediaDocumentDTO dto, Account a) {
		// create Project image
		ProjectImage image = new ProjectImage(dto.getFile(), p, a);
		image.setComment(dto.getComment());
		image.setDivision(dto.getDivision());
		image.setDisplayName(dto.getDisplayName());
		image.setOutputFlag(dto.getOutputFlag());
		if (dto.getOutputFlag() != null && dto.getOutputFlag().equals("T")) {
			image.setOutputNumber(dto.getOutputNumber());
		}
		image.create();
	}

	private void updateProjectImage(Project p, ProjectMediaDocumentDTO dto, Account a) {
		// update Building image
		ProjectImage piUpdate = new ProjectImageRepository().findById(dto.getId()).orElseGet(() -> {
			throw new IllegalArgumentException("存在しないファイルです");
		});
		piUpdate.setUpdateAccount(a);
		piUpdate.setComment(dto.getComment());
		piUpdate.setDivision(dto.getDivision());
		piUpdate.setDisplayName(dto.getDisplayName());
		piUpdate.setOutputFlag(dto.getOutputFlag());
		if (dto.getOutputFlag() != null && dto.getOutputFlag().equals("T")) {
			piUpdate.setOutputNumber(dto.getOutputNumber());
		}
		piUpdate.update();
	}

	private void createAllRelatedOpinion(Project p, List<ProjectOpinion> projectOpinions, Account a) {
		if (projectOpinions != null) {
			projectOpinions.forEach(po -> createProjectOpinion(p, po, a));
		}
	}

	private void createProjectOpinion(Project p, ProjectOpinion po, Account a) {
		if (po.getFormFile() != null) {
			if (FileExtention.isImage(po.getFormFile().getOriginalFilename())) {
				ProjectImage pi = new ProjectImage(po.getFormFile(), p, a);
				pi.setDivision("PROJECT");
				pi.setComment(po.getComment());
				pi.setDisplayName(po.getFormFile().getOriginalFilename());

				po.setFileId(pi.create().getId());
			} else if (FileExtention.isDocument(po.getFormFile().getOriginalFilename())) {
				ProjectFile pf = new ProjectFile(po.getFormFile(), p, a);
				pf.setDivision("PROJECT");
				pf.setComment(po.getComment());
				pf.setDisplayName(po.getFormFile().getOriginalFilename());

				po.setFileId(pf.create().getId());
			} else if (FileExtention.isVideo(po.getFormFile().getOriginalFilename())) {
				ProjectVideo pv = new ProjectVideo(po.getFormFile(), p, a);
				pv.setDivision("PROJECT");
				pv.setComment(po.getComment());
				pv.setDisplayName(po.getFormFile().getOriginalFilename());

				po.setFileId(pv.create().getId());
			}
		}
		po.setProjectId(p.getId());
		po.create();
	}

	private void createProjectSchedule(Project p, LocalDate date, Long actionStatusId, Boolean isChangedScheduleDate, Account a) {
		ProjectSchedule model = new ProjectSchedule();
		model.setProjectId(p.getId());
		model.setMProjectActionStatusId(actionStatusId);
		model.setScheduleDate(date);
		model.setIsChangedScheduleDate(isChangedScheduleDate);
		model.setCreateAccount(a);
		model.create();
	}

	private void createAllRelatedProjectSchedule(Project p, ProjectCreateDTO dto, Account a) {
		if (dto.getProjectScheduleDto() != null) {
			dto.getProjectScheduleDto().forEach(s -> {
				createProjectSchedule(p, s.getScheduleDate(), s.getActionStatusId(), false, a);
			});
		}
	}

	private void createRelatedProjectPlan(Project p, List<ProjectPlan> projectPlanDto, Account a) {
		if (projectPlanDto != null) {
			projectPlanDto.forEach(pl -> {
				createProjectPlan(p, pl, a);
			});
		}
	}

	private void createProjectPlan(Project p, ProjectPlan pl, Account a) {
		pl.setProjectId(p.getId());
		pl.setCreateAccount(a);
		pl.create();
	}

	private void updateRelatedProjectPlan(Project p, List<ProjectPlan> projectPlanDto, Account a) {
		if (p.getId() != null) {
			new ProjectPlanRepository().deleteByProjectId(p.getId());
			if (projectPlanDto != null) {
				projectPlanDto.forEach(pl -> {
					createProjectPlan(p, pl, a);
				});
			}
		}
	}
}
