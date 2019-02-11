package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.ICompositeShopDivisionBatch;
import jp.co.world.storedevelopment.model.mapper.repository.ICompositeShopDivisionRepository;

public class ICompositeShopDivisionTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new ICompositeShopDivisionRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		ICompositeShopDivisionBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 6;
	}

	@Override
	protected int getExpected() {
		return new ICompositeShopDivisionRepository().countAll();
	}

}
