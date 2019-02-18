package jp.co.world.storedevelopment;

import static org.junit.Assert.*;

import org.junit.Test;

import jp.co.world.storedevelopment.model.mapper.repository.WorldAuthAccountRepository;

public class WorldAuthSwithDataSourceTest {

	static {
		System.setProperty("mybatis", "");
		System.setProperty("mode", "local_test");
	}

	@Test
	public void swithcDataSource() {
		try {
			int count = new WorldAuthAccountRepository().countAll();
			assertEquals(1, count);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail();

		}
	}
}
