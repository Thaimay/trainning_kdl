package jp.co.world.storedevelopment.sp.controller.dto;

public class RelatedNegotiationFindOptionDTO {
	private Long id;
	private String name;
	private boolean isOnly;

	public RelatedNegotiationFindOptionDTO(Long id, String name, boolean isOnly) {
		setId(id);
		setName(name);
		setOnly(isOnly);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOnly() {
		return isOnly;
	}

	public void setOnly(boolean isOnly) {
		this.isOnly = isOnly;
	}

}
