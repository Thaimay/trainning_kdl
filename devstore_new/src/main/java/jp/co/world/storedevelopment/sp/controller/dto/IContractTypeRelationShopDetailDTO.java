package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.IContractType;

public class IContractTypeRelationShopDetailDTO implements DTO<IContractType> {

	private Long id;
	private Long contractTypeId;
	private String contractTypeCd;
	private String contractTypeName;

	public IContractTypeRelationShopDetailDTO() {

	}

	public IContractTypeRelationShopDetailDTO(IContractType iContractType) {
		this.copyProperties(this, iContractType);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getContractTypeId() {
		return contractTypeId;
	}

	public void setContractTypeId(Long contractTypeId) {
		this.contractTypeId = contractTypeId;
	}

	public String getContractTypeCd() {
		return contractTypeCd;
	}

	public void setContractTypeCd(String contractTypeCd) {
		this.contractTypeCd = contractTypeCd;
	}

	public String getContractTypeName() {
		return contractTypeName;
	}

	public void setContractTypeName(String contractTypeName) {
		this.contractTypeName = contractTypeName;
	}

	@Override
	public IContractType createModel() {
		return new IContractType();
	}

}
