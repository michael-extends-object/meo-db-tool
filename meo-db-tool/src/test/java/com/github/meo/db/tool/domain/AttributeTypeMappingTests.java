package com.github.meo.db.tool.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.github.meo.db.tool.testsuite.TestObjects;

public class AttributeTypeMappingTests {

	IAttributeType attributeType;
	DatabaseTableColumn column;
	AttributeTypeMapping attributeTypeMapping;
	AttributeTypeMapping attributeTypeMappingA;
	AttributeTypeMapping attributeTypeMappingB;

	@Before
	public void setUp() {
		attributeType = new AttributeType();
		column = new DatabaseTableColumn();
		attributeTypeMapping = new AttributeTypeMapping();
	}
	
	@Test
	public void testNewInstance() {
		attributeTypeMapping = new AttributeTypeMapping(attributeType, column);
		assertTrue(attributeType == attributeTypeMapping.getAttributeType());
		assertTrue(column == attributeTypeMapping.getDatabaseTableColumn());
	}

	@Test
	public void testSetGetAttributeType() {
		attributeTypeMapping.setAttributeType(attributeType);
		assertTrue(attributeType == attributeTypeMapping.getAttributeType());
	}

	@Test
	public void testSetGetAttributeTypeNull() {
		attributeTypeMapping.setAttributeType(null);
		assertNotNull(attributeTypeMapping.getAttributeType());
	}

	@Test
	public void testSetGetDatabaseTableColumn() {
		attributeTypeMapping.setDatabaseTableColumn(column);
		assertTrue(column == attributeTypeMapping.getDatabaseTableColumn());
	}

	@Test
	public void testSetGetDatabaseTableColumnNull() {
		attributeTypeMapping.setDatabaseTableColumn(null);
		assertNotNull(attributeTypeMapping.getDatabaseTableColumn());
	}

	@Test
	public void testCloneAttibuteMapping() {
		attributeTypeMapping = TestObjects.getAttributeTypeMappingA();
		AttributeTypeMapping attributeTypeMappingClone = attributeTypeMapping
				.clone();
		assertFalse(attributeTypeMapping == attributeTypeMappingClone);
		assertEquals(attributeTypeMapping, attributeTypeMappingClone);
	}

	@Test
	public void testEqualsNull() {
		for (AttributeTypeMapping attributeTypeMapping : TestObjects
				.getAttributeTypeMappings()) {
			assertFalse(attributeTypeMapping.equals(null));
		}
	}

	@Test
	public void testEqualsSameObject() {
		assertTrue(attributeTypeMapping.equals(attributeTypeMapping));
	}

	@Test
	public void testEqualsDifferentClass() {
		assertFalse(attributeTypeMapping.equals(new Object()));
	}

	@Test
	public void testEqualsDifferentAttribute() {
		attributeTypeMappingA = TestObjects.getAttributeTypeMappingA();
		attributeTypeMappingB = TestObjects.getAttributeTypeMappingA();
		attributeTypeMappingA.setAttributeType(TestObjects.getAttributeTypeA());
		attributeTypeMappingA.setAttributeType(TestObjects.getAttributeTypeB());
		assertFalse(attributeTypeMappingA.equals(attributeTypeMappingB));
	}

	@Test
	public void testEqualsDifferentDatabaseTableColumn() {
		attributeTypeMappingA = TestObjects.getAttributeTypeMappingA();
		attributeTypeMappingB = TestObjects.getAttributeTypeMappingA();
		attributeTypeMappingA.setDatabaseTableColumn(TestObjects
				.getDatabaseTableColumnA());
		attributeTypeMappingA.setDatabaseTableColumn(TestObjects
				.getDatabaseTableColumnB());
		assertFalse(attributeTypeMappingA.equals(attributeTypeMappingB));
	}

}
