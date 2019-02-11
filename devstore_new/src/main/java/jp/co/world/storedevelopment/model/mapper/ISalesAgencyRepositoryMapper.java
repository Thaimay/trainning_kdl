package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.ISalesAgency;

public interface ISalesAgencyRepositoryMapper extends RepositoryMapper<ISalesAgency> {

	@Select("select * from i_sales_agency where is_deleted is false and sales_agency_cd = '${code}'")
	ISalesAgency findByImportCode(@Param("code") String salesAgencyCd);

	@Select("select * from i_sales_agency where is_deleted is false and sales_agency_id in (select sales_agency_id from i_shop where is_deleted is false and id = #{shopId})")
	ISalesAgency findByShopId(@Param("shopId") Long id);
}
