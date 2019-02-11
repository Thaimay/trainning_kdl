package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.InvalidDeviceToken;
import jp.co.world.storedevelopment.model.mapper.InvalidDeviceTokenRepositoryMapper;

public class InvalidDeviceTokenRepository  extends Repository<InvalidDeviceToken, InvalidDeviceTokenRepositoryMapper> {

    @Override
    protected InvalidDeviceTokenRepositoryMapper repositoryMapper(SqlSession session) {
        return session.getMapper(InvalidDeviceTokenRepositoryMapper.class);
    }

    @Override
    public String tableName() {
        return ActiveModelStringUtils.toTableName(InvalidDeviceToken.class);
    }

    public List<InvalidDeviceToken> getScreeningTargetList() {
        return execute((mapper) -> {
            return mapper.getScreeningTargetList();
        });
    }

}
