package jp.co.world.storedevelopment.model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.Test;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.mapper.repository.IBusinessCardRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewBusinessCardRepository;

public class NegotiationInterviewBusinessCardTest extends ModelTest {

	@Test
	public void create() {
		IBusinessCard card = new IBusinessCardRepository().getHead().orElse(new IBusinessCard());

		Negotiation negotiation = new Negotiation().create();

		NegotiationInterviewBusinessCard businessCard = new NegotiationInterviewBusinessCard(negotiation);
		businessCard.setBusinessCardId(card.getId());
		businessCard.create();

		assertNotEquals(Long.valueOf(0), businessCard.getId());

		Optional<NegotiationInterviewBusinessCard> negotiationOption = new NegotiationInterviewBusinessCardRepository()
				.findById(businessCard.getId());

		if (negotiationOption.isPresent()) {
			NegotiationInterviewBusinessCard createdInterviewBusinessCard = negotiationOption.get();
			assertNotEquals(Long.valueOf(0), businessCard.getId());
			assertEquals(businessCard.getId(), createdInterviewBusinessCard.getId());
		} else {
			fail();
		}
	}
}