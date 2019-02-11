package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.IStepsRateCalculationDivisionBatch;
import jp.co.world.storedevelopment.model.mapper.repository.IStepsRateCalculationDivisionRepository;

public class IStepsRateCalculationDivisionTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new IStepsRateCalculationDivisionRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		IStepsRateCalculationDivisionBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 6;
	}

	@Override
	protected int getExpected() {
		return new IStepsRateCalculationDivisionRepository().countAll();
	}

}
