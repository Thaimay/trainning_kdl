package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.IShopCompany;

public class IShopCompanyRelationShopListDTO implements DTO<IShopCompany> {

	private Long id;
	private Long companyId;
	private String companyCd;
	private String companyName;

	public IShopCompanyRelationShopListDTO() {

	}

	public IShopCompanyRelationShopListDTO(IShopCompany iShopCompany) {
		this.copyProperties(this, iShopCompany);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyCd() {
		return companyCd;
	}

	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
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

}
