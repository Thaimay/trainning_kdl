package jp.co.world.storedevelopment.model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.Test;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.mapper.repository.BrandRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewBrandRepository;

public class NegotiationInterviewBrandTest extends ModelTest {

	@Test
	public void create() {
	    List<Brand> list = new BrandRepository().findAll();
	    Brand brand = list.get(0);

		Negotiation negotiation = new Negotiation().create();

		NegotiationInterviewBrand interviewBrand = new NegotiationInterviewBrand(negotiation);
		interviewBrand.setBrandId(brand.getId());
		interviewBrand.create();

		assertNotEquals(Long.valueOf(0), interviewBrand.getId());

		Optional<NegotiationInterviewBrand> negotiationOption = new NegotiationInterviewBrandRepository()
				.findById(interviewBrand.getId());

		if (negotiationOption.isPresent()) {
			NegotiationInterviewBrand createdInterviewBrand = negotiationOption.get();
			assertNotEquals(Long.valueOf(0), interviewBrand.getId());
			assertEquals(interviewBrand.getId(), createdInterviewBrand.getId());
		} else {
			fail();
		}
	}


}






