package com.github.meo.db.tool.sql;

import java.util.List;

import com.github.meo.db.tool.domain.DatabaseTable;
import com.github.meo.db.tool.domain.DatabaseTableColumn;

public class SqlUtils {

	public final static String SQL_INSERT_STATEMENT = "INSERT INTO %s(%s) VALUES(%s)";
	public final static String SQL_SELECT_STATEMENT = "SELECT %s FROM %s";
	public final static String SQL_SELECT_WHERE_STATEMENT = "SELECT %s FROM %s WHERE %s";
	public final static String SQL_UPDATE_STATEMENT = "UPDATE %s SET %s";
	public final static String SQL_UPDATE_WHERE_STATEMENT = "UPDATE %s SET %s WHERE %s";
	public final static String SQL_DELETE_STATEMENT = "DELETE FROM %s";
	public final static String SQL_DELETE_WHERE_STATEMENT = "DELETE FROM %s WHERE %s";

	public SqlUtils() {
	}

	public static String getColumnList(
			List<DatabaseTableColumn> databaseTableColumns) {

		if (databaseTableColumns == null) {
			throw new IllegalArgumentException(
					"<null> is not a valid argument!");
		}

		if (databaseTableColumns.isEmpty()) {
			throw new IllegalArgumentException(
					"The column list is empty!");
		}

		String sqlList = "";

		for (DatabaseTableColumn databaseTableColumn : databaseTableColumns) {
			sqlList += ", ";
			sqlList += databaseTableColumn;

		}

		return sqlList.replaceFirst(", ", "");
	}

	public static String getValuePlaceholders(int count) {

		String sqlList = "";

		for (int i = 0; i < count; i++) {
			sqlList += ", ?";
		}

		return sqlList.replaceFirst(", ", "");
	}

	public static String getWhereCondition(
			List<DatabaseTableColumn> databaseTableColmuns) {
		String whereCondition = "";

		for (DatabaseTableColumn databaseTableColumn : databaseTableColmuns) {
			whereCondition += " AND ";
			whereCondition += databaseTableColumn;
			whereCondition += " = ?";
		}

		return whereCondition.replaceFirst(" AND ", "");
	}

	public static String getInsertStatement(DatabaseTable databaseTable,
			List<DatabaseTableColumn> databaseTableColmuns) {

		SqlColumnList columnList = new SqlColumnList(databaseTableColmuns);

		return String.format(SQL_INSERT_STATEMENT, databaseTable, columnList,
				columnList.getPlaceholders());
	}

	public static String getSelectStatement(DatabaseTable databaseTable,
			List<DatabaseTableColumn> databaseTableColumns) {

		SqlColumnList columnList = new SqlColumnList(databaseTableColumns);

		return String.format(SQL_SELECT_STATEMENT, columnList, databaseTable);
	}

	public static String getDeleteStatement(DatabaseTable databaseTable,
			List<DatabaseTableColumn> databaseTableColumns) {

		SqlColumnList columnList = new SqlColumnList(databaseTableColumns);

		return String.format(SQL_DELETE_WHERE_STATEMENT, databaseTable,
				columnList.getWhereCondition());
	}
}