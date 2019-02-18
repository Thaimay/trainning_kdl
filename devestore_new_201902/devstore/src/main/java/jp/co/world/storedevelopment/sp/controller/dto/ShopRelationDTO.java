package jp.co.world.storedevelopment.sp.controller.dto;

import com.world.storedevelopment.utils.ZenkakuUtils;

public class ShopRelationDTO {

	private Long id;

	private String name = "";

	private String nameZenkaku;
	public ShopRelationDTO() {
		//
	}

	public ShopRelationDTO(Long id, String name, String nameZenkaku) {
		this.id = id;
		this.name = name;
		this.nameZenkaku = nameZenkaku;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameZenkaku() {
		return ZenkakuUtils.toZenkaku(nameZenkaku);
	}

	public void setNameZenkaku(String nameZenkaku) {
		this.nameZenkaku = nameZenkaku;
	}
}
