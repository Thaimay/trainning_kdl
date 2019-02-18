package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.ISalesAgencyContractBatch;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesAgencyContractRepository;

public class ISalesAgencyContractTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new ISalesAgencyContractRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		ISalesAgencyContractBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 10226;
	}

	@Override
	protected int getExpected() {
		return new ISalesAgencyContractRepository().countAll();
	}
}
