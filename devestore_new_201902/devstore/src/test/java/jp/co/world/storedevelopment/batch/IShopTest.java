package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.IShopBatch;
import jp.co.world.storedevelopment.model.mapper.repository.IShopRepository;

public class IShopTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new IShopRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		IShopBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 31296;
	}

	@Override
	protected int getExpected() {
		return new IShopRepository().countAll();
	}

}
