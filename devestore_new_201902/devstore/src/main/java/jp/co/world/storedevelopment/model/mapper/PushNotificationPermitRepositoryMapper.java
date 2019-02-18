package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.PushNotificationPermit;

public interface PushNotificationPermitRepositoryMapper extends RepositoryMapper<PushNotificationPermit> {
    @Select("SELECT * FROM push_notification_permit where device_token IS NOT NULL OR device_token != ''")
    List<PushNotificationPermit> getPushNotificationPermitList();

    @Select("SELECT * FROM push_notification_permit where device_token = '{targetDeviceToken}' AND is_deleted IS false ORDER BY id DESC LIMIT 1")
    List<PushNotificationPermit> findByDeviceToken(@Param("targetDeviceToken") String targetDeviceToken);

}
