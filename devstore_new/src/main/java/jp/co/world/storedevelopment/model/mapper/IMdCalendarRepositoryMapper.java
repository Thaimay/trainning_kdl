package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.IMdCalendar;

public interface IMdCalendarRepositoryMapper extends RepositoryMapper<IMdCalendar> {
	@Select("select * from i_md_calendar where is_deleted is false and seqno = '${code}'")
	IMdCalendar findByImportCode(@Param("code") String seqno);
}
