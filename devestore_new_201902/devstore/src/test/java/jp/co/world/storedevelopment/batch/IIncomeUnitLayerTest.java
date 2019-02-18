package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.IIncomeUnitLayerBatch;
import jp.co.world.storedevelopment.model.mapper.repository.IIncomeUnitLayerRepository;

public class IIncomeUnitLayerTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new IIncomeUnitLayerRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		IIncomeUnitLayerBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 1581;
	}

	@Override
	protected int getExpected() {
		return new IIncomeUnitLayerRepository().countAll();
	}

}
