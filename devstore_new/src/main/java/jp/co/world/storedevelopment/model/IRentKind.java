package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.IRentKindModelMapper;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class IRentKind extends IActiveModel<IRentKind> {

	private Long rentKindId;
	private String rentKindCd;
	private String rentKindDivision;
	private String rentKindName;
	private String coordinationCreatedDatetime;
	private String coordinationCreatedAccountCode;
	private String coordinationUpdateDatetime;
	private String coordinationUpdateAccountCode;
	private String action;

	public Long getRentKindId() {
		return rentKindId;
	}

	public void setRentKindId(Long rentKindId) {
		this.rentKindId = rentKindId;
	}

	public String getRentKindCd() {
		return rentKindCd;
	}

	public void setRentKindCd(String rentKindCd) {
		this.rentKindCd = rentKindCd;
	}

	public String getRentKindDivision() {
		return rentKindDivision;
	}

	public void setRentKindDivision(String rentKindDivision) {
		this.rentKindDivision = rentKindDivision;
	}

	public String getRentKindName() {
		return rentKindName;
	}

	public void setRentKindName(String rentKindName) {
		this.rentKindName = rentKindName;
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
	protected ModelMapper<IRentKind> modelMapper(SqlSession session) {
		return session.getMapper(IRentKindModelMapper.class);
	}

}
