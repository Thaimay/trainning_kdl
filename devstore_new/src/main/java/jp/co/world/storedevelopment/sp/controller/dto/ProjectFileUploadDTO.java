package jp.co.world.storedevelopment.sp.controller.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.FileDivision;
import jp.co.world.storedevelopment.model.ProjectFile;
import jp.co.world.storedevelopment.model.ProjectImage;
import jp.co.world.storedevelopment.model.ProjectVideo;
import jp.co.world.storedevelopment.model.mapper.repository.FileDivisionRepository;
import jp.co.world.storedevelopment.model.value.FileExtention;

public class ProjectFileUploadDTO {

	private Long id;
	private String displayName;
	private String originFileName;
	private String comment;
	private String accountName;
	private String path;
	private String pathDocument;
	private String size;
	private String byTheTime;
	private String type;
	private String division;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime createdDatetime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime updateDatetime;
	private String outputFlag;
	private Integer outputNumber;
	private Boolean isDeleted;

	public ProjectFileUploadDTO(ProjectFile f) {
		setId(f.getId());
		setDisplayName(f.getDisplayName());
		setOriginFileName(f.getName());
		setComment(f.getComment());
		setAccountName(f.getAccountName());
		if (FilenameUtils.getExtension(f.getUrlPath()).toUpperCase().equals(FileExtention.DOCX.toString())) {
			setPathDocument("img/docx.jpg");
		} else if (FilenameUtils.getExtension(f.getUrlPath()).toUpperCase().equals(FileExtention.XLSX.toString())) {
			setPathDocument("img/xlsx.jpg");
		} else if (FilenameUtils.getExtension(f.getUrlPath()).toUpperCase().equals(FileExtention.PPTX.toString())) {
			setPathDocument("img/pptx.jpg");
		} else {
			setPathDocument("img/pdf.jpg");
		}
		setPath(f.getUrlPath());
		setSize(f.getReadableSize());
		setByTheTime(f.calcByTheTime().toString());
		setCreatedDatetime(f.getCreatedDatetime());
		setUpdateDatetime(f.getUpdateDatetime());
		setType("DOCUMENT");
		setDivision(f.getDivision());
		setOutputFlag(f.getOutputFlag());
		setOutputNumber(f.getOutputNumber());
		setIsDeleted(f.getIsDeleted());
	}

	public ProjectFileUploadDTO(ProjectImage f) {
		setId(f.getId());
		setDisplayName(f.getDisplayName());
		setOriginFileName(f.getName());
		setComment(f.getComment());
		setAccountName(f.getAccountName());
		setPath(f.getUrlPath());
		setSize(f.getReadableSize());
		setByTheTime(f.calcByTheTime().toString());
		setCreatedDatetime(f.getCreatedDatetime());
		setUpdateDatetime(f.getUpdateDatetime());
		setType("IMAGE");
		setDivision(f.getDivision());
		setOutputFlag(f.getOutputFlag());
		setOutputNumber(f.getOutputNumber());
		setIsDeleted(f.getIsDeleted());
	}

	public ProjectFileUploadDTO(ProjectVideo f) {
		setId(f.getId());
		setDisplayName(f.getDisplayName());
		setOriginFileName(f.getName());
		setComment(f.getComment());
		setAccountName(f.getAccountName());
		setPath(f.getUrlPath());
		setSize(f.getReadableSize());
		setByTheTime(f.calcByTheTime().toString());
		setCreatedDatetime(f.getCreatedDatetime());
		setUpdateDatetime(f.getUpdateDatetime());
		setType("VIDEO");
		setDivision(f.getDivision());
		setOutputFlag(f.getOutputFlag());
		setOutputNumber(f.getOutputNumber());
		setIsDeleted(f.getIsDeleted());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getOriginFileName() {
		return originFileName;
	}

	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accoutName) {
		this.accountName = accoutName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String url) {
		path = url;
	}

	public String getPathDocument() {
		return pathDocument;
	}

	public void setPathDocument(String pathDocument) {
		this.pathDocument = pathDocument;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getByTheTime() {
		return byTheTime;
	}

	public void setByTheTime(String byTheTime) {
		this.byTheTime = byTheTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
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

	public String getOutputFlag() {
		return outputFlag;
	}

	public void setOutputFlag(String outputFlag) {
		this.outputFlag = outputFlag;
	}

	public Integer getOutputNumber() {
		return outputNumber;
	}

	public void setOutputNumber(Integer outputNumber) {
		this.outputNumber = outputNumber;
	}

	public String getCreatedDatetimeValue() {
		if (createdDatetime != null) {
			return createdDatetime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
		}
		return StringUtils.EMPTY;
	}

	public String getUpdateDatetimeValue() {
		if (updateDatetime != null) {
			return updateDatetime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
		}
		return StringUtils.EMPTY;
	}

	public String getDivisionName() {
		if (!division.isEmpty()) {
			Optional<FileDivision> fileDivision = new FileDivisionRepository().getFileDivisionByCode(division);
			if (fileDivision.isPresent()) {
				return fileDivision.get().getDisplayName();
			}
		}
		return division;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
