package com.github.meo.db.tool.domain;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class Database {

	String name;
	DataSource dataSource;
	List<EntityMapping> entityMappings;

	public Database() {
		init();
	}

	public Database(String name) {
		init();
		setName(name);
	}

	private void init() {
		entityMappings = new ArrayList<EntityMapping>();
	}

	public boolean addEntityMapping(EntityMapping entityMapping) {
		return entityMappings.add(entityMapping);
	}

	public DatabaseTable getDatabaseTable(Entity entity) {

		if (entity == null) {
			return null;
		}

		return getEntityMapping(entity).getDatabaseTable();
	}

	public EntityMapping getEntityMapping(Entity entity) {

		for (EntityMapping entityMapping : getEntityMappings()) {
			if (entity.getName().equals(entityMapping.getEntity().getName())) {
				return entityMapping;
			}
		}

		return null;
	}

	public List<AttributeMapping> getAttributeMappings(Entity entity) {

		List<AttributeMapping> attributeMapping = new ArrayList<AttributeMapping>();

		EntityMapping entityMapping = getEntityMapping(entity);

		if(entityMapping == null) {
			return attributeMapping;
		}
		
		attributeMapping.addAll(entityMapping.getAttributeMappings());

		return entityMapping.getAttributeMappings();
	}

	public List<DatabaseTableColumn> getDatabaseTableColumns(Entity entity) {

		List<DatabaseTableColumn> databaseTableColumns = new ArrayList<DatabaseTableColumn>();

		for (AttributeMapping attributeMapping : getAttributeMappings(entity)) {
			databaseTableColumns.add(attributeMapping.getDatabaseTableColumn());
		}

		return databaseTableColumns;
	}

	public int getColumnCount(Entity entity) {
		return getDatabaseTableColumns(entity).size();
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

	public List<EntityMapping> getEntityMappings() {
		return entityMappings;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setEntityMappings(List<EntityMapping> entityMappings) {
		this.entityMappings = entityMappings;
	}

	public DatabaseTableColumn getDatabaseTableColumn(Entity entity, Attribute attribute) {
		
		if(attribute == null) {
			throw new IllegalArgumentException("null is not a valid argument!");
		}
		
		return getDatabaseTableColumn(entity, attribute.getName());
	}
	
	public DatabaseTableColumn getDatabaseTableColumn(Entity entity, String attributeName) {
		
		if(entity == null || attributeName == null) {
			throw new IllegalArgumentException("null is not a valid argument!");
		}
		
		for (AttributeMapping attributeMapping : getAttributeMappings(entity)) {
			if (attributeName.equals(attributeMapping.getAttribute().getName())) {
				return attributeMapping.getDatabaseTableColumn();
			}
		}
		
		return null;
	}
	
}