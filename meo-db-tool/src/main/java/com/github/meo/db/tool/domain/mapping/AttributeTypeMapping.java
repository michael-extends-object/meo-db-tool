package com.github.meo.db.tool.domain.mapping;

import com.github.meo.db.tool.domain.AttributeType;
import com.github.meo.db.tool.domain.IAttributeType;
import com.github.meo.db.tool.domain.db.Column;

public class AttributeTypeMapping {

	IAttributeType attributeType;
	Column column;

	public AttributeTypeMapping() {
		init();
	}

	public AttributeTypeMapping(IAttributeType attributeType,
			Column column) {
		init();
		setAttributeType(attributeType);
		setColumn(column);
	}

	private void init() {
		setAttributeType(new AttributeType());
		setColumn(new Column());
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
		if (!getColumn().equals(
				attributeMapping.getColumn())) {
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
		attributeMapping.setColumn(getColumn());

		return attributeMapping;
	}

	public IAttributeType getAttributeType() {
		return attributeType;
	}

	public Column getColumn() {
		return column;
	}

	public void setAttributeType(IAttributeType attributeType) {

		if (attributeType == null) {
			return;
		}

		this.attributeType = attributeType;
	}

	public void setColumn(Column column) {

		if (column == null) {
			return;
		}

		this.column = column;
	}
}
