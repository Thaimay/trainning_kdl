package jp.co.world.storedevelopment.batch;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import jp.co.world.storedevelopment.batch.masterimport.ImportBatch;

public abstract class BatchTest {

	static {
		System.setProperty("run", ImportBatch.MODE);
	}

	@Test
	public void batchTest() {
		deleteAll();
		execute();
		assertion();
	}

	protected abstract void deleteAll();

	protected abstract void execute();

	protected void assertion() {
		assertEquals(getExpected(), getActual());
	}

	protected abstract int getActual();

	protected abstract int getExpected();
}
