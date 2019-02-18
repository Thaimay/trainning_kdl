package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.IShoppingStreet;
import jp.co.world.storedevelopment.model.mapper.repository.IShoppingStreetRepository;

public class IShoppingStreetBatch extends ImportBatch<IShoppingStreet> {

	private IShoppingStreetRepository repository = new IShoppingStreetRepository();
	
	//@formatter:off

	private static List<String> fields =
			asList("shoppingStreetId",
					"clientCode",
					"validDivision",
					"tradingStartDateOpenDate",
					"tradingEndDateCloseDate",
					"parentAccountCode",
					"parentAccountDivision",
					"approvalFlag",
					"approverCode",
					"approvalTime",
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action");

	//@formatter:on

	private IShoppingStreetBatch() {
		super("I_SHOTENGAI.csv", fields);
	}
	
	public static void main(String[] args) {
		new IShoppingStreetBatch().execute(args);
	}

	@Override
	protected Optional<IShoppingStreet> findBy(IShoppingStreet model) {
		return repository.findByImportCode(model);
	}
	
	@Override
	protected IShoppingStreet createModel() {
		return new IShoppingStreet();
	}
	
}
