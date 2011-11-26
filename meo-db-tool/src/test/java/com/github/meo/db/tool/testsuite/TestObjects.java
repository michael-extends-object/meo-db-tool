package com.github.meo.db.tool.testsuite;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.github.meo.db.tool.domain.Attribute;
import com.github.meo.db.tool.domain.AttributeImpl;
import com.github.meo.db.tool.domain.AttributeMapping;
import com.github.meo.db.tool.domain.Cardinality;
import com.github.meo.db.tool.domain.Database;
import com.github.meo.db.tool.domain.DatabaseTable;
import com.github.meo.db.tool.domain.DatabaseTableColumn;
import com.github.meo.db.tool.domain.Entity;
import com.github.meo.db.tool.domain.EntityImpl;
import com.github.meo.db.tool.domain.EntityMapping;
import com.github.meo.db.tool.domain.Relationship;
import com.github.meo.db.tool.domain.RelationshipImpl;

public class TestObjects {

	static Logger logger = Logger.getLogger(TestObjects.class);

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

	public static Attribute getAttributeA() {
		attributeA = new AttributeImpl(ATTRIBUTE_NAME_A);
		attributeA.setValue("value");
		attributeA.setPrimaryKey(true);
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
		attributeC.setPrimaryKey(true);
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
		return getDriver(JDBC_DRIVER_CLASS_NAME_PSQL);
	}

	public static DataSource getDataSourceA() {
		return new SimpleDriverDataSource(getDriver(JDBC_DRIVER_HSQL), URL,
				USERNAME, PASSWORD);
	}

	public static DataSource getJdbcDataSourceA() {
		return new SimpleDriverDataSource(getDriver(JDBC_DRIVER_HSQL), URL,
				USERNAME, PASSWORD);
	}

	public static DataSource getJdbcDataSourceB() {
		return new SimpleDriverDataSource(getDriver(JDBC_DRIVER_HSQL), URL,
				USERNAME, PASSWORD);
	}

	public static DataSource getJdbcDataSourceC() {
		return new SimpleDriverDataSource(getDriver(JDBC_DRIVER_HSQL), URL,
				USERNAME, PASSWORD);
	}

	public static List<DataSource> getJdbcDataSources() {
		List<DataSource> dataSources = new ArrayList<DataSource>();
		dataSources.add(getJdbcDataSourceA());
		return dataSources;
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

	public static Database getDatabaseUserManagementSource() {
		Database databaseUserManagement = new Database();

		databaseUserManagement.addEntityMapping(getEntityMappingUser());
		databaseUserManagement.setName("Database User Management (Source)");

		DataSource dataSource = new SimpleDriverDataSource(
				getDriver(JDBC_DRIVER_HSQL),
				"jdbc:hsqldb:mem:UserManagementSource");
		databaseUserManagement.setDataSource(dataSource);

		databaseUserManagement.addEntityMapping(getEntityMappingUser());
		databaseUserManagement.addEntityMapping(getEntityMappingGroup());

		return databaseUserManagement;
	}

	public static Entity getEntityUser() {

		Entity user = new EntityImpl("User");

		user.addAttribute(new AttributeImpl("User ID"));
		user.addAttribute(new AttributeImpl("Username"));
		user.addAttribute(new AttributeImpl("Password"));
		user.addAttribute(new AttributeImpl("Group ID"));

		user.addRelationship(getRelationshipUserToGroup());
		
		return user;
	}

	public static EntityMapping getEntityMappingUser() {

		EntityMapping entityMappingUser = new EntityMapping();
		Entity entityUser = getEntityUser();

		entityMappingUser.setEntity(entityUser);
		entityMappingUser.setDatabaseTable(new DatabaseTable("USER"));

		AttributeMapping userIdMapping = new AttributeMapping();
		userIdMapping.setAttribute(entityUser.getAttribute("User ID"));
		userIdMapping.setDatabaseTableColumn(new DatabaseTableColumn("ID"));

		AttributeMapping usernameMapping = new AttributeMapping();
		usernameMapping.setAttribute(entityUser.getAttribute("Username"));
		usernameMapping.setDatabaseTableColumn(new DatabaseTableColumn(
				"USERNAME"));

		AttributeMapping passwordMapping = new AttributeMapping();
		passwordMapping.setAttribute(entityUser.getAttribute("Password"));
		passwordMapping.setDatabaseTableColumn(new DatabaseTableColumn(
				"PASSWORD"));

		AttributeMapping groupIdMapping = new AttributeMapping();
		groupIdMapping.setAttribute(entityUser.getAttribute("Group ID"));
		groupIdMapping.setDatabaseTableColumn(new DatabaseTableColumn(
				"GROUP_ID"));

		entityMappingUser.addAttributeMapping(userIdMapping);
		entityMappingUser.addAttributeMapping(usernameMapping);
		entityMappingUser.addAttributeMapping(passwordMapping);
		entityMappingUser.addAttributeMapping(groupIdMapping);

		return entityMappingUser;
	}

	public static Entity getEntityGroup() {
		Entity group = new EntityImpl("User Group");

		Attribute groupId = new AttributeImpl("Group ID");
		Attribute username = new AttributeImpl("Group Name");

		group.addAttribute(groupId);
		group.addAttribute(username);

		return group;
	}

	public static EntityMapping getEntityMappingGroup() {

		EntityMapping entityMappingGroup = new EntityMapping();
		Entity entityGroup = getEntityGroup();

		entityMappingGroup.setEntity(entityGroup);
		entityMappingGroup.setDatabaseTable(new DatabaseTable("USER_GROUP"));

		AttributeMapping userIdMapping = new AttributeMapping();
		userIdMapping.setAttribute(entityGroup.getAttribute("Group ID"));
		userIdMapping.setDatabaseTableColumn(new DatabaseTableColumn("ID"));

		AttributeMapping groupnameMapping = new AttributeMapping();
		groupnameMapping.setAttribute(entityGroup.getAttribute("Group Name"));
		groupnameMapping.setDatabaseTableColumn(new DatabaseTableColumn(
				"GROUPNAME"));

		entityMappingGroup.addAttributeMapping(userIdMapping);
		entityMappingGroup.addAttributeMapping(groupnameMapping);

		return entityMappingGroup;
	}

	public static Relationship getRelationshipUserToGroup() {

		String relationshipName = "User to Group";
		Entity group = getEntityGroup();
		Cardinality cardinality = Cardinality.OneToMany;

		Relationship relationship = new RelationshipImpl();
		relationship.setName(relationshipName);
		relationship.setReferencedEntity(group);
		relationship.setCardinality(cardinality);

		return relationship;
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

	public static Relationship getRelationshipA() {
		Relationship relationshipA = new RelationshipImpl();
		return relationshipA;
	}

	public static List<Relationship> getRelationships() {

		List<Relationship> relationships = new ArrayList<Relationship>();

		relationships.add(getRelationshipA());

		return relationships;
	}

}
