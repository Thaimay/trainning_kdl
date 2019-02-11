package jp.co.world.storedevelopment.model;

public abstract class IActiveModel<T> extends ActiveModel<T> {

	private String createdAccountCode = "batch";
	private String updateAccountCode = "batch";

	@Override
	public String getCreatedAccountCode() {
		return createdAccountCode;
	}

	@Override
	public void setCreatedAccountCode(String createdAccountCode) {
		this.createdAccountCode = createdAccountCode;
	}

	@Override
	public String getUpdateAccountCode() {
		return updateAccountCode;
	}

	@Override
	public void setUpdateAccountCode(String updateAccountCode) {
		this.updateAccountCode = updateAccountCode;
	}

}
