package jp.co.world.storedevelopment.sp.controller.dto;

import com.world.storedevelopment.utils.ZenkakuUtils;

import jp.co.world.storedevelopment.model.ICorporation;

public class ICorporationListDTO implements DTO<ICorporation> {

	private Long id;
	private String corporationCd;
	private String corporationName;
	private Long corporationGpId;

	@Override
	public ICorporation createModel() {
		return new ICorporation();
	}

	public ICorporationListDTO() {
		//
	}

	public ICorporationListDTO(ICorporation ic) {
		this.copyProperties(this, ic);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCorporationName() {
		return corporationName;
	}

	public void setCorporationName(String corporationName) {
		this.corporationName = corporationName;
	}

	public Long getCorporationGpId() {
		return corporationGpId;
	}

	public void setCorporationGpId(Long corporationGpId) {
		this.corporationGpId = corporationGpId;
	}

	public String getCorporationCd() {
		return corporationCd;
	}

	public void setCorporationCd(String corporationCd) {
		this.corporationCd = corporationCd;
	}
	
	public String getName() {
		return ZenkakuUtils.toZenkaku(corporationName);
	}

}
