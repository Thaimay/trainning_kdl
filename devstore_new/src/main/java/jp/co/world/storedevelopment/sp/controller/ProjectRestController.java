package jp.co.world.storedevelopment.sp.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jp.co.world.storedevelopment.common.controller.CommonProjectController;
import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.MProjectProgressStatus;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.ProjectOpinion;
import jp.co.world.storedevelopment.model.ProjectTask;
import jp.co.world.storedevelopment.model.Shop;
import jp.co.world.storedevelopment.model.mapper.repository.MProjectProgressStatusRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectReadLaterAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectTaskRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ShopRepository;
import jp.co.world.storedevelopment.model.service.AccountDataService;
import jp.co.world.storedevelopment.model.service.ProjectService;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectCreateDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectCreateFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectDetailDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectExclutionControlDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectFindFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectUpdateDTO;

/**
 * @author hungdh
 *
 */
@RestController
@RequestMapping("/sp/project")
public class ProjectRestController extends CommonProjectController {
	private ProjectService projectService = new ProjectService();
	private ProjectRepository projectRepository = new ProjectRepository();

	@CrossOrigin
	@RequestMapping("/detail/{id}")
	public ProjectDetailDTO detail(@PathVariable Long id) {
		try {
			logStartMethod("detail");

			ProjectDetailDTO dto = getDetail(id);

			logEndMethod("detail");
			return dto;
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			logException("detail", ex.getMessage());
			return null;
		}
	}

	@CrossOrigin
	@RequestMapping("/updatetimestamp/{id}")
	public ProjectExclutionControlDTO updatetimestamp(@PathVariable Long id) {
		Project model = new ProjectRepository().findById(id).orElseGet(() -> {
			throw new IllegalStateException("");
		});
		return new ProjectExclutionControlDTO(model);
	}

	@CrossOrigin
	@GetMapping("/list")
	public List<ProjectListDTO> getProjectList() {
		try {
			logStartMethod("getProjectList");

			ProjectFindFormDTO dto = new ProjectFindFormDTO();
			dto.setAccount(getAccount());

			List<ProjectListDTO> listDTO = findForSP(dto, getAccount());

			logEndMethod("getProjectList");
			return listDTO;
		} catch (Exception ex) {
			ex.printStackTrace(System.out);

			logException("getProjectList", ex.getMessage());
			return null;
		}
	}

	@CrossOrigin
	@RequestMapping("/initCreateData/{buildingId}")
	public ProjectCreateFormDTO initCreateData(@PathVariable Long buildingId) {
		try {
			logStartMethod("initCreateData");
			ProjectCreateFormDTO dto = new ProjectCreateFormDTO(buildingId);

			logEndMethod("initCreateData");
			return dto;
		} catch (Exception ex) {
			logException("initCreateData", ex.getMessage());
			return null;
		}
	}

	@CrossOrigin
	@RequestMapping("/find")
	public List<ProjectListDTO> find(@RequestBody @Valid ProjectFindFormDTO dto) {
		try {
			logStartMethod("find", dto);

			dto.setAccount(getAccount());
			dto.setAccountId(getAccount().getId());
			List<ProjectListDTO> listDTO = findForSP(dto, getAccount());

			// set condition to data user
			if (dto.getIsDefaultSearch()) {
				new AccountDataService().updateProjectConditionSP(getAccount(), dto.defaultCondition());
			}

			logEndMethod("find");
			return listDTO;
		} catch (Exception ex) {
			logException("find", ex.getMessage());
			return null;
		}
	}

	@CrossOrigin
	@RequestMapping("/update")
	@ResponseStatus(HttpStatus.OK)
	public Long updateProject(@RequestParam("json") String json, @RequestParam("imgs") List<MultipartFile> imgs,
			@RequestParam("docs") List<MultipartFile> docs,
			@RequestParam("opinionFiles") List<MultipartFile> opinionFiles) {
		try {
			logStartMethod("updateProject");

			ProjectUpdateDTO dto = ProjectUpdateDTO.toDTO(json);
			refactorObject(dto);
			int fileOpinionIndex = 0;

			for (ProjectOpinion po : dto.getProjectOpinionDto()) {
				if (po.getHasFile() == true && opinionFiles.size() > fileOpinionIndex) {
					po.setFormFile(opinionFiles.get(fileOpinionIndex));
					fileOpinionIndex++;
				}
			}
			dto.setMultipartFiles(docs);
			Long id = projectService.updateAll(dto, getAccount());

			logEndMethod("updateProject");
			return id;
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			logException("updateProject", ex.getMessage());
			return null;
		}
	}

	@CrossOrigin
	@RequestMapping("/create")
	@ResponseStatus(HttpStatus.OK)
	public Long createProject(@RequestParam("json") String json, @RequestParam("docs") List<MultipartFile> docs,
			@RequestParam("opinionFiles") List<MultipartFile> opinionFiles) {
		try {
			logStartMethod("createProject");

			ProjectCreateDTO dto = ProjectCreateDTO.toDTO(json);
			refactorObject(dto);
			int fileOpinionIndex = 0;

			for (ProjectOpinion po : dto.getProjectOpinionDto()) {
				if (po.getHasFile() == true && opinionFiles.size() > fileOpinionIndex) {
					po.setFormFile(opinionFiles.get(fileOpinionIndex));
					fileOpinionIndex++;
				}
			}
			dto.setMultipartFiles(docs);
			Project project = projectService.createAll(dto, getAccount());

			logEndMethod("createProject");

			return project.getId();
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			logException("createProject", ex.getMessage());
			return null;
		}
	}

	public ProjectDetailDTO getDetail(long id) {
		Project project = projectRepository.findById(id).orElseThrow(() -> {
			throw new IllegalArgumentException("存在しないIDです");
		});
		return new ProjectDetailDTO(project, getAccount());
	}

	@RequestMapping(value = "/readLater/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void readLater(@PathVariable Long id) {
		try {
			logStartMethod("readLater");
			Project project = new ProjectRepository().findById(id).orElseThrow(() -> {
				throw new IllegalArgumentException("存在しないIDです");
			});
			new ProjectReadLaterAccountRepository().switchReadLater(project, getAccount());
			logEndMethod("readLater");
		} catch (Exception ex) {
			logException("readLater", ex.getMessage());
		}
	}

	public List<ProjectListDTO> findForSP(ProjectFindFormDTO dto, Account account) {
		return projectRepository.findForSP(dto).stream().map(p -> new ProjectListDTO(p, account))
				.collect(Collectors.toList());
	}

	@CrossOrigin
	@RequestMapping("/countproject")
	public int countProject(@RequestBody @Valid ProjectFindFormDTO dto) {
		try {
			logStartMethod("countproject");
			dto.setAccount(getAccount());
			dto.setAccountId(getAccount().getId());
			// set condition to data user
			if (dto.getIsDefaultSearch()) {
				new AccountDataService().updateProjectConditionSP(getAccount(), dto.defaultCondition());
			}
			int count = getCountProject(dto);
			logEndMethod("countproject");
			return count;
		} catch (Exception ex) {
			logException("countproject", ex.getMessage());
			return 0;
		}
	}
	public int getCountProject(ProjectFindFormDTO dto) {
		return projectRepository.getCountFindForSP(dto);
	}
	@CrossOrigin
	@RequestMapping(value = "/projectTask/complete/{id}")
	public boolean projectTaskComplete(@PathVariable Long id) {
		try {
			logStartMethod("projectTaskComplete", id);
			ProjectTask model = new ProjectTaskRepository().findById(id).orElseGet(() -> {
				throw new IllegalStateException();
			});
			model.complete();
			logEndMethod("projectTaskComplete");
			return model.getComplete();
		} catch (Exception ex) {
			logException("projectTaskComplete", ex.getMessage());
			return false;
		}
	}

	@CrossOrigin
	@RequestMapping("/find/shop/{ishopId}")
	public Shop findShop(@PathVariable Long ishopId) {
		try {
			logStartMethod("findShop");
			Shop shop = new ShopRepository().findByIShopId(ishopId);
			logEndMethod("findShop");
			return shop;
		} catch (Exception ex) {
			logException("findShop", ex.getMessage());
			return null;
		}
	}

	@CrossOrigin
	@RequestMapping("/find/listprojectprogress")
	public List<MProjectProgressStatus> findProjectProgress() {
		try {
			logStartMethod("findProjectProgress");
			List<MProjectProgressStatus> list = new MProjectProgressStatusRepository().findAll();
			logEndMethod("findProjectProgress");
			return list;
		} catch (Exception ex) {
			logException("findProjectProgress", ex.getMessage());
			return null;
		}
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
