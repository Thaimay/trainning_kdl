package jp.co.world.storedevelopment.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationRepository;

public class ImportantInformationReadAccountTest extends ModelTest {
	@Test
	public void create() {
		createReadRecord();
	}

	@Test
	public void isRead() {
		Account account = new AccountRepository().getHead().get();
		ImportantInformation important = new ImportantInformationRepository().getHead().get();

		assertTrue(important.isRead(account) == false);

		important.read(account);

		assertTrue(important.isRead(account));

		important.read(account);

		assertTrue(important.isRead(account) == false);
	}

	private void createReadRecord() {
		ImportantInformationReadAccount important = new ImportantInformationReadAccount();
		important.setAccountId(new AccountRepository().getHead().get().getId());
		important.setImportantInformationId(new ImportantInformationRepository().getHead().get().getId());
		important.create();
	}
}
