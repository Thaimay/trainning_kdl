
package jp.co.world.storedevelopment.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.Test;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.RoleRepository;

public class RoleTest extends ModelTest {

	@Test
	public void create() {
		Role role = new Role("123456", "説明", "", "", "", false, false, false, "LV2");
		role.create();

		assertNotEquals(Long.valueOf(0), role.getId());

		Optional<Role> accountOption = new RoleRepository().findById(role.getId());

		if (accountOption.isPresent()) {
			Role created = accountOption.get();
			assertNotEquals(Long.valueOf(0), role.getId());
			assertEquals(role.getId(), created.getId());
		} else {
			fail();
		}
	}

	@Test
	public void findByAccount() {
		Account account = new AccountRepository().getHead().get();
		Role role = new Role("123456", "説明", "", "", "000000", false, false, false, "LV2");
		role.create();

		assertNotEquals(Long.valueOf(0), role.getId());

		Optional<Role> roleOpt = new RoleRepository().findByAccount(account);

		assertTrue(roleOpt.isPresent());
	}

	@Test
	public void findNoneByAccount() {
		Account account = new Account("111111", "権限テスト", "111@test.com", "");
		account.create();
		SubAccount subAccount = new SubAccount();
		subAccount.setAreaCode("");
		subAccount.setEmployeeCode(account.getEmployeeCd());
		subAccount.create();

		Optional<Role> roleOpt = new RoleRepository().findByAccount(account);

		assertTrue(roleOpt.isPresent() == false);
	}

	@Test
	public void findCompanyCodeByAccount() {
		Account account = new Account("111111", "権限テスト", "111@test.com", "");
		account.setExpensesDepartmentCd("000002");
		account.create();

		Optional<Role> roleOpt = new RoleRepository().findByAccount(account);
		assertTrue(roleOpt.isPresent());
	}

	@Test
	public void findIncomeCodeByAccount() {
		Account account = new Account("111111", "権限テスト", "111@test.com", "");
		account.setExpensesDepartmentCd("000003");
		account.create();

		Optional<Role> roleOpt = new RoleRepository().findByAccount(account);
		assertTrue(roleOpt.isPresent());
	}

	@Test
	public void hasPermission() {
		Role role = new Role("123456", "説明", "", "", "", false, false, false, "LV2");
		assertTrue(role.hasPermission("LV2"));
		assertFalse(role.hasPermission("LV1"));
	}

}