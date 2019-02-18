package jp.co.world.storedevelopment.sp.controller.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.ProjectSchedule;

public class ProjectScheduleDTO {
	public ProjectScheduleDTO() {

	}

	public ProjectScheduleDTO(ProjectSchedule projectSchedule) {
		this.setActionStatusId(projectSchedule.getProjectActionStatus().getId());
		this.setScheduleDate(projectSchedule.getScheduleDate());
	}

	public Long getActionStatusId() {
		return actionStatusId;
	}

	public void setActionStatusId(Long actionStatusId) {
		this.actionStatusId = actionStatusId;
	}

	public LocalDate getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(LocalDate scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	private Long actionStatusId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate scheduleDate;

}
