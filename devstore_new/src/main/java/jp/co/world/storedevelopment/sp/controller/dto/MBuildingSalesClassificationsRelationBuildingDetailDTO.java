package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.MBuildingSalesClassifications;

public class MBuildingSalesClassificationsRelationBuildingDetailDTO implements DTO<MBuildingSalesClassifications> {

	private Long id;
	private String value;

	public MBuildingSalesClassificationsRelationBuildingDetailDTO() {

	}

	public MBuildingSalesClassificationsRelationBuildingDetailDTO(
			MBuildingSalesClassifications mBuildingSalesClassifications) {
		this.copyProperties(this, mBuildingSalesClassifications);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public MBuildingSalesClassifications createModel() {
		return new MBuildingSalesClassifications();
	}

}
