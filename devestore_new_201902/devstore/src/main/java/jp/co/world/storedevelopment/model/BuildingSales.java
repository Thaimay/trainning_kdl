package jp.co.world.storedevelopment.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.apache.ibatis.session.SqlSession;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.BuildingSalesModelMapper;

/**
 * @author hungdh
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class BuildingSales extends ActiveModel<BuildingSales> {

	private String buildingCd;
	private Long division;
	private BigDecimal sales;
	private BigDecimal area;
	private BigDecimal monthBasis;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate financialMonth;
	private Long type;

	private String[] ignoreFields = new String[] {};

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	public void setIgnoreFields(String[] ignoreFields) {
		this.ignoreFields = ignoreFields;
	}

	public String getBuildingCd() {
		return buildingCd;
	}

	public void setBuildingCd(String buildingCd) {
		this.buildingCd = buildingCd;
	}

	public Long getDivision() {
		return division;
	}

	public void setDivision(Long division) {
		this.division = division;
	}

	public BigDecimal getSales() {
		return sales;
	}

	public void setSales(BigDecimal sales) {
		this.sales = sales;
	}

	public BigDecimal getArea() {
		return area;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
	}

	public BigDecimal getMonthBasis() {
		return monthBasis;
	}

	public void setMonthBasis(BigDecimal monthBasis) {
		this.monthBasis = monthBasis;
	}

	public LocalDate getFinancialMonth() {
		return financialMonth;
	}

	public void setFinancialMonth(LocalDate financialMonth) {
		this.financialMonth = financialMonth;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	@Override
	protected ModelMapper<BuildingSales> modelMapper(SqlSession session) {
		return session.getMapper(BuildingSalesModelMapper.class);
	}

}
