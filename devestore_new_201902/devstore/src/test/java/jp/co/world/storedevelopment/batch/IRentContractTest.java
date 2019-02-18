package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.IRentContractBatch;
import jp.co.world.storedevelopment.model.mapper.repository.IRentContractRepository;

public class IRentContractTest extends BatchTest {
	@Override
	protected void deleteAll() {
		new IRentContractRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		IRentContractBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 12774;
	}

	@Override
	protected int getExpected() {
		return new IRentContractRepository().countAll();
	}
}
