package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.world.storedevelopment.model.BuildingFile;

public interface BuildingFileRepositoryMapper extends BuildingRelatedRepositoryMapper<BuildingFile> {
	@Select("select * from file where building_id = '${buildingId}' and is_deleted is false order by update_datetime desc")
	List<BuildingFile> findBuildingFileRelatedProject(@Param("buildingId") Long buildingId);
}
