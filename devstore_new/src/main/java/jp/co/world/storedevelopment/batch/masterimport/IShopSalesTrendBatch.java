package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.IShopSalesTrend;
import jp.co.world.storedevelopment.model.mapper.repository.IShopSalesTrendRepository;

public class IShopSalesTrendBatch extends ImportBatch<IShopSalesTrend> {

	private IShopSalesTrendRepository repository = new IShopSalesTrendRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"buildingCd",
					"buildingName",
					"shopId",
					"shopName",
					"tsuboNum",
					"sales",
					"salesPlanningRatio",
					"salesYearToYear",
					"operatingIncome",
					"operatingProfitPlanningRatio",
					"operatingProfitYearToYear",
					"monthTsuboSales",
					"monthTsuboSalesPlanningRatio",
					"monthTsuboSalesYearToYear",
					"appropriatingYearMonth",
					"dispOrder" 
					);

	//@formatter:on

	private IShopSalesTrendBatch() {
		super("I_SHOP_SALES_TREND.csv", fields);
	}

	public static void main(String[] args) {
		new IShopSalesTrendBatch().execute(args);
	}

	@Override
	protected Optional<IShopSalesTrend> findBy(IShopSalesTrend model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected IShopSalesTrend createModel() {
		return new IShopSalesTrend();
	}
}
