package com.github.meo.db.tool.domain;

public class RelationshipImpl implements Relationship {

	String name;
	Entity referencedEntity;
	Cardinality cardinality;

	public RelationshipImpl() {

	}

	public RelationshipImpl(String name) {
		setName(name);
	}

	public RelationshipImpl(Entity referencedEntity,
			Cardinality cardinality) {
		setReferencedEntity(referencedEntity);
		setCardinality(cardinality);
	}

	public RelationshipImpl(String name, Entity entity,
			Entity referencedEntity, Cardinality cardinality) {
		setName(name);
		setReferencedEntity(referencedEntity);
		setCardinality(cardinality);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Entity getReferencedEntity() {
		return referencedEntity;
	}

	public Cardinality getCardinality() {
		return cardinality;
	}

	public void setReferencedEntity(Entity referencedEntity) {
		this.referencedEntity = referencedEntity;
	}

	public void setCardinality(Cardinality cardinality) {
		this.cardinality = cardinality;
	}

	public String toString() {

		// named entity
		if (name != null) {
			return getName();
		}

		return String.format("'Relationship to %s'", referencedEntity);
	}
}
