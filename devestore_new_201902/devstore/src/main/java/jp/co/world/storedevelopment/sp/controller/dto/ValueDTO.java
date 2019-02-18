package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

public class ValueDTO {

	private Long id;

	private String label = "";

	public ValueDTO() {
		//
	}

	public ValueDTO(Long id, String label) {
		this.id = id;
		this.label = label;

	}

	public ValueDTO(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public static List<ValueDTO> toList(List<Long> ids) {
		return ids.stream().map(id -> new ValueDTO(id)).collect(Collectors.toList());
	}
}
