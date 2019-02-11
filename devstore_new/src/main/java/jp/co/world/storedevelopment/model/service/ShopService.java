package jp.co.world.storedevelopment.model.service;

import java.util.List;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.IShop;
import jp.co.world.storedevelopment.model.Shop;
import jp.co.world.storedevelopment.model.ShopFile;
import jp.co.world.storedevelopment.model.ShopImage;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ShopFileRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ShopImageRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ShopRepository;
import jp.co.world.storedevelopment.sp.controller.dto.ShopUpdateDTO;

public class ShopService {

	public void updateAll(ShopUpdateDTO dto, Account a) {
		Shop s = dto.toModel();

		if (s.getId() != null && s.getId() > 0) {
			updateShopHistory(s.getId());
			updateShop(s, a);
		} else {
			createShop(s, a);
		}

		updateAllRelatedFile(s, dto.getShopFileDto(), a);
		updateAllRelatedImage(s, dto.getShopImageDto(), a);
		
		IShop ishop = s.iShop();
		new ProjectRepository().findByShop(ishop).forEach(p -> {
			p.copyShop(ishop);
		});
		
		
	}

	private void createShop(Shop s, Account a) {
		s.setCreateAccount(a);
		s.create();

		IShop ishop = s.iShop();
		new ProjectRepository().findByShop(ishop).forEach(p -> {
			p.copyShop(ishop);
		});
	}

	private void updateShop(Shop s, Account a) {
		s.setIgnoreFields(new String[] { "iShopId", "createdDatetime", "createdAccountCode" });
		s.setUpdateAccount(a);
		s.update();
		
		IShop ishop = s.iShop();
		new ProjectRepository().findByShop(ishop).forEach(p -> {
			p.copyShop(ishop);
		});
	}

	private void updateAllRelatedFile(Shop s, List<ShopFile> files, Account a) {
		files.forEach(sf -> updateRelatedFile(s, sf, a));
	}

	private void updateRelatedFile(Shop s, ShopFile sf, Account a) {
		if (sf.getId().equals(Long.valueOf(0))) {
			createShopFile(s, sf, a);
		} else if (sf.getIsDeleted()) {
			sf.delete();
		} else {
			updateShopFile(s, sf, a);
		}
	}

	private void updateShopFile(Shop s, ShopFile sf, Account a) {
		// get file by id
		ShopFile sfUpdate = new ShopFileRepository().findById(sf.getId()).orElseGet(() -> {
			throw new IllegalArgumentException("存在しないファイルです");
		});

		// compare information
		if (!sfUpdate.getDisplayName().equals(sf.getDisplayName()) 
			|| !sfUpdate.getComment().equals(sf.getComment())) {
			sfUpdate.setUpdateAccount(a);
			sfUpdate.setComment(sf.getComment());
			sfUpdate.setDisplayName(sf.getDisplayName());
			sfUpdate.update();
		}
	}

	private void createShopFile(Shop s, ShopFile sf, Account a) {
		ShopFile sfCreated = new ShopFile(sf.getFile(), s, a);
		sfCreated.setComment(sf.getComment());
		sfCreated.setDisplayName(sf.getDisplayName());
		sfCreated.create();
	}

	private void updateAllRelatedImage(Shop s, List<ShopImage> images, Account a) {
		images.forEach(si -> updateRelatedImage(s, si, a));
	}

	private void updateRelatedImage(Shop s, ShopImage si, Account a) {
		if (si.getId().equals(Long.valueOf(0))) {
			createShopImage(s, si, a);
		} else if (si.getIsDeleted()) {
			si.delete();
		} else {
			updateShopImage(s, si, a);
		}
	}

	private void createShopImage(Shop s, ShopImage si, Account a) {
		// create shop image
		ShopImage siCreated = new ShopImage(si.getFile(), s, a);
		siCreated.setShopId(s.getId());
		siCreated.setComment(si.getComment());
		siCreated.create();

		// update default image
		if (si.getIsDefaultImage()) {
			updateImagePath(s, siCreated);
		}
	}

	private void updateShopImage(Shop s, ShopImage si, Account a) {
		// update shop image
		ShopImage siUpdate = new ShopImageRepository().findById(si.getId()).orElseGet(() -> {
			throw new IllegalArgumentException("存在しないファイルです");
		});
		// compare information
		if (!siUpdate.getComment().equals(si.getComment())) {
			siUpdate.setUpdateAccount(a);
			siUpdate.setComment(si.getComment());
			siUpdate.update();
		}

		// update default image
		if (si.getIsDefaultImage()) {
			updateImagePath(s, siUpdate);
		}
	}

	private void updateImagePath(Shop s, ShopImage si) {
		s.setImagePath(si.urlPath() + si.getName());
		s.update();
	}

	private void updateShopHistory(Long id) {
		new ShopRepository().updateShopHistory(id);
	}
}