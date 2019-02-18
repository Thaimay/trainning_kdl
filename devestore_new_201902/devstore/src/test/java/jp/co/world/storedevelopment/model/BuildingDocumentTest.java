package jp.co.world.storedevelopment.model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingDocumentRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;

public class BuildingDocumentTest extends ModelTest {
	@Test
	public void createDocument() throws IOException {
		Account account = new AccountRepository().getHead().get();
		Building building = new BuildingRepository().getHead().get();

		Path path = Paths.get("./_test", "sample.pdf");
		InputStream is = Files.newInputStream(path);
		MultipartFile multiPartFile = new MockMultipartFile("sample.pdf", "sample.pdf", null, is);

		BuildingDocument document = new BuildingDocument(multiPartFile, building, account);
		document.create();

		InputStream createdIs = Files.newInputStream(Paths.get(File.DOCUMENT_FILE_PATH, document.getName()));
		MultipartFile multi = new MockMultipartFile(document.getName(), document.getName(), null, createdIs);
		Long multiSize = multi.getSize();

		assertEquals(document.getSize(), multiSize);
		assertEquals(3, new BuildingDocumentRepository().countAll());

		Path createImagePath = Paths.get(File.DOCUMENT_FILE_PATH, document.getName());
		assertTrue(Files.exists(createImagePath));

		Building result = new BuildingRepository().findById(building.getId()).get();
		assertEquals(result.getId(), building.getId());
	}

}
