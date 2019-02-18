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
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationNoticeAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationRepository;

public class NegotiationNoticeAccountTest extends ModelTest {

	@Test
	public void create() {
		Negotiation negotiation = new Negotiation().create();
		Account account = new AccountRepository().getHead().get();

		NegotiationNoticeAccount accessRecord = new NegotiationNoticeAccount(negotiation, account);
		accessRecord.create();

		assertNotEquals(Long.valueOf(0), accessRecord.getId());

		Optional<NegotiationNoticeAccount> negotiationOption = new NegotiationNoticeAccountRepository()
				.findById(accessRecord.getId());

		if (negotiationOption.isPresent()) {
			NegotiationNoticeAccount createdNoticeAccount = negotiationOption.get();
			assertNotEquals(Long.valueOf(0), accessRecord.getId());
			assertEquals(accessRecord.getId(), createdNoticeAccount.getId());
		} else {
			fail();
		}
	}

	@Test
	public void deleteByNegotiation() {
		Negotiation negotiation = new NegotiationRepository().getHead().get();
		List<Account> account = new AccountRepository().findAll();
		assertTrue(account.size() > 0);
		negotiation.addNoticeAccount(account.get(0));

		int size = negotiation.getNoticeAccounts().size();
		assertTrue(size > 0);

		int deleteSize = new NegotiationNoticeAccountRepository().deleteByNegotiation(negotiation);
		assertEquals(size, deleteSize);

		assertEquals(0, negotiation.getNoticeAccounts().size());

	}

}
