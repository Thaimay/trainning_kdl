package jp.co.world.storedevelopment.sp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jp.co.world.storedevelopment.common.controller.CommonBuildingController;
import jp.co.world.storedevelopment.model.IShop;
import jp.co.world.storedevelopment.model.ShopFile;
import jp.co.world.storedevelopment.model.ShopImage;
import jp.co.world.storedevelopment.model.mapper.repository.ShopRepository;
import jp.co.world.storedevelopment.model.service.ShopService;
import jp.co.world.storedevelopment.sp.controller.dto.IShopRelationShopDetailDTO;
import jp.co.world.storedevelopment.sp.controller.dto.IShopRelationShopListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ShopFindFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ShopHistoryListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ShopUpdateDTO;

/**
 * @author TaiNM
 *
 */
@RestController
@RequestMapping("/sp/shop")
public class ShopRestController extends CommonBuildingController {
	ShopRepository shopRepository;

	@CrossOrigin
	@GetMapping("/list")
	public List<IShopRelationShopListDTO> getShopList() {
		try {
			logStartMethod("getShopList");
			List<IShopRelationShopListDTO> dtos = new ArrayList<>();

			List<IShop> iShops = new ShopRepository().findShop(ShopFindFormDTO.EMPTY);

			if (iShops != null && iShops.size() > 0) {
				dtos = iShops.stream().map(x -> new IShopRelationShopListDTO(x)).collect(Collectors.toList());
			}
			logEndMethod("getShopList");
			return dtos;
		} catch (Exception ex) {
			logException("getShopList", ex.getMessage());
			return null;
		}
	}

	@CrossOrigin
	@RequestMapping("/find")
	public List<IShopRelationShopListDTO> find(@RequestBody @Valid ShopFindFormDTO dto) {
		try {
			logStartMethod("find", dto);
			List<IShopRelationShopListDTO> dtos = new ArrayList<>();

			List<IShop> iShops = new ShopRepository().findShop(dto);

			if (iShops != null && iShops.size() > 0) {
				dtos = iShops.stream().map(x -> new IShopRelationShopListDTO(x)).collect(Collectors.toList());
			}
			logEndMethod("find");
			return dtos;
		} catch (Exception ex) {
			logException("find", ex.getMessage());
			return null;
		}
	}

	@CrossOrigin
	@RequestMapping("/detail/{id}")
	public IShopRelationShopDetailDTO detail(@PathVariable Long id) {
		try {
			logStartMethod("detail");
			IShop iShop = new ShopRepository().findShopById(id);
			IShopRelationShopDetailDTO detailDto = iShop != null ? new IShopRelationShopDetailDTO(iShop, getAccount())
					: null;
			logEndMethod("detail");
			return detailDto;
		} catch (Exception ex) {
			logException("detail", ex.getMessage());
			return null;
		}
	}

	@CrossOrigin
	@RequestMapping("/update")
	@ResponseStatus(HttpStatus.OK)
	public void updateShop(@RequestParam("json") String json, @RequestParam("files") List<MultipartFile> images, @RequestParam("docs") List<MultipartFile> docs) {
		try {
			logStartMethod("updateShop");
			ShopUpdateDTO dto = ShopUpdateDTO.toDTO(json);

			int fileIndex = 0;

			for (ShopFile sf : dto.getShopFileDto()) {

				if (sf.getId() == 0) {
					sf.setFile(docs.get(fileIndex));
					fileIndex++;
				}
			}

			fileIndex = 0;
			for (ShopImage bi : dto.getShopImageDto()) {

				if (bi.getId() == 0) {
					bi.setFile(images.get(fileIndex));
					fileIndex++;
				}
			}

			new ShopService().updateAll(dto, getAccount());
			logEndMethod("updateShop");
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			logException("updateShop", ex.getMessage());
		}
	}

	@CrossOrigin
	@RequestMapping("/history")
	public List<ShopHistoryListDTO> getListShopHistory(@Valid String shopId, @Valid String name) {
		try {
			logStartMethod("getListShopHistory");
			List<ShopHistoryListDTO> historyList = new ShopRepository().getShopHistoryList(shopId);
			Stream<ShopHistoryListDTO> stream = historyList.stream().filter(x -> x.getName().startsWith(name));
			historyList = stream.collect(Collectors.toList());
			logEndMethod("getListShopHistory");
			return historyList;
		} catch (Exception ex) {
			logException("getListShopHistory", ex.getMessage());
			return null;
		}
	}
}
