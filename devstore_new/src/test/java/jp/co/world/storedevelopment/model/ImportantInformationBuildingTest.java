package jp.co.world.storedevelopment.model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationBuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationRepository;

public class ImportantInformationBuildingTest extends ModelTest {
    @Test
    public void create() {
        Building b = new BuildingRepository().getHead().get();
        ImportantInformation in = new ImportantInformationRepository().getHead().get();
        ImportantInformationBuilding inb = new ImportantInformationBuilding(in, b);

        inb.create();
        assertNotEquals(Long.valueOf(0), inb.getId());

        Optional<ImportantInformationBuilding> result = new ImportantInformationBuildingRepository()
                .findById(in.getId());

        assertTrue(result.isPresent());
    }

}
