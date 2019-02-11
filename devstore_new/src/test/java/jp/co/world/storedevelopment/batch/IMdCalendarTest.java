package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.IMdCalendarBatch;
import jp.co.world.storedevelopment.model.mapper.repository.IMdCalendarRepository;

public class IMdCalendarTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new IMdCalendarRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		IMdCalendarBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 11323;
	}

	@Override
	protected int getExpected() {
		return new IMdCalendarRepository().countAll();
	}

}
