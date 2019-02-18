package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.Optional;

import jp.co.world.storedevelopment.model.ISalesAgencyTarget;
import jp.co.world.storedevelopment.model.ParticipatingStoreCorporation;
import jp.co.world.storedevelopment.model.Shop;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesAgencyTargetRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ParticipatingStoreCorporationRepository;

public class ShopRelationIShopDTO implements DTO<Shop> {

	private Long id;
	private Long iShopId;
	private String section;
	private String frontage;
	private Long iSalesAgencyTargetId;
	private Long participatingStoreCorporationId;
	private Integer buildingExpectedValue;
	private String remarks;

	public ShopRelationIShopDTO() {

	}

	public ShopRelationIShopDTO(Shop shop) {
		copyProperties(this, shop);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getiShopId() {
		return iShopId;
	}

	public void setiShopId(Long iShopId) {
		this.iShopId = iShopId;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getFrontage() {
		return frontage;
	}

	public void setFrontage(String frontage) {
		this.frontage = frontage;
	}

	public Long getiSalesAgencyTargetId() {
		return iSalesAgencyTargetId;
	}

	public void setiSalesAgencyTargetId(Long iSalesAgencyTargetId) {
		this.iSalesAgencyTargetId = iSalesAgencyTargetId;
	}

	public Long getParticipatingStoreCorporationId() {
		return participatingStoreCorporationId;
	}

	public void setParticipatingStoreCorporationId(Long participatingStoreCorporationId) {
		this.participatingStoreCorporationId = participatingStoreCorporationId;
	}

	public Integer getBuildingExpectedValue() {
		return buildingExpectedValue;
	}

	public void setBuildingExpectedValue(Integer buildingExpectedValue) {
		this.buildingExpectedValue = buildingExpectedValue;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public ISalesAgencyTargetRelationShopDetailDTO getiSalesAgencyTarget() {
		if (iSalesAgencyTargetId != null) {
			Optional<ISalesAgencyTarget> iSalesAgencyTarget = new ISalesAgencyTargetRepository()
					.findById(iSalesAgencyTargetId);
			if (iSalesAgencyTarget.isPresent()) {
				return new ISalesAgencyTargetRelationShopDetailDTO(iSalesAgencyTarget.get());
			}
		}
		return null;
	}

	public ParticipatingStoreCorporationRelationShopDetailDTO getParticipatingStoreCorporation() {
		if (participatingStoreCorporationId != null) {
			Optional<ParticipatingStoreCorporation> participatingStoreCorporation = new ParticipatingStoreCorporationRepository()
					.findById(participatingStoreCorporationId);
			if (participatingStoreCorporation.isPresent()) {
				return new ParticipatingStoreCorporationRelationShopDetailDTO(participatingStoreCorporation.get());
			}
		}
		return null;
	}

	@Override
	public Shop createModel() {
		return new Shop();
	}

}
