
package jp.co.world.storedevelopment.model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.Test;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.mapper.repository.DepartmentRepository;

public class DepartmentTest extends ModelTest {

	@Test
	public void create() {
		Department account = new Department("000", "000", "00000");
		account.create();

		assertNotEquals(Long.valueOf(0), account.getId());

		Optional<Department> accountOption = new DepartmentRepository().findById(account.getId());

		if (accountOption.isPresent()) {
			Department created = accountOption.get();
			assertNotEquals(Long.valueOf(0), account.getId());
			assertEquals(account.getId(), created.getId());
		} else {
			fail();
		}
	}

}