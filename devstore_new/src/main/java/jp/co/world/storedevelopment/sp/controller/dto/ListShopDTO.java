package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.Optional;

import jp.co.world.storedevelopment.model.IShop;
import jp.co.world.storedevelopment.model.Shop;
import jp.co.world.storedevelopment.model.mapper.repository.IShopRepository;

public class ListShopDTO implements DTO<Shop> {

	private Long id;

	public ListShopDTO() {

	}

	public ListShopDTO(Shop shop) {
		this.copyProperties(this, shop);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Shop createModel() {
		return new Shop();
	}

	public String getName() {
		Optional<IShop> iShop = new IShopRepository().findById(this.getId());
		return iShop != null ? iShop.get().getShopNameZenkaku() : "";
	}

}
