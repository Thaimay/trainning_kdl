package jp.co.world.storedevelopment.sp.controller.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.Negotiation;

public abstract class NegotiationFormDTO implements DTO<Negotiation> {

	@Size(min = 0, max = 64)
	private String title = "";
	@NotNull
	private String division;
	@Size(min = 0, max = 10000)
	private String purpose = "";
	@Size(min = 0, max = 10000)
	private String content = "";
	@Size(min = 0, max = 64)
	private String place = "";
	@Size(min = 0, max = 10000)
	private String nextActionContent = "";
	@NotBlank
	private String releaseLevel = "";
	@NotNull
	private String businessCardFree = "";
	@NotNull
	private String interviewAccountFree = "";
	private Boolean release = false;
	private Integer priority = 0;
	private Boolean stop = false;
	private String companyCd = "";

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime scheduleStartDatetime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime scheduleEndDatetime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime implementationEndDatetime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime implementationStartDatetime;

	private Boolean noSendMail = false;

	@Override
	public Negotiation createModel() {
		return new Negotiation();
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNextActionContent() {
		return nextActionContent;
	}

	public void setNextActionContent(String nextActionContent) {
		this.nextActionContent = nextActionContent;
	}

	public Boolean getRelease() {
		return release;
	}

	public void setRelease(Boolean release) {
		this.release = release;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public LocalDateTime getScheduleEndDatetime() {
		return scheduleEndDatetime;
	}

	public void setScheduleEndDatetime(LocalDateTime scheduleEndDateTime) {
		scheduleEndDatetime = scheduleEndDateTime;
	}

	public LocalDateTime getImplementationEndDatetime() {
		return implementationEndDatetime;
	}

	public void setImplementationEndDatetime(LocalDateTime implementationEndDatetime) {
		this.implementationEndDatetime = implementationEndDatetime;
	}

	public LocalDateTime getImplementationStartDatetime() {
		return implementationStartDatetime;
	}

	public void setImplementationStartDatetime(LocalDateTime implementationStartDatetime) {
		this.implementationStartDatetime = implementationStartDatetime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDateTime getScheduleStartDatetime() {
		return scheduleStartDatetime;
	}

	public void setScheduleStartDatetime(LocalDateTime scheduleStartDateTime) {
		scheduleStartDatetime = scheduleStartDateTime;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Boolean getStop() {
		return stop;
	}

	public void setStop(Boolean stop) {
		this.stop = stop;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getReleaseLevel() {
		return releaseLevel;
	}

	public void setReleaseLevel(String releaseLevel) {
		this.releaseLevel = releaseLevel;
	}

	public Boolean getNoSendMail() {
		return noSendMail;
	}

	public void setNoSendMail(Boolean noSendMail) {
		this.noSendMail = noSendMail;
	}

	public String getBusinessCardFree() {
		return businessCardFree;
	}

	public void setBusinessCardFree(String businessCardFree) {
		this.businessCardFree = businessCardFree;
	}

	public String getInterviewAccountFree() {
		return interviewAccountFree;
	}

	public void setInterviewAccountFree(String interviewAccountFree) {
		this.interviewAccountFree = interviewAccountFree;
	}

	public String getCompanyCd() {
		return companyCd;
	}

	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}

}
