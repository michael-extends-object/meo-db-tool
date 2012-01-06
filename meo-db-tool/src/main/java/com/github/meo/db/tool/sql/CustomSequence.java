package com.github.meo.db.tool.sql;

public class CustomSequence implements Sequence {

	private String tableName;
	private String columnNameKey;
	private String columnNameValue;
	private String keyValue;
	private int incrementBy = 1;

	public CustomSequence(String tableName, String columnNameKey,
			String columnNameValue, String keyValue) {
		setTableName(tableName);
		setColumnNameKey(columnNameKey);
		setColumnNameValue(columnNameValue);
		setKeyValue(keyValue);
	}

	public String getCurrentValueSql() {
		return String.format(SqlStatementUtils.SQL_SELECT_WHERE_STATEMENT,
				getColumnNameValue(), getTableName(), getWhereCondition());
	}

	public String getNextValueSql() {
		return String.format(SqlStatementUtils.SQL_SELECT_WHERE_STATEMENT,
				getColumnNameValue() + " + " + getIncrementBy(),
				getTableName(), getWhereCondition());
	}

	private String getWhereCondition() {
		return columnNameKey + " = " + keyValue;
	}

	public String getTableName() {
		return tableName;
	}

	public String getColumnNameKey() {
		return columnNameKey;
	}

	public String getColumnNameValue() {
		return columnNameValue;
	}

	public String getKeyValue() {
		return keyValue;
	}

	public int getIncrementBy() {
		return incrementBy;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setColumnNameKey(String columnNameKey) {
		this.columnNameKey = columnNameKey;
	}

	public void setColumnNameValue(String columnNameValue) {
		this.columnNameValue = columnNameValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	public void setIncrementBy(int incrementBy) {
		this.incrementBy = incrementBy;
	}

}
