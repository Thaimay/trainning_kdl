package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.IIncomeUnitBatch;
import jp.co.world.storedevelopment.model.mapper.repository.IIncomeUnitRepository;

public class IIncomeUnitTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new IIncomeUnitRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		IIncomeUnitBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 2984;
	}

	@Override
	protected int getExpected() {
		return new IIncomeUnitRepository().countAll();
	}

}
