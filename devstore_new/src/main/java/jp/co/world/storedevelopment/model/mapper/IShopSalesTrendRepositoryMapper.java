package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.world.storedevelopment.model.IShopSalesTrend;

public interface IShopSalesTrendRepositoryMapper extends BuildingRelatedRepositoryMapper<IShopSalesTrend> {
	@Select("select * from i_shop_sales_trend where is_deleted is false and disp_order = '${code}'")
	IShopSalesTrend findByImportCode(@Param("code") long dispOrder);
}
