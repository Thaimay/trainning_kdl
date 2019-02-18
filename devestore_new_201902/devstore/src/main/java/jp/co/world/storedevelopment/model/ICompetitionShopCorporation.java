package jp.co.world.storedevelopment.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ICompetitionShopCorporationModelMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class ICompetitionShopCorporation extends IActiveModel<ICompetitionShopCorporation> {

	private BigDecimal competitionShopCorporationId;
	private String competitionShopCorporationCd;
	private String competitionShopCorporationName;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime registrationTime;
	private String registrantCd;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime updateTime;
	private String updaterCd;
	private String action;
	private Long pastId;

	public BigDecimal getCompetitionShopCorporationId() {
		return competitionShopCorporationId;
	}

	public void setCompetitionShopCorporationId(BigDecimal competitionShopCorporationId) {
		this.competitionShopCorporationId = competitionShopCorporationId;
	}

	public String getCompetitionShopCorporationCd() {
		return competitionShopCorporationCd;
	}

	public void setCompetitionShopCorporationCd(String competitionShopCorporationCd) {
		this.competitionShopCorporationCd = competitionShopCorporationCd;
	}

	public String getCompetitionShopCorporationName() {
		return competitionShopCorporationName;
	}

	public void setCompetitionShopCorporationName(String competitionShopCorporationName) {
		this.competitionShopCorporationName = competitionShopCorporationName;
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
	protected ModelMapper<ICompetitionShopCorporation> modelMapper(SqlSession session) {
		return session.getMapper(ICompetitionShopCorporationModelMapper.class);
	}

}
