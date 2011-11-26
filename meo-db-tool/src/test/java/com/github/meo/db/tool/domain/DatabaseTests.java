package com.github.meo.db.tool.domain;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

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
		DataSource dataSource = TestObjects.getDataSourceA();
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
			assertEquals(TestObjects.getDatabaseTableA(),
					database.getDatabaseTable(TestObjects.getEntityA()));
		}
	}

	@Test
	public void getDatabaseTableNull() {

		List<Database> databases = TestObjects.getDatabases();

		for (Database database : databases) {
			assertNull(database.getDatabaseTable(null));
		}
	}

	public void getEntityMapping() {

		for (EntityMapping entityMapping : TestObjects.getEntityMappings()) {
			database.addEntityMapping(entityMapping);
		}

		assertEquals(TestObjects.getEntityMappingB(), TestObjects.getEntityB());
	}

	public void getEntityMappingNull() {
		assertNull(database.getEntityMapping(null));
	}

	@Test
	public void getAttributeMappings() {
		database.addEntityMapping(TestObjects.getEntityMappingA());
		assertEquals(TestObjects.getAttributeMappings(),
				database.getAttributeMappings(TestObjects.getEntityA()));
	}

	@Test
	public void getColumnCount() {
		database.setEntityMappings(TestObjects.getEntityMappings());
		assertEquals(TestObjects.getDatabaseTableColumns().size(),
				database.getColumnCount(TestObjects.getEntityA()));
	}

	@Test
	public void getAttributeMappingsNull() {
		database.getAttributeMappings(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getDatabaseTableColumnNullAttribute() {
		try {
			database.getDatabaseTableColumn(new EntityImpl(), (Attribute) null);
		} catch (IllegalArgumentException e) {
			assertEquals("null is not a valid argument!", e.getMessage());
			throw e;
		}

	}

	@Test(expected = IllegalArgumentException.class)
	public void getDatabaseTableColumnNullEntity() {
		try {
			database.getDatabaseTableColumn(null, new AttributeImpl());
		} catch (IllegalArgumentException e) {
			assertEquals("null is not a valid argument!", e.getMessage());
			throw e;
		}
	}

	@Test
	public void getDatabaseTableColumnResultNull() {

		Attribute attribute = new AttributeImpl();
		attribute.setName("Attribute name");
		
		assertNull(database.getDatabaseTableColumn(new EntityImpl(), attribute));
	}
}
