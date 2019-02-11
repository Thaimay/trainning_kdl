package jp.co.world.storedevelopment.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.dev.RandomTestData;
import jp.co.world.storedevelopment.model.mapper.ICorporationGroupModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class ICorporationGroup extends IActiveModel<ICorporationGroup> {

	private BigDecimal corporationGpId;
	private String corporationGpCd;
	private String corporationGpName;
	private String corporationGpNameKana;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime coordinationCreatedDatetime;
	private String coordinationCreatedAccountCode;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime coordinationUpdateDatetime;
	private String coordinationUpdateAccountCode;
	private String action;

	private List<ICorporation> iCorporations;

	private String[] ignoreFields = new String[] { "iCorporations" };

	public ICorporationGroup() {

	}

	public ICorporationGroup(String corporationGpName) {
		this.setCorporationGpId(new BigDecimal(RandomTestData.getInteger(3)));
		this.setCorporationGpCd(RandomTestData.getString(4, false, true));
		this.setCorporationGpName(corporationGpName);
		this.setCorporationGpNameKana(corporationGpName);
		this.setCreatedAccountCode(new AccountRepository().getHead().get().getEmployeeCd());
		this.setUpdateAccountCode(new AccountRepository().getHead().get().getEmployeeCd());
	}

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	public void setIgnoreFields(String[] ignoreFields) {
		this.ignoreFields = ignoreFields;
	}

	public BigDecimal getCorporationGpId() {
		return corporationGpId;
	}

	public void setCorporationGpId(BigDecimal corporationGpId) {
		this.corporationGpId = corporationGpId;
	}

	public String getCorporationGpCd() {
		return corporationGpCd;
	}

	public void setCorporationGpCd(String corporationGpCd) {
		this.corporationGpCd = corporationGpCd;
	}

	public String getCorporationGpName() {
		return corporationGpName;
	}

	public void setCorporationGpName(String corporationGpName) {
		this.corporationGpName = corporationGpName;
	}

	public String getCorporationGpNameKana() {
		return corporationGpNameKana;
	}

	public void setCorporationGpNameKana(String corporationGpNameKana) {
		this.corporationGpNameKana = corporationGpNameKana;
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

	public List<ICorporation> getiCorporations() {
		return iCorporations;
	}

	public void setiCorporations(List<ICorporation> iCorporations) {
		this.iCorporations = iCorporations;
	}

	@Override
	protected ModelMapper<ICorporationGroup> modelMapper(SqlSession session) {
		return session.getMapper(ICorporationGroupModelMapper.class);
	}

}
