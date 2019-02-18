package jp.co.world.storedevelopment;

import static org.junit.Assert.assertTrue;

public class PartnersPortalAuthTest {
	public void failAuth() {
		PartnersPortalAuth auth = new PartnersPortalAuth();

		Boolean result = auth.check();
		assertTrue(result == false);
	}
}
