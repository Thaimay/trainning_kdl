package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.PushNotificationPermit;
import jp.co.world.storedevelopment.model.mapper.PushNotificationPermitRepositoryMapper;

public class PushNotificationPermitRepository extends Repository<PushNotificationPermit, PushNotificationPermitRepositoryMapper> {

    @Override
    protected PushNotificationPermitRepositoryMapper repositoryMapper(SqlSession session) {
        return session.getMapper(PushNotificationPermitRepositoryMapper.class);
    }

    @Override
    public String tableName() {
        return ActiveModelStringUtils.toTableName(PushNotificationPermit.class);
    }

    public List<PushNotificationPermit> getPushNotificationPermitList() {
        return execute((mapper) -> {
            return mapper.getPushNotificationPermitList();
        });
    }

    public List<PushNotificationPermit> findByDeviceToken(String targetDeviceToken) {
        return execute((mapper) -> {
            return mapper.findByDeviceToken(targetDeviceToken);
        });
    }

}
