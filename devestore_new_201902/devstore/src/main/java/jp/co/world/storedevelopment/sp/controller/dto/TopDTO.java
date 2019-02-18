package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.Arrays;
import java.util.List;

public class TopDTO {
	private List<TodoListDTO> todoList = Arrays.asList();

	private List<TopNegotiationListDTO> negotiationList = Arrays.asList();

	private List<RecentChangeNegotiationListDTO> recentNegotiationList = Arrays.asList();

	private List<RelatedTaskListDTO> relatedTaskList = Arrays.asList();

	public List<TodoListDTO> getTodoList() {
		return todoList;
	}

	public void setTodoList(List<TodoListDTO> todoList) {
		this.todoList = todoList;
	}

	public List<TopNegotiationListDTO> getNegotiationList() {
		return negotiationList;
	}

	public void setNegotiationList(List<TopNegotiationListDTO> negotiationList) {
		this.negotiationList = negotiationList;
	}

	public List<RecentChangeNegotiationListDTO> getRecentNegotiationList() {
		return recentNegotiationList;
	}

	public void setRecentNegotiationList(List<RecentChangeNegotiationListDTO> recentNegotiationList) {
		this.recentNegotiationList = recentNegotiationList;
	}

	public List<RelatedTaskListDTO> getRelatedTaskList() {
		return relatedTaskList;
	}

	public void setRelatedTaskList(List<RelatedTaskListDTO> relatedTaskList) {
		this.relatedTaskList = relatedTaskList;
	}

}
