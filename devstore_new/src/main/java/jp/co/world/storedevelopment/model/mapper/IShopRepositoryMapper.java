package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.world.storedevelopment.model.IShop;

public interface IShopRepositoryMapper extends ShopRelatedRepositoryMapper<IShop> {

	@Select("select * from i_shop where is_deleted = false and shop_name_zenkaku_hankaku like '%${text}%' or shop_name_zenkaku_hankaku like '%${hankaku}%' limit ${limit}")
	List<IShop> findByText(@Param("text") String text, @Param("hankaku") String hankaku, @Param("limit") int limit);

	@Select("select ish.* from i_shop ish inner join i_shop_admin isha on isha.shop_id = ish.shop_id inner join m_shop_type mst on mst.id = isha.shop_type_id where ish.open_date <= now() and (ish.close_date >= now() or ish.close_date is null) and (concat(ish.shop_cd,' ',ish.shop_name_zenkaku) like '%${shop}%' or concat(ish.shop_cd,' ',ish.shop_name_zenkaku) like '%${hankaku}%') order by ish.shop_name_zenkaku limit ${limit}")
	List<IShop> findByString(@Param("shop") String text, @Param("hankaku") String hankaku, @Param("limit") int limit);

	@Select("select * from I_SHOP where is_deleted = false")
	List<IShop> findNotDeleted();

	@Select("select * from i_Shop where place_id = ${placeId}")
	IShop findByPlaceId(@Param("placeId") Long placeId);

	@Select("select * from i_shop where is_deleted is false and shop_id = '${shopId}'")
	IShop findByImportId(@Param("shopId") Long shopId);

	@Select("select * from I_SHOP where shop_cd like '${shop}%' limit ${limit}")
	List<IShop> findShopCdByString(@Param("shop") String text, @Param("limit") int limit);
}
