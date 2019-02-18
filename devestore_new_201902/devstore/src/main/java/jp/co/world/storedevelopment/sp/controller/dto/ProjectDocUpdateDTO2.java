package jp.co.world.storedevelopment.sp.controller.dto;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jp.co.world.storedevelopment.model.ProjectDocument;

public class ProjectDocUpdateDTO2 implements DTO<ProjectDocument> {
	private static final ObjectMapper MAPPER = new ObjectMapper();

	private Long id;
	private int projectId;
	private int fileId;
	private String name;
	private int projectDocumentCoversheetClassification;
	private String meetingPoint;
	private String outputStatus;
	
	public ProjectDocUpdateDTO2(ProjectDocument project) {
		this.copyProperties(this, project);
	}

	public static ProjectDocUpdateDTO2 toDTO(String json) {
		try {
			MAPPER.registerModule(new JavaTimeModule());
			return MAPPER.readValue(json, ProjectDocUpdateDTO2.class);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getMessage());
		}
	}
	
	@Override
	public ProjectDocument createModel() {
		return new ProjectDocument();
	}

	public ProjectDocUpdateDTO2() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	
	

}
