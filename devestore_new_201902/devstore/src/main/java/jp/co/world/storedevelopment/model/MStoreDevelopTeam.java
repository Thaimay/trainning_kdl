package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.MStoreDevelopTeamModelMapper;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class MStoreDevelopTeam extends ActiveModel<MStoreDevelopTeam> {

	private String incomeUnitCd;
	private String incomeUnitName;
	private String deptCd;
	private String deptName;

	private String[] ignoreFields = new String[] {};

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	@Override
	protected ModelMapper<MStoreDevelopTeam> modelMapper(SqlSession session) {
		return session.getMapper(MStoreDevelopTeamModelMapper.class);
	}

	public String getIncomeUnitCd() {
		return incomeUnitCd;
	}

	public void setIncomeUnitCd(String incomeUnitCd) {
		this.incomeUnitCd = incomeUnitCd;
	}

	public String getIncomeUnitName() {
		return incomeUnitName;
	}

	public void setIncomeUnitName(String incomeUnitName) {
		this.incomeUnitName = incomeUnitName;
	}

	public String getDeptCd() {
		return deptCd;
	}

	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}
