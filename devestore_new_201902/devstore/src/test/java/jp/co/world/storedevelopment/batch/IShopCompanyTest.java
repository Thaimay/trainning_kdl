package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.IShopCompanyBatch;
import jp.co.world.storedevelopment.model.mapper.repository.IShopCompanyRepository;

public class IShopCompanyTest extends BatchTest {
	@Override
	protected void deleteAll() {
		new IShopCompanyRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		IShopCompanyBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 3049;
	}

	@Override
	protected int getExpected() {
		return new IShopCompanyRepository().countAll();
	}
}
