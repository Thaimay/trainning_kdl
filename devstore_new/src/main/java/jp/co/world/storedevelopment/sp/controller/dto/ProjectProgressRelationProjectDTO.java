package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.ProjectProgress;

public class ProjectProgressRelationProjectDTO implements DTO<ProjectProgress> {

	private Long id;
	private String category;
	private Integer priority;
	private String code;
	private String name;

	public ProjectProgressRelationProjectDTO() {

	}

	public ProjectProgressRelationProjectDTO(ProjectProgress projectProgress) {
		copyProperties(this, projectProgress);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public ProjectProgress createModel() {
		return new ProjectProgress();
	}

}
