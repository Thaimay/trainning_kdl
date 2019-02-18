package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.List;

public class AreaLocalityDTO {

	private long id;
	private String name;
	List<PrefecturesLocalityDTO> listPrefectures;

	public AreaLocalityDTO() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PrefecturesLocalityDTO> getListPrefectures() {
		return listPrefectures;
	}

	public void setListPrefectures(List<PrefecturesLocalityDTO> listPrefectures) {
		this.listPrefectures = listPrefectures;
	}

}
