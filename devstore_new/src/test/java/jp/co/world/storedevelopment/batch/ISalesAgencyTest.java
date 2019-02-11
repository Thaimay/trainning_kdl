package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.ISalesAgencyBatch;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesAgencyRepository;

public class ISalesAgencyTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new ISalesAgencyRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		ISalesAgencyBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 11;
	}

	@Override
	protected int getExpected() {
		return new ISalesAgencyRepository().countAll();
	}

}
