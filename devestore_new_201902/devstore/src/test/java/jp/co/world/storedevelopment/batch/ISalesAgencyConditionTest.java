package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.ISalesAgencyConditionBatch;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesAgencyConditionRepository;

public class ISalesAgencyConditionTest extends BatchTest {
	@Override
	protected void deleteAll() {
		new ISalesAgencyConditionRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		ISalesAgencyConditionBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 10382;
	}

	@Override
	protected int getExpected() {
		return new ISalesAgencyConditionRepository().countAll();
	}
}
