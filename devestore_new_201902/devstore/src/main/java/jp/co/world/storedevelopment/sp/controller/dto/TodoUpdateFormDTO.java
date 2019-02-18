package jp.co.world.storedevelopment.sp.controller.dto;

public class TodoUpdateFormDTO extends TodoCreateFormDTO {

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
