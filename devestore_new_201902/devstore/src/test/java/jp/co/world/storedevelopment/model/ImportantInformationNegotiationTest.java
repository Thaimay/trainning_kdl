package jp.co.world.storedevelopment.model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationNegotiationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationRepository;

public class ImportantInformationNegotiationTest extends ModelTest {
    @Test
    public void create() {
        Negotiation n = new NegotiationRepository().getHead().get();
        ImportantInformation in = new ImportantInformationRepository().getHead().get();
        ImportantInformationNegotiation inn = new ImportantInformationNegotiation(in, n);

        inn.create();
        assertNotEquals(Long.valueOf(0), inn.getId());

        Optional<ImportantInformationNegotiation> result = new ImportantInformationNegotiationRepository()
                .findById(in.getId());

        assertTrue(result.isPresent());
    }

}
