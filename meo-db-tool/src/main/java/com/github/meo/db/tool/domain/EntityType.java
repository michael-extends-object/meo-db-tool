package com.github.meo.db.tool.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;


public class EntityType implements IEntityType {

	private String name;
	private List<IAttributeType> attributeTypes;

	public EntityType() {
		init();
	}

	public EntityType(String name) {
		init();
		setName(name);
	}

	private void init() {
		name = "";
		setAttributeTypes(new ArrayList<IAttributeType>());
	}

	public boolean addAttributeType(IAttributeType attributeType) {

		if (attributeType == null) {
			return false;
		}

		return getAttributeTypes().add(attributeType);
	}

	public String getName() {
		return name;
	}

	public List<IAttributeType> getAttributeTypes() {
		return attributeTypes;
	}

	public List<IAttributeType> getAttributeTypesPrimaryKey() {

		List<IAttributeType> attributeTypesPrimaryKey = new ArrayList<IAttributeType>();

		for (IAttributeType attributeType : getAttributeTypes()) {
			if (attributeType.isPrimaryKey()) {
				attributeTypesPrimaryKey.add(attributeType);
			}
		}

		/*
		 * The primary key is the combination of all attributes, in case no PK
		 * attribute is set
		 */
		if (attributeTypesPrimaryKey.isEmpty()) {
			return getAttributeTypes();
		}

		return attributeTypesPrimaryKey;
	}

	public void setName(String name) {

		Assert.notNull(name);

		this.name = name;
	}

	public void setAttributeTypes(List<IAttributeType> attributeTypes) {

		Assert.notNull(attributeTypes);

		this.attributeTypes = attributeTypes;
	}

	public IEntity getEntity() {
		return new Entity(this);
	}

	public IAttributeType getAttributeType(String name) {

		Assert.notNull(name);

		IAttributeType resultAttributeType = null;

		for (IAttributeType attributeType : getAttributeTypes()) {
			if (name.equals(attributeType.getName())) {
				resultAttributeType = attributeType;
			}
		}

		return resultAttributeType;
	}

	@Override
	public String toString() {
		return name;
	}

	public IEntityType clone() {

		IEntityType entityType = new EntityType();

		entityType.setName(getName());
		// entityType.setEntityRelationshipModel(getEntityRelationshipModel());

		for (IAttributeType attributeType : getAttributeTypes()) {
			entityType.addAttributeType(attributeType.clone());
		}

		return entityType;
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

		IEntityType entityType = (IEntityType) object;

		/*
		 * Do the objects have the same name?
		 */
		if (!getName().equals(entityType.getName())) {
			return false;
		}

		/*
		 * Do the objects have the same attribute types?
		 */
		if (getAttributeTypes().size() != entityType.getAttributeTypes().size()) {
			return false;
		}

		for (int i = 0; i < getAttributeTypes().size(); i++) {
			if (!getAttributeTypes().get(i).equals(
					entityType.getAttributeTypes().get(i))) {
				return false;
			}
		}

		return true;
	}

}