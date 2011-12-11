package com.github.meo.db.tool.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.meo.db.tool.domain.Database;
import com.github.meo.db.tool.domain.IEntityType;
import com.github.meo.db.tool.testsuite.TestObjects;

public class EntityRowMapperTests {

	EntityRowMapper entityRowMapper;
	IEntityType entityType;

	@Before
	public void setUp() throws Exception {
		entityRowMapper = new EntityRowMapper();
	}

	@Test
	public void testEntityRowMapperEntity() {
		entityType = TestObjects.getEntityTypeA();
		entityRowMapper = new EntityRowMapper(entityType);
		assertTrue(entityType == entityRowMapper.getEntityType());
	}

	@Test
	public void testEntityRowMapperDatabaseEntity() {
		Database database = TestObjects.getDatabaseA();
		entityType = TestObjects.getEntityTypeA();
		entityRowMapper = new EntityRowMapper(database, entityType);
		assertTrue(database == entityRowMapper.getDatabase());
		assertTrue(entityType == entityRowMapper.getEntityType());
	}

	@Test
	public void testSetGetDatabase() {
		Database database = TestObjects.getDatabaseA();
		entityRowMapper.setDatabase(database);
		assertTrue(database == entityRowMapper.getDatabase());
	}

	@Test
	public void testSetGetEntity() {
		entityType = TestObjects.getEntityTypeA();
		entityRowMapper.setEntityType(entityType);
		assertTrue(entityType == entityRowMapper.getEntityType());
	}

}
