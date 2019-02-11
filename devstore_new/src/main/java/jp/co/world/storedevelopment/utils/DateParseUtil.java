package jp.co.world.storedevelopment.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParseUtil {
	public static LocalDate parse(DateTimeFormatter formatter, String value) {
		try {
			return LocalDate.parse(value, formatter);
		} catch (java.time.format.DateTimeParseException e) {
			return null;
		}
	}
}
