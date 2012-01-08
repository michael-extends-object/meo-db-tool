package com.github.meo.db.tool.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.meo.db.tool.exception.AttributeNotFoundException;
import com.github.meo.db.tool.testsuite.TestObjects;

public class EntityTests {

	private final static String ENTITY_TYPE_NAME = "Entity Name";
	private final static String ATTRIBUTE_NAME = "Attribute Name";

	private IEntityType entityType;
	private IEntity entity;
	private IAttributeType attributeType;

	@Before
	public void setUp() {
		entityType = new EntityType(ENTITY_TYPE_NAME);
		entity = entityType.getEntity();
		attributeType = new AttributeType("Attribute");
	}

	@Test
	public void testNewInstanceEntityType() {
		entityType = new EntityType("Entity Type");
		entity = new Entity(entityType);
		assertTrue(entityType == entity.getEntityType());
	}

	@Test
	public void testGetEntityType() {
		IEntityType entityType = new EntityType("Entity Class");
		entity = new Entity(entityType);
		assertEquals(entityType, entity.getEntityType());
	}

	@Test
	public void getAtrribute() {

		List<IAttributeType> attributeTypes = TestObjects.getAttributeTypes();
		IEntityType entityType = new EntityType(ENTITY_TYPE_NAME);
		entityType.setAttributeTypes(attributeTypes);
		entity = entityType.getEntity();

		String attributeName = attributeTypes.get(1).getName();

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
			IEntity clone = entity.clone();
			assertTrue(entity.equals(clone));
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
			EntityType entityType = new EntityType("New entity type");
			IEntity differentEntity = entityType.getEntity();
			assertFalse(entity.equals(differentEntity));
		}
	}

	@Test
	public void equalsDifferentAttributesSize() {
		List<IAttributeType> attributeTypes = new ArrayList<IAttributeType>();
		attributeTypes.add(new AttributeType());
		attributeTypes.add(new AttributeType());
		entityType.setAttributeTypes(attributeTypes);
		entity = entityType.getEntity();
		attributeTypes.add(new AttributeType());
		IEntity differentEntity = entityType.getEntity();
		assertFalse(entity.equals(differentEntity));
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

	@Test(expected = IllegalArgumentException.class)
	public void getAtrributeNullArgument() {

		List<IAttributeType> attributeTypes = TestObjects.getAttributeTypes();
		entityType.setAttributeTypes(attributeTypes);

		entity = entityType.getEntity();

		try {
			entity.getAttribute(null);
		} catch (IllegalArgumentException e) {
			assertEquals(
					"[Assertion failed] - this argument is required; it must not be null",
					e.getMessage());
			throw e;
		}
	}

	@Test
	public void getAttributesPrimaryKey() {

		List<IAttributeType> attributeTypes = TestObjects.getAttributeTypes();

		for (IAttributeType attributeType : attributeTypes) {
			attributeType.setPrimaryKey(false);
		}

		List<IAttribute> expectedAttributes = new ArrayList<IAttribute>();

		attributeTypes.get(0).setPrimaryKey(true);
		attributeTypes.get(2).setPrimaryKey(true);
		expectedAttributes.add(attributeTypes.get(0).createAttribute());
		expectedAttributes.add(attributeTypes.get(2).createAttribute());

		entityType.setAttributeTypes(attributeTypes);
		entity = entityType.getEntity();

		assertEquals(expectedAttributes, entity.getAttributesPrimaryKey());
	}

	@Test
	public void testToString() {

		IAttributeType attributeTypeA = new AttributeType("Attribute A");
		IAttributeType attributeTypeB = new AttributeType("Attribute B");
		IAttributeType attributeTypeC = new AttributeType("Attribute C");

		attributeTypeA.setPrimaryKey(true);
		attributeTypeC.setPrimaryKey(true);

		entityType.addAttributeType(attributeTypeA);
		entityType.addAttributeType(attributeTypeB);
		entityType.addAttributeType(attributeTypeC);

		entity = entityType.getEntity();

		entity.setAttributeValue("Attribute A", "A");
		entity.setAttributeValue("Attribute B", "B");
		entity.setAttributeValue("Attribute C", "C");

		String expectedString = ENTITY_TYPE_NAME
				+ "('Attribute A' = 'A', 'Attribute C' = 'C')";
		assertEquals(expectedString, entity.toString());
	}

	@Test
	public void testGetSetAttributeValue() {
		entityType.addAttributeType(new AttributeType(ATTRIBUTE_NAME));
		entity = entityType.getEntity();
		String value = "Value";
		entity.setAttributeValue(ATTRIBUTE_NAME, value);
		assertEquals(value, entity.getAttributeValue(ATTRIBUTE_NAME));
	}

	@Test(expected = IllegalArgumentException.class)
	public void voidtestGetAttributeValueNullAttribute() {
		try {
			entity.getAttributeValue((IAttribute) null);
		} catch (IllegalArgumentException e) {
			assertEquals(
					"[Assertion failed] - this argument is required; it must not be null",
					e.getMessage());
			throw e;
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void voidtestGetAttributeValueNullAttributeName() {
		try {
			entity.getAttributeValue((String) null);
		} catch (IllegalArgumentException e) {
			assertEquals(
					"[Assertion failed] - this argument is required; it must not be null",
					e.getMessage());
			throw e;
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void voidtestSetAttributeValueNullAttribute() {
		try {
			entity.setAttributeValue((IAttribute) null, new Object());
		} catch (IllegalArgumentException e) {
			assertEquals(
					"[Assertion failed] - this argument is required; it must not be null",
					e.getMessage());
			throw e;
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void voidtestSetAttributeValueNullAttributeName() {
		try {
			entity.setAttributeValue((String) null, new Object());
		} catch (IllegalArgumentException e) {
			assertEquals(
					"[Assertion failed] - this argument is required; it must not be null",
					e.getMessage());
			throw e;
		}
	}

	@Test(expected = AttributeNotFoundException.class)
	public void testGetAttributeValueAttributeNotFound()
			throws AttributeNotFoundException {

		String expectedString = "Couldn't find the attribute 'Attribute'";

		try {
			entity.getAttributeValue(attributeType.createAttribute());
		} catch (AttributeNotFoundException e) {
			assertEquals(expectedString, e.getMessage());
			throw e;
		}
	}

	@Test
	public void testGetAttributesNotNull() {

		entityType.setAttributeTypes(TestObjects.getAttributeTypes());
		entity = entityType.getEntity();

		List<IAttribute> attributes = new ArrayList<IAttribute>();
		List<IAttribute> attributesNotNull = new ArrayList<IAttribute>();

		attributes = entity.getAttributes();

		for (IAttribute attribute : attributes) {
			attribute.setValue(null);
		}

		entity.setAttributeValue(attributes.get(0), "Value");
		entity.setAttributeValue(attributes.get(2), "Value");
		attributesNotNull.add(attributes.get(0));
		attributesNotNull.add(attributes.get(2));

		assertEquals(attributesNotNull, entity.getAttributesNotNull());
	}

	@Test
	public void testGetAttributeValue() {
		IEntityType entityType = new EntityType(ENTITY_TYPE_NAME);
		entityType.addAttributeType(new AttributeType(ATTRIBUTE_NAME));
		IEntity entity = entityType.getEntity();
		entity.setAttributeValue(ATTRIBUTE_NAME, "Value");
		IAttribute attribute = entity.getAttribute(ATTRIBUTE_NAME);
		assertEquals("Value", entity.getAttributeValue(attribute));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetAttributeValueNull() {
		try {
			entity.getAttributeValue((IAttribute) null);
		} catch (IllegalArgumentException e) {
			assertEquals(
					"[Assertion failed] - this argument is required; it must not be null",
					e.getMessage());
			throw e;
		}
	}

	@Test
	public void testGetAttributesPrimaryKeyNoPk() {
		List<IAttributeType> attributeTypes = new ArrayList<IAttributeType>();
		attributeTypes.add(new AttributeType("Attribute Type A"));
		attributeTypes.add(new AttributeType("Attribute Type B"));
		attributeTypes.add(new AttributeType("Attribute Type C"));
		List<IAttribute> expectedAttributes = new ArrayList<IAttribute>();
		for (IAttributeType attributeType : attributeTypes) {
			expectedAttributes.add(new Attribute(attributeType));
		}
		entityType = new EntityType(ENTITY_TYPE_NAME);
		entityType.setAttributeTypes(attributeTypes);
		entity = entityType.getEntity();
		assertEquals(expectedAttributes, entity.getAttributesPrimaryKey());
	}

	@Test
	public void testGetAttributesPrimaryKeyNotNull() {

		IAttributeType attributeTypeA = new AttributeType("Attribute Type A");
		IAttributeType attributeTypeB = new AttributeType("Attribute Type B");
		IAttributeType attributeTypeC = new AttributeType("Attribute Type C");

		List<IAttributeType> attributeTypes = new ArrayList<IAttributeType>();

		attributeTypes.add(attributeTypeA);
		attributeTypes.add(attributeTypeB);
		attributeTypes.add(attributeTypeC);

		entityType = new EntityType(ENTITY_TYPE_NAME);
		entityType.setAttributeTypes(attributeTypes);
		entity = entityType.getEntity();
		entity.setAttributeValue("Attribute Type A", "Value");
		entity.setAttributeValue("Attribute Type C", "Value");

		List<IAttribute> expectedAttributes = new ArrayList<IAttribute>();
		expectedAttributes.add(entity.getAttribute("Attribute Type A"));
		expectedAttributes.add(entity.getAttribute("Attribute Type C"));

		assertEquals(expectedAttributes,
				entity.getAttributesPrimaryKeyNotNull());
	}
}