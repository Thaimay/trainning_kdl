package jp.co.world.storedevelopment.pc.controller.dto;

import com.world.storedevelopment.utils.HankakuUtils;
import com.world.storedevelopment.utils.ZenkakuUtils;

import jp.co.world.storedevelopment.Application;

public abstract class FindFormDTO {

	private int numberOfPage = 0;
	private int pagingSize = Application.PAGING_SIZE;
	private String keyWord = "";
	private Long accountId;

	public int getNumberOfPage() {
		return numberOfPage;
	}

	public void setNumberOfPage(int page) {
		this.numberOfPage = page;
	}

	public int getPagingSize() {
		return pagingSize;
	}

	public void setPagingSize(int pagingSize) {
		this.pagingSize = pagingSize;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getKeyWordHankaku() {
		return HankakuUtils.toHankaku(keyWord);
	}
	
	public String getKeyWordZenkaku() {
		return ZenkakuUtils.toZenkaku(keyWord);
	}
}
