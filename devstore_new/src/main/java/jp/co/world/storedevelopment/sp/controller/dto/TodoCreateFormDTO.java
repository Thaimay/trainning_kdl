package jp.co.world.storedevelopment.sp.controller.dto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@JsonIgnoreProperties(ignoreUnknown = true)
// @JsonDeserialize(using = LocalDateDeserializer.class)
// @JsonSerialize(using = LocalDateSerializer.class)
public class TodoCreateFormDTO extends TodoFormDTO {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	public static TodoCreateFormDTO toDTO(String json) {
		try {
			MAPPER.registerModule(new JavaTimeModule());
			return MAPPER.readValue(json, TodoCreateFormDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getMessage());
		}
	}
	
	private List<Long> accountIds = new ArrayList<>();

	public List<Long> getAccountIds() {
		return accountIds;
	}

	public void setAccountIds(List<Long> accountIds) {
		this.accountIds = accountIds;
	}
}
