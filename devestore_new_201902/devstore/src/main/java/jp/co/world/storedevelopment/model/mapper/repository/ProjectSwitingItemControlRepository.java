package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.ProjectSwitingItemControl;
import jp.co.world.storedevelopment.model.mapper.ProjectSwitingItemControlRepositoryMapper;

public class ProjectSwitingItemControlRepository extends Repository<ProjectSwitingItemControl, ProjectSwitingItemControlRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ProjectSwitingItemControl.class);
	}

	@Override
	protected ProjectSwitingItemControlRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ProjectSwitingItemControlRepositoryMapper.class);
	}

	public Optional<ProjectSwitingItemControl> findByProjectCategoryId(Long projectCategoryId) {
		return execute((mapper) -> {
			ProjectSwitingItemControl projectSwitingItemControl = mapper.findByProjectCategoryId(projectCategoryId);
			return Optional.ofNullable(projectSwitingItemControl);
		});
	}

}
