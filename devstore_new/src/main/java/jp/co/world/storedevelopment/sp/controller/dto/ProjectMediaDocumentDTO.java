package jp.co.world.storedevelopment.sp.controller.dto;

import org.springframework.web.multipart.MultipartFile;

public class ProjectMediaDocumentDTO {
	private Long id;
	private String division = "";
	private String comment = "";
	private String displayName;
	private String originFileName;
	private String outputFlag;
	private Integer outputNumber;
	private Boolean isDeleted;
	private MultipartFile file;
	private String path;
	private Boolean addFileReference = false;
	private String urlPath;

	public ProjectMediaDocumentDTO() {
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
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

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Boolean getAddFileReference() {
		return addFileReference;
	}

	public void setAddFileReference(Boolean addFileReference) {
		this.addFileReference = addFileReference;
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

}
