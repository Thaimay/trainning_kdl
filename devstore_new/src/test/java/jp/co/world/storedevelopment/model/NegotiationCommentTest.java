package jp.co.world.storedevelopment.model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.Test;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationCommentRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationRepository;

public class NegotiationCommentTest extends ModelTest {

	@Test
	public void create() {
		String text = "test comment";
		Negotiation negotiation = new NegotiationRepository().getHead().orElse(new Negotiation());
		Account account = new AccountRepository().getHead().orElse(new Account());

		NegotiationComment negotiatiionComment = new NegotiationComment(negotiation, account, text).create();

		assertNotEquals(Long.valueOf(0), negotiatiionComment.getId());

		Optional<NegotiationComment> negotiationOption = new NegotiationCommentRepository()
				.findById(negotiatiionComment.getId());

		if (negotiationOption.isPresent()) {
			NegotiationComment createdNegotiationComment = negotiationOption.get();
			assertNotEquals(Long.valueOf(0), negotiatiionComment.getId());
			assertEquals(negotiatiionComment.getId(), createdNegotiationComment.getId());
		} else {
			fail();
		}
	}

}
