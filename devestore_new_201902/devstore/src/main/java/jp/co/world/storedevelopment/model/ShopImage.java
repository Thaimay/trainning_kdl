package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.web.multipart.MultipartFile;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ShopImageModelMapper;
import jp.co.world.storedevelopment.model.value.FileExtention;

public class ShopImage extends File<ShopImage> {

	private Long shopId;
	private Boolean isDefaultImage;

	public ShopImage() {
	}

	public ShopImage(MultipartFile file, Shop shop, Account account) {
		super(file, account);
		setShopId(shop.getId());
	}

	public ShopImage(MultipartFile file, Long shopId, Account account) {
		super(file, account);
		setShopId(shopId);
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	@Override
	public String filePrefix() {
		return "shop_";
	}

	@Override
	public String basePath() {
		return File.IMAGE_FILE_PATH;
	}
	
	@Override
	public String urlPath() {
		return "image/";
	}

	@Override
	public Boolean isImage() {
		return FileExtention.isImage(getName());
	}

	@Override
	public Boolean isDocument() {
		return FileExtention.isDocument(getName());
	}

	@Override
	protected ModelMapper<ShopImage> modelMapper(SqlSession session) {
		return session.getMapper(ShopImageModelMapper.class);
	}

	public Boolean getIsDefaultImage() {
		return isDefaultImage;
	}

	public void setIsDefaultImage(Boolean isDefaultImage) {
		this.isDefaultImage = isDefaultImage;
	}
}
