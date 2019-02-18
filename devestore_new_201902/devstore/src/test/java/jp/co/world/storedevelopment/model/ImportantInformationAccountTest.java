package jp.co.world.storedevelopment.model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationRepository;

public class ImportantInformationAccountTest extends ModelTest {
    @Test
    public void create() {
        Account a = new AccountRepository().getHead().get();
        ImportantInformation in = new ImportantInformationRepository().getHead().get();
        ImportantInformationAccount ina = new ImportantInformationAccount(in, a);

        ina.create();
        assertNotEquals(Long.valueOf(0), ina.getId());

        Optional<ImportantInformationAccount> result = new ImportantInformationAccountRepository().findById(in.getId());

        assertTrue(result.isPresent());
    }
 
}
