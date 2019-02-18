package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.ICompetitionBatch;
import jp.co.world.storedevelopment.model.mapper.repository.ICompetitionRepository;

public class ICompetitionTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new ICompetitionRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		ICompetitionBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 9552;
	}

	@Override
	protected int getExpected() {
		return new ICompetitionRepository().countAll();
	}

}
