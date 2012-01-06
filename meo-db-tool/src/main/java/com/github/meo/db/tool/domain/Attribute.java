package com.github.meo.db.tool.domain;

import com.github.meo.db.tool.xml.AttributeAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.springframework.util.Assert;

@XmlJavaTypeAdapter(AttributeAdapter.class)
public class Attribute implements IAttribute, Cloneable {

	private IAttributeType attributeType;
	private Object value;

	public Attribute(IAttributeType attributeType) {
		setAttributeType(attributeType);
	}

	public Attribute(IAttributeType attributeType, Object value) {
		setAttributeType(attributeType);
		setValue(value);
	}

	public IAttributeType getAttributeType() {
		return attributeType;
	}

	public String getName() {
		return getAttributeType().getName();
	}

	public Object getValue() {
		return value;
	}

	public boolean isPrimaryKey() {
		return getAttributeType().isPrimaryKey();
	}

	private void setAttributeType(IAttributeType attributeType) {

		Assert.notNull(attributeType);

		this.attributeType = attributeType;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String toString() {
		return getName();
	}

	@Override
	public IAttribute clone() {
		IAttribute attribute = new Attribute(getAttributeType());
		attribute.setValue(getValue());
		return attribute;
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

		IAttribute attribute = (IAttribute) object;

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