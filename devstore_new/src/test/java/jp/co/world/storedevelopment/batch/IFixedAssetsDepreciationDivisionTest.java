package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.IFixedAssetsDepreciationDivisionBatch;
import jp.co.world.storedevelopment.model.mapper.repository.IFixedAssetsDepreciationDivisionRepository;

public class IFixedAssetsDepreciationDivisionTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new IFixedAssetsDepreciationDivisionRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		IFixedAssetsDepreciationDivisionBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 2;
	}

	@Override
	protected int getExpected() {
		return new IFixedAssetsDepreciationDivisionRepository().countAll();
	}

}
