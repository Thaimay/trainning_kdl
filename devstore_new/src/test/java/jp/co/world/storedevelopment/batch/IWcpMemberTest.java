package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.IWcpMemberBatch;
import jp.co.world.storedevelopment.model.mapper.repository.IWcpMemberRepository;

public class IWcpMemberTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new IWcpMemberRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		IWcpMemberBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 4850;
	}

	@Override
	protected int getExpected() {
		return new IWcpMemberRepository().countAll();
	}

}
