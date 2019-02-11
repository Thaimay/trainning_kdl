package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ProjectTaskOwnerModelMapper;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class ProjectTaskOwner extends ActiveModel<ProjectTaskOwner> {

	private Long projectTaskId;
	private Long iAccountId;

	private String[] ignoreFields = new String[] {};

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	@Override
	protected ModelMapper<ProjectTaskOwner> modelMapper(SqlSession session) {
		return session.getMapper(ProjectTaskOwnerModelMapper.class);
	}

	public Long getProjectTaskId() {
		return projectTaskId;
	}

	public void setProjectTaskId(Long projectTaskId) {
		this.projectTaskId = projectTaskId;
	}

	public Long getiAccountId() {
		return iAccountId;
	}

	public void setiAccountId(Long iAccountId) {
		this.iAccountId = iAccountId;
	}

}
