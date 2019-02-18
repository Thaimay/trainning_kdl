package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.IAreaBlockBatch;
import jp.co.world.storedevelopment.model.mapper.repository.IAreaBlockRepository;

public class IAreaBlockTest extends BatchTest {
	@Override
	protected void deleteAll() {
		new IAreaBlockRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		IAreaBlockBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 125;
	}

	@Override
	protected int getExpected() {
		return new IAreaBlockRepository().countAll();
	}
}
