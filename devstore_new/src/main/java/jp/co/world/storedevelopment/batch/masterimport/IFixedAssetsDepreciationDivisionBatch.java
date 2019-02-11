package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.IFixedAssetsDepreciationDivision;
import jp.co.world.storedevelopment.model.mapper.repository.IFixedAssetsDepreciationDivisionRepository;

public class IFixedAssetsDepreciationDivisionBatch extends ImportBatch<IFixedAssetsDepreciationDivision> {

	private IFixedAssetsDepreciationDivisionRepository repository = new IFixedAssetsDepreciationDivisionRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"stepsRateCalculationDivisionId",
					"stepsRateCalculationDivisionCd",
					"stepsRateCalculationDivisionName",
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action");

	//@formatter:on

	private IFixedAssetsDepreciationDivisionBatch() {
		super("I_FIXED_ASSETS_DEPRECIATION_DIVISION.csv", fields);
	}

	public static void main(String[] args) {
		new IFixedAssetsDepreciationDivisionBatch().execute(args);
	}

	@Override
	protected Optional<IFixedAssetsDepreciationDivision> findBy(IFixedAssetsDepreciationDivision model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected IFixedAssetsDepreciationDivision createModel() {
		return new IFixedAssetsDepreciationDivision();
	}
}
