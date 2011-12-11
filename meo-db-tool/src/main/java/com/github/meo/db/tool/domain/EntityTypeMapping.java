package com.github.meo.db.tool.domain;

import java.util.ArrayList;
import java.util.List;

public class EntityTypeMapping {

	IEntityType entityType;
	DatabaseTable databaseTable;
	List<AttributeTypeMapping> attributeTypeMappings;

	public EntityTypeMapping() {
		init();
	}

	public void init() {
		attributeTypeMappings = new ArrayList<AttributeTypeMapping>();
	}

	public DatabaseTableColumn getDatabaseTableColumn(IAttribute attribute) {

		if (attribute == null) {
			return null;
		}

		DatabaseTableColumn databaseTableColumn = null;

		for (AttributeTypeMapping attributeTypeMapping : getAttributeTypeMappings()) {
			if (attribute.getAttributeType().getName().equals(
					attributeTypeMapping.getAttributeType().getName())) {
				databaseTableColumn = attributeTypeMapping.getDatabaseTableColumn();
			}
		}

		return databaseTableColumn;
	}

	public boolean addAttributeTypeMapping(AttributeTypeMapping attributeTypeMapping) {
		return attributeTypeMappings.add(attributeTypeMapping);
	}

	public IEntityType getEntityType() {
		return entityType;
	}

	public DatabaseTable getDatabaseTable() {
		return databaseTable;
	}

	public List<AttributeTypeMapping> getAttributeTypeMappings() {
		return attributeTypeMappings;
	}

	public void setEntityType(IEntityType entityType) {
		this.entityType = entityType;
	}

	public void setDatabaseTable(DatabaseTable databaseTable) {
		this.databaseTable = databaseTable;
	}

	public void setAttributeTypeMappings(List<AttributeTypeMapping> attributeTypeMappings) {
		this.attributeTypeMappings = attributeTypeMappings;
	}

}