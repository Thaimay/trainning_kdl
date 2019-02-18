package jp.co.world.storedevelopment.dev;

import jp.co.world.storedevelopment.model.Account;

abstract class InitTestDataSupport {
	protected Account MAIN_ACCOUNT = null;

	public void doInit(InitTestData main) {
		MAIN_ACCOUNT = main.MAIN_ACCOUNT;
		init(main);
	}

	protected abstract void init(InitTestData main);
}
