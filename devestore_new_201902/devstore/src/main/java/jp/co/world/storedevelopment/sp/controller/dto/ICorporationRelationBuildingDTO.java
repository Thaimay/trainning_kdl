package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.Optional;

import jp.co.world.storedevelopment.model.ICorporation;
import jp.co.world.storedevelopment.model.ICorporationGroup;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationGroupRepository;

public class ICorporationRelationBuildingDTO implements DTO<ICorporation> {

	private Long id;
	private String corporationCd;
	private String corporationName;
	private Long corporationGpId;

	public ICorporationRelationBuildingDTO() {

	}

	public ICorporationRelationBuildingDTO(ICorporation iCorporation) {
		copyProperties(this, iCorporation);
	}

	@Override
	public ICorporation createModel() {
		return new ICorporation();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCorporationCd() {
		return corporationCd;
	}

	public void setCorporationCd(String corporationCd) {
		this.corporationCd = corporationCd;
	}

	public String getCorporationName() {
		return corporationName;
	}

	public void setCorporationName(String corporationName) {
		this.corporationName = corporationName;
	}

	public Long getCorporationGpId() {
		return corporationGpId;
	}

	public void setCorporationGpId(Long corporationGpId) {
		this.corporationGpId = corporationGpId;
	}

	public ICorporationGroupRelationICorporationDTO getCorporationGroup() {
		if (corporationGpId != null) {
			Optional<ICorporationGroup> iCorporationGroup = new ICorporationGroupRepository().findById(corporationGpId);
			if (iCorporationGroup.isPresent()) {
				return new ICorporationGroupRelationICorporationDTO(iCorporationGroup.get());
			}
		}
		return null;
	}

}
