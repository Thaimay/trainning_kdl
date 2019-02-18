package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.MPeriod;
import jp.co.world.storedevelopment.sp.controller.dto.PeriodSearchListDTO;

public interface MPeriodRepositoryMapper extends RepositoryMapper<MPeriod> {
	@Select("select * from m_period where to_date('${date}','YYYY-MM-DD') between start_date and end_date")
	MPeriod getPeriod(@Param("date") String date);

	@Select("select * from m_period where period = ${period}")
	MPeriod findByPeriod(@Param("period") Long period);

	@Select("select id, up_down from ("
			+ "select distinct mpe.period as id, case when p.implementation_datetime between mpe.start_date and mpe.first_half_end_date then '上期' else '下期' end as up_down, case when p.implementation_datetime between mpe.start_date and mpe.first_half_end_date then 1 else 2 end as up_down_order_by from project p left join m_period mpe on p.implementation_datetime between mpe.start_date and mpe.end_date "
			+ " where operation_division = '進行中' and implementation_datetime is not null  and implementation_datetime is not null and implementation_datetime >= now()"
			+ " union "
			+ "select distinct mpe.period as id, case when p.implementation_schedule_datetime between mpe.start_date and mpe.first_half_end_date then '上期' else '下期' end as up_down, case when p.implementation_schedule_datetime between mpe.start_date and mpe.first_half_end_date then 1 else 2 end as up_down_order_by from project p left join m_period mpe on p.implementation_schedule_datetime between mpe.start_date and mpe.end_date "
			+ "where operation_division = '進行中' and implementation_schedule_datetime is not null and implementation_schedule_datetime is not null and implementation_schedule_datetime >= now()"
			+ ") x order by id, up_down_order_by")
	List<PeriodSearchListDTO> getCurrentImplementationPeriod();
}
