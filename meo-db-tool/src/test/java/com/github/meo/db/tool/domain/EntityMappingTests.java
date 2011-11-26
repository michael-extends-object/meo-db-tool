package com.github.meo.db.tool.domain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.meo.db.tool.testsuite.TestObjects;

public class EntityMappingTests {

	Entity entity;
	DatabaseTable table;
	EntityMapping entityMapping;
	List<AttributeMapping> attributeMappings;

	@Before
	public void setUp() {
		entity = new EntityImpl();
		table = new DatabaseTable();
		entityMapping = new EntityMapping();
		attributeMappings = new ArrayList<AttributeMapping>();
	}

	@Test
	public void setGetEntity() {
		entityMapping.setEntity(entity);
		assertTrue(entity == entityMapping.getEntity());
	}

	@Test
	public void setGetDatabaseTable() {
		entityMapping.setDatabaseTable(table);
		assertTrue(table == entityMapping.getDatabaseTable());
	}

	@Test
	public void setGetAttributeMappings() {
		entityMapping.setAttributeMappings(attributeMappings);
		assertTrue(attributeMappings == entityMapping.getAttributeMappings());
	}

	@Test
	public void addAttributeMapping() {
		List<AttributeMapping> expectedAttributeMappings = TestObjects
				.getAttributeMappings();

		for (AttributeMapping attributeMapping : expectedAttributeMappings) {
			entityMapping.addAttributeMapping(attributeMapping);
		}

		assertEquals(expectedAttributeMappings,
				entityMapping.getAttributeMappings());
	}

	@Test
	public void getDatabaseTable() {
		entityMapping = TestObjects.getEntityMappingA();

		assertEquals(TestObjects.getDatabaseTableColumnA(),
				entityMapping.getDatabaseTableColumn(TestObjects
						.getAttributeA()));
		assertEquals(TestObjects.getDatabaseTableColumnB(),
				entityMapping.getDatabaseTableColumn(TestObjects
						.getAttributeB()));
		assertEquals(TestObjects.getDatabaseTableColumnC(),
				entityMapping.getDatabaseTableColumn(TestObjects
						.getAttributeC()));
	}

	@Test
	public void getDatabaseTableNullAttribute() {
		for (EntityMapping entityMapping : TestObjects.getEntityMappings()) {
			assertNull(entityMapping.getDatabaseTableColumn(null));
		}

	}
}
