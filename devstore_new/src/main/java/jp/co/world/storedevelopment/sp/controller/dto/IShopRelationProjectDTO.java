package jp.co.world.storedevelopment.sp.controller.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.Department;
import jp.co.world.storedevelopment.model.IIncomeUnit;
import jp.co.world.storedevelopment.model.IPlace;
import jp.co.world.storedevelopment.model.IRentContract;
import jp.co.world.storedevelopment.model.IShop;
import jp.co.world.storedevelopment.model.Role;
import jp.co.world.storedevelopment.model.Shop;
import jp.co.world.storedevelopment.model.SubAccount;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IIncomeUnitRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IPlaceRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IRentContractRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ShopRepository;

public class IShopRelationProjectDTO implements DTO<IShop> {

	private Long id;
	private Long shopId;
	private String shopCd;
	private String shopNameZenkaku;
	private Long iIncomeUnitId;
	private BigDecimal contractTsubo;
	private Long placeId;

	private Account account;

	public IShopRelationProjectDTO() {

	}

	public IShopRelationProjectDTO(IShop iShop) {
		copyProperties(this, iShop);
	}

	public IShopRelationProjectDTO(IShop iShop, Account account) {
		this(iShop);
		setAccount(account);
	}

	@Override
	public IShop createModel() {
		return new IShop();
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

	public Long getiIncomeUnitId() {
		return iIncomeUnitId;
	}

	public void setiIncomeUnitId(Long iIncomeUnitId) {
		this.iIncomeUnitId = iIncomeUnitId;
	}

	public BigDecimal getContractTsubo() {
		return contractTsubo;
	}

	public void setContractTsubo(BigDecimal contractTsubo) {
		this.contractTsubo = contractTsubo;
	}

	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public ShopRelationIShopDTO getShop() {
		if (id != null) {
			Shop shop = new ShopRepository().findByIShopId(id);
			if (shop != null) {
				return new ShopRelationIShopDTO(shop);
			}
		}
		return null;
	}

	public IRentContract getRentContract() {
		if (shopId != null) {
			List<IRentContract> iRentContracts = new IRentContractRepository().findByShopId(shopId);
			if (iRentContracts != null && iRentContracts.size() > 0) {
				return iRentContracts.get(iRentContracts.size() - 1);
			}
		}
		return null;
	}

	public IIncomeUnit getIncomeUnit() {
		if (iIncomeUnitId != null) {
			Optional<IIncomeUnit> incomeUnit = new IIncomeUnitRepository().findById(iIncomeUnitId);
			if (incomeUnit.isPresent()) {
				return incomeUnit.get();
			}
		}
		return null;
	}

	public BuildingRelationShopDetailDTO getBuilding() {
		if (placeId == null) {
			return null;
		}

		Building building = null;
		IPlace optIPlace = new IPlaceRepository().findByPlaceId(placeId);
		if (optIPlace != null && optIPlace.getOriginBuildingId() != null) {
			building = new BuildingRepository().getBuildingByOriginBuildingId(optIPlace.getOriginBuildingId());
		}
		return building != null ? new BuildingRelationShopDetailDTO(building) : null;
	}

	public String getName() {
		return shopNameZenkaku;
	}

	public Boolean getIsDisplayEconomy() {
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
}
