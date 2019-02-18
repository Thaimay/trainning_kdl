package jp.co.world.storedevelopment.sp.controller.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.Account;

public class TodoFindByDeadlineFormDTO extends TodoFormDTO {
	private Account account;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate selectDate;

	private static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");

	public String getScheduleStartDateValue() {
		return DATE_FORMAT.format(selectDate);
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public LocalDate getSelectDate() {
		return selectDate;
	}

	public void setSelectDate(LocalDate selectDate) {
		this.selectDate = selectDate;
	}

}
