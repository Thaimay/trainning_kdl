package jp.co.world.storedevelopment.model;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationRepository;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationFindFormDTO;

public class NegotiationRoleSearchTest extends ModelTest {
	private void createNegotiation() {
		Negotiation open = new Negotiation();
		open.setTitle("OPEN LEVEL 2");
		open.setReleaseLevel("NEGOTIATION_LV2");
		open.setRelease(true);
		open.create();

		Negotiation close = new Negotiation();
		close.setTitle("CLOSE LEVEL 2");
		close.setReleaseLevel("NEGOTIATION_LV2");
		close.setRelease(false);
		close.create();
	}

	@Test
	public void findNegotiationLevel1None() {
		createNegotiation();

		NegotiationFindFormDTO dto = new NegotiationFindFormDTO();
		Account target = new AccountRepository().getHead().get();
		dto.setAccount(target);

		List<Negotiation> list = new NegotiationRepository().find(dto);

		assertEquals(list.size(), 0);
	}

	@Test
	public void findNegotiationLevel2Open() {
		createNegotiation();

		NegotiationFindFormDTO dto = new NegotiationFindFormDTO();
		Account target = new AccountRepository().findByEmail("role1@test.com").get();
		dto.setAccount(target);

		List<Negotiation> list = new NegotiationRepository().find(dto);

		assertEquals(list.size(), 1);

		Negotiation negotiation = list.get(0);
		assertEquals(negotiation.getTitle(), "OPEN LEVEL 2");
	}

	@Test
	public void findNegotiationLevel2Close() {
		createNegotiation();

		NegotiationFindFormDTO dto = new NegotiationFindFormDTO();
		Account target = new AccountRepository().findByEmail("role2@test.com").get();
		dto.setAccount(target);

		List<Negotiation> list = new NegotiationRepository().find(dto);

		assertEquals(list.size(), 1);

		Negotiation negotiation = list.get(0);
		assertEquals(negotiation.getTitle(), "CLOSE LEVEL 2");
	}

}
