package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.Todo;
import jp.co.world.storedevelopment.model.mapper.TodoRepositoryMapper;
import jp.co.world.storedevelopment.sp.controller.dto.TodoFindByDeadlineFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.TodoFindFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.TodoListDTO;

public class TodoRepository extends Repository<Todo, TodoRepositoryMapper> {

	@Override
	protected TodoRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(TodoRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(Todo.class);
	}

	public List<TodoListDTO> find(TodoFindFormDTO dto) {
		return execute((mapper) -> {
			List<Todo> myIssues = mapper.find(dto);
			return myIssues.stream().map(n -> new TodoListDTO(n)).collect(Collectors.toList());
		});
	}
	
	public List<TodoListDTO> findByBetweenDate(TodoFindByDeadlineFormDTO dto) {
		return execute((mapper) -> {
			List<Todo> todo = mapper.findByBetweenDate(dto);
			return todo.stream().map(n -> new TodoListDTO(n)).collect(Collectors.toList());
		});
	}

	public int deleteProjectTaskBy(Long projectId) {
		return execute((mapper) -> {
			return mapper.deleteProjectTaskBy(projectId);
		});
	}

	public int deleteByProject(Long id, Integer number) {
		return execute((mapper) -> {
			return mapper.deleteBy(id, number);
		});		
	}
	
	public int deleteByNegotiation(Negotiation n) {
		return execute((mapper) -> {
			return mapper.deleteByNegotiation(n);
		});
	}

	public int deleteByDetailsDivisionId(Integer detailsDivisionId) {
		return execute((mapper) -> {
			return mapper.deleteByDetailsDivisionId(detailsDivisionId);
		});
	}
}
