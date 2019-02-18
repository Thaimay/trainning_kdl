package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.IBrand;

public class IBrandRelationProjectDTO implements DTO<IBrand> {

	private Long id;
	private String brandCd;
	private String brandName;

	public IBrandRelationProjectDTO() {

	}

	public IBrandRelationProjectDTO(IBrand iBrand) {
		this.copyProperties(this, iBrand);
	}

	@Override
	public IBrand createModel() {
		return new IBrand();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrandCd() {
		return brandCd;
	}

	public void setBrandCd(String brandCd) {
		this.brandCd = brandCd;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getName() {
		return brandName;
	}
}
