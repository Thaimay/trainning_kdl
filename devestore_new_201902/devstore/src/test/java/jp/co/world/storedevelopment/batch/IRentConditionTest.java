package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.IRentConditionBatch;
import jp.co.world.storedevelopment.model.mapper.repository.IRentConditionRepository;

public class IRentConditionTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new IRentConditionRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		IRentConditionBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 17481;
	}

	@Override
	protected int getExpected() {
		return new IRentConditionRepository().countAll();
	}

}
