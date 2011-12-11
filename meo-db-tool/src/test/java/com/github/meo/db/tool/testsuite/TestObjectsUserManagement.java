package com.github.meo.db.tool.testsuite;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.github.meo.db.tool.domain.EntityRelationshipModel;
import com.github.meo.db.tool.domain.IAttributeType;
import com.github.meo.db.tool.domain.AttributeType;
import com.github.meo.db.tool.domain.AttributeTypeMapping;
import com.github.meo.db.tool.domain.Cardinality;
import com.github.meo.db.tool.domain.Database;
import com.github.meo.db.tool.domain.DatabaseTable;
import com.github.meo.db.tool.domain.DatabaseTableColumn;
import com.github.meo.db.tool.domain.IEntity;
import com.github.meo.db.tool.domain.IEntityRelationshipModel;
import com.github.meo.db.tool.domain.IEntityType;
import com.github.meo.db.tool.domain.EntityType;
import com.github.meo.db.tool.domain.EntityTypeMapping;
import com.github.meo.db.tool.domain.IRelationship;
import com.github.meo.db.tool.domain.Relationship;
import com.github.meo.db.tool.exception.AttributeTypeNotFoundException;

public class TestObjectsUserManagement {

	static Logger logger = Logger.getLogger(TestObjectsUserManagement.class);

	public static final String JDBC_DRIVER_HSQL = "org.hsqldb.jdbcDriver";
	public static final String DB_URL_HSQL_SOURCE = "jdbc:hsqldb:mem:UserManagementSource";
	public static final String DB_URL_HSQL_TARGET = "jdbc:hsqldb:mem:UserManagementTarget";

	public static IAttributeType getAttributeTypeUserId() {
		return new AttributeType("User Id");
	}

	public static IAttributeType getAttributeTypeUserName() {
		return new AttributeType("Username");
	}

	public static IAttributeType getAttributeTypeUserPassword() {
		return new AttributeType("Password");
	}

	public static IAttributeType getAttributeTypeUserGroupId() {
		return new AttributeType("Group Id");
	}

	public static IAttributeType getAttributeTypeGroupId() {
		return new AttributeType("Group Id");
	}

	public static IAttributeType getAttributeTypeGroupName() {
		return new AttributeType("Groupname");
	}

	public static List<IAttributeType> getAttributeTypesUser() {
		List<IAttributeType> attributeTypesUser = new ArrayList<IAttributeType>();
		attributeTypesUser.add(getAttributeTypeUserId());
		attributeTypesUser.add(getAttributeTypeUserName());
		attributeTypesUser.add(getAttributeTypeUserPassword());
		attributeTypesUser.add(getAttributeTypeUserGroupId());
		return attributeTypesUser;
	}

	public static List<IAttributeType> getAttributeTypesGroup() {
		List<IAttributeType> attributeTypesGroup = new ArrayList<IAttributeType>();
		attributeTypesGroup.add(getAttributeTypeGroupId());
		attributeTypesGroup.add(getAttributeTypeGroupName());
		return attributeTypesGroup;
	}
	
	public static IEntityRelationshipModel getErm() {
		IEntityRelationshipModel erm = new EntityRelationshipModel("ERM");
		erm.addEntityType(getTypeUser());
		erm.addEntityType(getTypeGroup());
		erm.addRelationship(getRelationshipUserToGroup());
		return erm;
	}

	public static DataSource getDataSource() {
		Driver driver = TestObjects.getDriver(JDBC_DRIVER_HSQL);
		return new SimpleDriverDataSource(driver, DB_URL_HSQL_SOURCE);
	}

	public static Database getDatabaseUserManagementSource() {
		Database databaseUserManagement = new Database();

		databaseUserManagement.setName("Database User Management (Source)");
		databaseUserManagement.setDataSource(getDataSource());
		databaseUserManagement.addEntityTypeMapping(getEntityTypeMappingUser());
		databaseUserManagement
				.addEntityTypeMapping(getEntityTypeMappingGroup());

		return databaseUserManagement;
	}

	public static IEntityType getTypeUser() {
		IEntityType user = new EntityType("User");
		user.setAttributeTypes(getAttributeTypesUser());
		return user;
	}

	public static IEntityType getTypeGroup() {
		IEntityType group = new EntityType("User Group");
		group.setAttributeTypes(getAttributeTypesGroup());
		return group;
	}

	public static IEntity getUserA() {
		IEntity user = getTypeUser().getEntity();
		try {
			user.setAttributeValue("User Id", 5);
			user.setAttributeValue("Username", "Peter");
			user.setAttributeValue("Password", "12345");
			user.setAttributeValue("Group Id", 4);
		} catch (AttributeTypeNotFoundException e) {
			e.printStackTrace();
		}
		return user;
	}

	public static IEntity getGroupA() {
		IEntity group = getTypeGroup().getEntity();
		try {
			group.setAttributeValue("Group Id", 1);
			group.setAttributeValue("Groupname", "Group A");
		} catch (AttributeTypeNotFoundException e) {
			e.printStackTrace();
		}

		return group;
	}

	public static List<AttributeTypeMapping> getAttributeTypeMappingsUser() {
		List<AttributeTypeMapping> attributeTypeMappingsUser = new ArrayList<AttributeTypeMapping>();
		AttributeTypeMapping mappingUserId = new AttributeTypeMapping(
				getAttributeTypeUserId(), new DatabaseTableColumn("ID"));
		AttributeTypeMapping mappingUserName = new AttributeTypeMapping(
				getAttributeTypeUserName(), new DatabaseTableColumn("USERNAME"));
		AttributeTypeMapping mappingUserPassword = new AttributeTypeMapping(
				getAttributeTypeUserPassword(), new DatabaseTableColumn(
						"PASSWORD"));
		AttributeTypeMapping mappingUserGroupId = new AttributeTypeMapping(
				getAttributeTypeUserGroupId(), new DatabaseTableColumn(
						"GROUP_ID"));
		attributeTypeMappingsUser.add(mappingUserId);
		attributeTypeMappingsUser.add(mappingUserName);
		attributeTypeMappingsUser.add(mappingUserPassword);
		attributeTypeMappingsUser.add(mappingUserGroupId);
		return attributeTypeMappingsUser;
	}

	public static List<AttributeTypeMapping> getAttributeTypeMappingsGroup() {
		List<AttributeTypeMapping> attributeTypeMappingsUser = new ArrayList<AttributeTypeMapping>();
		AttributeTypeMapping mappingGroupId = new AttributeTypeMapping(
				getAttributeTypeGroupId(), new DatabaseTableColumn("ID"));
		AttributeTypeMapping mappingGroupName = new AttributeTypeMapping(
				getAttributeTypeGroupName(), new DatabaseTableColumn(
						"GROUPNAME"));
		attributeTypeMappingsUser.add(mappingGroupId);
		attributeTypeMappingsUser.add(mappingGroupName);
		return attributeTypeMappingsUser;
	}

	public static EntityTypeMapping getEntityTypeMappingUser() {
		EntityTypeMapping entityTypeMappingUser = new EntityTypeMapping();
		entityTypeMappingUser.setEntityType(getTypeUser());
		entityTypeMappingUser.setDatabaseTable(new DatabaseTable("USER"));
		entityTypeMappingUser
				.setAttributeTypeMappings(getAttributeTypeMappingsUser());
		return entityTypeMappingUser;
	}

	public static EntityTypeMapping getEntityTypeMappingGroup() {
		EntityTypeMapping entityTypeMappingGroup = new EntityTypeMapping();
		entityTypeMappingGroup.setEntityType(getTypeGroup());
		entityTypeMappingGroup
				.setDatabaseTable(new DatabaseTable("USER_GROUP"));
		entityTypeMappingGroup
				.setAttributeTypeMappings(getAttributeTypeMappingsGroup());
		return entityTypeMappingGroup;
	}

	public static IRelationship getRelationshipUserToGroup() {

		String relationshipName = "User to Group";
		IEntityType user = getTypeUser();
		IEntityType group = getTypeGroup();
		Cardinality cardinality = Cardinality.OneToMany;

		IRelationship relationship = new Relationship(relationshipName);
		relationship.setEntity(user);
		relationship.setReferencedEntity(group);
		relationship.setCardinality(cardinality);

		return relationship;
	}

}
