package jp.co.world.storedevelopment.pc.controller.view.model;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.IShop;
import jp.co.world.storedevelopment.model.ShopHistory;
import jp.co.world.storedevelopment.model.mapper.repository.IShopRepository;
import jp.co.world.storedevelopment.pc.controller.dto.IShopRelationShopHistoryDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ShopDetailDTO;

public class ShopHistoryViewModel extends ShopDetailDTO {

	private Long shopId;
	private Account account;

	public ShopHistoryViewModel(ShopHistory shopHistory, Account account) {
		super(shopHistory.toShop());
		this.setShopId(shopHistory.getShopId());
		setAccount(account);
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getHistoryName() {
		if (getUpdateDatetime() != null && getUpdateAccountName() != null) {
			return getUpdateDatetime().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")) + " "
					+ getUpdateAccountName();
		} else {
			return "";
		}
	}

	public IShopRelationShopHistoryDTO getiShop() {
		if (getiShopId() == null) {
			return null;
		}

		Optional<IShop> iShop = new IShopRepository().findById(getiShopId());
		return iShop.isPresent() ? new IShopRelationShopHistoryDTO(iShop.get(), account) : null;
	}
}
