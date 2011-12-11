package com.github.meo.db.tool.domain;

import java.util.ArrayList;
import java.util.List;

public class EntityRelationshipModel implements IEntityRelationshipModel {

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

}