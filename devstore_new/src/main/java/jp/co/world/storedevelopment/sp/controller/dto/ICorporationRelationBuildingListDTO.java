package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.Optional;

import jp.co.world.storedevelopment.model.ICorporation;
import jp.co.world.storedevelopment.model.ICorporationGroup;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationGroupRepository;

public class ICorporationRelationBuildingListDTO implements DTO<ICorporation> {

	private Long id;
	private String corporationCd;
	private String corporationName;
	private Long corporationGpId;

	public ICorporationRelationBuildingListDTO() {

	}

	public ICorporationRelationBuildingListDTO(ICorporation iCorporation) {
		copyProperties(this, iCorporation);
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

	public ICorporationGroupRelationBuildingListDTO getiCorporationGroup() {
		if (getCorporationGpId() == null) {
			return null;
		}

		Optional<ICorporationGroup> iCorporationGroup = new ICorporationGroupRepository()
				.findById(getCorporationGpId());
		return iCorporationGroup.isPresent() ? new ICorporationGroupRelationBuildingListDTO(iCorporationGroup.get())
				: null;
	}

	public String getName() {
		return getCorporationName();
	}

	@Override
	public ICorporation createModel() {
		return new ICorporation();
	}

}
