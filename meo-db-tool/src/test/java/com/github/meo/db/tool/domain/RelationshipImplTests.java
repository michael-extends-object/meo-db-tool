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

public class RelationshipImplTests {

	Relationship relationship;

	public final static String RELATIONSHIP_NAME = "Relationship Name";
	String expectedString;
	String actualString;

	@Before
	public void setUp() throws Exception {
		relationship = new RelationshipImpl();
	}

	@Test
	public void testNewInstanceName() {

		String name = RELATIONSHIP_NAME;

		relationship = new RelationshipImpl(name);

		assertTrue(name == relationship.getName());
	}

	@Test
	public void testNewInstanceEntityReferencedEntityCardinality() {

		Entity referencedEntity = TestObjects.getEntityB();
		Cardinality cardinality = Cardinality.ManyToMany;

		relationship = new RelationshipImpl(referencedEntity, cardinality);

		assertTrue(referencedEntity == relationship.getReferencedEntity());
		assertTrue(cardinality == relationship.getCardinality());
	}

	@Test
	public void testNewInstanceNameEntityReferencedEntityCardinality() {

		String name = RELATIONSHIP_NAME;
		Entity entity = TestObjects.getEntityA();
		Entity referencedEntity = TestObjects.getEntityB();
		Cardinality cardinality = Cardinality.ManyToMany;

		relationship = new RelationshipImpl(name, entity, referencedEntity,
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
		Relationship differentRelationship = relationship.clone();
		differentRelationship.setName("Different name");
		assertFalse(relationship.equals(differentRelationship));
	}

	@Test
	public void testEqualsDifferentReferencedEntity() {
		Relationship differentRelationship = relationship.clone();
		differentRelationship.setReferencedEntity(new EntityImpl(
				"Different entity"));
		assertFalse(relationship.equals(differentRelationship));
	}

	@Test
	public void testEqualsDifferentReferencedEntitiesSize() {
		Relationship differentRelationship = relationship.clone();
		differentRelationship.addReferencedEntity(new EntityImpl());
		assertFalse(relationship.equals(differentRelationship));
	}

	@Test
	public void testEqualsDifferentReferencedEntities() {
		Relationship differentRelationship = relationship.clone();
		relationship.addReferencedEntity(new EntityImpl("Entity"));
		differentRelationship.addReferencedEntity(new EntityImpl(
				"Different entity"));
		assertFalse(relationship.equals(differentRelationship));
	}

	@Test
	public void testEqualsDifferentCardinality() {
		Relationship differentRelationship = relationship.clone();
		relationship.setCardinality(Cardinality.OneToMany);
		assertFalse(relationship.equals(differentRelationship));
	}

	@Test
	public void testEqualsEqualObject() {
		Relationship clonedRelationship = relationship.clone();
		assertTrue(relationship.equals(clonedRelationship));
	}

	@Test
	public void testClone() {
		Relationship relationship = TestObjects.getRelationshipA();
		Relationship clonedRelationship = relationship.clone();
		assertNotSame(relationship, clonedRelationship);
		assertEquals(relationship, clonedRelationship);
	}

	@Test
	public void testToString() {

		Entity referencedEntity = TestObjects.getEntityB();

		relationship.setReferencedEntity(referencedEntity);

		expectedString = "'Relationship to Entity B'";
		actualString = relationship.toString();
		assertEquals(expectedString, actualString);
	}

	@Test
	public void testAddReferencedEntity() {
		Entity referencedEntity = TestObjects.getEntityB();
		assertTrue(relationship.addReferencedEntity(referencedEntity));
		assertEquals(referencedEntity, relationship.getReferencedEntities()
				.get(0));
	}

	@Test
	public void testAddReferencedEntityNull() {
		assertFalse(relationship.addReferencedEntity(null));
		for (Entity referencedEntity : relationship.getReferencedEntities()) {
			assertNotNull(referencedEntity);
		}
	}

	@Test
	public void testAddReferencedEntities() {
		List<Entity> referencedEntities = TestObjects.getEntities();
		int initialSize = relationship.getReferencedEntities().size();
		assertTrue(relationship.addReferencedEntities(referencedEntities));

		for (int i = 0; i < relationship.getReferencedEntities().size(); i++) {
			assertEquals(referencedEntities.get(i), relationship
					.getReferencedEntities().get(i + initialSize));
		}
	}

	@Test
	public void testaddReferencedEntitiesExpectFalse() {
		List<Entity> referencedEntities = new ArrayList<Entity>();
		referencedEntities.add(new EntityImpl());
		referencedEntities.add(null);
		referencedEntities.add(new EntityImpl());
		assertFalse(relationship.addReferencedEntities(referencedEntities));
	}

	@Test
	public void testAddReferencedEntitiesNull() {
		assertFalse(relationship.addReferencedEntities(null));
		for (Entity referencedEntity : relationship.getReferencedEntities()) {
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
		for (Entity referencedEntity : TestObjects.getEntities()) {
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
		List<Entity> referencedEntities = TestObjects.getEntities();
		relationship.setReferencedEntities(referencedEntities);
		assertEquals(referencedEntities, relationship.getReferencedEntities());
	}

	@Test
	public void testSetReferencedEntitiesNull() {
		relationship.setReferencedEntities(null);
		for (Entity referencedEntity : relationship.getReferencedEntities()) {
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