package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.world.storedevelopment.model.ShopFile;

public interface ShopFileRepositoryMapper extends ShopRelatedRepositoryMapper<ShopFile> {
	@Select("select * from file f where f.shop_id in ( select id from shop where i_shop_id = #{shopId}) and f.is_deleted is false order by f.update_datetime desc")
	List<ShopFile> findShopFileRelatedProject(@Param("shopId") Long shopId);
}
