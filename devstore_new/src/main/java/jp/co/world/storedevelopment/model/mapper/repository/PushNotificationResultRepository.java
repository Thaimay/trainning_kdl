package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.PushNotificationResult;
import jp.co.world.storedevelopment.model.mapper.PushNotificationResultRepositoryMapper;

public class PushNotificationResultRepository extends Repository<PushNotificationResult, PushNotificationResultRepositoryMapper> {

    @Override
    protected PushNotificationResultRepositoryMapper repositoryMapper(SqlSession session) {
        return session.getMapper(PushNotificationResultRepositoryMapper.class);
    }

    @Override
    public String tableName() {
        return ActiveModelStringUtils.toTableName(PushNotificationResult.class);
    }

    public List<PushNotificationResult> findByReserveId(Long reserveId) {
        return execute((mapper) -> {
            return mapper.findByReserveId(reserveId);
        });
    }

}
