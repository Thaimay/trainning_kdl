package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

public interface ProjectDocRelatedRepositoryMapper<T> extends RepositoryMapper<T> {
	@Select("select * from ${table} where origin_building_id = #{originBuildingId} and is_deleted is false order by update_datetime desc")
	List<T> findByOriginProjectDocId(@Param("table") String table, @Param("originBuildingId") Long originBuildingId);

	@Select("select * from ${table} where project_id = #{projectId} and is_deleted is false order by update_datetime desc")
	List<T> findByProjectDocId(@Param("table") String table, @Param("projectId") Long projectId);

}
