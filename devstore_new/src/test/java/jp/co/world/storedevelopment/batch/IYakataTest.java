package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.IYakataBatch;
import jp.co.world.storedevelopment.model.mapper.repository.IYakataRepository;

public class IYakataTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new IYakataRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		IYakataBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 4189;
	}

	@Override
	protected int getExpected() {
		return new IYakataRepository().countAll();
	}

}
