package jp.co.world.storedevelopment;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.world.storedevelopment.utils.ZenkakuUtils;

public class ZenkakuSerializer extends JsonSerializer<String> {
	@Override
	public void serialize(
	            String value,
	            JsonGenerator gen,
	            SerializerProvider serializers) throws IOException, JsonProcessingException {
		gen.writeString(ZenkakuUtils.toZenkaku(value));
	}
}
