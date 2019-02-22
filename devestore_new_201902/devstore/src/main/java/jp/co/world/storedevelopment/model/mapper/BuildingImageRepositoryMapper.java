package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.world.storedevelopment.model.BuildingImage;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;

public interface BuildingImageRepositoryMapper extends BuildingRelatedRepositoryMapper<BuildingImage> {

	@Select("select * from file where file.is_deleted is false and file.building_id in (select building_id from project where project.is_deleted is false and project.id = #{projectId}) ")
	List<BuildingImage> findByProject(@Param("projectId") BuildingRelationFindByTextFormDTO projectId);
	
	@Select("select * from file where building_id = '${buildingId}' and is_deleted is false order by update_datetime desc")
	List<BuildingImage> findBuildingImageRelatedProject(@Param("buildingId") Long buildingId);
	
	//Add by QuyenLS
	@Select("select * from file where project_id = '${projectId}' and is_deleted is false order by update_datetime desc")
	List<BuildingImage> findProjectDocImageRelatedProject(@Param("projectId") Long projectId);
	
}
