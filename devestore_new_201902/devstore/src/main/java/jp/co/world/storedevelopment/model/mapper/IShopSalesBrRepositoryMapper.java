package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.IShopSalesBr;

public interface IShopSalesBrRepositoryMapper extends RepositoryMapper<IShopSalesBr> {
	@Select("select * from i_shop_sales_br where is_deleted is false and disp_order = '${code}'")
	IShopSalesBr findByImportCode(@Param("code") long dispOrder);
}
