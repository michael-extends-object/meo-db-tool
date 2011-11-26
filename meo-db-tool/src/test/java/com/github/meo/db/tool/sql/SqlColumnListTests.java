package com.github.meo.db.tool.sql;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.meo.db.tool.domain.DatabaseTableColumn;
import com.github.meo.db.tool.testsuite.TestObjects;

public class SqlColumnListTests {

	SqlColumnList sqlColumnList;
	List<DatabaseTableColumn> databaseTableColumns;

	private String expectedString;
	private String actualString;

	@Before
	public void setUp() throws Exception {
		sqlColumnList = new SqlColumnList();
		databaseTableColumns = TestObjects.getDatabaseTableColumns();
	}

	@Test
	public void testSqlColumnListListOfDatabaseTableColumn() {
		sqlColumnList = new SqlColumnList(databaseTableColumns);
		assertTrue(databaseTableColumns == sqlColumnList
				.getDatabaseTableColumns());
	}

	@Test
	public void testSize() {
		sqlColumnList.setDatabaseTableColumns(databaseTableColumns);
		assertEquals(3, sqlColumnList.size());
	}

	@Test
	public void testToString() {
		sqlColumnList.setDatabaseTableColumns(databaseTableColumns);
		expectedString = "Database table column A, Database table column B, Database table column C";
		actualString = sqlColumnList.toString();
		assertEquals(expectedString, actualString);
	}

	@Test
	public void testGetPlaceholders() {
		sqlColumnList.setDatabaseTableColumns(databaseTableColumns);
		expectedString = "?, ?, ?";
		actualString = sqlColumnList.getPlaceholders();
		assertEquals(expectedString, actualString);
	}

	@Test
	public void testAddDatabaseTableColumn() {
		sqlColumnList.addDatabaseTableColumn(TestObjects
				.getDatabaseTableColumnA());
		sqlColumnList.addDatabaseTableColumn(TestObjects
				.getDatabaseTableColumnB());
		sqlColumnList.addDatabaseTableColumn(TestObjects
				.getDatabaseTableColumnC());
		assertEquals(databaseTableColumns,
				sqlColumnList.getDatabaseTableColumns());
	}

	@Test
	public void testGetSetDatabaseTableColumns() {
		sqlColumnList.setDatabaseTableColumns(databaseTableColumns);
		assertTrue(databaseTableColumns == sqlColumnList
				.getDatabaseTableColumns());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetDatabaseTableColumnsNull() {
		try {
			sqlColumnList.setDatabaseTableColumns(null);
		} catch (IllegalArgumentException e) {
			expectedString = "<null> is not a valid argument!";
			actualString = e.getMessage();
			assertEquals(expectedString, actualString);
			throw e;
		}

	}

	@Test
	public void testGetWhereCondition() {
		sqlColumnList.setDatabaseTableColumns(databaseTableColumns);
		expectedString = "Database table column A = ? AND Database table column B = ? AND Database table column C = ?";
		actualString = sqlColumnList.getWhereCondition();
		assertEquals(expectedString, actualString);
	}

}
