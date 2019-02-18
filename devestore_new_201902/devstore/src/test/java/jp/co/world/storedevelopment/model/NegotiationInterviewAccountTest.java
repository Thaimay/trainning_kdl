package jp.co.world.storedevelopment.model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.Test;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationRepository;

public class NegotiationInterviewAccountTest extends ModelTest {

	@Test
	public void createWithAccount() {
		Negotiation negotiation = new Negotiation().create();
		Account account = new AccountRepository().getHead().get();

		NegotiationInterviewAccount acount = new NegotiationInterviewAccount(negotiation, account);
		acount.create();

		assertNotEquals(Long.valueOf(0), acount.getId());

		Optional<NegotiationInterviewAccount> negotiationOption = new NegotiationInterviewAccountRepository()
				.findById(acount.getId());

		if (negotiationOption.isPresent()) {
			NegotiationInterviewAccount createdAccessRecord = negotiationOption.get();
			assertNotEquals(Long.valueOf(0), acount.getId());
			assertEquals(acount.getId(), createdAccessRecord.getId());
		} else {
			fail();
		}
	}

	@Test
	public void createWithFreeName() {
		Negotiation negotiation = new Negotiation().create();
		String name = "free name";

		NegotiationInterviewAccount acount = new NegotiationInterviewAccount(negotiation, name);
		acount.create();

		assertNotEquals(Long.valueOf(0), acount.getId());

		Optional<NegotiationInterviewAccount> negotiationOption = new NegotiationInterviewAccountRepository()
				.findById(acount.getId());

		if (negotiationOption.isPresent()) {
			NegotiationInterviewAccount createdAccessRecord = negotiationOption.get();
			assertNotEquals(Long.valueOf(0), acount.getId());
			assertEquals(acount.getId(), createdAccessRecord.getId());
			assertEquals(name, createdAccessRecord.getUnmanagedName());
		} else {
			fail();
		}
	}

	@Test
	public void deleteByNegotiation() {
		Negotiation negotiation = new NegotiationRepository().getHead().get();
		List<Account> corporations = new AccountRepository().findAll();
		assertTrue(corporations.size() > 0);
		negotiation.addInterviewAccount(corporations.get(0));

		int size = negotiation.getInterviewAccounts().size();
		assertTrue(size > 0);

		int deleteSize = new NegotiationInterviewAccountRepository().deleteByNegotiation(negotiation);
		assertEquals(size, deleteSize);

		assertEquals(0, negotiation.getInterviewAccounts().size());

	}

}
