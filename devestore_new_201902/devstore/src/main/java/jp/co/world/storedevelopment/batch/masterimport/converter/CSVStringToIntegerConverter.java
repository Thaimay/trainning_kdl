package jp.co.world.storedevelopment.batch.masterimport.converter;

import org.apache.commons.beanutils.Converter;

public class CSVStringToIntegerConverter implements Converter {

	@Override
	public <T> T convert(Class<T> type, Object value) {
		if (value == null || value.toString().equals("")) {
			return null;
		} else {
			if (type == Integer.class) {
				Integer number = new Integer((String) value);
				return type.cast(number);
			}
			return type.cast(value.toString());
		}
	}

}
