package jp.co.world.storedevelopment.sp.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.IRentContract;
import jp.co.world.storedevelopment.model.ISalesAgency;
import jp.co.world.storedevelopment.model.ISalesAgencyCondition;
import jp.co.world.storedevelopment.model.ISalesAgencyContract;
import jp.co.world.storedevelopment.model.ISalesAgencyTarget;
import jp.co.world.storedevelopment.model.IShop;
import jp.co.world.storedevelopment.model.IShopAdmin;
import jp.co.world.storedevelopment.model.MShopType;
import jp.co.world.storedevelopment.model.mapper.repository.IBrandIncomeUnitRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IRentContractRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesAgencyConditionRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesAgencyContractRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesAgencyRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesAgencyTargetRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IShopAdminRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IShopRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MShopTypeRepository;

public class ProjectReferenceShopDTO {
	private Long buildingId = null;
	private String buildingName = "";
	private String shopCd = "";
	private String shopName = "";
	private String section = "";
	private String frontage = "";
	private String floorNum = "";
	private BigDecimal contractTsubo;
	private String businessHours = "";
	private Integer buildingExpectedValue;
	private String remarks = "";
	private List<NegotiationRelationDTO> listBrandIncome = new ArrayList<NegotiationRelationDTO>();
	private NegotiationRelationDTO listSalesAgency;
	private IRentContractRelationShopDetailDTO rentContract;
	private Boolean isDisplayEconomy = false;
	private NegotiationRelationDTO salesAgencyTarget;
	private ISalesAgencyContractRelationShopDTO salesAgencyContract;
	private ISalesAgencyConditionRelationShopDTO salesAgencyCondition;
	private NegotiationRelationDTO participatingStoreCorporation;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate rentStartDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate rentEndDate;
	private Float rentYear;
	private String operationForm = "";

	public ProjectReferenceShopDTO(Long iShopId, Account a) {
		if (iShopId != null) {
			Optional<IShop> optShop = new IShopRepository().findById(iShopId);
			if (optShop.isPresent()) {
				shopCd = optShop.get().getShopCd();
				shopName = optShop.get().getShopNameZenkaku();
				contractTsubo = optShop.get().getContractTsubo();
				floorNum = optShop.get().getFloorNum();
				Long shopId = optShop.get().getShopId();

				IShopRelationShopDetailDTO tempDto = new IShopRelationShopDetailDTO(optShop.get(), a);

				if (tempDto.getBuilding() != null) {
					BuildingRelationShopDetailDTO building = tempDto.getBuilding();
					buildingId = building.getId();
					buildingName = building.getName();
				}

				ShopDetailDTO shop = tempDto.getShop();
				if (shop != null) {
					section = shop.getSection();
					frontage = shop.getFrontage();
					buildingExpectedValue = shop.getBuildingExpectedValue();
					remarks = shop.getRemarks();

					businessHours = shop.getBusinessHours();
					rentStartDate = shop.getRentStartDate();
					rentEndDate = shop.getRentEndDate();
					rentYear = shop.getRentYear();

					if (shop.getParticipatingStoreCorporation() != null) {
						participatingStoreCorporation = new NegotiationRelationDTO(
								shop.getParticipatingStoreCorporation().getId(),
								shop.getParticipatingStoreCorporation().getName());
					}
				}

				IRentContract iRentContract = new IRentContractRepository().findRentContractRefShop(shopId);
				if (iRentContract != null) {
					if (iRentContract.getContractTypeId() == null && tempDto.inShopTownGroup()
							&& !tempDto.getShopTownParentId().equals(shopId.toString())) {
						Optional<Long> optContractTypeId = new IRentContractRepository()
								.findParentContractTypeId(tempDto.getShopTownParentId());
						if (optContractTypeId.isPresent()) {
							iRentContract.setContractTypeId(optContractTypeId.get());
						}
					}

					rentContract = new IRentContractRelationShopDetailDTO(iRentContract);
				}

				isDisplayEconomy = tempDto.isDisplayEconomy();

				ISalesAgencyContract contract = new ISalesAgencyContractRepository()
						.findSalesAgencyContractRefShop(shopId);
				if (contract != null) {
					if (contract.getSalesAgencyTargetId() == null && tempDto.inShopTownGroup()
							&& !tempDto.getShopTownParentId().equals(shopId.toString())) {
						Optional<Long> optSalesAgencyTargetId = new ISalesAgencyContractRepository()
								.findParentSalesAgencyTargetId(tempDto.getShopTownParentId());
						if (optSalesAgencyTargetId.isPresent()) {
							contract.setSalesAgencyTargetId(optSalesAgencyTargetId.get());
						}
					}
					if (contract.getSalesAgencyTargetId() != null) {
						Optional<ISalesAgencyTarget> opt = new ISalesAgencyTargetRepository()
								.findBySalesAgencyTargetId(contract.getSalesAgencyTargetId());
						if (opt.isPresent()) {
							salesAgencyTarget = new NegotiationRelationDTO(opt.get().getId(),
									opt.get().getSalesAgencyTargetName());
						}
					}
					salesAgencyContract = new ISalesAgencyContractRelationShopDTO(contract);
				}

				ISalesAgencyCondition condition = new ISalesAgencyConditionRepository()
						.findSalesAgencyConditionRefShop(shopId);
				if (condition != null) {
					salesAgencyCondition = new ISalesAgencyConditionRelationShopDTO(condition);
				}

				Optional<IShopAdmin> optShopAdmin = new IShopAdminRepository().findByShopId(shopId);
				if (optShopAdmin.isPresent() && optShopAdmin.get().getShopTypeId() != null) {
					Long shopTypeId = optShopAdmin.get().getShopTypeId().longValue();
					Optional<MShopType> optShopType = new MShopTypeRepository().findById(shopTypeId);
					if(optShopType.isPresent() && !optShopType.get().getIsDeleted()) {
						operationForm = optShopType.get().getName();
					}
				}
			}

			listBrandIncome = new IBrandIncomeUnitRepository().findFromShopAdmin(iShopId);
			if (listBrandIncome == null || listBrandIncome.size() == 0) {
				listBrandIncome = new IBrandIncomeUnitRepository().findByShopId(iShopId);
			}

			Optional<ISalesAgency> optSalesAgency = new ISalesAgencyRepository().findByShopId(iShopId);
			if (optSalesAgency.isPresent()) {
				listSalesAgency = new NegotiationRelationDTO(optSalesAgency.get().getId(),
						optSalesAgency.get().getSalesAgencyName());
			}
		}
	}

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getShopCd() {
		return shopCd;
	}

	public void setShopCd(String shopCd) {
		this.shopCd = shopCd;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getFrontage() {
		return frontage;
	}

	public void setFrontage(String frontage) {
		this.frontage = frontage;
	}

	public String getFloorNum() {
		return floorNum;
	}

	public void setFloorNum(String floorNum) {
		this.floorNum = floorNum;
	}

	public BigDecimal getContractTsubo() {
		return contractTsubo;
	}

	public void setContractTsubo(BigDecimal contractTsubo) {
		this.contractTsubo = contractTsubo;
	}

	public String getBusinessHours() {
		return businessHours;
	}

	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}

	public Integer getBuildingExpectedValue() {
		return buildingExpectedValue;
	}

	public void setBuildingExpectedValue(Integer buildingExpectedValue) {
		this.buildingExpectedValue = buildingExpectedValue;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<NegotiationRelationDTO> getListBrandIncome() {
		return listBrandIncome;
	}

	public void setListBrandIncome(List<NegotiationRelationDTO> listBrandIncome) {
		this.listBrandIncome = listBrandIncome;
	}

	public NegotiationRelationDTO getListSalesAgency() {
		return listSalesAgency;
	}

	public void setListSalesAgency(NegotiationRelationDTO listSalesAgency) {
		this.listSalesAgency = listSalesAgency;
	}

	public IRentContractRelationShopDetailDTO getRentContract() {
		return rentContract;
	}

	public void setRentContract(IRentContractRelationShopDetailDTO rentContract) {
		this.rentContract = rentContract;
	}

	public Boolean getIsDisplayEconomy() {
		return isDisplayEconomy;
	}

	public void setIsDisplayEconomy(Boolean isDisplayEconomy) {
		this.isDisplayEconomy = isDisplayEconomy;
	}

	public NegotiationRelationDTO getSalesAgencyTarget() {
		return salesAgencyTarget;
	}

	public void setSalesAgencyTarget(NegotiationRelationDTO salesAgencyTarget) {
		this.salesAgencyTarget = salesAgencyTarget;
	}

	public ISalesAgencyContractRelationShopDTO getSalesAgencyContract() {
		return salesAgencyContract;
	}

	public void setSalesAgencyContract(ISalesAgencyContractRelationShopDTO salesAgencyContract) {
		this.salesAgencyContract = salesAgencyContract;
	}

	public ISalesAgencyConditionRelationShopDTO getSalesAgencyCondition() {
		return salesAgencyCondition;
	}

	public void setSalesAgencyCondition(ISalesAgencyConditionRelationShopDTO salesAgencyCondition) {
		this.salesAgencyCondition = salesAgencyCondition;
	}

	public NegotiationRelationDTO getParticipatingStoreCorporation() {
		return participatingStoreCorporation;
	}

	public void setParticipatingStoreCorporation(NegotiationRelationDTO participatingStoreCorporation) {
		this.participatingStoreCorporation = participatingStoreCorporation;
	}

	public LocalDate getRentStartDate() {
		return rentStartDate;
	}

	public void setRentStartDate(LocalDate rentStartDate) {
		this.rentStartDate = rentStartDate;
	}

	public LocalDate getRentEndDate() {
		return rentEndDate;
	}

	public void setRentEndDate(LocalDate rentEndDate) {
		this.rentEndDate = rentEndDate;
	}

	public Float getRentYear() {
		return rentYear;
	}

	public void setRentYear(Float rentYear) {
		this.rentYear = rentYear;
	}

	public String getOperationForm() {
		return operationForm;
	}

	public void setOperationForm(String operationForm) {
		this.operationForm = operationForm;
	}

}
