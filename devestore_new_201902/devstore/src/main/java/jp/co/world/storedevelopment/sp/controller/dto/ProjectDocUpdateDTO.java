package jp.co.world.storedevelopment.sp.controller.dto;

public class ProjectDocUpdateDTO {
	private String searchInput = "";
	private int accountId = 0;
	private String keyWord = null;
	private String name = "";
	public ProjectDocUpdateDTO() {

	}
	public String getSearchInput() {
		return searchInput;
	}
	public void setSearchInput(String searchInput) {
		this.searchInput = searchInput;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
