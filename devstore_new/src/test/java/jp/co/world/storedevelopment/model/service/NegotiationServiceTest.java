package jp.co.world.storedevelopment.model.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Brand;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.IBusinessCard;
import jp.co.world.storedevelopment.model.ICorporation;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.NegotiationDivision;
import jp.co.world.storedevelopment.model.NegotiationFile;
import jp.co.world.storedevelopment.model.NegotiationImage;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BrandRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBusinessCardRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationRepository;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationCreateFileDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationCreateFormDTO;

public class NegotiationServiceTest extends ModelTest {

	private Account account;

	private Negotiation negotiation;

	private NegotiationCreateFormDTO dto;

	@Test
	public void createAllWithoutFile() throws Throwable {
		account = new AccountRepository().getHead().get();
		dto = dtoFixture();

		negotiation = dto.toModel();

		new NegotiationService().createAll(negotiation, dto, account);

		assertNotEquals(Long.valueOf(0), negotiation.getId());

		Optional<Negotiation> negotiationOption = new NegotiationRepository().findById(negotiation.getId());

		assertDTO(negotiationOption);

		try {
			Negotiation n = negotiationOption.orElseThrow(() -> {
				throw new IllegalArgumentException();
			});
			assertEquals(n.getInterviewAccounts().size(), 2);
			assertEquals(n.getInterviewCorporations().size(), 1);
			assertEquals(n.getInterviewBussinessCards().size(), 1);
			assertEquals(n.getInterviewBrands().size(), 1);
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	@Test
	public void createAllWithFile() {
		account = new AccountRepository().getHead().get();

		dto = dtoFixtureWithFileupload();

		negotiation = dto.toModel();

		new NegotiationService().createAll(negotiation, dto, account);

		assertNotEquals(Long.valueOf(0), negotiation.getId());

		Optional<Negotiation> negotiationOption = new NegotiationRepository().findById(negotiation.getId());

		assertDTO(negotiationOption);

		List<NegotiationImage> images = negotiation.getImages();
		NegotiationImage image = images.get(0);

		List<NegotiationFile> files = negotiation.getFiles();
		NegotiationFile file = files.get(0);

		assertEquals(dto.getMultipartFiles().get().size(), files.size());
		assertEquals(file.getDivision(), "NEGOTIATION");
		assertEquals(file.getComment(), "ファイルのコメント");
	}

	@Test
	public void saveTemporary() {
		account = new AccountRepository().getHead().get();
		dto = dtoFixtureWithFileupload();
		negotiation = dto.toModel();

		new NegotiationService().saveTemporary(negotiation, dto, account);

		Optional<Negotiation> negotiationOption = new NegotiationRepository().findById(negotiation.getId());

		assertDTO(negotiationOption);

		assertTrue(negotiationOption.get().isDraft());
	}

	private NegotiationCreateFormDTO dtoFixtureWithFileupload() {
		NegotiationCreateFormDTO d = dtoFixture();
		NegotiationCreateFileDTO fileDTO = new NegotiationCreateFileDTO();
		fileDTO.setDivision("NEGOTIATION");
		fileDTO.setComment("ファイルのコメント");
		d.setFileInformation(Arrays.asList(fileDTO));

		Path path = Paths.get("./_test", "apita.jpg");
		assertTrue(Files.exists(path));

		try {
			InputStream is = Files.newInputStream(path);
			MultipartFile file = new MockMultipartFile("apita.jpg", "apita.jpg", null, is);

			d.setMultipartFiles(Optional.ofNullable(Arrays.asList(file)));

		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}

		return d;
	}

	private NegotiationCreateFormDTO dtoFixture() {
		List<Account> accounts = new AccountRepository().findAll();
		List<Long> accountIds = accounts.stream().map(a -> a.getId()).collect(Collectors.toList()).subList(0, 2);

		List<ICorporation> corporations = new ICorporationRepository().findAll();
		List<Long> corporationsIds = corporations.stream().map(c -> c.getId()).collect(Collectors.toList()).subList(0,
				1);

		List<Building> buildings = new BuildingRepository().findAll();
		List<Long> buildingIds = buildings.stream().map(b -> b.getId()).collect(Collectors.toList()).subList(0, 1);

		List<Brand> brands = new BrandRepository().findAll();
		List<Long> brandIds = brands.stream().map(b -> b.getId()).collect(Collectors.toList()).subList(0, 1);

		List<IBusinessCard> cards = new IBusinessCardRepository().findAll();
		List<Long> cardIds = cards.stream().map(c -> c.getId()).collect(Collectors.toList()).subList(0, 1);

		NegotiationCreateFormDTO dto = new NegotiationCreateFormDTO();
		dto.setDivision(NegotiationDivision.NEGOTIATON.toString());
		dto.setContent("test-content");
		dto.setNextActionContent("next action");
		dto.setPriority(1);
		dto.setRelease(false);
		dto.setTitle("test-title");
		dto.setScheduleStartDatetime(LocalDateTime.now());
		dto.setScheduleEndDatetime(LocalDateTime.now());

		dto.setAccountIds(accountIds);
		dto.setCorporationIds(corporationsIds);
		dto.setBuildingIds(buildingIds);
		dto.setNoticeIds(accountIds);
		dto.setBrandIds(brandIds);
		dto.setBussinessCardIds(cardIds);

		return dto;
	}

	private void assertDTO(Optional<Negotiation> negotiationOption) {
		if (!negotiationOption.isPresent()) {
			fail();
		}
		Negotiation created = negotiationOption.get();

		assertTrue(created.getId() > 0);
		assertEquals(created.getTitle(), negotiation.getTitle());

		int accountSize = new AccountRepository().countAll();
		// assertEquals(accountSize, created.getAccessRecoreds().size());
		assertEquals("interview account size", dto.getAccountIds().size(), created.getInterviewAccounts().size());
		assertEquals("interview courporation size", dto.getCorporationIds().size(),
				created.getInterviewCorporations().size());
		assertEquals("interview building size", dto.getBuildingIds().size(), created.getInterviewBuildings().size());
		assertEquals("interview brand size", dto.getBrandIds().size(), created.getInterviewBrands().size());
		assertEquals("interview notice size", dto.getAccountIds().size(), created.getNoticeAccounts().size());

	}

}
