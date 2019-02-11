package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.ICorporation;
import jp.co.world.storedevelopment.model.ICorporationGroup;
import jp.co.world.storedevelopment.model.MCreditRank;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationGroupRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MCreditRankRepository;

public class ICorporationRelationBuildingDetailDTO implements DTO<ICorporation> {

	private Long id;
	private String corporationCd;
	private String corporationName;
	private Long corporationGpId;

	public ICorporationRelationBuildingDetailDTO() {

	}

	public ICorporationRelationBuildingDetailDTO(ICorporation iCorporation) {
		this.copyProperties(this, iCorporation);
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

	public ICorporationGroupRelationBuildingDetailDTO getiCorporationGroup() {
		ICorporationGroup iCorporationGroup = new ICorporationGroupRepository().findById(this.getCorporationGpId())
				.orElseThrow(() -> new IllegalStateException("存在しない企業グループＩＤです:" + this.getCorporationGpId()));
		return new ICorporationGroupRelationBuildingDetailDTO(iCorporationGroup);
	}

	public MCreditRankRelationBuildingDetailDTO getmCreditRank() {
		if (getCorporationCd() == null) {
			return null;
		}

		MCreditRank mCreditRank = new MCreditRankRepository().findByCorporationCd(getCorporationCd());
		return mCreditRank != null ? new MCreditRankRelationBuildingDetailDTO(mCreditRank) : null;
	}
	
	public String getName() {
		return getCorporationName();
	}

	@Override
	public ICorporation createModel() {
		return new ICorporation();
	}

}
