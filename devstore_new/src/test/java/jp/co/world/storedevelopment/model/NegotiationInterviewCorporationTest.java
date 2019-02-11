package jp.co.world.storedevelopment.model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.Test;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewCorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationRepository;

public class NegotiationInterviewCorporationTest extends ModelTest {

	@Test
	public void create() {
		Negotiation negotiation = new Negotiation().create();
		ICorporation corporation = new ICorporation().create();

		NegotiationInterviewCorporation accessRecord = new NegotiationInterviewCorporation(negotiation, corporation);
		accessRecord.create();

		assertNotEquals(Long.valueOf(0), accessRecord.getId());

		Optional<NegotiationInterviewCorporation> negotiationOption = new NegotiationInterviewCorporationRepository()
				.findById(accessRecord.getId());

		if (negotiationOption.isPresent()) {
			NegotiationInterviewCorporation createdAccessRecord = negotiationOption.get();
			assertNotEquals(Long.valueOf(0), accessRecord.getId());
			assertEquals(accessRecord.getId(), createdAccessRecord.getId());
		} else {
			fail();
		}
	}

	@Test
	public void deleteByNegotiation() {
		Negotiation negotiation = new NegotiationRepository().getHead().get();
		List<ICorporation> corporations = new ICorporationRepository().findAll();
		assertTrue(corporations.size() > 0);
		negotiation.addInterviewCorporation(corporations.get(0));

		int size = negotiation.getInterviewCorporations().size();
		assertTrue(size > 0);

		int deleteSize = new NegotiationInterviewCorporationRepository().deleteByNegotiation(negotiation);
		assertEquals(size, deleteSize);

		assertEquals(0, negotiation.getInterviewCorporations().size());

	}

}
