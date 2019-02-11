package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.IBusinessCard;

public class IBusinessCardRelationProjectDTO implements DTO<IBusinessCard> {

	private Long id;
	private String name;

	public IBusinessCardRelationProjectDTO() {

	}

	public IBusinessCardRelationProjectDTO(IBusinessCard businessCard) {
		this.copyProperties(this, businessCard);
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

	@Override
	public IBusinessCard createModel() {
		return new IBusinessCard();
	}

}
