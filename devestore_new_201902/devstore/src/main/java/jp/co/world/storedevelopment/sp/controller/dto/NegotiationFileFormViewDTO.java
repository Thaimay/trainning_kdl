package jp.co.world.storedevelopment.sp.controller.dto;

import org.apache.commons.io.FilenameUtils;

import jp.co.world.storedevelopment.model.NegotiationFile;
import jp.co.world.storedevelopment.model.value.FileExtention;

public class NegotiationFileFormViewDTO {
	private Long id;

	private String division;

	private String comment = "";

	private String filePath;

	private String name;

	public NegotiationFileFormViewDTO(NegotiationFile file) {
		setId(file.getId());
		setDivision(file.getDivision());
		setComment(file.getComment());
		setPathImageValue(FilenameUtils.getExtension(file.getUrlPath()));
		setName(file.getName());
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
	
	public String getType() {
		return "DOCUMENT";
	}
}
