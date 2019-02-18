package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.ProjectClassification;

public interface ProjectClassificationRepositoryMapper extends RepositoryMapper<ProjectClassification> {
	@Select(" select a.* from project_classification a join project_category_classification b on a.id = b.project_classification_id where a.is_deleted is false and a.classification = '${classification}' and b.is_deleted is false and b.project_category_id = '${categoryId}' order by disp_order asc")
	List<ProjectClassification> getClassificationByCategory(@Param("classification") String classification,
			@Param("categoryId") Long categoryId);

	@Select("select a.* from project_classification a where a.is_deleted is false and a.classification = '${classification}'")
	List<ProjectClassification> getClassificationByClassification(@Param("classification") String classification);

	@Select("select a.* from project_classification a where a.is_deleted is false and a.id in (select pccp.child_id from project_classificatoin_child_parent pccp where pccp.is_deleted is false and pccp.parent_id = '${id}' LIMIT 1)")
	ProjectClassification getClassificationChild(@Param("id") Long id);

	@Select(" select a.* from project_classification a join project_category_classification pcc on a.id =  pcc.project_classification_id where a.is_deleted is false and a.classification = 'PROJECT_CATEGORY_COLOR' and   pcc.is_deleted is false and pcc.project_category_id = '${id}' order by disp_order asc")
	ProjectClassification findClassificationColor(@Param("id") Long id);
}
