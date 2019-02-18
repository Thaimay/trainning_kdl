package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

public interface ProjectRelatedRepositoryMapper<T> extends RepositoryMapper<T> {

	@Select("select * from ${table} where project_id = #{projectId} and is_deleted is false")
	List<T> findByProjectId(@Param("table") String table, @Param("projectId") Long projectId);
	
	@Delete("delete from ${table} where project_id = #{projectId}")
	int deleteByProjectId(@Param("table") String table, @Param("projectId") Long projectId);

	@Select("select * from ${table}_history where project_id = #{projectId} and is_deleted is false")
	List<T> findHistoryByProjectId(@Param("table") String table, @Param("projectId") Long projectId);
}
