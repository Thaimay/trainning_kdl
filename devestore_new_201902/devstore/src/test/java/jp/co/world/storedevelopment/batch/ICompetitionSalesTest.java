package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.ICompetitionSalesBatch;
import jp.co.world.storedevelopment.model.mapper.repository.ICompetitionSalesRepository;

public class ICompetitionSalesTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new ICompetitionSalesRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		ICompetitionSalesBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 19617;
	}

	@Override
	protected int getExpected() {
		return new ICompetitionSalesRepository().countAll();
	}

}
