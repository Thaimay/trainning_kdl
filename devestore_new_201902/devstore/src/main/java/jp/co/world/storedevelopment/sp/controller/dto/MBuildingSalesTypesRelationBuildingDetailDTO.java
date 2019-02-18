package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.MBuildingSalesTypes;

public class MBuildingSalesTypesRelationBuildingDetailDTO implements DTO<MBuildingSalesTypes> {

	private Long id;
	private String value;

	public MBuildingSalesTypesRelationBuildingDetailDTO() {

	}

	public MBuildingSalesTypesRelationBuildingDetailDTO(MBuildingSalesTypes mBuildingSalesTypes) {
		this.copyProperties(this, mBuildingSalesTypes);
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
	public MBuildingSalesTypes createModel() {
		return new MBuildingSalesTypes();
	}

}
