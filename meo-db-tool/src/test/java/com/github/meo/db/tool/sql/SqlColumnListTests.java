package com.github.meo.db.tool.sql;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.meo.db.tool.domain.db.Column;
import com.github.meo.db.tool.testsuite.TestObjects;

public class SqlColumnListTests {

	SqlColumnList sqlColumnList;
	List<Column> columns;

	private String expectedString;
	private String actualString;

	@Before
	public void setUp() throws Exception {
		sqlColumnList = new SqlColumnList();
		columns = TestObjects.getColumns();
	}

	@Test
	public void testSqlColumnListOfColumn() {
		sqlColumnList = new SqlColumnList(columns);
		assertTrue(columns == sqlColumnList.columns());
	}

	@Test
	public void testSize() {
		sqlColumnList.setColumns(columns);
		assertEquals(3, sqlColumnList.size());
	}

	@Test
	public void testToString() {
		sqlColumnList.setColumns(columns);
		expectedString = "Column A, Column B, Column C";
		actualString = sqlColumnList.toString();
		assertEquals(expectedString, actualString);
	}

	@Test
	public void testGetPlaceholders() {
		sqlColumnList.setColumns(columns);
		expectedString = "?, ?, ?";
		actualString = sqlColumnList.getPlaceholders();
		assertEquals(expectedString, actualString);
	}

	@Test
	public void testAddColumn() {
		sqlColumnList.addColumn(TestObjects.getColumnA());
		sqlColumnList.addColumn(TestObjects.getColumnB());
		sqlColumnList.addColumn(TestObjects.getColumnC());
		assertEquals(columns, sqlColumnList.columns());
	}

	@Test
	public void testGetSetColumns() {
		sqlColumnList.setColumns(columns);
		assertTrue(columns == sqlColumnList.columns());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetColumnsNull() {
		try {
			sqlColumnList.setColumns(null);
		} catch (IllegalArgumentException e) {
			expectedString = "[Assertion failed] - this argument is required; it must not be null";
			actualString = e.getMessage();
			assertEquals(expectedString, actualString);
			throw e;
		}
	}

	@Test
	public void testGetWhereCondition() {
		sqlColumnList.setColumns(columns);
		expectedString = "Column A = ? AND Column B = ? AND Column C = ?";
		actualString = sqlColumnList.getWhereCondition();
		assertEquals(expectedString, actualString);
	}

}
