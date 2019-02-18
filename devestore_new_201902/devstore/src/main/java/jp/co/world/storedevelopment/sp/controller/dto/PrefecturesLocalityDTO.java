package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.List;

public class PrefecturesLocalityDTO {

	private long id;
	private String labelId;
	private String name;
	private List<BlockLocalityDTO> listBlock;

	public PrefecturesLocalityDTO() {
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

	public List<BlockLocalityDTO> getListBlock() {
		return listBlock;
	}

	public void setListBlock(List<BlockLocalityDTO> listBlock) {
		this.listBlock = listBlock;
	}

	public String getLabelId() {
		return labelId;
	}

	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}

}
