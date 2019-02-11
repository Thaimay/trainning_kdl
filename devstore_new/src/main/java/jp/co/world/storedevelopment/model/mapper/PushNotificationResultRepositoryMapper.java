package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.PushNotificationResult;

public interface PushNotificationResultRepositoryMapper extends RepositoryMapper<PushNotificationResult> {

    @Select("SELECT * FROM push_notification_result WHERE reserve_id = '${reserveID}'")
    List<PushNotificationResult> findByReserveId(@Param("reserveId") Long reserveId);

}
