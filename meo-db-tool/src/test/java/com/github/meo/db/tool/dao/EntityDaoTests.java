package com.github.meo.db.tool.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.jdbc.SimpleJdbcTestUtils;

import com.github.meo.db.tool.domain.IEntity;
import com.github.meo.db.tool.domain.IEntityType;
import com.github.meo.db.tool.testsuite.TestObjects;
import com.github.meo.db.tool.testsuite.TestObjectsUserManagement;

public class EntityDaoTests {

	IEntityDao entityDao;
	private static final Resource SQL_SCRIPT_TABLE_CREATE = new FileSystemResource(
			"src/test/resources/SQL/TableCreate.sql");
	private static final Resource SQL_SCRIPT_TEST_DATA_INSERT = new FileSystemResource(
			"src/test/resources/SQL/TestDataInsert.sql");
	private static final Resource SQL_SCRIPT_TABLE_DROP = new FileSystemResource(
			"src/test/resources/SQL/TableDrop.sql");

	@Before
	public void setUp() throws Exception {
		entityDao = new EntityDao();
	}

	@Test
	public void testNewInstanceDatabase() {
		Database database = TestObjects.getDatabaseA();
		entityDao = new EntityDao(database);
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
		entityDao.setDatabase(null);
		assertNotNull(entityDao.getDatabase());
	}

	@Test
	public void testSetDatabaseNullDataSource() {
		SimpleJdbcTemplate jdbcTemplate = new SimpleJdbcTemplate(
				TestObjects.getDataSourceA());
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
	public void testInsertSelectDeleteEntityUser() {
		Database database = TestObjectsUserManagement
				.getDatabaseUserManagementSource();
		IEntityType entityTypeUser = TestObjectsUserManagement.getTypeUser();
		IEntity user = TestObjectsUserManagement.getUserA();
		IEntityDao entityDao = new EntityDao(database);

		createTables(entityDao.getJdbcTemplate());
		entityDao.insertEntity(user);
		List<IEntity> entities = entityDao.selectEntities(entityTypeUser);

		assertEquals(1, entities.size());
		assertEquals(user, entities.get(0));

		entityDao.deleteEntity(user);

		entities = entityDao.selectEntities(entityTypeUser);

		assertEquals(0, entities.size());

		dropTables(entityDao.getJdbcTemplate());

	}

	@Test
	public void testInsertSelectDeleteEntityUserGroup() {

		Database database = TestObjectsUserManagement
				.getDatabaseUserManagementSource();
		IEntityType entityTypeGroup = TestObjectsUserManagement.getTypeGroup();
		IEntity group = TestObjectsUserManagement.getGroupA();
		IEntityDao entityDao = new EntityDao();
		entityDao.setDatabase(database);

		createTables(entityDao.getJdbcTemplate());
		entityDao.insertEntity(group);
		List<IEntity> entities = entityDao.selectEntities(entityTypeGroup);

		assertEquals(1, entities.size());
		assertEquals(group, entities.get(0));

		entityDao.deleteEntity(group);

		entities = entityDao.selectEntities(entityTypeGroup);

		assertEquals(0, entities.size());

		dropTables(entityDao.getJdbcTemplate());
	}

	@Ignore
	@Test
	public void testInsertEntityWithOneToOneRelationship() {

		IEntityDao entityDao = new EntityDao();
		entityDao.setDatabase(TestObjectsUserManagement
				.getDatabaseUserManagementSource());

		IEntityType entityTypeUser = TestObjectsUserManagement.getTypeUser();
		IEntity user = entityTypeUser.getEntity();
		// Entity group = (Entity)
		// user.getRelationships().get(0).getReferencedEntities()
		// .get(0);

		user.setAttributeValue("User ID", 5);
		user.setAttributeValue("Username", "Peter");
		user.setAttributeValue("Password", "12345");
		user.setAttributeValue("Group ID", 4);
		// group.setAttributeValue("Group ID", 4);
		// group.setAttributeValue("Group Name", "Group Name");

		createTables(entityDao.getJdbcTemplate());

		entityDao.insertEntity(user);

		List<IEntity> entities = entityDao.selectEntities(entityTypeUser);

		assertEquals(1, entities.size());
		assertEquals(user, entities.get(0));

		List<IEntity> entitiesUserGroup = entityDao
				.selectEntities(entityTypeUser);

		assertEquals(1, entitiesUserGroup.size());
		// assertEquals(group, entitiesUserGroup.get(0));

		dropTables(entityDao.getJdbcTemplate());
	}

	@Test
	public void testSelectEntityUser() {
		IEntityDao entityDao = new EntityDao();
		entityDao.setDatabase(TestObjectsUserManagement
				.getDatabaseUserManagementSource());

		IEntityType entityTypeUser = TestObjectsUserManagement.getTypeUser();

		createTables(entityDao.getJdbcTemplate());
		insertTestData(entityDao.getJdbcTemplate());

		List<IEntity> entities = entityDao.selectEntities(entityTypeUser);

		assertEquals(1, entities.size());

		assertEquals(2, entities.get(0).getAttribute("User Id").getValue());
		assertEquals("User name", entities.get(0).getAttribute("Username")
				.getValue());
		assertEquals("Password", entities.get(0).getAttribute("Password")
				.getValue());
		assertEquals(2, entities.get(0).getAttribute("Group Id").getValue());

		dropTables(entityDao.getJdbcTemplate());
	}

	@Test
	public void testSelectEntityGroup() {

		IEntityDao entityDao = new EntityDao();
		entityDao.setDatabase(TestObjectsUserManagement
				.getDatabaseUserManagementSource());

		IEntityType group = TestObjectsUserManagement.getTypeGroup();

		createTables(entityDao.getJdbcTemplate());
		insertTestData(entityDao.getJdbcTemplate());

		List<IEntity> entities = entityDao.selectEntities(group);

		assertEquals(1, entities.size());

		IEntity selectedEntity = entities.get(0);
		assertEquals(2, selectedEntity.getAttributeValue("Group Id"));
		assertEquals("Group name",
				selectedEntity.getAttributeValue("Groupname"));

		dropTables(entityDao.getJdbcTemplate());
	}

	@Ignore
	@Test
	public void testSelectEntityWithRelationship() {

		IEntityDao entityDao = new EntityDao();
		entityDao.setDatabase(TestObjectsUserManagement
				.getDatabaseUserManagementSource());

		IEntityType user = TestObjectsUserManagement.getTypeUser();

		createTables(entityDao.getJdbcTemplate());
		insertTestData(entityDao.getJdbcTemplate());

		List<IEntity> entities = entityDao.selectEntities(user);

		assertEquals(1, entities.size());

		// Entity group = (Entity) entities.get(0).getRelationships().get(0)
		// .getReferencedEntities().get(0);
		// assertEquals(2, group.getAttributeValue("Group ID"));
		// assertEquals("Group name", group.getAttributeValue("Group Name"));

		dropTables(entityDao.getJdbcTemplate());
	}

	@Test
	public void deleteEntities() {

		entityDao.setDatabase(TestObjectsUserManagement
				.getDatabaseUserManagementSource());

		createTables(entityDao.getJdbcTemplate());

		IEntity entity = TestObjectsUserManagement.getUserA();
		IEntityType entityType = entity.getEntityType();
		entityDao.insertEntity(entity);
		entityDao.insertEntity(entity);
		entityDao.insertEntity(entity);

		List<IEntity> entities;

		entities = entityDao.selectEntities(entityType);
		assertEquals(3, entities.size());

		entityDao.deleteEntities(entityType);

		entities = entityDao.selectEntities(entityType);
		assertEquals(0, entities.size());

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
