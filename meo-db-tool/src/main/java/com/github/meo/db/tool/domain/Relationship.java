package com.github.meo.db.tool.domain;

import java.util.ArrayList;
import java.util.List;

public class Relationship implements IRelationship, Cloneable {

	private String name;
	private Object entity;
	private Object referencedEntity;
	private List<Object> referencedEntities;
	private Cardinality cardinality;

	public Relationship() {
		init();
	}

	public Relationship(String name) {
		init();
		setName(name);
	}

	public Relationship(IEntity referencedEntity, Cardinality cardinality) {
		init();
		setReferencedEntity(referencedEntity);
		setCardinality(cardinality);
	}

	public Relationship(String name, IEntity entity,
			IEntity referencedEntity, Cardinality cardinality) {
		init();
		setName(name);
		setReferencedEntity(referencedEntity);
		setCardinality(cardinality);
	}

	private void init() {
		name = "";
		entity = new Entity();
		referencedEntity = new Entity();
		referencedEntities = new ArrayList<Object>();
		cardinality = Cardinality.OneToOne;
	}

	public boolean addReferencedEntity(Object entity) {

		if (entity == null) {
			return false;
		}

		return referencedEntities.add(entity);
	}

	public boolean addReferencedEntities(List<Object> referencedEntities) {

		boolean isOperationSuccessful = true;

		if (referencedEntities == null) {
			return false;
		}

		for (Object referencedEntity : referencedEntities) {
			if (!addReferencedEntity(referencedEntity)) {
				isOperationSuccessful = false;
			}
		}

		return isOperationSuccessful;
	}

	public String getName() {
		return name;
	}

	public Object getEntity() {
		return entity;
	}

	public Object getReferencedEntity() {
		return referencedEntity;
	}

	public List<Object> getReferencedEntities() {
		return referencedEntities;
	}

	public Cardinality getCardinality() {
		return cardinality;
	}

	public void setName(String name) {

		if (name == null) {
			return;
		}

		this.name = name;
	}

	public void setEntity(Object entity) {

		if (entity == null) {
			return;
		}

		this.entity = entity;
	}

	public void setReferencedEntity(Object referencedEntity) {

		if (referencedEntity == null) {
			return;
		}

		this.referencedEntity = referencedEntity;
	}

	public void setReferencedEntities(List<Object> referencedEntities) {

		if (referencedEntities == null) {
			return;
		}

		this.referencedEntities = new ArrayList<Object>();

		for (Object referencedEntity : referencedEntities) {
			addReferencedEntity(referencedEntity);
		}
	}

	public void setCardinality(Cardinality cardinality) {

		if (cardinality == null) {
			return;
		}

		this.cardinality = cardinality;
	}

	public String toString() {

		// named entity
		if (!"".equals(getName())) {
			return getName();
		}

		return String.format("'Relationship to %s'", referencedEntity);
	}

	public IRelationship clone() {

		IRelationship relationship = new Relationship();

		relationship.setName(getName());

		relationship.setEntity(getEntity());

		relationship.setReferencedEntity(getReferencedEntity());

		for (Object referencedEntity : getReferencedEntities()) {
			relationship.addReferencedEntity(referencedEntity);
		}

		relationship.setCardinality(getCardinality());

		return relationship;
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

		IRelationship relationship = (IRelationship) object;

		/*
		 * Do the objects have the same name?
		 */
		if (!getName().equals(relationship.getName())) {
			return false;
		}

		/*
		 * Do the objects have the same entity?
		 */
		if (!getEntity().equals(relationship.getEntity())) {
			return false;
		}

		/*
		 * Do the objects have the same referenced entity?
		 */
		if (!getReferencedEntity().equals(relationship.getReferencedEntity())) {
			return false;
		}

		/*
		 * Do the objects have the same referenced entities?
		 */
		if (getReferencedEntities().size() != relationship
				.getReferencedEntities().size()) {
			return false;
		}

		for (int i = 0; i < getReferencedEntities().size(); i++) {
			if (!getReferencedEntities().get(i).equals(
					relationship.getReferencedEntities().get(i))) {
				return false;
			}
		}

		/*
		 * Do the objects have the same cardinality?
		 */
		if (!getCardinality().equals(relationship.getCardinality())) {
			return false;
		}

		return true;
	}
}