package com.github.meo.db.tool.domain.mapping;

import org.springframework.util.Assert;

import com.github.meo.db.tool.domain.AttributeType;
import com.github.meo.db.tool.domain.IAttributeType;
import com.github.meo.db.tool.domain.db.Column;

public class AttributeTypeMapping {

	private IAttributeType attributeType;
	private Column column;

	public AttributeTypeMapping() {
		init();
	}

	public AttributeTypeMapping(IAttributeType attributeType, Column column) {
		setAttributeType(attributeType);
		setColumn(column);
	}

	private void init() {
		setAttributeType(new AttributeType());
		setColumn(new Column());
	}

	public IAttributeType getAttributeType() {
		return attributeType;
	}

	public Column getColumn() {
		return column;
	}

	public void setAttributeType(IAttributeType attributeType) {
		Assert.notNull(attributeType);
		this.attributeType = attributeType;
	}

	public void setColumn(Column column) {
		Assert.notNull(column);
		this.column = column;
	}

	@Override
	public String toString() {
		return String.format("AttributeMapping(%s, %s)", getAttributeType(),
				getColumn());
	}

	@Override
	public AttributeTypeMapping clone() {
		AttributeTypeMapping attributeTypeMapping = new AttributeTypeMapping();
		attributeTypeMapping.setAttributeType(getAttributeType());
		attributeTypeMapping.setColumn(getColumn());
		return attributeTypeMapping;
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

		AttributeTypeMapping attributeTypeMapping = (AttributeTypeMapping) object;

		/*
		 * Do the objects have the same attribute type?
		 */
		if (!getAttributeType().equals(attributeTypeMapping.getAttributeType())) {
			return false;
		}

		/*
		 * Do the objects have the same column?
		 */
		if (!getColumn().equals(attributeTypeMapping.getColumn())) {
			return false;
		}

		return true;
	}

}