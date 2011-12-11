package com.github.meo.db.tool.testsuite;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.github.meo.db.tool.domain.IAttribute;
import com.github.meo.db.tool.domain.IAttributeType;
import com.github.meo.db.tool.domain.AttributeType;
import com.github.meo.db.tool.domain.AttributeTypeMapping;
import com.github.meo.db.tool.domain.Cardinality;
import com.github.meo.db.tool.domain.Database;
import com.github.meo.db.tool.domain.DatabaseTable;
import com.github.meo.db.tool.domain.DatabaseTableColumn;
import com.github.meo.db.tool.domain.IEntity;
import com.github.meo.db.tool.domain.IEntityType;
import com.github.meo.db.tool.domain.EntityType;
import com.github.meo.db.tool.domain.EntityTypeMapping;
import com.github.meo.db.tool.domain.IRelationship;
import com.github.meo.db.tool.domain.Relationship;
import com.github.meo.db.tool.exception.AttributeTypeNotFoundException;

public class TestObjects {

	static Logger logger = Logger.getLogger(TestObjects.class);

	public static final String ATTRIBUTE_NAME_A = "Attribute A";
	public static final String ATTRIBUTE_NAME_B = "Attribute B";
	public static final String ATTRIBUTE_NAME_C = "Attribute C";

	public static final String ENTITY_NAME_A = "Entity A";
	public static final String ENTITY_NAME_B = "Entity B";
	public static final String ENTITY_NAME_C = "Entity C";

	public static final String JDBC_DRIVER_HSQL = "org.hsqldb.jdbcDriver";
	public static final String JDBC_DRIVER_PSQL = "org.postgresql.Driver";
	public static final String DB_URL_HSQL_SOURCE = "jdbc:hsqldb:mem:SourceDatabase";
	public static final String DB_URL_HSQL_TARGET = "jdbc:hsqldb:mem:TargetDatabase";
	public static final String JDBC_DRIVER_CLASS_NAME_PSQL = "org.postgresql.Driver";
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

	public static IAttributeType getAttributeTypeA() {
		IAttributeType attributeType = new AttributeType(ATTRIBUTE_NAME_A);
		attributeType.setPrimaryKey(true);
		return attributeType;
	}

	public static IAttributeType getAttributeTypeB() {
		IAttributeType attributeType = new AttributeType(ATTRIBUTE_NAME_B);
		return attributeType;
	}

	public static IAttributeType getAttributeTypeC() {
		IAttributeType attributeType = new AttributeType(ATTRIBUTE_NAME_C);
		attributeType.setPrimaryKey(true);
		return attributeType;
	}

	public static List<IAttributeType> getAttributeTypes() {
		List<IAttributeType> attributeTypes = new ArrayList<IAttributeType>();
		attributeTypes.add(getAttributeTypeA());
		attributeTypes.add(getAttributeTypeB());
		attributeTypes.add(getAttributeTypeC());
		return attributeTypes;
	}

	public static AttributeTypeMapping getAttributeTypeMappingA() {
		AttributeTypeMapping attributeTypeMapping = new AttributeTypeMapping();
		attributeTypeMapping.setAttributeType(getAttributeTypeA());
		attributeTypeMapping.setDatabaseTableColumn(getDatabaseTableColumnA());
		return attributeTypeMapping;
	}

	public static AttributeTypeMapping getAttributeTypeMappingB() {
		AttributeTypeMapping attributeTypeMapping = new AttributeTypeMapping();
		attributeTypeMapping.setAttributeType(getAttributeTypeB());
		attributeTypeMapping.setDatabaseTableColumn(getDatabaseTableColumnB());
		return attributeTypeMapping;
	}

	public static AttributeTypeMapping getAttributeTypeMappingC() {
		AttributeTypeMapping attributeTypeMapping = new AttributeTypeMapping();
		attributeTypeMapping.setAttributeType(getAttributeTypeC());
		attributeTypeMapping.setDatabaseTableColumn(getDatabaseTableColumnC());
		return attributeTypeMapping;
	}

	public static List<AttributeTypeMapping> getAttributeTypeMappings() {
		List<AttributeTypeMapping> attributeTypeMappings = new ArrayList<AttributeTypeMapping>();
		attributeTypeMappings.add(getAttributeTypeMappingA());
		attributeTypeMappings.add(getAttributeTypeMappingB());
		attributeTypeMappings.add(getAttributeTypeMappingC());
		return attributeTypeMappings;
	}

	public static IAttribute getAttributeA() {
		IAttribute attribute = getAttributeTypeA().getAttribute();
		attribute.setValue("value");
		return attribute;
	}

	public static IAttribute getAttributeB() {
		IAttribute attribute = getAttributeTypeB().getAttribute();
		attribute.setValue(Boolean.TRUE);
		return attribute;
	}

	public static IAttribute getAttributeC() {
		IAttribute attribute = getAttributeTypeC().getAttribute();
		attribute.setValue(8.8);
		return attribute;
	}

	public static List<IAttribute> getAttributes() {
		List<IAttribute> attributes = new ArrayList<IAttribute>();
		attributes.add(getAttributeA());
		attributes.add(getAttributeB());
		attributes.add(getAttributeC());
		return attributes;
	}

	public static IEntityType getEntityTypeA() {
		IEntityType entityType = new EntityType(ENTITY_NAME_A);
		entityType.setAttributeTypes(getAttributeTypes());
		return entityType;
	}

	public static IEntityType getEntityTypeB() {
		IEntityType entityType = new EntityType(ENTITY_NAME_B);
		entityType.setAttributeTypes(getAttributeTypes());
		return entityType;
	}

	public static IEntityType getEntityTypeC() {
		IEntityType entityType = new EntityType(ENTITY_NAME_C);
		entityType.setAttributeTypes(getAttributeTypes());
		return entityType;
	}

	public static List<IEntityType> getEntityTypes() {
		List<IEntityType> entityTypes = new ArrayList<IEntityType>();
		entityTypes.add(getEntityTypeA());
		entityTypes.add(getEntityTypeB());
		entityTypes.add(getEntityTypeC());
		return entityTypes;
	}

	public static EntityTypeMapping getEntityTypeMappingA() {
		EntityTypeMapping entityTypeMapping = new EntityTypeMapping();
		entityTypeMapping.setEntityType(getEntityTypeA());
		entityTypeMapping.setDatabaseTable(getDatabaseTableA());
		entityTypeMapping.setAttributeTypeMappings(getAttributeTypeMappings());
		return entityTypeMapping;
	}

	public static EntityTypeMapping getEntityTypeMappingB() {
		EntityTypeMapping entityTypeMapping = new EntityTypeMapping();
		entityTypeMapping.setEntityType(getEntityTypeB());
		entityTypeMapping.setDatabaseTable(getDatabaseTableB());
		entityTypeMapping.setAttributeTypeMappings(getAttributeTypeMappings());
		return entityTypeMapping;
	}

	public static EntityTypeMapping getEntityTypeMappingC() {
		EntityTypeMapping entityTypeMapping = new EntityTypeMapping();
		entityTypeMapping.setEntityType(getEntityTypeC());
		entityTypeMapping.setDatabaseTable(getDatabaseTableC());
		entityTypeMapping.setAttributeTypeMappings(getAttributeTypeMappings());
		return entityTypeMapping;
	}

	public static List<EntityTypeMapping> getEntityTypeMappings() {
		List<EntityTypeMapping> entityTypeMappings = new ArrayList<EntityTypeMapping>();
		entityTypeMappings.add(getEntityTypeMappingA());
		entityTypeMappings.add(getEntityTypeMappingB());
		entityTypeMappings.add(getEntityTypeMappingC());
		return entityTypeMappings;
	}

	public static IEntity getEntityA() {
		IEntity entity = getEntityTypeA().getEntity();
		try {
			entity.setAttributeValue(ATTRIBUTE_NAME_A, "value");
			entity.setAttributeValue(ATTRIBUTE_NAME_B, Boolean.TRUE);
			entity.setAttributeValue(ATTRIBUTE_NAME_C, 8.8);
		} catch (AttributeTypeNotFoundException e) {
			logger.error(e);
		}

		return entity;
	}

	public static IEntity getEntityB() {
		IEntity entity = getEntityTypeB().getEntity();
		try {
			entity.setAttributeValue(ATTRIBUTE_NAME_A, "value");
			entity.setAttributeValue(ATTRIBUTE_NAME_B, Boolean.TRUE);
			entity.setAttributeValue(ATTRIBUTE_NAME_C, 8.8);
		} catch (AttributeTypeNotFoundException e) {
			logger.error(e);
		}
		return entity;
	}

	public static IEntity getEntityC() {
		IEntity entity = getEntityTypeC().getEntity();
		try {
			entity.setAttributeValue(ATTRIBUTE_NAME_A, "value");
			entity.setAttributeValue(ATTRIBUTE_NAME_B, Boolean.TRUE);
			entity.setAttributeValue(ATTRIBUTE_NAME_C, 8.8);
		} catch (AttributeTypeNotFoundException e) {
			e.printStackTrace();
		}
		return entity;
	}

	public static List<IEntity> getEntities() {
		List<IEntity> entities = new ArrayList<IEntity>();
		entities.add(getEntityA());
		entities.add(getEntityB());
		entities.add(getEntityC());
		return entities;
	}

	public static Driver getDriver() {
		return getDriver(JDBC_DRIVER_CLASS_NAME_PSQL);
	}

	public static DataSource getDataSourceA() {
		return new SimpleDriverDataSource(getDriver(JDBC_DRIVER_HSQL), URL,
				USERNAME, PASSWORD);
	}

	public static DataSource getDataSourceB() {
		return new SimpleDriverDataSource(getDriver(JDBC_DRIVER_HSQL), URL,
				USERNAME, PASSWORD);
	}

	public static DataSource getDataSourceC() {
		return new SimpleDriverDataSource(getDriver(JDBC_DRIVER_HSQL), URL,
				USERNAME, PASSWORD);
	}

	public static List<DataSource> getJdbcDataSources() {
		List<DataSource> dataSources = new ArrayList<DataSource>();
		dataSources.add(getDataSourceA());
		return dataSources;
	}

	public static Database getDatabaseA() {
		Database database = new Database(DATABASE_NAME_A);
		database.setDataSource(getDataSourceA());
		database.setEntityTypeMappings(getEntityTypeMappings());
		return database;
	}

	public static Database getDatabaseB() {
		Database database = new Database(DATABASE_NAME_B);
		database.setDataSource(getDataSourceB());
		database.setEntityTypeMappings(getEntityTypeMappings());
		return database;
	}

	public static Database getDatabaseC() {
		Database database = new Database(DATABASE_NAME_C);
		database.setDataSource(getDataSourceC());
		database.setEntityTypeMappings(getEntityTypeMappings());
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
		databaseTable.setDatabaseTableColumns(getDatabaseTableColumns());
		return databaseTable;
	}

	public static DatabaseTable getDatabaseTableB() {
		DatabaseTable databaseTable = new DatabaseTable(DATABASE_TABLE_NAME_B);
		databaseTable.setDatabaseTableColumns(getDatabaseTableColumns());
		return databaseTable;
	}

	public static DatabaseTable getDatabaseTableC() {
		DatabaseTable databaseTable = new DatabaseTable(DATABASE_TABLE_NAME_C);
		databaseTable.setDatabaseTableColumns(getDatabaseTableColumns());
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

	public static Driver getDriver(String driverClassName) {

		Driver driver = null;

		try {
			driver = (Driver) Class.forName(driverClassName).newInstance();
		} catch (Exception e) {
			logger.error(String.format("Could not instantiate class '%s'",
					driverClassName));
		}

		return driver;
	}

	public static IRelationship getRelationshipA() {
		IRelationship relationship;
		relationship = new Relationship();
		relationship.setName("Relationship A");

		for (IEntity entity : getEntities()) {
			relationship.addReferencedEntity(entity);
		}

		relationship.setCardinality(Cardinality.OneToMany);
		return relationship;
	}

	public static List<IRelationship> getRelationships() {

		List<IRelationship> relationships = new ArrayList<IRelationship>();

		relationships.add(getRelationshipA());

		return relationships;
	}

}
