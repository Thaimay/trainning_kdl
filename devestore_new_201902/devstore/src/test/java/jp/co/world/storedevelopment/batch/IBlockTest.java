package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.IBlockBatch;
import jp.co.world.storedevelopment.model.mapper.repository.IBlockRepository;

public class IBlockTest extends BatchTest {
	@Override
	protected void deleteAll() {
		new IBlockRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		IBlockBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 125;
	}

	@Override
	protected int getExpected() {
		return new IBlockRepository().countAll();
	}
}
