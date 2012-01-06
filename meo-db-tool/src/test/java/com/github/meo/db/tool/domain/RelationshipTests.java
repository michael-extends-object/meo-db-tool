package com.github.meo.db.tool.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.meo.db.tool.testsuite.TestObjects;

public class RelationshipTests {

	private IRelationshipType relationshipType;
	private IRelationship relationship;
	private IRelationship differentRelationship;
	private IEntity entity;
	private final static String RELATIONSHIP_NAME = "Relationship Name";
	private String expectedString;
	private String actualString;
	private EntityType entityType;

	@Before
	public void setUp() {
		relationshipType = new RelationshipType(RELATIONSHIP_NAME);
		entityType = new EntityType();
		relationship = relationshipType.getRelationship();
		entity = entityType.getEntity();
	}

	@Test
	public void testSetGetEntityType() {
		relationship.setEntity(entity);
		assertEquals(entity, relationship.getEntity());
	}

	@Test
	public void testSetGetEntityTypeNull() {
		relationship.setEntity(null);
		assertNotNull(relationship.getEntity());
	}

	@Test
	public void testToStringNamedEntity() {
		String name = RELATIONSHIP_NAME;
		assertEquals(name, relationship.toString());
	}

	@Test
	public void testEqualsNull() {
		assertFalse(relationship.equals(null));
	}

	@Test
	public void testEqualsSameObject() {
		assertTrue(relationship.equals(relationship));
	}

	@Test
	public void testEqualsDifferentClass() {
		assertFalse(relationship.equals(new Object()));
	}

	@Test
	public void equalsDifferentEntity() {
		for (IRelationship relationship : TestObjects.getRelationships()) {
			IRelationship clonedRelationship = relationship.clone();
			clonedRelationship.setEntity((new EntityType("Different entity"))
					.getEntity());
			assertFalse(relationship.equals(clonedRelationship));
		}
	}

	@Test
	public void testEqualsDifferentReferencedEntitiesSize() {
		differentRelationship = relationship.clone();
		differentRelationship.addReferencedEntity(entity);
		assertFalse(relationship.equals(differentRelationship));
	}

	@Test
	public void equalsDifferentReferencedEntities() {
		for (IRelationship relationship : TestObjects.getRelationships()) {
			IRelationship clonedRelationship = relationship.clone();
			relationship.addReferencedEntity((new EntityType("A")).getEntity());
			clonedRelationship.addReferencedEntity((new EntityType("B")).getEntity());
			assertFalse(relationship.equals(clonedRelationship));
		}
	}

	@Test
	public void testEqualsEqualObject() {
		IRelationship clonedRelationship = relationship.clone();
		assertTrue(relationship.equals(clonedRelationship));
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
		expectedString = "Relationship Name";
		actualString = relationship.toString();
		assertEquals(expectedString, actualString);
	}

	@Test
	public void testAddReferencedEntity() {
		IEntity referencedEntity = TestObjects.getEntityB();
		assertTrue(relationship.addReferencedEntity(referencedEntity));
		assertEquals(referencedEntity, relationship.getReferencedEntities()
				.get(0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddReferencedEntityNull()  {
		try {
			relationship.addReferencedEntity(null);
		} catch (IllegalArgumentException e) {
			assertEquals("[Assertion failed] - this argument is required; it must not be null", e.getMessage());
			
			for (Object referencedEntity : relationship.getReferencedEntities()) {
				assertNotNull(referencedEntity);
			}
			
			throw e;
		}
	}

	@Test
	public void testAddReferencedEntities() {
		List<IEntity> referencedEntities = TestObjects.getEntities();

		int initialSize = relationship.getReferencedEntities().size();

		relationship.addReferencedEntities(referencedEntities);

		for (int i = 0; i < relationship.getReferencedEntities().size(); i++) {
			assertEquals(referencedEntities.get(i), relationship
					.getReferencedEntities().get(i + initialSize));
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddReferencedEntitiesNull() {
		try {
			relationship.addReferencedEntities(null);	
		} catch(IllegalArgumentException e) {
		
			assertEquals("[Assertion failed] - this argument is required; it must not be null", e.getMessage());
			
			for (Object referencedEntity : relationship.getReferencedEntities()) {
				assertNotNull(referencedEntity);
			}
			
			throw e;
		}
	}

	@Test
	public void testGetSetReferencedEntities() {
		List<IEntity> referencedEntities = TestObjects.getEntities();
		relationship.setReferencedEntities(referencedEntities);
		assertEquals(referencedEntities, relationship.getReferencedEntities());
	}

	@Test
	public void testSetReferencedEntitiesNull() {
		relationship.setReferencedEntities(null);
		for (Object referencedEntity : relationship.getReferencedEntities()) {
			assertNotNull(referencedEntity);
		}
	}
}