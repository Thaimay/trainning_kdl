package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.IShopType;

public interface IShopTypeRepositoryMapper extends RepositoryMapper<IShopType> {

	@Select("select * from i_shop_type where is_deleted is false and shop_type_id = ${shopTypeId}")
	IShopType findByImportCode(@Param("shopTypeId") Long shopTypeId);
}
