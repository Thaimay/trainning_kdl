package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.IBrandIncomeUnit;
import jp.co.world.storedevelopment.sp.controller.dto.IBrandIncomeUnitBusinessListDTO;

public interface IBrandIncomeUnitRepositoryMapper extends RepositoryMapper<IBrandIncomeUnit> {
	@Select("select * from i_brand_income_unit where is_deleted is false and brand_income_unit_cd = '${code}'")
	IBrandIncomeUnit findByImportCode(@Param("code") String brandIncomeUnitCd);

	@Select("select * from i_brand_income_unit where brand_income_unit_name like '%${text}%' or brand_income_unit_name like '%${hankaku}%' limit ${limit}")
	List<IBrandIncomeUnit> findByText(@Param("text") String text, @Param("hankaku") String hankaku,
			@Param("limit") int limit);

	@Select("select * from i_brand_income_unit ib left join i_shop ishop on ib.income_unit_id = ishop.i_income_unit_id where ishop.i_income_unit_id is not null and ishop.id = '${shopId}'")
	List<IBrandIncomeUnit> findByShopId(@Param("shopId") Long id);
	
	@Select("select ib.* from i_brand_income_unit ib inner join i_shop_admin isa on ib.brand_income_unit_id = isa.brand_income_unit_id inner join i_shop ish on ish.shop_id = isa.shop_id where ish.id = '${shopId}'")
	List<IBrandIncomeUnit> findFromShopAdmin(@Param("shopId") Long id);
	
	@Select("select ib.id brand_id, brand_income_unit_name brand_name, isc.id company_id, isc.company_name from i_brand_income_unit ib left join i_income_unit ii on ii.income_unit_id = ib.income_unit_id left join i_shop_company isc on isc.company_id = ii.company_id where isc.company_id is not null order by ib.id")
	List<IBrandIncomeUnitBusinessListDTO> getBrandBusinessList();
}
