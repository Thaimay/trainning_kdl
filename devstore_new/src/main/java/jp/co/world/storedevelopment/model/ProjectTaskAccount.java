package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ProjectTaskAccountModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class ProjectTaskAccount extends ActiveModel<ProjectTaskAccount> {

	private Long projectTaskId;
	private Long accountId;

	private String[] ignoreFields = new String[] {};

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}
	
	public Account account() {
		return new AccountRepository().findById(getAccountId()).orElseGet(() -> {
			throw new IllegalStateException();
		});
	}

	@Override
	protected ModelMapper<ProjectTaskAccount> modelMapper(SqlSession session) {
		return session.getMapper(ProjectTaskAccountModelMapper.class);
	}

	public Long getProjectTaskId() {
		return projectTaskId;
	}

	public void setProjectTaskId(Long projectTaskId) {
		this.projectTaskId = projectTaskId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

}
