package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.IStatusBatch;
import jp.co.world.storedevelopment.model.mapper.repository.IStatusRepository;

public class IStatusTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new IStatusRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		IStatusBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 5;
	}

	@Override
	protected int getExpected() {
		return new IStatusRepository().countAll();
	}

}
