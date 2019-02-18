package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.IContractTypeBatch;
import jp.co.world.storedevelopment.model.mapper.repository.IContractTypeRepository;

public class IContractTypeTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new IContractTypeRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		IContractTypeBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 9;
	}

	@Override
	protected int getExpected() {
		return new IContractTypeRepository().countAll();
	}
}
