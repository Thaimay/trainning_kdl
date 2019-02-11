package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.IRentKindBatch;
import jp.co.world.storedevelopment.model.mapper.repository.IRentKindRepository;

public class IRentKindTest extends BatchTest {
	@Override
	protected void deleteAll() {
		new IRentKindRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		IRentKindBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 2;
	}

	@Override
	protected int getExpected() {
		return new IRentKindRepository().countAll();
	}
}
