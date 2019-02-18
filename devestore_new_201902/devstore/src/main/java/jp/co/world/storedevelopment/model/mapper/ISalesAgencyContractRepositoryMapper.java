package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.ISalesAgencyContract;

public interface ISalesAgencyContractRepositoryMapper extends RepositoryMapper<ISalesAgencyContract> {
	@Select("select * from i_sales_agency_contract where is_deleted is false and sales_agency_contract_id = '${code}'")
	ISalesAgencyContract findByImportCode(@Param("code") String salesAgencyContractId);

	@Select("select * from i_sales_agency_contract where is_deleted is false and shop_id = '${id}'")
	ISalesAgencyContract findByShopId(@Param("id") Long shopId);

	@Select("select isac.* from i_sales_agency_contract isac where isac.is_deleted = false and isac.shop_id::varchar in (select ish.shop_id::varchar from i_shop ish where ish.is_deleted = false and ish.shop_id = '${shopId}' and (ish.close_date is null or now() between ish.open_date and ish.close_date) union select ish.shop_town_parent_id from i_shop ish where ish.is_deleted = false and ish.shop_id = '${shopId}' and (ish.close_date is null or now() between ish.open_date and ish.close_date)) order by isac.start_date desc, isac.end_date desc, isac.id desc")
	List<ISalesAgencyContract> findSalesAgencyContractRefShop(@Param("shopId") Long shopId);
	
	@Select("select isac.sales_agency_target_id from i_sales_agency_contract isac where isac.is_deleted = false and isac.shop_id in (select ish.shop_id from i_shop ish where ish.is_deleted = false and (ish.close_date is null or now() between ish.open_date and ish.close_date) and ish.shop_id::varchar = '${shopTownParentId}') and isac.sales_agency_target_id is not null order by isac.id desc limit 1")
	Long findParentSalesAgencyTargetId(@Param("shopTownParentId") String shopTownParentId);
}
