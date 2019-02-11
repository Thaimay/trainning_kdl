package jp.co.world.storedevelopment.model.mapper.repository;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.BuildingMeetingHistory;
import jp.co.world.storedevelopment.model.mapper.BuildingMeetingHistoryRepositoryMapper;

public class BuildingMeetingHistoryRepository
		extends BuildingRelatedRepository<BuildingMeetingHistory, BuildingMeetingHistoryRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(BuildingMeetingHistory.class);
	}

	@Override
	protected BuildingMeetingHistoryRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(BuildingMeetingHistoryRepositoryMapper.class);
	}

}
