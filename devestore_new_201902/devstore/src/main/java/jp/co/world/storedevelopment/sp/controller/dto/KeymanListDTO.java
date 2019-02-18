package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.IBusinessCard;
import jp.co.world.storedevelopment.model.Keyman;
import jp.co.world.storedevelopment.model.mapper.repository.IBusinessCardRepository;

public class KeymanListDTO implements DTO<Keyman> {

	private Long id;
	private String name;
	private Long businessCardId;

	@Override
	public Keyman createModel() {
		return new Keyman();
	}

	public KeymanListDTO() {
		//
	}

	public KeymanListDTO(Keyman km) {
		this.copyProperties(this, km);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBusinessCardId() {
		return businessCardId;
	}

	public void setBusinessCardId(Long businessCardId) {
		this.businessCardId = businessCardId;
	}

	public BusinessCardRelationBuildingListDTO getBusinessCard() {
		IBusinessCard businessCard = new IBusinessCardRepository().findById(this.getBusinessCardId())
				.orElseThrow(() -> new IllegalStateException("存在しない名刺管理IDです:" + this.getBusinessCardId()));
		return new BusinessCardRelationBuildingListDTO(businessCard);
	}

}
