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
import com.github.meo.db.tool.domain.IAttributeType;
import com.github.meo.db.tool.domain.IEntity;
import com.github.meo.db.tool.domain.IErm;
import com.github.meo.db.tool.domain.IEntityType;
import com.github.meo.db.tool.domain.IRelationshipType;
import com.github.meo.db.tool.domain.RelationshipType;
import com.github.meo.db.tool.domain.db.Table;
import com.github.meo.db.tool.domain.db.Column;
import com.github.meo.db.tool.domain.mapping.AttributeTypeMapping;
import com.github.meo.db.tool.domain.mapping.ErmMapping;
import com.github.meo.db.tool.domain.mapping.EntityTypeMapping;
import com.github.meo.db.tool.domain.mapping.RelationshipTypeMapping;

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

	public static IErm getErm() {
		IErm erm = new Erm("ERM");
		erm.addEntityType(getTypeUser());
		erm.addEntityType(getTypeGroup());
		erm.addRelationshipType(getRelationshipUserToGroup());
		return erm;
	}

	public static ErmMapping getErmMapping() {
		ErmMapping ermMapping = new ErmMapping();
		ermMapping.setErm(getErm());
		ermMapping.setErm(getErm());
		ermMapping.addEntityTypeMapping(getEntityTypeMappingUser());
		ermMapping.addEntityTypeMapping(getEntityTypeMappingGroup());
		ermMapping
				.addRelationshipTypeMapping(getRelationshipMappingUserToGroup());
		return ermMapping;
	}

	public static DataSource getDataSource() {
		Driver driver = TestObjects.getDriver(JDBC_DRIVER_HSQL);
		return new SimpleDriverDataSource(driver, DB_URL_HSQL_SOURCE);
	}

	public static Database getDatabaseUserManagementSource() {
		Database databaseUserManagement = new Database();
		databaseUserManagement.setName("Database User Management (Source)");
		databaseUserManagement.setDataSource(getDataSource());
		databaseUserManagement.addEntityRelationshipModel(getErm());
		databaseUserManagement
				.addErmMapping(getErmMapping());
		databaseUserManagement.setCurrentErm(getErm());
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
		user.setAttributeValue("User Id", 5);
		user.setAttributeValue("Username", "Peter");
		user.setAttributeValue("Password", "12345");
		user.setAttributeValue("Group Id", 4);
		return user;
	}

	public static IEntity getGroupA() {
		IEntity group = getTypeGroup().getEntity();
		group.setAttributeValue("Group Id", 1);
		group.setAttributeValue("Groupname", "Group A");
		return group;
	}

	public static List<AttributeTypeMapping> getAttributeTypeMappingsUser() {
		List<AttributeTypeMapping> attributeTypeMappingsUser = new ArrayList<AttributeTypeMapping>();
		AttributeTypeMapping mappingUserId = new AttributeTypeMapping(
				getAttributeTypeUserId(), new Column("ID"));
		AttributeTypeMapping mappingUserName = new AttributeTypeMapping(
				getAttributeTypeUserName(), new Column("USERNAME"));
		AttributeTypeMapping mappingUserPassword = new AttributeTypeMapping(
				getAttributeTypeUserPassword(), new Column(
						"PASSWORD"));
		AttributeTypeMapping mappingUserGroupId = new AttributeTypeMapping(
				getAttributeTypeUserGroupId(), new Column(
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
				getAttributeTypeGroupId(), new Column("ID"));
		AttributeTypeMapping mappingGroupName = new AttributeTypeMapping(
				getAttributeTypeGroupName(), new Column(
						"GROUPNAME"));
		attributeTypeMappingsUser.add(mappingGroupId);
		attributeTypeMappingsUser.add(mappingGroupName);
		return attributeTypeMappingsUser;
	}

	public static EntityTypeMapping getEntityTypeMappingUser() {
		EntityTypeMapping entityTypeMappingUser = new EntityTypeMapping();
		entityTypeMappingUser.setEntityType(getTypeUser());
		entityTypeMappingUser.setTable(new Table("USER"));
		entityTypeMappingUser
				.setAttributeTypeMappings(getAttributeTypeMappingsUser());
		return entityTypeMappingUser;
	}

	public static EntityTypeMapping getEntityTypeMappingGroup() {
		EntityTypeMapping entityTypeMappingGroup = new EntityTypeMapping();
		entityTypeMappingGroup.setEntityType(getTypeGroup());
		entityTypeMappingGroup
				.setTable(new Table("USER_GROUP"));
		entityTypeMappingGroup
				.setAttributeTypeMappings(getAttributeTypeMappingsGroup());
		return entityTypeMappingGroup;
	}

	public static IRelationshipType getRelationshipUserToGroup() {

		String relationshipName = "User to Group";
		IEntityType user = getTypeUser();
		IEntityType group = getTypeGroup();
		Cardinality cardinality = Cardinality.OneToMany;

		IRelationshipType relationshipType = new RelationshipType(
				relationshipName);
		relationshipType.setEntityType(user);
		relationshipType.setReferencedEntityType(group);
		relationshipType.setCardinality(cardinality);

		return relationshipType;
	}

	public static RelationshipTypeMapping getRelationshipMappingUserToGroup() {
		RelationshipTypeMapping relationshipMapping = new RelationshipTypeMapping(
				getRelationshipUserToGroup());
		return relationshipMapping;
	}

}
