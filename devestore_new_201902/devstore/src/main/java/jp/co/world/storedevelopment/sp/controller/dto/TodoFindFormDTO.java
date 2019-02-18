package jp.co.world.storedevelopment.sp.controller.dto;

import java.time.LocalDateTime;

import jp.co.world.storedevelopment.model.Account;

public class TodoFindFormDTO extends TodoFormDTO {
	
	private Account account;
	
	private int numberOfPage;
	
	private String devisionCategory = "";
	
	private int detailsDevisionCategory = 0;
	
	private int linkDestinationId = 0;
	
	private String fillText = "";
	
	private String content = "";
	
	private String contentSub = "";
	
	private Boolean isOpened = false;
	
	private String createdAccountCode = "";
	
	private String updateAccountCode = "";
	
	private LocalDateTime showStartDatetime;
	
	private LocalDateTime showEndDatetime;
	
	private LocalDateTime deadlineDatetime;
	

	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public int getNumberOfPage() {
		return numberOfPage;
	}

	public void setNumberOfPage(int page) {
		this.numberOfPage = page;
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

	public void setDevisionCategory(String devisionCategory) {
		this.devisionCategory = devisionCategory;
	}

	public int getDetailsDevisionCategory() {
		return detailsDevisionCategory;
	}

	public void setDetailsDevisionCategory(int detailsDevisionCategory) {
		this.detailsDevisionCategory = detailsDevisionCategory;
	}

	public int getLinkDestinationId() {
		return linkDestinationId;
	}

	public void setLinkDestinationId(int linkDestinationId) {
		this.linkDestinationId = linkDestinationId;
	}

	public String getContent_sub() {
		return contentSub;
	}

	public void setContent_sub(String content_sub) {
		this.contentSub = content_sub;
	}

	public Boolean getIsOpened() {
		return isOpened;
	}

	public void setIsOpened(Boolean isOpened) {
		this.isOpened = isOpened;
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

	public String getFillText() {
		return fillText;
	}

	public void setFillText(String fillText) {
		this.fillText = fillText;
	}
}
