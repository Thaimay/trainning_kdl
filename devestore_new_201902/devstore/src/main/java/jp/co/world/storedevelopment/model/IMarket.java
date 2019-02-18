package jp.co.world.storedevelopment.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.IMarketModelMapper;

/**
 * @author hungdh
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class IMarket extends IActiveModel<IMarket> {

	private BigDecimal marketId;
	private String marketCd;
	private String marketName;
	private String managementDividionCd;
	private String managementDividionName;
	private String deptTypeDividionCd;
	private String deptTypeName;
	private String shopType;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime coordinationCreatedDatetime;
	private String coordinationCreatedAccountCode;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime coordinationUpdateDatetime;
	private String coordinationUpdateAccountCode;
	private String action;

	public IMarket() {
	}

	public BigDecimal getMarketId() {
		return marketId;
	}

	public void setMarketId(BigDecimal marketId) {
		this.marketId = marketId;
	}

	public String getMarketCd() {
		return marketCd;
	}

	public void setMarketCd(String marketCd) {
		this.marketCd = marketCd;
	}

	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public String getManagementDividionCd() {
		return managementDividionCd;
	}

	public void setManagementDividionCd(String managementDividionCd) {
		this.managementDividionCd = managementDividionCd;
	}

	public String getManagementDividionName() {
		return managementDividionName;
	}

	public void setManagementDividionName(String managementDividionName) {
		this.managementDividionName = managementDividionName;
	}

	public String getDeptTypeDividionCd() {
		return deptTypeDividionCd;
	}

	public void setDeptTypeDividionCd(String deptTypeDividionCd) {
		this.deptTypeDividionCd = deptTypeDividionCd;
	}

	public String getDeptTypeName() {
		return deptTypeName;
	}

	public void setDeptTypeName(String deptTypeName) {
		this.deptTypeName = deptTypeName;
	}

	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
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
	protected ModelMapper<IMarket> modelMapper(SqlSession session) {
		return session.getMapper(IMarketModelMapper.class);
	}
}
