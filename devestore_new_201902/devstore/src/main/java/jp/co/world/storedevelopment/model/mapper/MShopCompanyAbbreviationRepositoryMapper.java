package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.MShopCompanyAbbreviation;

public interface MShopCompanyAbbreviationRepositoryMapper extends RepositoryMapper<MShopCompanyAbbreviation> {
	@Select("select * from m_shop_company_abbreviation msc "
			+ "left join i_shop_company isc on msc.company_cd = isc.company_cd "
			+ "left join i_income_unit iiu on isc.company_id = iiu.company_id "
			+ "left join i_brand_income_unit ibi on ibi.income_unit_id = iiu.income_unit_id "
			+ "where ibi.brand_income_unit_name = '${iBrandIncomeUnitName}' limit 1")
	MShopCompanyAbbreviation findByName(@Param("iBrandIncomeUnitName") String iBrandIncomeUnitName);
}
