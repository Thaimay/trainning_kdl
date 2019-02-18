package jp.co.world.storedevelopment.sp.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jp.co.world.storedevelopment.common.controller.CommonNegotiationController;
import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.NegotiationComment;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationCommentRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectRepository;
import jp.co.world.storedevelopment.model.service.AccountDataService;
import jp.co.world.storedevelopment.model.service.NegotiationService;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationCommentCreateDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationCreateFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationDetailDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationFindFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationUpdateFormViewDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectFindFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.RelatedNegotiationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.RelatedNegotiationFindFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.SelectOptionDTO;

@RestController
@RequestMapping("/sp/negotiation")
public class NegotiationRestController extends CommonNegotiationController {

	@RequestMapping("/detail/{id}")
	public NegotiationDetailDTO detail(@PathVariable Long id) {
		try {
			logStartMethod("detail");
			Negotiation negotiation = new NegotiationRepository().findById(id).orElseThrow(() -> {
				throw new IllegalArgumentException("存在しないIDです");
			});
			negotiation.open(getAccount());
			NegotiationDetailDTO detailDto = new NegotiationDetailDTO(negotiation, getAccount());
			logEndMethod("detail");
			return detailDto;
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			logException("detail", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/company/list")
	public List<SelectOptionDTO> company() {
		try {
			logStartMethod("company");
			logEndMethod("company");
			return getAccount().companyList().stream().map(c -> {
				return new SelectOptionDTO(c.getCompanyKanjiName(), c.getCompanyCode());
			}).collect(Collectors.toList());
		} catch (Exception ex) {
			logException("company", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/list")
	public List<NegotiationListDTO> list() {
		try {
			logStartMethod("list");
			NegotiationFindFormDTO dto = new NegotiationFindFormDTO();
			dto.setAccount(getAccount());
			List<NegotiationListDTO> listDto = NegotiationListDTO.toList(new NegotiationRepository().find(dto),
					getAccount());
			logEndMethod("list");
			return listDto;
		} catch (Exception ex) {
			logException("list", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find")
	public List<NegotiationListDTO> find(@RequestBody @Valid NegotiationFindFormDTO dto) {
		try {
			logStartMethod("find", dto);
			dto.setAccount(getAccount());
			List<NegotiationListDTO> listDto = NegotiationListDTO.toList(new NegotiationRepository().find(dto),
					getAccount());

			// set condition to data user
			if (dto.getIsDefaultSearch()) {
				new AccountDataService().updateNegotiationCondition(getAccount(), getNegotiationConditionFromDTO(dto));
			}

			logEndMethod("find");
			return listDto;
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			logException("find", ex.getMessage());
			return null;
		}
	}

	public String getNegotiationConditionFromDTO(NegotiationFindFormDTO dto) {
		NegotiationFindFormDTO newDTO = new NegotiationFindFormDTO();
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

		newDTO.setCorporationsJson(dto.getCorporationsJson());
		newDTO.setBuildingJson(dto.getBuildingJson());
		newDTO.setShopJson(dto.getShopJson());
		newDTO.setAccountsJson(dto.getAccountsJson());
		newDTO.setBusinessCardsJson(dto.getBusinessCardsJson());
		newDTO.setUpdateAccountJson(dto.getUpdateAccountJson());
		return newDTO.toJSON();
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
	public List<ProjectListDTO> relatedProjectNegotiationList(@RequestBody @Valid ProjectFindFormDTO dto)
			throws Throwable {
		try {
			logStartMethod("relatedProjectNegotiationList", dto);
			dto.setAccountId(getAccount().getId());
			dto.setAccount(getAccount());
			List<ProjectListDTO> listDTO = findForSP(dto, getAccount());
			logEndMethod("relatedProjectNegotiationList");
			return listDTO;
		} catch (Exception ex) {
			logException("relatedProjectNegotiationList", ex.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/create")
	@ResponseStatus(HttpStatus.OK)
	public NegotiationDetailDTO create(@RequestParam("json") String json,
			@RequestParam("files") List<MultipartFile> files) throws Throwable {
		try {
			logStartMethod("create");

			NegotiationCreateFormDTO dto = NegotiationCreateFormDTO.toDTO(json);
			Negotiation negotiation = dto.toModel();

			dto.setMultipartFileList(files);

			new NegotiationService().createAll(negotiation, dto, getAccount());

			Negotiation created = new NegotiationRepository().findById(negotiation.getId()).orElseThrow(() -> {
				throw new IllegalArgumentException("存在しないIDです");
			});
			return new NegotiationDetailDTO(created, getAccount());
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			logException("create", ex.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/create/noSendMail")
	@ResponseStatus(HttpStatus.OK)
	public NegotiationDetailDTO createNoSendMail(@RequestParam("json") String json,
			@RequestParam("files") List<MultipartFile> files) throws Throwable {
		try {
			logStartMethod("create/noSendMail");

			NegotiationCreateFormDTO dto = NegotiationCreateFormDTO.toDTO(json);
			Negotiation negotiation = dto.toModel();

			dto.setMultipartFileList(files);

			new NegotiationService().createNoSendMail(negotiation, dto, getAccount());

			Negotiation created = new NegotiationRepository().findById(negotiation.getId()).orElseThrow(() -> {
				throw new IllegalArgumentException("存在しないIDです");
			});
			return new NegotiationDetailDTO(created, getAccount());
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			logException("create/noSendMail", ex.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/draft")
	@ResponseStatus(HttpStatus.OK)
	public NegotiationDetailDTO draft(@RequestParam("json") String json,
			@RequestParam("files") List<MultipartFile> files) {
		try {
			logStartMethod("draft");
			NegotiationCreateFormDTO dto = NegotiationCreateFormDTO.toDTO(json);
			Negotiation negotiation = dto.toModel();

			dto.setMultipartFileList(files);

			new NegotiationService().saveTemporary(negotiation, dto, getAccount());

			Negotiation created = new NegotiationRepository().findById(negotiation.getId()).orElseThrow(() -> {
				throw new IllegalArgumentException("存在しないIDです");
			});
			NegotiationDetailDTO detailDto = new NegotiationDetailDTO(created, getAccount());
			logEndMethod("draft");
			return detailDto;
		} catch (Exception ex) {
			logException("draft", ex.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/comment/add")
	@ResponseStatus(HttpStatus.OK)
	public NegotiationDetailDTO addComment(@RequestBody @Valid NegotiationCommentCreateDTO dto) {
		try {
			logStartMethod("addComment", dto);
			Negotiation negotiation = new NegotiationRepository().findById(dto.getId()).orElseThrow(() -> {
				throw new IllegalArgumentException("存在しないIDです");
			});
			NegotiationComment comment = dto.toModel();
			comment.setCreateAccount(getAccount());
			comment.setAccountCode(getAccount().getEmployeeCd());
			negotiation.addComment(comment, getAccount());
			NegotiationDetailDTO detailDto = new NegotiationDetailDTO(negotiation, getAccount());
			logEndMethod("addComment");
			return detailDto;
		} catch (Exception ex) {
			logException("addComment", ex.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/comment/delete/{id}")
	@ResponseStatus(HttpStatus.OK)
	public NegotiationDetailDTO deleteComment(@PathVariable Long id) {
		try {
			logStartMethod("addComment");
			NegotiationComment comment = new NegotiationCommentRepository().findById(id).orElseThrow(() -> {
				throw new IllegalArgumentException("存在しないIDです");
			});

			Negotiation negotiation = comment.getNegotiation();
			if (comment.delete()) {
				NegotiationDetailDTO detailDto = new NegotiationDetailDTO(negotiation, getAccount());
				logEndMethod("addComment");
				return detailDto;
			} else {
				throw new IllegalStateException("商談コメントの削除に失敗しました:" + comment.getId());
			}
		} catch (Exception ex) {
			logException("addComment", ex.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/readLater/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void readLater(@PathVariable Long id) {
		try {
			logStartMethod("readLater");
			Negotiation negotiation = new NegotiationRepository().findById(id).orElseThrow(() -> {
				throw new IllegalArgumentException("存在しないIDです");
			});
			negotiation.switchReadLater(getAccount());
			logEndMethod("readLater");
		} catch (Exception ex) {
			logException("readLater", ex.getMessage());
		}
	}

	@RequestMapping(value = "/showUpdate/{id}")
	@ResponseStatus(HttpStatus.OK)
	public NegotiationUpdateFormViewDTO showUpdate(@PathVariable Long id) {
		try {
			logStartMethod("showUpdate");
			Negotiation negotiation = new NegotiationRepository().findById(id).orElseThrow(() -> {
				throw new IllegalArgumentException("存在しないIDです");
			});
			NegotiationUpdateFormViewDTO dto = new NegotiationUpdateFormViewDTO(negotiation, getAccount());
			dto.setCanEditImportant(getAccount().canEditImportant(negotiation));
			logEndMethod("showUpdate");
			return dto;
		} catch (Exception ex) {
			logException("showUpdate", ex.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/update")
	@ResponseStatus(HttpStatus.OK)
	public NegotiationDetailDTO update(@RequestParam("json") String json,
			@RequestParam("files") List<MultipartFile> files) throws Throwable {
		try {
			logStartMethod("update");

			NegotiationUpdateFormViewDTO dto = NegotiationUpdateFormViewDTO.toDTO(json);
			Negotiation negotiation = toModel(dto);
			dto.setMultipartFileList(files);

			new NegotiationService().updateAll(negotiation, dto, getAccount());
			return new NegotiationDetailDTO(negotiation, getAccount());
		} catch (Exception ex) {
			logException("update", ex.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/update/noSendMail")
	@ResponseStatus(HttpStatus.OK)
	public NegotiationDetailDTO noSendMailUpdate(@RequestParam("json") String json,
			@RequestParam("files") List<MultipartFile> files) throws Throwable {
		try {
			logStartMethod("update/noSendMail");

			NegotiationUpdateFormViewDTO dto = NegotiationUpdateFormViewDTO.toDTO(json);
			Negotiation negotiation = toModel(dto);
			dto.setMultipartFileList(files);

			new NegotiationService().updateNoSendMail(negotiation, dto, getAccount());
			return new NegotiationDetailDTO(negotiation, getAccount());
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			logException("update/noSendMail", ex.getMessage());
			return null;
		}
	}

	private Negotiation toModel(NegotiationUpdateFormViewDTO dto) {
		Negotiation origin = new NegotiationRepository().findById(dto.getId()).orElseThrow(() -> {
			throw new IllegalArgumentException("存在しないIDです");
		});

		Negotiation negotiation = dto.toModel();
		negotiation.setCreatedAccountCode(origin.getCreatedAccountCode());
		negotiation.setCreatedDatetime(origin.getCreatedDatetime());
		return negotiation;
	}

	public List<ProjectListDTO> findForSP(ProjectFindFormDTO dto, Account account) {
		return new ProjectRepository().findForSP(dto).stream().map(p -> new ProjectListDTO(p, account))
				.collect(Collectors.toList());
	}

}
