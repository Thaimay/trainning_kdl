package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.IRemodelingHistoryBatch;
import jp.co.world.storedevelopment.model.mapper.repository.IRemodelingHistoryRepository;

public class IRemodelingHistoryTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new IRemodelingHistoryRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		IRemodelingHistoryBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 31022;
	}

	@Override
	protected int getExpected() {
		return new IRemodelingHistoryRepository().countAll();
	}

}
