package jp.co.world.storedevelopment.pc.controller.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import jp.co.world.storedevelopment.pc.controller.form.TopFindForm;
import jp.co.world.storedevelopment.sp.controller.dto.ImportantInformationListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.TodoListDTO;

public class TopDTO extends jp.co.world.storedevelopment.sp.controller.dto.TopDTO {
	private List<TodoListDTO> todoDeaLineList = new ArrayList<TodoListDTO>();
	private List<ImportantInformationListDTO> importantInformationList = new ArrayList<ImportantInformationListDTO>();

	public TopDTO() {
	}

	public List<ImportantInformationListDTO> getImportantInformationList() {
		return importantInformationList;
	}

	public void setImportantInformationList(List<ImportantInformationListDTO> importantInformationList) {
		this.importantInformationList = importantInformationList;
	}

	public LocalDate parseToLocalDate(String date) {
		return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	public void setNegotiationList(TopFindForm form) {

	}

	public List<TodoListDTO> getTodoDeaLineList() {
		return todoDeaLineList;
	}

	public void setTodoDeaLineList(List<TodoListDTO> todoDeaLineList) {
		this.todoDeaLineList = todoDeaLineList;
	}

}
