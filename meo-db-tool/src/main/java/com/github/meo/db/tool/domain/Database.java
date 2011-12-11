package com.github.meo.db.tool.domain;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class Database {

	String name;
	DataSource dataSource;
	List<EntityTypeMapping> entityTypeMappings;
	List<IEntityRelationshipModel> entityRelationshipModels;
	List<IRelationship> relationships;
	
	public Database() {
		init();
	}

	public Database(String name) {
		init();
		setName(name);
	}

	private void init() {
		entityTypeMappings = new ArrayList<EntityTypeMapping>();
		entityRelationshipModels = new ArrayList<IEntityRelationshipModel>();
		relationships = new ArrayList<IRelationship>();
	}

	public boolean addEntityTypeMapping(EntityTypeMapping entityTypeMapping) {
		return entityTypeMappings.add(entityTypeMapping);
	}

	public DatabaseTable getDatabaseTable(IEntityType entityType) {

		if (entityType == null) {
			return null;
		}

		return getEntityTypeMapping(entityType).getDatabaseTable();
	}

	public EntityTypeMapping getEntityTypeMapping(IEntityType entityType) {

		for (EntityTypeMapping entityTypeMapping : getEntityTypeMappings()) {
			if (entityType.getName().equals(
					entityTypeMapping.getEntityType().getName())) {
				return entityTypeMapping;
			}
		}

		return null;
	}

	public List<AttributeTypeMapping> getAttributeTypeMappings(
			IEntityType entityType) {
		List<AttributeTypeMapping> attributeTypeMapping = new ArrayList<AttributeTypeMapping>();

		EntityTypeMapping entityTypeMapping = getEntityTypeMapping(entityType);

		if (entityTypeMapping == null) {
			return attributeTypeMapping;
		}

		attributeTypeMapping.addAll(entityTypeMapping
				.getAttributeTypeMappings());

		return entityTypeMapping.getAttributeTypeMappings();
	}

	public List<DatabaseTableColumn> getDatabaseTableColumns(
			IEntityType entityType) {

		List<DatabaseTableColumn> databaseTableColumns = new ArrayList<DatabaseTableColumn>();

		for (AttributeTypeMapping attributeTypeMapping : getAttributeTypeMappings(entityType)) {
			databaseTableColumns.add(attributeTypeMapping
					.getDatabaseTableColumn());
		}

		return databaseTableColumns;
	}

	public int getColumnCount(IEntityType entityType) {
		return getDatabaseTableColumns(entityType).size();
	}

	@Override
	public String toString() {
		return getName();
	}

	public String getName() {
		return name;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public DatabaseTableColumn getDatabaseTableColumn(IEntityType entityType,
			IAttributeType attributeType) {

		if (attributeType == null) {
			throw new IllegalArgumentException("null is not a valid argument!");
		}

		return getDatabaseTableColumn(entityType, attributeType.getName());
	}

	public DatabaseTableColumn getDatabaseTableColumn(IEntityType entityType,
			String attributeName) {

		if (entityType == null || attributeName == null) {
			throw new IllegalArgumentException("null is not a valid argument!");
		}

		for (AttributeTypeMapping attributeTypeMapping : getAttributeTypeMappings(entityType)) {
			if (attributeName.equals(attributeTypeMapping.getAttributeType()
					.getName())) {
				return attributeTypeMapping.getDatabaseTableColumn();
			}
		}

		return null;
	}

	public List<EntityTypeMapping> getEntityTypeMappings() {
		return entityTypeMappings;
	}

	public List<IEntityRelationshipModel> getEntityRelationshipModels() {
		return entityRelationshipModels;
	}

	public void setEntityTypeMappings(List<EntityTypeMapping> entityTypeMappings) {
		this.entityTypeMappings = entityTypeMappings;
	}

	public void setEntityRelationshipModels(
			List<IEntityRelationshipModel> entityRelationshipModels) {
		this.entityRelationshipModels = entityRelationshipModels;
	}

}