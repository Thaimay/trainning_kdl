package jp.co.world.storedevelopment.model.value;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class FileNameTest {

	private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy_MM_dd");

	@Test
	public void imageName() {
		String name = FileName.randomImageName();
		String today = dateFormat.format(LocalDate.now());

		assertTrue(name, StringUtils.contains(name, today));
	}

}
