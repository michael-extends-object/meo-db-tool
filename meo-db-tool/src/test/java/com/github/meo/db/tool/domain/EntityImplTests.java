package com.github.meo.db.tool.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.meo.db.tool.testsuite.TestObjects;

public class EntityImplTests {

	private final static String ENTITY_NAME = "Entity Name";

	private Entity entity;

	@Before
	public void setUp() {

		entity = new EntityImpl();
	}

	@Test
	public void createInstanceA() {

		entity = new EntityImpl(ENTITY_NAME);

		assertEquals(ENTITY_NAME, entity.getName());
	}

	@Test
	public void nestedEntities() {

		Entity nestedEntity = new EntityImpl("Nested Entity");

		entity.addAttribute(nestedEntity);

		assertEquals(nestedEntity, entity.getAttribute(nestedEntity.getName()));

	}

	@Test
	public void getSetName() {

		entity.setName(ENTITY_NAME);

		assertEquals(ENTITY_NAME, entity.getName());
	}

	@Test
	public void getSetAttributes() {

		List<Attribute> attributes = TestObjects.getAttributes();

		entity.setAttributes(attributes);

		assertEquals(attributes, entity.getAttributes());
	}

	@Test
	public void addAttribute() {

		List<Attribute> attributes = TestObjects.getAttributes();

		for (int i = 0; i < attributes.size(); i++) {
			entity.addAttribute(attributes.get(i));
		}

		List<Attribute> attributesActual = entity.getAttributes();

		for (int i = 0; i < attributesActual.size(); i++) {
			assertEquals(attributes.get(i), attributesActual.get(i));
		}
	}

	@Test
	public void getAtrribute() {

		List<Attribute> attributes = TestObjects.getAttributes();

		String attributeName = attributes.get(1).getName();

		for (int i = 0; i < attributes.size(); i++) {
			entity.addAttribute(attributes.get(i));
		}

		assertEquals(attributeName, entity.getAttribute(attributeName)
				.getName());
	}

	@Test
	public void cloneEntity() {

		Entity clonedEntity;

		for (Entity entity : TestObjects.getEntities()) {

			clonedEntity = (Entity) entity.clone();

			assertEquals(entity.getName(), clonedEntity.getName());

			for (int i = 0; i < entity.getAttributes().size(); i++) {
				assertTrue(entity.getAttributes().get(i)
						.equals(clonedEntity.getAttributes().get(i)));
			}
		}
	}

	@Test
	public void equalsNull() {
		for (Entity entity : TestObjects.getEntities()) {
			assertFalse(entity.equals(null));
		}
	}

	@Test
	public void equalsSameObject() {
		for (Entity entity : TestObjects.getEntities()) {
			assertTrue(entity.equals(entity));
		}
	}

	@Test
	public void equalsClonedObject() {
		for (Entity entity : TestObjects.getEntities()) {
			assertTrue(entity.equals(entity.clone()));
		}
	}

	@Test
	public void equalsDifferentClass() {
		for (Entity entity : TestObjects.getEntities()) {
			entity.equals(new Object());
		}
	}

	@Test
	public void equalsA() {

		Entity entityClone;

		for (Entity entity : TestObjects.getEntities()) {
			entityClone = (Entity) entity.clone();
			entityClone.setName(null);
			entity.equals(entityClone);
		}
	}

	@Test
	public void equalsB() {

		Entity entityClone;

		for (Entity entity : TestObjects.getEntities()) {
			entityClone = (Entity) entity.clone();
			entityClone.setAttributes(null);
			entity.equals(entityClone);
		}
	}

	@Test
	public void equalsC() {

		Entity entityClone;

		for (Entity entity : TestObjects.getEntities()) {
			entityClone = (Entity) entity.clone();
			entityClone.getAttributes().get(1).setValue("Modified value");
			entity.equals(entityClone);
		}
	}

	@Test
	public void equals() {

		Entity modifiedEntity;

		for (Entity entity : TestObjects.getEntities()) {
			modifiedEntity = (Entity) entity.clone();
			modifiedEntity.setName("Modified name");
			assertFalse(entity.equals(modifiedEntity));
		}

		for (Entity entity : TestObjects.getEntities()) {
			modifiedEntity = (Entity) entity.clone();
			modifiedEntity.setAttributes(new ArrayList<Attribute>());
			assertFalse(entity.equals(modifiedEntity));
		}

		for (Entity entity : TestObjects.getEntities()) {
			modifiedEntity = (Entity) entity.clone();
			modifiedEntity.setName("Modified name");
			modifiedEntity.setAttributes(new ArrayList<Attribute>());
			assertFalse(entity.equals(modifiedEntity));
		}

		for (Entity entity : TestObjects.getEntities()) {
			entity.setName(null);
			entity.setAttributes(null);
			assertTrue(entity.equals(entity));
			assertTrue(entity.equals(entity.clone()));

			modifiedEntity = (Entity) entity.clone();
			modifiedEntity.setName("Modified name");
			assertFalse(entity.equals(modifiedEntity));

			modifiedEntity.setName(null);
			List<Attribute> attributes = new ArrayList<Attribute>();
			attributes.add(new AttributeImpl("Attribute"));
			modifiedEntity.setAttributes(attributes);
			assertFalse(entity.equals(modifiedEntity));
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void getAtrributeNullArgument() {

		List<Attribute> attributes = TestObjects.getAttributes();

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
	public void getSetObject() {
		Object object = new Object();
		entity.setValue(object);
		assertSame(object, entity.getValue());
	}
}