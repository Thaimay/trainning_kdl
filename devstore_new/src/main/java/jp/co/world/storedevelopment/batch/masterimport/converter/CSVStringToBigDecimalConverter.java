package jp.co.world.storedevelopment.batch.masterimport.converter;

import java.math.BigDecimal;

import org.apache.commons.beanutils.Converter;

public class CSVStringToBigDecimalConverter implements Converter {

	@Override
	public <T> T convert(Class<T> type, Object value) {
		if (value == null || value.toString().equals("")) {
			return null;
		} else {
			if (type == java.math.BigDecimal.class) {
				BigDecimal number = new BigDecimal((String) value);
				return type.cast(number);
			}
			return type.cast(value.toString());
		}
	}

}
