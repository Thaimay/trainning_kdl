package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.ImportantInformation;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.Todo;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;

public class CreateAccountDTO {

	private Long id;

	private String code;

	private String name;

	private String updateDatetime;

	public CreateAccountDTO() {
		//
	}

	public CreateAccountDTO(Account a) {
	    setId(a.getId());
	    setCode(a.getEmployeeCd());
	    setName(a.getFullName());
	}
	
	public CreateAccountDTO(Account a, Negotiation n) {
	    this(a);
		setUpdateDatetime(n.getUpdateDatetimeValue());
	}
	
	public CreateAccountDTO(Account a, Todo mi) {
		setId(a.getId());
		setCode(a.getEmployeeCd());
		setName(a.getFullName());
	}
	
	public CreateAccountDTO(Todo mi) {
		Account account = new AccountRepository().findByCode(mi.getUpdateAccountCode())
				.orElseThrow(() -> new IllegalStateException("存在しないアカウントコードです:"));
		setId(account.getId());
	}

	public CreateAccountDTO(Account a, ImportantInformation in) {
	    setId(a.getId());
	    setCode(a.getEmployeeCd());
	    setName(a.getFullName());
	    setUpdateDatetime(in.getUpdateDatetimeValue());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(String updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

}
