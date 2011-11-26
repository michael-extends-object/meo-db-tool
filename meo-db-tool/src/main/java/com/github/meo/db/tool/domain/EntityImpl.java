package com.github.meo.db.tool.domain;

import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, Cloneable {

	private String name;
	private List<Attribute> attributes;
	List<Relationship> realtionships;

	public EntityImpl() {
		init();
	}

	public EntityImpl(String name) {
		init();
		setName(name);
	}

	private void init() {
		setAttributes(new ArrayList<Attribute>());
		setRelationships(new ArrayList<Relationship>());
	}

	@Override
	public Object clone() {
		Entity entity = new EntityImpl();

		entity.setName(getName());

		if (getAttributes() == null) {
			entity.setAttributes(null);
		} else {
			for (Attribute attribute : getAttributes()) {
				entity.addAttribute((Attribute) attribute.clone());
			}
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

		Entity entity = (Entity) object;

		/*
		 * Do the objects have the same name?
		 */
		if (getName() == null) {
			if (entity.getName() != null) {
				return false;
			}
		} else {

			if (entity.getName() == null) {
				return false;
			}

			if (!getName().equals(entity.getName())) {
				return false;
			}
		}

		/*
		 * Do the objects have the same attributes?
		 */
		if (getAttributes() == null) {
			if (entity.getAttributes() != null) {
				return false;
			}
		} else {
			if (entity.getAttributes() == null) {
				return false;
			} else {
				if (getAttributes().size() != entity.getAttributes().size()) {
					return false;
				}

				for (int i = 0; i < getAttributes().size(); i++) {
					if (!getAttributes().get(i).equals(
							entity.getAttributes().get(i))) {
						return false;
					}
				}
			}
		}

		return true;
	}

	public void addAttribute(Attribute attribute) {
		getAttributes().add(attribute);
	}

	/**
	 * 
	 * @param name
	 * @return Returns the argument with the given name. In case there was no
	 *         argument with the given name, the method will return null.
	 */
	public Attribute getAttribute(String name) {

		if (name == null) {
			throw new IllegalArgumentException(
					"The given attribute name is null!");
		}

		Attribute resultAttribute = null;

		for (Attribute attribute : getAttributes()) {
			if (name.equals(attribute.getName())) {
				resultAttribute = attribute;
			}
		}

		return resultAttribute;
	}

	public String getName() {
		return name;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public List<Attribute> getAttributesPrimaryKey() {

		List<Attribute> attributesPrimaryKey = new ArrayList<Attribute>();

		for (Attribute attribute : getAttributes()) {
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

	public List<Attribute> getAttributesNotNull() {

		List<Attribute> attributesNotNull = new ArrayList<Attribute>();

		for (Attribute attribute : getAttributes()) {
			if (attribute.getValue() != null) {
				attributesNotNull.add(attribute);
			}
		}

		return attributesNotNull;
	}

	public List<Attribute> getAttributesPrimaryKeyNotNull() {

		List<Attribute> attributesPrimaryKeyNotNull = new ArrayList<Attribute>();

		for (Attribute attribute : getAttributesPrimaryKey()) {
			if (attribute.getValue() != null) {
				attributesPrimaryKeyNotNull.add(attribute);
			}
		}

		return attributesPrimaryKeyNotNull;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public String toString() {
		return getName();
	}

	public boolean setAttributeValue(Attribute attribute, Object value) {

		if (attribute == null) {
			throw new IllegalArgumentException("null is not valid argument!");
		}

		return setAttributeValue(attribute.getName(), value);
	}

	public boolean setAttributeValue(String attributeName, Object value) {

		if (attributeName == null) {
			throw new IllegalArgumentException("null is not valid argument!");
		}

		getAttribute(attributeName).setValue(value);

		return false;
	}

	public Object getAttributeValue(Attribute attribute) {

		if (attribute == null) {
			throw new IllegalArgumentException("null is not valid argument!");
		}

		return getAttributeValue(attribute.getName());
	}

	public Object getAttributeValue(String attributeName) {

		if (attributeName == null) {
			throw new IllegalArgumentException("null is not valid argument!");
		}

		Attribute attribute = getAttribute(attributeName);

		if (attribute == null) {
			throw new IllegalArgumentException(String.format(
					"Attribute with name '%s' could not be found!",
					attributeName));
		}

		return attribute.getValue();
	}

	public boolean addRelationship(Relationship relationship) {
		return getRealtionships().add(relationship);
	}
	
	public List<Relationship> getRealtionships() {
		return realtionships;
	}

	public void setRelationships(List<Relationship> realtionships) {

		if (realtionships == null) {
			throw new IllegalArgumentException("<null> is not a valid argument");
		}

		this.realtionships = realtionships;
	}
	

}