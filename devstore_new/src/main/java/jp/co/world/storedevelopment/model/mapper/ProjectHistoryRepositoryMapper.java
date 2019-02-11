package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.ProjectHistory;

public interface ProjectHistoryRepositoryMapper extends RepositoryMapper<ProjectHistory> {
	@Select("select * from project_history where is_deleted is false and project_id = '${project_id}'")
	List<ProjectHistory> getProjectHistoryList(@Param("project_id") Long project_id);

	void updateProjectHistory(@Param("project") Project project);
}