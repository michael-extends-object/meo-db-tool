package com.github.meo.db.tool.domain;

import java.util.ArrayList;
import java.util.List;

public class RelationshipImpl implements Relationship, Cloneable {

	private String name;
	private Entity referencedEntity;
	private List<Entity> referencedEntities;
	private Cardinality cardinality;

	public RelationshipImpl() {
		init();
	}

	public RelationshipImpl(String name) {
		init();
		setName(name);
	}

	public RelationshipImpl(Entity referencedEntity, Cardinality cardinality) {
		init();
		setReferencedEntity(referencedEntity);
		setCardinality(cardinality);
	}

	public RelationshipImpl(String name, Entity entity,
			Entity referencedEntity, Cardinality cardinality) {
		init();
		setName(name);
		setReferencedEntity(referencedEntity);
		setCardinality(cardinality);
	}

	private void init() {
		name = "";
		referencedEntity = new EntityImpl();
		referencedEntities = new ArrayList<Entity>();
		cardinality = Cardinality.OneToOne;
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

		Relationship relationship = (Relationship) object;

		/*
		 * Do the objects have the same name?
		 */
		if (!getName().equals(relationship.getName())) {
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

	public Relationship clone() {

		Relationship relationship = new RelationshipImpl();

		relationship.setName(getName());

		relationship.setReferencedEntity(getReferencedEntity().clone());

		for (Entity referencedEntity : getReferencedEntities()) {
			relationship.addReferencedEntity(referencedEntity);
		}

		relationship.setCardinality(getCardinality());

		return relationship;
	}

	public String toString() {

		// named entity
		if (!"".equals(getName())) {
			return getName();
		}

		return String.format("'Relationship to %s'", referencedEntity);
	}

	public boolean addReferencedEntity(Entity referencedEntity) {

		if (referencedEntity == null) {
			return false;
		}

		return getReferencedEntities().add(referencedEntity);
	}

	public boolean addReferencedEntities(List<Entity> referencedEntities) {

		boolean isOperationSuccessful = true;

		if (referencedEntities == null) {
			return false;
		}

		for (Entity referencedEntity : referencedEntities) {
			if (!addReferencedEntity(referencedEntity)) {
				isOperationSuccessful = false;
			}
		}

		return isOperationSuccessful;
	}

	public String getName() {
		return name;
	}

	public Entity getReferencedEntity() {
		return referencedEntity;
	}

	public List<Entity> getReferencedEntities() {
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

	public void setReferencedEntity(Entity referencedEntity) {

		if (referencedEntity == null) {
			return;
		}

		this.referencedEntity = referencedEntity;
	}

	public void setReferencedEntities(List<Entity> referencedEntities) {

		if (referencedEntities == null) {
			return;
		}

		this.referencedEntities = new ArrayList<Entity>();

		for (Entity referencedEntity : referencedEntities) {
			addReferencedEntity(referencedEntity);
		}
	}

	public void setCardinality(Cardinality cardinality) {

		if (cardinality == null) {
			return;
		}

		this.cardinality = cardinality;
	}
}
