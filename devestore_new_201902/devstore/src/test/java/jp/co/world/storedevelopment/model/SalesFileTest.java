package jp.co.world.storedevelopment.model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationImageRepository;
import jp.co.world.storedevelopment.model.mapper.repository.SalesFileRepository;
import jp.co.world.storedevelopment.model.value.FileDivision;

public class SalesFileTest extends ModelTest {

	@Test
	public void create() throws IOException {
		Account account = new AccountRepository().getHead().get();
		FileDivision division = FileDivision.OTHER;
		Path path = Paths.get("./_test", "apita.jpg");
		InputStream is = Files.newInputStream(path);
		MultipartFile multiPartFile = new MockMultipartFile("apita.jpg", "apita.jpg", null, is);

		SalesFile image = new SalesFile(multiPartFile, FileDivision.toString(division), account);
		image.create();

		InputStream createdIs = Files.newInputStream(Paths.get(image.basePath(), image.getName()));
		MultipartFile multi = new MockMultipartFile(image.getName(), image.getName(), null, createdIs);
		Long multiSize = multi.getSize();

		assertEquals(image.getSize(), multiSize);
		assertEquals(3, new NegotiationImageRepository().countAll());

		Path createImagePath = Paths.get(image.basePath(), image.getName());
		assertTrue(Files.exists(createImagePath));
	}

	@Test
	public void find() throws IOException {
		Account account = new AccountRepository().getHead().get();
		FileDivision division = FileDivision.OTHER;
		Path path = Paths.get("./_test", "apita.jpg");
		InputStream is = Files.newInputStream(path);
		MultipartFile multiPartFile = new MockMultipartFile("apita.jpg", "apita.jpg", null, is);

		SalesFile image = new SalesFile(multiPartFile, FileDivision.toString(division), account);
		image.create();
		image.setName("検索用文言");
		image.update();

		List<SalesFile> list = new SalesFileRepository().findByText("検索用");
		assertEquals(1, list.size());
	}

}
