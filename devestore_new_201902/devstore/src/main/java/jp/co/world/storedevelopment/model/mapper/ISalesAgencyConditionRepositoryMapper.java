package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.ISalesAgencyCondition;

public interface ISalesAgencyConditionRepositoryMapper extends RepositoryMapper<ISalesAgencyCondition> {
	@Select("select * from i_sales_agency_condition where is_deleted is false and sales_agency_condition_id = '${code}'")
	ISalesAgencyCondition findByImportCode(@Param("code") String salesAgencyConditionId);

	@Select("select * from i_sales_agency_condition where is_deleted is false and shop_id = '${id}'")
	ISalesAgencyCondition findByShopId(@Param("id") Long shopId);
	
	@Select("select isac.* from i_sales_agency_condition isac where isac.is_deleted = false and isac.shop_id::varchar in (select ish.shop_id::varchar from i_shop ish where ish.is_deleted = false and ish.shop_id = '${shopId}' and (ish.close_date is null or now() between ish.open_date and ish.close_date) union select ish.shop_town_parent_id from i_shop ish where ish.is_deleted = false and ish.shop_id = '${shopId}' and (ish.close_date is null or now() between ish.open_date and ish.close_date)) order by isac.start_date desc, isac.end_date desc, isac.id desc")
	List<ISalesAgencyCondition> findSalesAgencyConditionRefShop(@Param("shopId") Long shopId);
}
