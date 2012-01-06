package com.github.meo.db.tool.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;

import com.github.meo.db.tool.dao.Database;
import com.github.meo.db.tool.domain.AttributeType;
import com.github.meo.db.tool.domain.EntityRelationshipModel;
import com.github.meo.db.tool.domain.EntityType;
import com.github.meo.db.tool.domain.IAttributeType;
import com.github.meo.db.tool.domain.IEntityRelationshipModel;
import com.github.meo.db.tool.domain.IEntityType;
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
	public void toStringTest() {
		database.setName(DATABASE_NAME);
		assertEquals(DATABASE_NAME, database.toString());
	}

	@Test
	public void getTable() {

		List<Database> databases = TestObjects.getDatabases();

		for (Database database : databases) {
			database.setCurrentEntityRelationshipModel(TestObjects.getErmA());
			assertEquals(TestObjects.getTableA(),
					database.getTable(TestObjects.getEntityTypeA()));
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void getTableNull() {

		database = TestObjects.getDatabaseA();

		try {
			database.getTable((IEntityType) null);
		} catch (IllegalArgumentException e) {
			assertEquals(
					"[Assertion failed] - this argument is required; it must not be null",
					e.getMessage());
			throw e;
		}

	}

	@Test(expected = IllegalArgumentException.class)
	public void getEntityTypeMappingNullEntityType() {
		try {
			database.getEntityTypeMapping(null);
		} catch (IllegalArgumentException e) {
			assertEquals(
					"[Assertion failed] - this argument is required; it must not be null",
					e.getMessage());
			throw e;
		}

	}

	@Test
	public void getAttributeMappingsEntityType() {
		database = TestObjects.getDatabaseA();
		database.setCurrentEntityRelationshipModel(TestObjects.getErmA());
		assertEquals(TestObjects.getAttributeTypeMappings(),
				database.getAttributeTypeMappings(TestObjects.getEntityTypeA()));
	}

	@Test
	public void getColumnCountEntityType() {
		database = TestObjects.getDatabaseA();
		database.setCurrentEntityRelationshipModel(TestObjects.getErmA());
		assertEquals(3, database.getColumnCount(TestObjects.getEntityTypeA()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void getAttributeMappingsEntityTypeNull() {
		try {
			database.getAttributeTypeMappings((IEntityType) null);
		} catch (IllegalArgumentException e) {
			assertEquals(
					"[Assertion failed] - this argument is required; it must not be null",
					e.getMessage());
			throw e;
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void getColumnEntityTypeNullAttribute() {
		try {
			database.getColumn(new EntityType(),
					(IAttributeType) null);
		} catch (IllegalArgumentException e) {
			assertEquals(
					"[Assertion failed] - this argument is required; it must not be null",
					e.getMessage());
			throw e;
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void getColumnNullEntityType() {
		try {
			database.getColumn((IEntityType) null,
					new AttributeType());
		} catch (IllegalArgumentException e) {
			assertEquals(
					"[Assertion failed] - this argument is required; it must not be null",
					e.getMessage());
			throw e;
		}
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
	public void testGetTableEntityType() {
		database = TestObjects.getDatabaseA();
		expectedString = "Database table column A";
		actualString = database.getColumn(
				new EntityType("Entity A"), "Attribute A").getName();
		assertEquals(expectedString, actualString);
	}
}
