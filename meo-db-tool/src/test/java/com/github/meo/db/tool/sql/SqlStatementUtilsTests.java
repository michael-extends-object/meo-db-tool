package com.github.meo.db.tool.sql;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.meo.db.tool.domain.db.Table;
import com.github.meo.db.tool.domain.db.Column;
import com.github.meo.db.tool.testsuite.TestObjects;

public class SqlStatementUtilsTests {

	SqlStatementUtils sqlUtils;

	private String expectedString;
	private String actualString;
	private Table table;
	private List<Column> columns;

	@Before
	public void setUp() {
		sqlUtils = new SqlStatementUtils();
		table = TestObjects.getTableA();
		columns = table.getColumns();
	}

	@Test
	public void testGetColumnList() {
		expectedString = "Column A, Column B, Column C";
		actualString = SqlStatementUtils.getColumnList(columns);
		assertEquals(expectedString, actualString);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetColumnListNull() {

		try {
			SqlStatementUtils.getColumnList(null);
		} catch (IllegalArgumentException e) {
			expectedString = "[Assertion failed] - this collection must not be empty: it must contain at least 1 element";
			actualString = e.getMessage();
			assertEquals(expectedString, actualString);
			throw e;
		}

	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetColumnListEmptyList() {

		try {
			SqlStatementUtils.getColumnList(new ArrayList<Column>());
		} catch (IllegalArgumentException e) {
			expectedString = "[Assertion failed] - this collection must not be empty: it must contain at least 1 element";
			actualString = e.getMessage();
			assertEquals(expectedString, actualString);
			throw e;
		}

	}

	@Test
	public void testGetValuesPlaceholders() {
		expectedString = "?, ?, ?";
		actualString = SqlStatementUtils.getValuePlaceholders(3);
		assertEquals(expectedString, actualString);
	}

	@Test
	public void testGetWhereCondition() {
		expectedString = "Column A = ? AND Column B = ? AND Column C = ?";
		actualString = SqlStatementUtils.getWhereCondition(columns);
		assertEquals(expectedString, actualString);
	}

	@Test
	public void testGetInsertStatement() {
		expectedString = "INSERT INTO Table A(Column A, Column B, Column C) VALUES(?, ?, ?)";
		actualString = SqlStatementUtils.getInsertStatement(table, columns);
		assertEquals(expectedString, actualString);
	}

	@Test
	public void testGetSelectStatement() {
		expectedString = "SELECT Column A, Column B, Column C FROM Table A";
		actualString = SqlStatementUtils.getSelectStatement(table, columns);
		assertEquals(expectedString, actualString);
	}

	@Test
	public void testGetDeleteStatement() {
		expectedString = "DELETE FROM Table A";
		actualString = SqlStatementUtils.getDeleteStatement(table);
		assertEquals(expectedString, actualString);
	}

	@Test
	public void testGetDeleteWhereStatement() {
		expectedString = "DELETE FROM Table A WHERE Column A = ? AND Column B = ? AND Column C = ?";
		actualString = SqlStatementUtils.getDeleteStatement(table, columns);
		assertEquals(expectedString, actualString);
	}
}
