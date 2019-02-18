package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;

import com.world.storedevelopment.utils.ZenkakuUtils;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.IBrandIncomeUnit;
import jp.co.world.storedevelopment.model.ICorporation;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBrandIncomeUnitRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectRepository;

public class NegotiationRelationProjectDTO {

	private long id = 0;
	private String name = "";

	public NegotiationRelationProjectDTO() {
		//
	}

	public NegotiationRelationProjectDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return ZenkakuUtils.toZenkaku(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProjectCorporation() throws JSONException {
		Project project = getProject();
		if (project != null && project.getBuildingId() != null) {
			Optional<Building> building = new BuildingRepository().findById(project.getBuildingId());
			if (building.isPresent() && building.get().getiCorporationId() != null) {
				Optional<ICorporation> corporation = new ICorporationRepository().findById(building.get().getiCorporationId());
				if(corporation.isPresent()) {				
					JSONObject obj = new JSONObject();
					obj.put("id", corporation.get().getId());
					obj.put("name", corporation.get().getCorporationName());
					return obj.toString();
				}
			}
		}
		return new JSONObject().toString();
	}
	
	public String getProjectBuilding() throws JSONException {
		Project project = getProject();
		if (project != null && project.getBuildingId() != null) {
			Optional<Building> building = new BuildingRepository().findById(project.getBuildingId());
			if (building.isPresent()) {
				JSONObject obj = new JSONObject();
				obj.put("id", building.get().getId());
				obj.put("name", building.get().getName());
				return obj.toString();
			}
		}
		return new JSONObject().toString();
	}

	public String getProjectBrand() throws JSONException {
		Project project = getProject();
		if (project != null && project.getBrandId() != null) {
			Optional<IBrandIncomeUnit> brand = new IBrandIncomeUnitRepository().findById(project.getBrandId());
			if (brand.isPresent()) {
				JSONObject obj = new JSONObject();
				obj.put("id", brand.get().getId());
				obj.put("name", brand.get().getBrandIncomeUnitName());
				return obj.toString();
			}
		}
		return new JSONObject().toString();
	}
	
	public String getProjectAccount() throws JSONException {
		Project project = getProject();
		if (project != null && !project.getCreatedAccountCode().isEmpty()) {
			Optional<Account> account = new AccountRepository().findByCode(project.getCreatedAccountCode());
			if (account.isPresent()) {
				JSONObject obj = new JSONObject();
				obj.put("id", account.get().getId());
				obj.put("name", account.get().getFullName());
				return obj.toString();
			}
		}
		return new JSONObject().toString();
	}

	private Project getProject() {
		Optional<Project> project = new ProjectRepository().findById(this.getId());
		if (project.isPresent()) {
			return project.get();
		}
		return null;
	}
}
