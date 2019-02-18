package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.BuildingTradeArea;

public class BuildingTradeAreaRelationBuildingDetailDTO implements DTO<BuildingTradeArea> {

	private Long id;
	private Integer tradeAreaPopulation1Km;
	private Integer male1Km;
	private Integer female1Km;
	private Integer daytimePopulation1Km;
	private Integer nighttimePopulation1Km;
	private Integer retailSales1Km;
	private Long salesFloorArea1Km;
	private Long departmentOccupancy1Km;
	private Long populationPower1Km;
	private Integer tradeAreaPower1Km;
	private Integer tradeAreaPopulation3Km;
	private Integer male3Km;
	private Integer female3Km;
	private Integer situatedInRetailSales3Km;
	private Long withinSalesFloorArea3Km;
	private Long withinSalesFloorAreaAddingUp3Km;
	private Long departmentOccupancy3Km;
	private Long populationPower3Km;
	private Integer tradeAreaPower3Km;
	private Integer tradeAreaPopulation5Km;
	private Integer male5Km;
	private Integer female5Km;
	private Integer situatedInRetailSales5Km;
	private Long withinSalesFloorArea5Km;
	private Long withinSalesFloorAreaAddingUp5Km;
	private Long departmentOccupancy5Km;
	private Long populationPower5Km;
	private Integer tradeAreaPower5Km;
	private Integer tradeAreaPopulation10Km;
	private Integer male10Km;
	private Integer female10Km;
	private Integer situatedInRetailSales10Km;
	private Long withinSalesFloorArea10Km;
	private Long withinSalesFloorAreaAddingUp10Km;
	private Long departmentOccupancy10Km;
	private Long populationPower10Km;
	private Integer tradeAreaPower10Km;
	private Integer drivePopulationWithin5Minutes;
	private Integer drivePopulationWithin15Minutes;
	private Integer drivePopulationWithin20Minutes;
	private Integer drivePopulationWithin30Minutes;
	private Integer expensesPerPeople;
	private Integer incomePerPeople;
	private String tradeAreaAnalysisGisPath;

	public BuildingTradeAreaRelationBuildingDetailDTO() {

	}

	public BuildingTradeAreaRelationBuildingDetailDTO(BuildingTradeArea buildingTradeArea) {
		this.copyProperties(this, buildingTradeArea);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTradeAreaPopulation1Km() {
		return tradeAreaPopulation1Km;
	}

	public void setTradeAreaPopulation1Km(Integer tradeAreaPopulation1Km) {
		this.tradeAreaPopulation1Km = tradeAreaPopulation1Km;
	}

	public Integer getMale1Km() {
		return male1Km;
	}

	public void setMale1Km(Integer male1Km) {
		this.male1Km = male1Km;
	}

	public Integer getFemale1Km() {
		return female1Km;
	}

	public void setFemale1Km(Integer female1Km) {
		this.female1Km = female1Km;
	}

	public Integer getDaytimePopulation1Km() {
		return daytimePopulation1Km;
	}

	public void setDaytimePopulation1Km(Integer daytimePopulation1Km) {
		this.daytimePopulation1Km = daytimePopulation1Km;
	}

	public Integer getNighttimePopulation1Km() {
		return nighttimePopulation1Km;
	}

	public void setNighttimePopulation1Km(Integer nighttimePopulation1Km) {
		this.nighttimePopulation1Km = nighttimePopulation1Km;
	}

	public Integer getRetailSales1Km() {
		return retailSales1Km;
	}

	public void setRetailSales1Km(Integer retailSales1Km) {
		this.retailSales1Km = retailSales1Km;
	}

	public Long getSalesFloorArea1Km() {
		return salesFloorArea1Km;
	}

	public void setSalesFloorArea1Km(Long salesFloorArea1Km) {
		this.salesFloorArea1Km = salesFloorArea1Km;
	}

	public Long getDepartmentOccupancy1Km() {
		return departmentOccupancy1Km;
	}

	public void setDepartmentOccupancy1Km(Long departmentOccupancy1Km) {
		this.departmentOccupancy1Km = departmentOccupancy1Km;
	}

	public Long getPopulationPower1Km() {
		return populationPower1Km;
	}

	public void setPopulationPower1Km(Long populationPower1Km) {
		this.populationPower1Km = populationPower1Km;
	}

	public Integer getTradeAreaPower1Km() {
		return tradeAreaPower1Km;
	}

	public void setTradeAreaPower1Km(Integer tradeAreaPower1Km) {
		this.tradeAreaPower1Km = tradeAreaPower1Km;
	}

	public Integer getTradeAreaPopulation3Km() {
		return tradeAreaPopulation3Km;
	}

	public void setTradeAreaPopulation3Km(Integer tradeAreaPopulation3Km) {
		this.tradeAreaPopulation3Km = tradeAreaPopulation3Km;
	}

	public Integer getMale3Km() {
		return male3Km;
	}

	public void setMale3Km(Integer male3Km) {
		this.male3Km = male3Km;
	}

	public Integer getFemale3Km() {
		return female3Km;
	}

	public void setFemale3Km(Integer female3Km) {
		this.female3Km = female3Km;
	}

	public Integer getSituatedInRetailSales3Km() {
		return situatedInRetailSales3Km;
	}

	public void setSituatedInRetailSales3Km(Integer situatedInRetailSales3Km) {
		this.situatedInRetailSales3Km = situatedInRetailSales3Km;
	}

	public Long getWithinSalesFloorArea3Km() {
		return withinSalesFloorArea3Km;
	}

	public void setWithinSalesFloorArea3Km(Long withinSalesFloorArea3Km) {
		this.withinSalesFloorArea3Km = withinSalesFloorArea3Km;
	}

	public Long getWithinSalesFloorAreaAddingUp3Km() {
		return withinSalesFloorAreaAddingUp3Km;
	}

	public void setWithinSalesFloorAreaAddingUp3Km(Long withinSalesFloorAreaAddingUp3Km) {
		this.withinSalesFloorAreaAddingUp3Km = withinSalesFloorAreaAddingUp3Km;
	}

	public Long getDepartmentOccupancy3Km() {
		return departmentOccupancy3Km;
	}

	public void setDepartmentOccupancy3Km(Long departmentOccupancy3Km) {
		this.departmentOccupancy3Km = departmentOccupancy3Km;
	}

	public Long getPopulationPower3Km() {
		return populationPower3Km;
	}

	public void setPopulationPower3Km(Long populationPower3Km) {
		this.populationPower3Km = populationPower3Km;
	}

	public Integer getTradeAreaPower3Km() {
		return tradeAreaPower3Km;
	}

	public void setTradeAreaPower3Km(Integer tradeAreaPower3Km) {
		this.tradeAreaPower3Km = tradeAreaPower3Km;
	}

	public Integer getTradeAreaPopulation5Km() {
		return tradeAreaPopulation5Km;
	}

	public void setTradeAreaPopulation5Km(Integer tradeAreaPopulation5Km) {
		this.tradeAreaPopulation5Km = tradeAreaPopulation5Km;
	}

	public Integer getMale5Km() {
		return male5Km;
	}

	public void setMale5Km(Integer male5Km) {
		this.male5Km = male5Km;
	}

	public Integer getFemale5Km() {
		return female5Km;
	}

	public void setFemale5Km(Integer female5Km) {
		this.female5Km = female5Km;
	}

	public Integer getSituatedInRetailSales5Km() {
		return situatedInRetailSales5Km;
	}

	public void setSituatedInRetailSales5Km(Integer situatedInRetailSales5Km) {
		this.situatedInRetailSales5Km = situatedInRetailSales5Km;
	}

	public Long getWithinSalesFloorArea5Km() {
		return withinSalesFloorArea5Km;
	}

	public void setWithinSalesFloorArea5Km(Long withinSalesFloorArea5Km) {
		this.withinSalesFloorArea5Km = withinSalesFloorArea5Km;
	}

	public Long getWithinSalesFloorAreaAddingUp5Km() {
		return withinSalesFloorAreaAddingUp5Km;
	}

	public void setWithinSalesFloorAreaAddingUp5Km(Long withinSalesFloorAreaAddingUp5Km) {
		this.withinSalesFloorAreaAddingUp5Km = withinSalesFloorAreaAddingUp5Km;
	}

	public Long getDepartmentOccupancy5Km() {
		return departmentOccupancy5Km;
	}

	public void setDepartmentOccupancy5Km(Long departmentOccupancy5Km) {
		this.departmentOccupancy5Km = departmentOccupancy5Km;
	}

	public Long getPopulationPower5Km() {
		return populationPower5Km;
	}

	public void setPopulationPower5Km(Long populationPower5Km) {
		this.populationPower5Km = populationPower5Km;
	}

	public Integer getTradeAreaPower5Km() {
		return tradeAreaPower5Km;
	}

	public void setTradeAreaPower5Km(Integer tradeAreaPower5Km) {
		this.tradeAreaPower5Km = tradeAreaPower5Km;
	}

	public Integer getTradeAreaPopulation10Km() {
		return tradeAreaPopulation10Km;
	}

	public void setTradeAreaPopulation10Km(Integer tradeAreaPopulation10Km) {
		this.tradeAreaPopulation10Km = tradeAreaPopulation10Km;
	}

	public Integer getMale10Km() {
		return male10Km;
	}

	public void setMale10Km(Integer male10Km) {
		this.male10Km = male10Km;
	}

	public Integer getFemale10Km() {
		return female10Km;
	}

	public void setFemale10Km(Integer female10Km) {
		this.female10Km = female10Km;
	}

	public Integer getSituatedInRetailSales10Km() {
		return situatedInRetailSales10Km;
	}

	public void setSituatedInRetailSales10Km(Integer situatedInRetailSales10Km) {
		this.situatedInRetailSales10Km = situatedInRetailSales10Km;
	}

	public Long getWithinSalesFloorArea10Km() {
		return withinSalesFloorArea10Km;
	}

	public void setWithinSalesFloorArea10Km(Long withinSalesFloorArea10Km) {
		this.withinSalesFloorArea10Km = withinSalesFloorArea10Km;
	}

	public Long getWithinSalesFloorAreaAddingUp10Km() {
		return withinSalesFloorAreaAddingUp10Km;
	}

	public void setWithinSalesFloorAreaAddingUp10Km(Long withinSalesFloorAreaAddingUp10Km) {
		this.withinSalesFloorAreaAddingUp10Km = withinSalesFloorAreaAddingUp10Km;
	}

	public Long getDepartmentOccupancy10Km() {
		return departmentOccupancy10Km;
	}

	public void setDepartmentOccupancy10Km(Long departmentOccupancy10Km) {
		this.departmentOccupancy10Km = departmentOccupancy10Km;
	}

	public Long getPopulationPower10Km() {
		return populationPower10Km;
	}

	public void setPopulationPower10Km(Long populationPower10Km) {
		this.populationPower10Km = populationPower10Km;
	}

	public Integer getTradeAreaPower10Km() {
		return tradeAreaPower10Km;
	}

	public void setTradeAreaPower10Km(Integer tradeAreaPower10Km) {
		this.tradeAreaPower10Km = tradeAreaPower10Km;
	}

	public Integer getDrivePopulationWithin5Minutes() {
		return drivePopulationWithin5Minutes;
	}

	public void setDrivePopulationWithin5Minutes(Integer drivePopulationWithin5Minutes) {
		this.drivePopulationWithin5Minutes = drivePopulationWithin5Minutes;
	}

	public Integer getDrivePopulationWithin15Minutes() {
		return drivePopulationWithin15Minutes;
	}

	public void setDrivePopulationWithin15Minutes(Integer drivePopulationWithin15Minutes) {
		this.drivePopulationWithin15Minutes = drivePopulationWithin15Minutes;
	}

	public Integer getDrivePopulationWithin20Minutes() {
		return drivePopulationWithin20Minutes;
	}

	public void setDrivePopulationWithin20Minutes(Integer drivePopulationWithin20Minutes) {
		this.drivePopulationWithin20Minutes = drivePopulationWithin20Minutes;
	}

	public Integer getDrivePopulationWithin30Minutes() {
		return drivePopulationWithin30Minutes;
	}

	public void setDrivePopulationWithin30Minutes(Integer drivePopulationWithin30Minutes) {
		this.drivePopulationWithin30Minutes = drivePopulationWithin30Minutes;
	}

	public Integer getExpensesPerPeople() {
		return expensesPerPeople;
	}

	public void setExpensesPerPeople(Integer expensesPerPeople) {
		this.expensesPerPeople = expensesPerPeople;
	}

	public Integer getIncomePerPeople() {
		return incomePerPeople;
	}

	public void setIncomePerPeople(Integer incomePerPeople) {
		this.incomePerPeople = incomePerPeople;
	}

	public String getTradeAreaAnalysisGisPath() {
		return tradeAreaAnalysisGisPath;
	}

	public void setTradeAreaAnalysisGisPath(String tradeAreaAnalysisGisPath) {
		this.tradeAreaAnalysisGisPath = tradeAreaAnalysisGisPath;
	}

	@Override
	public BuildingTradeArea createModel() {
		return new BuildingTradeArea();
	}

}
