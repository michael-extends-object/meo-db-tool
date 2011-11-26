package com.github.meo.db.tool.domain;

import java.util.ArrayList;
import java.util.List;

public class EntityMapping {

	Entity entity;
	DatabaseTable databaseTable;
	List<AttributeMapping> attributeMappings;

	public EntityMapping() {
		init();
	}

	public void init() {
		attributeMappings = new ArrayList<AttributeMapping>();
	}

	public DatabaseTableColumn getDatabaseTableColumn(Attribute attribute) {

		if (attribute == null) {
			return null;
		}

		DatabaseTableColumn databaseTableColumn = null;

		for (AttributeMapping attributeMapping : getAttributeMappings()) {
			if (attribute.getName().equals(
					attributeMapping.getAttribute().getName())) {
				databaseTableColumn = attributeMapping.getDatabaseTableColumn();
			}
		}

		return databaseTableColumn;
	}

	public boolean addAttributeMapping(AttributeMapping attributeMapping) {
		return attributeMappings.add(attributeMapping);
	}

	public Entity getEntity() {
		return entity;
	}

	public DatabaseTable getDatabaseTable() {
		return databaseTable;
	}

	public List<AttributeMapping> getAttributeMappings() {
		return attributeMappings;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public void setDatabaseTable(DatabaseTable databaseTable) {
		this.databaseTable = databaseTable;
	}

	public void setAttributeMappings(List<AttributeMapping> attributeMappings) {
		this.attributeMappings = attributeMappings;
	}

}