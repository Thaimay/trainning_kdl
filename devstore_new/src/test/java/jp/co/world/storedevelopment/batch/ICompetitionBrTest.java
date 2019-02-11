package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.ICompetitionBrBatch;
import jp.co.world.storedevelopment.model.mapper.repository.ICompetitionBrRepository;

public class ICompetitionBrTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new ICompetitionBrRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		ICompetitionBrBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 1542;
	}

	@Override
	protected int getExpected() {
		return new ICompetitionBrRepository().countAll();
	}

}
