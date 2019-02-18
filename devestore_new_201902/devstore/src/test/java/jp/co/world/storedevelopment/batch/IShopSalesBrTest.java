package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.IShopSalesBrBatch;
import jp.co.world.storedevelopment.model.mapper.repository.IShopSalesBrRepository;

public class IShopSalesBrTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new IShopSalesBrRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		IShopSalesBrBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 125;
	}

	@Override
	protected int getExpected() {
		return new IShopSalesBrRepository().countAll();
	}

}
