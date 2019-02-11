package jp.co.world.storedevelopment.model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationCorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationRepository;

public class ImportantInformationCorporationTest {
	@Test
	public void create() {
		ICorporation c = new ICorporationRepository().getHead().get();
		ImportantInformation in = new ImportantInformationRepository().getHead().get();
		ImportantInformationCorporation inc = new ImportantInformationCorporation(in, c);

		inc.create();
		assertNotEquals(Long.valueOf(0), inc.getId());

		Optional<ImportantInformationCorporation> result = new ImportantInformationCorporationRepository()
				.findById(in.getId());

		assertTrue(result.isPresent());
	}

}
