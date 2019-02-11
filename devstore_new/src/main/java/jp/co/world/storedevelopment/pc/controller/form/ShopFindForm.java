
package jp.co.world.storedevelopment.pc.controller.form;

import java.util.List;

import jp.co.world.storedevelopment.pc.controller.dto.FindFormDTO;

/**
 * @author thaipd
 *
 */
public class ShopFindForm extends FindFormDTO {

	public static final ShopFindForm EMPTY = new ShopFindForm();

	private List<Long> shopIds;
	private List<Long> iShopCompanyIds;
	private List<Long> iAreaIds;
	private String jsonCurrentShop = "[]";
	private String jsonCurrentShopCompany = "[]";
	private String jsonCurrentArea = "[]";
	private boolean isAdvancedSearch = false;

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

	public String getJsonCurrentShop() {
		return jsonCurrentShop;
	}

	public void setJsonCurrentShop(String jsonCurrentShop) {
		this.jsonCurrentShop = jsonCurrentShop;
	}

	public String getJsonCurrentShopCompany() {
		return jsonCurrentShopCompany;
	}

	public void setJsonCurrentShopCompany(String jsonCurrentShopCompany) {
		this.jsonCurrentShopCompany = jsonCurrentShopCompany;
	}

	public String getJsonCurrentArea() {
		return jsonCurrentArea;
	}

	public void setJsonCurrentArea(String jsonCurrentArea) {
		this.jsonCurrentArea = jsonCurrentArea;
	}

	public boolean isAdvancedSearch() {
		return isAdvancedSearch;
	}

	public void setAdvancedSearch(boolean isAdvancedSearch) {
		this.isAdvancedSearch = isAdvancedSearch;
	}

}
