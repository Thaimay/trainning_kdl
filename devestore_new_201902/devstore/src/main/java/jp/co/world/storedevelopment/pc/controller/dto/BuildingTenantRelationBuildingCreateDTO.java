package jp.co.world.storedevelopment.pc.controller.dto;

import jp.co.world.storedevelopment.model.BuildingTenant;
import jp.co.world.storedevelopment.model.Tenant;

public class BuildingTenantRelationBuildingCreateDTO implements DTO<BuildingTenant> {

	private Long id;
	private String buildingCd;
	private Long tenantId;
	private Boolean isDeleted = false;
	private Tenant tenant;

	@Override
	public BuildingTenant createModel() {
		return new BuildingTenant();
	}

	public BuildingTenantRelationBuildingCreateDTO() {

	}

	public BuildingTenantRelationBuildingCreateDTO(BuildingTenant buildingTenant) {
		this.copyProperties(this, buildingTenant);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBuildingCd() {
		return buildingCd;
	}

	public void setBuildingCd(String buildingCd) {
		this.buildingCd = buildingCd;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

}
