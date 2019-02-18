package jp.co.world.storedevelopment.model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.Test;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewBuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationRepository;

public class NegotiationInterviewBuildingTest extends ModelTest {

	@Test
	public void create() {
		List<Building> list = new BuildingRepository().findAll();
		Building mock = list.get(0);

		Negotiation negotiation = new Negotiation().create();

		NegotiationInterviewBuilding building = new NegotiationInterviewBuilding(negotiation);
		building.setBuildingId(mock.getId());
		building.create();

		assertNotEquals(Long.valueOf(0), building.getId());

		Optional<NegotiationInterviewBuilding> negotiationOption = new NegotiationInterviewBuildingRepository()
				.findById(building.getId());

		if (negotiationOption.isPresent()) {
			NegotiationInterviewBuilding createdInterviewBuilding = negotiationOption.get();
			assertNotEquals(Long.valueOf(0), building.getId());
			assertEquals(building.getId(), createdInterviewBuilding.getId());
		} else {
			fail();
		}
	}

	@Test
	public void deleteByNegotiation() {
		Negotiation negotiation = new NegotiationRepository().getHead().get();
		List<Building> buildings = new BuildingRepository().findAll();
		assertTrue(buildings.size() > 0);
		negotiation.addInterviewBuilding(buildings.get(0));

		int size = negotiation.getInterviewBuildings().size();
		assertTrue(size > 0);

		int deleteSize = new NegotiationInterviewBuildingRepository().deleteByNegotiation(negotiation);
		assertEquals(size, deleteSize);

		assertEquals(0, negotiation.getInterviewBuildings().size());

	}

}
