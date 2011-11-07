package com.github.meo.db.tool.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.github.meo.db.tool.testsuite.TestObjects;

public class AttributeImplTests {

	private final static String ATTRIBUTE_NAME = "Attribute Name";
	private final static Object ATTRIBUTE_VALUE = new String("Attribute Value");

	private Attribute attribute;

	@Before
	public void setUp() {
		attribute = new AttributeImpl();
	}

	@Test
	public void createInstanceA() {
		attribute = new AttributeImpl(ATTRIBUTE_NAME);
		assertEquals(ATTRIBUTE_NAME, attribute.getName());
	}

	@Test
	public void createInstanceB() {
		attribute = new AttributeImpl(ATTRIBUTE_NAME, ATTRIBUTE_VALUE);
		assertEquals(ATTRIBUTE_NAME, attribute.getName());
		assertEquals(ATTRIBUTE_VALUE, attribute.getValue());
	}

	@Test
	public void cloneAttribute() {
		for (Attribute attribute : TestObjects.getAttributes()) {
			Attribute clonedAttribute = (Attribute) attribute.clone();
			assertNotSame(attribute, clonedAttribute);
			assertTrue(attribute.equals(clonedAttribute));
			assertEquals(attribute.getClass(), clonedAttribute.getClass());
			assertEquals(attribute.getName(), clonedAttribute.getName());
			assertEquals(attribute.getValue(), clonedAttribute.getValue());
		}
	}

	@Test
	public void equals() {

		Attribute modifiedAttribute;

		for (Attribute attribute : TestObjects.getAttributes()) {
			modifiedAttribute = (Attribute) attribute.clone();
			modifiedAttribute.setName("Modified name");
			assertFalse(attribute.equals(modifiedAttribute));
		}

		for (Attribute attribute : TestObjects.getAttributes()) {
			modifiedAttribute = (Attribute) attribute.clone();
			modifiedAttribute.setValue("Modified value");
			assertFalse(attribute.equals(modifiedAttribute));
		}

		for (Attribute attribute : TestObjects.getAttributes()) {
			modifiedAttribute = (Attribute) attribute.clone();
			modifiedAttribute.setName("Modified name");
			modifiedAttribute.setValue("Modified value");
			assertFalse(attribute.equals(modifiedAttribute));
		}

		for (Attribute attribute : TestObjects.getAttributes()) {
			modifiedAttribute = (Attribute) attribute.clone();
			modifiedAttribute.setName(null);
			assertFalse(attribute.equals(modifiedAttribute));
		}

		for (Attribute attribute : TestObjects.getAttributes()) {
			modifiedAttribute = (Attribute) attribute.clone();
			modifiedAttribute.setValue(null);
			attribute.setValue("Attribute value");
			assertFalse(attribute.equals(modifiedAttribute));
		}

		for (Attribute attribute : TestObjects.getAttributes()) {
			modifiedAttribute = (Attribute) attribute.clone();
			modifiedAttribute.setValue("Modified Attribute value");
			attribute.setValue(null);
			assertFalse(attribute.equals(modifiedAttribute));
		}

		for (Attribute attribute : TestObjects.getAttributes()) {
			modifiedAttribute = (Attribute) attribute.clone();
			modifiedAttribute.setValue("Modified Attribute value");
			attribute.setValue("Attribute value");
			assertFalse(attribute.equals(modifiedAttribute));
		}

		for (Attribute attribute : TestObjects.getAttributes()) {
			attribute.setName(null);
			attribute.setValue(null);
			modifiedAttribute = (Attribute) attribute.clone();
			assertTrue(attribute.equals(attribute));
			assertTrue(attribute.equals(attribute.clone()));

			modifiedAttribute.setName("Modified name");
			assertFalse(attribute.equals(modifiedAttribute));

			modifiedAttribute.setName(null);
			modifiedAttribute.setValue("Modified value");
			assertFalse(attribute.equals(modifiedAttribute));

		}
	}

	@Test
	public void equalsSameObject() {
		for (Attribute attribute : TestObjects.getAttributes()) {
			assertTrue(attribute.equals(attribute));
		}
	}

	@Test
	public void equalsClonedObject() {
		for (Attribute attribute : TestObjects.getAttributes()) {
			assertTrue(attribute.equals(attribute.clone()));
		}
	}

	@Test
	public void equalsDifferentClass() {
		for (Attribute attribute : TestObjects.getAttributes()) {
			attribute = new AttributeImpl();
			attribute.equals(new Object());
		}
	}

	@Test
	public void getSetName() {
		attribute.setName(ATTRIBUTE_NAME);
		assertEquals(ATTRIBUTE_NAME, attribute.getName());
	}

	@Test
	public void getValue() {
		attribute.setValue(ATTRIBUTE_VALUE);
		assertEquals(ATTRIBUTE_VALUE, attribute.getValue());
	}
}