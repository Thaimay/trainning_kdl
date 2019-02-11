package jp.co.world.storedevelopment.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ICompetitionBrModelMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class ICompetitionBr extends IActiveModel<ICompetitionBr> {

	private BigDecimal competitionBrId;
	private String competitionBrCd;
	private String competitionBrName;
	private BigDecimal competitionCorporateId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime registrationTime;
	private String registrantCd;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime updateTime;
	private String updaterCd;
	private String action;
	private Long pastId;

	public BigDecimal getCompetitionBrId() {
		return competitionBrId;
	}

	public void setCompetitionBrId(BigDecimal competitionBrId) {
		this.competitionBrId = competitionBrId;
	}

	public String getCompetitionBrCd() {
		return competitionBrCd;
	}

	public void setCompetitionBrCd(String competitionBrCd) {
		this.competitionBrCd = competitionBrCd;
	}

	public String getCompetitionBrName() {
		return competitionBrName;
	}

	public void setCompetitionBrName(String competitionBrName) {
		this.competitionBrName = competitionBrName;
	}

	public BigDecimal getCompetitionCorporateId() {
		return competitionCorporateId;
	}

	public void setCompetitionCorporateId(BigDecimal competitionCorporateId) {
		this.competitionCorporateId = competitionCorporateId;
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
	protected ModelMapper<ICompetitionBr> modelMapper(SqlSession session) {
		return session.getMapper(ICompetitionBrModelMapper.class);
	}

}
