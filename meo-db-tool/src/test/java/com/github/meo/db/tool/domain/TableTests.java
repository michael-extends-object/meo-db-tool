package com.github.meo.db.tool.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.github.meo.db.tool.domain.db.Table;
import com.github.meo.db.tool.testsuite.TestObjects;

public class TableTests {

	private static final String TABLE_NAME = "Database table name";

	Table table;
	Table tableA;
	Table tableB;

	@Before
	public void setup() {
		table = new Table();
		tableA = new Table();
		tableB = new Table();
	}

	@Test
	public void createInstance() {
		table = new Table(TABLE_NAME);
		assertEquals(TABLE_NAME, table.getName());
	}

	@Test
	public void toStringTest() {
		table.setName(TABLE_NAME);
		assertEquals(TABLE_NAME, table.toString());
	}

	@Test
	public void equalsNull() {
		for (Table table : TestObjects.getTables()) {
			assertFalse(table.equals(null));
		}
	}

	@Test
	public void equalsSameObject() {
		assertTrue(table.equals(table));
	}

	@Test
	public void equalsDifferentClass() {
		assertFalse(tableA.equals(new Object()));
	}

	@Test
	public void equalsName() {
		tableA.setName(TABLE_NAME);
		tableB.setName(TABLE_NAME);
		assertTrue(tableA.equals(tableB));
	}

	@Test
	public void equalsNullNameA() {
		tableA.setName(null);
		tableB.setName(TABLE_NAME);
		assertFalse(tableA.equals(tableB));
	}

	@Test
	public void equalsNullNameB() {
		tableA.setName(TABLE_NAME);
		tableB.setName(null);
		assertFalse(tableA.equals(tableB));
	}

	@Test
	public void equalsDifferentDriver() {
		tableA.setName(TABLE_NAME);
		tableB.setName("Different name");
		assertFalse(tableA.equals(tableB));
	}
}
