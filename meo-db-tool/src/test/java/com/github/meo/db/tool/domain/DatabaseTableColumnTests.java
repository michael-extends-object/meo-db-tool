package com.github.meo.db.tool.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.github.meo.db.tool.testsuite.TestObjects;

public class DatabaseTableColumnTests {

	public DatabaseTableColumn databaseTableColumn;
	public DatabaseTableColumn databaseTableColumnA;
	public DatabaseTableColumn databaseTableColumnB;
	
	public final static String COLUMN_NAME = "Column name";

	@Before
	public void setUp() {
		databaseTableColumn = new DatabaseTableColumn();
		databaseTableColumnA = new DatabaseTableColumn();
		databaseTableColumnB = new DatabaseTableColumn();
	}

	@Test
	public void createInstance() {
		databaseTableColumn = new DatabaseTableColumn(COLUMN_NAME);
		assertEquals(COLUMN_NAME, databaseTableColumn.getName());
	}

	@Test
	public void toStringTest() {
		databaseTableColumn.setName(COLUMN_NAME);
		assertEquals(COLUMN_NAME, databaseTableColumn.toString());
	}

	@Test
	public void equalsNull() {
		for (DatabaseTableColumn databaseTableColumn : TestObjects.getDatabaseTableColumns()) {
			assertFalse(databaseTableColumn.equals(null));
		}
	}

	@Test
	public void equalsSameObject() {
		assertTrue(databaseTableColumn.equals(databaseTableColumn));
	}

	@Test
	public void equalsDifferentClass() {
		assertFalse(databaseTableColumn.equals(new Object()));
	}

	@Test
	public void equalsName() {
		databaseTableColumnA.setName(COLUMN_NAME);
		databaseTableColumnB.setName(COLUMN_NAME);
		assertTrue(databaseTableColumnA.equals(databaseTableColumnB));
	}

	@Test
	public void equalsNullNameA() {
		databaseTableColumnA.setName(COLUMN_NAME);
		assertFalse(databaseTableColumnA.equals(databaseTableColumnB));
	}

	@Test
	public void equalsNullNameB() {
		databaseTableColumnB.setName(COLUMN_NAME);
		assertFalse(databaseTableColumnA.equals(databaseTableColumnB));
	}

	@Test
	public void equalsDifferentDriver() {
		databaseTableColumnA.setName(COLUMN_NAME);
		databaseTableColumnB.setName("Different name");
		assertFalse(databaseTableColumnA.equals(databaseTableColumnB));
	}

}
