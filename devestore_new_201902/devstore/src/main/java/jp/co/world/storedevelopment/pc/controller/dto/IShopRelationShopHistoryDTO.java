package jp.co.world.storedevelopment.pc.controller.dto;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.IShop;
import jp.co.world.storedevelopment.sp.controller.dto.IShopRelationShopDetailDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ShopDetailDTO;

public class IShopRelationShopHistoryDTO extends IShopRelationShopDetailDTO {

	public IShopRelationShopHistoryDTO() {
		super();
	}

	public IShopRelationShopHistoryDTO(IShop iShop, Account account) {
		super(iShop, account);
	}

	public ShopDetailDTO getShop() {
		return null;
	}

}
