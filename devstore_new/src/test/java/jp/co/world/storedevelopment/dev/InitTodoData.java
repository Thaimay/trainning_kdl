package jp.co.world.storedevelopment.dev;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import jp.co.world.storedevelopment.model.Todo;

public class InitTodoData extends InitTestDataSupport {

	@Override
	public void init(InitTestData main) {
		MAIN_ACCOUNT = main.MAIN_ACCOUNT;

		IntStream.range(0, GlobalVariable.todoSize).forEach(i -> createTodo(i));
		IntStream.range(0, GlobalVariable.todoSize).forEach(i -> createTodoShowEndTomorrow(i));
		IntStream.range(0, GlobalVariable.todoSize).forEach(i -> createTodoShowEndYestardey(i));
		IntStream.range(0, GlobalVariable.thirdPartyTodoSize).forEach(i -> createTodoThirdParty(i));

	}

	private void createTodo(int i) {
		Todo todo = new Todo("Todo content" + i);
		todo.setAccountId(MAIN_ACCOUNT.getId());
		// todo.setDevisionCategory("case");
		// todo.setDetailsDevisionCategory(i);
		// todo.setIsOpened(false);
		todo.setContentSub("content_sub");
		todo.setShowStartDatetime(LocalDateTime.now());
		todo.setShowEndDatetime(LocalDateTime.now());
		todo.setDeadlineDatetime(LocalDateTime.now());
		todo.setCreatedAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		todo.setUpdateAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		todo.create();
	}

	private void createTodoShowEndTomorrow(int i) {
		Todo todo = new Todo("TodoShowEndTomorrow content" + i);
		todo.setAccountId(MAIN_ACCOUNT.getId());
		// todo.setDevisionCategory("NEGOTIATION");
		// todo.setDetailsDevisionCategory(i);
		// todo.setIsOpened(false);
		todo.setContentSub("content_sub");
		todo.setShowStartDatetime(LocalDateTime.now());
		todo.setShowEndDatetime(LocalDateTime.now().plusDays(1).plusHours(2));
		todo.setDeadlineDatetime(LocalDateTime.now().plusDays(1));
		todo.setCreatedAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		todo.setUpdateAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		todo.create();
	}

	private void createTodoShowEndYestardey(int i) {
		Todo todo = new Todo("TodoShowEndYestardey content" + i);
		todo.setAccountId(MAIN_ACCOUNT.getId());
		// todo.setDevisionCategory("NEGOTIATION");
		// todo.setDetailsDevisionCategory(i);
		// todo.setIsOpened(false);
		todo.setContentSub("content_sub");
		todo.setShowStartDatetime(LocalDateTime.now().minusDays(1));
		todo.setShowEndDatetime(LocalDateTime.now().minusDays(1));
		todo.setDeadlineDatetime(LocalDateTime.now());
		todo.setCreatedAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		todo.setUpdateAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		todo.create();
	}

	private void createTodoThirdParty(int i) {
		Todo todo = new Todo("ThirdPartyIssue content" + i);
		todo.setAccountId(2L);
		// todo.setDevisionCategory("NEGOTIATION");
		// todo.setDetailsDevisionCategory(i);
		// todo.setIsOpened(false);
		todo.setContentSub("content_sub");
		todo.setShowStartDatetime(LocalDateTime.now());
		todo.setShowEndDatetime(LocalDateTime.now());
		todo.setDeadlineDatetime(LocalDateTime.now());
		todo.setCreatedAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		todo.setUpdateAccountCode(MAIN_ACCOUNT.getEmployeeCd());
		todo.create();
	}

}
