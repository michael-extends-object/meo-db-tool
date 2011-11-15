package com.github.meo.db.tool.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.github.meo.db.tool.testsuite.TestObjects;

public class DatabaseTableTests {

	private static final String TABLE_NAME = "Database table name";

	DatabaseTable databaseTable;
	DatabaseTable databaseTableA;
	DatabaseTable databaseTableB;

	@Before
	public void setup() {
		databaseTable = new DatabaseTable();
		databaseTableA = new DatabaseTable();
		databaseTableB = new DatabaseTable();
	}

	@Test
	public void createInstance() {
		databaseTable = new DatabaseTable(TABLE_NAME);
		assertEquals(TABLE_NAME, databaseTable.getName());
	}

	@Test
	public void toStringTest() {
		databaseTable.setName(TABLE_NAME);
		assertEquals(TABLE_NAME, databaseTable.toString());
	}

	@Test
	public void equalsNull() {
		for (DatabaseTable databaseTable : TestObjects.getDatabaseTables()) {
			assertFalse(databaseTable.equals(null));
		}
	}

	@Test
	public void equalsSameObject() {
		assertTrue(databaseTable.equals(databaseTable));
	}

	@Test
	public void equalsDifferentClass() {
		assertFalse(databaseTableA.equals(new Object()));
	}

	@Test
	public void equalsName() {
		databaseTableA.setName(TABLE_NAME);
		databaseTableB.setName(TABLE_NAME);
		assertTrue(databaseTableA.equals(databaseTableB));
	}

	@Test
	public void equalsNullNameA() {
		databaseTableA.setName(null);
		databaseTableB.setName(TABLE_NAME);
		assertFalse(databaseTableA.equals(databaseTableB));
	}

	@Test
	public void equalsNullNameB() {
		databaseTableA.setName(TABLE_NAME);
		databaseTableB.setName(null);
		assertFalse(databaseTableA.equals(databaseTableB));
	}

	@Test
	public void equalsDifferentDriver() {
		databaseTableA.setName(TABLE_NAME);
		databaseTableB.setName("Different name");
		assertFalse(databaseTableA.equals(databaseTableB));
	}
}
