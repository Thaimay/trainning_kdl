package jp.co.world.storedevelopment.pc.controller.view.model;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.PropertyUtils;

import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.IArea;
import jp.co.world.storedevelopment.model.IIncomeUnit;
import jp.co.world.storedevelopment.model.IPlace;
import jp.co.world.storedevelopment.model.IShop;
import jp.co.world.storedevelopment.model.IShopCompany;
import jp.co.world.storedevelopment.model.Shop;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IAreaRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IIncomeUnitRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IPlaceRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IShopCompanyRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ShopRepository;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationShopListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.IAreaRelationShopListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.IIncomeUnitRelationShopListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.IShopCompanyRelationShopListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ShopListDTO;

public class ShopViewModel extends IShop {

	public static List<ShopViewModel> toList(List<IShop> list) {
		return list.stream().map(b -> {
			ShopViewModel model = new ShopViewModel();
			copyProperties(model, b);
			return model;
		}).collect(Collectors.toList());
	}

	private static ShopViewModel copyProperties(ShopViewModel model, IShop iShop) {
		try {
			PropertyUtils.copyProperties(model, iShop);
		} catch (Exception e) {
			throw new IllegalStateException("オブジェクトのコピーに失敗しました");
		}
		return model;
	}

	public IShopCompanyRelationShopListDTO getiShopCompany() {
		if (getiShopCompanyId() == null) {
			return null;
		}

		Optional<IShopCompany> iShopCompany = new IShopCompanyRepository().findById(getiShopCompanyId());
		return iShopCompany.isPresent() ? new IShopCompanyRelationShopListDTO(iShopCompany.get()) : null;
	}

	public IAreaRelationShopListDTO getiArea() {
		if (getWholesaleAreaId() == null) {
			return null;
		}

		Optional<IArea> iArea = new IAreaRepository().findById(getWholesaleAreaId());
		return iArea.isPresent() ? new IAreaRelationShopListDTO(iArea.get()) : null;
	}

	public IIncomeUnitRelationShopListDTO getiIncomeUnit() {
		if (getiIncomeUnitId() == null) {
			return null;
		}

		IIncomeUnit iIncomeUnit = new IIncomeUnitRepository().findByIncomeUnitId(getiIncomeUnitId());
		return iIncomeUnit != null ? new IIncomeUnitRelationShopListDTO(iIncomeUnit) : null;
	}

	public BuildingRelationShopListDTO getBuilding() {
		if (getPlaceId() == null) {
			return null;
		}

		Building building = null;
		IPlace optIPlace = new IPlaceRepository().findByPlaceId(getPlaceId());
		if (optIPlace!= null && optIPlace.getOriginBuildingId() != null) {
			building = new BuildingRepository().getBuildingByOriginBuildingId(optIPlace.getOriginBuildingId());
		}
		return building != null ? new BuildingRelationShopListDTO(building) : null;
	}

	public ShopListDTO getShop() {
		if (getId() == null) {
			return null;
		}

		Shop shop = new ShopRepository().findByIShopId(getId());
		return shop != null ? new ShopListDTO(shop) : null;
	}

	public String getStringShopName() {
		return this.getShopCd() + " " + this.getShopNameZenkaku();
	}

}
