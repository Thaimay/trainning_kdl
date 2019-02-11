package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.IIncomeUnitLayer;

public interface IIncomeUnitLayerRepositoryMapper extends RepositoryMapper<IIncomeUnitLayer> {
	@Select("select * from i_income_unit_layer where is_deleted is false and income_unit_id = '${code}'")
	IIncomeUnitLayer findByImportCode(@Param("code") String incomeUnitId);
}
