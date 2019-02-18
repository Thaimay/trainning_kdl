package jp.co.world.storedevelopment.sp.controller.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.NegotiationFile;
import jp.co.world.storedevelopment.model.NegotiationImage;
import jp.co.world.storedevelopment.model.NegotiationVideo;
import jp.co.world.storedevelopment.model.value.FileExtention;

public class FileUploadViewDetailDTO {

	private String name;

	private String comment;

	private String accountName;

	private String path;

	private String size;

	private String byTheTime;

	private String type;

	private String pathDocument;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime updateDatetime;

	public FileUploadViewDetailDTO(NegotiationFile f) {
		setName(f.getDisplayName());
		setComment(f.getComment());
		setAccountName(f.getAccountName());
		setPath(f.getUrlPath());
		setSize(f.getReadableSize());
		setByTheTime(f.calcByTheTime().toString());
		setUpdateDatetime(f.getUpdateDatetime());
		setType("DOCUMENT");
		if (FilenameUtils.getExtension(f.getUrlPath()).toUpperCase().equals(FileExtention.DOCX.toString())) {
			setPathDocument("img/docx.jpg");
		} else if (FilenameUtils.getExtension(f.getUrlPath()).toUpperCase().equals(FileExtention.XLSX.toString())) {
			setPathDocument("img/xlsx.jpg");
		} else if (FilenameUtils.getExtension(f.getUrlPath()).toUpperCase().equals(FileExtention.PPTX.toString())) {
			setPathDocument("img/pptx.jpg");
		} else {
			setPathDocument("img/pdf.jpg");
		}
	}

	public FileUploadViewDetailDTO(NegotiationImage f) {
		setName(f.getDisplayName());
		setComment(f.getComment());
		setAccountName(f.getAccountName());
		setPath(f.getUrlPath());
		setSize(f.getReadableSize());
		setByTheTime(f.calcByTheTime().toString());
		setUpdateDatetime(f.getUpdateDatetime());
		setType("IMAGE");
	}

	public FileUploadViewDetailDTO(NegotiationVideo f) {
		setName(f.getDisplayName());
		setComment(f.getComment());
		setAccountName(f.getAccountName());
		setPath(f.getUrlPath());
		setSize(f.getReadableSize());
		setByTheTime(f.calcByTheTime().toString());
		setUpdateDatetime(f.getUpdateDatetime());
		setType("VIDEO");
	}

	public String getName() {
		return name;
	}

	public void setName(String fileName) {
		name = fileName;
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

	public LocalDateTime getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(LocalDateTime updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public String getUpdateDatetimeValue() {
		if(updateDatetime != null) {
			return updateDatetime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
		}
		return StringUtils.EMPTY;
	}

	public String getPathDocument() {
		return pathDocument;
	}

	public void setPathDocument(String pathDocument) {
		this.pathDocument = pathDocument;
	}
}
