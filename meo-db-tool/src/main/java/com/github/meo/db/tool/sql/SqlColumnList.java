package com.github.meo.db.tool.sql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import com.github.meo.db.tool.domain.db.Column;

public class SqlColumnList {

	List<Column> columns;

	public SqlColumnList() {
		init();
	}

	public SqlColumnList(List<Column> columns) {
		setColumns(columns);
	}

	private void init() {
		setColumns(new ArrayList<Column>());
	}

	public int size() {
		return columns().size();
	}

	@Override
	public String toString() {
		return SqlStatementUtils.getColumnList(columns());
	}

	public String getPlaceholders() {
		return SqlStatementUtils.getValuePlaceholders(size());
	}

	public boolean addColumn(Column column) {
		Assert.notNull(column);
		return columns.add(column);
	}

	public List<Column> columns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		Assert.notNull(columns);
		this.columns = columns;
	}

	public String getWhereCondition() {
		return SqlStatementUtils.getWhereCondition(columns());
	}

}