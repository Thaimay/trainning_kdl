package jp.co.world.storedevelopment.model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.mapper.repository.PushNotificationResultRepository;

public class PushNotificationResultTest extends ModelTest {

	@Test
	public void create() {
		PushNotificationResult pushNotificationResult = new PushNotificationResult("対象ファイル", "Android", 1L, 30L, 20L,
				10L);
		pushNotificationResult.create();

		assertNotEquals(Long.valueOf(0), pushNotificationResult.getId());

		Optional<PushNotificationResult> result = new PushNotificationResultRepository()
				.findById(pushNotificationResult.getId());
		assertTrue(result.isPresent());

	}

}