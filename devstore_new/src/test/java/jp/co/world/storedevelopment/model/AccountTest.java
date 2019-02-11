
package jp.co.world.storedevelopment.model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationFindByTextFormDTO;

public class AccountTest extends ModelTest {

	@Test
	public void create() {
		Account account = new Account("123456", "create_t@test.com", "test1234");
		account.create();

		assertNotEquals(Long.valueOf(0), account.getId());

		Optional<Account> accountOption = new AccountRepository().findById(account.getId());

		if (accountOption.isPresent()) {
			Account created = accountOption.get();
			assertNotEquals(Long.valueOf(0), account.getId());
			assertEquals(account.getId(), created.getId());
		} else {
			fail();
		}
	}

	@Test
	public void findByText() {
		List<String> names = Arrays.asList("atest-1", "atest-2", "atest-3");
		names.forEach(n -> {
			Account a = new Account(n);
			a.setEmployeeCd(n);
			a.setMailAddress("example" + n + "@test.com");
			a.create();
		});

		AccountRepository repository = new AccountRepository();
		NegotiationRelationFindByTextFormDTO dto = new NegotiationRelationFindByTextFormDTO();
		dto.setText("atest");
		List<NegotiationRelationDTO> accounts = repository.findByText(dto);

		assertEquals(names.size(), accounts.size());
	}

}