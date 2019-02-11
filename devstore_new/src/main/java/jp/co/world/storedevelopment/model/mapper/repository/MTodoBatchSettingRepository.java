package jp.co.world.storedevelopment.model.mapper.repository;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.MTodoBatchSetting;
import jp.co.world.storedevelopment.model.mapper.MTodoBatchSettingRepositoryMapper;

public class MTodoBatchSettingRepository extends Repository<MTodoBatchSetting, MTodoBatchSettingRepositoryMapper> {

    @Override
    protected MTodoBatchSettingRepositoryMapper repositoryMapper(SqlSession session) {
        return session.getMapper(MTodoBatchSettingRepositoryMapper.class);
    }

    @Override
    public String tableName() {
        return ActiveModelStringUtils.toTableName(MTodoBatchSetting.class);
    }

	public MTodoBatchSetting findByName(String name) {
		return execute((mapper) -> {
			return mapper.findByName(name);
		});
	}

}
