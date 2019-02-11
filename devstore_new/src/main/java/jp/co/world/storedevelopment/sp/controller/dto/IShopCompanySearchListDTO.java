package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.List;

public class IShopCompanySearchListDTO {

	private long id;
	private String name;
	List<NegotiationRelationDTO> listBrand;

	public IShopCompanySearchListDTO() {
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

	public List<NegotiationRelationDTO> getListBrand() {
		return listBrand;
	}

	public void setListBrand(List<NegotiationRelationDTO> listBrand) {
		this.listBrand = listBrand;
	}

}
