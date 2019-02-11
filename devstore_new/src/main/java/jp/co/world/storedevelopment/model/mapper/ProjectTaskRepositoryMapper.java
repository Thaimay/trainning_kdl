package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import jp.co.world.storedevelopment.model.ProjectTask;

public interface ProjectTaskRepositoryMapper extends ProjectRelatedRepositoryMapper<ProjectTask> {
	@Select("select * from project_task WHERE is_deleted = false AND complete = false AND project_id IN (select id from project WHERE is_deleted = false AND operation_division = '進行中')")
	public List<ProjectTask> findAllTargetBatch();
}
