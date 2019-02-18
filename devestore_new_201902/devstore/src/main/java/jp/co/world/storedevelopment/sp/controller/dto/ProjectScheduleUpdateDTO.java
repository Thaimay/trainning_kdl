package jp.co.world.storedevelopment.sp.controller.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.MProjectActionStatus;
import jp.co.world.storedevelopment.model.ProjectSchedule;

public class ProjectScheduleUpdateDTO implements DTO<ProjectSchedule> {

	private Long id;
	private String name;
	private Long projectStatusId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate targetSchedule;

	@Override
	public ProjectSchedule createModel() {
		return new ProjectSchedule();
	}

	public ProjectScheduleUpdateDTO() {
		//
	}

	public ProjectScheduleUpdateDTO(ProjectSchedule projectSchedule, MProjectActionStatus mProjectActionStatus) {
		setId(projectSchedule.getId());
		setName(mProjectActionStatus.getName());
		setProjectStatusId(mProjectActionStatus.getId());
		setTargetSchedule(projectSchedule.getScheduleDate());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProjectStatusId() {
		return projectStatusId;
	}

	public void setProjectStatusId(Long projectStatusId) {
		this.projectStatusId = projectStatusId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getTargetSchedule() {
		return targetSchedule;
	}

	public void setTargetSchedule(LocalDate scheduleDate) {
		this.targetSchedule = scheduleDate;
	}

}
