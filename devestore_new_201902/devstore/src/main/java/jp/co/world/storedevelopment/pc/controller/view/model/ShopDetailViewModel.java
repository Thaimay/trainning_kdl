package jp.co.world.storedevelopment.pc.controller.view.model;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.IShop;
import jp.co.world.storedevelopment.sp.controller.dto.IShopRelationShopDetailDTO;

public class ShopDetailViewModel extends IShopRelationShopDetailDTO {

	public ShopDetailViewModel(IShop is, Account account) {
		super(is, account);
	}
}
