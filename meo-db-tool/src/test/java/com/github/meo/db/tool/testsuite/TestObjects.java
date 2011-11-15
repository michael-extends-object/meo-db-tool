package com.github.meo.db.tool.testsuite;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

import com.github.meo.db.tool.domain.Attribute;
import com.github.meo.db.tool.domain.AttributeImpl;
import com.github.meo.db.tool.domain.AttributeMapping;
import com.github.meo.db.tool.domain.Database;
import com.github.meo.db.tool.domain.DatabaseTable;
import com.github.meo.db.tool.domain.DatabaseTableColumn;
import com.github.meo.db.tool.domain.Entity;
import com.github.meo.db.tool.domain.EntityImpl;
import com.github.meo.db.tool.domain.EntityMapping;
import com.github.meo.db.tool.domain.JdbcDataSource;

public class TestObjects {

	public static final String ATTRIBUTE_NAME_A = "Attribute A";
	public static final String ATTRIBUTE_NAME_B = "Attribute B";
	public static final String ATTRIBUTE_NAME_C = "Attribute C";

	public static Attribute attributeA;
	public static Attribute attributeB;
	public static Attribute attributeC;

	public static final String ENTITY_NAME_A = "Entity A";
	public static final String ENTITY_NAME_B = "Entity B";
	public static final String ENTITY_NAME_C = "Entity C";

	public static final String JDBC_DRIVER_HSQL = "org.hsqldb.jdbcDriver";
	public static final String JDBC_DRIVER_PSQL = "org.postgresql.Driver";
	public static final String DB_URL_HSQL_SOURCE = "jdbc:hsqldb:mem:SourceDatabase";
	public static final String DB_URL_HSQL_TARGET = "jdbc:hsqldb:mem:TargetDatabase";
	public static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";
	public static final String INVALID_DRIVER_CLASS_NAME = "invalid.Driver";
	public static final String URL = "jdbc:postgresql://localhost/dbem";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";

	public static final String DATABASE_NAME_A = "Database A";
	public static final String DATABASE_NAME_B = "Database B";
	public static final String DATABASE_NAME_C = "Database C";

	public static final String DATABASE_TABLE_NAME_A = "Database table A";
	public static final String DATABASE_TABLE_NAME_B = "Database table B";
	public static final String DATABASE_TABLE_NAME_C = "Database table C";

	public static final String DATABASE_TABLE_COLUMN_NAME_A = "Database table column A";
	public static final String DATABASE_TABLE_COLUMN_NAME_B = "Database table column B";
	public static final String DATABASE_TABLE_COLUMN_NAME_C = "Database table column C";

	public static Attribute getAttributeA() {
		attributeA = new AttributeImpl(ATTRIBUTE_NAME_A);
		attributeA.setValue("value");
		return attributeA;
	}

	public static Attribute getAttributeB() {
		attributeB = new AttributeImpl(ATTRIBUTE_NAME_B);
		attributeB.setValue(Boolean.TRUE);
		return attributeB;
	}

	public static Attribute getAttributeC() {
		attributeC = new AttributeImpl(ATTRIBUTE_NAME_C);
		attributeC.setValue(8.8);
		return attributeC;
	}

	public static List<Attribute> getAttributes() {
		List<Attribute> attributes = new ArrayList<Attribute>();
		attributes.add(getAttributeA());
		attributes.add(getAttributeB());
		attributes.add(getAttributeC());
		return attributes;
	}

	public static Entity getEntityA() {
		Entity entity = new EntityImpl(ENTITY_NAME_A);
		entity.setAttributes(getAttributes());
		return entity;
	}

	public static Entity getEntityB() {
		Entity entity = new EntityImpl(ENTITY_NAME_B);
		entity.setAttributes(getAttributes());
		return entity;
	}

	public static Entity getEntityC() {
		Entity entity = new EntityImpl(ENTITY_NAME_C);
		entity.setAttributes(getAttributes());
		return entity;
	}

	public static List<Entity> getEntities() {
		List<Entity> entities = new ArrayList<Entity>();
		entities.add(getEntityA());
		entities.add(getEntityB());
		entities.add(getEntityC());
		return entities;
	}

	public static Driver getDriver() {
		return JdbcDataSource.getDriver(DRIVER_CLASS_NAME);
	}

	public static String getDriverClassName() {
		return DRIVER_CLASS_NAME;
	}

	public static String getInvalidDriverClassName() {
		return INVALID_DRIVER_CLASS_NAME;
	}

	public static String getUrl() {
		return URL;
	}

	public static String getUsername() {
		return USERNAME;
	}

	public static String getPassword() {
		return PASSWORD;
	}

	public static JdbcDataSource getJdbcDataSourceA() {
		JdbcDataSource jdbcDataSource = new JdbcDataSource();

		jdbcDataSource
				.setDriver(JdbcDataSource.getDriver(getDriverClassName()));
		jdbcDataSource.setUrl(URL);
		jdbcDataSource.setUsername(USERNAME);
		jdbcDataSource.setPassword(PASSWORD);

		return jdbcDataSource;
	}

	public static JdbcDataSource getJdbcDataSourceB() {
		JdbcDataSource jdbcDataSource = new JdbcDataSource();

		jdbcDataSource
				.setDriver(JdbcDataSource.getDriver(getDriverClassName()));
		jdbcDataSource.setUrl(URL);
		jdbcDataSource.setUsername(USERNAME);
		jdbcDataSource.setPassword(PASSWORD);

		return jdbcDataSource;
	}

	public static JdbcDataSource getJdbcDataSourceC() {
		JdbcDataSource jdbcDataSource = new JdbcDataSource();

		jdbcDataSource
				.setDriver(JdbcDataSource.getDriver(getDriverClassName()));
		jdbcDataSource.setUrl(URL);
		jdbcDataSource.setUsername(USERNAME);
		jdbcDataSource.setPassword(PASSWORD);

		return jdbcDataSource;
	}

	public static List<JdbcDataSource> getJdbcDataSources() {
		List<JdbcDataSource> jdbcDataSources = new ArrayList<JdbcDataSource>();
		jdbcDataSources.add(getJdbcDataSourceA());

		return jdbcDataSources;
	}

	public static Database getDatabaseA() {
		Database database = new Database(DATABASE_NAME_A);
		database.setDataSource(getJdbcDataSourceA());
		database.setEntityMappings(getEntityMappings());
		return database;
	}

	public static Database getDatabaseB() {
		Database database = new Database(DATABASE_NAME_B);
		database.setDataSource(getJdbcDataSourceB());
		database.setEntityMappings(getEntityMappings());
		return database;
	}

	public static Database getDatabaseC() {
		Database database = new Database(DATABASE_NAME_C);
		database.setDataSource(getJdbcDataSourceC());
		database.setEntityMappings(getEntityMappings());
		return database;
	}

	public static List<Database> getDatabases() {
		List<Database> databases = new ArrayList<Database>();
		databases.add(getDatabaseA());
		databases.add(getDatabaseB());
		databases.add(getDatabaseC());
		return databases;
	}

	public static DatabaseTable getDatabaseTableA() {
		DatabaseTable databaseTable = new DatabaseTable(DATABASE_TABLE_NAME_A);
		return databaseTable;
	}

	public static DatabaseTable getDatabaseTableB() {
		DatabaseTable databaseTable = new DatabaseTable(DATABASE_TABLE_NAME_B);
		return databaseTable;
	}

	public static DatabaseTable getDatabaseTableC() {
		DatabaseTable databaseTable = new DatabaseTable(DATABASE_TABLE_NAME_C);
		return databaseTable;
	}

	public static List<DatabaseTable> getDatabaseTables() {
		List<DatabaseTable> databaseTables = new ArrayList<DatabaseTable>();
		databaseTables.add(getDatabaseTableA());
		databaseTables.add(getDatabaseTableB());
		databaseTables.add(getDatabaseTableC());
		return databaseTables;
	}

	public static DatabaseTableColumn getDatabaseTableColumnA() {
		DatabaseTableColumn databaseTableColumn = new DatabaseTableColumn(
				DATABASE_TABLE_COLUMN_NAME_A);
		return databaseTableColumn;
	}

	public static DatabaseTableColumn getDatabaseTableColumnB() {
		DatabaseTableColumn databaseTableColumn = new DatabaseTableColumn(
				DATABASE_TABLE_COLUMN_NAME_B);
		return databaseTableColumn;
	}

	public static DatabaseTableColumn getDatabaseTableColumnC() {
		DatabaseTableColumn databaseTableColumn = new DatabaseTableColumn(
				DATABASE_TABLE_COLUMN_NAME_C);
		return databaseTableColumn;
	}

	public static List<DatabaseTableColumn> getDatabaseTableColumns() {
		List<DatabaseTableColumn> databaseTableColumns = new ArrayList<DatabaseTableColumn>();
		databaseTableColumns.add(getDatabaseTableColumnA());
		databaseTableColumns.add(getDatabaseTableColumnB());
		databaseTableColumns.add(getDatabaseTableColumnC());
		return databaseTableColumns;
	}

	public static AttributeMapping getAttributeMappingA() {
		AttributeMapping attributeMapping = new AttributeMapping();
		attributeMapping.setAttribute(getAttributeA());
		attributeMapping.setDatabaseTableColumn(getDatabaseTableColumnA());
		return attributeMapping;
	}

	public static AttributeMapping getAttributeMappingB() {
		AttributeMapping attributeMapping = new AttributeMapping();
		attributeMapping.setAttribute(getAttributeB());
		attributeMapping.setDatabaseTableColumn(getDatabaseTableColumnB());
		return attributeMapping;
	}

	public static AttributeMapping getAttributeMappingC() {
		AttributeMapping attributeMapping = new AttributeMapping();
		attributeMapping.setAttribute(getAttributeC());
		attributeMapping.setDatabaseTableColumn(getDatabaseTableColumnC());
		return attributeMapping;
	}

	public static List<AttributeMapping> getAttributeMappings() {
		List<AttributeMapping> attributeMappings = new ArrayList<AttributeMapping>();
		attributeMappings.add(getAttributeMappingA());
		attributeMappings.add(getAttributeMappingB());
		attributeMappings.add(getAttributeMappingC());
		return attributeMappings;
	}

	public static EntityMapping getEntityMappingA() {
		EntityMapping entityMapping = new EntityMapping();
		entityMapping.setEntity(getEntityA());
		entityMapping.setDatabaseTable(getDatabaseTableA());
		entityMapping.setAttributeMappings(getAttributeMappings());
		return entityMapping;
	}

	public static EntityMapping getEntityMappingB() {
		EntityMapping entityMapping = new EntityMapping();
		entityMapping.setEntity(getEntityB());
		entityMapping.setDatabaseTable(getDatabaseTableB());
		entityMapping.setAttributeMappings(getAttributeMappings());
		return entityMapping;
	}

	public static EntityMapping getEntityMappingC() {
		EntityMapping entityMapping = new EntityMapping();
		entityMapping.setEntity(getEntityC());
		entityMapping.setDatabaseTable(getDatabaseTableC());
		entityMapping.setAttributeMappings(getAttributeMappings());
		return entityMapping;
	}

	public static List<EntityMapping> getEntityMappings() {
		List<EntityMapping> entityMappings = new ArrayList<EntityMapping>();
		entityMappings.add(getEntityMappingA());
		entityMappings.add(getEntityMappingB());
		entityMappings.add(getEntityMappingC());
		return entityMappings;
	}
}
