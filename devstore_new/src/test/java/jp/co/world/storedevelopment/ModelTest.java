package jp.co.world.storedevelopment;

import org.junit.Before;

import jp.co.world.storedevelopment.dev.InitTestData;
import jp.co.world.storedevelopment.model.Account;

public abstract class ModelTest extends CRUDTest {

	protected Account MAIN_ACCOUNT;

	@Override
	@Before
	public void before() {
		super.before();
		InitTestData testData = new InitTestData();
		testData.initForTest();
		MAIN_ACCOUNT = testData.MAIN_ACCOUNT;
	}
}
