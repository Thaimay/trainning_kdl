package jp.co.world.storedevelopment.sp.controller.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.ProjectImage;

public class ProjectImageRelationProjectDTO implements DTO<ProjectImage> {

	private Long id;
	private Long projectId;
	private String name;
	private String displayName;
	private String type;
	private String comment;
	private Long size;
	private String division;
	private String path;
	private String createdAccountCode;
	private String createdAccountName;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime updateDatetime;
	private Boolean isDeleted;

	public ProjectImageRelationProjectDTO() {
	}

	public ProjectImageRelationProjectDTO(ProjectImage projectImage) {
		this.copyProperties(this, projectImage);
		this.setPath(projectImage.urlPath() + projectImage.getName());
		this.setCreatedAccountName(projectImage.getAccountName());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCreatedAccountCode() {
		return createdAccountCode;
	}

	public void setCreatedAccountCode(String createdAccountCode) {
		this.createdAccountCode = createdAccountCode;
	}

	public String getCreatedAccountName() {
		return createdAccountName;
	}

	public void setCreatedAccountName(String createdAccountName) {
		this.createdAccountName = createdAccountName;
	}

	public LocalDateTime getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(LocalDateTime updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getUpdateDatetimeString() {
		return updateDatetime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
	}

	@Override
	public ProjectImage createModel() {
		return new ProjectImage();
	}

}
