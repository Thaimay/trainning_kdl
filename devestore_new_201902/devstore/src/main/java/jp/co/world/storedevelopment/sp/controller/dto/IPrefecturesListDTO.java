package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.IPrefectures;

public class IPrefecturesListDTO implements DTO<IPrefectures> {

	private Long id;
	private String prefecturesName;

	@Override
	public IPrefectures createModel() {
		return new IPrefectures();
	}

	public IPrefecturesListDTO() {
		//
	}

	public IPrefecturesListDTO(IPrefectures ip) {
		this.copyProperties(this, ip);
	}

	public String getPrefecturesName() {
		return prefecturesName;
	}

	public void setPrefecturesName(String prefecturesName) {
		this.prefecturesName = prefecturesName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.getPrefecturesName();
	}
}
