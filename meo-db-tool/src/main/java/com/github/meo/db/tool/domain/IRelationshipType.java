package com.github.meo.db.tool.domain;


public interface IRelationshipType {

	public String getName();

	public IEntityType getEntityType();

	public IEntityType getReferencedEntityType();

	public Cardinality getCardinality();

	public IRelationship getRelationship();
	
	public void setName(String name);

	public void setEntityType(IEntityType entityType);

	public void setReferencedEntityType(IEntityType referencedEntityType);

	public void setCardinality(Cardinality cardinality);

	public String toString();

	public IRelationshipType clone();

	public boolean equals(Object object);

}