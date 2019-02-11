package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.ProjectProgress;

public interface ProjectProgressRepositoryMapper extends RepositoryMapper<ProjectProgress> {

	@Select("select * from m_project_progress_status where name like '%${text}%' or name like '%${hankaku}%' limit ${limit}")
	List<ProjectProgress> findByText(@Param("text") String text, @Param("hankaku") String hankaku,
			@Param("limit") int limit);

	@Select("select * from m_project_progress_status where is_deleted is false and category = '${category}' and project_category_id = '${projectCategoryId}' ORDER BY priority ASC")
	List<ProjectProgress> getByCategory(@Param("category") String category,
			@Param("projectCategoryId") Long projectCategoryId);
}
