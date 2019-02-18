package jp.co.world.storedevelopment.model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.Test;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationReadLaterAccountRepository;

public class NegotiationReadLaterAccountTest extends ModelTest {

	@Test
	public void create() {
		Negotiation negotiation = new Negotiation().create();
		Account account = new AccountRepository().getHead().get();

		NegotiationReadLaterAccount readLaterAccount = new NegotiationReadLaterAccount(negotiation, account);
		readLaterAccount.create();

		assertNotEquals(Long.valueOf(0), readLaterAccount.getId());

		Optional<NegotiationReadLaterAccount> negotiationOption = new NegotiationReadLaterAccountRepository()
				.findById(readLaterAccount.getId());

		if (negotiationOption.isPresent()) {
			NegotiationReadLaterAccount createdAccessRecord = negotiationOption.get();
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

		NegotiationReadLaterAccount readLaterAccount = new NegotiationReadLaterAccount(negotiation, account);
		readLaterAccount.create();

		Optional<NegotiationReadLaterAccount> option = new NegotiationReadLaterAccountRepository()
				.findByAccount(negotiation, account);
		assertTrue(option.isPresent());
	}

}
