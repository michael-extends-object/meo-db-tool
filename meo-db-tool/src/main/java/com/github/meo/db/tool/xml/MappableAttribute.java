package com.github.meo.db.tool.xml;

import com.github.meo.db.tool.domain.IAttribute;
import com.github.meo.db.tool.domain.IAttributeType;

public class MappableAttribute {

	private IAttributeType attributeType;
	private Object value;

	public IAttributeType getAttributeType() {
		return attributeType;
	}

	public Object getValue() {
		return value;
	}

	public void setAttributeType(IAttributeType attributeType) {
		this.attributeType = attributeType;
	}

	public void setValue(Object value) {
		this.value = value;
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

		MappableAttribute attribute = (MappableAttribute) object;

		/*
		 * Do the objects have the same attribute type?
		 */
		if (!getAttributeType().equals(attribute.getAttributeType())) {
			return false;
		}

		/*
		 * Do the objects have the same value?
		 */
		if (getValue() == null) {
			if (attribute.getValue() != null) {
				return false;
			}
		} else {
			if (attribute.getValue() == null) {
				return false;
			}

			if (!getValue().equals(attribute.getValue())) {
				return false;
			}
		}

		return true;
	}
}
