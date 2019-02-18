package jp.co.world.storedevelopment.pc.controller;

import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import jp.co.world.storedevelopment.common.controller.CommonProjectController;
import jp.co.world.storedevelopment.model.FileDivision;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.ProjectHistory;
import jp.co.world.storedevelopment.model.ProjectOpinion;
import jp.co.world.storedevelopment.model.ProjectTask;
import jp.co.world.storedevelopment.model.mapper.repository.FileDivisionRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectClassificationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectHistoryRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectTaskRepository;
import jp.co.world.storedevelopment.model.service.AccountDataService;
import jp.co.world.storedevelopment.model.service.ProjectService;
import jp.co.world.storedevelopment.pc.controller.form.ProjectFindForm;
import jp.co.world.storedevelopment.pc.controller.view.model.ProjectCreateViewModel;
import jp.co.world.storedevelopment.pc.controller.view.model.ProjectDetailViewModel;
import jp.co.world.storedevelopment.pc.controller.view.model.ProjectHistoryViewModel;
import jp.co.world.storedevelopment.pc.controller.view.model.ProjectViewModel;
import jp.co.world.storedevelopment.sp.controller.dto.AccountListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.PeriodSearchListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectCreateDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectHistoryListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectUpdateDTO;

@Controller
@RequestMapping("/pc/project")
public class ProjectController extends CommonProjectController {
	private static final String BASE_DIR = "pc/project/";
	private ProjectService projectService = new ProjectService();
	private ProjectRepository projectRepository = new ProjectRepository();
	private ProjectHistoryRepository projectHistoryRepository = new ProjectHistoryRepository();

	private String detailView(Model model, ProjectDetailViewModel dto) {
		model.addAttribute("project", dto);
		List<ProjectHistoryListDTO> historyList = getProjectHistoryList(dto.getId());
		model.addAttribute("historyList", historyList);
		return BASE_DIR + "detail";
	}

	private String listView(Model model, ProjectFindForm dto) {
		findKeyword(model, dto);
		model.addAttribute("landingPossibilityClassification", findProjectLandingClassification());
		model.addAttribute("lstOtherProjectTeam", findMStoreDevelopTeam());
		model.addAttribute("lstSalesChannel", findISaleschannel());
		model.addAttribute("projectClassificationProgressCompany", findProjectClassificationProgressCompany());
		model.addAttribute("projectClassificationProgressNegotiation", findProjectClassificationProgressNegotiation());

		model.addAttribute("projectPlanClassification", findProjectPlanClassification());
		model.addAttribute("locality", findLocality());
		model.addAttribute("listPlanPeriod", getListPlanPeriodSearch());
		model.addAttribute("listProjectCategory", findProjectCategory());
		model.addAttribute("listImplementationPeriod", getCurrentImplementationPeriod());
		model.addAttribute("listExpirationPeriod", getListExpirationPeriodSearch());
		model.addAttribute("listStoreDevelopTeam", findMStoreDevelopTeam());
		model.addAttribute("listIShopCompany", findIShopCompanySearchList());
		model.addAttribute("listMShopType", findMShopType());

		return BASE_DIR + "list";
	}

	private void findKeyword(Model model, ProjectFindForm dto) {
		List<ProjectViewModel> projectKeywords = new ArrayList<ProjectViewModel>();
		ProjectFindForm findKeywordDTO = ProjectFindForm.EMPTY;
		int listKeywordCount = 0;
		int pageKeywordCount = 0;

		if (dto != null) {
			dto.setAccountId(getAccount().getId());
			findKeywordDTO = dto;
			projectKeywords = findForPC(findKeywordDTO);
			listKeywordCount = getCountFindForPC(findKeywordDTO);
			pageKeywordCount = getPageCount(listKeywordCount, findKeywordDTO.getPagingSize());
		}

		model.addAttribute("findKeywordDTO", findKeywordDTO);
		model.addAttribute("projectKeywords", projectKeywords);
		model.addAttribute("countKeyword", NumberFormat.getNumberInstance(Locale.US).format(listKeywordCount));
		model.addAttribute("pageCountKeyword", pageKeywordCount);
	}

	@RequestMapping("/list")
	public String list(Model model) {
		try {
			logStartMethod("list");
			ProjectFindForm dto = ProjectFindForm.EMPTY;

			if (getAccount().hasDataUser()) {
				String json = getAccount().fetchAccountData().get().getProjectConditionPC();
				if (!json.isEmpty()) {
					dto = ProjectFindForm.toDTO(json);
					removePastPeriodFromDTO(dto);
					dto.defaultIsPrint();
				}
			}

			String list = listView(model, dto);
			logEndMethod("list");
			return list;
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			logException("list", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find")
	public String find(Model model, @Valid ProjectFindForm dto) {
		try {
			logStartMethod("find");
			String find = listView(model, dto);

			// set condition to data user
			if (dto.getIsDefaultSearch()) {
				new AccountDataService().updateProjectConditionPC(getAccount(), dto.defaultCondition());

				removePastPeriodFromDTO(dto);
			}
			logEndMethod("find");
			return find;
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			logException("find", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id, Model model) {
		try {
			logStartMethod("detail");

			ProjectDetailViewModel dto = getDetail(id);
			String detailView = detailView(model, dto);

			logEndMethod("detail");
			return detailView;
		} catch (Exception ex) {
			logException("detail", ex.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/showUpdate/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String showUpdate(Model model, @PathVariable Long id) {
		try {
			logStartMethod("showUpdate");

			ProjectDetailViewModel dto = getDetail(id);
			model.addAttribute("project", dto);
			model.addAttribute("projectCategoryList", findProjectCategory());
			model.addAttribute("suspensionClassificationList",
					findSuspensionClassification(dto.getProjectCategoryId()));
			model.addAttribute("landingClassificationList", findLandingClassification(dto.getProjectCategoryId()));
			model.addAttribute("landingPossibilityClassificationList",
					findLandingPossibilityClassification(dto.getProjectCategoryId()));
			model.addAttribute("companyProjectProgressList", findCompanyProjectProgress(dto.getProjectCategoryId()));
			model.addAttribute("negotiationProjectProgressList",
					findNegotiationProjectProgress(dto.getProjectCategoryId()));
			model.addAttribute("fileDivision", findFileDivision());
			model.addAttribute("findISaleschannel", findISaleschannel());
			model.addAttribute("projectMeetingResultList", findMProjectMeetingResult());
			model.addAttribute("lstOtherProjectTeam", findMStoreDevelopTeam());
			model.addAttribute("projectRequestorReason",
					new ProjectClassificationRepository().getRequestorReasonClassification());
			model.addAttribute("listMShopType", findMShopType());

			logEndMethod("showUpdate");
			return BASE_DIR + "edit";
		} catch (Exception ex) {
			logException("showUpdate", ex.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/update")
	@ResponseBody
	public String update(Model model, @Valid ProjectUpdateDTO dto, @Valid List<MultipartFile> files,
			@Valid List<MultipartFile> opinionFiles) {
		try {
			logStartMethod("update");
			refactorObject(dto);
			int fileOpinionIndex = 0;
			for (Field field : dto.getClass().getDeclaredFields()) {
				logger.info(field.getType().getSimpleName());
			}
			for (ProjectOpinion po : dto.getProjectOpinionDto()) {
				if (po.getHasFile() == true && opinionFiles.size() > fileOpinionIndex) {
					po.setFormFile(opinionFiles.get(fileOpinionIndex));
					fileOpinionIndex++;
				}
			}
			dto.setMultipartFiles(files);
			projectService.updateAll(dto, this.getAccount());

			String update = dto.getId().toString();
			logEndMethod("update");

			return update;
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			logException("update", ex.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/showCreate")
	@ResponseStatus(HttpStatus.OK)
	public String showCreate(Model model) {
		try {
			logStartMethod("showCreate");
			model.addAttribute("projectCreate", new ProjectCreateViewModel());
			List<FileDivision> listDto = new FileDivisionRepository().findAll();
			model.addAttribute("projectFileDivision", listDto);
			model.addAttribute("projectMeetingResultList", findMProjectMeetingResult());
			model.addAttribute("lstOtherProjectTeam", findMStoreDevelopTeam());

			Long categoryId = 0L;
			if (findProjectCategory().size() > 0) {
				categoryId = findProjectCategory().get(0).getId();
			}
			model.addAttribute("suspensionClassificationList", findSuspensionClassification(categoryId));
			model.addAttribute("landingClassificationList", findLandingClassification(categoryId));
			model.addAttribute("landingPossibilityClassificationList",
					findLandingPossibilityClassification(categoryId));
			model.addAttribute("projectRequestorReason",
					new ProjectClassificationRepository().getRequestorReasonClassification());
			model.addAttribute("projectProgressCompany", findCompanyProjectProgress(categoryId));
			model.addAttribute("projectProgressNegotiation", findNegotiationProjectProgress(categoryId));
			model.addAttribute("listMShopType", findMShopType());
			
			AccountListDTO account= new AccountListDTO();
			account.setFullName(this.getAccount().getFullName());
			account.setId(this.getAccount().getId());
			
			model.addAttribute("userInfo", account);
			
			logEndMethod("showCreate");
			return BASE_DIR + "create";
		} catch (Exception ex) {
			logException("showCreate", ex.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/create")
	@ResponseBody
	public String create(Model model, @Valid ProjectCreateDTO dto, @Valid List<MultipartFile> docs,
			@Valid List<MultipartFile> opinionFiles) {
		try {
			logStartMethod("create");
			refactorObject(dto);
			dto.setMultipartFiles(docs);

			if (dto.getProjectOpinionDto() != null) {
				int fileIndex = 0;

				for (ProjectOpinion po : dto.getProjectOpinionDto()) {
					if (fileIndex <= opinionFiles.size()) {
						if (po.getHasFile() != null && po.getHasFile()) {
							po.setFormFile(opinionFiles.get(fileIndex));
							fileIndex++;
						}
					}
				}
			}

			Project p = projectService.createAll(dto, this.getAccount());
			logEndMethod("create");
			return p.getId().toString();
		} catch (Exception ex) {
			logException("create", ex.getMessage());
			return null;
		}
	}

	public List<ProjectViewModel> findForPC(ProjectFindForm dto) {
		dto.setAccount(getAccount());
		List<ProjectViewModel> viewModel = ProjectViewModel.toList(projectRepository.findForPC(dto), getAccount());
		return viewModel;
	}

	public int getCountFindForPC(ProjectFindForm dto) {
		dto.setAccount(getAccount());
		return projectRepository.getCountFindForPC(dto);
	}

	public int getPageCount(int count, int pagingSize) {
		int pageCount = (int) Math.ceil(Double.valueOf(count) / Double.valueOf(pagingSize)) -1;
		return pageCount > 0 ? pageCount : 0;
	}

	public ProjectDetailViewModel getDetail(long id) {
		Project project = projectRepository.findById(id).orElseThrow(() -> {
			throw new IllegalArgumentException("存在しないIDです");
		});
		return new ProjectDetailViewModel(project, getAccount());
	}

	@RequestMapping(value = "/projectTask/complete/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void projectTaskComplete(@PathVariable Long id) {
		try {
			logStartMethod("projectTaskComplete", id);
			ProjectTask model = new ProjectTaskRepository().findById(id).orElseGet(() -> {
				throw new IllegalStateException();
			});
			model.complete();
			logEndMethod("projectTaskComplete");
		} catch (Exception ex) {
			logException("projectTaskComplete", ex.getMessage());
		}
	}

	private List<ProjectHistoryListDTO> getProjectHistoryList(Long projectId) {
		try {
			logStartMethod("getProjectHistoryList");
			List<ProjectHistoryListDTO> historyList = null;
			Optional<Project> project = projectRepository.findById(projectId);
			if (project.isPresent()) {
				historyList = projectHistoryRepository.getProjectHistoryList(project.get().getId());
			}

			logEndMethod("getProjectHistoryList");
			return historyList;
		} catch (Exception ex) {
			logException("getProjectHistoryList", ex.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/revision/{id}", method = RequestMethod.POST)
	public String revision(@PathVariable Long id, @Valid Long idHistory, Model model) {
		try {
			logStartMethod("revision");
			String revisionView = "";

			if (idHistory > 0) {
				ProjectHistory history = null;
				List<ProjectHistoryListDTO> historyList = getProjectHistoryList(id);

				if (historyList != null && historyList.size() > 0 && idHistory != null && idHistory > 0) {
					history = projectHistoryRepository.findById(idHistory).get();
				}
				revisionView = revisionView(model, id, historyList, history);
			} else {
				revisionView = "redirect:../detail/" + id;
			}
			logEndMethod("revision");
			return revisionView;
		} catch (Exception ex) {
			logException("revision", ex.getMessage());
			return null;
		}
	}

	private String revisionView(Model model, Long projectId, List<ProjectHistoryListDTO> historyList,
			ProjectHistory history) {
		model.addAttribute("project", new ProjectHistoryViewModel(history, getAccount()));
		model.addAttribute("historyList", historyList);
		return BASE_DIR + "revision";
	}

	private List<PeriodSearchListDTO> getListPlanPeriodSearch() {
		List<PeriodSearchListDTO> listDTO = new ArrayList<>();
		Integer presentPeriod = getPresentPeriod();
		if (presentPeriod != null) {
			for (int i = presentPeriod; i < presentPeriod + 2; i++) {
				listDTO.add(new PeriodSearchListDTO(i, PeriodSearchListDTO.UP));
				listDTO.add(new PeriodSearchListDTO(i, PeriodSearchListDTO.DOWN));
			}
		}
		return listDTO;
	}
	
	private List<PeriodSearchListDTO> getListExpirationPeriodSearch() {
		List<PeriodSearchListDTO> listDTO = new ArrayList<>();
		Integer presentPeriod = getPresentPeriod();
		if (presentPeriod != null) {
			for (int i = presentPeriod; i < presentPeriod + 5; i++) {
				listDTO.add(new PeriodSearchListDTO(i, PeriodSearchListDTO.UP));
				listDTO.add(new PeriodSearchListDTO(i, PeriodSearchListDTO.DOWN));
			}
		}
		return listDTO;
	}

	private void removePastPeriodFromDTO(ProjectFindForm dto) {
		Integer presentPeriod = getPresentPeriod();

		// remove past plan period
		List<String> filterPlan = new ArrayList<>();
		dto.getPlanPeriodUpDowns().forEach(x -> {
			String planPeriod = x.replace(PeriodSearchListDTO.UP, StringUtils.EMPTY).replace(PeriodSearchListDTO.DOWN,
					StringUtils.EMPTY);
			if (StringUtils.isNumeric(planPeriod) && Integer.valueOf(planPeriod) >= presentPeriod) {
				filterPlan.add(x);
			}
		});
		dto.setPlanPeriodUpDowns(filterPlan);

		// remove past implementation period
		List<String> filterImplementation = new ArrayList<>();
		dto.getImplementationPeriodUpDowns().forEach(x -> {
			String implementationPeriod = x.replace(PeriodSearchListDTO.UP, StringUtils.EMPTY)
					.replace(PeriodSearchListDTO.DOWN, StringUtils.EMPTY);
			if (StringUtils.isNumeric(implementationPeriod) && Integer.valueOf(implementationPeriod) >= presentPeriod) {
				filterImplementation.add(x);
			}
		});
		dto.setImplementationPeriodUpDowns(filterImplementation);

		// remove past expiration period
		List<String> filterExpiration = new ArrayList<>();
		dto.getExpirationPeriodUpDowns().forEach(x -> {
			String expirationPeriod = x.replace(PeriodSearchListDTO.UP, StringUtils.EMPTY)
					.replace(PeriodSearchListDTO.DOWN, StringUtils.EMPTY);
			if (StringUtils.isNumeric(expirationPeriod) && Integer.valueOf(expirationPeriod) >= presentPeriod) {
				filterExpiration.add(x);
			}
		});
		dto.setExpirationPeriodUpDowns(filterExpiration);
	}

	private void refactorObject(Object obj) {
		try {
			for (Field field : obj.getClass().getDeclaredFields()) {
				if (field.getType().getSimpleName().equals("String")) {
					field.setAccessible(true);
					if (field.get(obj) != null && field.get(obj).equals("") && !field.getName().equals("title")) {
						field.set(obj, null);
					}
				} else if (field.getType().getSimpleName().equals("List")) {
					field.setAccessible(true);
					if (field.get(obj) != null) {
						List<?> list = (List<?>)field.get(obj);
						for (Object o : list) {
							refactorObject(o);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("refactorObject(): " + e.getMessage());
		}
	}
}
