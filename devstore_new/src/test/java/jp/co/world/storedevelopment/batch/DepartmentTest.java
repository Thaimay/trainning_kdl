package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.DepartmentBatch;
import jp.co.world.storedevelopment.model.mapper.repository.DepartmentRepository;

public class DepartmentTest extends BatchTest {
	@Override
	protected void deleteAll() {
		new DepartmentRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		DepartmentBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 19519;
	}

	@Override
	protected int getExpected() {
		return new DepartmentRepository().countAll();
	}
}
