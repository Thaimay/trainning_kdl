package jp.co.world.storedevelopment.model;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.MProjectActionStatusModelMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class MProjectActionStatus extends ActiveModel<MProjectActionStatus> {

	private Long projectCategoryId;
	private Long salesChannelId;
	private String name;
	private Integer sort;
	private String companyStatusCode;
	private String negotiationStatusCode;
	private String otherStatusCode;
	private Boolean scheduleUse;
	private String schedule;
	
	public Boolean sameCompany(Optional<MProjectProgressStatus> status) {
		if (status.isPresent()) {
			return sameStatus(status.get(), companyProgressStatus());
		} else {
			return false;
		}
	}
	
	public Boolean sameNegotiation(Optional<MProjectProgressStatus> status) {
		if (status.isPresent()) {
			return sameStatus(status.get(), negotiationProgressStatus());
		} else {
			return false;
		}		
	}

	public Boolean sameOther(Optional<MProjectProgressStatus> status) {
		if (status.isPresent()) {
			return sameStatus(status.get(), otherProgressStatus());
		} else {
			return false;
		}		
	}

	private Boolean sameStatus(MProjectProgressStatus status, Optional<MProjectProgressStatus> myStatus) {
		if (myStatus.isPresent()) {
			return status.getPriority() >= myStatus.get().getPriority();
		} else {
			return false;
		}
	}
	
	public Optional<MProjectProgressStatus> companyProgressStatus() {
		return MProjectProgressStatus.companyStatus(getProjectCategoryId(), getCompanyStatusCode());
	}
	
	public Optional<MProjectProgressStatus> negotiationProgressStatus() {
		return MProjectProgressStatus.negotiationStatus(getProjectCategoryId(), getNegotiationStatusCode());
	}
	
	public Optional<MProjectProgressStatus> otherProgressStatus() {
		return MProjectProgressStatus.otherStatus(getProjectCategoryId(), getOtherStatusCode());
	}
	
	public Long getProjectCategoryId() {
		return projectCategoryId;
	}

	public void setProjectCategoryId(Long projectCategoryId) {
		this.projectCategoryId = projectCategoryId;
	}

	public Long getSalesChannelId() {
		return salesChannelId;
	}

	public void setSalesChannelId(Long salesChannelId) {
		this.salesChannelId = salesChannelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getCompanyStatusCode() {
		return companyStatusCode;
	}

	public void setCompanyStatusCode(String companyStatusCode) {
		this.companyStatusCode = companyStatusCode;
	}

	public String getNegotiationStatusCode() {
		return negotiationStatusCode;
	}

	public void setNegotiationStatusCode(String negotiationStatusCode) {
		this.negotiationStatusCode = negotiationStatusCode;
	}

	public String getOtherStatusCode() {
		return otherStatusCode;
	}

	public void setOtherStatusCode(String otherStatusCode) {
		this.otherStatusCode = otherStatusCode;
	}

	public Boolean getScheduleUse() {
		return scheduleUse;
	}

	public void setScheduleUse(Boolean scheduleUse) {
		this.scheduleUse = scheduleUse;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	@Override
	protected ModelMapper<MProjectActionStatus> modelMapper(SqlSession session) {
		return session.getMapper(MProjectActionStatusModelMapper.class);
	}

}
