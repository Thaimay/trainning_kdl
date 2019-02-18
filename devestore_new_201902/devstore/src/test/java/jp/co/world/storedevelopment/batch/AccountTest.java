package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.AccountBatch;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.TodoRepository;

public class AccountTest extends BatchTest {
	@Override
	protected void deleteAll() {
		new TodoRepository().deleteAllWithResetSerial();
		new ImportantInformationAccountRepository().deleteAllWithResetSerial();
		new AccountRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		AccountBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 15513;
	}

	@Override
	protected int getExpected() {
		return new AccountRepository().countAll();
	}
}
