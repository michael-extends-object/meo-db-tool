package com.github.meo.db.tool.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.meo.db.tool.testsuite.TestObjects;

public class DatabaseTests {

	Database database;
	private static final String DATABASE_NAME = "Database table name";

	@Before
	public void setup() {
		database = new Database();
	}

	@Test
	public void createInstance() {
		database = new Database(DATABASE_NAME);
		assertEquals(DATABASE_NAME, database.getName());
	}

	@Test
	public void getSetName() {
		String databaseName = TestObjects.DATABASE_NAME_A;
		database.setName(databaseName);
		assertEquals(databaseName, database.getName());
	}

	@Test
	public void getSetDataSource() {
		JdbcDataSource dataSource = TestObjects.getJdbcDataSourceA();
		database.setDataSource(dataSource);
		assertEquals(dataSource, database.getDataSource());
	}

	@Test
	public void getSetEntityMappings() {
		List<EntityMapping> entityMapping = TestObjects.getEntityMappings();
		database.setEntityMappings(entityMapping);
		assertTrue(entityMapping == database.getEntityMappings());
	}

	@Test
	public void toStringTest() {
		database.setName(DATABASE_NAME);
		assertEquals(DATABASE_NAME, database.toString());
	}

	@Test
	public void addEntityMapping() {

		List<EntityMapping> entityMappings = TestObjects.getEntityMappings();

		for (EntityMapping entityMapping : entityMappings) {
			database.addEntityMapping(entityMapping);
		}

		assertEquals(entityMappings, database.getEntityMappings());

	}

	@Test
	public void getDatabaseTable() {

		List<Database> databases = TestObjects.getDatabases();

		for (Database database : databases) {
			assertEquals(TestObjects.getDatabaseTableA(), database.getDatabaseTable(TestObjects.getEntityA()));
		}
	}
	
	@Test
	public void getDatabaseTableNull() {

		List<Database> databases = TestObjects.getDatabases();

		for (Database database : databases) {
			assertNull(database.getDatabaseTable(null));
		}
	}
}
