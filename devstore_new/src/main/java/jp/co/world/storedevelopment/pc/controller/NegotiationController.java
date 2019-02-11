package jp.co.world.storedevelopment.pc.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.common.controller.CommonNegotiationController;
import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.NegotiationComment;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationCommentRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectRepository;
import jp.co.world.storedevelopment.model.service.AccountDataService;
import jp.co.world.storedevelopment.model.service.NegotiationService;
import jp.co.world.storedevelopment.pc.controller.dto.NegotiationUpdateFormViewModel;
import jp.co.world.storedevelopment.pc.controller.form.NegotiationCreateForm;
import jp.co.world.storedevelopment.pc.controller.form.NegotiationFindForm;
import jp.co.world.storedevelopment.pc.controller.form.NegotiationUpdateForm;
import jp.co.world.storedevelopment.pc.controller.form.ProjectFindForm;
import jp.co.world.storedevelopment.pc.controller.view.model.NegotiationDetailViewModel;
import jp.co.world.storedevelopment.pc.controller.view.model.NegotiationViewModel;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationCommentCreateDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationCommentDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationDetailDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.RelatedNegotiationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.RelatedNegotiationFindFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.SelectOptionDTO;

@Controller
@RequestMapping("/pc/negotiation")
public class NegotiationController extends CommonNegotiationController {

	private static final String BASE_DIR = "pc/negotiation/";

	private String listView(Model model, NegotiationFindForm dto) {
		List<Negotiation> list = new NegotiationRepository().findForPC(dto);
		int allNegotiationSize = new NegotiationRepository().countForPC(dto);
		int totalPageSize = (int) Math.ceil((double) allNegotiationSize / (double) Application.PAGING_SIZE) - 1;
			
		dto.loadJson();

		model.addAttribute("numberOfPage", dto.getNumberOfPage());
		model.addAttribute("totalPageSize", totalPageSize);
		model.addAttribute("negotiations", NegotiationViewModel.toList(list, getAccount()));
		model.addAttribute("searchQuery", dto);
		if(totalPageSize > 0) {
			model.addAttribute("pageCountKeyword", totalPageSize);
		} else {
			model.addAttribute("pageCountKeyword", 0);
		}
		return BASE_DIR + "list";
	}

	private String detailView(Model model, Negotiation negotiation) {
		model.addAttribute("negotiation", new NegotiationDetailViewModel(negotiation, getAccount()));
		return BASE_DIR + "detail";
	}

	@RequestMapping("/index")
	public String index(Model model) {
		try {
			logStartMethod("index");
			String index = list(model);
			logEndMethod("index");
			return index;
		} catch (Exception ex) {
			logException("index", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/list")
	public String list(Model model) {
		try {
			logStartMethod("list");
			NegotiationFindForm dto = NegotiationFindForm.EMPTY;

			if (getAccount().hasDataUser()) {
				String json = getAccount().fetchAccountData().get().getNegotiationCondition();
				if (!json.isEmpty()) {
					dto = NegotiationFindForm.toDTO(json);
				}
			}

			dto.setAccount(getAccount());
			String listView = listView(model, dto);
			logEndMethod("list");
			return listView;
		} catch (Exception ex) {
			logException("list", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find")
	public String find(Model model, NegotiationFindForm dto) {
		try {
			logStartMethod("find");
			dto.setAccount(getAccount());
			NegotiationListDTO.toList(new NegotiationRepository().findForPC(dto), getAccount());
			String listView = listView(model, dto);

			// set condition to data user
			if (dto.getIsDefaultSearch()) {
				new AccountDataService().updateNegotiationCondition(getAccount(), getNegotiationConditionFromDTO(dto));
			}

			logEndMethod("find");
			return listView;
		} catch (Exception ex) {
			logException("find", ex.getMessage());
			return null;
		}
	}

	public String getNegotiationConditionFromDTO(NegotiationFindForm dto) {
		NegotiationFindForm newDTO = new NegotiationFindForm();
		newDTO.setTitle(dto.getTitle());
		newDTO.setImplementationStartDate(dto.getImplementationStartDate());
		newDTO.setImplementationEndDate(dto.getImplementationEndDate());
		newDTO.setCorporationIds(dto.getCorporationIds());
		newDTO.setBuildingIds(dto.getBuildingIds());
		newDTO.setShopIds(dto.getShopIds());
		newDTO.setAccountIds(dto.getAccountIds());
		newDTO.setInterviewIds(dto.getInterviewIds());
		newDTO.setUpdateAccountIds(dto.getUpdateAccountIds());
		newDTO.setDivision(dto.getDivision());
		newDTO.setOrderByOption(dto.getOrderByOption());
		newDTO.loadJson();
		return newDTO.toJSON();
	}

	private List<SelectOptionDTO> companyOptions() {
		return getAccount().companyList().stream().map(c -> {
			return new SelectOptionDTO(c.getCompanyKanjiName(), c.getCompanyCode());
		}).collect(Collectors.toList());
	}

	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id, Model model) {
		try {
			logStartMethod("detail");
			Negotiation negotiation = new NegotiationRepository().findById(id).orElseThrow(() -> {
				throw new IllegalArgumentException("存在しないIDです");
			});
			negotiation.open(getAccount());
			String detailView = detailView(model, negotiation);
			logEndMethod("detail");
			return detailView;
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			logException("detail", ex.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/showCreate/{createType}")
	public String showCreate(Model model, @ModelAttribute("form") NegotiationCreateForm form,
			@ModelAttribute @PathVariable String createType) {
		try {
			logStartMethod("showCreate");
			model.addAttribute("loginAccount",
					new NegotiationRelationDTO(getAccount().getId(), getAccount().getFullName()));
			model.addAttribute("companys", companyOptions());
			model.addAttribute("canCreateImportant", getAccount().canCreateImportant());
			model.addAttribute("fileDivisionList", findFileDivision());
			logEndMethod("showCreate");
			return BASE_DIR + createType + "_create_form";
		} catch (Exception ex) {
			logException("showCreate", ex.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/create/{createType}")
	@ResponseBody
	public String create(@ModelAttribute("form") @Valid NegotiationCreateForm form, BindingResult bindingResult,
			@ModelAttribute @PathVariable String createType) {
		try {
			logStartMethod("create");
			if (bindingResult.hasErrors()) {
				return BASE_DIR + createType + "_create_form";
			}

			Negotiation negotiation = form.toModel();
			form.setMultipartFileList(form.getUploadFiles());

			if (form.getNoSendMail()) {
				new NegotiationService().createAll(negotiation, form, getAccount());
			} else {
				new NegotiationService().createNoSendMail(negotiation, form, getAccount());
			}

			Negotiation created = new NegotiationRepository().findById(negotiation.getId()).orElseThrow(() -> {
				throw new IllegalArgumentException("存在しないIDです");
			});

			logEndMethod("create");
			return created.getId().toString();
		} catch (Exception ex) {
			logException("create", ex.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/draft/{createType}")
	@ResponseBody
	public String draft(@ModelAttribute("form") @Valid NegotiationCreateForm form, BindingResult bindingResult,
			@ModelAttribute @PathVariable String createType) {
		try {
			logStartMethod("draft");
			if (bindingResult.hasErrors()) {
				return "redirect:/" + BASE_DIR + createType + "_create_form";
			}

			Negotiation negotiation = form.toModel();
			form.setMultipartFileList(form.getUploadFiles());

			new NegotiationService().saveTemporary(negotiation, form, getAccount());

			Negotiation created = new NegotiationRepository().findById(negotiation.getId()).orElseThrow(() -> {
				throw new IllegalArgumentException("存在しないIDです");
			});

			logEndMethod("draft");
			return created.getId().toString();
		} catch (Exception ex) {
			logException("draft", ex.getMessage());
			return null;
		}

	}

	@RequestMapping(value = "/comment/list/{id}")
	@ResponseBody
	public List<NegotiationCommentDTO> commentList(@PathVariable Long id) {
		try {
			logStartMethod("commentList");
			Negotiation negotiation = new NegotiationRepository().findById(id).orElseThrow(() -> {
				throw new IllegalArgumentException("存在しないIDです");
			});
			List<NegotiationCommentDTO> lstComment = negotiation.getComments().stream().map(c -> {
				return new NegotiationCommentDTO(c, getAccount());
			}).collect(Collectors.toList());

			logEndMethod("commentList");
			return lstComment;
		} catch (Exception ex) {
			logException("commentList", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/detail/find")
	@ResponseBody
	public RelatedNegotiationDTO relatedNegotiationList(@RequestBody @Valid RelatedNegotiationFindFormDTO dto)
			throws Throwable {
		try {
			logStartMethod("relatedNegotiationList", dto);
			RelatedNegotiationDTO data = new RelatedNegotiationDTO(dto, getAccount());
			logEndMethod("relatedNegotiationList");
			return data;
		} catch (Exception ex) {
			logException("relatedNegotiationList", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/detail/project")
	@ResponseBody
	public List<ProjectListDTO> relatedProjectNegotiationList(@RequestBody @Valid ProjectFindForm dto)
			throws Throwable {
		try {
			logStartMethod("relatedProjectNegotiationList", dto);
			dto.setAccountId(getAccount().getId());
			List<ProjectListDTO> listDTO = findForPC(dto, getAccount());
			logEndMethod("relatedProjectNegotiationList");
			return listDTO;
		} catch (Exception ex) {
			logException("relatedProjectNegotiationList", ex.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/comment/add")
	@ResponseBody
	public List<NegotiationCommentDTO> addComment(@RequestBody @Valid NegotiationCommentCreateDTO dto)
			throws Throwable {
		try {
			logStartMethod("addComment", dto);

			Negotiation negotiation = new NegotiationRepository().findById(dto.getId()).orElseThrow(() -> {
				throw new IllegalArgumentException("存在しないIDです");
			});
			NegotiationComment comment = dto.toModel();
			comment.setCreateAccount(getAccount());
			comment.setAccountCode(getAccount().getEmployeeCd());
			negotiation.addComment(comment, getAccount());
			List<NegotiationCommentDTO> lstComment = new NegotiationDetailDTO(negotiation, getAccount()).getComments();

			logEndMethod("addComment");
			return lstComment;
		} catch (Exception ex) {
			logException("addComment", ex.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/comment/delete/{id}")
	@ResponseBody
	public List<NegotiationCommentDTO> deleteComment(@PathVariable Long id) {
		try {
			logStartMethod("deleteComment");
			List<NegotiationCommentDTO> lstComment = null;
			NegotiationComment comment = new NegotiationCommentRepository().findById(id).orElseThrow(() -> {
				throw new IllegalArgumentException("存在しないIDです");
			});
			if (comment.delete()) {
				Negotiation negotiation = comment.getNegotiation();
				lstComment = negotiation.getComments().stream().map(c -> {
					return new NegotiationCommentDTO(c, getAccount());
				}).collect(Collectors.toList());
			} else {
				throw new IllegalStateException("商談コメントの削除に失敗しました:" + comment.getId());
			}

			logEndMethod("deleteComment");
			return lstComment;
		} catch (Exception ex) {
			logException("deleteComment", ex.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/showUpdate/{id}")
	public String showUpdate(Model model, @PathVariable Long id) {
		try {
			logStartMethod("showUpdate");

			Negotiation negotiation = new NegotiationRepository().findById(id).orElseThrow(() -> {
				throw new IllegalArgumentException("存在しないIDです");
			});
			NegotiationUpdateFormViewModel dto = new NegotiationUpdateFormViewModel(negotiation, getAccount());
			dto.setCanEditImportant(getAccount().canEditImportant(negotiation));
			model.addAttribute("negotiation", dto);
			model.addAttribute("fileDivisionList", findFileDivision());

			logEndMethod("showUpdate");
			return BASE_DIR + "update_form";
		} catch (Exception ex) {
			logException("showUpdate", ex.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/update")
	@ResponseBody
	public String update(Model model, @ModelAttribute("form") @Valid NegotiationUpdateForm form,
			BindingResult bindingResult) {
		try {
			logStartMethod("update");
			String detailView = "";
			if (bindingResult.hasErrors()) {
				detailView = showUpdate(model, form.getId());
				logEndMethod("update");
				return detailView;
			}

			Negotiation origin = new NegotiationRepository().findById(form.getId()).orElseThrow(() -> {
				throw new IllegalArgumentException("存在しないIDです");
			});

			Negotiation negotiation = form.toModel();
			negotiation.setCreatedAccountCode(origin.getCreatedAccountCode());
			negotiation.setCreatedDatetime(origin.getCreatedDatetime());
			form.setMultipartFileList(form.getUploadFiles());

			if (form.getNoSendMail()) {
				new NegotiationService().updateAll(negotiation, form, getAccount());
			} else {
				new NegotiationService().updateNoSendMail(negotiation, form, getAccount());
			}

			logEndMethod("update");
			return negotiation.getId().toString();
		} catch (Exception ex) {
			logException("update", ex.getMessage());
			return null;
		}
	}

	public List<ProjectListDTO> findForPC(ProjectFindForm dto, Account account) {
		return new ProjectRepository().findForPC(dto).stream().map(p -> new ProjectListDTO(p, account))
				.collect(Collectors.toList());
	}

}
