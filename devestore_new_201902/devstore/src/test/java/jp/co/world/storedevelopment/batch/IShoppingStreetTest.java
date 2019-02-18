package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.IShoppingStreetBatch;
import jp.co.world.storedevelopment.model.mapper.repository.IShoppingStreetRepository;

public class IShoppingStreetTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new IShoppingStreetRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		IShoppingStreetBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 609;
	}

	@Override
	protected int getExpected() {
		return new IShoppingStreetRepository().countAll();
	}

}
