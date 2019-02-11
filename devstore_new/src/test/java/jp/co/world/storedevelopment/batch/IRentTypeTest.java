package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.IRentTypeBatch;
import jp.co.world.storedevelopment.model.mapper.repository.IRentTypeRepository;

public class IRentTypeTest extends BatchTest {
	@Override
	protected void deleteAll() {
		new IRentTypeRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		IRentTypeBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 5;
	}

	@Override
	protected int getExpected() {
		return new IRentTypeRepository().countAll();
	}
}
