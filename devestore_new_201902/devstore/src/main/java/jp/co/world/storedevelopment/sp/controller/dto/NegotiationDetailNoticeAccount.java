package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.NegotiationNoticeAccount;

public class NegotiationDetailNoticeAccount {

	private String name = "";

	private boolean isRead = false;
	
	private boolean isUser = false;

	private String sendType = "TO";

	public NegotiationDetailNoticeAccount() {
		//
	}

	public NegotiationDetailNoticeAccount(String name, boolean isRead, String sendType, boolean isUser) {
		this.name = name;
		this.isRead = isRead;
		this.sendType = sendType;
		this.isUser = isUser;
	}

	public NegotiationDetailNoticeAccount(NegotiationNoticeAccount na, boolean isRead, boolean isUser) {
		this(na.getAccountName(), isRead, na.getSendType(), isUser);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(boolean isRead) {
		this.isRead = isRead;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	
	public boolean getIsUser() {
		return isUser;
	}

	public void setIsUser(boolean isUser) {
		this.isUser = isUser;
	}

	public String getReadStatus() {
		if (!isUser) {
			return "--";
		}

		if (isRead) {
			return "既読";
		} else {
			return "未読";
		}
	}
}
