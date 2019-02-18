package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.ISalesAgencyTargetBatch;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesAgencyTargetRepository;

public class ISalesAgencyTargetTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new ISalesAgencyTargetRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		ISalesAgencyTargetBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 211;
	}

	@Override
	protected int getExpected() {
		return new ISalesAgencyTargetRepository().countAll();
	}

}
