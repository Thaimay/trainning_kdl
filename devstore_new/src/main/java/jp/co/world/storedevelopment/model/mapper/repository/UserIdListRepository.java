package jp.co.world.storedevelopment.model.mapper.repository;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.UserIdList;
import jp.co.world.storedevelopment.model.mapper.UserIdListRepositoryMapper;

public class UserIdListRepository extends Repository<UserIdList, UserIdListRepositoryMapper> {

    @Override
    protected UserIdListRepositoryMapper repositoryMapper(SqlSession session) {
        return session.getMapper(UserIdListRepositoryMapper.class);
    }

    @Override
    public String tableName() {
        return ActiveModelStringUtils.toTableName(UserIdList.class);
    }

    public UserIdList findByReserveId(Long id) {
        return execute((mapper) -> {
            return mapper.findByReserveId(id);
        });
    }
}
