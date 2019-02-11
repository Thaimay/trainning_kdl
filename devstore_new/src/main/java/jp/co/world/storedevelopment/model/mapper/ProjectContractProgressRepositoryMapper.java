package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.world.storedevelopment.model.ProjectContractProgress;

public interface ProjectContractProgressRepositoryMapper
		extends ProjectRelatedRepositoryMapper<ProjectContractProgress> {
	@Select("select * from project_contract_progress where is_deleted is false and category = 'CURRENT' and project_id = '${id}'")
	ProjectContractProgress getCurrentContractByProjectId(@Param("id") Long projectId);

	@Select("select * from project_contract_progress where is_deleted is false and category = 'PROGRESS' and project_id = '${id}'")
	ProjectContractProgress getProgressContractByProjectId(@Param("id") Long projectId);

	@Select("select * from project_contract_progress_history where is_deleted is false and category = 'CURRENT' and project_id = '${id}'")
	ProjectContractProgress getHistoryCurrentContractByProjectId(@Param("id") Long projectId);

	@Select("select * from project_contract_progress_history where is_deleted is false and category = 'PROGRESS' and project_id = '${id}'")
	ProjectContractProgress getHistoryProgressContractByProjectId(@Param("id") Long projectId);
}
