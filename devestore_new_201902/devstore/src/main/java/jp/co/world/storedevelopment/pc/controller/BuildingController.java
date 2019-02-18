package jp.co.world.storedevelopment.pc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import jp.co.world.storedevelopment.common.controller.CommonBuildingController;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.BuildingFile;
import jp.co.world.storedevelopment.model.BuildingImage;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingFileRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingImageRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MBuildingSalesClassificationsRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MBuildingSalesTypesRepository;
import jp.co.world.storedevelopment.model.service.BuildingService;
import jp.co.world.storedevelopment.pc.controller.form.BuildingCreateForm;
import jp.co.world.storedevelopment.pc.controller.form.BuildingFindForm;
import jp.co.world.storedevelopment.pc.controller.view.model.BuildingDetailViewModel;
import jp.co.world.storedevelopment.pc.controller.view.model.BuildingHistoryViewModel;
import jp.co.world.storedevelopment.pc.controller.view.model.BuildingViewModel;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingHistoryListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingUpdateDTO;

@Controller
@RequestMapping("/pc/building")
public class BuildingController extends CommonBuildingController {

	private static final String BASE_DIR = "pc/building/";
	private BuildingRepository buildingRepository = new BuildingRepository();

	private String listView(Model model, BuildingFindForm dto) {
		findKeyword(model, dto);

		return BASE_DIR + "list";
	}

	private void findKeyword(Model model, BuildingFindForm dto) {
		List<Building> listBuildingKeyword = new ArrayList<Building>();
		BuildingFindForm findKeywordDTO = BuildingFindForm.EMPTY;
		int listBuildingKeywordCount = 0;
		int pageKeywordCount = 0;

		if (dto != null) {
			findKeywordDTO = dto;
			listBuildingKeyword = buildingRepository.findForPC(findKeywordDTO);
			listBuildingKeywordCount = buildingRepository.getCountFindForPC(findKeywordDTO);
			pageKeywordCount = this.getPageCount(listBuildingKeywordCount, dto.getPagingSize());
		}

		model.addAttribute("findKeywordDTO", findKeywordDTO);
		model.addAttribute("buildingKeywords", BuildingViewModel.toList(listBuildingKeyword));
		model.addAttribute("countKeyword", listBuildingKeywordCount);
		model.addAttribute("pageCountKeyword", pageKeywordCount);
	}

	private String detailView(Model model, Building building, List<BuildingHistoryListDTO> historyList) {
		model.addAttribute("building", new BuildingDetailViewModel(building, getAccount()));
		model.addAttribute("buildingSalesTypes", new MBuildingSalesTypesRepository().findAll());
		model.addAttribute("historyList", historyList);
		return BASE_DIR + "detail";
	}

	private String revisionView(Model model, Long buildingId, List<BuildingHistoryListDTO> historyList,
			Building history) {
		model.addAttribute("building", new BuildingHistoryViewModel(history, getAccount()));
		model.addAttribute("buildingId", buildingId);
		model.addAttribute("buildingSalesTypes", new MBuildingSalesTypesRepository().findAll());
		model.addAttribute("historyList", historyList);
		return BASE_DIR + "revision";
	}

	@RequestMapping("/index")
	public String index(Model model) {
		try {
			logStartMethod("index");
			String index = index(model);
			logEndMethod("index");
			return index;
		} catch (Exception ex) {
			logException("index", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/list")
	public String list(Model model) {
		try {
			logStartMethod("list");
			String list = listView(model, BuildingFindForm.EMPTY);
			logEndMethod("list");
			return list;
		} catch (Exception ex) {
			logException("list", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find")
	public String find(Model model, @Valid BuildingFindForm dto) {
		try {
			logStartMethod("find");
			BuildingViewModel.toList(buildingRepository.findForPC(dto));
			String find = listView(model, dto);
			logEndMethod("find");
			return find;
		} catch (Exception ex) {
			logException("find", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id, Model model) {
		try {
			logStartMethod("detail");
			Building building = buildingRepository.findById(id).orElseThrow(() -> {
				throw new IllegalArgumentException("存在しないIDです");
			});
			List<BuildingHistoryListDTO> historyList = getBuildingHistoryList(id);
			String detailView = detailView(model, building, historyList);
			logEndMethod("detail");
			return detailView;
		} catch (Exception ex) {
			logException("detail", ex.getMessage());
			return null;
		}
	}

	// @RequestMapping(value = "/revision/{id}", method = RequestMethod.GET)
	// public String revision(@PathVariable Long id, Model model) {
	// Building history = null;
	// List<BuildingHistoryListDTO> historyList = null;
	//
	// Optional<Building> building = buildingRepository.findById(id);
	//
	// if (building.isPresent()) {
	// historyList =
	// buildingRepository.getBuildingHistoryList(building.get().getBuildingCd());
	// if (historyList != null && historyList.size() > 0) {
	// history =
	// buildingRepository.findBuildingHistoryById(historyList.get(0).getId());
	// }
	// }
	//
	// return revisionView(model, id, historyList, history);
	// }

	@RequestMapping(value = "/revision/{id}", method = RequestMethod.POST)
	public String revision(@PathVariable Long id, @Valid Long idHistory, Model model) {
		try {
			logStartMethod("revision");
			String revisionView = "";

			if (idHistory > 0) {
				Building history = null;
				List<BuildingHistoryListDTO> historyList = getBuildingHistoryList(id);

				if (historyList != null && historyList.size() > 0 && idHistory != null && idHistory > 0) {
					history = buildingRepository.findBuildingHistoryById(idHistory);
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

	private List<BuildingHistoryListDTO> getBuildingHistoryList(Long buildingId) {
		try {
			logStartMethod("getBuildingHistoryList");
			List<BuildingHistoryListDTO> historyList = null;
			Optional<Building> building = buildingRepository.findById(buildingId);
			if (building.isPresent()) {
				historyList = buildingRepository.getBuildingHistoryList(building.get().getBuildingCd());
			}

			logEndMethod("getBuildingHistoryList");
			return historyList;
		} catch (Exception ex) {
			logException("getBuildingHistoryList", ex.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/showUpdate/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String showUpdate(Model model, @PathVariable Long id) {
		try {
			logStartMethod("showUpdate");
			Building building = buildingRepository.findById(id).orElseThrow(() -> {
				throw new IllegalArgumentException("存在しないIDです");
			});
			model.addAttribute("building", new BuildingDetailViewModel(building));
			model.addAttribute("buildingSalesTypes", new MBuildingSalesTypesRepository().findAll());
			model.addAttribute("buildingSalesClassifications", new MBuildingSalesClassificationsRepository().findAll());
			logEndMethod("showUpdate");
			return BASE_DIR + "edit";
		} catch (Exception ex) {
			logException("showUpdate", ex.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/update")
	@ResponseBody
	public String update(Model model, @Valid BuildingUpdateDTO dto, @Valid List<MultipartFile> docs,
			@Valid List<MultipartFile> imgs) {
		try {
			logStartMethod("update");
			if (dto.getBuildingFileDto() == null) {
				dto.setBuildingFileDto(new ArrayList<BuildingFile>());
			}
			processBuildingFileData(dto.getBuildingFileDto(), docs);

			if (dto.getBuildingImageDto() == null) {
				dto.setBuildingImageDto(new ArrayList<BuildingImage>());
			}
			processBuildingImageData(dto.getBuildingImageDto(), imgs);

			new BuildingService().updateAll(dto, this.getAccount());

			String update = dto.getId().toString();
			logEndMethod("update");

			return update;
		} catch (Exception ex) {
			logException("update", ex.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/showCreate")
	@ResponseStatus(HttpStatus.OK)
	public String showCreate(Model model) {
		try {
			logStartMethod("showCreate");
			model.addAttribute("buildingSalesTypes", new MBuildingSalesTypesRepository().findAll());
			model.addAttribute("buildingSalesClassifications", new MBuildingSalesClassificationsRepository().findAll());
			logEndMethod("showCreate");
			return BASE_DIR + "create";
		} catch (Exception ex) {
			logException("showCreate", ex.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/create")
	@ResponseBody
	public void create(Model model, @Valid BuildingCreateForm dto, @Valid List<MultipartFile> docs,
			@Valid List<MultipartFile> imgs) {
		try {
			logStartMethod("create");
			if (dto.getBuildingFileDto() == null) {
				dto.setBuildingFileDto(new ArrayList<BuildingFile>());
			}
			processBuildingFileData(dto.getBuildingFileDto(), docs);

			if (dto.getBuildingImageDto() == null) {
				dto.setBuildingImageDto(new ArrayList<BuildingImage>());
			}
			processBuildingImageData(dto.getBuildingImageDto(), imgs);

			new BuildingService().createAllForPC(dto, this.getAccount());
			logEndMethod("create");
		} catch (Exception ex) {
			logException("create", ex.getMessage());
		}
	}

	private void processBuildingFileData(List<BuildingFile> data, List<MultipartFile> docs) {
		try {
			logStartMethod("processBuildingFileData");
			int fileIndex = 0;

			for (BuildingFile bf : data) {
				if (bf.getId() == 0) {
					bf.setFile(docs.get(fileIndex));
					fileIndex++;
				} else if (!bf.getIsDeleted()) {
					bf = new BuildingFileRepository().findById(bf.getId()).get();
				}
			}
			logEndMethod("processBuildingFileData");
		} catch (Exception ex) {
			logException("processBuildingFileData", ex.getMessage());
		}
	}

	private void processBuildingImageData(List<BuildingImage> data, List<MultipartFile> imgs) {
		try {
			logStartMethod("processBuildingImageData");
			int fileIndex = 0;
			for (BuildingImage bi : data) {

				if (bi.getId() == 0) {
					bi.setFile(imgs.get(fileIndex));
					fileIndex++;
				} else if (!bi.getIsDeleted()) {
					bi = new BuildingImageRepository().findById(bi.getId()).get();
				}
			}
			logEndMethod("processBuildingImageData");
		} catch (Exception ex) {
			logException("processBuildingImageData", ex.getMessage());
		}

	}

	@RequestMapping(value = "/checkBuildingCd")
	@ResponseBody
	public boolean checkBuildingCd(@Valid String buildingCd) {
		try {
			logStartMethod("checkBuildingCd");
			Building building = buildingRepository.getBuildingByBuildingCd(buildingCd);
			logEndMethod("checkBuildingCd");
			return building == null;
		} catch (Exception ex) {
			logException("checkBuildingCd", ex.getMessage());
			return false;
		}
	}

	@RequestMapping(value = "/checkOriginBuildingId")
	@ResponseBody
	public boolean checkOriginBuildingId(@Valid Long originBuildingId) {
		try {
			logStartMethod("checkOriginBuildingId");
			Building building = buildingRepository.getBuildingByOriginBuildingId(originBuildingId);
			logEndMethod("checkOriginBuildingId");
			return building == null;
		} catch (Exception ex) {
			logException("checkOriginBuildingId", ex.getMessage());
			return false;
		}
	}

	public Integer getPageCount(Integer count, Integer pagingSize) {
		Integer pageCount = (int) Math.ceil(Double.valueOf(count) / Double.valueOf(pagingSize)) - 1;
		if (pageCount > 0) {
			return pageCount;
		} else {
			return 0;
		}
	}

}
