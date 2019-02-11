package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.IPlaceBatch;
import jp.co.world.storedevelopment.model.mapper.repository.IPlaceRepository;

public class IPlaceTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new IPlaceRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		IPlaceBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 38326;
	}

	@Override
	protected int getExpected() {
		return new IPlaceRepository().countAll();
	}
}
