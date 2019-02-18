package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.ISalesChannelBatch;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesChannelRepository;

public class ISalesChannelTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new ISalesChannelRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		ISalesChannelBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 8;
	}

	@Override
	protected int getExpected() {
		return new ISalesChannelRepository().countAll();
	}

}
