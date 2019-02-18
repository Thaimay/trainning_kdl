package jp.co.world.storedevelopment.sp.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.RecentChangeRepository;
import jp.co.world.storedevelopment.model.mapper.repository.RelatedTaskRepository;
import jp.co.world.storedevelopment.model.mapper.repository.TodoRepository;
import jp.co.world.storedevelopment.sp.controller.dto.RecentChangeNegotiationListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.RelatedTaskListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.TodoFindByDeadlineFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.TodoListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.TopDTO;
import jp.co.world.storedevelopment.sp.controller.dto.TopFindDTO;
import jp.co.world.storedevelopment.sp.controller.dto.TopNegotiationListDTO;

@RestController
@RequestMapping("/sp/top")
public class TopRestController extends AppController {
	@RequestMapping("/account")
	public Account loginAccount() {
		return getAccount();
	}

	@RequestMapping("/list")
	public TopDTO list(@RequestBody @Valid TopFindDTO findDTO) {
		try {
			logStartMethod("list", findDTO);
			TopDTO dto = new TopDTO();
			dto.setTodoList(findDeadline(findDTO));
			dto.setNegotiationList(findNegotiation(findDTO, getAccount()));
			dto.setRelatedTaskList(findRelatedTaskList());
			logEndMethod("list");
			return dto;
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			logException("list", ex.getMessage());
			return null;
		}
	}

	private List<TodoListDTO> findDeadline(TopFindDTO findDTO) {
		TodoFindByDeadlineFormDTO dto = new TodoFindByDeadlineFormDTO();
		dto.setAccount(getAccount());
		dto.setSelectDate(findDTO.getDeadlineDate());
		return new TodoRepository().findByBetweenDate(dto);
	}

	private List<TopNegotiationListDTO> findNegotiation(TopFindDTO findDTO, Account account) {
		List<Negotiation> list = new NegotiationRepository().findByScheduleDate(findDTO.getDeadlineDate(), account);
		return TopNegotiationListDTO.toListDTO(list, getAccount());
	}

	private List<RecentChangeNegotiationListDTO> findNegotiationAll() {
		return new RecentChangeRepository().findRecentChangeNegotiation().stream().map(r -> {
			return new RecentChangeNegotiationListDTO(r);
		}).collect(Collectors.toList());
	}

	private List<RelatedTaskListDTO> findRelatedTaskList() {
		return new RelatedTaskRepository().findRelatedTaskList().stream().map(r -> {
			return new RelatedTaskListDTO(r);
		}).collect(Collectors.toList());
	}
}
