package jp.co.world.storedevelopment.sp.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.world.storedevelopment.model.mapper.repository.TodoRepository;
import jp.co.world.storedevelopment.sp.controller.dto.TodoFindByDeadlineFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.TodoFindFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.TodoListDTO;

@RestController
@RequestMapping("/sp/todo")
public class TodoRestController extends AppController {

	@CrossOrigin
	@RequestMapping("/list")
	public List<TodoListDTO> list() {
		try {
			logStartMethod("list");
			TodoFindFormDTO dto = new TodoFindFormDTO();
			dto.setAccount(getAccount());
			dto.setShowStartDatetime(LocalDateTime.now());
			dto.setShowEndDatetime(LocalDateTime.now());
			List<TodoListDTO> listDTO = new TodoRepository().find(dto);
			logEndMethod("list");
			return listDTO;
		} catch (Exception ex) {
			logException("list", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find")
	public List<TodoListDTO> find(@RequestBody @Valid TodoFindFormDTO dto) {
		try {
			logStartMethod("find", dto);
			dto.setAccount(getAccount());
			List<TodoListDTO> listDto = new TodoRepository().find(dto);
			logEndMethod("find");
			return listDto;
		} catch (Exception ex) {
			logException("find", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/deadline")
	public List<TodoListDTO> findDeadline(@RequestBody @Valid TodoFindByDeadlineFormDTO dto) {
		try {
			logStartMethod("findDeadline", dto);
			dto.setAccount(getAccount());
			dto.setSelectDate(LocalDate.now());
			List<TodoListDTO> listDto = new TodoRepository().findByBetweenDate(dto);
			logEndMethod("findDeadline");
			return listDto;
		} catch (Exception ex) {
			logException("findDeadline", ex.getMessage());
			return null;
		}
	}

}
