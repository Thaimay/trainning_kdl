package jp.co.world.storedevelopment.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.dev.RandomTestData;
import jp.co.world.storedevelopment.model.mapper.IIncomeUnitModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class IIncomeUnit extends IActiveModel<IIncomeUnit> {

	private BigDecimal incomeUnitId;
	private String incomeUnitCd;
	private String incomeUnitName;
	private BigDecimal companyId;
	private String coordinationCreator;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime coordinationCreationTime;
	private String coordinationDeletingFlag;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime coordinationApplyingDate;
	private String coordinationUpdater;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime coordinationUpdateTime;

	public IIncomeUnit() {

	}

	public IIncomeUnit(String name) {
		this.setIncomeUnitId(BigDecimal.valueOf(RandomTestData.getInteger(3)));
		this.setIncomeUnitCd(RandomTestData.getString(4, false, true));
		this.setIncomeUnitName(name);
		this.setCreatedAccountCode(new AccountRepository().getHead().get().getEmployeeCd());
		this.setUpdateAccountCode(new AccountRepository().getHead().get().getEmployeeCd());
	}

	public BigDecimal getIncomeUnitId() {
		return incomeUnitId;
	}

	public void setIncomeUnitId(BigDecimal incomeUnitId) {
		this.incomeUnitId = incomeUnitId;
	}

	public String getIncomeUnitCd() {
		return incomeUnitCd;
	}

	public void setIncomeUnitCd(String incomeUnitCd) {
		this.incomeUnitCd = incomeUnitCd;
	}

	public String getIncomeUnitName() {
		return incomeUnitName;
	}

	public void setIncomeUnitName(String incomeUnitName) {
		this.incomeUnitName = incomeUnitName;
	}

	public BigDecimal getCompanyId() {
		return companyId;
	}

	public void setCompanyId(BigDecimal companyId) {
		this.companyId = companyId;
	}

	@Override
	protected ModelMapper<IIncomeUnit> modelMapper(SqlSession session) {
		return session.getMapper(IIncomeUnitModelMapper.class);
	}

	public String getCoordinationCreator() {
		return coordinationCreator;
	}

	public void setCoordinationCreator(String coordinationCreator) {
		this.coordinationCreator = coordinationCreator;
	}

	public String getCoordinationDeletingFlag() {
		return coordinationDeletingFlag;
	}

	public void setCoordinationDeletingFlag(String coordinationDeletingFlag) {
		this.coordinationDeletingFlag = coordinationDeletingFlag;
	}

	public String getCoordinationUpdater() {
		return coordinationUpdater;
	}

	public void setCoordinationUpdater(String coordinationUpdater) {
		this.coordinationUpdater = coordinationUpdater;
	}

	public LocalDateTime getCoordinationCreationTime() {
		return coordinationCreationTime;
	}

	public void setCoordinationCreationTime(LocalDateTime coordinationCreationTime) {
		this.coordinationCreationTime = coordinationCreationTime;
	}

	public LocalDateTime getCoordinationApplyingDate() {
		return coordinationApplyingDate;
	}

	public void setCoordinationApplyingDate(LocalDateTime coordinationApplyingDate) {
		this.coordinationApplyingDate = coordinationApplyingDate;
	}

	public LocalDateTime getCoordinationUpdateTime() {
		return coordinationUpdateTime;
	}

	public void setCoordinationUpdateTime(LocalDateTime coordinationUpdateTime) {
		this.coordinationUpdateTime = coordinationUpdateTime;
	}

}
