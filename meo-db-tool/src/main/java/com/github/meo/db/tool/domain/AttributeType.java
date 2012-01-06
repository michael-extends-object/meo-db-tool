package com.github.meo.db.tool.domain;


public class AttributeType implements IAttributeType, Cloneable {

	private String name;
	private boolean isPrimaryKey;

	public AttributeType() {
		init();
	}

	public AttributeType(String name) {
		init();
		setName(name);
	}

	public AttributeType(String name, boolean isPrimaryKey) {
		init();
		setName(name);
		setPrimaryKey(isPrimaryKey);
	}

	private void init() {
		setName("");
		setPrimaryKey(false);
	}

	public String getName() {
		return name;
	}

	public boolean isPrimaryKey() {
		return this.isPrimaryKey;
	}

	public void setName(String name) {

		if (name == null) {
			return;
		}

		this.name = name;
	}

	public void setPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}

	public String toString() {
		return name;
	}

	/**
	 * Clone attribute
	 */
	@Override
	public IAttributeType clone() {

		IAttributeType attributeType = new AttributeType();

		attributeType.setName(getName());

		return attributeType;
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

		IAttributeType attributeType = (IAttributeType) object;

		/*
		 * Do the objects have the same name?
		 */
		if (!getName().equals(attributeType.getName())) {
			return false;
		}

		return true;
	}

	public IAttribute getAttribute() {
		IAttribute attribute = new Attribute(this);
		return attribute;
	}

}
