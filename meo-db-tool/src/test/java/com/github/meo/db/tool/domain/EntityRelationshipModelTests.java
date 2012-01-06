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

public class EntityRelationshipModelTests {

	private final static String ERM_NAME = "ERM Name";
	private IEntityRelationshipModel erm;

	@Before
	public void setUp() {
		erm = new EntityRelationshipModel();
	}

	@Test
	public void testNewInstanceString() {
		erm = new EntityRelationshipModel(ERM_NAME);
		assertEquals(ERM_NAME, erm.getName());
	}

	@Test
	public void testAddEntityType() {

		List<IEntityType> entityTypes = TestObjects.getEntityTypes();

		for (IEntityType entityType : entityTypes) {
			erm.addEntityType(entityType);
		}

		assertEquals(entityTypes, erm.getEntityTypes());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddEntityTypeNull() {
		try {
			erm.addEntityType(null);
		} catch (IllegalArgumentException e) {
			assertEquals(
					"[Assertion failed] - this argument is required; it must not be null",
					e.getMessage());

			for (IEntityType entityType : erm.getEntityTypes()) {
				assertNotNull(entityType);
			}

			throw e;
		}
	}

	@Test
	public void testAddRelationshipTypes() {
		List<IRelationshipType> relationshipTypes = TestObjects
				.getRelationshipTypes();
		for (IRelationshipType relationshipType : relationshipTypes) {
			erm.addRelationshipType(relationshipType);
		}
		assertEquals(relationshipTypes, erm.getRelationshipTypes());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddRelationshipNull() {
		try {
			erm.addRelationshipType(null);
		} catch (IllegalArgumentException e) {
			assertEquals(
					"[Assertion failed] - this argument is required; it must not be null",
					e.getMessage());

			for (IRelationshipType relationshipType : erm
					.getRelationshipTypes()) {
				assertNotNull(relationshipType);
			}

			throw e;
		}
	}

	@Test
	public void testSetName() {
		erm.setName(ERM_NAME);
		assertEquals(ERM_NAME, erm.getName());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetNameNull() {

		try {
			erm.setName(null);
		} catch (IllegalArgumentException e) {
			assertNotNull(erm.getName());
			assertEquals(
					"[Assertion failed] - this argument is required; it must not be null",
					e.getMessage());
			throw e;
		}
	}

	@Test
	public void testSetRelationshipTypes() {
		List<IRelationshipType> relationshipTypes = TestObjects
				.getRelationshipTypes();
		erm.setRelationshipTypes(relationshipTypes);
		assertEquals(relationshipTypes, erm.getRelationshipTypes());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetRelationshipsNull() {

		try {
			erm.setRelationshipTypes(null);
		} catch (IllegalArgumentException e) {
			assertNotNull(erm.getRelationshipTypes());
			assertEquals(
					"[Assertion failed] - this argument is required; it must not be null",
					e.getMessage());
			throw e;
		}
	}

	@Test
	public void testSetEntityTypes() {
		List<IEntityType> entityTypes = getEntityTypes();
		erm.setEntityTypes(entityTypes);
		assertEquals(entityTypes, erm.getEntityTypes());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetEntityTypesNull() {

		try {
			erm.setEntityTypes(null);
		} catch (IllegalArgumentException e) {
			assertNotNull(erm.getEntityTypes());
			assertEquals(
					"[Assertion failed] - this argument is required; it must not be null",
					e.getMessage());
			throw e;
		}

	}

	private List<IEntityType> getEntityTypes() {
		List<IEntityType> entityTypes = new ArrayList<IEntityType>();
		IEntityType entityTypeA = new EntityType("Entity Class A");
		IEntityType entityTypeB = new EntityType("Entity Class B");
		IEntityType entityTypeC = new EntityType("Entity Class C");
		entityTypes.add(entityTypeA);
		entityTypes.add(entityTypeB);
		entityTypes.add(entityTypeC);
		return entityTypes;
	}

	@Test
	public void testToString() {
		erm.setName(ERM_NAME);
		assertEquals(ERM_NAME, erm.toString());
	}

	@Test
	public void testEqualsNull() {
		assertFalse(erm.equals(null));
	}

	@Test
	public void testEqualsSameObject() {
		assertTrue(erm.equals(erm));
	}

	@Test
	public void testEqualsDifferentClass() {
		assertFalse(erm.equals(new Object()));
	}

	@Test
	public void testEqualsDifferentName() {
		IEntityRelationshipModel differentErm = erm.clone();
		erm.setName(ERM_NAME);
		differentErm.setName("Different Name");
		assertFalse(erm.equals(differentErm));
	}

	@Test
	public void testEqualsDifferentEntityTypesSize() {
		IEntityRelationshipModel differentErm = erm.clone();
		erm.addEntityType(new EntityType());
		erm.addEntityType(new EntityType());
		differentErm.addEntityType(new EntityType());
		assertFalse(erm.equals(differentErm));
	}

	@Test
	public void testEqualsDifferentEntityTypes() {
		IEntityRelationshipModel differentErm = erm.clone();
		erm.addEntityType(new EntityType("Entity Type"));
		differentErm.addEntityType(new EntityType("Different Entity Type"));
		assertFalse(erm.equals(differentErm));
	}

	@Test
	public void testEqualsDifferentRelationshipTypesSize() {
		IEntityRelationshipModel differentErm = erm.clone();
		erm.addRelationshipType(new RelationshipType());
		erm.addRelationshipType(new RelationshipType());
		differentErm.addRelationshipType(new RelationshipType());
		assertFalse(erm.equals(differentErm));
	}

	@Test
	public void testEqualsDifferentRelationshipTypes() {
		IEntityRelationshipModel differentErm = erm.clone();
		erm.addRelationshipType(new RelationshipType("Relationship Type"));
		differentErm.addRelationshipType(new RelationshipType(
				"Different Relationship Type"));
		assertFalse(erm.equals(differentErm));
	}

	@Test
	public void testEqualsClone() {
		IEntityRelationshipModel differentErm = erm.clone();
		assertTrue(erm.equals(differentErm));
	}

	@Test
	public void testClone() {
		erm.addEntityType(new EntityType());
		erm.addRelationshipType(new RelationshipType());
		IEntityRelationshipModel differentErm = erm.clone();
		assertTrue(erm.equals(differentErm));
	}
}