package jp.co.world.storedevelopment.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.dev.RandomTestData;
import jp.co.world.storedevelopment.model.mapper.ISalesChannelModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;

/**
 * @author tainm
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class ISalesChannel extends IActiveModel<ISalesChannel> {

	private BigDecimal salesChannelId;
	private String salesChannelCd;
	private String salesChannelName;
	private String verificationSalesChannelCd;
	private String verificationSalesChannelName;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime coordinationCreatedDatetime;
	private String coordinationCreatedAccountCode;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime coordinationUpdateDatetime;
	private String coordinationUpdateAccountCode;
	private String action;

	public ISalesChannel() {

	}

	public ISalesChannel(String name) {
		this.setSalesChannelId(new BigDecimal(RandomTestData.getInteger(3)));
		this.setSalesChannelCd(RandomTestData.getString(4, false, true));
		this.setSalesChannelName(name);
		this.setCreatedAccountCode(new AccountRepository().getHead().get().getEmployeeCd());
		this.setUpdateAccountCode(new AccountRepository().getHead().get().getEmployeeCd());
	}

	private String[] ignoreFields = new String[] {};

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	public void setIgnoreFields(String[] ignoreFields) {
		this.ignoreFields = ignoreFields;
	}

	public BigDecimal getSalesChannelId() {
		return salesChannelId;
	}

	public void setSalesChannelId(BigDecimal salesChannelId) {
		this.salesChannelId = salesChannelId;
	}

	public String getSalesChannelCd() {
		return salesChannelCd;
	}

	public void setSalesChannelCd(String salesChannelCd) {
		this.salesChannelCd = salesChannelCd;
	}

	public String getSalesChannelName() {
		return salesChannelName;
	}

	public void setSalesChannelName(String salesChannelName) {
		this.salesChannelName = salesChannelName;
	}

	public String getVerificationSalesChannelCd() {
		return verificationSalesChannelCd;
	}

	public void setVerificationSalesChannelCd(String verificationSalesChannelCd) {
		this.verificationSalesChannelCd = verificationSalesChannelCd;
	}

	public String getVerificationSalesChannelName() {
		return verificationSalesChannelName;
	}

	public void setVerificationSalesChannelName(String verificationSalesChannelName) {
		this.verificationSalesChannelName = verificationSalesChannelName;
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
	protected ModelMapper<ISalesChannel> modelMapper(SqlSession session) {
		return session.getMapper(ISalesChannelModelMapper.class);
	}
}