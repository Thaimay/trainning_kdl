package jp.co.world.storedevelopment.pc.controller;


import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.ProjectDocument;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectDocumentRepository;



@Controller
@RequestMapping(value="/pc/project_document")
public class ProjectDocumentController {
	
	
	private static final String BASE_DIR = "pc/project_document/";
	private ProjectDocumentRepository project = new ProjectDocumentRepository();

	
	@RequestMapping()
	private String listView(Model model) {
		List<ProjectDocument> projectList = project.getProjectList();
		model.addAttribute("projectList", projectList);
		
		return BASE_DIR + "list";
	}
	
	@RequestMapping(value = "/showCreate")
	@ResponseStatus(HttpStatus.OK)
	public String showCreate(Model model) {
		ProjectDocument projectDoc = new ProjectDocument();
		
		model.addAttribute("projectDoc", projectDoc);
		
		return BASE_DIR + "create";
	}
	
	@RequestMapping(value="/list", method = RequestMethod.POST)
	public String insertProject(Model model, @ModelAttribute("projectDoc") ProjectDocument projectDoc) {
		
		ProjectDocument newProjectDocument = new ProjectDocument();
		newProjectDocument.setIgnoreFields(new String[] {"id", "corporationGroup", "isDeleted" });
		
		newProjectDocument.setProjectId(projectDoc.getProjectId()); 
		newProjectDocument.setFileId(projectDoc.getFileId());		   
		newProjectDocument.setName(projectDoc.getName()); 
		newProjectDocument.setProjectDocumentCoversheetClassification(projectDoc.getProjectDocumentCoversheetClassification());
		newProjectDocument.setMeetingPoint(projectDoc.getMeetingPoint()); 
		newProjectDocument.setOutputStatus(projectDoc.getOutputStatus());
		newProjectDocument.setCreatedDatetime(projectDoc.getCreatedDatetime());
		newProjectDocument.setUpdateDatetime(projectDoc.getUpdateDatetime());
		newProjectDocument.setCreatedAccountCode(projectDoc.getCreatedAccountCode());
		newProjectDocument.setUpdateAccountCode(projectDoc.getUpdateAccountCode());
		
		newProjectDocument.create();
		
		return "redirect:/" + BASE_DIR;
	}
	
	
	
	@RequestMapping(value="/detail/{id}", method = RequestMethod.GET)
	public String detailProjectDoc(Model model, @PathVariable Long id) {
		ProjectDocument projectById = project.findProjectById(id);
		model.addAttribute("project", projectById);
		
		return BASE_DIR + "detail";
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public String editProjectForm(Model model, @PathVariable Long id) {
		ProjectDocument projectById = project.findProjectById(id);
		model.addAttribute("project", projectById);
		
		return BASE_DIR + "edit";
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.POST)
	public String updateProject(Model model, @ModelAttribute("project") ProjectDocument project, @PathVariable Long id) {
		ProjectDocument newProjectDocument = new ProjectDocument();
		newProjectDocument.setIgnoreFields(new String[] {"corporationGroup", "isDeleted", "projectId", "fileId",
				"createdDatetime", "createdAccountCode", "updateAccountCode"});
		newProjectDocument.setId(id);
		newProjectDocument.setName(project.getName()); 
		newProjectDocument.setProjectDocumentCoversheetClassification(project.getProjectDocumentCoversheetClassification());
		newProjectDocument.setMeetingPoint(project.getMeetingPoint()); 
		newProjectDocument.setOutputStatus(project.getOutputStatus());
		newProjectDocument.setUpdateDatetime(project.getUpdateDatetime());

		
		newProjectDocument.update();
		
		return "redirect:/" + BASE_DIR;
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String updateProject(Model model, @PathVariable Long id) {
		ProjectDocument newProjectDocument = new ProjectDocument();
		
		newProjectDocument.setId(id);
		
		newProjectDocument.delete();
		
		return "redirect:/" + BASE_DIR;
	}
	
	@RequestMapping(value="/search", method = RequestMethod.POST)
	public String searchProject(Model model, @RequestParam("nameSearch") String name) {
		
		List<ProjectDocument> projectList = project.getProjectListSearch(name);
		
		model.addAttribute("projectList", projectList);
		return BASE_DIR + "list";
	}
	
	
//	@RequestMapping(value="/api")
	@ResponseBody
	public List<ProjectDocument> apiRestful(Model model) {
		
		List<ProjectDocument> list = project.getProjectList();
		
		
		//return BASE_DIR + "API";
		return list;
	}
	
	
}
