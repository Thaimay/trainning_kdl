package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.world.storedevelopment.model.ICompetitionSales;

public interface ICompetitionSalesRepositoryMapper extends BuildingRelatedRepositoryMapper<ICompetitionSales> {
	@Select("select * from i_competition_sales where building_cd = '${buildingCd}' and year_month >= '${yearMonthFrom}' and year_month <= '${yearMonthTo}' and is_deleted is false")
	List<ICompetitionSales> findByBuildingCdFromTo(@Param("buildingCd") String buildingCd,
			@Param("yearMonthFrom") int yearMonthFrom, @Param("yearMonthTo") int yearMonthTo);

	@Select("select * from i_competition_sales where is_deleted is false and disp_order = ${dispOrder}")
	ICompetitionSales findByImportCode(@Param("dispOrder") int dispOrder);
}
