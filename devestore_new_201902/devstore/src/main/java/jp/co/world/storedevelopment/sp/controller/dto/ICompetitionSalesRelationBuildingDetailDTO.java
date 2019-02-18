package jp.co.world.storedevelopment.sp.controller.dto;

import java.math.BigDecimal;

import jp.co.world.storedevelopment.model.ICompetitionSales;

public class ICompetitionSalesRelationBuildingDetailDTO implements DTO<ICompetitionSales> {

	private Long competitionShopId;
	private String competitionShopName;
	private BigDecimal tsuboNum;
	private BigDecimal n5Num;
	private BigDecimal n4Num;
	private BigDecimal n3Num;
	private BigDecimal n2Num;
	private BigDecimal n1Num;
	private BigDecimal nNum;

	public ICompetitionSalesRelationBuildingDetailDTO() {

	}

	public Long getCompetitionShopId() {
		return competitionShopId;
	}

	public void setCompetitionShopId(Long competitionShopId) {
		this.competitionShopId = competitionShopId;
	}

	public String getCompetitionShopName() {
		return competitionShopName;
	}

	public void setCompetitionShopName(String competitionShopName) {
		this.competitionShopName = competitionShopName;
	}

	public BigDecimal getTsuboNum() {
		return tsuboNum;
	}

	public void setTsuboNum(BigDecimal tsuboNum) {
		this.tsuboNum = tsuboNum;
	}

	public BigDecimal getN5Num() {
		return n5Num;
	}

	public void setN5Num(BigDecimal n5Num) {
		this.n5Num = n5Num;
	}

	public BigDecimal getN4Num() {
		return n4Num;
	}

	public void setN4Num(BigDecimal n4Num) {
		this.n4Num = n4Num;
	}

	public BigDecimal getN3Num() {
		return n3Num;
	}

	public void setN3Num(BigDecimal n3Num) {
		this.n3Num = n3Num;
	}

	public BigDecimal getN2Num() {
		return n2Num;
	}

	public void setN2Num(BigDecimal n2Num) {
		this.n2Num = n2Num;
	}

	public BigDecimal getN1Num() {
		return n1Num;
	}

	public void setN1Num(BigDecimal n1Num) {
		this.n1Num = n1Num;
	}

	public BigDecimal getnNum() {
		return nNum;
	}

	public void setnNum(BigDecimal nNum) {
		this.nNum = nNum;
	}

	@Override
	public ICompetitionSales createModel() {
		return new ICompetitionSales();
	}

}
