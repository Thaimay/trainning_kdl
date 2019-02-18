package jp.co.world.storedevelopment.model;

import java.time.LocalDateTime;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.IShoppingStreetModelMapper;

/**
 * @author hungdh
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class IShoppingStreet extends IActiveModel<IShoppingStreet> {

	private Long shoppingStreetId = 0L;
	private String clientCode;
	private String validDivision;
	private String tradingStartDateOpenDate;
	private String tradingEndDateCloseDate;
	private String parentAccountCode;
	private String parentAccountDivision;
	private String approvalFlag;
	private String approverCode;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime approvalTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime coordinationCreatedDatetime;
	private String coordinationCreatedAccountCode;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime coordinationUpdateDatetime;
	private String coordinationUpdateAccountCode;
	private String action;

	public Long getShoppingStreetId() {
		return shoppingStreetId;
	}
	
	public void setShoppingStreetId(Long shoppingStreetId) {
		this.shoppingStreetId = shoppingStreetId;
	}
	
	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getValidDivision() {
		return validDivision;
	}

	public void setValidDivision(String validDivision) {
		this.validDivision = validDivision;
	}

	public String getTradingStartDateOpenDate() {
		return tradingStartDateOpenDate;
	}

	public void setTradingStartDateOpenDate(String tradingStartDateOpenDate) {
		this.tradingStartDateOpenDate = tradingStartDateOpenDate;
	}

	public String getTradingEndDateCloseDate() {
		return tradingEndDateCloseDate;
	}

	public void setTradingEndDateCloseDate(String tradingEndDateCloseDate) {
		this.tradingEndDateCloseDate = tradingEndDateCloseDate;
	}

	public String getParentAccountCode() {
		return parentAccountCode;
	}

	public void setParentAccountCode(String parentAccountCode) {
		this.parentAccountCode = parentAccountCode;
	}

	public String getParentAccountDivision() {
		return parentAccountDivision;
	}

	public void setParentAccountDivision(String parentAccountDivision) {
		this.parentAccountDivision = parentAccountDivision;
	}

	public String getApprovalFlag() {
		return approvalFlag;
	}

	public void setApprovalFlag(String approvalFlag) {
		this.approvalFlag = approvalFlag;
	}

	public String getApproverCode() {
		return approverCode;
	}

	public void setApproverCode(String approverCode) {
		this.approverCode = approverCode;
	}

	public LocalDateTime getApprovalTime() {
		return approvalTime;
	}

	public void setApprovalTime(LocalDateTime approvalTime) {
		this.approvalTime = approvalTime;
	}

	public LocalDateTime getCoordinationCreatedDatetime() {
		return coordinationCreatedDatetime;
	}

	public void setCoordinationCreatedDatetime(LocalDateTime coordinationCreatedDatetime) {
		this.coordinationCreatedDatetime = coordinationCreatedDatetime;
	}

	public String getCoordinationCreatedAccountCode() {
		return coordinationCreatedAccountCode;
	}

	public void setCoordinationCreatedAccountCode(String coordinationCreatedAccountCode) {
		this.coordinationCreatedAccountCode = coordinationCreatedAccountCode;
	}

	public LocalDateTime getCoordinationUpdateDatetime() {
		return coordinationUpdateDatetime;
	}

	public void setCoordinationUpdateDatetime(LocalDateTime coordinationUpdateDatetime) {
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
	protected ModelMapper<IShoppingStreet> modelMapper(SqlSession session) {
		return session.getMapper(IShoppingStreetModelMapper.class);
	}

}
