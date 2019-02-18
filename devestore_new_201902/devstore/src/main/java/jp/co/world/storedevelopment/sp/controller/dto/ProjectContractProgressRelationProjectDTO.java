package jp.co.world.storedevelopment.sp.controller.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.MPeriod;
import jp.co.world.storedevelopment.model.ProjectContractProgress;
import jp.co.world.storedevelopment.model.mapper.repository.MPeriodRepository;

public class ProjectContractProgressRelationProjectDTO implements DTO<ProjectContractProgress> {

	private Long projectId;
	private String form;
	private String category;
	private String contractTargetName;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate contractStartDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate contractEndDate;
	private Float contractNumberOfYear;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate rentStartDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate rentEndDate;
	private Float rentYear;
	private Boolean autoUpdate;
	private String memo;
	private String operationForm;

	public ProjectContractProgressRelationProjectDTO() {

	}

	public ProjectContractProgressRelationProjectDTO(ProjectContractProgress model) {
		this.copyProperties(this, model);
	}

	@Override
	public ProjectContractProgress createModel() {
		return new ProjectContractProgress();
	}

	public String contractNumberValue() {
		if (getContractNumberOfYear() == null) {
			return "";
		} else {
			return String.format("%.1f", getContractNumberOfYear());
		}
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getContractTargetName() {
		return contractTargetName;
	}

	public void setContractTargetName(String contractTargetName) {
		this.contractTargetName = contractTargetName;
	}

	public LocalDate getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(LocalDate contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public LocalDate getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(LocalDate contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public Float getContractNumberOfYear() {
		return contractNumberOfYear;
	}

	public void setContractNumberOfYear(Float contractNumberOfYear) {
		this.contractNumberOfYear = contractNumberOfYear;
	}

	public LocalDate getRentStartDate() {
		return rentStartDate;
	}

	public void setRentStartDate(LocalDate rentStartDate) {
		this.rentStartDate = rentStartDate;
	}

	public LocalDate getRentEndDate() {
		return rentEndDate;
	}

	public void setRentEndDate(LocalDate rentEndDate) {
		this.rentEndDate = rentEndDate;
	}

	public Float getRentYear() {
		return rentYear;
	}

	public void setRentYear(Float rentYear) {
		this.rentYear = rentYear;
	}

	public Boolean getAutoUpdate() {
		return autoUpdate;
	}

	public void setAutoUpdate(Boolean autoUpdate) {
		this.autoUpdate = autoUpdate;
	}

	public String getMemo() {
		return memo;
	}

	public String getOperationForm() {
		return operationForm;
	}

	public void setOperationForm(String operationForm) {
		this.operationForm = operationForm;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getTerm() {
		if (contractEndDate != null) {
			Optional<MPeriod> opt = new MPeriodRepository()
					.getPeriod(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(contractEndDate));
			if (opt.isPresent()) {
				if(contractEndDate.isBefore(opt.get().getSecondHalfStartDate())) {
					return opt.get().getPeriod() + "期 上期";
				} else {
					return opt.get().getPeriod() + "期 下期";
				}
			}
		}

		return StringUtils.EMPTY;
	}
}
