package jp.co.world.storedevelopment.sp.controller.dto;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.BuildingImage;
import jp.co.world.storedevelopment.model.ProjectDocImage;
import jp.co.world.storedevelopment.model.ProjectDocument;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingImageRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectDocImageRepository;


public class ProjectDocDetailDTO implements DTO<ProjectDocument> {
	
	private Long id;
	private int projectId;
	private int fileId;
	private String name;
	private int projectDocumentCoversheetClassification;
	private String meetingPoint;
	private String outputStatus;
	private String imagePath;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime createdDatetime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime updateDatetime;
	private String createdAccountCode;
	private String updateAccountCode;
	
	@Override
	public ProjectDocument createModel() {
		return new ProjectDocument();
	}
	
	public ProjectDocDetailDTO(ProjectDocument projectDoc) {
		copyProperties(this, projectDoc);
	}
	
	public ProjectDocDetailDTO(ProjectDocument building, Account account) {
		this(building);
//		List<NegotiationListDTO> dto = NegotiationListDTO.toList(building.findRelatedNegotiations(), account);
//		setRelatedNegotiationList(dto);
//		setRelatedProjects(building, account);
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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

	public String getCreatedAccountCode() {
		return createdAccountCode;
	}

	public void setCreatedAccountCode(String createdAccountCode) {
		this.createdAccountCode = createdAccountCode;
	}

	public String getUpdateAccountCode() {
		return updateAccountCode;
	}

	public void setUpdateAccountCode(String updateAccountCode) {
		this.updateAccountCode = updateAccountCode;
	}


	public List<ProjectDocImageRelationProjectDocDetailDTO> getProjectDocImages() {
		List<ProjectDocImageRelationProjectDocDetailDTO> dtos = new ArrayList<>();

		if (getId() == null) {
			return dtos;
		}

		List<ProjectDocImage> projectDocImages = new ProjectDocImageRepository().findByProjectId(getId());
		return projectDocImages != null && projectDocImages.size() > 0 ? projectDocImages.stream()
				.map(x -> new ProjectDocImageRelationProjectDocDetailDTO(x)).collect(Collectors.toList()) : dtos;
	}

	
	
	
	
}
