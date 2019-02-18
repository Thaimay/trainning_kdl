package jp.co.world.storedevelopment.model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.mapper.repository.InvalidDeviceTokenRepository;

public class InvalidDeviceTokenTest extends ModelTest {

	@Test
	public void create() {
		InvalidDeviceToken invalidDeviceToken = new InvalidDeviceToken("device_token", 1L, "エラー", "Android", "on");
		invalidDeviceToken.create();

		assertNotEquals(Long.valueOf(0), invalidDeviceToken.getId());

		Optional<InvalidDeviceToken> result = new InvalidDeviceTokenRepository().findById(invalidDeviceToken.getId());
		assertTrue(result.isPresent());

	}

}