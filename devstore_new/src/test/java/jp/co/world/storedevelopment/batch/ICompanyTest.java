package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.ICompanyBatch;
import jp.co.world.storedevelopment.model.mapper.repository.ICompanyRepository;

public class ICompanyTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new ICompanyRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		ICompanyBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 82;
	}

	@Override
	protected int getExpected() {
		return new ICompanyRepository().countAll();
	}

}
