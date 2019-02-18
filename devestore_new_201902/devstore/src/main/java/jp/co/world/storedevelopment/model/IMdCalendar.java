package jp.co.world.storedevelopment.model;

import java.math.BigDecimal;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.IMdCalendarModelMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class IMdCalendar extends IActiveModel<IMdCalendar> {

	private BigDecimal status;
	private String dataDivision1;
	private String dataDivision2;
	private Integer seqno;
	private String calendarDate;
	private String salesMdYear;
	private String salesMdMonth;
	private String salesMdWeekly;
	private String backupK;
	private String publicHolidayK;
	private String appropriatingYearMonth;
	private String closingFg;
	private String registrationDate;
	private String registrationTm;
	private String updateDate;
	private String updateTm;
	private String lastCalendarDate;
	private String lastSalesMdYear;
	private String lastSalesMdMonth;
	private String lastSalesMdWeekly;
	private String lastBackupK;

	public IMdCalendar() {

	}

	public BigDecimal getStatus() {
		return status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getDataDivision1() {
		return dataDivision1;
	}

	public void setDataDivision1(String dataDivision1) {
		this.dataDivision1 = dataDivision1;
	}

	public String getDataDivision2() {
		return dataDivision2;
	}

	public void setDataDivision2(String dataDivision2) {
		this.dataDivision2 = dataDivision2;
	}

	public Integer getSeqno() {
		return seqno;
	}

	public void setSeqno(Integer seqno) {
		this.seqno = seqno;
	}

	public String getCalendarDate() {
		return calendarDate;
	}

	public void setCalendarDate(String calendarDate) {
		this.calendarDate = calendarDate;
	}

	public String getSalesMdYear() {
		return salesMdYear;
	}

	public void setSalesMdYear(String salesMdYear) {
		this.salesMdYear = salesMdYear;
	}

	public String getSalesMdMonth() {
		return salesMdMonth;
	}

	public void setSalesMdMonth(String salesMdMonth) {
		this.salesMdMonth = salesMdMonth;
	}

	public String getSalesMdWeekly() {
		return salesMdWeekly;
	}

	public void setSalesMdWeekly(String salesMdWeekly) {
		this.salesMdWeekly = salesMdWeekly;
	}

	public String getBackupK() {
		return backupK;
	}

	public void setBackupK(String backupK) {
		this.backupK = backupK;
	}

	public String getPublicHolidayK() {
		return publicHolidayK;
	}

	public void setPublicHolidayK(String publicHolidayK) {
		this.publicHolidayK = publicHolidayK;
	}

	public String getAppropriatingYearMonth() {
		return appropriatingYearMonth;
	}

	public void setAppropriatingYearMonth(String appropriatingYearMonth) {
		this.appropriatingYearMonth = appropriatingYearMonth;
	}

	public String getClosingFg() {
		return closingFg;
	}

	public void setClosingFg(String closingFg) {
		this.closingFg = closingFg;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getRegistrationTm() {
		return registrationTm;
	}

	public void setRegistrationTm(String registrationTm) {
		this.registrationTm = registrationTm;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateTm() {
		return updateTm;
	}

	public void setUpdateTm(String updateTm) {
		this.updateTm = updateTm;
	}

	public String getLastCalendarDate() {
		return lastCalendarDate;
	}

	public void setLastCalendarDate(String lastCalendarDate) {
		this.lastCalendarDate = lastCalendarDate;
	}

	public String getLastSalesMdYear() {
		return lastSalesMdYear;
	}

	public void setLastSalesMdYear(String lastSalesMdYear) {
		this.lastSalesMdYear = lastSalesMdYear;
	}

	public String getLastSalesMdMonth() {
		return lastSalesMdMonth;
	}

	public void setLastSalesMdMonth(String lastSalesMdMonth) {
		this.lastSalesMdMonth = lastSalesMdMonth;
	}

	public String getLastSalesMdWeekly() {
		return lastSalesMdWeekly;
	}

	public void setLastSalesMdWeekly(String lastSalesMdWeekly) {
		this.lastSalesMdWeekly = lastSalesMdWeekly;
	}

	public String getLastBackupK() {
		return lastBackupK;
	}

	public void setLastBackupK(String lastBackupK) {
		this.lastBackupK = lastBackupK;
	}

	@Override
	protected ModelMapper<IMdCalendar> modelMapper(SqlSession session) {
		return session.getMapper(IMdCalendarModelMapper.class);
	}

}
