package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.BuildingKeymanModelMapper;

/**
 * @author tainm
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class BuildingKeyman extends ActiveModel<BuildingKeyman> {

	private String buildingCd;
	private Long keymanId;
	private String category = "";
	private String priority = "";
	private String role = "";
	private String note = "";

	public BuildingKeyman() {
	}

	public BuildingKeyman(Building building, Keyman keyman) {
		buildingCd = building.getBuildingCd();
		keymanId = keyman.getId();
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

	@Override
	protected ModelMapper<BuildingKeyman> modelMapper(SqlSession session) {
		return session.getMapper(BuildingKeymanModelMapper.class);
	}

}