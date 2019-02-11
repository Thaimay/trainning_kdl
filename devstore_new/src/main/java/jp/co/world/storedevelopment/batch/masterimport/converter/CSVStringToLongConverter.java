package jp.co.world.storedevelopment.batch.masterimport.converter;

import org.apache.commons.beanutils.Converter;

public class CSVStringToLongConverter implements Converter {

	@Override
	public <T> T convert(Class<T> type, Object value) {
		if (value == null || value.toString().equals("")) {
			return null;
		} else {
			if (type == Long.class) {
				Long number = new Long((String) value);
				return type.cast(number);
			}
			return type.cast(value.toString());
		}
	}

}
