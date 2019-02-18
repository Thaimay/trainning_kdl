package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.ProjectPlan;

public interface ProjectPlanRepositoryMapper extends RepositoryMapper<ProjectPlan> {
	@Select("SELECT * FROM project_plan WHERE is_deleted = false AND project_id = ${projectId}")
	public List<ProjectPlan> findByProjectid(@Param("projectId") Long projectId);

	@Select("SELECT * FROM project_plan WHERE is_deleted = false AND project_id = ${id} AND category = 'PROGRESS'")
	public ProjectPlan findProgressByProjectId(@Param("id") Long id);

	@Select("SELECT * FROM project_plan WHERE is_deleted = false AND project_id = ${id} AND category = 'CURRENT'")
	public ProjectPlan findCurrentByProjectId(@Param("id") Long id);
	
	@Delete("Delete FROM project_plan WHERE project_id = ${projectId}")
	public int deleteByProjectId(@Param("projectId") Long projectId);

	@Select("SELECT * FROM project_plan_history WHERE is_deleted = false AND project_id = ${projectId}")
	public List<ProjectPlan> findHistoryByProjectid(@Param("projectId") Long projectId);
}
