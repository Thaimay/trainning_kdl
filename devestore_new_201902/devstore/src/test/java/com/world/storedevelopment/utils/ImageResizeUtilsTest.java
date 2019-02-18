package com.world.storedevelopment.utils;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import jp.co.world.storedevelopment.model.NegotiationImage;
import jp.co.world.storedevelopment.utils.ImageResizeUtils;

public class ImageResizeUtilsTest {
	@Test
	public void createImage() throws IOException {
		Path path = Paths.get("./_test", "apita.jpg");
		InputStream is = Files.newInputStream(path);
		MultipartFile multiPartFile = new MockMultipartFile("apita.jpg", "apita.jpg", null, is);

		byte[] result = ImageResizeUtils.resize(multiPartFile, 40, 60, 1);

		assertNotEquals(result, multiPartFile.getBytes());

		java.io.File f = new java.io.File(NegotiationImage.IMAGE_FILE_PATH + "apita.jpg");

		try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(f));) {
			stream.write(result);
		} catch (IOException e) {
			fail();
		}
	}
}
