package jp.co.world.storedevelopment.batch.masterimport.converter;

import java.time.format.DateTimeFormatter;

import org.apache.commons.beanutils.Converter;

public class CSVStringToLocalDateConverter implements Converter {

	@Override
	public <T> T convert(Class<T> type, Object value) {
		if (value == null) {
			return null;
		} else {
			if (type == java.time.LocalDate.class) {
				if (value.toString().matches("\\d{4}-\\d{2}-\\d{2}")) {
					DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					return type.cast(java.time.LocalDate.parse((String) value, f));
				} else if (value.toString().matches("\\d{4}/\\d{2}/\\d{2}")) {
					DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy/MM/dd");
					return type.cast(java.time.LocalDate.parse((String) value, f));
				} else if (value.toString().matches("\\d{8}")) {
					DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyyMMdd");
					return type.cast(java.time.LocalDate.parse((String) value, f));
				} else {
					return null;
				}
			}
			return type.cast(value.toString());
		}
	}

}
