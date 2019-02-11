package jp.co.world.storedevelopment.sp.controller.dto;

import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.BuildingKeyman;
import jp.co.world.storedevelopment.model.Keyman;
import jp.co.world.storedevelopment.model.mapper.repository.KeymanRepository;

public class BuildingKeymanRelationBuildingDetailDTO implements DTO<BuildingKeyman> {

	private Long id;
	private Long keymanId;
	private String category;
	private String priority;
	private String role;
	private String note;
	private Boolean isDeleted;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime updateDatetime;

	public KeymanRelationBuildingDetailDTO getKeyman() {
		if (getKeymanId() == null) {
			return null;
		}

		Optional<Keyman> keyman = new KeymanRepository().findById(getKeymanId());
		return keyman.isPresent() ? new KeymanRelationBuildingDetailDTO(keyman.get()) : null;
	}

	@Override
	public BuildingKeyman createModel() {
		return new BuildingKeyman();
	}

	public BuildingKeymanRelationBuildingDetailDTO() {

	}

	public BuildingKeymanRelationBuildingDetailDTO(BuildingKeyman buildingKeyman) {
		copyProperties(this, buildingKeyman);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public LocalDateTime getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(LocalDateTime updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
