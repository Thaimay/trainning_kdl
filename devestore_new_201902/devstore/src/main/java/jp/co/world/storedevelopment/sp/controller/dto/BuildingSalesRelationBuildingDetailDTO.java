package jp.co.world.storedevelopment.sp.controller.dto;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.BuildingSales;
import jp.co.world.storedevelopment.model.MBuildingSalesClassifications;
import jp.co.world.storedevelopment.model.MBuildingSalesTypes;
import jp.co.world.storedevelopment.model.mapper.repository.MBuildingSalesClassificationsRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MBuildingSalesTypesRepository;

public class BuildingSalesRelationBuildingDetailDTO implements DTO<BuildingSales> {

	private Long id;
	private Long division;
	private BigDecimal sales;
	private BigDecimal area;
	private BigDecimal monthBasis;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate financialMonth;
	private Long type;
	private Boolean isDeleted;

	public BuildingSalesRelationBuildingDetailDTO() {

	}

	public BuildingSalesRelationBuildingDetailDTO(BuildingSales buildingSales) {
		this.copyProperties(this, buildingSales);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDivision() {
		return division;
	}

	public void setDivision(Long division) {
		this.division = division;
	}

	public BigDecimal getSales() {
		return sales.divide(new BigDecimal(1000000), 0, BigDecimal.ROUND_HALF_UP);
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
		return monthBasis.divide(new BigDecimal(1000), 0, BigDecimal.ROUND_HALF_UP);
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

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public MBuildingSalesClassificationsRelationBuildingDetailDTO getmBuildingSalesClassifications() {
		if (getDivision() == null) {
			return null;
		}

		Optional<MBuildingSalesClassifications> mBuildingSalesClassifications = new MBuildingSalesClassificationsRepository()
				.findById(getDivision());
		return mBuildingSalesClassifications.isPresent()
				? new MBuildingSalesClassificationsRelationBuildingDetailDTO(mBuildingSalesClassifications.get())
				: null;
	}

	public MBuildingSalesTypesRelationBuildingDetailDTO getmBuildingSalesTypes() {
		if (getType() == null) {
			return null;
		}

		Optional<MBuildingSalesTypes> mBuildingSalesTypes = new MBuildingSalesTypesRepository().findById(getType());
		return mBuildingSalesTypes.isPresent()
				? new MBuildingSalesTypesRelationBuildingDetailDTO(mBuildingSalesTypes.get())
				: null;
	}

	@Override
	public BuildingSales createModel() {
		return new BuildingSales();
	}

	private static String formart(String n) { 
		double amount = Double.parseDouble(n);
		DecimalFormat formatter = new DecimalFormat("###,###");
		return formatter.format(amount);
	}
	
	public String getSalesString() {
		return formart(getSales().toString());
	}
	
	public String getAreaString() {
		return formart(getArea().toString());
	}
	
	public String getMonthBasisString() {
		return formart(getMonthBasis().toString());
	}
}
