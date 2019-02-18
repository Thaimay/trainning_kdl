package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.Optional;

import jp.co.world.storedevelopment.model.BuildingTenant;
import jp.co.world.storedevelopment.model.Tenant;
import jp.co.world.storedevelopment.model.mapper.repository.TenantRepository;

public class BuildingTenantRelationBuildingDetailDTO implements DTO<BuildingTenant> {

	private String buildingCd;
	private Long tenantId;

	@Override
	public BuildingTenant createModel() {
		return new BuildingTenant();
	}

	public BuildingTenantRelationBuildingDetailDTO() {

	}

	public BuildingTenantRelationBuildingDetailDTO(BuildingTenant buildingTenant) {
		copyProperties(this, buildingTenant);
	}

	public Long getId() {
		return getTenantId();
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

	public Tenant getTenant() {
		if (getTenantId() == null) {
			return null;
		}

		Optional<Tenant> tenant = new TenantRepository().findById(getTenantId());
		return tenant.isPresent() ? tenant.get() : null;
	}

}
