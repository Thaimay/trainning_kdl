package com.tierline.mybatis.activemodel.converter;

import java.time.format.DateTimeFormatter;

import org.apache.commons.beanutils.Converter;

public class LocalDateToStringConverter implements Converter {

	@Override
	public <T> T convert(Class<T> type, Object value) {
		if (value == null) {
			return null;
		} else {
			if (value instanceof java.time.LocalDate) {
				DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				return type.cast(f.format((java.time.LocalDate) value));
			}
			return type.cast(value.toString());
		}
	}

}
