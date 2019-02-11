package jp.co.world.storedevelopment.pc.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.RelatedTaskRepository;
import jp.co.world.storedevelopment.model.mapper.repository.TodoRepository;
import jp.co.world.storedevelopment.pc.controller.dto.TopDTO;
import jp.co.world.storedevelopment.pc.controller.form.TopFindForm;
import jp.co.world.storedevelopment.sp.controller.AppController;
import jp.co.world.storedevelopment.sp.controller.dto.ImportantInformationFindForm;
import jp.co.world.storedevelopment.sp.controller.dto.ImportantInformationListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.RelatedTaskListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.TodoFindByDeadlineFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.TodoFindFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.TodoListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.TopNegotiationListDTO;

@Controller
@RequestMapping("/pc")
public class TopController extends AppController {

	private static final String BASE_PATH = "pc/top";

	private String listView(@RequestBody @Valid Model model, TopFindForm form) {
		TopDTO dto = new TopDTO();
		if (form.getDeadline() == null) {
			form.setDeadline(LocalDate.now());
		}
		dto.setTodoDeaLineList(findDeadline(form));
		dto.setTodoList(createTodoListDTO());
		dto.setImportantInformationList(createImportantInformationListDTO());
		dto.setNegotiationList(findNegotiation(form));
		dto.setRelatedTaskList(findRelatedTaskList());
		model.addAttribute("currentAccount", getAccount().getId());
		model.addAttribute("top", dto);
		model.addAttribute("form", form);
		return BASE_PATH + "/index";
	}

	@RequestMapping(value = { "/", "/top", "/index" })
	public String list(Model model, TopFindForm dto) {
		try {
			logStartMethod("list", dto);
			String listView = listView(model, dto);
			logEndMethod("list");
			return listView;
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			logException("list", ex.getMessage());
			return null;
		}
	}

	private List<TodoListDTO> findDeadline(TopFindForm form) {
		TodoFindByDeadlineFormDTO dto = new TodoFindByDeadlineFormDTO();
		dto.setAccount(getAccount());
		dto.setSelectDate(form.getDeadline());
		return new TodoRepository().findByBetweenDate(dto);
	}

	private List<TodoListDTO> createTodoListDTO() {
		TodoFindFormDTO dto = new TodoFindFormDTO();
		dto.setAccount(getAccount());
		dto.setDeadlineDatetime(LocalDateTime.now());
		return new TodoRepository().find(dto);
	}

	private List<ImportantInformationListDTO> createImportantInformationListDTO() {
		ImportantInformationFindForm dto = new ImportantInformationFindForm();
		dto.setAccount(getAccount());
		return new ImportantInformationRepository().find(dto);
	}

	private List<TopNegotiationListDTO> findNegotiation(TopFindForm form) {
		List<Negotiation> list = new NegotiationRepository().findByScheduleDate(form.getDeadline(), getAccount());
		return TopNegotiationListDTO.toListDTO(list, getAccount());
	}
	
	private List<RelatedTaskListDTO> findRelatedTaskList() {
		return new RelatedTaskRepository().findRelatedTaskList().stream().map(r -> {
			return new RelatedTaskListDTO(r);
		}).collect(Collectors.toList());
	}
}
