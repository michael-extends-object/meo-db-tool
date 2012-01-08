package com.github.meo.db.tool.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import com.github.meo.db.tool.exception.AttributeNotFoundException;

public class Entity implements IEntity, Cloneable {

	private IEntityType entityType;
	private List<IAttribute> attributes;

	/**
	 * @deprecated Only used by JAXB interface. Will be removed after the
	 *             XML-binding does not need this constructor anymore.
	 */
	@Deprecated
	public Entity() {
	}

	public Entity(IEntityType entityType) {
		setEntityType(entityType);
	}

	private void addAttribute(IAttribute attribute) {

		Assert.notNull(attribute);

		getAttributes().add(attribute);
	}

	/**
	 * 
	 * @param name
	 * @return Returns the argument with the given name. In case there was no
	 *         argument with the given name, the method will return null.
	 * @throws NotFoundException
	 */
	public IAttribute getAttribute(String name)
			throws AttributeNotFoundException {

		Assert.notNull(name);

		for (IAttribute attribute : getAttributes()) {
			if (name.equals(attribute.getAttributeType().getName())) {
				return attribute;
			}
		}

		throw new AttributeNotFoundException(name);
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

	private void setEntityType(IEntityType entityType) {

		Assert.notNull(entityType);

		this.entityType = entityType;

		List<IAttribute> attributes = new ArrayList<IAttribute>();

		for (IAttributeType attributeType : getEntityType().getAttributeTypes()) {
			attributes.add(new Attribute(attributeType));
		}

		setAttributes(attributes);
	}

	public void setAttributeValue(IAttribute attribute, Object value)
			throws AttributeNotFoundException {

		Assert.notNull(attribute);

		setAttributeValue(attribute.getAttributeType().getName(), value);
	}

	public void setAttributeValue(String attributeName, Object value)
			throws AttributeNotFoundException {

		Assert.notNull(attributeName);

		IAttribute attribute = getAttribute(attributeName);

		attribute.setValue(value);
	}

	public Object getAttributeValue(IAttribute attribute)
			throws AttributeNotFoundException {

		Assert.notNull(attribute);

		return getAttributeValue(attribute.getAttributeType().getName());
	}

	public Object getAttributeValue(String attributeName)
			throws AttributeNotFoundException {

		Assert.notNull(attributeName);

		IAttribute attribute = getAttribute(attributeName);

		return attribute.getValue();
	}

	private void setAttributes(List<IAttribute> attributes) {

		Assert.notNull(attributes);

		this.attributes = new ArrayList<IAttribute>();

		for (IAttribute attribute : attributes) {
			addAttribute(attribute);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(getEntityType().getName());
		sb.append("(");
		for (IAttribute attribute : getAttributesPrimaryKey()) {
			sb.append(String.format(", '%s' = '%s'", attribute.getName(),
					attribute.getValue()));
		}
		sb.append(")");
		return sb.toString().replaceFirst(", ", "");
	}

	@Override
	public IEntity clone() {
		IEntity entity = new Entity(getEntityType());

		for (IAttribute attribute : getAttributes()) {
			entity.setAttributeValue(attribute, attribute.getValue());
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