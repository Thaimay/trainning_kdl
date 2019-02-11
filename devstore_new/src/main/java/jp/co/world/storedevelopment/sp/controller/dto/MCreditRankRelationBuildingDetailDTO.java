package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.MCreditRank;

public class MCreditRankRelationBuildingDetailDTO implements DTO<MCreditRank> {

	private Long id;
	private String creditRank;

	@Override
	public MCreditRank createModel() {
		return new MCreditRank();
	}

	public MCreditRankRelationBuildingDetailDTO() {

	}

	public MCreditRankRelationBuildingDetailDTO(MCreditRank mCreditRank) {
		this.copyProperties(this, mCreditRank);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreditRank() {
		return creditRank;
	}

	public void setCreditRank(String creditRank) {
		this.creditRank = creditRank;
	}
	
	public String getName() {
		return this.getCreditRank().trim();
	}

}
