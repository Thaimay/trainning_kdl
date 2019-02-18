package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.IShopSalesLatestOneYearBatch;
import jp.co.world.storedevelopment.model.mapper.repository.IShopRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IShopSalesLatestOneYearRepository;
import jp.co.world.storedevelopment.model.mapper.repository.PmCorporationRepository;

public class IShopSalesLatestOneYearTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new PmCorporationRepository().deleteAllWithResetSerial();
		new IShopRepository().deleteAllWithResetSerial();
		new IShopSalesLatestOneYearRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		IShopSalesLatestOneYearBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 4892;
	}

	@Override
	protected int getExpected() {
		return new IShopSalesLatestOneYearRepository().countAll();
	}

}
