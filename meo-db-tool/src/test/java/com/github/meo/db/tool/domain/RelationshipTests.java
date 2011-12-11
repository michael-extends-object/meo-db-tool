package com.github.meo.db.tool.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.meo.db.tool.testsuite.TestObjects;

public class RelationshipTests {

	IRelationship relationship;
	IRelationship differentRelationship;
	IEntityType entityType;

	public final static String RELATIONSHIP_NAME = "Relationship Name";
	String expectedString;
	String actualString;

	@Before
	public void setUp() {
		relationship = new Relationship();
		entityType = new EntityType();
	}

	@Test
	public void testSetGetEntityType() {
		relationship.setEntity(entityType);
		assertEquals(entityType, relationship.getEntity());
	}

	@Test
	public void testSetGetEntityTypeNull() {
		relationship.setEntity(null);
		assertNotNull(relationship.getEntity());
	}

	@Test
	public void testSetGetReferencedEntityType() {
		relationship.setReferencedEntity(entityType);
		assertTrue(entityType == relationship.getReferencedEntity());
	}

	@Test
	public void testSetGetReferencedEntityNull() {
		relationship.setReferencedEntity(null);
		assertNotNull(relationship.getReferencedEntity());
	}

	@Test
	public void testSetGetCardinality() {
		Cardinality cardinality = Cardinality.OneToMany;
		relationship.setCardinality(cardinality);
		assertEquals(cardinality, relationship.getCardinality());
	}

	@Test
	public void testSetGetCardinalityNull() {
		relationship.setCardinality(null);
		assertNotNull(relationship.getCardinality());
	}

	@Test
	public void testNewInstanceName() {

		String name = RELATIONSHIP_NAME;

		relationship = new Relationship(name);

		assertTrue(name == relationship.getName());
	}

	@Test
	public void testNewInstanceEntityReferencedEntityCardinality() {

		IEntity referencedEntity = TestObjects.getEntityB();
		Cardinality cardinality = Cardinality.ManyToMany;

		relationship = new Relationship(referencedEntity, cardinality);

		assertTrue(referencedEntity == relationship.getReferencedEntity());
		assertTrue(cardinality == relationship.getCardinality());
	}

	@Test
	public void testNewInstanceNameEntityReferencedEntityCardinality() {

		String name = RELATIONSHIP_NAME;
		IEntity entity = TestObjects.getEntityA();
		IEntity referencedEntity = TestObjects.getEntityB();
		Cardinality cardinality = Cardinality.ManyToMany;

		relationship = new Relationship(name, entity, referencedEntity,
				cardinality);

		assertTrue(name == relationship.getName());
		assertTrue(referencedEntity == relationship.getReferencedEntity());
		assertTrue(cardinality == relationship.getCardinality());
	}

	@Test
	public void testToStringNamedEntity() {
		String name = RELATIONSHIP_NAME;
		relationship.setName(name);
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
	public void testEqualsDifferentName() {
		differentRelationship = relationship.clone();
		differentRelationship.setName("Different name");
		assertFalse(relationship.equals(differentRelationship));
	}

	@Test
	public void equalsDifferentEntity() {
		for (IRelationship relationship : TestObjects.getRelationships()) {
			IRelationship clonedRelationship = relationship.clone();
			clonedRelationship
					.setEntity(new EntityType("Different entity"));
			assertFalse(relationship.equals(clonedRelationship));
		}
	}

	@Test
	public void testEqualsDifferentReferencedEntitiesSize() {
		differentRelationship = relationship.clone();
		differentRelationship.addReferencedEntity(new Entity());
		assertFalse(relationship.equals(differentRelationship));
	}
	
	@Test
	public void equalsDifferentReferencedEntity() {
		for (IRelationship relationship : TestObjects.getRelationships()) {
			IRelationship clonedRelationship = relationship.clone();
			relationship.setReferencedEntity("Entity");
			clonedRelationship.setReferencedEntity("Different Entity");
			assertFalse(relationship.equals(clonedRelationship));
		}
	}

	@Test
	public void equalsDifferentReferencedEntities() {
		for (IRelationship relationship : TestObjects.getRelationships()) {
			IRelationship clonedRelationship = relationship.clone();
			relationship.addReferencedEntity("Entity");
			clonedRelationship.addReferencedEntity("Different Entity");
			assertFalse(relationship.equals(clonedRelationship));
		}
	}

	@Test
	public void testEqualsDifferentCardinality() {
		IRelationship differentRelationship = relationship.clone();
		relationship.setCardinality(Cardinality.OneToMany);
		assertFalse(relationship.equals(differentRelationship));
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

		IEntity referencedEntity = TestObjects.getEntityB();

		relationship.setReferencedEntity(referencedEntity);

		expectedString = "'Relationship to Entity B'";
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

	@Test
	public void testAddReferencedEntityNull() {
		assertFalse(relationship.addReferencedEntity(null));
		for (Object referencedEntity : relationship.getReferencedEntities()) {
			assertNotNull(referencedEntity);
		}
	}

	@Test
	public void testAddReferencedEntities() {
		List<Object> referencedEntities = new ArrayList<Object>();

		for (IEntity entity : TestObjects.getEntities()) {
			referencedEntities.add(entity);
		}

		int initialSize = relationship.getReferencedEntities().size();

		assertTrue(relationship.addReferencedEntities(referencedEntities));

		for (int i = 0; i < relationship.getReferencedEntities().size(); i++) {
			assertEquals(referencedEntities.get(i), relationship
					.getReferencedEntities().get(i + initialSize));
		}
	}

	@Test
	public void testaddReferencedEntitiesExpectFalse() {
		List<Object> referencedEntities = new ArrayList<Object>();
		referencedEntities.add(new Entity());
		referencedEntities.add(null);
		referencedEntities.add(new Entity());
		assertFalse(relationship.addReferencedEntities(referencedEntities));
	}

	@Test
	public void testAddReferencedEntitiesNull() {
		assertFalse(relationship.addReferencedEntities(null));
		for (Object referencedEntity : relationship.getReferencedEntities()) {
			assertNotNull(referencedEntity);
		}
	}

	@Test
	public void testGetSetName() {
		relationship.setName(RELATIONSHIP_NAME);
		assertTrue(RELATIONSHIP_NAME == relationship.getName());
	}

	@Test
	public void testGetSetNameNull() {
		relationship.setName(null);
		assertNotNull(relationship.getName());
	}

	@Test
	public void testGetSetReferencedEntity() {
		for (IEntity referencedEntity : TestObjects.getEntities()) {
			relationship.setReferencedEntity(referencedEntity);
			assertTrue(referencedEntity == relationship.getReferencedEntity());
		}
	}

	@Test
	public void testGetSetReferencedEntityNull() {
		relationship.setReferencedEntity(null);
		assertNotNull(relationship.getReferencedEntity());
	}

	@Test
	public void testGetSetReferencedEntities() {
		List<Object> referencedEntities = new ArrayList<Object>();

		for (IEntity entity : TestObjects.getEntities()) {
			referencedEntities.add(entity);
		}

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

	@Test
	public void testGetSetCardinality() {
		for (Cardinality referencedEntity : Cardinality.values()) {
			relationship.setCardinality(referencedEntity);
			assertTrue(referencedEntity == relationship.getCardinality());
		}
	}

	@Test
	public void testGetSetCardinalityNull() {
		relationship.setCardinality(null);
		assertNotNull(relationship.getCardinality());
	}
}