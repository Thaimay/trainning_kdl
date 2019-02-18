package jp.co.world.storedevelopment.sp.controller.dto;

import java.time.format.DateTimeFormatter;

import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.ProjectDocument;

public class ProjectDocListDTO implements DTO<ProjectDocument>{
	private static final String EMPTY = "";
	private static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	
	private int projectId;
	private int fileId;
	private String name;
	private int projectDocumentCoversheetClassification;
	private String meetingPoint;
	private String outputStatus;
	
	@Override
	public ProjectDocument createModel() {
		return new ProjectDocument();
	}

	public ProjectDocListDTO() {

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
