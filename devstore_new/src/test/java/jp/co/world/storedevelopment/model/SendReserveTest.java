package jp.co.world.storedevelopment.model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.mapper.repository.SendReserveRepository;

public class SendReserveTest extends ModelTest {

	@Test
	public void create() {
		SendReserve sendReserve = new SendReserve("000001", "通知メッセージ");
		sendReserve.create();

		assertNotEquals(Long.valueOf(0), sendReserve.getId());

		Optional<SendReserve> result = new SendReserveRepository().findById(sendReserve.getId());
		assertTrue(result.isPresent());

	}

}