package jp.co.world.storedevelopment.model.mapper.repository;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.ProjectTaskOwner;
import jp.co.world.storedevelopment.model.mapper.ProjectTaskOwnerRepositoryMapper;

public class ProjectTaskOwnerRepository extends Repository<ProjectTaskOwner, ProjectTaskOwnerRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ProjectTaskOwner.class);
	}

	@Override
	protected ProjectTaskOwnerRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ProjectTaskOwnerRepositoryMapper.class);
	}

}
