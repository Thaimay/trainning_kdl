package jp.co.world.storedevelopment.sp.controller.dto;

import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FilenameUtils;

import jp.co.world.storedevelopment.model.File;
import jp.co.world.storedevelopment.model.value.FileExtention;

public class NegotiationFileDTO {

	private String name;

	private String comment;

	private String accountName;

	private String path;

	private String pathImage;

	private String size;

	private String byTheTime;

	private String updateDatetime;

	public NegotiationFileDTO(File f) {
		setName(f.getDisplayName());
		setComment(f.getComment());
		setAccountName(f.getAccountName());
		setPath(f.getUrlPath());
		setSize(f.getReadableSize());
		setByTheTime(f.calcByTheTime().toString());
		setPathImageValue(FilenameUtils.getExtension(f.getUrlPath()));
		setUpdateDatetime(f.getUpdateDatetime().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")));
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

	public String getPathImage() {
		return pathImage;
	}

	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}

	private void setPathImageValue(String extension) {
		if (extension.toUpperCase().equals(FileExtention.DOCX.toString())) {
			setPathImage("img/docx.jpg");
		} else if (extension.toUpperCase().equals(FileExtention.XLSX.toString())) {
			setPathImage("img/xlsx.jpg");
		} else if (extension.toUpperCase().equals(FileExtention.PPTX.toString())) {
			setPathImage("img/pptx.jpg");
		} else {
			setPathImage("img/pdf.jpg");
		}
	}

	public String getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(String updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

}
