package com.github.meo.db.tool.domain.mapping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.github.meo.db.tool.domain.AttributeType;
import com.github.meo.db.tool.domain.IAttributeType;
import com.github.meo.db.tool.domain.db.Column;
import com.github.meo.db.tool.testsuite.TestObjects;

public class AttributeTypeMappingTests {

	IAttributeType attributeType;
	Column column;
	AttributeTypeMapping attributeTypeMapping;
	AttributeTypeMapping attributeTypeMappingA;
	AttributeTypeMapping attributeTypeMappingB;

	@Before
	public void setUp() {
		attributeType = new AttributeType();
		column = new Column();
		attributeTypeMapping = new AttributeTypeMapping();
	}
	
	@Test
	public void testNewInstance() {
		attributeTypeMapping = new AttributeTypeMapping(attributeType, column);
		assertTrue(attributeType == attributeTypeMapping.getAttributeType());
		assertTrue(column == attributeTypeMapping.getColumn());
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
	public void testSetGetColumn() {
		attributeTypeMapping.setColumn(column);
		assertTrue(column == attributeTypeMapping.getColumn());
	}

	@Test
	public void testSetGetColumnNull() {
		attributeTypeMapping.setColumn(null);
		assertNotNull(attributeTypeMapping.getColumn());
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
	public void testEqualsDifferentColumn() {
		attributeTypeMappingA = TestObjects.getAttributeTypeMappingA();
		attributeTypeMappingB = TestObjects.getAttributeTypeMappingA();
		attributeTypeMappingA.setColumn(TestObjects
				.getColumnA());
		attributeTypeMappingA.setColumn(TestObjects
				.getColumnB());
		assertFalse(attributeTypeMappingA.equals(attributeTypeMappingB));
	}

}
