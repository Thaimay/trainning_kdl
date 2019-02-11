package jp.co.world.storedevelopment.model.mapper.repository;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.ProjectPastConference;
import jp.co.world.storedevelopment.model.mapper.ProjectPastConferenceRepositoryMapper;

public class ProjectPastConferenceRepository
		extends ProjectRelatedRepository<ProjectPastConference, ProjectPastConferenceRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ProjectPastConference.class);
	}

	@Override
	protected ProjectPastConferenceRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ProjectPastConferenceRepositoryMapper.class);
	}

}
