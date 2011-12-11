package com.github.meo.db.tool.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.github.meo.db.tool.testsuite.TestObjects;

public class AttributeTypeTests {

	private final static String ATTRIBUTE_NAME = "Attribute Name";

	private IAttributeType attributeType;

	@Before
	public void setUp() {
		attributeType = new AttributeType();
	}

	@Test
	public void createInstanceA() {
		attributeType = new AttributeType(ATTRIBUTE_NAME);
		assertEquals(ATTRIBUTE_NAME, attributeType.getName());
	}
	
	@Test
	public void createInstanceB() {
		attributeType = new AttributeType(ATTRIBUTE_NAME, true);
		assertEquals(ATTRIBUTE_NAME, attributeType.getName());
		assertEquals(true, attributeType.isPrimaryKey());
	}

	@Test
	public void cloneAttribute() {
		for (IAttributeType attributeType : TestObjects.getAttributeTypes()) {
			IAttributeType clonedAttribute = attributeType.clone();
			assertNotSame(attributeType, clonedAttribute);
			assertTrue(attributeType.equals(clonedAttribute));
			assertEquals(attributeType.getClass(), clonedAttribute.getClass());
			assertEquals(attributeType.getName(), clonedAttribute.getName());
		}
	}

	@Test
	public void equalsNull() {
		for (IAttributeType attributeType : TestObjects.getAttributeTypes()) {
			assertFalse(attributeType.equals(null));
		}
	}

	@Test
	public void equals() {

		IAttributeType modifiedAttributeType;

		for (IAttributeType attributeType : TestObjects.getAttributeTypes()) {
			modifiedAttributeType = attributeType.clone();
			modifiedAttributeType.setName("Modified name");
			assertFalse(attributeType.equals(modifiedAttributeType));
		}

		for (IAttributeType attributeType : TestObjects.getAttributeTypes()) {
			attributeType.setName(null);
			modifiedAttributeType = attributeType.clone();
			assertTrue(attributeType.equals(attributeType));
			assertTrue(attributeType.equals(attributeType.clone()));

			modifiedAttributeType.setName("Modified name");
			assertFalse(attributeType.equals(modifiedAttributeType));
		}
	}

	@Test
	public void equalsSameObject() {
		for (IAttributeType attributeType : TestObjects.getAttributeTypes()) {
			assertTrue(attributeType.equals(attributeType));
		}
	}

	@Test
	public void equalsClonedObject() {
		for (IAttributeType attributeType : TestObjects.getAttributeTypes()) {
			assertTrue(attributeType.equals(attributeType.clone()));
		}
	}

	@Test
	public void equalsDifferentClass() {
		attributeType.equals(new Object());
	}

	@Test
	public void getSetName() {
		attributeType.setName(ATTRIBUTE_NAME);
		assertEquals(ATTRIBUTE_NAME, attributeType.getName());
	}

	@Test
	public void getSetIsPrimaryKey() {
		attributeType.setPrimaryKey(true);
		assertEquals(true, attributeType.isPrimaryKey());
	}
	
	@Test
	public void testToString() {
		attributeType.setName(ATTRIBUTE_NAME);
		assertEquals(ATTRIBUTE_NAME, attributeType.toString());
	}

	@Test
	public void testGetAttribute() {
		for (IAttributeType attributeType : TestObjects.getAttributeTypes()) {
			IAttribute attribute = attributeType.getAttribute();
			assertEquals(attributeType.getName(), attribute.getAttributeType()
					.getName());
		}
	}
}