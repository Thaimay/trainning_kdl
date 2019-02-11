package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.IFixedAssetsDepreciationDivision;

public interface IFixedAssetsDepreciationDivisionRepositoryMapper
		extends RepositoryMapper<IFixedAssetsDepreciationDivision> {
	@Select("select * from i_fixed_assets_depreciation_division where is_deleted is false and steps_rate_calculation_division_cd = '${code}'")
	IFixedAssetsDepreciationDivision findByImportCode(@Param("code") String stepsRateCalculationDivisionCd);
}
