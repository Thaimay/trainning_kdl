package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.ProjectSwitingItemControl;

public interface ProjectSwitingItemControlRepositoryMapper extends RepositoryMapper<ProjectSwitingItemControl> {

	@Select("select * from PROJECT_SWITING_ITEM_CONTROL where is_deleted = false and project_category_id = ${projectCategoryId}")
	ProjectSwitingItemControl findByProjectCategoryId(@Param("projectCategoryId") Long projectCategoryId);

}
