package jp.co.world.storedevelopment.model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.mapper.repository.PushNotificationPermitRepository;

public class PushNotificationPermitTest extends ModelTest {

	@Test
	public void create() {
		PushNotificationPermit pushNotificationPermit = new PushNotificationPermit("000000", "device_token", "Android");
		pushNotificationPermit.create();

		assertNotEquals(Long.valueOf(0), pushNotificationPermit.getId());

		Optional<PushNotificationPermit> result = new PushNotificationPermitRepository()
				.findById(pushNotificationPermit.getId());
		assertTrue(result.isPresent());

	}

}