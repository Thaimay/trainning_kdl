package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.ICorporationBatch;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IShopRepository;
import jp.co.world.storedevelopment.model.mapper.repository.PmCorporationRepository;

public class ICorporationTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new PmCorporationRepository().deleteAllWithResetSerial();
		new IShopRepository().deleteAllWithResetSerial();
		new ICorporationRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		ICorporationBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 1242;
	}

	@Override
	protected int getExpected() {
		return new ICorporationRepository().countAll();
	}

}
