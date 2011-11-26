package com.github.meo.db.tool.sql;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.meo.db.tool.domain.DatabaseTable;
import com.github.meo.db.tool.domain.DatabaseTableColumn;
import com.github.meo.db.tool.testsuite.TestObjects;

public class SqlUtilsTests {

	SqlUtils sqlUtils;

	private String expectedString;
	private String actualString;
	private DatabaseTable databaseTable;
	private List<DatabaseTableColumn> databaseTableColumns;

	@Before
	public void setUp() {
		sqlUtils = new SqlUtils();
		databaseTable = TestObjects.getDatabaseTableA();
		databaseTableColumns = databaseTable.getDatabaseTableColumns();
	}

	@Test
	public void testGetColumnList() {
		expectedString = "Database table column A, Database table column B, Database table column C";
		actualString = SqlUtils.getColumnList(databaseTableColumns);
		assertEquals(expectedString, actualString);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetColumnListNull() {

		try {
			SqlUtils.getColumnList(null);
		} catch (IllegalArgumentException e) {
			expectedString = "<null> is not a valid argument!";
			actualString = e.getMessage();
			assertEquals(expectedString, actualString);
			throw e;
		}

	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetColumnListEmptyList() {

		try {
			SqlUtils.getColumnList(new ArrayList<DatabaseTableColumn>());
		} catch (IllegalArgumentException e) {
			expectedString = "The column list is empty!";
			actualString = e.getMessage();
			assertEquals(expectedString, actualString);
			throw e;
		}

	}

	@Test
	public void testGetValuesPlaceholders() {
		expectedString = "?, ?, ?";
		actualString = SqlUtils.getValuePlaceholders(3);
		assertEquals(expectedString, actualString);
	}

	@Test
	public void testGetWhereCondition() {
		expectedString = "Database table column A = ? AND Database table column B = ? AND Database table column C = ?";
		actualString = SqlUtils.getWhereCondition(databaseTableColumns);
		assertEquals(expectedString, actualString);
	}

	@Test
	public void testGetInsertStatement() {
		expectedString = "INSERT INTO Database table A(Database table column A, Database table column B, Database table column C) VALUES(?, ?, ?)";
		actualString = SqlUtils.getInsertStatement(databaseTable,
				databaseTableColumns);
		assertEquals(expectedString, actualString);
	}

	@Test
	public void testGetSelectStatement() {
		expectedString = "SELECT Database table column A, Database table column B, Database table column C FROM Database table A";
		actualString = SqlUtils.getSelectStatement(databaseTable,
				databaseTableColumns);
		assertEquals(expectedString, actualString);
	}

	@Test
	public void testGetDeleteStatement() {
		expectedString = "DELETE FROM Database table A WHERE Database table column A = ? AND Database table column B = ? AND Database table column C = ?";
		actualString = SqlUtils.getDeleteStatement(databaseTable,
				databaseTableColumns);
		assertEquals(expectedString, actualString);
	}
}
