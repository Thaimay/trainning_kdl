
package jp.co.world.storedevelopment.model;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationRepository;
import jp.co.world.storedevelopment.sp.controller.dto.ImportantInformationFindForm;
import jp.co.world.storedevelopment.sp.controller.dto.ImportantInformationListDTO;

public class TopTest extends ModelTest {

	@Test
	public void findImportantInformationByDate() {
		ImportantInformationFindForm dto = new ImportantInformationFindForm();
		dto.setAccount(new AccountRepository().getHead().get());
		List<ImportantInformationListDTO> list = new ImportantInformationRepository().find(dto);

		assertEquals(list.size(), 3);
	}

}