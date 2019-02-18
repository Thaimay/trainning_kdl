package jp.co.world.storedevelopment.sp.controller.dto;

import java.time.LocalDateTime;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Todo;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;

public class TodoListDTO extends TodoDetailDTOSupport {

	private CreateAccountDTO updateAccount = new CreateAccountDTO();

	private Long accountId = Long.MIN_VALUE;

	private String devisionCategory;

	private int detailsDevisionCategory;

	private int linkDestinationId;

	private String content;

	private String contentSub;

	private Boolean isOpened;

	private String createdAccountCode;

	private String updateAccountCode;

	private LocalDateTime showStartDatetime;

	private LocalDateTime showEndDatetime;

	private LocalDateTime deadlineDatetime;

	private Long projectId;
	
	private Long negotiationId;

	public TodoListDTO() {
		//
	}

	public TodoListDTO(Todo td) {
		setId(td.getId());
		Account a = new AccountRepository().findById(td.getAccountId())
				.orElseThrow(() -> new IllegalStateException("存在しないアカウントコードです:"));
		setAccountId(td.getAccountId());
		setNegotiationId(td.getNegotiationId());
		setProjectId(td.getProjectId());
		setUpdateAccount(new CreateAccountDTO(a, td));
		setContent(td.getContent());
		setContentSub(td.getContentSub());
		setCreatedAccountCode(td.getCreatedAccountCode());
		setUpdateAccountCode(td.getUpdateAccountCode());
		setShowStartDatetime(td.getShowStartDatetime());
		setShowEndDatetime(td.getShowEndDatetime());
		setDeadlineDatetime(td.getDeadlineDatetime());
	}

	public CreateAccountDTO getUpdateAccount() {
		return updateAccount;
	}

	private void setUpdateAccount(CreateAccountDTO createAccountDTO) {
		this.updateAccount = createAccountDTO;
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

	public String getContentSub() {
		return contentSub;
	}

	public void setContentSub(String contentSub) {
		this.contentSub = contentSub;
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

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getNegotiationId() {
		return negotiationId;
	}

	public void setNegotiationId(Long negotiationId) {
		this.negotiationId = negotiationId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

}
