package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.List;

public class ShopFindFormDTO extends FindFormDTO {
	public static final ShopFindFormDTO EMPTY = new ShopFindFormDTO();

	private List<Long> shopIds;
	private List<Long> iShopCompanyIds;
	private List<Long> iAreaIds;

	public List<Long> getShopIds() {
		return shopIds;
	}

	public void setShopIds(List<Long> shopIds) {
		this.shopIds = shopIds;
	}

	public List<Long> getiShopCompanyIds() {
		return iShopCompanyIds;
	}

	public void setiShopCompanyIds(List<Long> iShopCompanyIds) {
		this.iShopCompanyIds = iShopCompanyIds;
	}

	public List<Long> getiAreaIds() {
		return iAreaIds;
	}

	public void setiAreaIds(List<Long> iAreaIds) {
		this.iAreaIds = iAreaIds;
	}

}
