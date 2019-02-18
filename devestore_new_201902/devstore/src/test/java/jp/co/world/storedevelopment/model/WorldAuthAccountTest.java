package jp.co.world.storedevelopment.model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.mapper.repository.WorldAuthAccountRepository;

public class WorldAuthAccountTest extends ModelTest {

	@Test
	public void create() {
		WorldAuthAccount account = new WorldAuthAccount("new@test.com", "pass", "nametaro");
		account.create();

		assertNotEquals(Long.valueOf(0), account.getUid());

		Optional<WorldAuthAccount> accountOption = new WorldAuthAccountRepository().findById(account.getUid());

		if (accountOption.isPresent()) {
			WorldAuthAccount created = accountOption.get();
			assertNotEquals(Long.valueOf(0), account.getUid());
			assertEquals(account.getUid(), created.getUid());
		} else {
			fail();
		}
	}

	@Test
	public void findByEmail() {
		List<String> emails = Arrays.asList("test@test.com", "atest-2@test.com", "atest-3@test.com");
		emails.forEach(e -> {
			WorldAuthAccount a = new WorldAuthAccount();
			a.setEmail(e);
			a.setShimei(e);
			a.create();
		});

		WorldAuthAccountRepository repository = new WorldAuthAccountRepository();
		Optional<WorldAuthAccount> accountOpt = repository.findByMail("test@test.com");

		assertTrue(accountOpt.isPresent());

	}

	@Test
	public void permissionList() {

		WorldAuthAccountRepository repository = new WorldAuthAccountRepository();
		Optional<WorldAuthAccount> accountOpt = repository.getHead();
		assertTrue(accountOpt.isPresent());

		WorldAuthAccount account = accountOpt.get();

		List<String> roles = account.permissionList();
		assertEquals(1, roles.size());

	}

}
