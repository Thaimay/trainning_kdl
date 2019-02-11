package jp.co.world.storedevelopment.batch.masterimport.converter;

import java.time.format.DateTimeFormatter;

import org.apache.commons.beanutils.Converter;

public class CSVStringToLocalDateTimeConverter implements Converter {

	@Override
	public <T> T convert(Class<T> type, Object value) {
		if (value == null) {
			return null;
		} else {
			if (type == java.time.LocalDateTime.class && value instanceof String) {
				if (value.toString().matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
					DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					return type.cast(java.time.LocalDateTime.parse(value.toString(), f));
				} else if (value.toString().matches("\\d{4}-\\d{2}-\\d{2}")) {
					DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					return type.cast(java.time.LocalDate.parse(value.toString(), f).atStartOfDay());
				} else if (value.toString().matches("\\d{4}/\\d{2}/\\d{2}")) {
					DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy/MM/dd");
					return type.cast(java.time.LocalDate.parse(value.toString(), f).atStartOfDay());
				} else if (value.toString().matches("\\d{2}/\\d{2}/\\d{2}")) {
					DateTimeFormatter f = DateTimeFormatter.ofPattern("yy/MM/dd");
					return type.cast(java.time.LocalDate.parse(value.toString(), f).atStartOfDay());
				} else if (value.toString().matches("\\d{14}")) {
					DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
					return type.cast(java.time.LocalDateTime.parse(value.toString(), f));
				} else if (value.toString().matches("\\d{8}")) {
					DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyyMMdd");
					return type.cast(java.time.LocalDate.parse(value.toString(), f).atStartOfDay());
				} else {
					return null;
				}
			}
			return type.cast(value.toString());
		}
	}

}
