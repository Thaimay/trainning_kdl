package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.ProjectCategory;

public interface ProjectCategoryRepositoryMapper extends RepositoryMapper<ProjectCategory> {

	@Select("select * from project_category where is_deleted is false order by sort asc")
	List<ProjectCategory> getAllBySort();
}
