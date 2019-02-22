package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import jp.co.world.storedevelopment.model.ProjectDocImage;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;

public interface ProjectDocImageRepositoryMapper extends ProjectDocRelatedRepositoryMapper<ProjectDocImage>{
	@Select("select * from file where file.is_deleted is false and file.project_id in (select project_id from project_document where project_document.is_deleted is false and project_document.id = #{projectId}) ")
	List<ProjectDocImage> findByProject(@Param("projectId") BuildingRelationFindByTextFormDTO projectId);
	
	@Select("select * from file where project_id = '${projectId}' and is_deleted is false order by update_datetime desc")
	List<ProjectDocImage> findProjectDocImageRelatedProject(@Param("projectId") Long projectId);

}
