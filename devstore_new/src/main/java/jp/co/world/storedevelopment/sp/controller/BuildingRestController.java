package jp.co.world.storedevelopment.sp.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jp.co.world.storedevelopment.common.controller.CommonBuildingController;
import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.BuildingFile;
import jp.co.world.storedevelopment.model.BuildingImage;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectRepository;
import jp.co.world.storedevelopment.model.service.BuildingService;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingDetailDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingFindFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingHistoryListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingUpdateDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectFindFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectListDTO;

/**
 * @author hungdh
 *
 */
@RestController
@RequestMapping("/sp/building")
public class BuildingRestController extends CommonBuildingController {

	@CrossOrigin
	@RequestMapping("/detail/{id}")
	public BuildingDetailDTO detail(@PathVariable Long id) {
		try {
			logStartMethod("detail");

			Building building = new BuildingRepository().findById(id).orElseThrow(() -> {
				throw new IllegalArgumentException("存在しないIDです");
			});

			BuildingDetailDTO dto = new BuildingDetailDTO(building, getAccount());
			logEndMethod("detail");
			return dto;
		} catch (Exception ex) {
			logException("detail", ex.getMessage());
			return null;
		}
	}

	@CrossOrigin
	@GetMapping("/list")
	public List<BuildingListDTO> getBuildingList() {
		try {
			logStartMethod("getBuildingList");
			List<BuildingListDTO> listDTO = new BuildingRepository().find(BuildingFindFormDTO.EMPTY);
			logEndMethod("getBuildingList");
			return listDTO;
		} catch (Exception ex) {
			logException("getBuildingList", ex.getMessage());
			return null;
		}
	}

	@CrossOrigin
	@RequestMapping("/find")
	public List<BuildingListDTO> find(@RequestBody @Valid BuildingFindFormDTO dto) {
		try {
			logStartMethod("find", dto);
			List<BuildingListDTO> listDTO = new BuildingRepository().find(dto);
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
	public void updateBuilding(@RequestParam("json") String json, @RequestParam("imgs") List<MultipartFile> imgs,
			@RequestParam("docs") List<MultipartFile> docs) {
		try {
			logStartMethod("updateBuilding");
			BuildingUpdateDTO dto = BuildingUpdateDTO.toDTO(json);
			int fileIndex = 0;

			for (BuildingFile bf : dto.getBuildingFileDto()) {

				if (bf.getId() == 0) {
					bf.setFile(docs.get(fileIndex));
					fileIndex++;
				}
			}

			fileIndex = 0;
			for (BuildingImage bi : dto.getBuildingImageDto()) {

				if (bi.getId() == 0) {
					bi.setFile(imgs.get(fileIndex));
					fileIndex++;
				}
			}

			new BuildingService().updateAll(dto, getAccount());
			logEndMethod("updateBuilding");
		} catch (Exception ex) {
			logException("updateBuilding", ex.getMessage());
		}
	}

	@CrossOrigin
	@RequestMapping("/history")
	public List<BuildingHistoryListDTO> getListBuildingHistory(@Valid String buildingCd, @Valid String name) {
		try {
			logStartMethod("getListBuildingHistory");
			List<BuildingHistoryListDTO> historyList = new BuildingRepository().getBuildingHistoryList(buildingCd);
			Stream<BuildingHistoryListDTO> stream = historyList.stream().filter(x -> x.getName().startsWith(name));
			historyList = stream.collect(Collectors.toList());
			logEndMethod("getListBuildingHistory");
			return historyList;
		} catch (Exception ex) {
			logException("getListBuildingHistory", ex.getMessage());
			return null;
		}
	}
	
	@RequestMapping("/detail/project")
	@ResponseBody
	public List<ProjectListDTO> relatedProjectNegotiationList(@RequestBody @Valid ProjectFindFormDTO dto)
			throws Throwable {
		try {
			logStartMethod("relatedProjectBuildingList", dto);
			dto.setAccountId(getAccount().getId());
			dto.setAccount(getAccount());
			List<ProjectListDTO> listDTO = findForSP(dto, getAccount());
			logEndMethod("relatedProjectBuildingList");
			return listDTO;
		} catch (Exception ex) {
			logException("relatedProjectBuildingList", ex.getMessage());
			return null;
		}
	}
	
	public List<ProjectListDTO> findForSP(ProjectFindFormDTO dto, Account account) {
		return new ProjectRepository().findForSP(dto).stream().map(p -> new ProjectListDTO(p, account))
				.collect(Collectors.toList());
	}

}
