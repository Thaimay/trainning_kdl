package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.List;

public class LocalityDTO {

	List<AreaLocalityDTO> listArea;

	public LocalityDTO() {

	}

	public List<AreaLocalityDTO> getListArea() {
		return listArea;
	}

	public void setListArea(List<AreaLocalityDTO> listArea) {
		this.listArea = listArea;
	}

}
