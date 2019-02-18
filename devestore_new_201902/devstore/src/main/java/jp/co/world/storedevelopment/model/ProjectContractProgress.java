package jp.co.world.storedevelopment.model;

import java.time.LocalDate;

import org.apache.ibatis.session.SqlSession;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ProjectContractProgressModelMapper;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class ProjectContractProgress extends ActiveModel<ProjectContractProgress> {

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

	private String[] ignoreFields = new String[] { "term" };

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	public void setIgnoreFields(String[] ignoreFields) {
		this.ignoreFields = ignoreFields;
	}

	@Override
	protected ModelMapper<ProjectContractProgress> modelMapper(SqlSession session) {
		return session.getMapper(ProjectContractProgressModelMapper.class);
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

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getOperationForm() {
		return operationForm;
	}

	public void setOperationForm(String operationForm) {
		this.operationForm = operationForm;
	}

}
