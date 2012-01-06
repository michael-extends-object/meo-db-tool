package com.github.meo.db.tool.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;


public class Relationship implements IRelationship, Cloneable {

	private IRelationshipType relationshipType;
	private IEntity entity;
	private List<IEntity> referencedEntities;

	public Relationship(IRelationshipType relationshipType) {
		init();
		setRelationshipType(relationshipType);
	}

	private void init() {
		setEntity(new Entity(new EntityType()));
		setReferencedEntities(new ArrayList<IEntity>());
	}

	public boolean addReferencedEntity(IEntity entity) {

		Assert.notNull(entity);

		return referencedEntities.add(entity);
	}

	public void addReferencedEntities(List<IEntity> referencedEntities) {

		Assert.notNull(referencedEntities);

		for (IEntity referencedEntity : referencedEntities) {
			addReferencedEntity(referencedEntity);
		}
	}

	public IRelationshipType getRelationshipType() {
		return relationshipType;
	}

	public IEntity getEntity() {
		return entity;
	}

	public List<IEntity> getReferencedEntities() {
		return referencedEntities;
	}

	public void setRelationshipType(IRelationshipType relationshipType) {
		this.relationshipType = relationshipType;
	}

	public void setEntity(IEntity entity) {

		if (entity == null) {
			return;
		}

		this.entity = entity;
	}

	public void setReferencedEntities(List<IEntity> referencedEntities) {

		if (referencedEntities == null) {
			return;
		}

		this.referencedEntities = new ArrayList<IEntity>();

		for (IEntity referencedEntity : referencedEntities) {
			addReferencedEntity(referencedEntity);
		}
	}

	public String toString() {
		return getRelationshipType().toString();
	}

	public IRelationship clone() {

		IRelationship relationship = new Relationship(getRelationshipType());

		relationship.setEntity(getEntity());

		for (IEntity referencedEntity : getReferencedEntities()) {
			relationship.addReferencedEntity(referencedEntity);
		}

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
		 * Do the objects have the same entity?
		 */
		if (!getEntity().equals(relationship.getEntity())) {
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

		return true;
	}

}