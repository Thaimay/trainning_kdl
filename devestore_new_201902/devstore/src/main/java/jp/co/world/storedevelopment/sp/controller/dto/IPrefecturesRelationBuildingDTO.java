package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.IPrefectures;

public class IPrefecturesRelationBuildingDTO implements DTO<IPrefectures> {

	private Long id;
	private String prefecturesName;

	public IPrefecturesRelationBuildingDTO() {

	}

	public IPrefecturesRelationBuildingDTO(IPrefectures iPrefectures) {
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

	@Override
	public IPrefectures createModel() {
		return new IPrefectures();
	}

}
