/**
 * 
 */
package com.github.meo.db.tool.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.meo.db.tool.domain.Database;
import com.github.meo.db.tool.domain.Entity;
import com.github.meo.db.tool.testsuite.TestObjects;

/**
 * 
 *
 */
public class EntityRowMapperTests {

	EntityRowMapper entityRowMapper;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		entityRowMapper = new EntityRowMapper();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.github.meo.db.tool.dao.EntityRowMapper#EntityRowMapper(com.github.meo.db.tool.domain.Entity)}
	 * .
	 */
	@Test
	public void testEntityRowMapperEntity() {
		Entity entity = TestObjects.getEntityA();
		entityRowMapper = new EntityRowMapper(entity);
		assertTrue(entity == entityRowMapper.getEntity());
	}

	/**
	 * Test method for
	 * {@link com.github.meo.db.tool.dao.EntityRowMapper#EntityRowMapper(com.github.meo.db.tool.domain.Database, com.github.meo.db.tool.domain.Entity)}
	 * .
	 */
	@Test
	public void testEntityRowMapperDatabaseEntity() {
		Database database = TestObjects.getDatabaseA();
		Entity entity = TestObjects.getEntityA();
		entityRowMapper = new EntityRowMapper(database, entity);
		assertTrue(database == entityRowMapper.getDatabase());
		assertTrue(entity == entityRowMapper.getEntity());
	}


	@Test
	public void testSetGetDatabase() {
		Database database = TestObjects.getDatabaseA();
		entityRowMapper.setDatabase(database);
		assertTrue(database == entityRowMapper.getDatabase());
	}

	/**
	 * Test method for
	 * {@link com.github.meo.db.tool.dao.EntityRowMapper#setEntity(com.github.meo.db.tool.domain.Entity)}
	 * and
	 * {@link com.github.meo.db.tool.dao.EntityRowMapper#getEntity(com.github.meo.db.tool.domain.Entity)}
	 * .
	 */
	@Test
	public void testSetGetEntity() {
		Entity entity = TestObjects.getEntityA();
		entityRowMapper.setEntity(entity);
		assertTrue(entity == entityRowMapper.getEntity());
	}

}
