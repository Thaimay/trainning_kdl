package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Tenant;

public class AccountListDTO implements DTO<Account> {

	private Long id;
	private String fullName;

	@Override
	public Account createModel() {
		return new Account();
	}

	public AccountListDTO() {
		//
	}

	public AccountListDTO(Tenant tn) {
		this.copyProperties(this, tn);
	}

	public String getName() {
		return this.getFullName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
