package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.ProjectOpinion;

public interface ProjectOpinionRepositoryMapper extends ProjectRelatedRepositoryMapper<ProjectOpinion> {
	@Select("select * from project_opinion where is_deleted is false and category = '${category}' and project_id = '${id}'")
	ProjectOpinion getProjectOpinion(@Param("id") Long projectId, @Param("category") String category);
	
	@Select(
			"select * from project WHERE is_deleted is false AND operation_division = '進行中' AND project_category_id IN (${ids})"
			+ " AND (id IN (select project_id from project_opinion WHERE category IN ('BRANCH', 'VIEW') AND (comment = '' || comment is null))"
			+ " OR  (select count(id) from project_opinion WHERE project_id = project.id AND category IN ('BRANCH', 'VIEW')) = 0)"
			+ " AND m_project_action_status_id IN (select id from m_project_action_status WHERE (project_category_id, sales_channel_id, sort) IN"
			+ " (select project_category_id, sales_channel_id, sort - 1 from m_project_action_status WHERE name = '物件上程'))"
			)
	List<Project> findAlert(@Param("ids") String id);
	
	@Select(
			"select * from project WHERE is_deleted is false AND operation_division = '進行中'"
			+ " AND id IN (select project_id from project_schedule WHERE is_deleted = false "
			+ " AND m_project_action_status_id IN (select id from m_project_action_status WHERE name = '基本合意'))"
			)
	List<Project> findConsenseAlert();

	@Select("select * from project_opinion_history where is_deleted is false and category = '${category}' and project_id = '${id}'")
	ProjectOpinion getHistoryProjectOpinion(@Param("id") Long projectId, @Param("category") String category);

}
