package jp.co.world.storedevelopment.sp.controller.dto;

import com.world.storedevelopment.utils.ZenkakuUtils;

public class KeyStringDTO {

	private String id;

	private String name = "";

	public KeyStringDTO() {
		//
	}

	public KeyStringDTO(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return ZenkakuUtils.toZenkaku(name);
	}

	public void setName(String name) {
		this.name = name;
	}

}
