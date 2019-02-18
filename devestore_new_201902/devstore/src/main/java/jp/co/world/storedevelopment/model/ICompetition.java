package jp.co.world.storedevelopment.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ICompetitionModelMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class ICompetition extends IActiveModel<ICompetition> {

	private BigDecimal competitionShopId;
	private String competitionShopCd;
	private String competitionShopName;
	private BigDecimal positionId;
	private BigDecimal competitionBrId;
	private String responsibleCd;
	private BigDecimal zoneId;
	private Long generationCd;
	private String sellingSpaceAddress;
	private String floor;
	private BigDecimal executingTsubo;
	private String openDate;
	private String status;
	private Long chargeShopId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime registrationTime;
	private String registrantCd;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime updateTime;
	private String updaterCd;
	private String action;
	private Long pastId;

	public BigDecimal getCompetitionShopId() {
		return competitionShopId;
	}

	public void setCompetitionShopId(BigDecimal competitionShopId) {
		this.competitionShopId = competitionShopId;
	}

	public String getCompetitionShopCd() {
		return competitionShopCd;
	}

	public void setCompetitionShopCd(String competitionShopCd) {
		this.competitionShopCd = competitionShopCd;
	}

	public String getCompetitionShopName() {
		return competitionShopName;
	}

	public void setCompetitionShopName(String competitionShopName) {
		this.competitionShopName = competitionShopName;
	}

	public BigDecimal getPositionId() {
		return positionId;
	}

	public void setPositionId(BigDecimal positionId) {
		this.positionId = positionId;
	}

	public BigDecimal getCompetitionBrId() {
		return competitionBrId;
	}

	public void setCompetitionBrId(BigDecimal competitionBrId) {
		this.competitionBrId = competitionBrId;
	}

	public String getResponsibleCd() {
		return responsibleCd;
	}

	public void setResponsibleCd(String responsibleCd) {
		this.responsibleCd = responsibleCd;
	}

	public BigDecimal getZoneId() {
		return zoneId;
	}

	public void setZoneId(BigDecimal zoneId) {
		this.zoneId = zoneId;
	}

	public Long getGenerationCd() {
		return generationCd;
	}

	public void setGenerationCd(Long generationCd) {
		this.generationCd = generationCd;
	}

	public String getSellingSpaceAddress() {
		return sellingSpaceAddress;
	}

	public void setSellingSpaceAddress(String sellingSpaceAddress) {
		this.sellingSpaceAddress = sellingSpaceAddress;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public BigDecimal getExecutingTsubo() {
		return executingTsubo;
	}

	public void setExecutingTsubo(BigDecimal executingTsubo) {
		this.executingTsubo = executingTsubo;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getChargeShopId() {
		return chargeShopId;
	}

	public void setChargeShopId(Long chargeShopId) {
		this.chargeShopId = chargeShopId;
	}

	public LocalDateTime getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(LocalDateTime registrationTime) {
		this.registrationTime = registrationTime;
	}

	public String getRegistrantCd() {
		return registrantCd;
	}

	public void setRegistrantCd(String registrantCd) {
		this.registrantCd = registrantCd;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdaterCd() {
		return updaterCd;
	}

	public void setUpdaterCd(String updaterCd) {
		this.updaterCd = updaterCd;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Long getPastId() {
		return pastId;
	}

	public void setPastId(Long pastId) {
		this.pastId = pastId;
	}

	@Override
	protected ModelMapper<ICompetition> modelMapper(SqlSession session) {
		return session.getMapper(ICompetitionModelMapper.class);
	}

}
