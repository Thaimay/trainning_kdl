package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.IBrandIncomeUnitModelMapper;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class IBrandIncomeUnit extends IActiveModel<IBrandIncomeUnit> {

	private Long brandIncomeUnitId;
	private String brandIncomeUnitCd;
	private String brandIncomeUnitName;
	private Long incomeUnitId;
	private String coordinationCreatedDatetime;
	private String coordinationCreatedAccountCode;
	private String coordinationUpdateDatetime;
	private String coordinationUpdateAccountCode;
	private String action;

	public Long getBrandIncomeUnitId() {
		return brandIncomeUnitId;
	}

	public void setBrandIncomeUnitId(Long brandIncomeUnitId) {
		this.brandIncomeUnitId = brandIncomeUnitId;
	}

	public String getBrandIncomeUnitCd() {
		return brandIncomeUnitCd;
	}

	public void setBrandIncomeUnitCd(String brandIncomeUnitCd) {
		this.brandIncomeUnitCd = brandIncomeUnitCd;
	}

	public String getBrandIncomeUnitName() {
		return brandIncomeUnitName;
	}

	public void setBrandIncomeUnitName(String brandIncomeUnitName) {
		this.brandIncomeUnitName = brandIncomeUnitName;
	}

	public Long getIncomeUnitId() {
		return incomeUnitId;
	}

	public void setIncomeUnitId(Long incomeUnitId) {
		this.incomeUnitId = incomeUnitId;
	}

	public String getCoordinationCreatedDatetime() {
		return coordinationCreatedDatetime;
	}

	public void setCoordinationCreatedDatetime(String coordinationCreatedDatetime) {
		this.coordinationCreatedDatetime = coordinationCreatedDatetime;
	}

	public String getCoordinationCreatedAccountCode() {
		return coordinationCreatedAccountCode;
	}

	public void setCoordinationCreatedAccountCode(String coordinationCreatedAccountCode) {
		this.coordinationCreatedAccountCode = coordinationCreatedAccountCode;
	}

	public String getCoordinationUpdateDatetime() {
		return coordinationUpdateDatetime;
	}

	public void setCoordinationUpdateDatetime(String coordinationUpdateDatetime) {
		this.coordinationUpdateDatetime = coordinationUpdateDatetime;
	}

	public String getCoordinationUpdateAccountCode() {
		return coordinationUpdateAccountCode;
	}

	public void setCoordinationUpdateAccountCode(String coordinationUpdateAccountCode) {
		this.coordinationUpdateAccountCode = coordinationUpdateAccountCode;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	protected ModelMapper<IBrandIncomeUnit> modelMapper(SqlSession session) {
		return session.getMapper(IBrandIncomeUnitModelMapper.class);
	}

}
