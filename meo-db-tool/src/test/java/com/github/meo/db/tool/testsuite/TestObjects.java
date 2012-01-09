package com.github.meo.db.tool.testsuite;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.github.meo.db.tool.dao.Database;
import com.github.meo.db.tool.domain.AttributeType;
import com.github.meo.db.tool.domain.Cardinality;
import com.github.meo.db.tool.domain.Erm;
import com.github.meo.db.tool.domain.EntityType;
import com.github.meo.db.tool.domain.IAttribute;
import com.github.meo.db.tool.domain.IAttributeType;
import com.github.meo.db.tool.domain.IEntity;
import com.github.meo.db.tool.domain.IErm;
import com.github.meo.db.tool.domain.IEntityType;
import com.github.meo.db.tool.domain.IRelationship;
import com.github.meo.db.tool.domain.IRelationshipType;
import com.github.meo.db.tool.domain.Relationship;
import com.github.meo.db.tool.domain.RelationshipType;
import com.github.meo.db.tool.domain.db.Table;
import com.github.meo.db.tool.domain.db.Column;
import com.github.meo.db.tool.domain.mapping.AttributeTypeMapping;
import com.github.meo.db.tool.domain.mapping.ErmMapping;
import com.github.meo.db.tool.domain.mapping.EntityTypeMapping;

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

	public static final String DATABASE_TABLE_NAME_A = "Table A";
	public static final String DATABASE_TABLE_NAME_B = "Table B";
	public static final String DATABASE_TABLE_NAME_C = "Table C";

	public static final String COLUMN_NAME_A = "Column A";
	public static final String COLUMN_NAME_B = "Column B";
	public static final String COLUMN_NAME_C = "Column C";

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
		attributeTypeMapping.setColumn(getColumnA());
		return attributeTypeMapping;
	}

	public static AttributeTypeMapping getAttributeTypeMappingB() {
		AttributeTypeMapping attributeTypeMapping = new AttributeTypeMapping();
		attributeTypeMapping.setAttributeType(getAttributeTypeB());
		attributeTypeMapping.setColumn(getColumnB());
		return attributeTypeMapping;
	}

	public static AttributeTypeMapping getAttributeTypeMappingC() {
		AttributeTypeMapping attributeTypeMapping = new AttributeTypeMapping();
		attributeTypeMapping.setAttributeType(getAttributeTypeC());
		attributeTypeMapping.setColumn(getColumnC());
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
		IAttribute attribute = getAttributeTypeA().createAttribute();
		attribute.setValue("value");
		return attribute;
	}

	public static IAttribute getAttributeB() {
		IAttribute attribute = getAttributeTypeB().createAttribute();
		attribute.setValue(Boolean.TRUE);
		return attribute;
	}

	public static IAttribute getAttributeC() {
		IAttribute attribute = getAttributeTypeC().createAttribute();
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
		entityTypeMapping.setTable(getTableA());
		entityTypeMapping.setAttributeTypeMappings(getAttributeTypeMappings());
		return entityTypeMapping;
	}

	public static EntityTypeMapping getEntityTypeMappingB() {
		EntityTypeMapping entityTypeMapping = new EntityTypeMapping();
		entityTypeMapping.setEntityType(getEntityTypeB());
		entityTypeMapping.setTable(getTableB());
		entityTypeMapping.setAttributeTypeMappings(getAttributeTypeMappings());
		return entityTypeMapping;
	}

	public static EntityTypeMapping getEntityTypeMappingC() {
		EntityTypeMapping entityTypeMapping = new EntityTypeMapping();
		entityTypeMapping.setEntityType(getEntityTypeC());
		entityTypeMapping.setTable(getTableC());
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
		entity.setAttributeValue(ATTRIBUTE_NAME_A, "value");
		entity.setAttributeValue(ATTRIBUTE_NAME_B, Boolean.TRUE);
		entity.setAttributeValue(ATTRIBUTE_NAME_C, 8.8);
		return entity;
	}

	public static IEntity getEntityB() {
		IEntity entity = getEntityTypeB().getEntity();
		entity.setAttributeValue(ATTRIBUTE_NAME_A, "value");
		entity.setAttributeValue(ATTRIBUTE_NAME_B, Boolean.TRUE);
		entity.setAttributeValue(ATTRIBUTE_NAME_C, 8.8);
		return entity;
	}

	public static IEntity getEntityC() {
		IEntity entity = getEntityTypeC().getEntity();
		entity.setAttributeValue(ATTRIBUTE_NAME_A, "value");
		entity.setAttributeValue(ATTRIBUTE_NAME_B, Boolean.TRUE);
		entity.setAttributeValue(ATTRIBUTE_NAME_C, 8.8);
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
		database.addEntityRelationshipModel(getErmA());
		database.addEntityRelationshipModel(getErmB());
		database.addEntityRelationshipModel(getErmC());
		database.addErmMapping(getErmMappingA());
		database.addErmMapping(getErmMappingB());
		database.addErmMapping(getErmMappingC());
		database.setCurrentErm(getErmA());
		return database;
	}

	public static Database getDatabaseB() {
		Database database = new Database(DATABASE_NAME_B);
		database.setDataSource(getDataSourceB());
		database.addEntityRelationshipModel(getErmA());
		database.addEntityRelationshipModel(getErmB());
		database.addEntityRelationshipModel(getErmC());
		database.addErmMapping(getErmMappingA());
		database.addErmMapping(getErmMappingB());
		database.addErmMapping(getErmMappingC());
		database.setCurrentErm(getErmB());
		return database;
	}

	public static Database getDatabaseC() {
		Database database = new Database(DATABASE_NAME_C);
		database.setDataSource(getDataSourceC());
		database.addEntityRelationshipModel(getErmA());
		database.addEntityRelationshipModel(getErmB());
		database.addEntityRelationshipModel(getErmC());
		database.addErmMapping(getErmMappingA());
		database.addErmMapping(getErmMappingB());
		database.addErmMapping(getErmMappingC());
		database.setCurrentErm(getErmC());
		return database;
	}

	public static List<Database> getDatabases() {
		List<Database> databases = new ArrayList<Database>();
		databases.add(getDatabaseA());
		databases.add(getDatabaseB());
		databases.add(getDatabaseC());
		return databases;
	}

	public static Table getTableA() {
		Table databaseTable = new Table(DATABASE_TABLE_NAME_A);
		databaseTable.setColumns(getColumns());
		return databaseTable;
	}

	public static Table getTableB() {
		Table databaseTable = new Table(DATABASE_TABLE_NAME_B);
		databaseTable.setColumns(getColumns());
		return databaseTable;
	}

	public static Table getTableC() {
		Table databaseTable = new Table(DATABASE_TABLE_NAME_C);
		databaseTable.setColumns(getColumns());
		return databaseTable;
	}

	public static List<Table> getTables() {
		List<Table> tables = new ArrayList<Table>();
		tables.add(getTableA());
		tables.add(getTableB());
		tables.add(getTableC());
		return tables;
	}

	public static Column getColumnA() {
		Column column = new Column(COLUMN_NAME_A);
		return column;
	}

	public static Column getColumnB() {
		Column column = new Column(COLUMN_NAME_B);
		return column;
	}

	public static Column getColumnC() {
		Column column = new Column(COLUMN_NAME_C);
		return column;
	}

	public static List<Column> getColumns() {
		List<Column> columns = new ArrayList<Column>();
		columns.add(getColumnA());
		columns.add(getColumnB());
		columns.add(getColumnC());
		return columns;
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

	public static IRelationshipType getRelationshipTypeA() {
		IRelationshipType relationshipType = new RelationshipType(
				"Relationship A");
		relationshipType.setEntityType(getEntityTypeA());
		relationshipType.setReferencedEntityType(getEntityTypeB());
		relationshipType.setCardinality(Cardinality.OneToMany);
		return relationshipType;
	}

	public static IRelationshipType getRelationshipTypeB() {
		IRelationshipType relationshipType = new RelationshipType(
				"Relationship B");
		relationshipType.setEntityType(getEntityTypeB());
		relationshipType.setReferencedEntityType(getEntityTypeC());
		relationshipType.setCardinality(Cardinality.ManyToMany);
		return relationshipType;
	}

	public static IRelationshipType getRelationshipTypeC() {
		IRelationshipType relationshipType = new RelationshipType(
				"Relationship C");
		relationshipType.setEntityType(getEntityTypeC());
		relationshipType.setReferencedEntityType(getEntityTypeA());
		relationshipType.setCardinality(Cardinality.OneToOne);
		return relationshipType;
	}

	public static List<IRelationshipType> getRelationshipTypes() {
		List<IRelationshipType> relationshipTypes = new ArrayList<IRelationshipType>();
		relationshipTypes.add(getRelationshipTypeA());
		relationshipTypes.add(getRelationshipTypeB());
		relationshipTypes.add(getRelationshipTypeC());
		return relationshipTypes;
	}

	public static IRelationship getRelationshipA() {
		IRelationship relationship = new Relationship(getRelationshipTypeA());
		relationship.setEntity(getEntityA());
		relationship.addReferencedEntity(getEntityB());
		relationship.addReferencedEntity(getEntityB());
		relationship.addReferencedEntity(getEntityB());
		return relationship;
	}

	public static IRelationship getRelationshipB() {
		IRelationship relationship = new Relationship(getRelationshipTypeB());
		relationship.setEntity(getEntityB());
		relationship.addReferencedEntity(getEntityC());
		relationship.addReferencedEntity(getEntityC());
		relationship.addReferencedEntity(getEntityC());
		return relationship;
	}

	public static IRelationship getRelationshipC() {
		IRelationship relationship = new Relationship(getRelationshipTypeB());
		relationship.setEntity(getEntityC());
		relationship.addReferencedEntity(getEntityA());
		return relationship;
	}

	public static List<IRelationship> getRelationships() {

		List<IRelationship> relationships = new ArrayList<IRelationship>();

		relationships.add(getRelationshipA());
		relationships.add(getRelationshipB());
		relationships.add(getRelationshipC());

		return relationships;
	}

	public static IErm getErmA() {
		IErm erm = new Erm("ERM A");
		erm.addEntityType(getEntityTypeA());
		erm.addEntityType(getEntityTypeB());
		erm.addEntityType(getEntityTypeC());
		erm.setRelationshipTypes(getRelationshipTypes());
		return erm;
	}

	public static IErm getErmB() {
		IErm erm = new Erm("ERM B");
		erm.addEntityType(getEntityTypeA());
		erm.addEntityType(getEntityTypeB());
		erm.addEntityType(getEntityTypeC());
		erm.setRelationshipTypes(getRelationshipTypes());
		return erm;
	}

	public static IErm getErmC() {
		IErm erm = new Erm("ERM C");
		erm.addEntityType(getEntityTypeA());
		erm.addEntityType(getEntityTypeB());
		erm.addEntityType(getEntityTypeC());
		erm.setRelationshipTypes(getRelationshipTypes());
		return erm;
	}

	public static ErmMapping getErmMappingA() {
		ErmMapping ermMapping = new ErmMapping();
		ermMapping.setErm(getErmA());
		ermMapping.addEntityTypeMapping(getEntityTypeMappingA());
		ermMapping.addEntityTypeMapping(getEntityTypeMappingB());
		ermMapping.addEntityTypeMapping(getEntityTypeMappingC());
		return ermMapping;
	}

	public static ErmMapping getErmMappingB() {
		ErmMapping ermMapping = new ErmMapping();
		ermMapping.setErm(getErmB());
		ermMapping.addEntityTypeMapping(getEntityTypeMappingA());
		ermMapping.addEntityTypeMapping(getEntityTypeMappingB());
		ermMapping.addEntityTypeMapping(getEntityTypeMappingC());
		return ermMapping;
	}

	public static ErmMapping getErmMappingC() {
		ErmMapping ermMapping = new ErmMapping();
		ermMapping.setErm(getErmC());
		ermMapping.addEntityTypeMapping(getEntityTypeMappingA());
		ermMapping.addEntityTypeMapping(getEntityTypeMappingB());
		ermMapping.addEntityTypeMapping(getEntityTypeMappingC());
		return ermMapping;
	}

	public static List<ErmMapping> getErmMappings() {
		List<ErmMapping> ermMappings = new ArrayList<ErmMapping>();
		ermMappings.add(getErmMappingA());
		ermMappings.add(getErmMappingB());
		ermMappings.add(getErmMappingC());
		return ermMappings;
	}

	public static List<IErm> getErms() {
		List<IErm> erms = new ArrayList<IErm>();
		erms.add(getErmA());
		erms.add(getErmB());
		erms.add(getErmC());
		return erms;
	}

}
