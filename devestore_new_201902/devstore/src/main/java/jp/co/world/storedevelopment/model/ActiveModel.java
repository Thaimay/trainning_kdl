package jp.co.world.storedevelopment.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

public abstract class ActiveModel<T> extends com.tierline.mybatis.activemodel.ActiveModel<T> {

	protected Long id;

	private String corporationGroup = "001";

	@Override
	public String createTableName() {
		return ActiveModelStringUtils.toSneke(this.getClass().getSimpleName());
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime createdDatetime = LocalDateTime.now();
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime updateDatetime = LocalDateTime.now();
	private String createdAccountCode = "";
	private String updateAccountCode = "";
	private Boolean isDeleted = false;

	@Override
	protected void setPrimaryKey(Object id) {
		setId((Long) id);
	}

	@Override
	protected String getPrimaryKey() {
		return getId().toString();
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
 		return id;
	}

	public LocalDateTime getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(LocalDateTime createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public LocalDateTime getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(LocalDateTime updateDatetime) {
		this.updateDatetime = updateDatetime;
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

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public void setCreateAccount(Account account) {
		setCreatedAccountCode(account.getEmployeeCd());
		setUpdateAccountCode(account.getEmployeeCd());
		setCreatedDatetime(LocalDateTime.now());
		setUpdateDatetime(LocalDateTime.now());
	}

	public void setUpdateAccount(Account account) {
		setUpdateAccountCode(account.getEmployeeCd());
		setUpdateDatetime(LocalDateTime.now());
	}

	@Override
	public T create() {
		if (emptyCratedStamp() || emptyUpdateStamp()) {
			throw new IllegalStateException("登録時のアカウントおよび時間が設定されてません");
		}
		return super.create();
	}

	@Override
	public Boolean update() {
		if (emptyUpdateStamp()) {
			throw new IllegalStateException("更新時のアカウントおよび時間が設定されてません");
		}
		return super.update();
	}

	private boolean emptyCratedStamp() {
		if (getCreatedAccountCode() == null || getCreatedDatetime() == null) {
			return true;
		}
		return false;
	}

	private boolean emptyUpdateStamp() {
		if (getUpdateAccountCode() == null || getUpdateDatetime() == null) {
			return true;
		}
		return false;
	}

	public String getCorporationGroup() {
		return corporationGroup;
	}

	public void setCorporationGroup(String corporationGroup) {
		this.corporationGroup = corporationGroup;
	}

}
