package com.github.meo.db.tool.sql;

import java.util.List;

import org.springframework.util.Assert;

import com.github.meo.db.tool.domain.db.Table;
import com.github.meo.db.tool.domain.db.Column;

public class SqlStatementUtils {

	public final static String SQL_INSERT_STATEMENT = "INSERT INTO %s(%s) VALUES(%s)";
	public final static String SQL_SELECT_STATEMENT = "SELECT %s FROM %s";
	public final static String SQL_SELECT_WHERE_STATEMENT = "SELECT %s FROM %s WHERE %s";
	public final static String SQL_UPDATE_STATEMENT = "UPDATE %s SET %s";
	public final static String SQL_UPDATE_WHERE_STATEMENT = "UPDATE %s SET %s WHERE %s";
	public final static String SQL_DELETE_STATEMENT = "DELETE FROM %s";
	public final static String SQL_DELETE_WHERE_STATEMENT = "DELETE FROM %s WHERE %s";

	public SqlStatementUtils() {
	}

	public static String getColumnList(List<Column> columns) {

		Assert.notEmpty(columns);

		String sqlList = "";

		for (Column column : columns) {
			sqlList += ", ";
			sqlList += column;
		}

		return sqlList.replaceFirst(", ", "");
	}

	public static String getValuePlaceholders(int count) {

		Assert.isTrue(count > 0, "The value must be greater than zero");
		String sqlList = "";

		for (int i = 0; i < count; i++) {
			sqlList += ", ?";
		}

		return sqlList.replaceFirst(", ", "");
	}

	public static String getWhereCondition(List<Column> colmuns) {

		Assert.notEmpty(colmuns);
		String whereCondition = "";

		for (Column column : colmuns) {
			whereCondition += " AND ";
			whereCondition += column;
			whereCondition += " = ?";
		}

		return whereCondition.replaceFirst(" AND ", "");
	}

	public static String getInsertStatement(Table table,
			List<Column> colmunsWhere) {

		Assert.notNull(table);
		Assert.notEmpty(colmunsWhere);

		SqlColumnList columnListWhere = new SqlColumnList(colmunsWhere);

		return String.format(SQL_INSERT_STATEMENT, table, columnListWhere,
				columnListWhere.getPlaceholders());
	}

	public static String getSelectStatement(Table table,
			List<Column> columns) {

		SqlColumnList columnListSelect = new SqlColumnList(columns);

		return String.format(SQL_SELECT_STATEMENT, columnListSelect, table);
	}

	public static String getSelectWhereStatement(Table table,
			List<Column> columnsSelect,
			List<Column> columnsWhere) {

		SqlColumnList columnListSelect = new SqlColumnList(columnsSelect);
		SqlColumnList columnListWhere = new SqlColumnList(columnsWhere);

		return String.format(SQL_SELECT_WHERE_STATEMENT, columnListSelect,
				table, columnListWhere.getWhereCondition());
	}

	public static String getDeleteStatement(Table table) {
		return String.format(SQL_DELETE_STATEMENT, table);
	}

	public static String getDeleteStatement(Table table,
			List<Column> columnsWhere) {

		SqlColumnList columnListWhere = new SqlColumnList(columnsWhere);

		return String.format(SQL_DELETE_WHERE_STATEMENT, table,
				columnListWhere.getWhereCondition());
	}
}