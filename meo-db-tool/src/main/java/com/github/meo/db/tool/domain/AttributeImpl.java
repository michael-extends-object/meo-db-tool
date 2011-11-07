package com.github.meo.db.tool.domain;

public class AttributeImpl implements Attribute, Cloneable {

	/**
	 * Attribute name
	 */
	private String name;

	/**
	 * Attribute value
	 */
	// TODO exclude value from inheritance to Entity
	private Object value;

	public AttributeImpl() {

	}

	public AttributeImpl(String name) {
		setName(name);
	}

	public AttributeImpl(String name, Object value) {
		setName(name);
		setValue(value);
	}

	/**
	 * Clone attribute
	 */
	@Override
	public Attribute clone() {

		Attribute attribute = new AttributeImpl();

		attribute.setName(getName());
		attribute.setValue(getValue());

		return attribute;
	}

	public boolean equals(Object object) {

		/*
		 * The objects should have the same class. It should be either Attribute
		 * or Entity in case of a nested entity.
		 */

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

		Attribute attribute = (Attribute) object;
		
		/*
		 * Do the objects have the same name?
		 */
		if (getName() == null) {
			if (attribute.getName() != null) {
				return false;
			}
		} else {
			if (attribute.getName() == null) {
				return false;
			}

			if (!getName().equals(attribute.getName())) {
				return false;
			}
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

	/*
	 * Getters
	 */

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}

	/*
	 * Setters
	 */

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
