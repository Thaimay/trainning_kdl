package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.IMarket;
import jp.co.world.storedevelopment.model.mapper.repository.IMarketRepository;

public class IMarketBatch extends ImportBatch<IMarket> {

	private IMarketRepository repository = new IMarketRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"marketId",
					"marketCd",
					"marketName",
					"managementDividionCd",
					"managementDividionName",
					"deptTypeDividionCd",
					"deptTypeName",
					"shopType",
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action");

	//@formatter:on

	private IMarketBatch() {
		super("I_MARKET.csv", fields);
	}

	public static void main(String[] args) {
		new IMarketBatch().execute(args);
	}

	@Override
	protected Optional<IMarket> findBy(IMarket model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected IMarket createModel() {
		return new IMarket();
	}
}
