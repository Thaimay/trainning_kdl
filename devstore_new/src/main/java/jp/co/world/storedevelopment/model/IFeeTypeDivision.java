package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.IFeeTypeDivisionModelMapper;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class IFeeTypeDivision extends IActiveModel<IFeeTypeDivision> {

	private Long feeTypeDivisionId;
	private String feeTypeDivisionName;
	private String coordinationCreatedDatetime;
	private String coordinationCreatedAccountCode;
	private String coordinationUpdateDatetime;
	private String coordinationUpdateAccountCode;
	private String action;

	public Long getFeeTypeDivisionId() {
		return feeTypeDivisionId;
	}

	public void setFeeTypeDivisionId(Long feeTypeDivisionId) {
		this.feeTypeDivisionId = feeTypeDivisionId;
	}

	public String getFeeTypeDivisionName() {
		return feeTypeDivisionName;
	}

	public void setFeeTypeDivisionName(String feeTypeDivisionName) {
		this.feeTypeDivisionName = feeTypeDivisionName;
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
	protected ModelMapper<IFeeTypeDivision> modelMapper(SqlSession session) {
		return session.getMapper(IFeeTypeDivisionModelMapper.class);
	}

}
