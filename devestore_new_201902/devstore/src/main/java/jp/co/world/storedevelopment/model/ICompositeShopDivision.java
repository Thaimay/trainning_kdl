package jp.co.world.storedevelopment.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ICompositeShopDivisionModelMapper;

/**
 * @author hungdh
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class ICompositeShopDivision extends IActiveModel<ICompositeShopDivision> {

	private BigDecimal compositeShopDivisionId;
	private String compositeShopDivisionCd;
	private String compositeShopDivisionName;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime coordinationCreatedDatetime;
	private String coordinationCreatedAccountCode;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime coordinationUpdateDatetime;
	private String coordinationUpdateAccountCode;
	private String action;

	public BigDecimal getCompositeShopDivisionId() {
		return compositeShopDivisionId;
	}

	public void setCompositeShopDivisionId(BigDecimal compositeShopDivisionId) {
		this.compositeShopDivisionId = compositeShopDivisionId;
	}

	public String getCompositeShopDivisionCd() {
		return compositeShopDivisionCd;
	}

	public void setCompositeShopDivisionCd(String compositeShopDivisionCd) {
		this.compositeShopDivisionCd = compositeShopDivisionCd;
	}

	public String getCompositeShopDivisionName() {
		return compositeShopDivisionName;
	}

	public void setCompositeShopDivisionName(String compositeShopDivisionName) {
		this.compositeShopDivisionName = compositeShopDivisionName;
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
	protected ModelMapper<ICompositeShopDivision> modelMapper(SqlSession session) {
		return session.getMapper(ICompositeShopDivisionModelMapper.class);
	}

}
