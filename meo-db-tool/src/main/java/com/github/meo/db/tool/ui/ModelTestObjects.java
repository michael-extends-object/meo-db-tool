package com.github.meo.db.tool.ui;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.github.meo.db.tool.dao.Database;
//import com.github.meo.db.tool.domain.EntityRelationshipModel;
import com.github.meo.db.tool.domain.AttributeType;
import com.github.meo.db.tool.domain.Cardinality;
import com.github.meo.db.tool.domain.EntityRelationshipModel;
import com.github.meo.db.tool.domain.EntityType;
import com.github.meo.db.tool.domain.IAttributeType;
import com.github.meo.db.tool.domain.IEntityRelationshipModel;
import com.github.meo.db.tool.domain.IEntityType;
import com.github.meo.db.tool.domain.IRelationshipType;
import com.github.meo.db.tool.domain.RelationshipType;
import com.github.meo.db.tool.domain.db.Table;
import com.github.meo.db.tool.domain.db.Column;
import com.github.meo.db.tool.domain.mapping.AttributeTypeMapping;
import com.github.meo.db.tool.domain.mapping.EntityRelationshipModelMapping;
import com.github.meo.db.tool.domain.mapping.EntityTypeMapping;
import com.github.meo.db.tool.domain.mapping.IEntityRelationshipModelMapping;
import com.github.meo.db.tool.domain.mapping.RelationshipTypeMapping;

public class ModelTestObjects {

	public static final String JDBC_DRIVER_PSQL = "org.postgresql.Driver";
	public static final String DB_URL_SOURCE = "jdbc:postgresql://localhost/ACCOUNT_MGT_SOURCE";
	public static final String DB_URL_TARGET = "jdbc:postgresql://localhost/ACCOUNT_MGT_TARGET";

	public static IAttributeType getAttributeTypeUserId() {
		return new AttributeType("ID");
	}

	public static IAttributeType getAttributeTypeUserName() {
		return new AttributeType("Name");
	}

	public static IAttributeType getAttributeTypeUserPassword() {
		return new AttributeType("Password");
	}

	public static IAttributeType getAttributeTypeAccountLocked() {
		return new AttributeType("Locked");
	}

	public static IAttributeType getAttributeTypeAccountExpiration() {
		return new AttributeType("Expiration");
	}

	public static IAttributeType getAttributeTypeUserGroupId() {
		return new AttributeType("Group ID");
	}

	public static IAttributeType getAttributeTypeGroupId() {
		return new AttributeType("ID");
	}

	public static IAttributeType getAttributeTypeGroupName() {
		return new AttributeType("Name");
	}

	public static List<IAttributeType> getAttributeTypesUser() {
		List<IAttributeType> attributeTypesUser = new ArrayList<IAttributeType>();
		attributeTypesUser.add(getAttributeTypeUserId());
		attributeTypesUser.add(getAttributeTypeUserName());
		attributeTypesUser.add(getAttributeTypeUserPassword());
		attributeTypesUser.add(getAttributeTypeAccountLocked());
		attributeTypesUser.add(getAttributeTypeAccountExpiration());
		attributeTypesUser.add(getAttributeTypeUserGroupId());
		return attributeTypesUser;
	}

	public static List<IAttributeType> getAttributeTypesGroup() {
		List<IAttributeType> attributeTypesGroup = new ArrayList<IAttributeType>();
		attributeTypesGroup.add(getAttributeTypeGroupId());
		attributeTypesGroup.add(getAttributeTypeGroupName());
		return attributeTypesGroup;
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

	public static IRelationshipType getRelationshipUserToGroup() {

		String relationshipName = "User to Group";
		IEntityType user = getTypeUser();
		IEntityType group = getTypeGroup();
		Cardinality cardinality = Cardinality.ManyToMany;

		IRelationshipType relationshipType = new RelationshipType(
				relationshipName);
		relationshipType.setEntityType(user);
		relationshipType.setReferencedEntityType(group);
		relationshipType.setCardinality(cardinality);

		return relationshipType;
	}

	public static IEntityRelationshipModel getErm() {
		IEntityRelationshipModel erm = new EntityRelationshipModel("ERM");
		erm.addEntityType(getTypeUser());
		erm.addEntityType(getTypeGroup());
		erm.addRelationshipType(getRelationshipUserToGroup());
		return erm;
	}

	public static IEntityRelationshipModelMapping getErmMapping() {
		IEntityRelationshipModelMapping ermMapping = new EntityRelationshipModelMapping();
		ermMapping.setEntityRelationshipModel(getErm());
		ermMapping.addEntityTypeMapping(getEntityTypeMappingUser());
		ermMapping.addEntityTypeMapping(getEntityTypeMappingGroup());
		ermMapping
				.addRelationshipTypeMapping(getRelationshipTypeMappingUserGroup());
		return ermMapping;
	}

	public static DataSource getDataSourceSource() {
		Driver driver = getDriver(JDBC_DRIVER_PSQL);
		return new SimpleDriverDataSource(driver, DB_URL_SOURCE, "postgres",
				"postgres");
	}

	public static DataSource getDataSourceTarget() {
		Driver driver = getDriver(JDBC_DRIVER_PSQL);
		return new SimpleDriverDataSource(driver, DB_URL_TARGET, "postgres",
				"postgres");
	}

	public static List<AttributeTypeMapping> getAttributeTypeMappingsUser() {
		List<AttributeTypeMapping> attributeTypeMappingsUser = new ArrayList<AttributeTypeMapping>();
		AttributeTypeMapping mappingUserId = new AttributeTypeMapping(
				getAttributeTypeUserId(), new Column("ID"));
		AttributeTypeMapping mappingUserName = new AttributeTypeMapping(
				getAttributeTypeUserName(), new Column("NAME"));
		AttributeTypeMapping mappingUserPassword = new AttributeTypeMapping(
				getAttributeTypeUserPassword(), new Column("PASSWORD"));
		AttributeTypeMapping mappingAccountLocked = new AttributeTypeMapping(
				getAttributeTypeAccountLocked(), new Column("LOCKED"));
		AttributeTypeMapping mappingAccountExpiration = new AttributeTypeMapping(
				getAttributeTypeAccountExpiration(), new Column("EXPIRATION"));
		AttributeTypeMapping mappingUserGroupId = new AttributeTypeMapping(
				getAttributeTypeUserGroupId(), new Column("GROUP_ID"));
		attributeTypeMappingsUser.add(mappingUserId);
		attributeTypeMappingsUser.add(mappingUserName);
		attributeTypeMappingsUser.add(mappingUserPassword);
		attributeTypeMappingsUser.add(mappingAccountLocked);
		attributeTypeMappingsUser.add(mappingAccountExpiration);
		attributeTypeMappingsUser.add(mappingUserGroupId);
		return attributeTypeMappingsUser;
	}

	public static List<AttributeTypeMapping> getAttributeTypeMappingsGroup() {
		List<AttributeTypeMapping> attributeTypeMappingsUser = new ArrayList<AttributeTypeMapping>();
		AttributeTypeMapping mappingGroupId = new AttributeTypeMapping(
				getAttributeTypeGroupId(), new Column("ID"));
		AttributeTypeMapping mappingGroupName = new AttributeTypeMapping(
				getAttributeTypeGroupName(), new Column("NAME"));
		attributeTypeMappingsUser.add(mappingGroupId);
		attributeTypeMappingsUser.add(mappingGroupName);
		return attributeTypeMappingsUser;
	}

	public static EntityTypeMapping getEntityTypeMappingUser() {
		EntityTypeMapping entityTypeMappingUser = new EntityTypeMapping();
		entityTypeMappingUser.setEntityType(getTypeUser());
		entityTypeMappingUser.setTable(new Table("USERS"));
		entityTypeMappingUser
				.setAttributeTypeMappings(getAttributeTypeMappingsUser());
		return entityTypeMappingUser;
	}

	public static EntityTypeMapping getEntityTypeMappingGroup() {
		EntityTypeMapping entityTypeMappingGroup = new EntityTypeMapping();
		entityTypeMappingGroup.setEntityType(getTypeGroup());
		entityTypeMappingGroup.setTable(new Table("USER_GROUPS"));
		entityTypeMappingGroup
				.setAttributeTypeMappings(getAttributeTypeMappingsGroup());
		return entityTypeMappingGroup;
	}

	public static Database getDatabaseAccountManagementSource() {
		Database databaseUserManagement = new Database();
		databaseUserManagement.setName("Account Management (Source)");
		databaseUserManagement.setDataSource(getDataSourceSource());
		databaseUserManagement.addEntityRelationshipModel(getErm());
		databaseUserManagement
				.addEntityRelationshipModelMapping(getErmMapping());
		return databaseUserManagement;
	}

	public static Database getDatabaseAccountManagementTarget() {
		Database databaseUserManagement = new Database();
		databaseUserManagement.setName("Account Management (Target)");
		databaseUserManagement.setDataSource(getDataSourceTarget());
		databaseUserManagement.addEntityRelationshipModel(getErm());
		databaseUserManagement
				.addEntityRelationshipModelMapping(getErmMapping());
		return databaseUserManagement;
	}

	public static Driver getDriver(String driverClassName) {

		Driver driver = null;

		try {
			driver = (Driver) Class.forName(driverClassName).newInstance();
		} catch (Exception e) {
			System.err.println(String.format(
					"Could not instantiate class '%s'", driverClassName));
		}

		return driver;
	}

	public static RelationshipTypeMapping getRelationshipTypeMappingUserGroup() {
		RelationshipTypeMapping relationshipTypeMapping = new RelationshipTypeMapping(
				getRelationshipUserToGroup());
		relationshipTypeMapping.addAttributeType(getAttributeTypeUserGroupId());
		relationshipTypeMapping
				.addReferencedAttributeType(getAttributeTypeGroupId());
		return relationshipTypeMapping;
	}
}