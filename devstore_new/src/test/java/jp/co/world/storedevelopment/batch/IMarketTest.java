package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.IMarketBatch;
import jp.co.world.storedevelopment.model.mapper.repository.IMarketRepository;

public class IMarketTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new IMarketRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		IMarketBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 26;
	}

	@Override
	protected int getExpected() {
		return new IMarketRepository().countAll();
	}

}
