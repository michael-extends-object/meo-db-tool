package com.github.meo.db.tool.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.github.meo.db.tool.testsuite.TestObjects;

public class AttributeMappingTests {

	Attribute attribute;
	DatabaseTableColumn column;
	AttributeMapping attributeMapping;
	AttributeMapping attributeMappingA;
	AttributeMapping attributeMappingB;

	@Before
	public void setUp() {
		attribute = new AttributeImpl();
		column = new DatabaseTableColumn();
		attributeMapping = new AttributeMapping();
	}

	@Test
	public void setGetAttribute() {
		attributeMapping.setAttribute(attribute);
		assertTrue(attribute == attributeMapping.getAttribute());
	}

	@Test
	public void setGetDatabase() {
		attributeMapping.setDatabaseTableColumn(column);
		assertTrue(column == attributeMapping.getDatabaseTableColumn());
	}
	
	@Test
	public void cloneAttibuteMapping() {
		attributeMapping = TestObjects.getAttributeMappingA();
		AttributeMapping attributeMappingClone = attributeMapping.clone();
		assertFalse(attributeMapping == attributeMappingClone);
		assertEquals(attributeMapping, attributeMappingClone);
	}
	
	@Test
	public void equalsNull() {
		for (AttributeMapping attributeMapping : TestObjects.getAttributeMappings()) {
			assertFalse(attributeMapping.equals(null));
		}
	}

	@Test
	public void equalsSameObject() {
		assertTrue(attributeMapping.equals(attributeMapping));
	}

	@Test
	public void equalsDifferentClass() {
		assertFalse(attributeMapping.equals(new Object()));
	}

	@Test
	public void equalsNullAttributeA() {
		attributeMappingA = TestObjects.getAttributeMappingA();
		attributeMappingB = TestObjects.getAttributeMappingA();
		attributeMappingA.setAttribute(null);
		assertFalse(attributeMappingA.equals(attributeMappingB));
	}

	@Test
	public void equalsNullAttributeB() {
		attributeMappingA = TestObjects.getAttributeMappingA();
		attributeMappingB = TestObjects.getAttributeMappingA();
		attributeMappingB.setAttribute(null);
		assertFalse(attributeMappingA.equals(attributeMappingB));
	}

	@Test
	public void equalsDifferentAttribute() {
		attributeMappingA = TestObjects.getAttributeMappingA();
		attributeMappingB = TestObjects.getAttributeMappingA();
		attributeMappingA.setAttribute(TestObjects.getAttributeA());
		attributeMappingA.setAttribute(TestObjects.getAttributeB());
		assertFalse(attributeMappingA.equals(attributeMappingB));
	}

	@Test
	public void equalsNullDatabaseTableColumnA() {
		attributeMappingA = TestObjects.getAttributeMappingA();
		attributeMappingB = TestObjects.getAttributeMappingA();
		attributeMappingA.setDatabaseTableColumn(null);
		assertFalse(attributeMappingA.equals(attributeMappingB));
	}

	@Test
	public void equalsNullDatabaseTableColumnB() {
		attributeMappingA = TestObjects.getAttributeMappingA();
		attributeMappingB = TestObjects.getAttributeMappingA();
		attributeMappingB.setDatabaseTableColumn(null);
		assertFalse(attributeMappingA.equals(attributeMappingB));
	}

	@Test
	public void equalsDifferentDatabaseTableColumn() {
		attributeMappingA = TestObjects.getAttributeMappingA();
		attributeMappingB = TestObjects.getAttributeMappingA();
		attributeMappingA.setDatabaseTableColumn(TestObjects
				.getDatabaseTableColumnA());
		attributeMappingA.setDatabaseTableColumn(TestObjects
				.getDatabaseTableColumnB());
		assertFalse(attributeMappingA.equals(attributeMappingB));
	}

}
