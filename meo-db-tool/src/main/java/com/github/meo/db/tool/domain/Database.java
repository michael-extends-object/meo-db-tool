package com.github.meo.db.tool.domain;

import java.util.ArrayList;
import java.util.List;

public class Database {

	String name;
	JdbcDataSource dataSource;
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

		DatabaseTable databaseTable = null;

		for (EntityMapping entityMapping : entityMappings) {
			if (entity.getName().equals(entityMapping.getEntity().getName())) {
				databaseTable = entityMapping.getDatabaseTable();
			}
		}

		return databaseTable;
	}

	@Override
	public String toString() {
		return getName();
	}

	public String getName() {
		return name;
	}

	public JdbcDataSource getDataSource() {
		return dataSource;
	}

	public List<EntityMapping> getEntityMappings() {
		return entityMappings;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDataSource(JdbcDataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setEntityMappings(List<EntityMapping> entityMappings) {
		this.entityMappings = entityMappings;
	}

}