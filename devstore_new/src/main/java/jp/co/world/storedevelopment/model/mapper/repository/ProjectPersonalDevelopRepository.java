package jp.co.world.storedevelopment.model.mapper.repository;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.ProjectPersonalDevelop;
import jp.co.world.storedevelopment.model.mapper.ProjectPersonalDevelopRepositoryMapper;

public class ProjectPersonalDevelopRepository
		extends ProjectRelatedRepository<ProjectPersonalDevelop, ProjectPersonalDevelopRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ProjectPersonalDevelop.class);
	}

	@Override
	protected ProjectPersonalDevelopRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ProjectPersonalDevelopRepositoryMapper.class);
	}

}
