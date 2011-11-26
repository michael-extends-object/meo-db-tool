package com.github.meo.db.tool.sql;

import java.util.ArrayList;
import java.util.List;

import com.github.meo.db.tool.domain.DatabaseTableColumn;

public class SqlColumnList {

	List<DatabaseTableColumn> databaseTableColumns;

	public SqlColumnList() {
		init();
	}

	public SqlColumnList(List<DatabaseTableColumn> databaseTableColumns) {
		setDatabaseTableColumns(databaseTableColumns);
	}

	private void init() {
		setDatabaseTableColumns(new ArrayList<DatabaseTableColumn>());
	}

	public int size() {
		return getDatabaseTableColumns().size();
	}

	@Override
	public String toString() {
		return SqlUtils.getColumnList(getDatabaseTableColumns());
	}

	public String getPlaceholders() {
		return SqlUtils.getValuePlaceholders(size());
	}

	public boolean addDatabaseTableColumn(
			DatabaseTableColumn databaseTableColumn) {
		return databaseTableColumns.add(databaseTableColumn);
	}

	public List<DatabaseTableColumn> getDatabaseTableColumns() {
		return databaseTableColumns;
	}

	public void setDatabaseTableColumns(
			List<DatabaseTableColumn> databaseTableColumns) {

		if (databaseTableColumns == null) {
			throw new IllegalArgumentException(
					"<null> is not a valid argument!");
		}

		this.databaseTableColumns = databaseTableColumns;
	}

	public String getWhereCondition() {
		return SqlUtils.getWhereCondition(getDatabaseTableColumns());
	}

}
