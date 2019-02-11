package jp.co.world.storedevelopment.sp.controller.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TopFindDTO {
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate deadlineDate;

	public LocalDate getDeadlineDate() {
		return deadlineDate;
	}

	public void setDeadlineDate(LocalDate deadlineDate) {
		this.deadlineDate = deadlineDate;
	}

}
