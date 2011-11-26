package com.github.meo.db.tool.domain;

import static org.junit.Assert.*;

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
	public void testGetSetName() {
		relationship.setName(RELATIONSHIP_NAME);
		assertTrue(RELATIONSHIP_NAME == relationship.getName());
	}

	@Test
	public void testGetSetReferencedEntity() {
		for (Entity referencedEntity : TestObjects.getEntities()) {
			relationship.setReferencedEntity(referencedEntity);
			assertTrue(referencedEntity == relationship.getReferencedEntity());
		}
	}

	@Test
	public void testGetSetCardinalityEntity() {
		for (Cardinality referencedEntity : Cardinality.values()) {
			relationship.setCardinality(referencedEntity);
			assertTrue(referencedEntity == relationship.getCardinality());
		}
	}

	@Test
	public void testToStringNamedEntity() {
		String name = RELATIONSHIP_NAME;
		relationship.setName(name);
		assertEquals(name, relationship.toString());
	}

	@Test
	public void testToString() {

		Entity referencedEntity = TestObjects.getEntityB();

		relationship.setReferencedEntity(referencedEntity);

		expectedString = "'Relationship to Entity B'";
		actualString = relationship.toString();
		assertEquals(expectedString, actualString);
	}

}
