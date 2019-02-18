package jp.co.world.storedevelopment.sp.controller.dto;

import java.io.File;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.Shop;

public class ShopListDTO implements DTO<Shop> {

	private Long id;
	private Long iShopId;
	private Integer buildingExpectedValue;
	private String imagePath;

	public ShopListDTO() {

	}

	public ShopListDTO(Shop shop) {
		copyProperties(this, shop);
		
		File file = new File(Application.resourcePath() + shop.getImagePath());
		if (file.exists()) {
			this.setImagePath(shop.getImagePath());
		} else {
			this.setImagePath("img/no_img.jpg");
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getiShopId() {
		return iShopId;
	}

	public void setiShopId(Long iShopId) {
		this.iShopId = iShopId;
	}

	public Integer getBuildingExpectedValue() {
		return buildingExpectedValue;
	}

	public void setBuildingExpectedValue(Integer buildingExpectedValue) {
		this.buildingExpectedValue = buildingExpectedValue;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	public Shop createModel() {
		return new Shop();
	}

}
