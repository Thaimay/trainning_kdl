package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.Account;

public class RelatedAccountDTO implements DTO<Account> {

	private Long id;
	private String name;

	@Override
	public Account createModel() {
		return new Account();
	}

	public RelatedAccountDTO() {
		//
	}

	public RelatedAccountDTO(Account a) {
		this.setId(a.getId());
		this.setName(a.getFullName());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
