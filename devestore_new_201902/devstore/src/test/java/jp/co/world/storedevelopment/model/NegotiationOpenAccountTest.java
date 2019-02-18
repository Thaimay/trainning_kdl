package jp.co.world.storedevelopment.model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.Test;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationOpenAccountRepository;

public class NegotiationOpenAccountTest extends ModelTest {

	@Test
	public void create() {
		Negotiation negotiation = new Negotiation().create();
		Account account = new AccountRepository().getHead().get();

		NegotiationOpenAccount readLaterAccount = new NegotiationOpenAccount(negotiation, account);
		readLaterAccount.create();

		assertNotEquals(Long.valueOf(0), readLaterAccount.getId());

		Optional<NegotiationOpenAccount> negotiationOption = new NegotiationOpenAccountRepository()
				.findById(readLaterAccount.getId());

		if (negotiationOption.isPresent()) {
			NegotiationOpenAccount createdAccessRecord = negotiationOption.get();
			assertNotEquals(Long.valueOf(0), readLaterAccount.getId());
			assertEquals(readLaterAccount.getId(), createdAccessRecord.getId());
		} else {
			fail();
		}
	}

	@Test
	public void findByAccount() {
		Account account = new AccountRepository().getHead().get();
		Negotiation negotiation = new Negotiation().create();

		NegotiationOpenAccount readLaterAccount = new NegotiationOpenAccount(negotiation, account);
		readLaterAccount.create();

		Optional<NegotiationOpenAccount> option = new NegotiationOpenAccountRepository().findByAccount(negotiation,
				account);
		assertTrue(option.isPresent());
	}

}
