package jp.co.world.storedevelopment.model.mapper;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.Brand;

public interface BrandModelMapper extends ModelMapper<Brand> {

	static String tableName = "brand";
}
