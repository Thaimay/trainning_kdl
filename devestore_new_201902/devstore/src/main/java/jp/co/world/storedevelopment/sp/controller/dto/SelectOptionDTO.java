package jp.co.world.storedevelopment.sp.controller.dto;

public class SelectOptionDTO {
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	private String label;
	private String value;
	
	public SelectOptionDTO(String label, String value) {
		setLabel(label);
		setValue(value);
	}
}
