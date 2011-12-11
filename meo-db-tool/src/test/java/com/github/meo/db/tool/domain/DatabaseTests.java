package com.github.meo.db.tool.domain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;

import com.github.meo.db.tool.testsuite.TestObjects;

public class DatabaseTests {

	Database database;
	private static final String DATABASE_NAME = "Database table name";
	private String expectedString;
	private String actualString;

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
	public void getSetEntityTypeMappings() {
		List<EntityTypeMapping> entityTypeMapping = TestObjects
				.getEntityTypeMappings();
		database.setEntityTypeMappings(entityTypeMapping);
		assertTrue(entityTypeMapping == database.getEntityTypeMappings());
	}

	@Test
	public void toStringTest() {
		database.setName(DATABASE_NAME);
		assertEquals(DATABASE_NAME, database.toString());
	}

	@Test
	public void addEntityTypeMapping() {

		List<EntityTypeMapping> entityTypeMappings = TestObjects
				.getEntityTypeMappings();

		for (EntityTypeMapping entityTypeMapping : entityTypeMappings) {
			database.addEntityTypeMapping(entityTypeMapping);
		}

		assertEquals(entityTypeMappings, database.getEntityTypeMappings());
	}

	@Test
	public void getDatabaseTable() {

		List<Database> databases = TestObjects.getDatabases();

		for (Database database : databases) {
			assertEquals(TestObjects.getDatabaseTableA(),
					database.getDatabaseTable(TestObjects.getEntityTypeA()));
		}
	}

	@Test
	public void getDatabaseTableNull() {

		List<Database> databases = TestObjects.getDatabases();

		for (Database database : databases) {
			assertNull(database.getDatabaseTable((IEntityType) null));
		}
	}

	@Test
	public void getEntityTypeMappingNull() {
		assertNull(database.getEntityTypeMapping(null));
	}

	@Test
	public void getAttributeMappingsEntityType() {
		database.addEntityTypeMapping(TestObjects.getEntityTypeMappingA());
		assertEquals(TestObjects.getAttributeTypeMappings(),
				database.getAttributeTypeMappings(TestObjects.getEntityTypeA()));
	}

	@Test
	public void getColumnCountEntityType() {
		database.setEntityTypeMappings(TestObjects.getEntityTypeMappings());
		assertEquals(TestObjects.getDatabaseTableColumns().size(),
				database.getColumnCount(TestObjects.getEntityTypeA()));
	}

	@Test
	public void getAttributeMappingsEntityTypeNull() {
		assertNotNull(database.getAttributeTypeMappings((IEntityType) null));
	}

	@Test(expected = IllegalArgumentException.class)
	public void getDatabaseTableColumnEntityTypeNullAttribute() {
		try {
			database.getDatabaseTableColumn(new EntityType(),
					(IAttributeType) null);
		} catch (IllegalArgumentException e) {
			assertEquals("null is not a valid argument!", e.getMessage());
			throw e;
		}

	}

	@Test(expected = IllegalArgumentException.class)
	public void getDatabaseTableColumnNullEntityType() {
		try {
			database.getDatabaseTableColumn((IEntityType) null,
					new AttributeType());
		} catch (IllegalArgumentException e) {
			assertEquals("null is not a valid argument!", e.getMessage());
			throw e;
		}
	}

	@Test
	public void getDatabaseTableColumnResultNullEntityType() {
		IAttributeType attributeType = new AttributeType("Attribute name");
		IAttribute attribute = attributeType.getAttribute();
		assertNull(database.getDatabaseTableColumn(new EntityType(),
				attribute.getAttributeType()));
	}

	@Test
	public void testSetGetEntityRelationshipModels() {
		List<IEntityRelationshipModel> entityRelationshipModels = new ArrayList<IEntityRelationshipModel>();
		entityRelationshipModels.add(new EntityRelationshipModel());
		entityRelationshipModels.add(new EntityRelationshipModel());
		entityRelationshipModels.add(new EntityRelationshipModel());
		database.setEntityRelationshipModels(entityRelationshipModels);
		assertEquals(entityRelationshipModels,
				database.getEntityRelationshipModels());
	}

	@Test
	public void testGetDatabaseTableEntityType() {
		database = TestObjects.getDatabaseA();
		expectedString = "Database table column A";
		actualString = database.getDatabaseTableColumn(
				new EntityType("Entity A"), "Attribute A").getName();
		assertEquals(expectedString, actualString);
	}
}
