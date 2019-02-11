package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.ICompetitionShopCorporationBatch;
import jp.co.world.storedevelopment.model.mapper.repository.ICompetitionShopCorporationRepository;

public class ICompetitionShopCorporationTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new ICompetitionShopCorporationRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		ICompetitionShopCorporationBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 1300;
	}

	@Override
	protected int getExpected() {
		return new ICompetitionShopCorporationRepository().countAll();
	}

}
