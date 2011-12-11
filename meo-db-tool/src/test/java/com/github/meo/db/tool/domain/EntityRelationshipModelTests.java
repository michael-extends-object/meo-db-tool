package com.github.meo.db.tool.domain;

import static org.junit.Assert.assertEquals;
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
			assertEquals("<null> is not a valid argument", e.getMessage());

			for (IEntityType entityType : erm.getEntityTypes()) {
				assertNotNull(entityType);
			}

			throw e;
		}
	}

	@Test
	public void testAddRelationship() {

		List<IRelationship> relationships = TestObjects.getRelationships();

		for (IRelationship relationship : relationships) {
			erm.addRelationship(relationship);
		}

		assertEquals(relationships, erm.getRelationships());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddRelationshipNull() {
		try {
			erm.addRelationship(null);
		} catch (IllegalArgumentException e) {
			assertEquals("<null> is not a valid argument", e.getMessage());

			for (IRelationship relationship : erm.getRelationships()) {
				assertNotNull(relationship);
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
			assertEquals("<null> is not a valid argument", e.getMessage());
			throw e;
		}
	}

	@Test
	public void testSetRelationships() {

		List<IRelationship> relationships = new ArrayList<IRelationship>();
		IRelationship relationship = new Relationship();
		relationships.add(relationship);

		erm.setRelationships(relationships);
		assertTrue(relationships == erm.getRelationships());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetRelationshipsNull() {

		try {
			erm.setRelationships(null);
		} catch (IllegalArgumentException e) {
			assertNotNull(erm.getRelationships());
			assertEquals("<null> is not a valid argument", e.getMessage());
			throw e;
		}
	}

	@Test
	public void testSetEntityTypes() {
		List<IEntityType> entityTypes = getEntityTypes();
		erm.setEntityTypes(entityTypes);
		assertTrue(entityTypes == erm.getEntityTypes());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetEntityTypesNull() {

		try {
			erm.setEntityTypes(null);
		} catch (IllegalArgumentException e) {
			assertNotNull(erm.getEntityTypes());
			assertEquals("<null> is not a valid argument", e.getMessage());
			throw e;
		}

	}
	
	@Test
	public void testToString() {
		erm.setName(ERM_NAME);
		assertEquals(ERM_NAME, erm.toString());
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
}
