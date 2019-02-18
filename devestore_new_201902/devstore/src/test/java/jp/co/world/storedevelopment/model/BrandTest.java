package jp.co.world.storedevelopment.model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.mapper.repository.BrandRepository;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationFindByTextFormDTO;

public class BrandTest extends ModelTest {

	@Test
	public void create() {
		Brand brand = new Brand().create();

		brand.create();

		assertNotEquals(Long.valueOf(0), brand.getId());

		Optional<Brand> BrandOption = new BrandRepository().findById(brand.getId());

		if (BrandOption.isPresent()) {
			Brand created = BrandOption.get();
			assertNotEquals(Long.valueOf(0), brand.getId());
			assertEquals(brand.getId(), created.getId());
		} else {
			fail();
		}
	}

	@Test
	public void findByText() {
		List<String> names = Arrays.asList("atest-1", "atest-2", "atest-3");
		names.forEach(n -> new Brand(n).create());

		BrandRepository repository = new BrandRepository();
		NegotiationRelationFindByTextFormDTO dto = new NegotiationRelationFindByTextFormDTO();
		dto.setText("atest");
		List<NegotiationRelationDTO> brand = repository.findByText(dto);

		assertEquals(names.size(), brand.size());

	}

}
