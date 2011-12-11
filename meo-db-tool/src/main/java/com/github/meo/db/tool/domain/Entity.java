package com.github.meo.db.tool.domain;

import java.util.ArrayList;
import java.util.List;

import com.github.meo.db.tool.exception.AttributeTypeNotFoundException;

public class Entity implements IEntity, Cloneable {

	private IEntityType entityType;
	private List<IAttribute> attributes;

	public Entity() {
		init();
	}

	public Entity(IEntityType entityType) {
		init();
		setEntityType(entityType);
	}

	private void init() {
		attributes = new ArrayList<IAttribute>();
	}

	public boolean addAttribute(IAttribute attribute) {

		if (attribute == null) {
			return false;
		}

		return getAttributes().add(attribute);
	}

	/**
	 * 
	 * @param name
	 * @return Returns the argument with the given name. In case there was no
	 *         argument with the given name, the method will return null.
	 * @throws AttributeTypeNotFoundException
	 */
	public IAttribute getAttribute(String name)
			throws AttributeTypeNotFoundException {

		if (name == null) {
			throw new IllegalArgumentException(
					"The given attribute name is null!");
		}

		for (IAttribute attribute : getAttributes()) {
			if (name.equals(attribute.getAttributeType().getName())) {
				return attribute;
			}
		}

		throw new AttributeTypeNotFoundException(name);
	}

	public IEntityType getEntityType() {
		return entityType;
	}

	public List<IAttribute> getAttributes() {
		return attributes;
	}

	public List<IAttribute> getAttributesPrimaryKey() {

		List<IAttribute> attributesPrimaryKey = new ArrayList<IAttribute>();

		for (IAttribute attribute : getAttributes()) {
			if (attribute.isPrimaryKey()) {
				attributesPrimaryKey.add(attribute);
			}
		}

		/*
		 * The primary key is the combination of all attributes, in case no PK
		 * attribute is set
		 */
		if (attributesPrimaryKey.isEmpty()) {
			return getAttributes();
		}

		return attributesPrimaryKey;
	}

	public List<IAttribute> getAttributesNotNull() {

		List<IAttribute> attributesNotNull = new ArrayList<IAttribute>();

		for (IAttribute attribute : getAttributes()) {
			if (attribute.getValue() != null) {
				attributesNotNull.add(attribute);
			}
		}

		return attributesNotNull;
	}

	public List<IAttribute> getAttributesPrimaryKeyNotNull() {

		List<IAttribute> attributesPrimaryKeyNotNull = new ArrayList<IAttribute>();

		for (IAttribute attribute : getAttributesPrimaryKey()) {
			if (attribute.getValue() != null) {
				attributesPrimaryKeyNotNull.add(attribute);
			}
		}

		return attributesPrimaryKeyNotNull;
	}

	public String toString() {
		return getEntityType().getName();
	}

	public void setEntityType(IEntityType entityType) {
		this.entityType = entityType;
		
		List<IAttribute> attributes = new ArrayList<IAttribute>();
		
		for (IAttributeType attributeType : getEntityType().getAttributeTypes()) {
			attributes.add(new Attribute(attributeType));
		}
		
		setAttributes(attributes);
	}

	public boolean setAttributeValue(IAttribute attribute, Object value)
			throws AttributeTypeNotFoundException {

		if (attribute == null) {
			throw new IllegalArgumentException("null is not valid argument!");
		}

		return setAttributeValue(attribute.getAttributeType().getName(), value);
	}

	public boolean setAttributeValue(String attributeName, Object value)
			throws AttributeTypeNotFoundException {

		if (attributeName == null) {
			throw new IllegalArgumentException("null is not valid argument!");
		}

		IAttribute attribute = getAttribute(attributeName);

		attribute.setValue(value);

		if (value.equals(attribute.getValue())) {
			return true;
		}

		return false;
	}

	public Object getAttributeValue(IAttribute attribute)
			throws AttributeTypeNotFoundException {

		if (attribute == null) {
			throw new IllegalArgumentException("null is not valid argument!");
		}

		return getAttributeValue(attribute.getAttributeType().getName());
	}

	public Object getAttributeValue(String attributeName)
			throws AttributeTypeNotFoundException {

		if (attributeName == null) {
			throw new IllegalArgumentException("null is not valid argument!");
		}

		IAttribute attribute = getAttribute(attributeName);

		return attribute.getValue();
	}

	public void setAttributes(List<IAttribute> attributes) {

		if (attributes == null) {
			return;
		}

		this.attributes = new ArrayList<IAttribute>();

		for (IAttribute attribute : attributes) {
			addAttribute(attribute);
		}
	}

	@Override
	public IEntity clone() {
		IEntity entity = new Entity();

		entity.setEntityType(getEntityType());

		for (IAttribute attribute : getAttributes()) {
			entity.addAttribute(attribute.clone());
		}

		return entity;
	}

	@Override
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

		IEntity entity = (IEntity) object;

		/*
		 * Do the objects have the same entity type?
		 */
		if (!getEntityType().equals(entity.getEntityType())) {
			return false;
		}

		/*
		 * Do the objects have the same attributes?
		 */
		if (getAttributes().size() != entity.getAttributes().size()) {
			return false;
		}

		for (int i = 0; i < getAttributes().size(); i++) {
			if (!getAttributes().get(i).equals(entity.getAttributes().get(i))) {
				return false;
			}
		}

		return true;
	}

}