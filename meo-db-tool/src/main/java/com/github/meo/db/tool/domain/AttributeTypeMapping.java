package com.github.meo.db.tool.domain;

public class AttributeTypeMapping {

	IAttributeType attributeType;
	DatabaseTableColumn databaseTableColumn;

	public AttributeTypeMapping() {
		init();
	}

	public AttributeTypeMapping(IAttributeType attributeType,
			DatabaseTableColumn databaseTableColumn) {
		init();
		setAttributeType(attributeType);
		setDatabaseTableColumn(databaseTableColumn);
	}

	private void init() {
		setAttributeType(new AttributeType());
		setDatabaseTableColumn(new DatabaseTableColumn());
	}

	public boolean equals(Object object) {

		// null reference?
		if (object == null) {
			return false;
		}

		/*
		 * Are the references pointing to the same object?
		 */
		if (this == object) {
			return true;
		}

		/*
		 * Same class?
		 */
		if (!getClass().equals(object.getClass())) {
			return false;
		}

		AttributeTypeMapping attributeMapping = (AttributeTypeMapping) object;

		/*
		 * Do the objects have the same attribute?
		 */
		if (!getAttributeType().equals(attributeMapping.getAttributeType())) {
			return false;
		}

		/*
		 * Do the objects have the same database table column?
		 */
		if (!getDatabaseTableColumn().equals(
				attributeMapping.getDatabaseTableColumn())) {
			return false;
		}

		return true;
	}

	/**
	 * Clone attributeMapping
	 */
	@Override
	public AttributeTypeMapping clone() {

		AttributeTypeMapping attributeMapping = new AttributeTypeMapping();

		attributeMapping.setAttributeType(getAttributeType());
		attributeMapping.setDatabaseTableColumn(getDatabaseTableColumn());

		return attributeMapping;
	}

	public IAttributeType getAttributeType() {
		return attributeType;
	}

	public DatabaseTableColumn getDatabaseTableColumn() {
		return databaseTableColumn;
	}

	public void setAttributeType(IAttributeType attributeType) {

		if (attributeType == null) {
			return;
		}

		this.attributeType = attributeType;
	}

	public void setDatabaseTableColumn(DatabaseTableColumn databaseTableColumn) {

		if (databaseTableColumn == null) {
			return;
		}

		this.databaseTableColumn = databaseTableColumn;
	}
}
