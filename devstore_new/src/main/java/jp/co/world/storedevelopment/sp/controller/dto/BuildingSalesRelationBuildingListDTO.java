package jp.co.world.storedevelopment.sp.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.BuildingSales;

public class BuildingSalesRelationBuildingListDTO implements DTO<BuildingSales> {

	private Long id;
	private String buildingCd;
	private Long division;
	private BigDecimal sales;
	private BigDecimal area;
	private BigDecimal monthBasis;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate financialMonth;
	private Long type;

	public BuildingSalesRelationBuildingListDTO() {

	}

	public BuildingSalesRelationBuildingListDTO(BuildingSales buildingSales) {
		this.copyProperties(this, buildingSales);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getSales() {
		return sales.divide(new BigDecimal(1000000), 0, BigDecimal.ROUND_HALF_UP);
	}

	public void setSales(BigDecimal sales) {
		this.sales = sales;
	}

	public LocalDate getFinancialMonth() {
		return financialMonth;
	}

	public void setFinancialMonth(LocalDate financialMonth) {
		this.financialMonth = financialMonth;
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

	public BigDecimal getArea() {
		return area;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
	}

	public BigDecimal getMonthBasis() {
		return monthBasis.divide(new BigDecimal(1000), 0, BigDecimal.ROUND_HALF_UP);
	}

	public void setMonthBasis(BigDecimal monthBasis) {
		this.monthBasis = monthBasis;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	@Override
	public BuildingSales createModel() {
		return new BuildingSales();
	}

}
