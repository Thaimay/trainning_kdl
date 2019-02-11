package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.MProjectMeetingResult;
import jp.co.world.storedevelopment.model.mapper.MProjectMeetingResultRepositoryMapper;

public class MProjectMeetingResultRepository extends Repository<MProjectMeetingResult, MProjectMeetingResultRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(MProjectMeetingResult.class);
	}

	@Override
	protected MProjectMeetingResultRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(MProjectMeetingResultRepositoryMapper.class);
	}

	public List<MProjectMeetingResult> getMProjectMeetingResultList() {
		return execute((mapper) -> {
			List<MProjectMeetingResult> mProjectMeetingResultList = mapper.getMProjectMeetingResultList();
			return mProjectMeetingResultList;
		});
	}
}
