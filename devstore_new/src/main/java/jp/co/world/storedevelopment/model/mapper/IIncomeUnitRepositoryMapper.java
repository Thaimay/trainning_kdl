package jp.co.world.storedevelopment.model.mapper;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.IIncomeUnit;

public interface IIncomeUnitRepositoryMapper extends RepositoryMapper<IIncomeUnit> {
	@Select("select * from i_income_unit where is_deleted is false and income_unit_id = '${incomeUnitId}'")
	IIncomeUnit findByIncomeUnitId(@Param("incomeUnitId") Long incomeUnitId);

	@Select("select * from i_income_unit where is_deleted is false and income_unit_id = ${id}")
	IIncomeUnit findByImportCode(@Param("id") BigDecimal incomeUnitId);
}
