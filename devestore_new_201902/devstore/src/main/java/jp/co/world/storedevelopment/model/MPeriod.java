package jp.co.world.storedevelopment.model;

import java.time.LocalDate;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.MPeriodModelMapper;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class MPeriod extends ActiveModel<MPeriod> {

	private Integer period;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate firstHalfEndDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate secondHalfStartDate;

	private String[] ignoreFields = new String[] {};

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	@Override
	protected ModelMapper<MPeriod> modelMapper(SqlSession session) {
		return session.getMapper(MPeriodModelMapper.class);
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalDate getFirstHalfEndDate() {
		return firstHalfEndDate;
	}

	public void setFirstHalfEndDate(LocalDate firstHalfEndDate) {
		this.firstHalfEndDate = firstHalfEndDate;
	}

	public LocalDate getSecondHalfStartDate() {
		return secondHalfStartDate;
	}

	public void setSecondHalfStartDate(LocalDate secondHalfStartDate) {
		this.secondHalfStartDate = secondHalfStartDate;
	}

}
