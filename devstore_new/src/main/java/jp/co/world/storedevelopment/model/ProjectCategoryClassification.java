package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ProjectCategoryClassificationModelMapper;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class ProjectCategoryClassification extends ActiveModel<ProjectCategoryClassification> {

	private Integer projectCategoryId;
	private Integer projectClassificationId;

	private String[] ignoreFields = new String[] {};

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	@Override
	protected ModelMapper<ProjectCategoryClassification> modelMapper(SqlSession session) {
		return session.getMapper(ProjectCategoryClassificationModelMapper.class);
	}

	public Integer getProjectCategoryId() {
		return projectCategoryId;
	}

	public void setProjectCategoryId(Integer projectCategoryId) {
		this.projectCategoryId = projectCategoryId;
	}

	public Integer getProjectClassificationId() {
		return projectClassificationId;
	}

	public void setProjectClassificationId(Integer projectClassificationId) {
		this.projectClassificationId = projectClassificationId;
	}

}
