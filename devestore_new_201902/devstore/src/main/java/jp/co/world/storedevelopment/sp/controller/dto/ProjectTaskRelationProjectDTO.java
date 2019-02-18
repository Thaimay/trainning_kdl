package jp.co.world.storedevelopment.sp.controller.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.ProjectTask;
import jp.co.world.storedevelopment.model.ProjectTaskAccount;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectTaskAccountRepository;

public class ProjectTaskRelationProjectDTO implements DTO<ProjectTask> {

	private Long id;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate period;
	@JsonFormat(pattern = "HH:mm")
	private LocalTime periodTime;
	private String important;
	private String comment;
	private Boolean complete;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime createdDatetime;
	private String createdAccountCode;
	private Boolean isDeleted;

	public ProjectTaskRelationProjectDTO() {

	}

	public ProjectTaskRelationProjectDTO(ProjectTask projectTask) {
		this.copyProperties(this, projectTask);
	}

	@Override
	public ProjectTask createModel() {
		return new ProjectTask();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getPeriod() {
		return period;
	}

	public void setPeriod(LocalDate period) {
		this.period = period;
	}

	public LocalTime getPeriodTime() {
		return periodTime;
	}

	public void setPeriodTime(LocalTime periodTime) {
		this.periodTime = periodTime;
	}

	public String getImportant() {
		return important;
	}

	public void setImportant(String important) {
		this.important = important;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Boolean getComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

	public LocalDateTime getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(LocalDateTime createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public String getCreatedAccountCode() {
		return createdAccountCode;
	}

	public void setCreatedAccountCode(String createdAccountCode) {
		this.createdAccountCode = createdAccountCode;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getCreatedAccountName() {
		if (!createdAccountCode.isEmpty()) {
			Optional<Account> createdAccount = new AccountRepository().findByCode(createdAccountCode);
			if (createdAccount.isPresent()) {
				return createdAccount.get().getFullName();
			}
		}
		return StringUtils.EMPTY;
	}

	public String getContactAccountName() {
		return String.join("／", getAccount().stream().map(x -> x.getName()).collect(Collectors.toList()));
	}

	public List<RelatedAccountDTO> getAccount() {
		List<RelatedAccountDTO> dtos = new ArrayList<>();
		if (id != null) {
			List<ProjectTaskAccount> list = new ProjectTaskAccountRepository().findByProjectTaskId(id);
			list.forEach(x -> {
				Optional<Account> account = new AccountRepository().findById(x.getAccountId());
				if (account.isPresent()) {
					dtos.add(new RelatedAccountDTO(account.get()));
				}
			});
		}
		return dtos;
	}

	public String getPeriodString() {
		if (period != null) {
			return period.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		}
		return StringUtils.EMPTY;
	}
	
	public String getPeriodTimeString() {
		if (periodTime != null) {
			return periodTime.format(DateTimeFormatter.ofPattern("HH:mm"));
		}
		return StringUtils.EMPTY;
	}

	public String getCreatedDatetimeString() {
		if (createdDatetime != null) {
			return createdDatetime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
		}
		return StringUtils.EMPTY;
	}

}
