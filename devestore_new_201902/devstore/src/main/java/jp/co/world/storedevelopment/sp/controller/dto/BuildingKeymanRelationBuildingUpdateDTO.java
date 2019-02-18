package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.BuildingKeyman;
import jp.co.world.storedevelopment.model.Keyman;

public class BuildingKeymanRelationBuildingUpdateDTO implements DTO<BuildingKeyman> {

	private Long id;
	private String buildingCd;
	private Long keymanId;
	private String category = "HQ leasing";
	private String priority;
	private String role;
	private String note;
	private Boolean isDeleted = false;

	private Keyman keyman;

	@Override
	public BuildingKeyman createModel() {
		return new BuildingKeyman();
	}

	public BuildingKeymanRelationBuildingUpdateDTO() {

	}

	public BuildingKeymanRelationBuildingUpdateDTO(BuildingKeyman buildingKeyman) {
		this.copyProperties(this, buildingKeyman);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBuildingCd() {
		return buildingCd;
	}

	public void setBuildingCd(String buildingCd) {
		this.buildingCd = buildingCd;
	}

	public Long getKeymanId() {
		return keymanId;
	}

	public void setKeymanId(Long keymanId) {
		this.keymanId = keymanId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Keyman getKeyman() {
		return keyman;
	}

	public void setKeyman(Keyman keyman) {
		this.keyman = keyman;
	}

}
