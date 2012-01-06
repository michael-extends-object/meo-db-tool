package com.github.meo.db.tool.domain.mapping;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.meo.db.tool.domain.EntityType;
import com.github.meo.db.tool.domain.IEntityType;
import com.github.meo.db.tool.domain.db.Table;
import com.github.meo.db.tool.testsuite.TestObjects;

public class EntityTypeMappingTests {

	IEntityType entityType;
	Table table;
	EntityTypeMapping entityTypeMapping;
	List<AttributeTypeMapping> attributeTypeMappings;

	@Before
	public void setUp() {
		entityType = new EntityType();
		table = new Table();
		entityTypeMapping = new EntityTypeMapping();
		attributeTypeMappings = new ArrayList<AttributeTypeMapping>();
	}

	@Test
	public void setGetEntity() {
		entityTypeMapping.setEntityType(entityType);
		assertTrue(entityType == entityTypeMapping.getEntityType());
	}

	@Test
	public void setGetTable() {
		entityTypeMapping.setTable(table);
		assertTrue(table == entityTypeMapping.getTable());
	}

	@Test
	public void setGetAttributeTypeMappings() {
		entityTypeMapping.setAttributeTypeMappings(attributeTypeMappings);
		assertTrue(attributeTypeMappings == entityTypeMapping
				.getAttributeTypeMappings());
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
	public void getTable() {
		entityTypeMapping = TestObjects.getEntityTypeMappingA();

		assertEquals(TestObjects.getColumnA(),
				entityTypeMapping.getColumn(TestObjects
						.getAttributeA()));
		assertEquals(TestObjects.getColumnB(),
				entityTypeMapping.getColumn(TestObjects
						.getAttributeB()));
		assertEquals(TestObjects.getColumnC(),
				entityTypeMapping.getColumn(TestObjects
						.getAttributeC()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void getTableNullAttribute() {
		EntityTypeMapping entityTypeMapping = TestObjects
				.getEntityTypeMappingA();
		try {
			entityTypeMapping.getColumn(null);
		} catch(IllegalArgumentException e) {
			assertEquals("[Assertion failed] - this argument is required; it must not be null", e.getMessage());
			throw e;
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetColumnNull() {
		try {
			entityTypeMapping.getColumn(null);
		} catch (IllegalArgumentException e) {
			assertEquals(
					"[Assertion failed] - this argument is required; it must not be null",
					e.getMessage());
			throw e;
		}
	}
}
