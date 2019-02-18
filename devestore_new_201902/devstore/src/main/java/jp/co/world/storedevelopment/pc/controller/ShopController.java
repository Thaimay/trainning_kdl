package jp.co.world.storedevelopment.pc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import jp.co.world.storedevelopment.common.controller.CommonBuildingController;
import jp.co.world.storedevelopment.model.IShop;
import jp.co.world.storedevelopment.model.Shop;
import jp.co.world.storedevelopment.model.ShopHistory;
import jp.co.world.storedevelopment.model.ShopImage;
import jp.co.world.storedevelopment.model.mapper.repository.IShopRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ShopRepository;
import jp.co.world.storedevelopment.model.service.ShopService;
import jp.co.world.storedevelopment.pc.controller.form.ShopFindForm;
import jp.co.world.storedevelopment.pc.controller.view.model.ShopDetailViewModel;
import jp.co.world.storedevelopment.pc.controller.view.model.ShopHistoryViewModel;
import jp.co.world.storedevelopment.pc.controller.view.model.ShopViewModel;
import jp.co.world.storedevelopment.sp.controller.dto.ShopHistoryListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ShopUpdateDTO;

@Controller
@RequestMapping("/pc/store")
public class ShopController extends CommonBuildingController {

	private static final String BASE_DIR = "pc/store/";
	private ShopRepository shopRepository = new ShopRepository();

	private String listView(Model model, ShopFindForm dto) {
		try {
			logStartMethod("listView");
			findAdvanced(model, dto);
			findKeyword(model, dto);
			logEndMethod("listView");
			return BASE_DIR + "list";
		} catch (Exception ex) {
			logException("listView", ex.getMessage());
			return null;
		}
	}

	private void findAdvanced(Model model, ShopFindForm dto) {
		List<IShop> listShopAdvanced = new ArrayList<IShop>();
		ShopFindForm findAdvancedDTO = ShopFindForm.EMPTY;
		int listShopAdvancedCount = 0;
		int pageAdvancedCount = 0;

		if (dto != null) {
			if (dto.isAdvancedSearch()) {
				findAdvancedDTO = dto;
			}

			listShopAdvanced = shopRepository.findForPC(findAdvancedDTO);
			listShopAdvancedCount = shopRepository.getCountFindForPC(findAdvancedDTO);
			pageAdvancedCount = this.getPageCount(listShopAdvancedCount, dto.getPagingSize());
		}

		model.addAttribute("findAdvancedDTO", findAdvancedDTO);
		model.addAttribute("shopAdvanceds", ShopViewModel.toList(listShopAdvanced));
		model.addAttribute("countAdvanced", listShopAdvancedCount);
		model.addAttribute("pageCountAdvanced", pageAdvancedCount);
	}

	private void findKeyword(Model model, ShopFindForm dto) {
		List<IShop> listShopKeyword = new ArrayList<IShop>();
		ShopFindForm findKeywordDTO = ShopFindForm.EMPTY;
		int listShopKeywordCount = 0;
		int pageKeywordCount = 0;

		if (dto != null) {
			if (!dto.isAdvancedSearch()) {
				findKeywordDTO = dto;
			}

			listShopKeyword = shopRepository.findForPC(findKeywordDTO);
			listShopKeywordCount = shopRepository.getCountFindForPC(findKeywordDTO);
			pageKeywordCount = this.getPageCount(listShopKeywordCount, dto.getPagingSize());
		}

		model.addAttribute("findKeywordDTO", findKeywordDTO);
		model.addAttribute("shopKeywords", ShopViewModel.toList(listShopKeyword));
		model.addAttribute("countKeyword", listShopKeywordCount);
		model.addAttribute("pageCountKeyword", pageKeywordCount);
	}

	private String detailView(Model model, IShop iShop, List<ShopHistoryListDTO> historyList) {
		model.addAttribute("iShop", new ShopDetailViewModel(iShop, getAccount()));
		model.addAttribute("historyList", historyList);
		return BASE_DIR + "detail";
	}

	private String revisionView(Model model, Long iShopId, List<ShopHistoryListDTO> historyList, ShopHistory history) {
		model.addAttribute("shop", new ShopHistoryViewModel(history, getAccount()));
		model.addAttribute("iShopId", iShopId);
		model.addAttribute("historyList", historyList);
		return BASE_DIR + "revision";
	}

	@RequestMapping("/index")
	public String index(Model model) {
		try {
			logStartMethod("index");
			String lst = list(model);

			logEndMethod("index");
			return lst;
		} catch (Exception ex) {
			logException("index", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/list")
	public String list(Model model) {
		try {
			logStartMethod("list");
			String listView = listView(model, ShopFindForm.EMPTY);
			logEndMethod("list");
			return listView;
		} catch (Exception ex) {
			logException("list", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find")
	public String find(Model model, @Valid ShopFindForm dto) {
		try {
			logStartMethod("find");
			ShopViewModel.toList(shopRepository.findForPC(dto));
			String listView = listView(model, dto);
			logEndMethod("find");
			return listView;
		} catch (Exception ex) {
			logException("find", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id, Model model) {
		try {
			logStartMethod("detail");
			IShop iShop = new ShopRepository().findShopById(id);
			List<ShopHistoryListDTO> historyList = getBuildingHistoryList(id);
			String detailView = detailView(model, iShop, historyList);
			logEndMethod("detail");
			return detailView;
		} catch (Exception ex) {
			logException("detail", ex.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/revision/{id}", method = RequestMethod.POST)
	public String revision(@PathVariable Long id, @Valid Long idHistory, Model model) {
		try {
			logStartMethod("revision");
			String redirectUrl = "";
			if (idHistory > 0) {
				ShopHistory history = null;
				List<ShopHistoryListDTO> historyList = getBuildingHistoryList(id);

				if (historyList != null && historyList.size() > 0 && idHistory != null && idHistory > 0) {
					history = shopRepository.findShopHistoryById(idHistory);
				}

				redirectUrl = revisionView(model, id, historyList, history);
			} else {
				redirectUrl = "redirect:../detail/" + id;
			}
			logEndMethod("revision");
			return redirectUrl;
		} catch (Exception ex) {
			logException("revision", ex.getMessage());
			return null;
		}
	}

	private List<ShopHistoryListDTO> getBuildingHistoryList(Long shopId) {
		List<ShopHistoryListDTO> historyList = null;
		Optional<IShop> iShop = new IShopRepository().findById(shopId);
		if (iShop.isPresent()) {
			Shop shop = shopRepository.findByIShopId(iShop.get().getId());

			if (shop != null) {
				historyList = shopRepository.getShopHistoryList(shop.getId().toString());
			}
		}

		return historyList;
	}

	@RequestMapping(value = "/showUpdate/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String showUpdate(Model model, @PathVariable Long id) {
		try {
			logStartMethod("showUpdate");
			IShop iShop = new IShopRepository().findById(id).orElseThrow(() -> {
				throw new IllegalArgumentException("存在しないIDです");
			});
			model.addAttribute("iShop", new ShopDetailViewModel(iShop, getAccount()));
			logEndMethod("showUpdate");
			return BASE_DIR + "edit";
		} catch (Exception ex) {
			logException("showUpdate", ex.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/update")
	@ResponseBody
	public String update(Model model, @Valid ShopUpdateDTO dto, @Valid List<MultipartFile> files) {
		try {
			logStartMethod("update");
			processShopImageData(dto, files);
			new ShopService().updateAll(dto, this.getAccount());
			String update = dto.getiShopId().toString();
			logEndMethod("update");
			return update;
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			logException("update", ex.getMessage());
			return null;
		}
	}

	private void processShopImageData(ShopUpdateDTO dto, List<MultipartFile> files) {
		if (dto.getShopImageDto() == null) {
			dto.setShopImageDto(new ArrayList<ShopImage>());
		}
		int fileIndex = 0;
		for (ShopImage bi : dto.getShopImageDto()) {

			if (bi.getId() == 0) {
				bi.setFile(files.get(fileIndex));
				fileIndex++;
			}
		}
	}

	public Integer getPageCount(Integer count, Integer pagingSize) {
		Integer pageCount = (int) Math.ceil(Double.valueOf(count) / Double.valueOf(pagingSize)) - 1;
		if (pageCount > 0) {
			return pageCount;
		} else {
			return 0;
		}
	}

}
