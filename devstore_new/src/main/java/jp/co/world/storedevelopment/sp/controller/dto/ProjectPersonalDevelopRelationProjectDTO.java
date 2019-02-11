package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.Optional;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.ProjectPersonalDevelop;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;

public class ProjectPersonalDevelopRelationProjectDTO implements DTO<ProjectPersonalDevelop> {

	private Long id;
	private Long accountId;
	private String category;

	public ProjectPersonalDevelopRelationProjectDTO() {

	}

	public ProjectPersonalDevelopRelationProjectDTO(ProjectPersonalDevelop projectPersonalDevelop) {
		copyProperties(this, projectPersonalDevelop);
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

	public String getName() {
		return getAccountName();
	}

	@Override
	public ProjectPersonalDevelop createModel() {
		return new ProjectPersonalDevelop();
	}

}
