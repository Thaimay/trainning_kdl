package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.IPrefecturesBatch;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IPrefecturesRepository;

public class IPrefecturesTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new BuildingRepository().deleteAllWithResetSerial();
		new IPrefecturesRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		IPrefecturesBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 49;
	}

	@Override
	protected int getExpected() {
		return new IPrefecturesRepository().countAll();
	}

}
