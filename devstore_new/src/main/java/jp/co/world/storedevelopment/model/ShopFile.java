package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.web.multipart.MultipartFile;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ShopFileModelMapper;
import jp.co.world.storedevelopment.model.value.FileExtention;

public class ShopFile extends File<ShopFile> {

	public ShopFile() {
	}

	@Override
	public String filePrefix() {
		return "shop_";
	}

	public ShopFile(MultipartFile file, Shop shop, Account account) {
		super(file, account);
		setShopId(shop.getId());
	}

	public ShopFile(MultipartFile file, Long shopId, Account account) {
		super(file, account);
		setShopId(shopId);
	}

	@Override
	protected ModelMapper<ShopFile> modelMapper(SqlSession session) {
		return session.getMapper(ShopFileModelMapper.class);
	}

	@Override
	public String basePath() {
		return DOCUMENT_FILE_PATH;
	}

	@Override
	public String urlPath() {
		return "document/";
	}

	@Override
	public Boolean isImage() {
		return FileExtention.isImage(getName());
	}

	@Override
	public Boolean isDocument() {
		return FileExtention.isDocument(getName());
	}

}
