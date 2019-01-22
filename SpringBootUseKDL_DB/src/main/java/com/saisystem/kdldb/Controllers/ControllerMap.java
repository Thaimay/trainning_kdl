package com.saisystem.kdldb.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.saisystem.kdldb.Mapper.MapperProject;
import com.saisystem.kdldb.Model.ProjectInfo;

@Controller
public class ControllerMap {
	
	private MapperProject mapperProject;
	
	public ControllerMap(MapperProject mapperProject) {
		this.mapperProject = mapperProject;
	}
	
	@Value("${header.message}")
	private String header;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String getAll(Model model) {
		model.addAttribute("header", header);
		
		List<ProjectInfo> projectInfoList = mapperProject.fillAll();
		
		model.addAttribute("listProject", projectInfoList);
		
		return "getAll";
	}
	
}
