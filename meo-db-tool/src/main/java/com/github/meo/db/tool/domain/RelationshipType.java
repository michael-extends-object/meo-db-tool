package com.github.meo.db.tool.domain;

import org.springframework.util.Assert;


public class RelationshipType implements IRelationshipType, Cloneable {

	private String name;
	private IEntityType entityType;
	private IEntityType referencedEntityType;
	private Cardinality cardinality;

	public RelationshipType() {
		init();
	}

	public RelationshipType(String name) {
		init();
		setName(name);
	}

	public RelationshipType(IEntityType entityType,
			IEntityType referencedEntityType) {
		init();
		setEntityType(entityType);
		setReferencedEntityType(referencedEntityType);
	}

	public RelationshipType(String name, IEntityType entityType,
			IEntityType referencedEntityType, Cardinality cardinality) {
		init();
		setName(name);
		setEntityType(entityType);
		setReferencedEntityType(referencedEntityType);
		setCardinality(cardinality);
	}

	private void init() {
		setName("");
		setEntityType(new EntityType());
		setReferencedEntityType(new EntityType());
		setCardinality(Cardinality.OneToOne);
	}

	public String getName() {
		return name;
	}

	public IEntityType getEntityType() {
		return entityType;
	}

	public IEntityType getReferencedEntityType() {
		return referencedEntityType;
	}

	public Cardinality getCardinality() {
		return cardinality;
	}

	public IRelationship getRelationship() {
		return new Relationship(this);
	}

	public void setName(String name) {

		Assert.notNull(name);

		this.name = name;
	}

	public void setEntityType(IEntityType entityType) {

		Assert.notNull(entityType);

		this.entityType = entityType;
	}

	public void setReferencedEntityType(IEntityType referencedEntityType) {

		Assert.notNull(referencedEntityType);

		this.referencedEntityType = referencedEntityType;
	}

	public void setCardinality(Cardinality cardinality) {

		Assert.notNull(cardinality);

		this.cardinality = cardinality;
	}

	public String toString() {

		// unnamed relationships
		if ("".equals(getName())) {
			return String.format("'Relationship %s to %s'", getEntityType(),
					getReferencedEntityType());
		}

		return getName();
	}

	public IRelationshipType clone() {
		IRelationshipType clone = new RelationshipType();
		clone.setName(getName());
		clone.setEntityType(getEntityType());
		clone.setReferencedEntityType(getReferencedEntityType());
		clone.setCardinality(getCardinality());
		return clone;
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

		IRelationshipType relationshipType = (IRelationshipType) object;

		/*
		 * Do the objects have the same name?
		 */
		if (!getName().equals(relationshipType.getName())) {
			return false;
		}

		/*
		 * Do the objects have the same entity type?
		 */
		if (!getEntityType().equals(relationshipType.getEntityType())) {
			return false;
		}

		/*
		 * Do the objects have the same referenced entity type?
		 */
		if (!getReferencedEntityType().equals(
				relationshipType.getReferencedEntityType())) {
			return false;
		}

		/*
		 * Do the objects have the same cardinality?
		 */
		if (!getCardinality().equals(relationshipType.getCardinality())) {
			return false;
		}

		return true;
	}
}