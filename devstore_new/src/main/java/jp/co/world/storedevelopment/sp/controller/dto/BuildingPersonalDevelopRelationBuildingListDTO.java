package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.Optional;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.BuildingPersonalDevelop;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;

public class BuildingPersonalDevelopRelationBuildingListDTO implements DTO<BuildingPersonalDevelop> {

	private Long id;
	private Long accountId;
	private String category;

	public BuildingPersonalDevelopRelationBuildingListDTO() {

	}

	public BuildingPersonalDevelopRelationBuildingListDTO(BuildingPersonalDevelop buildingPersonalDevelop) {
		copyProperties(this, buildingPersonalDevelop);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAccountName() {
		if (getAccountId() == null) {
			return "";
		}
		
		Optional<Account> account = new AccountRepository().findById(getAccountId());
		return account.isPresent() ? account.get().getFullName() : "";
	}

	@Override
	public BuildingPersonalDevelop createModel() {
		return new BuildingPersonalDevelop();
	}

}
