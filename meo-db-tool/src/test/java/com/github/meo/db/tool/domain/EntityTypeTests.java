package com.github.meo.db.tool.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.meo.db.tool.testsuite.TestObjects;

public class EntityTypeTests {

	private final static String ENTITY_TYPE_NAME = "Entity Class Name";
	private IEntityType entityType;

	@Before
	public void setUp() {
		entityType = new EntityType();
	}

	@Test
	public void newInstanceName() {
		entityType = new EntityType(ENTITY_TYPE_NAME);
	}

	@Test
	public void testAddAttributeType() {

		List<IAttributeType> attributesType = TestObjects.getAttributeTypes();

		assertNotNull(attributesType);

		for (IAttributeType attributeType : attributesType) {
			assertTrue(entityType.addAttributeType(attributeType));
		}

		assertEquals(attributesType, entityType.getAttributeTypes());
	}

	@Test
	public void testAddAttributeNull() {

		List<IAttributeType> attributeTypes = TestObjects.getAttributeTypes();

		entityType.setAttributeTypes(attributeTypes);

		assertFalse(entityType.addAttributeType(null));

		for (IAttributeType attributeType : attributeTypes) {
			assertNotNull(attributeType);
		}

	}

	@Test
	public void testGetSetName() {
		entityType.setName(ENTITY_TYPE_NAME);
		assertEquals(ENTITY_TYPE_NAME, entityType.getName());
	}

	@Test
	public void testGetSetNameNull() {
		entityType.setName(null);
		assertNotNull(entityType.getName());
	}

	@Test
	public void testGetSetAttributes() {
		List<IAttributeType> attributeTypes = TestObjects.getAttributeTypes();
		entityType.setAttributeTypes(attributeTypes);
		assertTrue(attributeTypes == entityType.getAttributeTypes());
	}

	@Test
	public void testGetSetAttributesNull() {
		List<IAttributeType> attributeTypes = TestObjects.getAttributeTypes();
		assertNotNull(attributeTypes);
		entityType.setAttributeTypes(attributeTypes);
		assertTrue(attributeTypes == entityType.getAttributeTypes());
		entityType.setAttributeTypes(null);
		assertNotNull(entityType.getAttributeTypes());
	}

	@Test
	public void testGetEntity() {
		entityType.setName(ENTITY_TYPE_NAME);
		entityType.addAttributeType(new AttributeType("Attribute A"));
		entityType.addAttributeType(new AttributeType("Attribute B"));
		entityType.addAttributeType(new AttributeType("Attribute C"));
		IEntity entity = entityType.getEntity();

		for (int i = 0; i < entityType.getAttributeTypes().size(); i++) {
			IAttributeType attributeType = entityType.getAttributeTypes().get(i);
			IAttribute attribute = entity.getAttributes().get(i);
			assertEquals(attributeType, attribute.getAttributeType());
		}
	}

	@Test
	public void getAttributeTypesPrimaryKey() {

		List<IAttributeType> expectedAttributeTypes = new ArrayList<IAttributeType>();

		expectedAttributeTypes.add(TestObjects.getAttributeTypeA());
		expectedAttributeTypes.add(TestObjects.getAttributeTypeC());

		entityType.setAttributeTypes(TestObjects.getAttributeTypes());

		assertEquals(expectedAttributeTypes,
				entityType.getAttributeTypesPrimaryKey());
	}

	@Test
	public void testGetAttributeTypesPrimaryKeyNoPk() {

		List<IAttributeType> attributeTypes = TestObjects.getAttributeTypes();

		for (IAttributeType attributeType : attributeTypes) {
			attributeType.setPrimaryKey(false);
		}

		entityType.setAttributeTypes(attributeTypes);

		assertEquals(attributeTypes, entityType.getAttributeTypesPrimaryKey());
	}
	
	@Test
	public void testToString() {
		entityType.setName(ENTITY_TYPE_NAME);
		assertEquals(ENTITY_TYPE_NAME, entityType.toString());
	}

	@Test
	public void testEqualsNull() {
		assertFalse(entityType.equals(null));
	}

	@Test
	public void testEqualsSameObjects() {
		assertTrue(entityType.equals(entityType));
	}

	@Test
	public void testEqualsDifferentClass() {
		assertFalse(entityType.equals(new Object()));
	}

	@Test
	public void testEqualsDifferentName() {
		IEntityType differentEntityType = entityType.clone();
		entityType.setName("Name");
		differentEntityType.setName("Different name");
		assertFalse(entityType.equals(differentEntityType));
	}

	@Test
	public void testEqualsDifferentAttributeTypesSize() {
		IEntityType differentEntityType = entityType.clone();
		entityType.addAttributeType(new AttributeType());
		entityType.addAttributeType(new AttributeType());
		entityType.addAttributeType(new AttributeType());
		differentEntityType.addAttributeType(new AttributeType());
		differentEntityType.addAttributeType(new AttributeType());
		assertFalse(entityType.equals(differentEntityType));
	}

	@Test
	public void testEqualsDifferentAttributeTypes() {
		entityType.addAttributeType(new AttributeType("Attribute A"));
		entityType.addAttributeType(new AttributeType("Attribute B"));
		IEntityType differentEntityType = entityType.clone();
		entityType.addAttributeType(new AttributeType("Attribute"));
		differentEntityType.addAttributeType(new AttributeType(
				"Different attribute"));
		assertFalse(entityType.equals(differentEntityType));
	}

	@Test
	public void testEqualObjects() {
		entityType.setName(ENTITY_TYPE_NAME);
		IEntityType differentEntityType = entityType.clone();
		assertTrue(entityType.equals(differentEntityType));
	}

	@Test
	public void testGetAttributeType() {
		entityType.setAttributeTypes(TestObjects.getAttributeTypes());
		assertEquals(TestObjects.getAttributeTypeA(),
				entityType.getAttributeType("Attribute A"));
		assertEquals(TestObjects.getAttributeTypeB(),
				entityType.getAttributeType("Attribute B"));
		assertEquals(TestObjects.getAttributeTypeC(),
				entityType.getAttributeType("Attribute C"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetAttributeTypeNull() {
		try {
			entityType.getAttributeType(null);
		} catch (IllegalArgumentException e) {
			assertEquals("The given attribute name is null!", e.getMessage());
			throw e;
		}

	}
}
