package com.tierline.mybatis.activemodel;

import java.util.function.Function;

import org.apache.ibatis.session.SqlSession;

public abstract class ActiveModel<T> implements BeanUtilsSupport {

	private static final String DEFAULT_PRIMARY_KEY_NAME = "id";
	static {
		BeanUtilsSupport.initBeanUtils();
	}

	protected String tableName = createTableName();

	private String[] DEFAULF_IGNORES = new String[0];

	public String createTableName() {
		return this.getClass().getSimpleName().toLowerCase();
	}

	public String primaryKeyName() {
		return DEFAULT_PRIMARY_KEY_NAME;
	}

	protected abstract void setPrimaryKey(Object id);

	protected abstract String getPrimaryKey();

	protected abstract ModelMapper<T> modelMapper(SqlSession session);

	protected <R> R execute(Function<Executer<T>, R> func) {
		try (SqlSession session = DefaultSessionFactory.getInstance().openSession()) {
			ModelMapper<T> mapper = modelMapper(session);
			R result = func.apply(new Executer<T>(session, mapper));
			session.commit();
			return result;
		}
	}

	protected String[] ignoreFields() {
		return DEFAULF_IGNORES;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public T create() {
		ActiveModel<T> obj = execute(e -> {
			e.mapper.insert(this, ignoreFields());
			setPrimaryKey(e.session.getMapper(DBToolMapper.class).lastInsertID());
			return this;
		});
		return As.of(obj);
	}

	public Boolean delete() {
		return execute(e -> {
			return e.mapper.delete(this);
		});
	}

	public Boolean update() {
		return execute(e -> {
			return e.mapper.update(this, ignoreFields());
		});
	}

	class Executer<E> {

		public SqlSession session;
		public ModelMapper<E> mapper;

		public Executer(SqlSession session, ModelMapper<E> mapper) {
			this.session = session;
			this.mapper = mapper;
		}

	}

}
