package jp.co.world.storedevelopment.sp.controller.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;

public class ProjectExclutionControlDTO {
	private String updatetimestamp;
	private String name;
	
	public ProjectExclutionControlDTO(Project project) {
		LocalDateTime time = project.getUpdateDatetime();
		Date date = Date.from(time.toInstant(ZoneId.systemDefault().getRules().getOffset(time)));
		Account a = new AccountRepository().findByCode(project.getUpdateAccountCode()).orElseGet(() -> {
			throw new IllegalStateException();
		});

		setUpdatetimestamp(String.valueOf(date.getTime()));
		setName(a.getFullName());
	}
	
	public String getUpdatetimestamp() {
		return updatetimestamp;
	}
	public void setUpdatetimestamp(String updatetimestamp) {
		this.updatetimestamp = updatetimestamp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
