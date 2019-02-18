package jp.co.world.storedevelopment.model;

import java.time.format.DateTimeFormatter;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ProjectDocumentModelMapper; 

public class ProjectDocument extends ActiveModel<ProjectDocument>{
	
	private static final String EMPTY = "";
	private static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	
	private int projectId;
	private int fileId;
	private String name;
	private int projectDocumentCoversheetClassification;
	private String meetingPoint;
	private String outputStatus;
	
	private String[] ignoreFields = new String[] {};
	
	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	public void setIgnoreFields(String[] ignoreFields) {
		this.ignoreFields = ignoreFields;
	}

	public ProjectDocument() {
		//
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
	
	public String createdDatetimeString() {
		if (this.getCreatedDatetime() != null) {
			return this.getCreatedDatetime().format(DATE_FORMAT);
		} 
		else {
			return EMPTY;
		}
	}
	
	public String updateDatetimeString() {
		if (this.getUpdateDatetime() != null) {
			return this.getUpdateDatetime().format(DATE_FORMAT);
		} 
		else {
			return EMPTY;
		}
	}

	@Override
	protected ModelMapper<ProjectDocument> modelMapper(SqlSession session) {
		return session.getMapper(ProjectDocumentModelMapper.class);
	}
	
	

}
