package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.OtherProjectTeamModelMapper;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class OtherProjectTeam extends ActiveModel<OtherProjectTeam> {

	private Long projectId;
	private String category;
	private String incomeUnitCd;
	private String deptCd;

	private String[] ignoreFields = new String[] {};

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	@Override
	protected ModelMapper<OtherProjectTeam> modelMapper(SqlSession session) {
		return session.getMapper(OtherProjectTeamModelMapper.class);
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getIncomeUnitCd() {
		return incomeUnitCd;
	}

	public void setIncomeUnitCd(String incomeUnitCd) {
		this.incomeUnitCd = incomeUnitCd;
	}

	public String getDeptCd() {
		return deptCd;
	}

	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}

}
