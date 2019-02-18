package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.IShop;
import jp.co.world.storedevelopment.model.mapper.repository.IShopRepository;

public class IShopBatch extends ImportBatch<IShop> {

	private IShopRepository repository = new IShopRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"shopId",
					"shopCd",
					"shopNameZenkaku",
					"shopNameZenkakuHankaku",
					"shopNameKana",
					"shopAbbreviationName",
					"shopAbbreviationNameAccounting",
					"postCd",
					"prefecturesName",
					"cityName",
					"townNameAddress",
					"buildingName",
					"floorNum",
					"shopAddress",
					"shopAddressOld",
					"shopAddressKana1",
					"shopAddressKana2",
					"phoneNumber",
					"faxNumber",
					"iShopCompanyId",
					"jisPrefecturesCd",
					"jisDistrictCd",
					"marketId",
					"transactionGp",
					"shopTownParentId",
					"consolidationId",
					"compoundShopDivisionId",
					"iIncomeUnitId",
					"contractTsubo",
					"effectiveTsubo",
					"backyardTsubo",
					"contractArea",
					"effectiveArea",
					"backyardArea",
					"statusId",
					"openDate",
					"preOpenDate",
					"closeDate",
					"salesAgencyId",
					"salesChannelId",
					"isEnhancedShop",
					"segmentDivisionId",
					"accountingStartDate",
					"accountingEndDate",
					"departmentStoreUnificationCd",
					"placeId",
					"wholesaleAreaId",
					"htposDivisionId",
					"stepsRateCalculationDivisionId",
					"depreciationOfFixedAssetsDivisionId",
					"relationUpdateDatetime",
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action");

	//@formatter:on

	private IShopBatch() {
		super("I_SHOP.csv", fields);
	}

	public static void main(String[] args) {
		new IShopBatch().execute(args);
	}
	
	@Override
	protected Optional<IShop> findBy(IShop model) {
		return repository.findByImportId(model);
	}
	
	@Override
	protected IShop createModel() {
		return new IShop();
	}

}
