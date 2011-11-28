package com.github.meo.db.tool.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.jdbc.SimpleJdbcTestUtils;

import com.github.meo.db.tool.domain.Database;
import com.github.meo.db.tool.domain.Entity;
import com.github.meo.db.tool.testsuite.TestObjects;

public class EntityDaoImplTests {

	EntityDao entityDao;
	private static final Resource SQL_SCRIPT_TABLE_CREATE = new FileSystemResource(
			"src/test/resources/SQL/TableCreate.sql");
	private static final Resource SQL_SCRIPT_TEST_DATA_INSERT = new FileSystemResource(
			"src/test/resources/SQL/TestDataInsert.sql");
	private static final Resource SQL_SCRIPT_TABLE_DROP = new FileSystemResource(
			"src/test/resources/SQL/TableDrop.sql");

	@Before
	public void setUp() throws Exception {
		entityDao = new EntityDaoImpl();
	}

	@Test
	public void testNewInstanceDatabase() {
		Database database = TestObjects.getDatabaseA();
		entityDao = new EntityDaoImpl(database);
		assertTrue(database == entityDao.getDatabase());
	}

	@Test
	public void testSetGetDatabase() {
		Database database = TestObjects.getDatabaseA();
		entityDao.setDatabase(database);
		assertTrue(database == entityDao.getDatabase());
	}

	@Test
	public void testSetDatabaseNull() {
		entityDao.setDatabase(new Database());
		assertNotNull(entityDao.getDatabase());
		entityDao.setDatabase(null);
		assertNotNull(entityDao.getDatabase());
	}

	@Test
	public void testSetDatabaseNullDataSource() {
		SimpleJdbcTemplate jdbcTemplate = new SimpleJdbcTemplate(
				TestObjects.getJdbcDataSourceA());
		entityDao.setJdbcTemplate(jdbcTemplate);
		assertTrue(jdbcTemplate == entityDao.getJdbcTemplate());
		entityDao.setDatabase(new Database());
		assertTrue(jdbcTemplate == entityDao.getJdbcTemplate());
	}

	@Test
	public void testSetGetJdbcTemplate() {
		SimpleJdbcTemplate jdbcTemplate = new SimpleJdbcTemplate(TestObjects
				.getDatabaseA().getDataSource());
		entityDao.setJdbcTemplate(jdbcTemplate);
		assertTrue(jdbcTemplate == entityDao.getJdbcTemplate());
	}

	@Test
	public void testSetJdbcTemplateNull() {
		SimpleJdbcTemplate jdbcTemplate = new SimpleJdbcTemplate(TestObjects
				.getDatabaseA().getDataSource());
		entityDao.setJdbcTemplate(jdbcTemplate);
		assertNotNull(entityDao.getJdbcTemplate());
		assertTrue(jdbcTemplate == entityDao.getJdbcTemplate());
		entityDao.setJdbcTemplate(null);
		assertNotNull(entityDao.getJdbcTemplate());
		assertTrue(jdbcTemplate == entityDao.getJdbcTemplate());
	}

	@Test
	public void testInsertSelectDeleteEntityUser() throws SQLException,
			InstantiationException, IllegalAccessException,
			ClassNotFoundException {

		Database database = TestObjects.getDatabaseUserManagementSource();
		Entity user = TestObjects.getEntityUser();
		EntityDao entityDao = new EntityDaoImpl();
		entityDao.setDatabase(database);

		user.setAttributeValue("User ID", 5);
		user.setAttributeValue("Username", "Peter");
		user.setAttributeValue("Password", "12345");
		user.setAttributeValue("Group ID", 4);

		createTables(entityDao.getJdbcTemplate());
		entityDao.insertEntity(user);
		List<Entity> entities = entityDao.selectEntities(user);

		assertEquals(1, entities.size());
		assertEquals(user, entities.get(0));

		entityDao.deleteEntity(user);

		entities = entityDao.selectEntities(user);

		assertEquals(0, entities.size());

		dropTables(entityDao.getJdbcTemplate());
	}

	@Test
	public void testInsertSelectDeleteEntityUserGroup() {

		Database database = TestObjects.getDatabaseUserManagementSource();
		Entity group = TestObjects.getEntityGroup();
		EntityDao entityDao = new EntityDaoImpl();
		entityDao.setDatabase(database);

		group.setAttributeValue("Group ID", 4);
		group.setAttributeValue("Group Name", "Group Name");

		createTables(entityDao.getJdbcTemplate());
		entityDao.insertEntity(group);
		List<Entity> entities = entityDao.selectEntities(group);

		assertEquals(1, entities.size());
		assertEquals(group, entities.get(0));

		entityDao.deleteEntity(group);

		entities = entityDao.selectEntities(group);

		assertEquals(0, entities.size());

		dropTables(entityDao.getJdbcTemplate());
	}

	@Test
	public void testInsertEntityWithOneToOneRelationship() {

		EntityDao entityDao = new EntityDaoImpl();
		entityDao.setDatabase(TestObjects.getDatabaseUserManagementSource());

		Entity user = TestObjects.getEntityUser();
		Entity group = user.getRelationships().get(0).getReferencedEntities()
				.get(0);

		user.setAttributeValue("User ID", 5);
		user.setAttributeValue("Username", "Peter");
		user.setAttributeValue("Password", "12345");
		user.setAttributeValue("Group ID", 4);
		group.setAttributeValue("Group ID", 4);
		group.setAttributeValue("Group Name", "Group Name");

		createTables(entityDao.getJdbcTemplate());

		entityDao.insertEntity(user);

		List<Entity> entities = entityDao.selectEntities(user);

		assertEquals(1, entities.size());
		assertEquals(user, entities.get(0));

		List<Entity> entitiesUserGroup = entityDao.selectEntities(group);

		assertEquals(1, entitiesUserGroup.size());
		assertEquals(group, entitiesUserGroup.get(0));

		dropTables(entityDao.getJdbcTemplate());
	}

	@Test
	public void testSelectEntityUser() {
		EntityDao entityDao = new EntityDaoImpl();
		entityDao.setDatabase(TestObjects.getDatabaseUserManagementSource());

		Entity user = TestObjects.getEntityUser();

		createTables(entityDao.getJdbcTemplate());
		insertTestData(entityDao.getJdbcTemplate());

		List<Entity> entities = entityDao.selectEntities(user);

		assertEquals(1, entities.size());

		assertEquals(2, entities.get(0).getAttribute("User ID").getValue());
		assertEquals("User name", entities.get(0).getAttribute("Username")
				.getValue());
		assertEquals("Password", entities.get(0).getAttribute("Password")
				.getValue());
		assertEquals(2, entities.get(0).getAttribute("Group ID").getValue());

		dropTables(entityDao.getJdbcTemplate());
	}

	@Test
	public void testSelectEntityGroup() {

		EntityDao entityDao = new EntityDaoImpl();
		entityDao.setDatabase(TestObjects.getDatabaseUserManagementSource());

		Entity group = TestObjects.getEntityGroup();

		createTables(entityDao.getJdbcTemplate());
		insertTestData(entityDao.getJdbcTemplate());

		List<Entity> entities = entityDao.selectEntities(group);

		assertEquals(1, entities.size());

		Entity selectedEntity = entities.get(0);
		assertEquals(2, selectedEntity.getAttributeValue("Group ID"));
		assertEquals("Group name",
				selectedEntity.getAttributeValue("Group Name"));

		dropTables(entityDao.getJdbcTemplate());
	}

	@Test
	public void testSelectEntityWithRelationship() {

		EntityDao entityDao = new EntityDaoImpl();
		entityDao.setDatabase(TestObjects.getDatabaseUserManagementSource());

		Entity user = TestObjects.getEntityUser();

		createTables(entityDao.getJdbcTemplate());
		insertTestData(entityDao.getJdbcTemplate());

		List<Entity> entities = entityDao.selectEntities(user);

		assertEquals(1, entities.size());

		Entity group = entities.get(0).getRelationships().get(0)
				.getReferencedEntities().get(0);
		assertEquals(2, group.getAttributeValue("Group ID"));
		assertEquals("Group name", group.getAttributeValue("Group Name"));

		dropTables(entityDao.getJdbcTemplate());
	}

	private void createTables(SimpleJdbcTemplate jdbcTemplate) {
		SimpleJdbcTestUtils.executeSqlScript(jdbcTemplate,
				SQL_SCRIPT_TABLE_CREATE, false);
	}

	private void insertTestData(SimpleJdbcTemplate jdbcTemplate) {
		SimpleJdbcTestUtils.executeSqlScript(jdbcTemplate,
				SQL_SCRIPT_TEST_DATA_INSERT, false);
	}

	private void dropTables(SimpleJdbcTemplate jdbcTemplate) {
		SimpleJdbcTestUtils.executeSqlScript(jdbcTemplate,
				SQL_SCRIPT_TABLE_DROP, false);
	}
}
