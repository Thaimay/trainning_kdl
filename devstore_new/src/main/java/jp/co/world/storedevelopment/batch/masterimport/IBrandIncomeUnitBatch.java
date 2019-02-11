package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.IBrandIncomeUnit;
import jp.co.world.storedevelopment.model.mapper.repository.IBrandIncomeUnitRepository;

public class IBrandIncomeUnitBatch extends ImportBatch<IBrandIncomeUnit> {

	private IBrandIncomeUnitRepository repository = new IBrandIncomeUnitRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"brandIncomeUnitId",
					"brandIncomeUnitCd",
					"brandIncomeUnitName",
					"incomeUnitId",
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action"
					);

	//@formatter:on

	private IBrandIncomeUnitBatch() {
		super("I_YAGOURIEKITANIMST.csv", fields);
	}

	public static void main(String[] args) {
		new IBrandIncomeUnitBatch().execute(args);
	}

	@Override
	protected Optional<IBrandIncomeUnit> findBy(IBrandIncomeUnit model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected IBrandIncomeUnit createModel() {
		return new IBrandIncomeUnit();
	}
}
