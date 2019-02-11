package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.ArrayList;
import java.util.List;

public class NegotiationCreateFileDTO {
	private Long id;
	private String division;
	private String fileDivision;
	private List<Long> buildingIds = new ArrayList<Long>();
	private List<Long> shopIds = new ArrayList<Long>();
	private List<Long> projectIds = new ArrayList<Long>();
	private String comment = "";

	public NegotiationCreateFileDTO() {
	}

	public Boolean unregistered() {
		return getId() == null;
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

	public String getFileDivision() {
		return fileDivision;
	}

	public void setFileDivision(String fileDivision) {
		this.fileDivision = fileDivision;
	}

	public List<Long> getBuildingIds() {
		return buildingIds;
	}

	public void setBuildingIds(List<Long> buildingIds) {
		this.buildingIds = buildingIds;
	}

	public List<Long> getShopIds() {
		return shopIds;
	}

	public void setShopIds(List<Long> shopIds) {
		this.shopIds = shopIds;
	}

	public List<Long> getProjectIds() {
		return projectIds;
	}

	public void setProjectIds(List<Long> projectIds) {
		this.projectIds = projectIds;
	}
}
