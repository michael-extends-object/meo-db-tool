package com.github.meo.db.tool.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.github.meo.db.tool.testsuite.TestObjects;

public class RelationshipTypeTests {

	IRelationshipType relationshipType;
	IRelationshipType differentRelationshipType;
	IEntityType entityType;

	public final static String RELATIONSHIP_NAME = "Relationship Name";
	String expectedString;
	String actualString;

	@Before
	public void setUp() {
		relationshipType = new RelationshipType();
		entityType = new EntityType();
	}

	@Test
	public void testNewInstanceEntityTypeEntityType() {
		IEntityType entityType = new EntityType("Entity");
		IEntityType referencedEntityType = new EntityType("Referenced Entity");
		relationshipType = new RelationshipType(entityType,
				referencedEntityType);
		assertEquals(entityType, relationshipType.getEntityType());
		assertEquals(referencedEntityType,
				relationshipType.getReferencedEntityType());
	}

	@Test
	public void testNewInstanceStringEntityTypeEntityTypeCardinality() {
		IEntityType entityType = new EntityType("Entity");
		IEntityType referencedEntityType = new EntityType("Referenced Entity");
		Cardinality cardinality = Cardinality.OneToMany;
		relationshipType = new RelationshipType(RELATIONSHIP_NAME, entityType,
				referencedEntityType, cardinality);
		assertEquals(RELATIONSHIP_NAME, relationshipType.getName());
		assertEquals(entityType, relationshipType.getEntityType());
		assertEquals(referencedEntityType,
				relationshipType.getReferencedEntityType());
		assertEquals(cardinality, relationshipType.getCardinality());
	}

	@Test
	public void testSetGetEntityType() {
		relationshipType.setEntityType(entityType);
		assertEquals(entityType, relationshipType.getEntityType());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetGetEntityTypeNull() {
		try {
			relationshipType.setEntityType(null);
		} catch (IllegalArgumentException e) {
			assertEquals(
					"[Assertion failed] - this argument is required; it must not be null",
					e.getMessage());
			throw e;
		}
	}

	@Test
	public void testSetGetReferencedEntityType() {
		relationshipType.setReferencedEntityType(entityType);
		assertTrue(entityType == relationshipType.getReferencedEntityType());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetGetReferencedEntityNull() {
		try {
			relationshipType.setReferencedEntityType(null);
		} catch (IllegalArgumentException e) {
			assertEquals(
					"[Assertion failed] - this argument is required; it must not be null",
					e.getMessage());
			assertNotNull(relationshipType.getReferencedEntityType());
			throw e;
		}
	}

	@Test
	public void testSetGetCardinality() {
		Cardinality cardinality = Cardinality.OneToMany;
		relationshipType.setCardinality(cardinality);
		assertEquals(cardinality, relationshipType.getCardinality());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetGetCardinalityNull() {
		try {
			relationshipType.setCardinality(null);
		} catch (IllegalArgumentException e) {
			assertEquals(
					"[Assertion failed] - this argument is required; it must not be null",
					e.getMessage());
			assertNotNull(relationshipType.getCardinality());
			throw e;
		}
	}

	@Test
	public void testNewInstanceName() {

		String name = RELATIONSHIP_NAME;

		relationshipType = new RelationshipType(name);

		assertTrue(name == relationshipType.getName());
	}

	@Test
	public void testToStringNamedEntity() {
		String name = RELATIONSHIP_NAME;
		relationshipType.setName(name);
		assertEquals(name, relationshipType.toString());
	}

	@Test
	public void testEqualsNull() {
		assertFalse(relationshipType.equals(null));
	}

	@Test
	public void testEqualsSameObject() {
		assertTrue(relationshipType.equals(relationshipType));
	}

	@Test
	public void testEqualsDifferentClass() {
		assertFalse(relationshipType.equals(new Object()));
	}

	@Test
	public void testEqualsDifferentName() {
		differentRelationshipType = relationshipType.clone();
		differentRelationshipType.setName("Different name");
		assertFalse(relationshipType.equals(differentRelationshipType));
	}

	@Test
	public void equalsDifferentEntityType() {
		for (IRelationshipType relationshipType : TestObjects
				.getRelationshipTypes()) {
			IRelationshipType clone = relationshipType.clone();
			clone.setEntityType(new EntityType("Different entity"));
			assertFalse(relationshipType.equals(clone));
		}
	}

	@Test
	public void equalsDifferentReferencedEntity() {
		for (IRelationshipType relationshipType : TestObjects
				.getRelationshipTypes()) {
			IRelationshipType clonedRelationshipType = relationshipType.clone();
			relationshipType.setReferencedEntityType(new EntityType("Entity"));
			clonedRelationshipType.setReferencedEntityType(new EntityType(
					"Different Entity"));
			assertFalse(relationshipType.equals(clonedRelationshipType));
		}
	}

	@Test
	public void testEqualsDifferentCardinality() {
		IRelationshipType differentRelationshipType = relationshipType.clone();
		relationshipType.setCardinality(Cardinality.OneToMany);
		assertFalse(relationshipType.equals(differentRelationshipType));
	}

	@Test
	public void testEqualsEqualObject() {
		IRelationshipType clonedRelationship = relationshipType.clone();
		assertTrue(relationshipType.equals(clonedRelationship));
	}

	@Test
	public void testClone() {
		IRelationship relationship = TestObjects.getRelationshipA();
		IRelationship clonedRelationship = relationship.clone();
		assertNotSame(relationship, clonedRelationship);
		assertEquals(relationship, clonedRelationship);
	}

	@Test
	public void testToString() {
		relationshipType.setEntityType(new EntityType("Entity Type A"));
		relationshipType
				.setReferencedEntityType(new EntityType("Entity Type B"));
		expectedString = "'Relationship Entity Type A to Entity Type B'";
		actualString = relationshipType.toString();
		assertEquals(expectedString, actualString);
	}

	@Test
	public void testGetSetName() {
		relationshipType.setName(RELATIONSHIP_NAME);
		assertEquals(RELATIONSHIP_NAME, relationshipType.getName());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetSetNameNull() {
		try {
			relationshipType.setName(null);
		} catch (IllegalArgumentException e) {
			assertEquals(
					"[Assertion failed] - this argument is required; it must not be null",
					e.getMessage());
			assertNotNull(relationshipType.getName());
			throw e;
		}
	}

	@Test
	public void testGetSetReferencedEntity() {
		for (IEntityType referencedEntityType : TestObjects.getEntityTypes()) {
			relationshipType.setReferencedEntityType(referencedEntityType);
			assertTrue(referencedEntityType == relationshipType
					.getReferencedEntityType());
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetSetReferencedEntityNull() {
		try {
			relationshipType.setReferencedEntityType(null);
		} catch (IllegalArgumentException e) {
			assertEquals(
					"[Assertion failed] - this argument is required; it must not be null",
					e.getMessage());
			assertNotNull(relationshipType.getReferencedEntityType());
			throw e;
		}
	}

	@Test
	public void testGetSetCardinality() {
		for (Cardinality referencedEntity : Cardinality.values()) {
			relationshipType.setCardinality(referencedEntity);
			assertTrue(referencedEntity == relationshipType.getCardinality());
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetSetCardinalityNull() {
		try {
			relationshipType.setCardinality(null);
		} catch (IllegalArgumentException e) {
			assertEquals(
					"[Assertion failed] - this argument is required; it must not be null",
					e.getMessage());
			assertNotNull(relationshipType.getCardinality());
			throw e;
		}
	}

	@Test
	public void testGetRelationship() {
		relationshipType.setName(RELATIONSHIP_NAME);
		IRelationship relationship = relationshipType.getRelationship();
		assertEquals(relationshipType, relationship.getRelationshipType());
	}
}