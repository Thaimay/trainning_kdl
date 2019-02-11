package jp.co.world.storedevelopment.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.IFixedAssetsDepreciationDivisionModelMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class IFixedAssetsDepreciationDivision extends IActiveModel<IFixedAssetsDepreciationDivision> {

	private BigDecimal stepsRateCalculationDivisionId;
	private String stepsRateCalculationDivisionCd;
	private String stepsRateCalculationDivisionName;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime coordinationCreatedDatetime;
	private String coordinationCreatedAccountCode;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime coordinationUpdateDatetime;
	private String coordinationUpdateAccountCode;
	private String action;

	public BigDecimal getStepsRateCalculationDivisionId() {
		return stepsRateCalculationDivisionId;
	}

	public void setStepsRateCalculationDivisionId(BigDecimal stepsRateCalculationDivisionId) {
		this.stepsRateCalculationDivisionId = stepsRateCalculationDivisionId;
	}

	public String getStepsRateCalculationDivisionCd() {
		return stepsRateCalculationDivisionCd;
	}

	public void setStepsRateCalculationDivisionCd(String stepsRateCalculationDivisionCd) {
		this.stepsRateCalculationDivisionCd = stepsRateCalculationDivisionCd;
	}

	public String getStepsRateCalculationDivisionName() {
		return stepsRateCalculationDivisionName;
	}

	public void setStepsRateCalculationDivisionName(String stepsRateCalculationDivisionName) {
		this.stepsRateCalculationDivisionName = stepsRateCalculationDivisionName;
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
	protected ModelMapper<IFixedAssetsDepreciationDivision> modelMapper(SqlSession session) {
		return session.getMapper(IFixedAssetsDepreciationDivisionModelMapper.class);
	}

}
