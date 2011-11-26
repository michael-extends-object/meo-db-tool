package com.github.meo.db.tool.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
	private static final Resource SQL_SCRIPT_TABLE_DROP = new FileSystemResource(
			"src/test/resources/SQL/TableDrop.sql");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		entityDao = new EntityDaoImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNewInstanceDatabase() {
		Database database = TestObjects.getDatabaseA();
		entityDao = new EntityDaoImpl(database);
		assertTrue(database == entityDao.getDatabase());
	}

	@Test
	public void testUpdate() {
		// fail("Not yet implemented");
	}

	@Test
	public void testInsertEntity() {
		// fail("Not yet implemented");
	}

	@Test
	public void testSetJdbcTemplate() {
		// fail("Not yet implemented");
	}

	@Test
	public void testUpdateEntity() {
		// fail("Not yet implemented");
	}

	@Test
	public void testDeleteEntity() {
		// fail("Not yet implemented");
	}

	@Test
	public void testSelectEntity() {
		// fail("Not yet implemented");
	}

	@Test
	public void testSelectEntities() {
		// fail("Not yet implemented");
	}

	@Test
	public void testSetSimpleJdbcTemplate() {
		SimpleJdbcTemplate jdbcTemplate = new SimpleJdbcTemplate(TestObjects
				.getDatabaseA().getDataSource());
		entityDao.setJdbcTemplate(jdbcTemplate);
		assertTrue(jdbcTemplate == entityDao.getJdbcTemplate());
	}

	@Test
	public void testSetGetDatabase() {
		Database database = TestObjects.getDatabaseA();
		entityDao.setDatabase(database);
		assertTrue(database == entityDao.getDatabase());
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
		user.setAttributeValue("Group ID", "4");

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

		group.setAttributeValue("Group ID", "4");
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
	public void testRelation() {

		EntityDao entityDao = new EntityDaoImpl();
		entityDao.setDatabase(TestObjects.getDatabaseUserManagementSource());
		
		Entity user = TestObjects.getEntityUser();
		Entity group = user.getRealtionships().get(0).getReferencedEntity();

		user.setAttributeValue("User ID", 5);
		user.setAttributeValue("Username", "Peter");
		user.setAttributeValue("Password", "12345");
		user.setAttributeValue("Group ID", "4");
		group.setAttributeValue("Group ID", "4");
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

	private void createTables(SimpleJdbcTemplate jdbcTemplate) {
		SimpleJdbcTestUtils.executeSqlScript(jdbcTemplate,
				SQL_SCRIPT_TABLE_CREATE, false);
	}

	private void dropTables(SimpleJdbcTemplate jdbcTemplate) {
		SimpleJdbcTestUtils.executeSqlScript(jdbcTemplate,
				SQL_SCRIPT_TABLE_DROP, false);
	}

	@Test
	public void test() {

	}
}
