package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.IShopSalesLatestOneYear;

public interface IShopSalesLatestOneYearRepositoryMapper
		extends BuildingRelatedRepositoryMapper<IShopSalesLatestOneYear> {

	@Select("select * from i_shop_sales_latest_one_year where shop_cd = '${shopCd}'")
	IShopSalesLatestOneYear findByShopCd(@Param("shopCd") String shopCd);

	@Select("select * from i_shop_sales_latest_one_year where is_deleted is false and disp_order = '${code}'")
	IShopSalesLatestOneYear findByImportCode(@Param("code") long dispOrder);
	
	@Select("select * from building where building_cd IN (select building_cd from i_shop_sales_latest_one_year where shop_cd = '${code}') limit 1")
	public Building findBy(@Param("code") String code);
}
