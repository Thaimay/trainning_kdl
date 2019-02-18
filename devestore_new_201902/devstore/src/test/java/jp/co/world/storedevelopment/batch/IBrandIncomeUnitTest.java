package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.IBrandIncomeUnitBatch;
import jp.co.world.storedevelopment.model.mapper.repository.IBrandIncomeUnitRepository;

public class IBrandIncomeUnitTest extends BatchTest {
	@Override
	protected void deleteAll() {
		new IBrandIncomeUnitRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		IBrandIncomeUnitBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 278;
	}

	@Override
	protected int getExpected() {
		return new IBrandIncomeUnitRepository().countAll();
	}
}
