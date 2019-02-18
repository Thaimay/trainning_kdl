package jp.co.world.storedevelopment.sp.controller.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.world.storedevelopment.utils.ZenkakuUtils;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Shop;
import jp.co.world.storedevelopment.model.ShopHistory;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;

public class ShopHistoryListDTO implements DTO<Shop> {

	private Long id;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime updateDatetime;
	private String updateAccountCode;

	public ShopHistoryListDTO() {

	}

	public ShopHistoryListDTO(ShopHistory s) {
		this.copyProperties(this, s);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(LocalDateTime updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public String getUpdateAccountCode() {
		return updateAccountCode;
	}

	public void setUpdateAccountCode(String updateAccountCode) {
		this.updateAccountCode = updateAccountCode;
	}

	public String getUpdateAccountName() {
		Optional<Account> updateAccount = new AccountRepository().findByCode(this.getUpdateAccountCode());
		return updateAccount.isPresent() ? updateAccount.get().getFullName() : "";
	}

	public String getName() {
		return ZenkakuUtils.toZenkaku(this.getUpdateDatetime().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")) + " "
				+ this.getUpdateAccountName());
	}

	@Override
	public Shop createModel() {
		return new Shop();
	}

}
