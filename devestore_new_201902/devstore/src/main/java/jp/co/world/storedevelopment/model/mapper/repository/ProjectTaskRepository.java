package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.ProjectTask;
import jp.co.world.storedevelopment.model.mapper.ProjectTaskRepositoryMapper;

public class ProjectTaskRepository extends ProjectRelatedRepository<ProjectTask, ProjectTaskRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ProjectTask.class);
	}

	@Override
	protected ProjectTaskRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ProjectTaskRepositoryMapper.class);
	}
	
	public List<ProjectTask> findAllTargetBatch() {
		return execute((mapper) -> {
			return mapper.findAllTargetBatch();
		});
	}

}
