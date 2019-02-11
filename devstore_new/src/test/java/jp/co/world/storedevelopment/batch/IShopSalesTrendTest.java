package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.IShopSalesTrendBatch;
import jp.co.world.storedevelopment.model.mapper.repository.IShopRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IShopSalesTrendRepository;
import jp.co.world.storedevelopment.model.mapper.repository.PmCorporationRepository;

public class IShopSalesTrendTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new PmCorporationRepository().deleteAllWithResetSerial();
		new IShopRepository().deleteAllWithResetSerial();
		new IShopSalesTrendRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		IShopSalesTrendBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 46792;
	}

	@Override
	protected int getExpected() {
		return new IShopSalesTrendRepository().countAll();
	}

}
