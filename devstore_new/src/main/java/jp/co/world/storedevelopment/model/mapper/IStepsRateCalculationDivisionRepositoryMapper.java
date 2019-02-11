package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.IStepsRateCalculationDivision;

public interface IStepsRateCalculationDivisionRepositoryMapper extends RepositoryMapper<IStepsRateCalculationDivision> {
	@Select("select * from i_steps_rate_calculation_division where is_deleted is false and steps_rate_calculation_division_cd = '${code}'")
	IStepsRateCalculationDivision findByImportCode(@Param("code") String stepsRateCalculationDivisionCd);
}
