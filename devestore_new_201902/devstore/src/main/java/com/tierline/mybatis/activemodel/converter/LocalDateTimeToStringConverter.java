package com.tierline.mybatis.activemodel.converter;

import java.time.format.DateTimeFormatter;

import org.apache.commons.beanutils.Converter;

public class LocalDateTimeToStringConverter implements Converter {

	@Override
	public <T> T convert(Class<T> type, Object value) {
		if (value == null) {
			return null;
		} else {
			if (value instanceof java.time.LocalDateTime) {
				DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
				return type.cast(f.format((java.time.LocalDateTime) value));
			}
			return type.cast(value.toString());
		}
	}

}
