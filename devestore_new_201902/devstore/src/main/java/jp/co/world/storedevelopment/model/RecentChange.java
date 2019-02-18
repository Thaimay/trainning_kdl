package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.RecentChangeModelMapper;

public class RecentChange extends ActiveModel<RecentChange> {

	private Long buildingId;
	private Long neogtationId;
	private Long projectId;
	private Long accountId;
	private String division;
	private String content;

	private String[] ignoreFields = new String[] {};

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	public void setIgnoreFields(String[] ignoreFields) {
		this.ignoreFields = ignoreFields;
	}

	public RecentChange() {
	}

	public RecentChange(Account account) {
		setAccountId(account.getId());
		setCreateAccount(account);
	}

	@Override
	protected ModelMapper<RecentChange> modelMapper(SqlSession session) {
		return session.getMapper(RecentChangeModelMapper.class);
	}

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
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

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getNeogtationId() {
		return neogtationId;
	}

	public void setNeogtationId(Long neogtationId) {
		this.neogtationId = neogtationId;
	}

}
