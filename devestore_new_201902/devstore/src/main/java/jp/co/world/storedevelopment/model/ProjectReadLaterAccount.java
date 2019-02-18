package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ProjectReadLaterAccountModelMapper;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class ProjectReadLaterAccount extends ActiveModel<ProjectReadLaterAccount> {

	private Long projectId;
	private Long accountId;

	private String[] ignoreFields = new String[] {};

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	@Override
	protected ModelMapper<ProjectReadLaterAccount> modelMapper(SqlSession session) {
		return session.getMapper(ProjectReadLaterAccountModelMapper.class);
	}
	
	public ProjectReadLaterAccount() {
		//
	}

	public ProjectReadLaterAccount(Project project, Account account) {
		projectId = project.id;
		accountId = account.id;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

}
