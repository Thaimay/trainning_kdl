package jp.co.world.storedevelopment.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.dev.GlobalVariable;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationRepository;
import jp.co.world.storedevelopment.sp.controller.dto.ImportantInformationFindForm;
import jp.co.world.storedevelopment.sp.controller.dto.ImportantInformationListDTO;

public class ImportantInformationTest extends ModelTest {
    @Test
    public void create() {
        Negotiation n = new NegotiationRepository().getHead().get();
        ImportantInformation in = new ImportantInformation();
        in.setShowStartDatetime(LocalDateTime.now());
        in.setShowEndDatetime(LocalDateTime.now());
        in.create();

        assertNotEquals(Long.valueOf(0), in.getId());
        Optional<ImportantInformation> result = new ImportantInformationRepository().findById(in.getId());

        assertTrue(result.isPresent());
    }

    @Test
    public void findAll() {
        List<ImportantInformation> list = new ImportantInformationRepository().findAll();
        assertEquals(GlobalVariable.importantNoticeSize * 3, list.size());
    }

    @Test
    public void find() {
        Account account = new AccountRepository().getHead().get();
        ImportantInformationFindForm dto = new ImportantInformationFindForm();
        ImportantInformation in = new ImportantInformation();
        in.setContent("検索用文章");
        in.setCreateAccount(account);
        in.setShowStartDatetime(LocalDateTime.now());
        in.setShowEndDatetime(LocalDateTime.now());
        in.create();
        dto.setAccount(account);
        dto.setText("検索用");
        new ImportantInformationAccount(in, account).create();

        List<ImportantInformationListDTO> list = new ImportantInformationRepository().find(dto);
        assertEquals(1, list.size());
    }

    @Test
    public void isRead() {
        Account account = new AccountRepository().getHead().get();
        ImportantInformation in = new ImportantInformationRepository().getHead().get();

        assertTrue(in.isRead(account) == false);
    }

    @Test
    public void read() {
        Account account = new AccountRepository().getHead().get();
        ImportantInformation in = new ImportantInformationRepository().getHead().get();

        assertTrue(in.isRead(account) == false);
        in.read(account);
        assertTrue(in.isRead(account));
    }

    @Test
    public void nice() {
        Account account = new AccountRepository().getHead().get();
        ImportantInformation in = new ImportantInformationRepository().getHead().get();

        assertTrue(in.isNice(account) == false);
        in.switchNice(account);
        assertTrue(in.isNice(account));
    }

}
