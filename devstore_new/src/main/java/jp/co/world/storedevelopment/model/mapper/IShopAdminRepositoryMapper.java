package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.IShopAdmin;

public interface IShopAdminRepositoryMapper extends RepositoryMapper<IShopAdmin> {

	@Select("select * from i_shop_admin where is_deleted is false and shop_admin_id = ${shopAdminId}")
	IShopAdmin findByImportCode(@Param("shopAdminId") Long shopAdminId);
	
	@Select("select * from i_shop_admin where is_deleted is false and shop_id = ${shopId}")
	IShopAdmin findByShopId(@Param("shopId") Long shopId);
}
