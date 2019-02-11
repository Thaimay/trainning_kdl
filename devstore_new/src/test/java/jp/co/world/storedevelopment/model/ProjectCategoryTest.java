package jp.co.world.storedevelopment.model;

import org.junit.Before;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;

public class ProjectCategoryTest extends ModelTest {
	Account account;

	@Override
	@Before
	public void before() {
		super.before();
		account = new AccountRepository().getHead().get();
	}

}
