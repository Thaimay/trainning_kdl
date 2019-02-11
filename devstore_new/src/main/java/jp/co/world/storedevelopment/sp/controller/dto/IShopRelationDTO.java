package jp.co.world.storedevelopment.sp.controller.dto;

import com.world.storedevelopment.utils.ZenkakuUtils;

public class IShopRelationDTO {

	private Long id;

	private String name = "";

	private String rawName = "";

	private String shopCd = "";

	public IShopRelationDTO() {
		//
	}

	public IShopRelationDTO(Long id, String name, String shopCd) {
		this.id = id;
		this.name = name;
		this.shopCd = shopCd;
		this.rawName = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		String shopName = this.shopCd + ' ' +this.name;
		return ZenkakuUtils.toZenkaku(shopName);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShopCd() {
		return shopCd;
	}

	public void setShopCd(String shopCd) {
		this.shopCd = shopCd;
	}

	public String getRawName() {
		return rawName;
	}

	public void setRawName(String rawName) {
		this.rawName = rawName;
	}

}
