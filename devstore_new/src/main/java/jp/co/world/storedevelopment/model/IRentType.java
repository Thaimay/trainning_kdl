package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.IRentTypeModelMapper;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class IRentType extends IActiveModel<IRentType> {

	private Long rentTypeId;
	private String rentTypeCd;
	private String rentTypeDivision;
	private String rentTypeName;
	private String coordinationCreatedDatetime;
	private String coordinationCreatedAccountCode;
	private String coordinationUpdateDatetime;
	private String coordinationUpdateAccountCode;
	private String action;

	public Long getRentTypeId() {
		return rentTypeId;
	}

	public void setRentTypeId(Long rentTypeId) {
		this.rentTypeId = rentTypeId;
	}

	public String getRentTypeCd() {
		return rentTypeCd;
	}

	public void setRentTypeCd(String rentTypeCd) {
		this.rentTypeCd = rentTypeCd;
	}

	public String getRentTypeDivision() {
		return rentTypeDivision;
	}

	public void setRentTypeDivision(String rentTypeDivision) {
		this.rentTypeDivision = rentTypeDivision;
	}

	public String getRentTypeName() {
		return rentTypeName;
	}

	public void setRentTypeName(String rentTypeName) {
		this.rentTypeName = rentTypeName;
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
	protected ModelMapper<IRentType> modelMapper(SqlSession session) {
		return session.getMapper(IRentTypeModelMapper.class);
	}

}
