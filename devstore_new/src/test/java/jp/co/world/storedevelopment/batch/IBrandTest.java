package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.IBrandBatch;
import jp.co.world.storedevelopment.model.mapper.repository.IBrandRepository;

public class IBrandTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new IBrandRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		IBrandBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 1365;
	}

	@Override
	protected int getExpected() {
		return new IBrandRepository().countAll();
	}

}
