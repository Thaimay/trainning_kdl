package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.ISettlementStatusBatch;
import jp.co.world.storedevelopment.model.mapper.repository.ISettlementStatusRepository;

public class ISettlementStatusTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new ISettlementStatusRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		ISettlementStatusBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 5;
	}

	@Override
	protected int getExpected() {
		return new ISettlementStatusRepository().countAll();
	}
}
