package jp.co.world.storedevelopment.logging;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LogUtil {

	public static String toJSON(Object obj) {
		String jsonInString = "";
		ObjectMapper mapper = new ObjectMapper();

		// Object to JSON in String
		try {
			jsonInString = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return jsonInString;
	}

	public static String getParameterFromRequest(HttpServletRequest request) {
		String parameter = "";
		if (request.getParameterMap() != null && request.getParameterMap().size() > 0) {
			parameter = new JSONObject(request.getParameterMap()).toString();
		}

		return parameter;
	}
}
