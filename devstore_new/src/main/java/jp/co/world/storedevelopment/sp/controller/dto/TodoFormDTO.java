package jp.co.world.storedevelopment.sp.controller.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.Todo;

public abstract class TodoFormDTO implements DTO<Todo> {

	private Long accountId = Long.MIN_VALUE;
	private String devisionCategory;
	private int detailsDevisionCategory;
	private int linkDestinationId;
	private String content;
	private String content_sub;
	private Boolean isOpened;
	private String createdAccountCode;
	private String updateAccountCode;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime showStartDatetime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime showEndDatetime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime deadlineDatetime;

	@Override
	public Todo createModel() {
		return new Todo();
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDevisionCategory() {
		return devisionCategory;
	}

	public void setDevisionCategory(String devision_category) {
		this.devisionCategory = devision_category;
	}

	public String getContentSub() {
		return content_sub;
	}

	public void setContentSub(String content_sub) {
		this.content_sub = content_sub;
	}

	public int getDetailsDevisionCategory() {
		return detailsDevisionCategory;
	}

	public void setDetailsDevisionCategory(int detailsDevisionCategory) {
		this.detailsDevisionCategory = detailsDevisionCategory;
	}
	
	public String getCreatedAccountCode() {
		return createdAccountCode;
	}

	public void setCreatedAccountCode(String createdAccountCode) {
		this.createdAccountCode = createdAccountCode;
	}

	public String getUpdateAccountCode() {
		return updateAccountCode;
	}

	public void setUpdateAccountCode(String updateAccountCode) {
		this.updateAccountCode = updateAccountCode;
	}

	public Boolean getIsOpened() {
		return isOpened;
	}

	public void setIsOpened(Boolean isOpened) {
		this.isOpened = isOpened;
	}

	public int getLinkDestinationId() {
		return linkDestinationId;
	}

	public void setLinkDestinationId(int linkDestinationId) {
		this.linkDestinationId = linkDestinationId;
	}

	public LocalDateTime getShowStartDatetime() {
		return showStartDatetime;
	}

	public void setShowStartDatetime(LocalDateTime showStartDatetime) {
		this.showStartDatetime = showStartDatetime;
	}

	public LocalDateTime getShowEndDatetime() {
		return showEndDatetime;
	}

	public void setShowEndDatetime(LocalDateTime showEndDatetime) {
		this.showEndDatetime = showEndDatetime;
	}

	public LocalDateTime getDeadlineDatetime() {
		return deadlineDatetime;
	}

	public void setDeadlineDatetime(LocalDateTime deadlineDatetime) {
		this.deadlineDatetime = deadlineDatetime;
	}

}
