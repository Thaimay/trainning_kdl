package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.IFeeTypeDivisionBatch;
import jp.co.world.storedevelopment.model.mapper.repository.IFeeTypeDivisionRepository;

public class IFeeTypeDivisionTest extends BatchTest {
	@Override
	protected void deleteAll() {
		new IFeeTypeDivisionRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		IFeeTypeDivisionBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 4;
	}

	@Override
	protected int getExpected() {
		return new IFeeTypeDivisionRepository().countAll();
	}
}
