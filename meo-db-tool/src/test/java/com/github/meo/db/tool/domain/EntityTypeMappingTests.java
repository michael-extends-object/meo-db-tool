package com.github.meo.db.tool.domain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.meo.db.tool.testsuite.TestObjects;

public class EntityTypeMappingTests {

	IEntityType entityType;
	DatabaseTable table;
	EntityTypeMapping entityTypeMapping;
	List<AttributeTypeMapping> attributeTypeMappings;

	@Before
	public void setUp() {
		entityType = new EntityType();
		table = new DatabaseTable();
		entityTypeMapping = new EntityTypeMapping();
		attributeTypeMappings = new ArrayList<AttributeTypeMapping>();
	}

	@Test
	public void setGetEntity() {
		entityTypeMapping.setEntityType(entityType);
		assertTrue(entityType == entityTypeMapping.getEntityType());
	}

	@Test
	public void setGetDatabaseTable() {
		entityTypeMapping.setDatabaseTable(table);
		assertTrue(table == entityTypeMapping.getDatabaseTable());
	}

	@Test
	public void setGetAttributeTypeMappings() {
		entityTypeMapping.setAttributeTypeMappings(attributeTypeMappings);
		assertTrue(attributeTypeMappings == entityTypeMapping.getAttributeTypeMappings());
	}

	@Test
	public void addAttributeMapping() {
		List<AttributeTypeMapping> expectedAttributeTypeMappings = TestObjects
				.getAttributeTypeMappings();

		for (AttributeTypeMapping attributeTypeMapping : expectedAttributeTypeMappings) {
			entityTypeMapping.addAttributeTypeMapping(attributeTypeMapping);
		}

		assertEquals(expectedAttributeTypeMappings,
				entityTypeMapping.getAttributeTypeMappings());
	}

	@Test
	public void getDatabaseTable() {
		entityTypeMapping = TestObjects.getEntityTypeMappingA();

		assertEquals(TestObjects.getDatabaseTableColumnA(),
				entityTypeMapping.getDatabaseTableColumn(TestObjects
						.getAttributeA()));
		assertEquals(TestObjects.getDatabaseTableColumnB(),
				entityTypeMapping.getDatabaseTableColumn(TestObjects
						.getAttributeB()));
		assertEquals(TestObjects.getDatabaseTableColumnC(),
				entityTypeMapping.getDatabaseTableColumn(TestObjects
						.getAttributeC()));
	}

	@Test
	public void getDatabaseTableNullAttribute() {
		for (EntityTypeMapping entityTypeMapping : TestObjects.getEntityTypeMappings()) {
			assertNull(entityTypeMapping.getDatabaseTableColumn(null));
		}

	}
	
	@Test
	public void testGetDatabaseTableColumn() {
		assertNull(entityTypeMapping.getDatabaseTableColumn(null));
	}
}
