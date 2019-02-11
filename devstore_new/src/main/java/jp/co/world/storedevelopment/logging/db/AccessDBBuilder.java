package jp.co.world.storedevelopment.logging.db;

import ch.qos.logback.classic.db.names.DBNameResolver;

public class AccessDBBuilder {

	static String buildInsertAccessLogSQL(DBNameResolver dbNameResolver) {
		StringBuilder sqlBuilder = new StringBuilder("INSERT INTO ");
		sqlBuilder.append(dbNameResolver.getTableName(AccessDBTableName.ACCESS_LOG)).append(" (");
		sqlBuilder.append(dbNameResolver.getColumnName(AccessDBColumnName.TIMESTMP)).append(", ");
		sqlBuilder.append(dbNameResolver.getColumnName(AccessDBColumnName.CATEGORY)).append(", ");
		sqlBuilder.append(dbNameResolver.getColumnName(AccessDBColumnName.USER_NAME)).append(", ");
		sqlBuilder.append(dbNameResolver.getColumnName(AccessDBColumnName.USER_CODE)).append(", ");
		sqlBuilder.append(dbNameResolver.getColumnName(AccessDBColumnName.URI)).append(", ");
		sqlBuilder.append(dbNameResolver.getColumnName(AccessDBColumnName.RESULT)).append(", ");
		sqlBuilder.append(dbNameResolver.getColumnName(AccessDBColumnName.LOGGER_NAME)).append(", ");
		sqlBuilder.append(dbNameResolver.getColumnName(AccessDBColumnName.LEVEL_STRING)).append(", ");
		sqlBuilder.append(dbNameResolver.getColumnName(AccessDBColumnName.THREAD_NAME)).append(", ");
		sqlBuilder.append(dbNameResolver.getColumnName(AccessDBColumnName.REFERENCE_FLAG)).append(", ");
		sqlBuilder.append(dbNameResolver.getColumnName(AccessDBColumnName.ARG0)).append(", ");
		sqlBuilder.append(dbNameResolver.getColumnName(AccessDBColumnName.ARG1)).append(", ");
		sqlBuilder.append(dbNameResolver.getColumnName(AccessDBColumnName.ARG2)).append(", ");
		sqlBuilder.append(dbNameResolver.getColumnName(AccessDBColumnName.ARG3)).append(", ");
		sqlBuilder.append(dbNameResolver.getColumnName(AccessDBColumnName.CALLER_FILENAME)).append(", ");
		sqlBuilder.append(dbNameResolver.getColumnName(AccessDBColumnName.CALLER_CLASS)).append(", ");
		sqlBuilder.append(dbNameResolver.getColumnName(AccessDBColumnName.CALLER_METHOD)).append(", ");
		sqlBuilder.append(dbNameResolver.getColumnName(AccessDBColumnName.CALLER_LINE)).append(") ");
		sqlBuilder.append("VALUES (?, ?, ? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		return sqlBuilder.toString();
	}
}
