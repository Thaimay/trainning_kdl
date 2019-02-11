package jp.co.world.storedevelopment.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationRepository;

public class ImportantInformationNiceAccountTest extends ModelTest {
	@Test
	public void create() {
		createNice();
	}

	@Test
	public void isNice() {
		Account account = new AccountRepository().getHead().get();
		ImportantInformation important = new ImportantInformationRepository().getHead().get();

		assertTrue(important.isNice(account) == false);

		important.switchNice(account);

		assertTrue(important.isNice(account));

		important.switchNice(account);

		assertTrue(important.isNice(account) == false);
	}

	private void createNice() {
		ImportantInformationNiceAccount i = new ImportantInformationNiceAccount();
		i.setAccountId(new AccountRepository().getHead().get().getId());
		i.setImportantInformationId(new ImportantInformationRepository().getHead().get().getId());
		i.create();
	}

}
