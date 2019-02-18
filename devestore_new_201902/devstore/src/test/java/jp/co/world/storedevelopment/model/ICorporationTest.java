package jp.co.world.storedevelopment.model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationFindByTextFormDTO;

public class ICorporationTest extends ModelTest {

	@Test
	public void create() {
		ICorporation Corporation = new ICorporation().create();

		Corporation.create();

		assertNotEquals(Long.valueOf(0), Corporation.getId());

		Optional<ICorporation> CorporationOption = new ICorporationRepository().findById(Corporation.getId());

		if (CorporationOption.isPresent()) {
			ICorporation created = CorporationOption.get();
			assertNotEquals(Long.valueOf(0), Corporation.getId());
			assertEquals(Corporation.getId(), created.getId());
		} else {
			fail();
		}
	}

	@Test
	public void findByText() {
		List<String> names = Arrays.asList("atest-1", "atest-2", "atest-3");
		names.forEach(n -> new ICorporation(n).create());

		ICorporationRepository repository = new ICorporationRepository();
		NegotiationRelationFindByTextFormDTO dto = new NegotiationRelationFindByTextFormDTO();
		dto.setText("atest");
		List<NegotiationRelationDTO> corporations = repository.findByText(dto);

		assertEquals(names.size(), corporations.size());

	}

}
