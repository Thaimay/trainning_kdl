package jp.co.world.storedevelopment.model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.mapper.repository.IBusinessCardRepository;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationFindByTextFormDTO;

public class IBusinessCardTest extends ModelTest {

	@Test
	public void create() {
		IBusinessCard businessCard = new IBusinessCard().create();

		businessCard.create();

		assertNotEquals(Long.valueOf(0), businessCard.getId());

		Optional<IBusinessCard> businessCardOption = new IBusinessCardRepository().findById(businessCard.getId());

		if (businessCardOption.isPresent()) {
			IBusinessCard created = businessCardOption.get();
			assertNotEquals(Long.valueOf(0), businessCard.getId());
			assertEquals(businessCard.getId(), created.getId());
		} else {
			fail();
		}
	}

	@Test
	public void findByText() {
		List<String> names = Arrays.asList("atest-1", "atest-2", "atest-3");
		names.forEach(n -> new IBusinessCard(n).create());

		IBusinessCardRepository repository = new IBusinessCardRepository();
		NegotiationRelationFindByTextFormDTO dto = new NegotiationRelationFindByTextFormDTO();
		dto.setText("atest");
		List<NegotiationRelationDTO> businessCard = repository.findByText(dto);

		assertEquals(names.size(), businessCard.size());

	}

}
