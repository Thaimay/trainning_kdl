package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.ICorporationGroupBatch;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationGroupRepository;

public class ICorporationGroupTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new ICorporationGroupRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		ICorporationGroupBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 123;
	}

	@Override
	protected int getExpected() {
		return new ICorporationGroupRepository().countAll();
	}

}
