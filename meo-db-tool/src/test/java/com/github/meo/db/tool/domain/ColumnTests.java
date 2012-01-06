package com.github.meo.db.tool.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.github.meo.db.tool.domain.db.Column;
import com.github.meo.db.tool.testsuite.TestObjects;

public class ColumnTests {

	public Column column;
	public Column columnA;
	public Column columnB;
	
	public final static String COLUMN_NAME = "Column name";

	@Before
	public void setUp() {
		column = new Column();
		columnA = new Column();
		columnB = new Column();
	}

	@Test
	public void createInstance() {
		column = new Column(COLUMN_NAME);
		assertEquals(COLUMN_NAME, column.getName());
	}

	@Test
	public void toStringTest() {
		column.setName(COLUMN_NAME);
		assertEquals(COLUMN_NAME, column.toString());
	}

	@Test
	public void equalsNull() {
		for (Column column : TestObjects.getColumns()) {
			assertFalse(column.equals(null));
		}
	}

	@Test
	public void equalsSameObject() {
		assertTrue(column.equals(column));
	}

	@Test
	public void equalsDifferentClass() {
		assertFalse(column.equals(new Object()));
	}

	@Test
	public void equalsName() {
		columnA.setName(COLUMN_NAME);
		columnB.setName(COLUMN_NAME);
		assertTrue(columnA.equals(columnB));
	}

	@Test
	public void equalsNullNameA() {
		columnA.setName(COLUMN_NAME);
		assertFalse(columnA.equals(columnB));
	}

	@Test
	public void equalsNullNameB() {
		columnB.setName(COLUMN_NAME);
		assertFalse(columnA.equals(columnB));
	}

	@Test
	public void equalsDifferentDriver() {
		columnA.setName(COLUMN_NAME);
		columnB.setName("Different name");
		assertFalse(columnA.equals(columnB));
	}

}
