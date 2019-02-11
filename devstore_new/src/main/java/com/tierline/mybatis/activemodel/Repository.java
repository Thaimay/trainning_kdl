package com.tierline.mybatis.activemodel;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;

public abstract class Repository<T, M extends RepositoryMapper<T>> implements BeanUtilsSupport {

	protected abstract M repositoryMapper(SqlSession session);

	protected <R> R execute(Function<M, R> func) {
		try (SqlSession session = getSessionFactory().openSession()) {
			M mapper = repositoryMapper(session);
			R result = func.apply(mapper);
			session.commit();
			return result;
		}
	}

	protected void execute(Consumer<M> func) {
		try (SqlSession session = getSessionFactory().openSession()) {
			M mapper = repositoryMapper(session);
			func.accept(mapper);
			session.commit();
		}
	}

	protected SessionFactory getSessionFactory() {
		return DefaultSessionFactory.getInstance();
	}

	public abstract String tableName();

	public String primaryKeyName() {
		return "id";
	}

	public Optional<T> findById(Long id) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findById(tableName(), primaryKeyName(), id));
		});
	}

	public void deleteAll() {
		execute((mapper) -> {
			mapper.deleteAll(tableName());
		});
	}

	public List<T> findAll() {
		return execute((mapper) -> {
			return mapper.findAll(tableName());
		});
	}

	public int countAll() {
		return execute((mapper) -> {
			return mapper.countAll(tableName());
		});
	}

	public void resetSerial() {
		execute((mapper) -> {
			mapper.resetSerial(tableName(), primaryKeyName());
		});
	}

	public void deleteAllWithResetSerial() {
		execute((mapper) -> {
			mapper.resetSerial(tableName(), primaryKeyName());
			mapper.deleteAll(tableName());
		});
	}

	public Optional<T> getHead() {
		return execute((mapper) -> {
			Cursor<T> cursor = mapper.getHead(tableName());
			Iterator<T> iterator = cursor.iterator();
			if (iterator.hasNext()) {
				return Optional.of(iterator.next());
			}
			return Optional.empty();
		});
	}

}
