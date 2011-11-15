package com.github.meo.db.tool.domain;

public class AttributeMapping {

	Attribute attribute;
	DatabaseTableColumn databaseTableColumn;

	public AttributeMapping() {
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

		AttributeMapping attributeMapping = (AttributeMapping) object;

		/*
		 * Do the objects have the same attribute?
		 */
		if (getAttribute() == null) {
			if (attributeMapping.getAttribute() != null) {
				return false;
			}
		} else {
			if (attributeMapping.getAttribute() == null) {
				return false;
			}

			if (!getAttribute().equals(attributeMapping.getAttribute())) {
				return false;
			}
		}

		/*
		 * Do the objects have the same database table column?
		 */
		if (getDatabaseTableColumn() == null) {
			if (attributeMapping.getDatabaseTableColumn() != null) {
				return false;
			}
		} else {
			if (attributeMapping.getDatabaseTableColumn() == null) {
				return false;
			}

			if (!getDatabaseTableColumn().equals(
					attributeMapping.getDatabaseTableColumn())) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Clone attributeMapping
	 */
	@Override
	public AttributeMapping clone() {

		AttributeMapping attributeMapping = new AttributeMapping();

		attributeMapping.setAttribute(getAttribute());
		attributeMapping.setDatabaseTableColumn(getDatabaseTableColumn());

		return attributeMapping;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public DatabaseTableColumn getDatabaseTableColumn() {
		return databaseTableColumn;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public void setDatabaseTableColumn(DatabaseTableColumn databaseTableColumn) {
		this.databaseTableColumn = databaseTableColumn;
	}
}
