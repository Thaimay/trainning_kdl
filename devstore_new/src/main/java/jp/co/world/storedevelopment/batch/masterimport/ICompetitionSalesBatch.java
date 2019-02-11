package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.ICompetitionSales;
import jp.co.world.storedevelopment.model.mapper.repository.ICompetitionSalesRepository;

public class ICompetitionSalesBatch extends ImportBatch<ICompetitionSales> {

	private ICompetitionSalesRepository repository = new ICompetitionSalesRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"buildingCd",
					"buildingName",
					"competitionShopId",
					"competitionShopName",
					"tsuboNum",
					"yearMonth",
					"salesRatio",
					"inputActiveDays",
					"dispOrder"
					);

	//@formatter:on

	private ICompetitionSalesBatch() {
		super("I_COMPETITION_SALES.csv", fields);
	}

	public static void main(String[] args) {
		new ICompetitionSalesBatch().execute(args);
	}

	@Override
	protected Optional<ICompetitionSales> findBy(ICompetitionSales model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected ICompetitionSales createModel() {
		return new ICompetitionSales();
	}
}
