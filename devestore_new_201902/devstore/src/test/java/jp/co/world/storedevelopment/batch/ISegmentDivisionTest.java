package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.ISegmentDivisionBatch;
import jp.co.world.storedevelopment.model.mapper.repository.ISegmentDivisionRepository;

public class ISegmentDivisionTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new ISegmentDivisionRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		ISegmentDivisionBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 12;
	}

	@Override
	protected int getExpected() {
		return new ISegmentDivisionRepository().countAll();
	}

}
