package com.tierline.mybatis.activemodel;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.cursor.Cursor;

public interface RepositoryMapper<T> {

	@SelectProvider(type = RepositorySelectSQLBuilder.class, method = "findById")
	T findById(String tableName, String primaryKeyName, Long id);

	@SelectProvider(type = RepositorySelectSQLBuilder.class, method = "findAll")
	List<T> findAll(String tableName);

	@SelectProvider(type = RepositorySelectSQLBuilder.class, method = "countAll")
	int countAll(String tableName);

	@SelectProvider(type = RepositorySelectSQLBuilder.class, method = "resetSerial")
	Long resetSerial(String tableName, String primaryKeyName);

	@SelectProvider(type = RepositorySelectSQLBuilder.class, method = "getHead")
	Cursor<T> getHead(String tableName);

	class RepositorySelectSQLBuilder {

		public String getHead(final String tableName) {
			return new SQL() {
				{
					SELECT("*");
					FROM(tableName);					
				}
			}.toString();
		}

		public String findById(final String tableName, final String primaryKeyName, final Long id) {
			return new SQL() {
				{
					SELECT("*");
					FROM(tableName);
					WHERE(format("%s = %s", primaryKeyName, id));
				}
			}.toString();
		}

		public String findAll(final String tableName) {
			return new SQL() {
				{
					SELECT("*");
					FROM(tableName);
				}
			}.toString();
		}

		public String countAll(final String tableName) {
			return new SQL() {
				{
					SELECT("count(*)");
					FROM(tableName);
				}
			}.toString();
		}

		public String resetSerial(final String tableName, final String primaryKeyName) {
			// return new SQL() {
			String s = new SQL() {
				{
					SELECT(format("SETVAL ('%s_%s_seq', '1', false)", tableName, primaryKeyName));
				}
			}.toString();
			return s;
		}

	}

	@DeleteProvider(type = RepositoryDeleteSQLBuilder.class, method = "deleteAll")
	void deleteAll(String tableName);

	class RepositoryDeleteSQLBuilder {
		public String deleteAll(String tableName) {
			return new SQL() {
				{
					DELETE_FROM(tableName);
				}
			}.toString();
		}

	}

}
