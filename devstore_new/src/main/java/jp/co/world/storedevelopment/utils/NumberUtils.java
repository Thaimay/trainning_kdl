package jp.co.world.storedevelopment.utils;

public class NumberUtils {
	public static Boolean isNumber(String value) {
		if (value == null) {
			return false;
		}
		
	    try {
	        Integer.parseInt(value);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
}
