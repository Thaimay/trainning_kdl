package jp.co.world.storedevelopment.sp.controller.dto;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.ProjectSectionProgress;

public class ActionStatusFormDTO {
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate implementationDatetime;
	private Long projectCategoryId;
	private Long iSalesChannelId;
	private Long officeProjectProgressId;
	private Long negotiationProjectProgressId;
	private Long mProjectActionStatusId;
	private Long articleReviewResultId;
	private Long managementResultId;
	private Long investmentProcessResultId;
	private Boolean investmentDiscussionTarget = false;
	private String operationDivision;
	private List<ProjectSectionProgress> projectSectionProgressDto = new ArrayList<>();

	public Project toModel() {
		try {
			Project model = new Project();
			PropertyUtils.copyProperties(model, this);
			return model;
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
			throw new IllegalStateException("オブジェクトのコピーで失敗しました");
		}
	}
	
	public ProjectSectionProgress progress() {
		if (getProjectSectionProgressDto().size() > 0) {
			return getProjectSectionProgressDto().get(0);
		} else {
			return new ProjectSectionProgress();
		}
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getImplementationDatetime() {
		return implementationDatetime;
	}
	public void setImplementationDatetime(LocalDate implementationDatetime) {
		this.implementationDatetime = implementationDatetime;
	}
	public Long getProjectCategoryId() {
		return projectCategoryId;
	}
	public void setProjectCategoryId(Long projectCategoryId) {
		this.projectCategoryId = projectCategoryId;
	}
	public Long getiSalesChannelId() {
		return iSalesChannelId;
	}
	public void setiSalesChannelId(Long iSalesChannelId) {
		this.iSalesChannelId = iSalesChannelId;
	}
	public Long getOfficeProjectProgressId() {
		return officeProjectProgressId;
	}
	public void setOfficeProjectProgressId(Long officeProjectProgressId) {
		this.officeProjectProgressId = officeProjectProgressId;
	}
	public Long getNegotiationProjectProgressId() {
		return negotiationProjectProgressId;
	}
	public void setNegotiationProjectProgressId(Long negotiationProjectProgressId) {
		this.negotiationProjectProgressId = negotiationProjectProgressId;
	}
	public Long getmProjectActionStatusId() {
		return mProjectActionStatusId;
	}
	public void setProjectActionStatusId(Long mProjectActionStatusId) {
		this.mProjectActionStatusId = mProjectActionStatusId;
	}
	public void setmProjectActionStatusId(Long mProjectActionStatusId) {
		this.mProjectActionStatusId = mProjectActionStatusId;
	}
	public Long getArticleReviewResultId() {
		return articleReviewResultId;
	}
	public void setArticleReviewResultId(Long articleReviewResultId) {
		this.articleReviewResultId = articleReviewResultId;
	}
	public Long getManagementResultId() {
		return managementResultId;
	}
	public void setManagementResultId(Long managementResultId) {
		this.managementResultId = managementResultId;
	}
	public Long getInvestmentProcessResultId() {
		return investmentProcessResultId;
	}
	public void setInvestmentProcessResultId(Long investmentProcessResultId) {
		this.investmentProcessResultId = investmentProcessResultId;
	}
	public Boolean getInvestmentDiscussionTarget() {
		return investmentDiscussionTarget;
	}
	public void setInvestmentDiscussionTarget(Boolean investmentDiscussionTarget) {
		this.investmentDiscussionTarget = investmentDiscussionTarget;
	}
	public String getOperationDivision() {
		return operationDivision;
	}
	public void setOperationDivision(String operationDivision) {
		this.operationDivision = operationDivision;
	}
	public List<ProjectSectionProgress> getProjectSectionProgressDto() {
		return projectSectionProgressDto;
	}
	public void setProjectSectionProgressDto(List<ProjectSectionProgress> projectSectionProgressDto) {
		this.projectSectionProgressDto = projectSectionProgressDto;
	}

}
