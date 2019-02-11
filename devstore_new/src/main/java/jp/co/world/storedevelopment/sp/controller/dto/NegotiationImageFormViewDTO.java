package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.NegotiationImage;

public class NegotiationImageFormViewDTO {
	private Long id;

	private String division;

	private String comment = "";

	private String filePath;

	private String name;

	public NegotiationImageFormViewDTO(NegotiationImage image) {
		setId(image.getId());
		setDivision(image.getDivision());
		setComment(image.getComment());
		setFilePath(image.getUrlPath());
		setName(image.getName());
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
	
	public String getType() {
		return "IMAGE";
	}

}
