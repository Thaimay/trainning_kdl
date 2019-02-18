package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.IShopCompany;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;

public interface IShopCompanyRepositoryMapper extends RepositoryMapper<IShopCompany> {
	@Select("select * from i_shop_company where company_name like '${iShopCompany.text}%' or company_name like '${iShopCompany.textHankaku}%' limit ${limit}")
	List<IShopCompany> findByText(@Param("iShopCompany") BuildingRelationFindByTextFormDTO dto,
			@Param("limit") int limit);
	
	@Select("select * from i_shop_company where is_deleted is false and company_id = '${code}'")
	IShopCompany findByImportCode(@Param("code") String companyId);

	@Select("select * from i_shop_company isc left join i_income_unit iiu on isc.company_id = iiu.company_id left join i_brand_income_unit ibi on ibi.income_unit_id = iiu.income_unit_id where ibi.brand_income_unit_name = '${iBrandIncomeUnitName}' Limit 1")
	IShopCompany findByName(@Param("iBrandIncomeUnitName") String iBrandIncomeUnitName);
}
