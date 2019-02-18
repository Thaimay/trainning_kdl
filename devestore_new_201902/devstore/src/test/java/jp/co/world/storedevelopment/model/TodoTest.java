package jp.co.world.storedevelopment.model;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.dev.GlobalVariable;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.TodoRepository;
import jp.co.world.storedevelopment.sp.controller.dto.TodoFindByDeadlineFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.TodoFindFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.TodoListDTO;

public class TodoTest extends ModelTest {

    @Override
    @Before
    public void before() {
        super.before();
    }

    @Test
    public void create() {
        Account account = new AccountRepository().getHead().orElse(new Account());
        Todo issue = new Todo("Issue Test");
        issue.setAccountId(account.getId());
//        issue.setDevisionCategory("case");
//        issue.setDetailsDevisionCategory(1);
//        issue.setLinkDestinationId(0);
//        issue.setIsOpened(false);
        issue.setContent("content");
        issue.setContentSub("content_sub");
        issue.setShowStartDatetime(LocalDateTime.now());
        issue.setShowEndDatetime(LocalDateTime.now());
        issue.setDeadlineDatetime(LocalDateTime.now());
        issue.create();

        assertNotEquals(Long.valueOf(0), issue.getId());

        Optional<Todo> todoOption = new TodoRepository().findById(issue.getId());

        if (todoOption.isPresent()) {
            Todo createdIssue = todoOption.get();
            assertNotEquals(Long.valueOf(0), issue.getId());
            assertEquals(createdIssue.getId(), issue.getId());
        } else {
            fail();
        }
    }

    @Test
    public void update() {
        Todo todo = new TodoRepository().findById(1L).orElseThrow(() -> {
            throw new IllegalArgumentException("存在しないIDです");
        });

//        todo.setDevisionCategory("update content");
//        todo.setDetailsDevisionCategory(1);
//        todo.setLinkDestinationId(1);
//        todo.setIsOpened(false);
        todo.setContent("updated content");
        todo.setContentSub("update content sub");
        todo.update();

//        assertEquals(todo.getDetailsDevisionCategory(), 1);
    }

    @Test
    public void list() {
        TodoFindFormDTO dto = new TodoFindFormDTO();
        Account account = new AccountRepository().getHead().get();
        dto.setAccount(account);
        dto.setContent("content");

        List<TodoListDTO> list = new TodoRepository().find(dto);
        assertEquals(GlobalVariable.sumTodoSize(), list.size());
    }

    @Test
    public void findByContentAndContentSub() {
        TodoFindFormDTO dto = new TodoFindFormDTO();
        Account account = new AccountRepository().getHead().get();
        dto.setAccount(account);
        dto.setFillText("content");

        List<TodoListDTO> list = new TodoRepository().find(dto);
        assertEquals(GlobalVariable.allIssueSize(), list.size());
    }

    @Test
    public void findForDeadlineDate() {
        TodoFindByDeadlineFormDTO dto = new TodoFindByDeadlineFormDTO();
        Account account = new AccountRepository().getHead().get();
        LocalDateTime DeadlineDate = LocalDateTime.of(2018, 2, 21, 2, 5, 5);

        dto.setDeadlineDatetime(DeadlineDate);
        dto.setAccount(account);
        List<TodoListDTO> list = new TodoRepository().findByBetweenDate(dto);
        assertEquals(GlobalVariable.sumTodoSize(), list.size());
    }

}