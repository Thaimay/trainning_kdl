package jp.co.world.storedevelopment.sp.controller.dto;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


import jp.co.world.storedevelopment.model.ProjectDocument;

public class ProjectDocCreateDTO implements DTO<ProjectDocument> {
	private static final ObjectMapper MAPPER = new ObjectMapper();

	private int projectId;
	private int fileId;
	private String name;
	private int projectDocumentCoversheetClassification;
	private String meetingPoint;
	private String outputStatus;
	private String createdAccountCode;
	private String updateAccountCode;
//	@JsonFormat(pattern = "yyyy-MM-dd")
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	private LocalDate createdDatetime;
//	@JsonFormat(pattern = "yyyy-MM-dd")
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	private LocalDateTime updateDatetime;

	public ProjectDocCreateDTO(ProjectDocument project) {
		this.copyProperties(this, project);
	}

	public static ProjectDocCreateDTO toDTO(String json) {
		try {
			MAPPER.registerModule(new JavaTimeModule());
			return MAPPER.readValue(json, ProjectDocCreateDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getMessage());
		}
	}

	@Override
	public ProjectDocument createModel() {
		return new ProjectDocument();
	}

	public ProjectDocCreateDTO() {

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

	public int getProjectDocumentCoversheetClassification() {
		return projectDocumentCoversheetClassification;
	}

	public void setProjectDocumentCoversheetClassification(int projectDocumentCoversheetClassification) {
		this.projectDocumentCoversheetClassification = projectDocumentCoversheetClassification;
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

	public String getCreatedAccountCode() {
		return createdAccountCode;
	}

	public void setCreatedAccountCode(String createAccountCode) {
		this.createdAccountCode = createAccountCode;
	}

	public String getUpdateAccountCode() {
		return updateAccountCode;
	}

	public void setUpdateAccountCode(String updateAccountCode) {
		this.updateAccountCode = updateAccountCode;
	}
	
	
//	
//
//	public LocalDate getCreatedDatetime() {
//		return createdDatetime;
//	}
//
//	public void setCreatedDatetime(LocalDate createdDatetime) {
//		this.createdDatetime = createdDatetime;
//	}

//	public LocalDateTime getUpdateDatetime() {
//		return updateDatetime;
//	}
//
//	public void setUpdateDatetime(LocalDateTime updateDatetime) {
//		this.updateDatetime = updateDatetime;
//	}
	
	
}
