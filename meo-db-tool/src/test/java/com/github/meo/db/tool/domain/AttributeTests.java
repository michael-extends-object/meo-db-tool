package com.github.meo.db.tool.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.github.meo.db.tool.testsuite.TestObjects;

public class AttributeTests {

	private final static String ATTRIBUTE_NAME = "Attribute Name";
	private final static Object ATTRIBUTE_VALUE = new String("Attribute Value");

	private IAttributeType attributeType;
	private IAttribute attribute;

	@Before
	public void setUp() {
		attributeType = new AttributeType(ATTRIBUTE_NAME);
		attribute = attributeType.getAttribute();
	}

	@Test
	public void createInstanceA() {
		attribute = new Attribute(attributeType);
		assertEquals(attributeType, attribute.getAttributeType());
	}

	@Test
	public void createInstanceB() {
		attribute = new Attribute(attributeType, ATTRIBUTE_VALUE);
		assertEquals(attributeType, attribute.getAttributeType());
		assertEquals(ATTRIBUTE_VALUE, attribute.getValue());
	}

	@Test
	public void cloneAttribute() {
		for (IAttribute attribute : TestObjects.getAttributes()) {
			IAttribute clonedAttribute = attribute.clone();
			assertNotSame(attribute, clonedAttribute);
			assertTrue(attribute.equals(clonedAttribute));
			assertEquals(attribute.getClass(), clonedAttribute.getClass());
			assertEquals(attribute.getAttributeType(),
					clonedAttribute.getAttributeType());
			assertEquals(attribute.getValue(), clonedAttribute.getValue());
		}
	}

	@Test
	public void equalsNull() {
		for (IAttribute attribute : TestObjects.getAttributes()) {
			assertFalse(attribute.equals(null));
		}
	}

	@Test
	public void testEqualsDifferentAttrbuteType() {
		IAttribute attributeCloned = attribute.clone();
		attributeCloned.setAttributeType(new AttributeType());
		assertFalse(attribute.equals(attributeCloned));
	}
	
	@Test
	public void testEqualsValueNullA() {
		IAttribute attributeCloned = attribute.clone();
		attribute.setValue("Value");
		attributeCloned.setValue(null);
		assertFalse(attribute.equals(attributeCloned));
	}
	
	@Test
	public void testEqualsValueNullB() {
		IAttribute attributeCloned = attribute.clone();
		attribute.setValue(null);
		attributeCloned.setValue("Value");
		assertFalse(attribute.equals(attributeCloned));
	}
	
	@Test
	public void testEqualsDifferentValue() {
		IAttribute attributeCloned = attribute.clone();
		attribute.setValue("Value");
		attributeCloned.setValue("Different value");
		assertFalse(attribute.equals(attributeCloned));
	}
	
	@Test
	public void equalsSameObject() {
		for (IAttribute attribute : TestObjects.getAttributes()) {
			assertTrue(attribute.equals(attribute));
		}
	}

	@Test
	public void equalsClonedObject() {
		for (IAttribute attribute : TestObjects.getAttributes()) {
			assertTrue(attribute.equals(attribute.clone()));
		}
	}

	@Test
	public void equalsDifferentClass() {
		for (IAttribute attribute : TestObjects.getAttributes()) {
			attribute.equals(new Object());
		}
	}

	@Test
	public void testSetGetAttributeType() {
		attribute.setAttributeType(attributeType);
		assertEquals(attributeType, attribute.getAttributeType());
	}

	@Test
	public void testSetAttributeTypeNull() {
		attribute.setAttributeType(null);
		assertNotNull(attribute.getAttributeType());
	}

	@Test
	public void testSetGetValue() {
		attribute.setValue(ATTRIBUTE_VALUE);
		assertEquals(ATTRIBUTE_VALUE, attribute.getValue());
	}

	@Test
	public void testToString() {
		assertEquals(ATTRIBUTE_NAME, attribute.toString());
	}
}