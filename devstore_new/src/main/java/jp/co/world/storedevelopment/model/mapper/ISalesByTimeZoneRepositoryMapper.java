package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.ISalesByTimeZone;

public interface ISalesByTimeZoneRepositoryMapper extends RepositoryMapper<ISalesByTimeZone> {
	@Select("select * from i_sales_by_time_zone where is_deleted is false and disp_order = '${code}'")
	ISalesByTimeZone findByImportCode(@Param("code") long dispOrder);
}
