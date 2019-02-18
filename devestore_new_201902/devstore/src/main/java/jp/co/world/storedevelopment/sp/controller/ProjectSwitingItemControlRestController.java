package jp.co.world.storedevelopment.sp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.world.storedevelopment.common.controller.CommonProjectController;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.ProjectSwitingItemControl;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectSwitingItemControlRepository;

/**
 * @author higashigawa
 *
 */
@RestController
@RequestMapping("/sp/projectswiting/")
public class ProjectSwitingItemControlRestController extends CommonProjectController {
	private ProjectRepository projectRepository = new ProjectRepository();
	private ProjectSwitingItemControlRepository projectSwitingItemControlRepository = new ProjectSwitingItemControlRepository();

	@CrossOrigin
	@RequestMapping("/category/{projectCategoryId}")
	public ProjectSwitingItemControl project(@PathVariable Long projectCategoryId) {

		try {
			logStartMethod("switing/project");
			//ProjectSwitingItemControl projectSwitingItemControl = new ProjectSwitingItemControl();
			ProjectSwitingItemControl projectSwitingItemControl = getProjectSwitingItemControlByProjectCategoryId(projectCategoryId);

			logEndMethod("switing/project");
			return projectSwitingItemControl;
		} catch (Exception ex) {
			logException("switing/project", ex.getMessage());
			return null;
		}
	}

	public Project getProject(long id) {
		Project project = projectRepository.findById(id).orElseThrow(() -> {
			throw new IllegalArgumentException("存在しないIDです");
		});
		return project;
	}

	public ProjectSwitingItemControl getProjectSwitingItemControlByProjectCategoryId(Long projectCategoryId) {
		ProjectSwitingItemControl projectSwitingItemControl = projectSwitingItemControlRepository
				.findByProjectCategoryId(projectCategoryId).orElseThrow(() -> {
			throw new IllegalArgumentException("存在しないIDです");
		});
		return projectSwitingItemControl;
	}
}
