package jp.co.world.storedevelopment.model;

import java.math.BigDecimal;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ICompetitionSalesModelMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class ICompetitionSales extends IActiveModel<ICompetitionSales> {

	private String buildingCd;
	private String buildingName;
	private Long competitionShopId;
	private String competitionShopName;
	private BigDecimal tsuboNum;
	private BigDecimal yearMonth;
	private BigDecimal salesRatio;
	private int inputActiveDays;
	private int dispOrder;

	@Override
	protected ModelMapper<ICompetitionSales> modelMapper(SqlSession session) {
		return session.getMapper(ICompetitionSalesModelMapper.class);
	}

	public String getBuildingCd() {
		return buildingCd;
	}

	public void setBuildingCd(String buildingCd) {
		this.buildingCd = buildingCd;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
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

	public int getInputActiveDays() {
		return inputActiveDays;
	}

	public void setInputActiveDays(int inputActiveDays) {
		this.inputActiveDays = inputActiveDays;
	}

	public int getDispOrder() {
		return dispOrder;
	}

	public void setDispOrder(int dispOrder) {
		this.dispOrder = dispOrder;
	}

	public BigDecimal getTsuboNum() {
		return tsuboNum;
	}

	public void setTsuboNum(BigDecimal tsuboNum) {
		this.tsuboNum = tsuboNum;
	}

	public BigDecimal getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(BigDecimal yearMonth) {
		this.yearMonth = yearMonth;
	}

	public BigDecimal getSalesRatio() {
		return salesRatio;
	}

	public void setSalesRatio(BigDecimal salesRatio) {
		this.salesRatio = salesRatio;
	}

}
