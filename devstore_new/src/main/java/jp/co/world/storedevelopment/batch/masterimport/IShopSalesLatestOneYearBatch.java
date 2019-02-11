package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.IShopSalesLatestOneYear;
import jp.co.world.storedevelopment.model.mapper.repository.IShopSalesLatestOneYearRepository;

public class IShopSalesLatestOneYearBatch extends ImportBatch<IShopSalesLatestOneYear> {

	private IShopSalesLatestOneYearRepository repository = new IShopSalesLatestOneYearRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"blockCd",
					"blockName",
					"buildingCd",
					"buildingName",
					"shopCd",
					"shopName",
					"openDate",
					"closeDate",
					"remodelingDate",
					"tsuboNum",
					"floor",
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
					"dispOrder"
					);

	//@formatter:on

	private IShopSalesLatestOneYearBatch() {
		super("I_SHOP_SALES.csv", fields);
	}

	public static void main(String[] args) {
		new IShopSalesLatestOneYearBatch().execute(args);
	}

	@Override
	protected Optional<IShopSalesLatestOneYear> findBy(IShopSalesLatestOneYear model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected IShopSalesLatestOneYear createModel() {
		return new IShopSalesLatestOneYear();
	}
}
