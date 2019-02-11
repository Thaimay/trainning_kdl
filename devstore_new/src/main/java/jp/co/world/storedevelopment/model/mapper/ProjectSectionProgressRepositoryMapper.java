package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.world.storedevelopment.model.ProjectSectionProgress;

public interface ProjectSectionProgressRepositoryMapper extends ProjectRelatedRepositoryMapper<ProjectSectionProgress> {
	@Select("select * from project_section_progress where is_deleted is false and category = 'CURRENT' and project_id = '${id}'")
	ProjectSectionProgress getCurrentSectionProgressByProjectId(@Param("id") Long projectId);

	@Select("select * from project_section_progress where is_deleted is false and category = 'NEGOTIATION' and project_id = '${id}'")
	ProjectSectionProgress getNegotiationSectionProgressByProjectId(@Param("id") Long projectId);

	@Select("select * from project_section_progress_history where is_deleted is false and category = 'CURRENT' and project_id = '${id}'")
	ProjectSectionProgress getHistoryCurrentSectionProgressByProjectId(@Param("id") Long projectId);

	@Select("select * from project_section_progress_history where is_deleted is false and category = 'NEGOTIATION' and project_id = '${id}'")
	ProjectSectionProgress getHistoryNegotiationSectionProgressByProjectId(@Param("id") Long projectId);
}
