package jp.co.world.storedevelopment.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonParseUtils {
	public static String parse(Object obj) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException("Jsonへの変換が失敗しました");
		}
	}
}
