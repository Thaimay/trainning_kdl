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
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationImageRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationRepository;

public class NegotiationImageTest extends ModelTest {
	@Test
	public void createImage() throws IOException {
		Account account = new AccountRepository().getHead().get();
		Negotiation negotiation = new NegotiationRepository().getHead().get();
		Path path = Paths.get("./_test", "apita.jpg");
		InputStream is = Files.newInputStream(path);
		MultipartFile multiPartFile = new MockMultipartFile("apita.jpg", "apita.jpg", null, is);

		NegotiationImage image = new NegotiationImage(multiPartFile, negotiation, account);
		image.create();

		InputStream createdIs = Files.newInputStream(Paths.get(NegotiationImage.IMAGE_FILE_PATH, image.getName()));
		MultipartFile multi = new MockMultipartFile(image.getName(), image.getName(), null, createdIs);
		Long multiSize = multi.getSize();

		assertEquals(image.getSize(), multiSize);
		assertEquals(3, new NegotiationImageRepository().countAll());

		Path createImagePath = Paths.get(NegotiationImage.IMAGE_FILE_PATH, image.getName());
		assertTrue(Files.exists(createImagePath));

		NegotiationImage result = new NegotiationImageRepository().findById(image.getId()).get();
		assertEquals(result.getNegotiationId(), negotiation.getId());
	}

}
