package jp.co.world.storedevelopment.sp.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.Department;
import jp.co.world.storedevelopment.model.IArea;
import jp.co.world.storedevelopment.model.IIncomeUnit;
import jp.co.world.storedevelopment.model.IPlace;
import jp.co.world.storedevelopment.model.IRentContract;
import jp.co.world.storedevelopment.model.IShop;
import jp.co.world.storedevelopment.model.IShopCompany;
import jp.co.world.storedevelopment.model.Role;
import jp.co.world.storedevelopment.model.Shop;
import jp.co.world.storedevelopment.model.SubAccount;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IAreaRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IIncomeUnitRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IPlaceRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IRentContractRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IShopCompanyRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ShopRepository;

public class IShopRelationShopDetailDTO implements DTO<IShop> {

	private Long id;
	private Long shopId;
	private String shopCd;
	private String shopNameZenkaku;
	private Long iShopCompanyId;
	private BigDecimal contractTsubo;
	private Long wholesaleAreaId;
	private Long iIncomeUnitId;
	private Long placeId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate openDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate closeDate;
	private String shopTownParentId;

	private Account account;

	public IShopRelationShopDetailDTO() {

	}

	public IShopRelationShopDetailDTO(IShop iShop) {
		copyProperties(this, iShop);
	}

	public IShopRelationShopDetailDTO(IShop iShop, Account account) {
		this(iShop);
		setAccount(account);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getShopCd() {
		return shopCd;
	}

	public void setShopCd(String shopCd) {
		this.shopCd = shopCd;
	}

	public String getShopNameZenkaku() {
		return shopNameZenkaku;
	}

	public void setShopNameZenkaku(String shopNameZenkaku) {
		this.shopNameZenkaku = shopNameZenkaku;
	}

	public Long getiShopCompanyId() {
		return iShopCompanyId;
	}

	public void setiShopCompanyId(Long iShopCompanyId) {
		this.iShopCompanyId = iShopCompanyId;
	}

	public BigDecimal getContractTsubo() {
		return contractTsubo;
	}

	public void setContractTsubo(BigDecimal contractTsubo) {
		this.contractTsubo = contractTsubo;
	}

	public Long getWholesaleAreaId() {
		return wholesaleAreaId;
	}

	public void setWholesaleAreaId(Long wholesaleAreaId) {
		this.wholesaleAreaId = wholesaleAreaId;
	}

	public Long getiIncomeUnitId() {
		return iIncomeUnitId;
	}

	public void setiIncomeUnitId(Long iIncomeUnitId) {
		this.iIncomeUnitId = iIncomeUnitId;
	}

	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}

	public LocalDate getOpenDate() {
		return openDate;
	}

	public void setOpenDate(LocalDate openDate) {
		this.openDate = openDate;
	}

	public LocalDate getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(LocalDate closeDate) {
		this.closeDate = closeDate;
	}

	public String getShopTownParentId() {
		return shopTownParentId;
	}

	public void setShopTownParentId(String shopTownParentId) {
		this.shopTownParentId = shopTownParentId;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public IShopCompanyRelationShopDetailDTO getiShopCompany() {
		if (getiShopCompanyId() == null) {
			return null;
		}

		Optional<IShopCompany> iShopCompany = new IShopCompanyRepository().findById(getiShopCompanyId());
		return iShopCompany.isPresent() ? new IShopCompanyRelationShopDetailDTO(iShopCompany.get()) : null;
	}

	public IAreaRelationShopDetailDTO getiArea() {
		if (getWholesaleAreaId() == null) {
			return null;
		}

		Optional<IArea> iArea = new IAreaRepository().findById(getWholesaleAreaId());
		return iArea.isPresent() ? new IAreaRelationShopDetailDTO(iArea.get()) : null;
	}

	public IIncomeUnitRelationShopDetailDTO getiIncomeUnit() {
		if (getiIncomeUnitId() == null) {
			return null;
		}

		IIncomeUnit iIncomeUnit = new IIncomeUnitRepository().findByIncomeUnitId(getiIncomeUnitId());
		return iIncomeUnit != null ? new IIncomeUnitRelationShopDetailDTO(iIncomeUnit) : null;
	}

	public BuildingRelationShopDetailDTO getBuilding() {
		if (getPlaceId() == null) {
			return null;
		}

		Building building = null;
		IPlace optIPlace = new IPlaceRepository().findByPlaceId(getPlaceId());
		if (optIPlace != null && optIPlace.getOriginBuildingId() != null) {
			building = new BuildingRepository().getBuildingByOriginBuildingId(optIPlace.getOriginBuildingId());
		}

		// // TODO: 2018/03/23 暫定対応
		// if (building == null) {
		// building = new BuildingRepository().getHead().orElseGet(() -> {
		// throw new IllegalStateException("館が存在しません");
		// });
		//
		// return new BuildingRelationShopListDTO(building);
		// } else {
		// return new BuildingRelationShopListDTO(building);
		// }
		return building != null ? new BuildingRelationShopDetailDTO(building) : null;
	}

	public IRentContractRelationShopDetailDTO getiRentContract() {
		if (getShopId() == null) {
			return null;
		}

		List<IRentContract> iRentContracts = new IRentContractRepository().findByShopId(getShopId());
		return iRentContracts != null && iRentContracts.size() > 0
				? new IRentContractRelationShopDetailDTO(iRentContracts.get(iRentContracts.size() - 1))
				: null;
	}

	public ShopDetailDTO getShop() {
		if (getId() == null) {
			return null;
		}

		Shop shop = new ShopRepository().findByIShopId(getId());
		return shop != null ? new ShopDetailDTO(shop) : null;
	}

	public Boolean isDisplayEconomy() {
		if (account == null || account.getRole() == null) {
			return false;
		}

		Role role = account.getRole();

		if (role.hasPermission("ECONOMIC_CONDITIONS")) {
			return true;
		} else if (role.hasPermission("ECONOMIC_CONDITIONS_LIMITED") && account.getSubAccount().isPresent()) {
			SubAccount subAccount = account.getSubAccount().get();

			// roleテーブルのonly_target_areaがtrueである。
			if (role.isOnlyTargetArea() && getBuilding() != null && getBuilding().getiArea() != null) {
				return subAccount.hasAreaCode(getBuilding().getiArea().getAreaCd());
			}

			if (account.department().isPresent()) {
				Department dept = account.department().get();

				// roleテーブルのonly_my_companyがtrueである。
				if (role.isOnlyMyCompany()) {
					return subAccount.getCompanyCode().equals(dept.getCompanyCd());
				}

				// roleテーブルのonly_income_unitがtrueである。
				if (role.isOnlyIncomeUnit()) {
					return subAccount.getIncomeUnitCode().equals(dept.getIncomeUnitCd1());
				}
			}
		}
		return false;
	}

	public Boolean inShopTownGroup() {
		return shopTownParentId != null && !shopTownParentId.isEmpty();
	}

	@Override
	public IShop createModel() {
		return new IShop();
	}

}
