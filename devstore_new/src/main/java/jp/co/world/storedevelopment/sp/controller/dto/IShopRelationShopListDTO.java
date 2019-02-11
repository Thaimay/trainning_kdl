package jp.co.world.storedevelopment.sp.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;

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

public class IShopRelationShopListDTO implements DTO<IShop> {

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

	public IShopRelationShopListDTO() {

	}

	public IShopRelationShopListDTO(IShop iShop) {
		copyProperties(this, iShop);
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

	@Override
	public IShop createModel() {
		return new IShop();
	}

}
