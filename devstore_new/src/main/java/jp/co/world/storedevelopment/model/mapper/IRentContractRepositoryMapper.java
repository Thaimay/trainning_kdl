package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.world.storedevelopment.model.IRentContract;

public interface IRentContractRepositoryMapper extends ShopRelatedRepositoryMapper<IRentContract> {
	@Select("select * from i_rent_contract where is_deleted is false and rent_contract_id = '${code}'")
	IRentContract findByImportCode(@Param("code") String rentContractId);
	
	@Select("select irc.* from i_rent_contract irc where irc.is_deleted = false and irc.shop_id::varchar in (select ish.shop_id::varchar from i_shop ish where ish.is_deleted = false and ish.shop_id = '${shopId}' and (ish.close_date is null or now() between ish.open_date and ish.close_date) union select ish.shop_town_parent_id from i_shop ish where ish.is_deleted = false and ish.shop_id = '${shopId}' and (ish.close_date is null or now() between ish.open_date and ish.close_date)) order by irc.start_date desc, irc.end_date desc, irc.id desc")
	List<IRentContract> findRentContractRefShop(@Param("shopId") Long shopId);
	
	@Select("select irc.contract_type_id from i_rent_contract irc where irc.is_deleted = false and irc.shop_id in (select ish.shop_id from i_shop ish where ish.is_deleted = false and (ish.close_date is null or now() between ish.open_date and ish.close_date) and ish.shop_id::varchar = '${shopTownParentId}') and irc.contract_type_id is not null order by irc.id desc limit 1")
	Long findParentContractTypeId(@Param("shopTownParentId") String shopTownParentId);
}
