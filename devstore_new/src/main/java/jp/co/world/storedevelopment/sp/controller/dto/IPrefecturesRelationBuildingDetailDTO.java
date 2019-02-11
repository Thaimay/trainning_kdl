package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.IPrefectures;

public class IPrefecturesRelationBuildingDetailDTO implements DTO<IPrefectures> {

	private Long id;
	private String prefecturesName;

	public IPrefecturesRelationBuildingDetailDTO() {

	}

	public IPrefecturesRelationBuildingDetailDTO(IPrefectures iPrefectures) {
		this.copyProperties(this, iPrefectures);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrefecturesName() {
		return prefecturesName;
	}

	public void setPrefecturesName(String prefecturesName) {
		this.prefecturesName = prefecturesName;
	}

	public String getName() {
		return this.getPrefecturesName();
	}

	@Override
	public IPrefectures createModel() {
		return new IPrefectures();
	}

}
