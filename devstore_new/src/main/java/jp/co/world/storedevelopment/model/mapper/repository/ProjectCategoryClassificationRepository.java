package jp.co.world.storedevelopment.model.mapper.repository;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.ProjectCategoryClassification;
import jp.co.world.storedevelopment.model.mapper.ProjectCategoryClassificationRepositoryMapper;

public class ProjectCategoryClassificationRepository extends Repository<ProjectCategoryClassification, ProjectCategoryClassificationRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ProjectCategoryClassification.class);
	}

	@Override
	protected ProjectCategoryClassificationRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ProjectCategoryClassificationRepositoryMapper.class);
	}

}
