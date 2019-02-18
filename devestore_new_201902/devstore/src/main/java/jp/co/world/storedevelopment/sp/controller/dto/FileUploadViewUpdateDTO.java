package jp.co.world.storedevelopment.sp.controller.dto;

import java.time.LocalDateTime;

import org.apache.commons.io.FilenameUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.NegotiationFile;
import jp.co.world.storedevelopment.model.NegotiationImage;
import jp.co.world.storedevelopment.model.NegotiationVideo;
import jp.co.world.storedevelopment.model.value.FileExtention;

public class FileUploadViewUpdateDTO {
	private Long id;

	private String division;

	private String comment;

	private String filePath;

	private String name;

	private String fileDivision;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime updateDatetime;

	private String type;

	public FileUploadViewUpdateDTO(NegotiationFile file) {
		setId(file.getId());
		setDivision(file.getDivision());
		setComment(file.getComment());
		setPathImageValue(FilenameUtils.getExtension(file.getUrlPath()));
		setName(file.getName());
		setUpdateDatetime(file.getUpdateDatetime());
		setFileDivision(file.getFileDivision());
		setType("DOCUMENT");
	}

	public FileUploadViewUpdateDTO(NegotiationImage image) {
		setId(image.getId());
		setDivision(image.getDivision());
		setComment(image.getComment());
		setFilePath(image.getUrlPath());
		setName(image.getName());
		setUpdateDatetime(image.getUpdateDatetime());
		setFileDivision(image.getFileDivision());
		setType("IMAGE");
	}

	public FileUploadViewUpdateDTO(NegotiationVideo video) {
		setId(video.getId());
		setDivision(video.getDivision());
		setComment(video.getComment());
		setFilePath(video.getUrlPath());
		setName(video.getName());
		setUpdateDatetime(video.getUpdateDatetime());
		setFileDivision(video.getFileDivision());
		setType("VIDEO");
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(LocalDateTime updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private void setPathImageValue(String extension) {
		if (extension.toUpperCase().equals(FileExtention.DOCX.toString())) {
			setFilePath("img/docx.jpg");
		} else if (extension.toUpperCase().equals(FileExtention.XLSX.toString())) {
			setFilePath("img/xlsx.jpg");
		} else if (extension.toUpperCase().equals(FileExtention.PPTX.toString())) {
			setFilePath("img/pptx.jpg");
		} else {
			setFilePath("img/pdf.jpg");
		}
	}

	public String getFileDivision() {
		return fileDivision;
	}

	public void setFileDivision(String fileDivision) {
		this.fileDivision = fileDivision;
	}
}
