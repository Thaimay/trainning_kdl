package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.IShopSalesBr;
import jp.co.world.storedevelopment.model.mapper.repository.IShopSalesBrRepository;

public class IShopSalesBrBatch extends ImportBatch<IShopSalesBr> {

	private IShopSalesBrRepository repository = new IShopSalesBrRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"mainIncomeUnitCd",
					"mainIncomeUnitName",
					"shopId",
					"shopName",
					"openDate",
					"closeDate",
					"remodelingDate",
					"tsuboNum",
					"fllor",
					"salesAchievement",
					"salesAchievementComposition",
					"salesAchievementLastYearDifferenceComposition",
					"salesAchievementYearToYear",
					"salesAchievementLastYearDifference",
					"operatingProfitAchievement",
					"operatingProfitAchievementComposition",
					"operatingProfitLastYearDifferenceComposition",
					"operatingProfitYearToYear",
					"operatingProfitLastYearDifference",
					"monthAreaSalesAchievement",
					"monthAreaSalesYearToYear",
					"monthAreaSalesLastYearDifference",
					"dispOrder");

	//@formatter:on

	private IShopSalesBrBatch() {
		super("I_SHOP_SALES_BR.csv", fields);
	}

	public static void main(String[] args) {
		new IShopSalesBrBatch().execute(args);
	}

	@Override
	protected Optional<IShopSalesBr> findBy(IShopSalesBr model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected IShopSalesBr createModel() {
		return new IShopSalesBr();
	}
}
