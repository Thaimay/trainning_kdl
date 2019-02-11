package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.IRentPaymentBatch;
import jp.co.world.storedevelopment.model.mapper.repository.IRentPaymentRepository;

public class IRentPaymentTest extends BatchTest {
	@Override
	protected void deleteAll() {
		new IRentPaymentRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		IRentPaymentBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 3;
	}

	@Override
	protected int getExpected() {
		return new IRentPaymentRepository().countAll();
	}
}
