package jp.co.world.storedevelopment.sp.controller;

import java.lang.reflect.Field;
import java.util.List;

import javax.validation.Valid;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jp.co.world.storedevelopment.model.BuildingFile;
import jp.co.world.storedevelopment.model.BuildingImage;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.ProjectDocument;
import jp.co.world.storedevelopment.model.ProjectOpinion;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectDocumentRepository;
import jp.co.world.storedevelopment.model.service.BuildingService;
import jp.co.world.storedevelopment.model.service.ProjectDocService;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingFindFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingUpdateDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectCreateDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectDocCreateDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectDocUpdateDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectDocUpdateDTO2;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectUpdateDTO;


@RestController
@RequestMapping("/sp/projectdoc")
public class ProjectDocRestController {
	ProjectDocService projectDocS = new ProjectDocService();
	
	@CrossOrigin
	@GetMapping("/list")
	public List<ProjectDocument> getBuildingList() {
		try {
			List<ProjectDocument> listDTO = new ProjectDocumentRepository().findAll();
			
			return listDTO;
		} catch (Exception ex) {
			return null;
		}
	}
	
	@CrossOrigin
	@RequestMapping("/detail/{id}")
	public ProjectDocument detail(@PathVariable Long id) {
		try {
			ProjectDocument dto = new ProjectDocumentRepository().findProjectById(id);
			return dto;
		} catch (Exception ex) {
			return null;
		}
	}

	
	@CrossOrigin
	@RequestMapping("/find")
	public List<ProjectDocument> find(@RequestBody @Valid ProjectDocUpdateDTO dto) {
		try {
	
			List<ProjectDocument> listDTO = new ProjectDocumentRepository().find(dto);

			return listDTO;
		} catch (Exception ex) {
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
			//logStartMethod("updateProject");

			ProjectDocUpdateDTO2 dto = ProjectDocUpdateDTO2.toDTO(json);
			refactorObject(dto);
//			int fileOpinionIndex = 0;
//
//			for (ProjectOpinion po : dto.getProjectOpinionDto()) {
//				if (po.getHasFile() == true && opinionFiles.size() > fileOpinionIndex) {
//					po.setFormFile(opinionFiles.get(fileOpinionIndex));
//					fileOpinionIndex++;
//				}
//			}
//			dto.setMultipartFiles(docs);
			Long id = projectDocS.updateAll(dto);

//			logEndMethod("updateProject");
			return id;
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
//			logException("updateProject", ex.getMessage());
			return null;
		}
	}

	
	@CrossOrigin
	@RequestMapping("/create")
	@ResponseStatus(HttpStatus.OK)
	public Long createProject(@RequestParam("json") String json) {
		try {
			//logStartMethod("createProject");

			ProjectDocCreateDTO dto = ProjectDocCreateDTO.toDTO(json);
			refactorObject(dto);
			
			
			ProjectDocument projectDoc = projectDocS.createAll(dto);

			//logEndMethod("createProject");

			return projectDoc.getId();
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		//	logException("createProject", ex.getMessage());
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
			//logger.error("refactorObject(): " + e.getMessage());
		}
	}
	
	
}
