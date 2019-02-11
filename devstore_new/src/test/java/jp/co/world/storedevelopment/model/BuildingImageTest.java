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
import jp.co.world.storedevelopment.model.mapper.repository.BuildingImageRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;

public class BuildingImageTest extends ModelTest {
	@Test
	public void createImage() throws IOException {
		Account account = new AccountRepository().getHead().get();
		Building building = new BuildingRepository().getHead().get();

		Path path = Paths.get("./_test", "apita.jpg");
		InputStream is = Files.newInputStream(path);
		MultipartFile multiPartFile = new MockMultipartFile("apita.jpg", "apita.jpg", null, is);

		BuildingImage image = new BuildingImage(multiPartFile, building, account);
		image.create();

		InputStream createdIs = Files.newInputStream(Paths.get(File.IMAGE_FILE_PATH, image.getName()));
		MultipartFile multi = new MockMultipartFile(image.getName(), image.getName(), null, createdIs);
		Long multiSize = multi.getSize();

		assertEquals(image.getSize(), multiSize);
		assertEquals(3, new BuildingImageRepository().countAll());

		Path createImagePath = Paths.get(File.IMAGE_FILE_PATH, image.getName());
		assertTrue(Files.exists(createImagePath));

		Building result = new BuildingRepository().findById(building.getId()).get();
		assertEquals(result.getId(), building.getId());
	}

}
