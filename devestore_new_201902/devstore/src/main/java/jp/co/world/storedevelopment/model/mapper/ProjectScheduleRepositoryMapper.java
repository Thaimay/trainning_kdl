package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.ProjectSchedule;

public interface ProjectScheduleRepositoryMapper extends RepositoryMapper<ProjectSchedule> {

	@Select("select * from project_schedule" +
			" where is_deleted = false" +
			"   and project_id = ${projectId}" +
			"   and m_project_action_status_id = ${mProjectActionStatusId} limit 1")
	public ProjectSchedule getProjectSchedule(@Param("projectId") Long projectId, @Param("mProjectActionStatusId") Long mProjectActionStatusId);

	@Select("SELECT * FROM project_schedule"
			+ " LEFT JOIN m_project_action_status m ON m_project_action_status_id = m.id"
			+ " WHERE project_schedule.is_deleted = false AND project_id = ${projectId} ORDER BY m.sort")
	public List<ProjectSchedule> findByProjectid(@Param("projectId") Long projectId);
	
	@Select("select * from project_schedule WHERE is_deleted = false AND project_id = ${projectId}"
			+ "AND m_project_action_status_id IN (select id from m_project_action_status WHERE name = '物件上程')")
	public ProjectSchedule findReviewSchedule(@Param("projectId") Long id);

	@Select("select * from project_schedule WHERE is_deleted = false AND project_id = ${projectId}"
			+ "AND m_project_action_status_id IN (select id from m_project_action_status WHERE name = '基本合意')")
	public ProjectSchedule findConsensusSchedule(@Param("projectId") Long id);
	
	@Delete("DELETE FROM project_schedule WHERE project_id = ${projectId}")
	public int deleteByProjectId(@Param("projectId") Long id);

	@Select("SELECT * FROM project_schedule_history"
			+ " LEFT JOIN m_project_action_status m ON m_project_action_status_id = m.id"
			+ " WHERE project_schedule_history.is_deleted = false AND project_id = ${projectId} ORDER BY m.sort")
	public List<ProjectSchedule> findHistoryByProjectid(@Param("projectId") Long projectId);

	@Select("select * from project_schedule_history" +
			" where is_deleted = false" +
			"   and project_id = ${projectId}" +
			"   and m_project_action_status_id = ${mProjectActionStatusId} limit 1")
	public ProjectSchedule getProjectScheduleHistory(@Param("projectId") Long projectId, @Param("mProjectActionStatusId") Long mProjectActionStatusId);
}
