package jp.co.world.storedevelopment.sp.controller.dto;

import com.world.storedevelopment.utils.ZenkakuUtils;

public class BuildingRelationDTO {

	private Long id;

	private String name = "";

	private Long idParent;

	public BuildingRelationDTO() {
		//
	}

	public BuildingRelationDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public BuildingRelationDTO(Long id, String name, Long idParent) {
		this.id = id;
		this.name = name;
		this.idParent = idParent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return ZenkakuUtils.toZenkaku(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getIdParent() {
		return idParent;
	}

	public void setIdParent(Long idParent) {
		this.idParent = idParent;
	}

}
