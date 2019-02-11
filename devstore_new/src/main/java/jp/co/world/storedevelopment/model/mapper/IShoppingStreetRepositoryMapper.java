package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.IShoppingStreet;

public interface IShoppingStreetRepositoryMapper extends RepositoryMapper<IShoppingStreet> {
	@Select("select * from i_shopping_street where is_deleted is false and shopping_street_id = ${shoppingStreetId}")
	IShoppingStreet findByImportCode(@Param("shoppingStreetId") Long shoppingStreetId);
}