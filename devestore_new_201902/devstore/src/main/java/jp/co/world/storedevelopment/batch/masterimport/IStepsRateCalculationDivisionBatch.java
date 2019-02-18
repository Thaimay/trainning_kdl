package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.IStepsRateCalculationDivision;
import jp.co.world.storedevelopment.model.mapper.repository.IStepsRateCalculationDivisionRepository;

public class IStepsRateCalculationDivisionBatch extends ImportBatch<IStepsRateCalculationDivision> {

	private IStepsRateCalculationDivisionRepository repository = new IStepsRateCalculationDivisionRepository();

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

	private IStepsRateCalculationDivisionBatch() {
		super("I_STEPS_RATE_CALCULATION_DIVISION.csv", fields);
	}

	public static void main(String[] args) {
		new IStepsRateCalculationDivisionBatch().execute(args);
	}

	@Override
	protected Optional<IStepsRateCalculationDivision> findBy(IStepsRateCalculationDivision model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected IStepsRateCalculationDivision createModel() {
		return new IStepsRateCalculationDivision();
	}
}
