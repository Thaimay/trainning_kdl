package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.ISalesByTimeZoneBatch;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesByTimeZoneRepository;

public class ISalesByTimeZoneTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new ISalesByTimeZoneRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		ISalesByTimeZoneBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 2650;
	}

	@Override
	protected int getExpected() {
		return new ISalesByTimeZoneRepository().countAll();
	}

}
