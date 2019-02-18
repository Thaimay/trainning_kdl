package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.Optional;

import jp.co.world.storedevelopment.model.ICorporation;
import jp.co.world.storedevelopment.model.PmCorporation;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;

public class PmCorporationRelationBuildingDetailDTO implements DTO<PmCorporation> {

	private Long id;
	private Long corporationId;

	public PmCorporationRelationBuildingDetailDTO() {

	}

	public PmCorporationRelationBuildingDetailDTO(PmCorporation pmCorporation) {
		copyProperties(this, pmCorporation);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(Long corporationId) {
		this.corporationId = corporationId;
	}

	public String getCorporationName() {
		if (getCorporationId() == null) {
			return null;
		}

		Optional<ICorporation> iCorporation = new ICorporationRepository().findById(getCorporationId());
		return iCorporation.isPresent() ? iCorporation.get().getCorporationName() : "";
	}

	public String getName() {
		return getCorporationName();
	}

	@Override
	public PmCorporation createModel() {
		return new PmCorporation();
	}

}
