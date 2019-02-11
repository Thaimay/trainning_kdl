package jp.co.world.storedevelopment.pc.controller.form;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.ProjectDocument;
import jp.co.world.storedevelopment.pc.controller.dto.DTO;

public class ProjectDocumentForm implements DTO<ProjectDocument> {
	private int id;
	private String corporationGroup;
	private int projectId;
	private int fileId;
	private String name;
	private int projectDocument;
	private String meetingPoint;
	private String outputStatus;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime createdDatetime = LocalDateTime.now();
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime updateDatetime = LocalDateTime.now();
	private String createdAccount;
	private String updateAccount;
	private boolean isDeleted;

	
	public ProjectDocumentForm() {
		//
	}
	
	public ProjectDocumentForm(ProjectDocument project) {
		this.copyProperties(this, project);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCorporationGroup() {
		return corporationGroup;
	}

	public void setCorporationGroup(String corporationGroup) {
		this.corporationGroup = corporationGroup;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProjectDocument() {
		return projectDocument;
	}

	public void setProjectDocument(int projectDocument) {
		this.projectDocument = projectDocument;
	}

	public String getMeetingPoint() {
		return meetingPoint;
	}

	public void setMeetingPoint(String meetingPoint) {
		this.meetingPoint = meetingPoint;
	}

	public String getOutputStatus() {
		return outputStatus;
	}

	public void setOutputStatus(String outputStatus) {
		this.outputStatus = outputStatus;
	}

	public LocalDateTime getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(LocalDateTime createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public LocalDateTime getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(LocalDateTime updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public String getCreatedAccount() {
		return createdAccount;
	}

	public void setCreatedAccount(String createdAccount) {
		this.createdAccount = createdAccount;
	}

	public String getUpdateAccount() {
		return updateAccount;
	}

	public void setUpdateAccount(String updateAccount) {
		this.updateAccount = updateAccount;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	
	
	@Override
	public ProjectDocument createModel() {
		return new ProjectDocument();
	}
}
