package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

public interface BuildingRelatedRepositoryMapper<T> extends RepositoryMapper<T> {

	@Select("select * from ${table} where origin_building_id = #{originBuildingId} and is_deleted is false order by update_datetime desc")
	List<T> findByOriginBuildingId(@Param("table") String table, @Param("originBuildingId") Long originBuildingId);

	@Select("select * from ${table} where building_id = #{buildingId} and is_deleted is false order by update_datetime desc")
	List<T> findByBuildingId(@Param("table") String table, @Param("buildingId") Long buildingId);

	@Select("select * from ${table} where building_cd = '${buildingCd}' and is_deleted is false order by update_datetime desc")
	List<T> findByBuildingCd(@Param("table") String table, @Param("buildingCd") String buildingCd);

	@Delete("delete from ${table} where building_id = #{buildingId}")
	int deleteByBuildingId(@Param("table") String table, @Param("buildingId") Long buildingId);

	@Delete("delete from ${table} where building_cd = '${buildingCd}'")
	int deleteByBuildingCd(@Param("table") String table, @Param("buildingCd") String buildingCd);

	@Select("select * from ${table}_history where building_id = #{buildingId} and is_deleted is false order by update_datetime desc")
	List<T> findHistoryByBuildingHistoryId(@Param("table") String table, @Param("buildingId") Long buildingId);

	@Select("select * from ${table}_history where building_cd = '${buildingCd}' and is_deleted is false order by update_datetime desc")
	List<T> findHistoryByBuildingHistoryCd(@Param("table") String table, @Param("buildingCd") String buildingCd);
	
}
