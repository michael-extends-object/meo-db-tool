package com.github.meo.db.tool.domain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.meo.db.tool.exception.AttributeTypeNotFoundException;
import com.github.meo.db.tool.testsuite.AttributeSetValueError;
import com.github.meo.db.tool.testsuite.TestObjects;

public class EntityTests {

	private final static String ENTITY_NAME = "Entity Name";

	private IEntityType entityType;
	private IEntity entity;
	private IAttributeType attributeType;
	private List<IAttribute> attributes;

	@Before
	public void setUp() {
		entityType = new EntityType("Entity Type");
		entity = new Entity();
		attributeType = new AttributeType("Attribute");
	}

	@Test
	public void testNewInstanceEntityType() {
		entityType = new EntityType("Entity Type");
		entity = new Entity(entityType);
		assertTrue(entityType == entity.getEntityType());
	}

	@Test
	public void testSetGetEntityType() {
		IEntityType entityType = new EntityType("Entity Class");
		entity.setEntityType(entityType);
		assertEquals(entityType, entity.getEntityType());
	}

	@Test
	public void getSetAttributes() {

		List<IAttribute> attributes = TestObjects.getAttributes();

		entity.setAttributes(attributes);

		assertEquals(attributes, entity.getAttributes());
	}

	@Test
	public void testAddAttribute() {

		List<IAttribute> attributes = TestObjects.getAttributes();

		for (int i = 0; i < attributes.size(); i++) {
			entity.addAttribute(attributes.get(i));
		}

		List<IAttribute> attributesActual = entity.getAttributes();

		for (int i = 0; i < attributesActual.size(); i++) {
			assertEquals(attributes.get(i), attributesActual.get(i));
		}
	}

	@Test
	public void testAddAttributeNull() {
		entity.addAttribute(null);

		for (IAttribute attribute : entity.getAttributes()) {
			assertNotNull(attribute);
		}
	}

	@Test
	public void getAtrribute() throws AttributeTypeNotFoundException {

		List<IAttribute> attributes = TestObjects.getAttributes();

		String attributeName = attributes.get(1).getName();

		for (int i = 0; i < attributes.size(); i++) {
			entity.addAttribute(attributes.get(i));
		}

		assertEquals(attributeName, entity.getAttribute(attributeName)
				.getName());
	}

	@Test
	public void cloneEntity() {

		IEntity clonedEntity;

		for (IEntity entity : TestObjects.getEntities()) {

			clonedEntity = (IEntity) entity.clone();

			for (int i = 0; i < entity.getAttributes().size(); i++) {
				assertTrue(entity.getAttributes().get(i)
						.equals(clonedEntity.getAttributes().get(i)));
			}

		}
	}

	@Test
	public void equalsNull() {
		for (IEntity entity : TestObjects.getEntities()) {
			assertFalse(entity.equals(null));
		}
	}

	@Test
	public void equalsSameObject() {
		for (IEntity entity : TestObjects.getEntities()) {
			assertTrue(entity.equals(entity));
		}
	}

	@Test
	public void equalsClonedObject() {
		for (IEntity entity : TestObjects.getEntities()) {
			assertTrue(entity.equals(entity.clone()));
		}
	}

	@Test
	public void equalsDifferentClass() {
		for (IEntity entity : TestObjects.getEntities()) {
			entity.equals(new Object());
		}
	}

	@Test
	public void equalsDifferentEntityType() {
		for (IEntity entity : TestObjects.getEntities()) {
			IEntity clonedEntity = entity.clone();
			clonedEntity.setEntityType(new EntityType("New entity type"));
			assertFalse(entity.equals(clonedEntity));
		}
	}

	@Test
	public void equalsB() {

		IEntity entityClone;

		for (IEntity entity : TestObjects.getEntities()) {
			entityClone = (IEntity) entity.clone();
			entityClone.setAttributes(null);
			entity.equals(entityClone);
		}
	}

	@Test
	public void equalsC() {

		IEntity entityClone;

		for (IEntity entity : TestObjects.getEntities()) {
			entityClone = (IEntity) entity.clone();
			entityClone.getAttributes().get(1).setValue("Modified value");
			entity.equals(entityClone);
		}
	}

	@Test
	public void equals() {

		IEntity modifiedEntity;

		for (IEntity entity : TestObjects.getEntities()) {
			modifiedEntity = (IEntity) entity.clone();
			modifiedEntity.addAttribute(attributeType.getAttribute());
			assertFalse(entity.equals(modifiedEntity));
		}

		for (IEntity entity : TestObjects.getEntities()) {
			entity.setAttributes(null);
			assertTrue(entity.equals(entity));
			assertTrue(entity.equals(entity.clone()));

			modifiedEntity = (IEntity) entity.clone();

			List<IAttribute> attributes = new ArrayList<IAttribute>();
			attributes.add(attributeType.getAttribute());
			modifiedEntity.setAttributes(attributes);
			assertFalse(entity.equals(modifiedEntity));
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void getAtrributeNullArgument()
			throws AttributeTypeNotFoundException {

		List<IAttribute> attributes = TestObjects.getAttributes();

		for (int i = 0; i < attributes.size(); i++) {
			entity.addAttribute(attributes.get(i));
		}

		try {
			entity.getAttribute(null);
		} catch (IllegalArgumentException e) {
			assertEquals("The given attribute name is null!", e.getMessage());
			throw e;
		}
	}

	@Test
	public void getAttributesPrimaryKey() {

		List<IAttribute> expectedAttributes = new ArrayList<IAttribute>();

		expectedAttributes.add(TestObjects.getAttributeA());
		expectedAttributes.add(TestObjects.getAttributeC());

		entity.setAttributes(TestObjects.getAttributes());

		assertEquals(expectedAttributes, entity.getAttributesPrimaryKey());
	}

	@Test
	public void testToString() {
		entity.setEntityType(new EntityType(ENTITY_NAME));
		assertEquals(ENTITY_NAME, entity.toString());
	}

	@Test
	public void testGetSetAttributeValue()
			throws AttributeTypeNotFoundException {
		IAttribute attribute = attributeType.getAttribute();
		String value = "Value";
		entity.addAttribute(attribute);
		entity.setAttributeValue(attribute, value);
		assertEquals(value, entity.getAttributeValue(attribute));
	}

	@Test(expected = IllegalArgumentException.class)
	public void voidtestGetAttributeValueNullAttribute()
			throws AttributeTypeNotFoundException {
		try {
			entity.getAttributeValue((IAttribute) null);
		} catch (IllegalArgumentException e) {
			assertEquals("null is not valid argument!", e.getMessage());
			throw e;
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void voidtestGetAttributeValueNullAttributeName()
			throws AttributeTypeNotFoundException {
		try {
			entity.getAttributeValue((String) null);
		} catch (IllegalArgumentException e) {
			assertEquals("null is not valid argument!", e.getMessage());
			throw e;
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void voidtestSetAttributeValueNullAttribute()
			throws AttributeTypeNotFoundException {
		try {
			entity.setAttributeValue((IAttribute) null, new Object());
		} catch (IllegalArgumentException e) {
			assertEquals("null is not valid argument!", e.getMessage());
			throw e;
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void voidtestSetAttributeValueNullAttributeName()
			throws AttributeTypeNotFoundException {
		try {
			entity.setAttributeValue((String) null, new Object());
		} catch (IllegalArgumentException e) {
			assertEquals("null is not valid argument!", e.getMessage());
			throw e;
		}
	}

	@Test(expected = AttributeTypeNotFoundException.class)
	public void voidtestGetAttributeValueAttributeNotFound()
			throws AttributeTypeNotFoundException {

		String expectedString = "Couldn't find the attribute 'Attribute'";

		try {
			entity.getAttributeValue(attributeType.getAttribute());
		} catch (AttributeTypeNotFoundException e) {
			assertEquals(expectedString, e.getMessage());
			throw e;
		}
	}

	@Test
	public void testGetAttributesNotNull()
			throws AttributeTypeNotFoundException {
		List<IAttribute> attributes = TestObjects.getAttributes();
		List<IAttribute> attributesNotNull = new ArrayList<IAttribute>();

		for (IAttribute attribute : attributes) {
			attribute.setValue(null);
		}

		entity.setAttributes(attributes);

		entity.setAttributeValue(attributes.get(0), "Value");
		entity.setAttributeValue(attributes.get(2), "Value");
		attributesNotNull.add(attributes.get(0));
		attributesNotNull.add(attributes.get(2));

		assertEquals(attributesNotNull, entity.getAttributesNotNull());
	}

	@Test
	public void setValueError() throws AttributeTypeNotFoundException {
		entity.addAttribute(new AttributeSetValueError(attributeType));
		assertFalse(entity.setAttributeValue("Attribute", "Value"));
	}
}