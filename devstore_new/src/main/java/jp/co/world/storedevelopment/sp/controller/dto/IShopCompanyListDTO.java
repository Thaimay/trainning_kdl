package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.IShopCompany;

public class IShopCompanyListDTO implements DTO<IShopCompany> {

	private Long id;
	private String companyName;

	public IShopCompanyListDTO() {

	}

	public IShopCompanyListDTO(IShopCompany iShopCompany) {
		this.copyProperties(this, iShopCompany);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public IShopCompany createModel() {
		return new IShopCompany();
	}
	
	public String getName() {
		return this.getCompanyName();
	}

}
