package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.ISalesAgencyTarget;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;

public interface ISalesAgencyTargetRepositoryMapper extends RepositoryMapper<ISalesAgencyTarget> {

	@Select("select * from i_sales_agency_target where sales_agency_target_name like '%${iSalesAgencyTarget.text}%' or sales_agency_target_name like '%${iSalesAgencyTarget.textHankaku}%' limit ${limit}")
	List<ISalesAgencyTarget> findByText(@Param("iSalesAgencyTarget") BuildingRelationFindByTextFormDTO dto, @Param("limit") int limit);

	@Select("select * from i_sales_agency_target where is_deleted is false and sales_agency_target_id = '${code}'")
	ISalesAgencyTarget findByImportCode(@Param("code") String salesAgencyTargetId);
}
