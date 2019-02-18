package jp.co.world.storedevelopment.sp.controller.dto;

import com.world.storedevelopment.utils.ZenkakuUtils;

public class NegotiationRelationDTO {

	private Long id;

	private String name = "";

	public NegotiationRelationDTO() {
		//
	}

	public NegotiationRelationDTO(Long id, String name) {
		this.id = id;
		this.name = name;
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

}
