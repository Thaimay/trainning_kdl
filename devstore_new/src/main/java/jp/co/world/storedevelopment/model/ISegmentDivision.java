package jp.co.world.storedevelopment.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ISegmentDivisionModelMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class ISegmentDivision extends IActiveModel<ISegmentDivision> {

	private BigDecimal segmentDivisionId;
	private String segmentDivisionCd;
	private String segmentDivisionName;
	private String generation;
	private String trafficQuantity;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime coordinationCreatedDatetime;
	private String coordinationCreatedAccountCode;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime coordinationUpdateDatetime;
	private String coordinationUpdateAccountCode;
	private String action;

	public BigDecimal getSegmentDivisionId() {
		return segmentDivisionId;
	}

	public void setSegmentDivisionId(BigDecimal segmentDivisionId) {
		this.segmentDivisionId = segmentDivisionId;
	}

	public String getSegmentDivisionCd() {
		return segmentDivisionCd;
	}

	public void setSegmentDivisionCd(String segmentDivisionCd) {
		this.segmentDivisionCd = segmentDivisionCd;
	}

	public String getSegmentDivisionName() {
		return segmentDivisionName;
	}

	public void setSegmentDivisionName(String segmentDivisionName) {
		this.segmentDivisionName = segmentDivisionName;
	}

	public String getGeneration() {
		return generation;
	}

	public void setGeneration(String generation) {
		this.generation = generation;
	}

	public String getTrafficQuantity() {
		return trafficQuantity;
	}

	public void setTrafficQuantity(String trafficQuantity) {
		this.trafficQuantity = trafficQuantity;
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
	protected ModelMapper<ISegmentDivision> modelMapper(SqlSession session) {
		return session.getMapper(ISegmentDivisionModelMapper.class);
	}

}
