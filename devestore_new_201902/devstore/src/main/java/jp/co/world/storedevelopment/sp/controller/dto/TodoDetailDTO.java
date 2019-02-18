package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Todo;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;

public class TodoDetailDTO extends TodoDetailDTOSupport {

	private CreateAccountDTO updateAccount = new CreateAccountDTO();
	
	private String devisionCategory;
	
	private int detailsDevisionCategory;
	
	private int linkDestinationId;
	
	private Boolean isOpened;
	
	private String content;
	
	private String contentSub;
	
	private String createdAccountCode;
	
	private String updateAccountCode;

	public TodoDetailDTO(Todo td) {
		Account account = new AccountRepository().findByCode(td.getUpdateAccountCode())
				.orElseThrow(() -> new IllegalStateException("存在しないアカウントコードです:"));
		setUpdateAccount(new CreateAccountDTO(account, td));
		setId(td.getId());
		setContent(td.getContent());
		setContentSub(td.getContentSub());
//		setDevisionCategory(td.getDevisionCategory());
//		setDetailsDevisionCategory(td.getDetailsDevisionCategory());
//		setLinkDestinationId(td.getLinkDestinationId());
//		setIsOpened(td.getIsOpened());
		setCreatedAccountCode(td.getCreatedAccountCode());
		setUpdateAccountCode(td.getUpdateAccountCode());
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

	public CreateAccountDTO getUpdateAccount() {
		return updateAccount;
	}

	public void setUpdateAccount(CreateAccountDTO account) {
		this.updateAccount = account;
	}

}
