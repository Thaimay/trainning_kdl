package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.ProjectCategory;
import jp.co.world.storedevelopment.model.mapper.ProjectCategoryRepositoryMapper;

public class ProjectCategoryRepository extends Repository<ProjectCategory, ProjectCategoryRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ProjectCategory.class);
	}

	@Override
	protected ProjectCategoryRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ProjectCategoryRepositoryMapper.class);
	}

	public List<ProjectCategory> getAllBySort() {
		return execute((mapper) -> {
			return mapper.getAllBySort();
		});
	}
}
