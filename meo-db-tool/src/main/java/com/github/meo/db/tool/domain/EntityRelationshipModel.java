package com.github.meo.db.tool.domain;

import java.util.ArrayList;
import java.util.List;

public class EntityRelationshipModel implements IEntityRelationshipModel,
		Cloneable {

	String name;
	List<IEntityType> entityTypes;
	List<IRelationship> relationships;

	public EntityRelationshipModel() {
		init();
	}

	public EntityRelationshipModel(String name) {
		init();
		setName(name);
	}

	private void init() {
		name = "";
		entityTypes = new ArrayList<IEntityType>();
		relationships = new ArrayList<IRelationship>();
	}

	public boolean addEntityType(IEntityType entityType) {

		if (entityType == null) {
			throw new IllegalArgumentException("<null> is not a valid argument");
		}

		return entityTypes.add(entityType);
	}

	public boolean addRelationship(IRelationship relationship) {

		if (relationship == null) {
			throw new IllegalArgumentException("<null> is not a valid argument");
		}

		return relationships.add(relationship);
	}

	public String getName() {
		return name;
	}

	public List<IEntityType> getEntityTypes() {
		return entityTypes;
	}

	public List<IRelationship> getRelationships() {
		return relationships;
	}

	public void setName(String name) {

		if (name == null) {
			throw new IllegalArgumentException("<null> is not a valid argument");
		}

		this.name = name;
	}

	public void setEntityTypes(List<IEntityType> entityTypes) {

		if (entityTypes == null) {
			throw new IllegalArgumentException("<null> is not a valid argument");
		}

		this.entityTypes = entityTypes;

	}

	public void setRelationships(List<IRelationship> relationships) {

		if (relationships == null) {
			throw new IllegalArgumentException("<null> is not a valid argument");
		}

		this.relationships = relationships;

	}

	@Override
	public String toString() {
		return name;
	}

	public IEntityRelationshipModel clone() {
		IEntityRelationshipModel erm = new EntityRelationshipModel(getName());

		for (IEntityType entityType : getEntityTypes()) {
			erm.addEntityType(entityType.clone());
		}

		for (IRelationship relationship : getRelationships()) {
			erm.addRelationship(relationship.clone());
		}

		return erm;
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

		IEntityRelationshipModel erm = (IEntityRelationshipModel) object;

		/*
		 * Do the objects have the same name?
		 */
		if (!getName().equals(erm.getName())) {
			return false;
		}

		/*
		 * Do the objects have the same entity types?
		 */
		if (getEntityTypes().size() != erm.getEntityTypes().size()) {
			return false;
		}

		for (int i = 0; i < getEntityTypes().size(); i++) {
			if (!getEntityTypes().get(i).equals(erm.getEntityTypes().get(i))) {
				return false;
			}
		}

		/*
		 * Do the objects have the same relationships?
		 */
		if (getRelationships().size() != erm.getRelationships().size()) {
			return false;
		}

		for (int i = 0; i < getRelationships().size(); i++) {
			if (!getRelationships().get(i)
					.equals(erm.getRelationships().get(i))) {
				return false;
			}
		}

		return true;
	}
}