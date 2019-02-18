package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.IContractKindBatch;
import jp.co.world.storedevelopment.model.mapper.repository.IContractKindRepository;

public class IContractKindTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new IContractKindRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		IContractKindBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 24;
	}

	@Override
	protected int getExpected() {
		return new IContractKindRepository().countAll();
	}
}
