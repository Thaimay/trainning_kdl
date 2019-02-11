package jp.co.world.storedevelopment.pc.controller.form;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;

public class TopFindForm {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate deadline = LocalDate.now();

	public LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	public String getDate() {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(deadline);
	}

}
