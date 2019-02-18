package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.Optional;

import jp.co.world.storedevelopment.model.IBusinessCard;
import jp.co.world.storedevelopment.model.ICorporation;
import jp.co.world.storedevelopment.model.Keyman;
import jp.co.world.storedevelopment.model.mapper.repository.IBusinessCardRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;

public class KeymanRelationBuildingDetailDTO implements DTO<Keyman> {

	private Long id;
	private String corporationGroup;
	private Long businessCardId;
	private Long corporationId;

	public KeymanRelationBuildingDetailDTO() {

	}

	public KeymanRelationBuildingDetailDTO(Keyman keyman) {
		copyProperties(this, keyman);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCorporationGroup() {
		return corporationGroup;
	}

	public void setCorporationGroup(String corporationGroup) {
		this.corporationGroup = corporationGroup;
	}

	public Long getBusinessCardId() {
		return businessCardId;
	}

	public void setBusinessCardId(Long businessCardId) {
		this.businessCardId = businessCardId;
	}

	public Long getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(Long corporationId) {
		this.corporationId = corporationId;
	}

	public BusinessCardRelationBuildingListDTO getBusinessCard() {
		if (getBusinessCardId() == null) {
			return null;
		}

		Optional<IBusinessCard> businessCard = new IBusinessCardRepository().findById(getBusinessCardId());
		return businessCard.isPresent() ? new BusinessCardRelationBuildingListDTO(businessCard.get()) : null;
	}

	public ICorporationRelationBuildingListDTO getiCorporation() {
		if (getCorporationId() == null) {
			return null;
		}

		Optional<ICorporation> iCorporation = new ICorporationRepository().findById(getCorporationId());
		return iCorporation.isPresent() ? new ICorporationRelationBuildingListDTO(iCorporation.get()) : null;
	}

	@Override
	public Keyman createModel() {
		return new Keyman();
	}

}
