package jp.co.world.storedevelopment.pc.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.world.storedevelopment.common.controller.CommonSalesFileController;
import jp.co.world.storedevelopment.model.mapper.repository.SalesFileRepository;
import jp.co.world.storedevelopment.model.service.SalesFileService;
import jp.co.world.storedevelopment.model.value.FileDivision;
import jp.co.world.storedevelopment.pc.controller.form.SalesCreateForm;
import jp.co.world.storedevelopment.pc.controller.form.SalesDeleteForm;
import jp.co.world.storedevelopment.pc.controller.form.SalesFindForm;
import jp.co.world.storedevelopment.sp.controller.dto.SalesFileListDTO;

@Controller
@RequestMapping("/pc/sales/file")
public class SalesFileController extends CommonSalesFileController {
	private static final String BASE_DIR = "pc/sales/file/";

	private String listView(Model model, SalesFindForm form) {
		List<SalesFileListDTO> list = new SalesFileRepository().findAll().stream().map(fd -> {
			return new SalesFileListDTO(fd);
		}).collect(Collectors.toList());

		model.addAttribute("files", list);
		model.addAttribute("divisions", FileDivision.values());
		model.addAttribute("findForm", form);
		return BASE_DIR + "list";
	}

	@RequestMapping("/list")
	public String list(Model model) {
		try {
			logStartMethod("list");
			String listView = listView(model, new SalesFindForm());
			logEndMethod("list");
			return listView;
		} catch (Exception ex) {
			logException("list", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find")
	public String find(Model model, @Valid SalesFindForm form) {
		try {
			logStartMethod("list");
			List<SalesFileListDTO> list = new SalesFileRepository().find(form).stream().map(fd -> {
				return new SalesFileListDTO(fd);
			}).collect(Collectors.toList());
			model.addAttribute("files", list);
			model.addAttribute("divisions", FileDivision.values());
			model.addAttribute("findForm", form);
			logEndMethod("list");
			return BASE_DIR + "list";
		} catch (Exception ex) {
			logException("list", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/create")
	public String create(Model model, @Valid SalesCreateForm form, BindingResult bindingResult) {
		try {
			logStartMethod("create");
			String listView = "";
			if (bindingResult.hasErrors()) {
				listView = list(model);
				logEndMethod("create");
				return listView;
			}
			new SalesFileService().createSalesFileForPC(form, getAccount());
			listView = listView(model, new SalesFindForm());
			logEndMethod("create");
			return listView;
		} catch (Exception ex) {
			logException("create", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/delete")
	public String delete(Model model, @Valid SalesDeleteForm form) {
		try {
			logStartMethod("delete");
			new SalesFileService().deleteAllForPC(form.getIds(), getAccount());
			String listView = listView(model, new SalesFindForm());
			logEndMethod("delete");
			return listView;
		} catch (Exception ex) {
			logException("delete", ex.getMessage());
			return null;
		}
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
}
