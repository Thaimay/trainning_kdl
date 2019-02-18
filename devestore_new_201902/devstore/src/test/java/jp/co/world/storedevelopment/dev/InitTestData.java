package jp.co.world.storedevelopment.dev;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import jp.co.world.storedevelopment.CRUDTest;
import jp.co.world.storedevelopment.model.Account;

/**
 * DB store_development_devにサンプルデータを挿入するJUNIT TESTの仕組みを利用している。
 *
 */
public class InitTestData extends CRUDTest {
	public Account MAIN_ACCOUNT;

	@Override
	@BeforeAll
	public void beforeAll() {
		super.beforeAll();
	}

	public void initForTest() {
		beforeInit();
		init();
	}

	@Before
	public void beforeInit() {
		super.before();
	}

	private void initMain() {
		new InitCommonData().init(this);

		new InitBuildingData().init(this);
		new InitNegotiationData().init(this);
		new InitInformationData().init(this);
		new InitTodoData().init(this);
		new InitProjectCategoryData().init(this);
		new InitMProjectProgressStatusData().init(this);
		new InitMProjectActionStatusData().init(this);
		new InitMProjectMeetingResultData().init(this);;
		new InitProjectData().init(this);
		new InitProjectSwitingItemControlData().init(this);
	}

	@Test
	public void init() {
		initMain();
	}

}
