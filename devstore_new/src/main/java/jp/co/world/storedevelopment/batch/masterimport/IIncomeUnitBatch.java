package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.IIncomeUnit;
import jp.co.world.storedevelopment.model.mapper.repository.IIncomeUnitRepository;

public class IIncomeUnitBatch extends ImportBatch<IIncomeUnit> {

	private IIncomeUnitRepository repository = new IIncomeUnitRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"incomeUnitId",
					"incomeUnitCd",
					"incomeUnitName",
					"companyId",
					"coordinationCreator",
					"coordinationCreationTime",
					"coordinationDeletingFlag",
					"coordinationApplyingDate",
					"coordinationUpdater",
					"coordinationUpdateTime");

	//@formatter:on

	private IIncomeUnitBatch() {
		super("RIEKITANIMST.csv", fields);
	}

	public static void main(String[] args) {
		new IIncomeUnitBatch().execute(args);
	}

	@Override
	protected Optional<IIncomeUnit> findBy(IIncomeUnit model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected IIncomeUnit createModel() {
		return new IIncomeUnit();
	}
}
