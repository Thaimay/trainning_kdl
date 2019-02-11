package jp.co.world.storedevelopment.model.value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FileName {

	private static final DateTimeFormatter TODAY_FORMAT = DateTimeFormatter.ofPattern("yyyy_MM_dd");

	private static final RandomString random = new RandomString();

	public static String randomImageName() {
		return TODAY_FORMAT.format(LocalDate.now()) + "_" + random.nextString();
	}

}
