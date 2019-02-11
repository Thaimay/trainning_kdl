package jp.co.world.storedevelopment.sp.controller.dto;

import java.time.LocalDate;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.MProjectActionStatus;
import jp.co.world.storedevelopment.model.ProjectSchedule;
import jp.co.world.storedevelopment.model.mapper.repository.MProjectActionStatusRepository;

public class ScheduleDateDTO {
	private String name;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate targetSchedule;
	private Long projectStatusId;
	private MProjectActionStatus projectActionStatus;
	private Boolean isChangedScheduleDate;

	public ScheduleDateDTO(ProjectSchedule schedule, MProjectActionStatus projectActionStatus) {
		setName(schedule.actionStatus().getName());
		setTargetSchedule(schedule.getScheduleDate());
		setProjectStatusId(schedule.getMProjectActionStatusId());
		setProjectActionStatus(projectActionStatus);
		setIsChangedScheduleDate(schedule.getIsChangedScheduleDate());
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

	public void setTargetSchedule(LocalDate targetSchedule) {
		this.targetSchedule = targetSchedule;
	}

	public Long getProjectStatusId() {
		return projectStatusId;
	}

	public void setProjectStatusId(Long projectStatusId) {
		this.projectStatusId = projectStatusId;
	}

	public MProjectActionStatus getProjectActionStatus() {
		return projectActionStatus;
	}

	public void setProjectActionStatus(MProjectActionStatus projectActionStatus) {
		this.projectActionStatus = projectActionStatus;
	}

	public Boolean getIsChangedScheduleDate() {
		return isChangedScheduleDate;
	}

	public void setIsChangedScheduleDate(Boolean isChangedScheduleDate) {
		this.isChangedScheduleDate = isChangedScheduleDate;
	}

	public boolean getIsPastDay() {
		boolean isPastDay = targetSchedule != null && targetSchedule.isBefore(LocalDate.now());
		if(getActionStatus() != null && projectActionStatus != null) {
			return getActionStatus().getSort() >= projectActionStatus.getSort() + 1 && isPastDay;
		}
		return isPastDay;
	}

	public MProjectActionStatus getActionStatus() {
		if (projectStatusId != null) {
			Optional<MProjectActionStatus> opt = new MProjectActionStatusRepository().findById(projectStatusId);
			if (opt.isPresent()) {
				return opt.get();
			}
		}
		return null;
	}
}
