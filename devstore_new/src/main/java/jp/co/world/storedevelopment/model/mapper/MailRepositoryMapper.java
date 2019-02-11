package jp.co.world.storedevelopment.model.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.Mail;

public interface MailRepositoryMapper extends RepositoryMapper<Mail> {

    @Select("select * from mail where to_char(scheduled_datetime, 'YYYY-MM-DD HH24:00') = '${cuurentDateTime}'")
	List<Mail> findByScheduledDatetime(@Param("cuurentDateTime") LocalDateTime cuurentDateTime);

    @Select("select * from mail where is_sent is false")
	List<Mail> getMailList();

}
